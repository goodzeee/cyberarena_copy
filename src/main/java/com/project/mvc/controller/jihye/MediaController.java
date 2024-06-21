package com.project.mvc.controller.jihye;

import com.project.mvc.dto.request.jihye.MediaDetailDto;
import com.project.mvc.entity.Media;
import com.project.mvc.service.zyo.MediaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/media")
public class MediaController {

    private final MediaService mediaService;

    // 특정 미디어 상세 조회 및 리뷰 목록 표시
    @GetMapping("/{mediaNo}")
    public String detail(@PathVariable("mediaNo") long mediaNo, Model model) {
        log.info("/media/{} : GET", mediaNo);

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
