package com.project.mvc.service.jihye;

import com.project.mvc.common.zyo.Search;
import com.project.mvc.dto.response.jihye.ReviewFindAllDto;
import com.project.mvc.entity.Media;
import com.project.mvc.mapper.jihye.ReviewMapper;
import com.project.mvc.mapper.zyo.MediaMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private MediaMapper mediaMapper;


    @Test
    @DisplayName("asdas")
    void setRating() {
        //given
        List<Media> all = mediaMapper.findAll(Search.builder().build());
        for (Media media : all) {
            List<ReviewFindAllDto> all1 = reviewMapper.findAll(media.getMediaNo());
            if(all1.size() != 0) {

            reviewMapper.updateMediaRating(media.getMediaNo());
            }
        }
        //when

        //then
    }


}