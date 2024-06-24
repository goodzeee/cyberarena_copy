package com.project.mvc.controller.zyo;

import com.project.mvc.dto.request.jihye.MediaDetailDto;
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

    // 특정 미디어 상세 조회 및 리뷰 목록 표시
    @GetMapping("/{mediaNo}")
    public String detail(@PathVariable("mediaNo") long mediaNo, Model model) {
//        log.info("/media/{} : GET", mediaNo);

        // 특정 미디어의 상세 정보 및 리뷰 목록 조회
        MediaDetailDto mediaDetailDto = mediaService.detail(mediaNo);

        if (mediaDetailDto == null) {
            // 예외 처리 로직 필요
            return "error"; // 예시로 "error" 페이지 반환
        }

        model.addAttribute("mmm", mediaDetailDto);

        return "reviewjsp/detail";
    }
}
