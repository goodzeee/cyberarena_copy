package com.project.mvc.controller;

import com.project.mvc.entity.Media;
import com.project.mvc.mapper.MediaMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {


    private final MediaMapper mediaMapper;

    @GetMapping("/index")
    public String index(Model model) {
        List<Media> all = mediaMapper.findAll();
        model.addAttribute("mediaList", all);
        return "index";
    }
}
