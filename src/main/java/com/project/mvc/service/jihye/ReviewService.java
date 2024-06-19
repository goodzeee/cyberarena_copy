package com.project.mvc.service.jihye;


import com.project.mvc.common.zyo.Search;
import com.project.mvc.dto.response.jihye.ReviewFindAllDto;
import com.project.mvc.dto.response.jihye.ReviewListDto;
import com.project.mvc.entity.Media;
import com.project.mvc.entity.Review;
import com.project.mvc.mapper.zyo.MediaMapper;
import com.project.mvc.mapper.jihye.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final MediaMapper mediaMapper;
    private final ReviewMapper reviewMapper;

    public List<ReviewListDto> findAllMediaWithReviews() {
        List<Media> mediaList = mediaMapper.findAll(null);

        return mediaList.stream()
                .map(media -> {
                    List<Review> reviewList = reviewMapper.findAll(media.getMediaNo());
                    return ReviewListDto.builder()
                            .media(media)
                            .reviews(reviewList)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
