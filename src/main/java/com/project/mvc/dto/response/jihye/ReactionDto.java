package com.project.mvc.dto.response.jihye;

import lombok.*;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor @Builder
public class ReactionDto {

    // 좋아요 처리를 위해 클라이언트에 보낼 json
    private int likeCount;  // 갱신된 총 좋아요 수

    // 현재 리액션 상태(안눌렀는지, 좋아요 눌렀는지) - active 배경색 처리
    private String userReaction;
}
