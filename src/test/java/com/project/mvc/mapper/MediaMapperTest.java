package com.project.mvc.mapper;

import com.project.mvc.common.zyo.Search;
import com.project.mvc.entity.Media;
import com.project.mvc.mapper.zyo.MediaMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    
    
}