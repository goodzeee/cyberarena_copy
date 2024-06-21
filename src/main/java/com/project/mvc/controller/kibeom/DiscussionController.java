package com.project.mvc.controller.kibeom;

import com.project.mvc.dto.request.kibeom.MakeDiscussionDto;
import com.project.mvc.dto.response.kibeom.DiscussFindAllDto;
import com.project.mvc.dto.response.kibeom.DiscussResponseDto;
import com.project.mvc.dto.response.kibeom.DiscussionDetailResponseDto;
import com.project.mvc.entity.Discussion;
import com.project.mvc.entity.Media;
import com.project.mvc.mapper.kibeom.DiscussionMapper;
import com.project.mvc.mapper.zyo.MediaMapper;
import com.project.mvc.service.kibeom.DiscussionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/discussion")
public class DiscussionController {

    private final DiscussionService discussionService;
    private final MediaMapper mediaMapper;
    private final DiscussionMapper discussionMapper;

    @GetMapping("/list")
    public String discussionList(Model model) {
        List<DiscussResponseDto> dtoList = discussionService.findAll();
        List<Media> temp = mediaMapper.findAll(null);
        List<Media> mList = new ArrayList<>();

        for (Media media : temp) {
            mList.add(media);
            if (mList.size() > 4) break;
        }
        log.debug("mList size: {}", mList.size());
        model.addAttribute("dList", dtoList);
        model.addAttribute("mList", mList);
        return "discussion/list";
    }

    @PostMapping("/register")
    public String makeDiscussion(MakeDiscussionDto dto) {
        log.debug("makeDiscussion: {}", dto);
        MakeDiscussionDto insertDto = discussionService.getMediaNo(dto);
        boolean flag = discussionMapper.insert(insertDto);
        if (flag) {
            return "redirect:/discussion/list";
        } else {
            return "redirect:/index";
        }
    }

    @GetMapping("/detail")
    public String discussionDetail(Model model, long dno) {
        DiscussionDetailResponseDto foundDsc = discussionService.findOne(dno);
        model.addAttribute("found", foundDsc);
        return "discussion/detail";
    }

}