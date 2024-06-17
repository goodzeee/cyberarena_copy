package com.project.mvc.mapper;

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
        List<Media> all = mediaMapper.findAll();
        //when
        System.out.println("all = " + all);
        //then
    }
    
}