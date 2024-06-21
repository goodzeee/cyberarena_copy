package com.project.mvc.service.jihye;


import com.project.mvc.common.jihye.Page;
import com.project.mvc.common.jihye.PageMaker;
import com.project.mvc.common.zyo.Search;
import com.project.mvc.dto.request.jihye.ReviewPostDto;
import com.project.mvc.dto.response.jihye.ReviewDetailDto;
import com.project.mvc.dto.response.jihye.ReviewListDto;
import com.project.mvc.entity.Review;
//import com.project.mvc.mapper.MediaMapper;
import com.project.mvc.mapper.jihye.ReviewMapper;
import com.project.mvc.mapper.zyo.MediaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final MediaMapper mediaMapper;
    private final ReviewMapper reviewMapper;

    // 1. 특정 미디어에 달린 리뷰 목록 조회 요청 중간처리
    public List<ReviewDetailDto> findList(long mediaNo) {

        List<Review> reviews = reviewMapper.findAll(mediaNo);

        return reviews.stream()
                .map(r -> new ReviewDetailDto(r))
                .collect(Collectors.toList());
    }

    // 2. 리뷰 등록
    public boolean register(ReviewPostDto dto) {
        Review review = Review.builder()
                .reviewText(dto.getReviewText())
                .email(dto.getEmail())
                .userRating(dto.getUserRating())
                .discussionStatus(dto.getDiscussionStatus())
                .mediaNo(dto.getMediaNo())
                .build();

        boolean flag = reviewMapper.save(review);
        if (flag) log.info("댓글 등록 성공 ! - {}", dto);
        else log.warn("댓글 등록 실패");
        return flag;
    }

    // 3. 리뷰 수정
    public boolean modify(ReviewModifyDto dto) {
        Review review = Review.builder()
                .reviewNo(dto.getReviewNo())
                .reviewText(dto.getReviewText())
                .userRating(dto.getUserRating())
                .discussionStatus(dto.getDiscussionStatus())
                .build();
        return reviewMapper.modify(review);
    }

    // 4. 리뷰 삭제
    public List<ReviewDetailDto> remove (long rno) {
        // 댓글 번호로 원본 글번호 찾기
        long mediaNo = reviewMapper.findMno(rno);

        boolean flag = reviewMapper.delete(rno);

        // 삭제 후 삭제된 목록 리턴
        return flag ? findList(mediaNo) : Collections.emptyList();
//        return reviewMapper.delete(reviewNo);
    }
}
