package com.project.mvc.mapper.jihye;

import com.project.mvc.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    // 선택한 미디어에 리뷰 등록 (email, 별점, 내용, 토론신청 여부)
    boolean saveReview(Review review);

    // 선택한 미디어에 달린 리뷰 전체조회
    List<Review> findAll(long mediaNo);

    // 리뷰 수정
    boolean updateReview(Review review);

    // 리뷰 삭제
    boolean deleteReview(long reviewNo);
}

