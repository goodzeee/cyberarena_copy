package com.project.mvc.dto.request.kibeom;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscussionCommentRequestDto {
/*
insert into discuss_reply
(discussion_no, email, discussion_reply_content)
values (5, 'abc2@def.com', '과연 그럴까요?');
 */
    private long discussionNo;
    private String email;
    private String discussionReplyContent;

}
