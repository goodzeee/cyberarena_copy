package com.project.mvc.controller.kibeom;

import com.project.mvc.common.jihye.PageMaker;
import com.project.mvc.common.zyo.Search;
import com.project.mvc.dto.request.kibeom.DiscussionModifyDto;
import com.project.mvc.dto.request.kibeom.MakeDiscussionDto;
import com.project.mvc.dto.response.kibeom.*;
import com.project.mvc.entity.Media;
import com.project.mvc.mapper.kibeom.DiscussionMapper;
import com.project.mvc.mapper.zyo.MediaMapper;
import com.project.mvc.service.jihye.ReviewService;
import com.project.mvc.service.kibeom.DiscussionReplyService;
import com.project.mvc.service.kibeom.DiscussionService;
import com.project.mvc.service.zyo.MediaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/discussion")
public class DiscussionController {

    private final DiscussionService discussionService;
    private final DiscussionReplyService discussionReplyService;
    private final MediaMapper mediaMapper;
    private final DiscussionMapper discussionMapper;
    private final ReviewService reviewService;
    private final MediaService mediaService;

    @GetMapping("/list")
    public String discussionList(@ModelAttribute("s") Search page, Model model) {
        page.setAmount(9);
        List<DiscussResponseDto> dtoList = discussionService.findAll(page);
        List<Media> mList = mediaMapper.findAll(null);

        PageMaker maker = new PageMaker(page, discussionService.getCount(page));

        model.addAttribute("maker", maker);
        model.addAttribute("dList", dtoList);
        model.addAttribute("mList", mList);
        return "discussion/list";
    }

    @GetMapping("/register")
    public String register() {
        return "discussion/register";
    }


    @PostMapping("/register")
    public String makeDiscussion(MakeDiscussionDto dto) {
        MakeDiscussionDto insertDto = discussionService.getMediaNo(dto);
        log.debug("insert dto: {}", insertDto);
        boolean flag = discussionMapper.insert(insertDto);
        if (flag) {
            return "redirect:/discussion/list";
        } else {
            return "redirect:/index";
        }
    }


    @GetMapping("/detail")
    public String discussionDetail(Model model, long dno, HttpServletRequest request, Search page) {
        DiscussionDetailResponseDto foundDsc = discussionService.findOne(dno);
        page.setAmount(10);
        List<DiscussAsideListDto> asideList = discussionService.findAsideList();
//        List<ReviewAsideListDto> reviewList = reviewService.findAsideList();

        List<MediaAsideListDto> mediaList = mediaService.findAsideList();

        // 댓글 수
        long count = discussionReplyService.getCount(dno);

        // 조회수 상승
        discussionService.updateViewCount(dno);

        model.addAttribute("count", count);
        model.addAttribute("found", foundDsc);
        model.addAttribute("aList", asideList);
        model.addAttribute("mList", mediaList);
//        model.addAttribute("rList", reviewList);

        String ref = request.getHeader("Referer");
        model.addAttribute("ref", ref);
        return "discussion/detail";
    }

    @GetMapping("/remove")
    public String discussionRemove(long dno) {

        boolean flag = discussionService.remove(dno);

        if (flag) {
            return "redirect:/discussion/list";
        } else {
            return "redirect:/index";
        }
    }


    @PostMapping("/modify")
    public String discussModify(DiscussionModifyDto dto) {

        log.debug("dto: {}", dto);

        boolean flag = discussionService.modify(dto);

        if (flag) {
            return "redirect:/discussion/detail?dno=" + dto.getDiscussionNo();
        } else {
            return "redirect:/discussion/list";
        }
    }

    @GetMapping("/list/{sort}")
    @ResponseBody
    public ResponseEntity<?> sortDiscussions(@PathVariable("sort") String sort) {
        log.debug("sort: {}", sort);
        List<DiscussResponseDto> sortedDiscussions = discussionService.getSortedDiscussions(sort);
        return ResponseEntity.ok(sortedDiscussions);
    }



}
