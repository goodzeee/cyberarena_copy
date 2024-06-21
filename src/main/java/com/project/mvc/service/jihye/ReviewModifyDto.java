package com.project.mvc.service.jihye;

import com.project.mvc.entity.DiscussionStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class ReviewModifyDto {

    private long reviewNo;
    private String reviewText;
    private double userRating;
    private DiscussionStatus discussionStatus;
    private LocalDateTime reviewCreatedAt;
}