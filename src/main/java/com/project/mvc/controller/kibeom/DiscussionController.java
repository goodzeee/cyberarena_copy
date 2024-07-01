package com.project.mvc.controller.kibeom;

import com.project.mvc.common.jihye.Page;
import com.project.mvc.common.jihye.PageMaker;
import com.project.mvc.common.zyo.Search;
import com.project.mvc.dto.request.kibeom.DiscussionCommentRequestDto;
import com.project.mvc.dto.request.kibeom.DiscussionModifyDto;
import com.project.mvc.dto.request.kibeom.MakeDiscussionDto;
import com.project.mvc.dto.response.kibeom.DiscussFindAllDto;
import com.project.mvc.dto.response.kibeom.DiscussResponseDto;
import com.project.mvc.dto.response.kibeom.DiscussionDetailResponseDto;
import com.project.mvc.entity.Discussion;
import com.project.mvc.entity.Media;
import com.project.mvc.mapper.kibeom.DiscussionMapper;
import com.project.mvc.mapper.zyo.MediaMapper;
import com.project.mvc.service.kibeom.DiscussionReplyService;
import com.project.mvc.service.kibeom.DiscussionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    @GetMapping("/list")
    public String discussionList(@ModelAttribute("s") Search page, Model model) {
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
        boolean flag = discussionMapper.insert(insertDto);
        if (flag) {
            return "redirect:/discussion/list";
        } else {
            return "redirect:/index";
        }
    }


    @GetMapping("/detail")
    public String discussionDetail(Model model, long dno, HttpServletRequest request) {
        DiscussionDetailResponseDto foundDsc = discussionService.findOne(dno);

        // 댓글 수
        long count = discussionReplyService.getCount(dno);

        // 조회수 상승
        discussionService.updateViewCount(dno);

        model.addAttribute("count", count);
        model.addAttribute("found", foundDsc);
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
            return "redirect:/discussion/list"; // 일단 리스트로 돌려보냄
        }
    }





}
