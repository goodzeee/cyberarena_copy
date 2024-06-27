package com.project.mvc.controller.kibeom;

import com.project.mvc.dto.request.kibeom.DiscussionCommentRequestDto;
import com.project.mvc.service.kibeom.DiscussionReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
        long discussionNo = dto.getDiscussionNo();
        discussionReplyService.insert(dto);
        return "redirect:/discussion/detail?dno=" + discussionNo;
    }

    @GetMapping("/reply/remove")
    public String removeDiscussion(long rno, long dno) {
        boolean flag = discussionReplyService.remove(rno);
        if (flag) {
            return "redirect:/discussion/detail?dno=" + dno;
        }
        return "redirect:/index";
    }

    @PostMapping("/reply/modify")
    public String modifyDiscussion(DiscussionCommentRequestDto dto) {
        log.info("/discussion/reply/modify : POST");
        log.debug("parameter: {}", dto);

//        boolean flag = discussionReplyService.update(dto);
//        if (flag) {
//            return "redirect:/discussion/detail?dno=" + dto.getDiscussionNo();
//        }
        return null;
    }
}