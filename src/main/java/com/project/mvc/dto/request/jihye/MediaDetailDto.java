package com.project.mvc.dto.request.jihye;

import com.project.mvc.dto.response.jihye.ReviewDetailDto;
import com.project.mvc.dto.response.jihye.ReviewFindAllDto;
import com.project.mvc.entity.Media;
import com.project.mvc.entity.Review;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaDetailDto {

    private long mediaNo;
    private int categoryNo;
    private String mediaTitle;
    private String creator;
    private double rating;
    private String mediaDesc;
    private int createdAt;
    private String imageUrl;

    @Setter
    private List<ReviewFindAllDto> reviews;
//    private List<ReviewDetailDto> reviews;

    public MediaDetailDto(Media media, List<ReviewFindAllDto> reviews) {
        this.mediaNo = media.getMediaNo();
        this.mediaTitle = media.getMediaTitle();
        this.creator = media.getCreator();
        this.rating = media.getRating();
        this.mediaDesc = media.getMediaDesc();
        this.createdAt = media.getCreatedAt();
        this.imageUrl = media.getImageUrl();

        this.reviews = reviews;
    }

    public MediaDetailDto(Media media) {

        this.mediaNo = media.getMediaNo();
        this.mediaTitle = media.getMediaTitle();
        this.creator = media.getCreator();
        this.rating = media.getRating();
        this.mediaDesc = media.getMediaDesc();
        this.createdAt = media.getCreatedAt();
        this.imageUrl = media.getImageUrl();

        this.reviews = reviews;
    }
}
