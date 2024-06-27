package com.project.mvc.service.jihye;


import com.project.mvc.common.jihye.Page;
import com.project.mvc.common.jihye.PageMaker;
import com.project.mvc.dto.request.jihye.ReviewModifyDto;
import com.project.mvc.dto.request.jihye.ReviewPostDto;
import com.project.mvc.dto.response.jihye.ReviewDetailDto;
import com.project.mvc.dto.response.jihye.ReviewFindAllDto;
import com.project.mvc.dto.response.jihye.ReviewListDto;
import com.project.mvc.entity.Review;
import com.project.mvc.entity.User;
import com.project.mvc.mapper.jihye.ReviewMapper;
import com.project.mvc.mapper.zyo.MediaMapper;
import com.project.mvc.service.seongjin.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final MediaMapper mediaMapper;
    private final ReviewMapper reviewMapper;
    private final UserService userService; // UserService를 주입 받음

    // 1. 특정 미디어에 달린 리뷰 목록 조회 요청 중간처리
    public ReviewListDto findList(long mediaNo) {

        List<ReviewFindAllDto> reviews = reviewMapper.findAll(mediaNo);

        List<ReviewDetailDto> dtoList = reviews.stream()
                .map(r -> new ReviewDetailDto(r))
                .collect(Collectors.toList());

        return ReviewListDto.builder()
                .reviews(dtoList)
//                .pageInfo(new PageMaker(page, reviewMapper.count(mediaNo)))
//                .loginUser(LoginUtil.getLoggedInUser(session))
                .build();

//        List<Review> reviews = reviewMapper.findAll(mediaNo);

//        List<ReviewDetailDto> dtoList = reviews.stream()
//                .map(r -> new ReviewDetailDto(r))
//                .collect(Collectors.toList());
//
//        return ReviewListDto.builder()
//                .reviews(dtoList)
//                .build();

//        List<ReviewDetailDto> reviewDetailDtoList = reviews.stream()
//                .map(review -> new ReviewDetailDto(review))
//                .collect(Collectors.toList());
//        return reviewDetailDtoList;
//        return reviews.stream()
//                .map(r -> new ReviewDetailDto(r))
//                .collect(Collectors.toList());
    }

    // 2. 리뷰 등록
    public boolean register(ReviewPostDto dto) {

        try {
            Review review = Review.builder()
                    .reviewText(dto.getReviewText())
                    .email(dto.getEmail())
                    .nickname(dto.getNickname())
                    .userRating(dto.getUserRating())
                    .discussionStatus(dto.getDiscussionStatus())
                    .mediaNo(dto.getMediaNo())
                    .build();

            boolean registrationSuccess = reviewMapper.save(review);

            if (registrationSuccess) {
                log.info("Review successfully saved: {}", dto);
            } else {
                log.warn("Failed to save review: {}", dto);
            }

            return registrationSuccess;
        } catch (Exception e) {
            log.error("Error occurred while registering review: {}", e.getMessage());
            return false;
        }
    }
//        Review review = Review.builder()
//                .reviewText(dto.getReviewText())
//                .email(dto.getEmail())
////                .email(LoginUtil.getLoggedInUserAccount(session))
//                .nickname(dto.getNickname())
//                .userRating(dto.getUserRating())
//                .discussionStatus(dto.getDiscussionStatus())
//                .mediaNo(dto.getMediaNo())
//                .build();
//
//        boolean flag = reviewMapper.save(review);
//        if (flag) log.info("댓글 등록 성공 ! - {}", dto);
//        else log.warn("댓글 등록 실패");
//        return flag;
//        return reviewMapper.save(review);

    // 3. 리뷰 수정
    public ReviewListDto modify(ReviewModifyDto dto) {
//        Review review = Review.builder()
//                .reviewNo(dto.getReviewNo())
//                .reviewText(dto.getReviewText())
//                .userRating(dto.getUserRating())
//                .discussionStatus(dto.getDiscussionStatus())
//                .build();
        reviewMapper.modify(dto.toEntity());
        return findList(dto.getReviewNo());
//        return reviewMapper.modify(review);
    }

    // 4. 리뷰 삭제
    public ReviewListDto remove (long reviewNo) {
        // 댓글 번호로 원본 글번호 찾기
        long mediaNo = reviewMapper.findMno(reviewNo);

        boolean deleted = reviewMapper.delete(reviewNo);

        return deleted ? findList(mediaNo) : null;
//        if (deleted) {
//            List<ReviewDetailDto> reviewList = findList(mediaNo);
//            return ReviewListDto.builder()
//                    .reviews(reviewList)
//                    .build();
//        } else {
//            throw new RuntimeException("리뷰 삭제에 실패했습니다.");
//        }
    }

}
