package com.project.mvc.mapper.zyo;

import com.project.mvc.common.zyo.Search;
import com.project.mvc.dto.zyo.DiscussRenderingDto;
import com.project.mvc.dto.zyo.ReviewRenderingDto;
import com.project.mvc.entity.Media;
import com.project.mvc.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MediaMapper {

    // 미디어 목록 조회
    List<Media> findAll(Search media);


    // 이미지 URL 조회
    List<Media> findImageUrlByCategory(int categoryNo);

    Media findMedia(long mediaNo);


    // 특정 카테고리에 포함된 미디어에 포함된 모든 리뷰의 정보를 가져온다.
    List<Review> findReviewByCategory(int categoryNo);

    // 특정 카테고리에 포함된 미디어에 포함된 모든 리뷰의 정보를 가져온다.
    List<DiscussRenderingDto> findDiscussRenderInfo(int categoryNo);

    List<ReviewRenderingDto> findReviewRenderInfo(int categoryNo);

    Media findByMediaNo(long mediaNo);
}
