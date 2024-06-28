package com.project.mvc.service.jihye;


import com.project.mvc.common.jihye.Page;
import com.project.mvc.common.jihye.PageMaker;
import com.project.mvc.dto.request.jihye.ReviewModifyDto;
import com.project.mvc.dto.request.jihye.ReviewPostDto;
import com.project.mvc.dto.response.jihye.ReviewDetailDto;
import com.project.mvc.dto.response.jihye.ReviewFindAllDto;
import com.project.mvc.dto.response.jihye.ReviewListDto;
import com.project.mvc.dto.seongjin.LoginDto;
import com.project.mvc.dto.seongjin.LoginUserInfoDto;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
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
                .build();
    }

    // 2. 리뷰 등록
    public boolean register(ReviewPostDto dto, HttpSession session) {

        Review r = dto.toEntity();
        //계정명을 엔터티에 추가 - 세션에서 계정명 가져오기
//        r.setEmail(LoginDto.getLoggedInUserAccount(session));

        LoginUserInfoDto loginUser = (LoginUserInfoDto) session.getAttribute("login");
        loginUser.getEmail();

            return reviewMapper.save(r);
    }

    // 3. 리뷰 수정
    public ReviewListDto modify(ReviewModifyDto dto) {

        reviewMapper.modify(dto.toEntity());
        return findList(dto.getReviewNo());
    }

    // 4. 리뷰 삭제
    public ReviewListDto remove(long reviewNo) {
        log.info("Removing review with reviewNo: {}", reviewNo);

        // 댓글 번호로 원본 미디어 번호 찾기
        long mediaNo = reviewMapper.findMno(reviewNo);

        boolean deleted = reviewMapper.delete(reviewNo);

        return deleted ? findList(mediaNo) : null;
    }

}
