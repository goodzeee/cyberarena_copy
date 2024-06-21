package com.project.mvc.service.zyo;

import com.project.mvc.common.zyo.Search;
import com.project.mvc.dto.request.jihye.MediaDetailDto;
import com.project.mvc.dto.response.jihye.ReviewDetailDto;
import com.project.mvc.entity.Category;
import com.project.mvc.entity.Media;
import com.project.mvc.entity.Review;
import com.project.mvc.mapper.jihye.ReviewMapper;
import com.project.mvc.mapper.zyo.MediaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MediaService {

    private final MediaMapper mediaMapper;
    private final ReviewMapper reviewMapper;

    // 미디어 조회
    public List<Media> findList(Search media) {

        return mediaMapper.findAll(media);
    }

    // 상세 조회 요청 중간처리
    public MediaDetailDto detail(long mediaNo) {
        // 게시물 정보 조회

        Media media = mediaMapper.findOne(mediaNo);

        // 특정 미디어에 달린 리뷰 목록 조회
//        List<ReviewDetailDto> reviewList = reviewMapper.findAll(mediaNo)
//                .stream()
//                .map(ReviewDetailDto::new)
//                .collect(Collectors.toList());

//        // MediaDetailDto에 리뷰 목록 추가
//        MediaDetailDto mediaDetailDto = MediaDetailDto.builder()
//                .mediaNo(media.getMediaNo())
//                .categoryNo(media.getCategoryNo())
//                .mediaTitle(media.getMediaTitle())
//                .creator(media.getCreator())
//                .rating(media.getRating())
//                .mediaDesc(media.getMediaDesc())
//                .createdAt(media.getCreatedAt())
//                .imageUrl(media.getImageUrl())
//                .reviews(reviewList)
//                .build();

        // 특정 미디어 상세조회 시 리뷰 목록 조회도 같이 처리 !
        List<Review> reviews = reviewMapper.findAll(mediaNo);

        MediaDetailDto dto = new MediaDetailDto(media);
        dto.setReviews(reviews);

        return dto;

    }
}
