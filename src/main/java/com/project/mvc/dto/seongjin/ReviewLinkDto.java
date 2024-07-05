package com.project.mvc.dto.seongjin;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewLinkDto {
    private long reviewNo;
    private double userRating;
    private String reviewText;
    private LocalDateTime reviewCreatedAt;
    private long mediaNo;
    private int categoryNo;
    private String mediaTitle;
    private String imageUrl;

    @Setter
    private String category;

}
