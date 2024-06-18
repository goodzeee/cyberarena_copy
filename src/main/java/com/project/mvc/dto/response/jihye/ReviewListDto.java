package com.project.mvc.dto.response.jihye;

import com.project.mvc.common.jihye.PageMaker;
import com.project.mvc.entity.Media;
import com.project.mvc.entity.Review;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewListDto {

    private Media media;
    private List<Review> reviews;

    // 로그인한 사용자 정보 가져와 리뷰 조회하기 위해 필요
//    @Setter
//    private LoginUserInfoDto loginUser;
}
