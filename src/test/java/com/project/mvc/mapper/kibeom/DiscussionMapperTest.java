package com.project.mvc.mapper.kibeom;

import com.project.mvc.dto.request.kibeom.MakeDiscussionDto;
import com.project.mvc.dto.response.kibeom.DiscussFindAllDto;
import com.project.mvc.entity.Discussion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiscussionMapperTest {

    @Autowired
    private DiscussionMapper discussionMapper;


    @Test
    @DisplayName("전체 조회 성공.")
    void findAll() {
        //given
        List<DiscussFindAllDto> all = discussionMapper.findAll();
        //when
        System.out.println(all);
        //then
    }

    @Test
    @DisplayName("저장 성공")
    void insert() {
        //given
        MakeDiscussionDto dto = MakeDiscussionDto.builder()
                .email("hgb@naver.com")
                .mediaNo(10)
                .discussionTitle("봉준호씨는 남자입니다.")
                .discussionOffer("남자 맞죠?")
                .build();
        //when
        boolean insert = discussionMapper.insert(dto);

        //then
        System.out.println(insert);
    }


    @Test
    @DisplayName("삭제 성공")
    void delete() {
        //given
        long no = 2;
        //when
        boolean flag = discussionMapper.delete(no);
        //then
        assertTrue(flag);
    }
    
    
    @Test
    @DisplayName("단일 조회 성공")
    void findOne() {
        //given
        long no = 1;
        //when
        Discussion foundData = discussionMapper.findOne(no);
        //then
        System.out.println("foundData = " + foundData);
        assertNotNull(foundData);
    }
    


}