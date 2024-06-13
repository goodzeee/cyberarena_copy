package com.project.mvc.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    private long reviewNo;
    private String email;
    private long mediaNo;
    private String reviewText;
    private double userRating;
    private String discussionStatus;
    private LocalDateTime reviewCreatedAt;

}
