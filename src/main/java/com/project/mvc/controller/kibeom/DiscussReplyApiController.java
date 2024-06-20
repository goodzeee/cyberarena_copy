package com.project.mvc.controller.kibeom;

import com.project.mvc.dto.request.kibeom.DiscussionCommentRequestDto;
import com.project.mvc.entity.DiscussReply;
import com.project.mvc.mapper.kibeom.DiscussReplyMapper;
import com.project.mvc.service.kibeom.DiscussionReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/discuss/reply")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class DiscussReplyApiController {

    private final DiscussReplyMapper discussReplyMapper;
    private final DiscussionReplyService discussionReplyService;

    @GetMapping("/{dno}")
    public ResponseEntity<?> getReplies(@PathVariable("dno") long dno) {
        List<DiscussReply> all = discussReplyMapper.findAll(dno);
//        for (DiscussReply discussReply : all) {
//            String s = discussionReplyService.dateFormatting(discussReply.getDiscussionReplyCreatedAt());
//            discussReply.setDiscussionReplyCreatedAt(LocalDateTime.parse(s));
//        }
        if (all == null) {
            String message = "댓글 없다.";
            log.warn(message);
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok().body(all);
    }

    @PostMapping
    public ResponseEntity<?> createReply(@RequestBody DiscussionCommentRequestDto dto, BindingResult result) {
        log.info("/api/v1/discuss/reply : POST");
        log.debug("parameter: {}", dto);

        boolean flag = discussReplyMapper.insert(dto);
        if (!flag) return ResponseEntity.internalServerError().body("댓글 등록 실패");

        return ResponseEntity.ok().body(dto);
    }
}