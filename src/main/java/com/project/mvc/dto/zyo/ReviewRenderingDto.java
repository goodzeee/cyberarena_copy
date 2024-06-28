package com.project.mvc.dto.zyo;

import lombok.*;

import java.time.LocalDateTime;

@Getter @ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ReviewRenderingDto {
    //user
    private String nickname;
    private String email;
    //review
    private long reviewNo;
    private double userRating;
    private String reviewText;
    private LocalDateTime reviewCreatedAt;
    //media
    private long mediaNo;
    private String mediaTitle;
    private String imageUrl;
}
