package com.project.mvc.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter @ToString @Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor @Builder
public class Review {

    private long reviewNo;
    private String email;   // 로그인한 글쓴이 이메일
    private long mediaNo;
    @Setter
    private String reviewText;
    private int userRating;
    private DiscussionStatus discussionStatus;
    private LocalDateTime reviewCreatedAt;

//    private int likeCount;  // 리뷰의 좋아요 수
}
