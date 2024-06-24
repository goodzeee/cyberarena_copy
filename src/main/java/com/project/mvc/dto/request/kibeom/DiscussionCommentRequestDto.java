package com.project.mvc.dto.request.kibeom;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class DiscussionCommentRequestDto {
    private long discussionNo;
    private String email;
    private String nickname;
    private String discussionReplyContent;
    private LocalDateTime discussionReplyCreatedAt;
}