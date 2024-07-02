package com.project.mvc.controller.zyo;

import com.project.mvc.dto.request.jihye.MediaDetailDto;
import com.project.mvc.dto.response.jihye.ReviewFindAllDto;
import com.project.mvc.dto.response.kibeom.DiscussFindAllDto;
import com.project.mvc.dto.zyo.DiscussRenderingDto;
import com.project.mvc.dto.zyo.ReviewRenderingDto;
import com.project.mvc.entity.Discussion;
import com.project.mvc.entity.Media;
import com.project.mvc.entity.Review;
import com.project.mvc.service.zyo.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @GetMapping("/movie")
    public String movie(Model model) {
        int categoryNo = 1; // 해당 카테고리 번호
        List<Media> media = mediaService.findImageUrlByCategory(categoryNo);
        List<ReviewRenderingDto> reviews = mediaService.findAllReviews(categoryNo);
        List<DiscussRenderingDto> discussions = mediaService.findAllDiscussions(categoryNo);
        model.addAttribute("reviews", reviews);
        model.addAttribute("discussions", discussions);
        model.addAttribute("media", media);
        return "media/movie";
    }

    @GetMapping("/series")
    public String series(Model model) {
        int categoryNo = 2; // 해당 카테고리 번호
        List<Media> media = mediaService.findImageUrlByCategory(categoryNo);
        List<ReviewRenderingDto> reviews = mediaService.findAllReviews(categoryNo);
        List<DiscussRenderingDto> discussions = mediaService.findAllDiscussions(categoryNo);
        model.addAttribute("reviews", reviews);
        model.addAttribute("discussions", discussions);
        model.addAttribute("media", media);
        return "media/series";
    }

    @GetMapping("/book")
    public String book(Model model) {
        int categoryNo = 3; // 해당 카테고리 번호
        List<Media> media = mediaService.findImageUrlByCategory(categoryNo);
        List<ReviewRenderingDto> reviews = mediaService.findAllReviews(categoryNo);
        List<DiscussRenderingDto> discussions = mediaService.findAllDiscussions(categoryNo);
        model.addAttribute("reviews", reviews);
        model.addAttribute("discussions", discussions);
        model.addAttribute("media", media);
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

    @GetMapping("/info")
    @ResponseBody
    public ResponseEntity<?> findMediaInfo(@RequestParam long mediaNo) {
        Media media = mediaService.findMedia(mediaNo);
        return ResponseEntity.ok()
                .body(media);

    }




}
