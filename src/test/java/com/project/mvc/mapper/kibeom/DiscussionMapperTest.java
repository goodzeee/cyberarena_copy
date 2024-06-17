package com.project.mvc.mapper.kibeom;

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
        List<Discussion> all = discussionMapper.findAll();
        //when
        System.out.println(all);
        //then
    }


}