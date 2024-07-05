package com.project.mvc.entity;

import com.project.mvc.dto.response.jihye.ReactionDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeLog {
    private long reviewLikeNo;   // 리뷰 좋아요 번호 PK
    private String email;      // 좋아요 찍은 계정
    private long reviewNo;     // 리뷰 번호
    private LocalDateTime likeCreatedAt;  // 좋아요 클릭 시간 (보여지는게 아니라 갖고 있기 위해)
}

