package com.project.mvc.controller.kibeom;


import com.project.mvc.common.zyo.Search;
import com.project.mvc.dto.request.kibeom.DiscussionCommentRequestDto;
import com.project.mvc.entity.Media;
import com.project.mvc.mapper.kibeom.DiscussReplyMapper;
import com.project.mvc.mapper.zyo.MediaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/media")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class MediaApiController {

    private final MediaMapper mediaMapper;
    private final DiscussReplyMapper discussReplyMapper;


    @GetMapping("/mediaList/{title}")
    public ResponseEntity<?> mediaList(@PathVariable String title) {

        List<Media> all =  mediaMapper.findAll(Search.builder()
                        .searchType("title")
                        .keyword(title)
                        .build());


        if (all == null) {
            String message = "찾는 미디어가 없습니다.";
            log.warn(message);
            return ResponseEntity
                    .badRequest()
                    .body(message);
        }

        return ResponseEntity
                .ok()
                .body(all);
    }
}
