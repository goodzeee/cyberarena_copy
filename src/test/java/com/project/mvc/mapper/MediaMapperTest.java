package com.project.mvc.mapper;

import com.project.mvc.common.zyo.Search;
import com.project.mvc.entity.Media;
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
        List<Media> all = mediaMapper.findAll(Search.builder()
                        .keyword("도깨비")
                        .searchType("title")
                .build());
        //when
        System.out.println("all = " + all);

        //then
    }
    
    @Test
    @DisplayName("findOne test")
    void findOne() {
        //given
        Media one = mediaMapper.findOne(10);
        //when
        System.out.println(one);
        //then
    }
    
    
}