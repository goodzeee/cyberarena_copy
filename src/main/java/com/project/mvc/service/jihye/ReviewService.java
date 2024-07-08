package com.project.mvc.service.jihye;


import com.project.mvc.dto.request.jihye.ReviewModifyDto;
import com.project.mvc.dto.request.jihye.ReviewPostDto;
import com.project.mvc.dto.response.jihye.ReviewDetailDto;
import com.project.mvc.dto.response.jihye.ReviewFindAllDto;
import com.project.mvc.dto.response.jihye.ReviewListDto;
import com.project.mvc.dto.response.kibeom.ReviewAsideListDto;
import com.project.mvc.dto.seongjin.LoginUserInfoDto;
import com.project.mvc.dto.seongjin.ReviewAllowDto;
import com.project.mvc.entity.LikeLog;
import com.project.mvc.entity.Review;
import com.project.mvc.mapper.jihye.LikeLogMapper;
import com.project.mvc.mapper.jihye.ReviewMapper;
import com.project.mvc.mapper.kibeom.DiscussionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewMapper reviewMapper;
    private final LikeLogMapper likeLogMapper;
    private final DiscussionMapper discussionMapper;

    // 1. 특정 미디어에 달린 리뷰 목록 조회 요청 중간처리
    public ReviewListDto findList(long mediaNo) {
        List<ReviewFindAllDto> reviews = reviewMapper.findAll(mediaNo);
//        int totalReviewCount = reviewMapper.count(mediaNo);

        // 리뷰 목록 전체 조회 초기 렌더링에 그려질 데이터
//        ReviewDetailDto responseDto = new ReviewDetailDto((ReviewFindAllDto) reviews);
//        responseDto.setLikeCount(likeLogMapper.countLikes(reviewNo));
//
//        LikeLog reaction = likeLogMapper.findOne(mediaNo);
//
//        String type = null;
//        if (reaction != null) {
//            type = "like";
//        }
//        responseDto.setUserReaction(type);

//        log.info("email: {}", email);
        List<ReviewDetailDto> dtoList = reviews.stream()
                .map(r -> {
                    log.info("r: {}", r);
                    ReviewDetailDto dto = new ReviewDetailDto(r);
                    dto.setLikeCount(likeLogMapper.countLikes(r.getReviewNo()));
                    LikeLog likeLog = likeLogMapper.findOne(r.getReviewNo(), r.getEmail());
                    log.info("likeLog: {}", likeLog);
                    dto.setUserReaction(likeLog != null ? "like" : null); // 사용자 리액션 상태 설정
                    return dto;
                })
                .collect(Collectors.toList());

//        log.info("{}", dtoList.get(0));

        return ReviewListDto.builder()
                .mediaNo(mediaNo)
                .reviews(dtoList)
                .build();
    }

    // 2. 리뷰 등록
    public boolean register(ReviewPostDto dto, HttpSession session) {

        Review r = dto.toEntity();
        //계정명을 엔터티에 추가 - 세션에서 계정명 가져오기
//        r.setEmail(LoginDto.getLoggedInUserAccount(session));

        LoginUserInfoDto loginUser = (LoginUserInfoDto) session.getAttribute("login");
        loginUser.getEmail();

        boolean flag = reviewMapper.save(r);
        if (flag) {
            reviewMapper.updateMediaRating(r.getMediaNo());
        }
        return flag;
    }

    // 3. 리뷰 수정
    public ReviewListDto modify(ReviewModifyDto dto) {

        reviewMapper.modify(dto.toEntity());
        reviewMapper.updateMediaRating(dto.getMno());
        return findList(dto.getReviewNo());
    }

    // 4. 리뷰 삭제
//    public boolean getRemove(long reviewNo) {
//        return reviewMapper.delete(reviewNo);
//    }

    // 4. 리뷰 삭제
    public ReviewListDto getRemove(long reviewNo) {
        log.info("Removing reviewNo: {}", reviewNo);

        // 리뷰 번호로 원본 미디어 번호 찾기
        long mno = (long) reviewMapper.findMno(reviewNo);
        log.info("mno: {}", mno);
        // 찾은 리뷰 번호 삭제
        boolean flag = reviewMapper.delete(reviewNo);
        ReviewListDto list = findList(mno);
        if (list.getReviews().isEmpty()) {
            reviewMapper.updateRatingWhenDeleteLastReview(mno);
        } else {
            reviewMapper.updateMediaRating(mno);
        }

        // 삭제 성공 시 해당 미디어의 리뷰 목록 반환
        return flag ? list : null;
    }

    public List<ReviewAsideListDto> findAsideList() {
        return reviewMapper.findAsideList();
    }

    public ReviewAllowDto allowReview(long reviewNo) {
        ReviewAllowDto allowDto = reviewMapper.getAllowDto(reviewNo);
        return allowDto;
    }
}


