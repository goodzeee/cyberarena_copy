package com.project.mvc.dto.response.jihye;

import com.project.mvc.entity.DiscussionStatus;
import com.project.mvc.entity.Review;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class ReviewFindAllDto {

    private long reviewNo;
    private String reviewText; // 리뷰 내용을 담을 필드
    private String email; // 리뷰 작성자를 담을 필드
    private double userRating;
    private DiscussionStatus discussionStatus;
    private LocalDateTime reviewCreatedAt;
    private long mediaNo;

    private int likeCount;  // 좋아요 수 추가
    private String nickname;
}

//    // Review 엔티티에서 ReviewFindAllDto로 매핑하는 생성자
//    public ReviewFindAllDto(Review r) {
//        this.reviewNo = r.getReviewNo();
//        this.reviewText = r.getReviewText(); // Review 엔티티의 리뷰 내용을 가져옴
//        this.email = r.getEmail(); // Review 엔티티의 리뷰 작성자 이메일을 가져옴
//        this.userRating = r.getUserRating();
//        this.discussionStatus = r.getDiscussionStatus();
//        this.reviewCreatedAt = r.getReviewCreatedAt();
//        this.mediaNo = r.getMediaNo();
//    }
//}
