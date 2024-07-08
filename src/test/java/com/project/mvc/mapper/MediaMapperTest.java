package com.project.mvc.mapper;

import com.project.mvc.common.zyo.Search;
import com.project.mvc.dto.response.kibeom.MediaAsideListDto;
import com.project.mvc.entity.Media;
import com.project.mvc.mapper.zyo.MediaMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MediaMapperTest {

    @Autowired
    private MediaMapper mediaMapper;
    
    
    @Test
    @DisplayName("전체조회")
    void findAll() {
        //given
        List<Media> all = mediaMapper.findAll(null);
        //when
        System.out.println("all = " + all);

        //then
    }
    
    @Test
    @DisplayName("findOne test")
    void findOne() {
        //given
        Media one = mediaMapper.findMedia(10);
        //when
        System.out.println(one);
        //then
    }


    @Test
    @DisplayName("findByTitle test")
    void findByTitle() {
        //given
        String title = "암살";
        //when
//        Media byTitle = (Media) mediaMapper.findByTitle(title);
        //then
//        System.out.println(byTitle);
    }


    @Test
    @DisplayName("미디어 평점 수정")
    void modifyRating() {
        //given
        List<Media> asideList = mediaMapper.findAll(null);
        Random random = new Random();
//        // 1.0 ~ 5.0 사이의 랜덤 소수 생성
//        for (int i = 0; i < 20; i++) {
//
//            System.out.println("rating = " + rating);
//        }
        
        //when
        for (Media mediaAsideListDto : asideList) {
            double rawRating = 1.0 + (4.0 * random.nextDouble());
            double rating = Math.round(rawRating * 10.0) / 10.0;
            mediaAsideListDto.setRating(rating);
            System.out.println(mediaAsideListDto.getRating());
        }
        

        //then
    }


    
    
}