package com.project.mvc.dto.request.kibeom;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MakeDiscussionDto {

    /*
    - 특정 미디어 선택
    - 주제 입력
    - 세부내용 입력
    discussion_no INT(8) PRIMARY KEY auto_increment,
    email VARCHAR(255),
    media_no INT(8),
    review_no INT(8),
    discussion_title TEXT NOT NULL,
    discussion_offer TEXT NOT NULL,
    discussion_created_at DATETIME default current_timestamp,
     */
    private String email;
    private String discussionTitle;
    private String discussionOffer;
}
