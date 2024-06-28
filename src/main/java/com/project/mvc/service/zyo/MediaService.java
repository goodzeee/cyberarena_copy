package com.project.mvc.service.zyo;

import com.project.mvc.common.zyo.Search;
import com.project.mvc.dto.request.jihye.MediaDetailDto;
import com.project.mvc.dto.response.jihye.ReviewDetailDto;
import com.project.mvc.dto.response.jihye.ReviewFindAllDto;
import com.project.mvc.dto.response.kibeom.DiscussFindAllDto;
import com.project.mvc.dto.zyo.DiscussRenderingDto;
import com.project.mvc.dto.zyo.ReviewRenderingDto;
import com.project.mvc.entity.Category;
import com.project.mvc.entity.Discussion;
import com.project.mvc.entity.Media;
import com.project.mvc.mapper.jihye.ReviewMapper;
import com.project.mvc.mapper.kibeom.DiscussionMapper;
import com.project.mvc.mapper.zyo.MediaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MediaService {

    private final MediaMapper mediaMapper;
    private final ReviewMapper reviewMapper;
    private final DiscussionMapper discussionMapper;

    // 미디어 조회
    public List<Media> findList(Search media) {
        return mediaMapper.findAll(media);
    }

    // 상세 조회 요청 중간처리
    public MediaDetailDto detail(long mediaNo) {
        // 게시물 정보 조회

        Media media = mediaMapper.findMedia(mediaNo);

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
        List<ReviewFindAllDto> reviews = reviewMapper.findAll(mediaNo);

        MediaDetailDto dto = new MediaDetailDto(media);
        dto.setReviews(reviews);

        return dto;
    }

     // 리뷰 목록 조회
    public List<ReviewRenderingDto> findAllReviews(int categoryNo) {
//        List<Review> allreviews = mediaMapper.findReviewByCategory(categoryNo)
//                .stream()
//                .sorted(Comparator.comparing(Review::getReviewCreatedAt).reversed())
//                .collect(Collectors.toList());
//        return allreviews;
        List<ReviewRenderingDto> reviewRenderList = mediaMapper.findReviewRenderInfo(categoryNo)
                .stream()
                .sorted(Comparator.comparing(ReviewRenderingDto::getReviewCreatedAt).reversed())
                .collect(Collectors.toList());
        return reviewRenderList;

    }


    // 토론 목록 조회
    public List<DiscussRenderingDto> findAllDiscussions(int categoryNo) {
        List<DiscussRenderingDto> allDiscussions = mediaMapper.findDiscussRenderInfo(categoryNo)
                .stream()
                .sorted(Comparator.comparing(DiscussRenderingDto::getReplyCount).reversed())
                .limit(3)
                .collect(Collectors.toList());
        for (DiscussRenderingDto disc : allDiscussions) {
            disc.dateFormatting();
        }
        return allDiscussions;
    }

    // 이미지 URL 조회
    public List<String> findImageUrlByCategory(int categoryNo) {
        return mediaMapper.findImageUrlByCategory(categoryNo);
    }

}
