package com.project.mvc.controller.kibeom;

import com.project.mvc.dto.request.kibeom.DiscussionCommentRequestDto;
import com.project.mvc.service.kibeom.DiscussionReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/discussion")
public class DiscussionReplyController {

    private final DiscussionReplyService discussionReplyService;

    @PostMapping("/reply/register")
    public String replyToDiscussion(DiscussionCommentRequestDto dto) {

        discussionReplyService.insert(dto);

        return null;
    }

}
