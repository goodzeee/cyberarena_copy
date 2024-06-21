package com.project.mvc.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter @ToString @Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor @Builder
public class Review {

    private long reviewNo;
    private String email;
    private long mediaNo;
    @Setter
    private String reviewText;
    private double userRating;
    private DiscussionStatus discussionStatus;
    private LocalDateTime reviewCreatedAt;

//    private User user;
//
//    private Media media;
}
