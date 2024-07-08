package com.project.mvc.dto.response.kibeom;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MediaAsideListDto {
    private long mediaNo;
    private int categoryNo;
    private String imageUrl;
    private String mediaTitle;
    private double rating;
}
