package com.project.mvc.controller.kibeom;

import com.project.mvc.entity.Discussion;
import com.project.mvc.mapper.kibeom.DiscussionMapper;
import com.project.mvc.service.kibeom.DiscussionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/discussion")
public class DiscussionController {

    private final DiscussionService discussionService;

    @GetMapping("/list")
    public String discussionList(Model model) {
        List<Discussion> disList = discussionService.findAll();
        model.addAttribute("dList", disList);
        return "discussion/list";
    }
}
