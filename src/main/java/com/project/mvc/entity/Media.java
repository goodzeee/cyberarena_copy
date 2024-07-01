package com.project.mvc.entity;

import com.project.mvc.dto.response.jihye.ReviewDetailDto;
import com.project.mvc.dto.response.kibeom.DiscussFindAllDto;
import com.project.mvc.dto.response.kibeom.DiscussResponseDto;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Media {

    private long mediaNo;
    private int categoryNo;
    private String mediaTitle;
    private String creator;
    private double rating;
    private String mediaDesc;
    private int createdAt;
    private String imageUrl;

    private List<ReviewDetailDto> reviews;

    private List<DiscussFindAllDto> discussions;
}
