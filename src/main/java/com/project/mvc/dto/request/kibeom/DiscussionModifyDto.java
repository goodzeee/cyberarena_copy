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
public class DiscussionModifyDto {

    private long discussionNo;
    private String discussionTitle;
    private String discussionOffer;

    // 토론 수정 후 버튼 눌렀을 때의 데이터가 잘 안들어옴
}
