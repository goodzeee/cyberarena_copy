package com.project.mvc.controller;

import com.project.mvc.common.zyo.Search;
import com.project.mvc.entity.Media;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.mvc.mapper.zyo.MediaMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MediaMapper mediaMapper;

    @GetMapping("/index")
    public String index(Search media, Model model) {
        List<Media> all = mediaMapper.findAll(media);
        model.addAttribute("mediaList", all);
        return "index";
    }
}
