package com.project.mvc.dto.response.jihye;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.mvc.entity.DiscussionStatus;
import lombok.*;

import java.time.format.DateTimeFormatter;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
// 리뷰 목록 상세 조회 화면을 보여줄 객체
public class ReviewDetailDto {

    private long mediaNo;

    @JsonProperty("review_no")
    private long reviewNo;
    private String text;
    private String email;
    private String nickname;
    private int userRating;
    private DiscussionStatus discussionStatus;
    private String reviewCreatedAt;

    // 리뷰 목록 전체 조회에 들어왔을 때 초기 화면에 보여줄
    @Setter
    private int likeCount;  // 총 좋아요 수
    @Setter
    private String userReaction;   // 현재 리액션 상태

    public ReviewDetailDto(ReviewFindAllDto r) {
        this.mediaNo = r.getMediaNo();
        this.reviewNo = r.getReviewNo();
        this.text = r.getReviewText();
        this.email = r.getEmail();
        this.nickname = r.getNickname();
        this.userRating = (int) r.getUserRating();
        this.discussionStatus = r.getDiscussionStatus();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a hh시 mm분");
        this.reviewCreatedAt = pattern.format(r.getReviewCreatedAt());
        this.likeCount = r.getLikeCount();
    }

//    public ReviewDetailDto(ReviewFindAllDto r) {
//        this.reviewNo = r.getReviewNo();
//        this.text = r.getReviewText();
//        this.email = r.getEmail();
//        this.nickname = r.getNickname();
//        this.userRating = r.getUserRating();
//        this.discussionStatus = r.getDiscussionStatus();
//
//        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a hh시 mm분");
//        this.reviewCreatedAt = pattern.format(r.getReviewCreatedAt());
//    }
}

