package com.project.mvc.controller.kibeom;

import com.project.mvc.dto.request.kibeom.DiscussionCommentRequestDto;
import com.project.mvc.dto.response.kibeom.DiscussReplyResponseDto;
import com.project.mvc.dto.response.kibeom.ReplyFinalDto;
import com.project.mvc.dto.seongjin.LoginUserInfoDto;
import com.project.mvc.entity.DiscussReply;
import com.project.mvc.mapper.kibeom.DiscussReplyMapper;
import com.project.mvc.service.kibeom.DiscussionReplyService;
import com.project.mvc.service.kibeom.DiscussionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    private final DiscussionService discussionService;

    @GetMapping("/{dno}")
    public ResponseEntity<?> getReplies(@PathVariable("dno") long dno, HttpSession session) {
        List<DiscussReply> all = discussReplyMapper.findAll(dno);
        List<DiscussReplyResponseDto> list = discussionService.convertToDto(all);
        LoginUserInfoDto login = (LoginUserInfoDto)session.getAttribute("login");

        ReplyFinalDto dto = ReplyFinalDto.builder()
                .dtoList(list)
                .loginUserDto(login)
                .build();

        if (list == null) {
            String message = "댓글 없다.";
            log.warn(message);
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<?> createReply(@RequestBody DiscussionCommentRequestDto dto, BindingResult result) {
        log.info("/api/v1/discuss/reply : POST");
        log.debug("parameter: {}", dto);
        dto.setDiscussionReplyCreatedAt(LocalDateTime.now());
        // 닉네임 설정
        dto.setNickname(discussReplyMapper.findNicknameByEmail(dto.getEmail()));
        boolean flag = discussionReplyService.insert(dto);
        if (!flag) return ResponseEntity.internalServerError().body("댓글 등록 실패");
        return ResponseEntity.ok().body(dto);
    }

}