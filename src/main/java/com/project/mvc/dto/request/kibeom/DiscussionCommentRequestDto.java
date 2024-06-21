package com.project.mvc.dto.request.kibeom;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

//    private String formattedDiscussionCreatedAt; // 포맷된 문자열을 저장할 필드 추가
//
//
//    private String dateFormatting(LocalDateTime regDateTime) {
//        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy. MM. dd a HH:mm");
//        return regDateTime.format(pattern);
//    }


}
