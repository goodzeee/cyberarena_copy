package com.project.mvc.dto.request.jihye;

import com.project.mvc.entity.DiscussionStatus;
import com.project.mvc.entity.Review;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class ReviewModifyDto {

    @NotNull
    private long reviewNo;     // 수정할 리뷰의 리뷰 번호
    private String reviewText;  // 수정한 새로운 리뷰 내용
    private double userRating;
    private DiscussionStatus discussionStatus;
//    private LocalDateTime reviewCreatedAt;
    @NotNull
    private long mno;  //수정 완료 후 미디어에 달린 새로운 리뷰 목록을 조회하기 위해

    // 엔터티로 변환하는 메서드
    public Review toEntity() {
        return Review.builder()
                .reviewNo(this.reviewNo)
                .reviewText(this.reviewText)
                .userRating(this.userRating)
                .discussionStatus(this.discussionStatus)
                .mediaNo(this.mno)
                .build();
    }
}
