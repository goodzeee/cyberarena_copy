package com.project.mvc.dto.response.jihye;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.mvc.entity.DiscussionStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ReviewDetailDto {

    @JsonProperty("review_no")
    private long rno;
    private String text;
    private double userRating;
    private DiscussionStatus discussionStatus;
    private LocalDateTime reviewCreatedAt;

    public ReviewDetailDto(ReviewFindAllDto r) {
        this.rno = r.getReviewNo();
        this.text = r.getReviewText();
        this.userRating = r.getUserRating();
        this.discussionStatus = r.getDiscussionStatus();
        this.reviewCreatedAt = r.getReviewCreatedAt();
    }
}
