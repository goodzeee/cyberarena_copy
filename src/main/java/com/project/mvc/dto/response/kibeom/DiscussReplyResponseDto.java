package com.project.mvc.dto.response.kibeom;

import com.project.mvc.dto.seongjin.LoginUserInfoDto;
import com.project.mvc.entity.DiscussReply;
import lombok.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class DiscussReplyResponseDto {

    private long discussionReplyNo;
    private String email;
    private String discussionReplyContent;
    private long discussionNo;
    private String nickname;
    private LocalDateTime discussionReplyCreatedAt;
    private String formattedDiscussionCreatedAt;
    // 로그인 유저



    public DiscussReplyResponseDto(DiscussReply d) {
        this.discussionReplyNo = d.getDiscussionReplyNo();
        this.email = d.getEmail();
        this.discussionReplyContent = d.getDiscussionReplyContent();
        this.discussionNo = d.getDiscussionNo();
        this.nickname = d.getNickname();
        this.discussionReplyCreatedAt = d.getDiscussionReplyCreatedAt();
        this.formattedDiscussionCreatedAt = dateFormatting(d.getDiscussionReplyCreatedAt());


    }

    private String dateFormatting(LocalDateTime regDateTime) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy. MM. dd a HH:mm");
        return pattern.format(regDateTime);
    }

}
