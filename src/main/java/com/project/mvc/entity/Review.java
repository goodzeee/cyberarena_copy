package com.project.mvc.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter @ToString @Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor @Builder
public class Review {

    private long reviewNo;
    private String email;   // 로그인한 이메일
    private long mediaNo;
    @Setter
    private String reviewText;
    private double userRating;
    private DiscussionStatus discussionStatus;
    private LocalDateTime reviewCreatedAt;

    private String nickname; // 추가된 필드: 리뷰 작성자의 닉네임
}
