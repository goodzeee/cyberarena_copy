package com.project.mvc.controller.zyo;

import com.project.mvc.service.zyo.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @GetMapping("/movie")
    public String movie(Model model) {
        int categoryNo = 1; // 해당 카테고리 번호
        List<String> imageUrls = mediaService.findImageUrlByCategory(categoryNo);
        System.out.println("imageUrls = " + imageUrls.size());
        model.addAttribute("imageUrls", imageUrls);
        return "media/movie";
    }

    @GetMapping("/series")
    public String series(Model model) {
        int categoryNo = 2; // 해당 카테고리 번호
        List<String> imageUrls = mediaService.findImageUrlByCategory(categoryNo);
        System.out.println("imageUrls = " + imageUrls.size());
        model.addAttribute("imageUrls", imageUrls);
        return "media/series";
    }

    @GetMapping("/book")
    public String book(Model model) {
        int categoryNo = 3; // 해당 카테고리 번호
        List<String> imageUrls = mediaService.findImageUrlByCategory(categoryNo);
        System.out.println("imageUrls = " + imageUrls.size());
        model.addAttribute("imageUrls", imageUrls);
        return "media/book";
    }

}
