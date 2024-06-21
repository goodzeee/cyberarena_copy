package com.project.mvc.dto.request.kibeom;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscussionCommentRequestDto {

    private long discussionNo;
    private String email;
    private String discussionReplyContent;

}
