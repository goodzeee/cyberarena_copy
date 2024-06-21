package com.project.mvc.mapper.kibeom;

import com.project.mvc.dto.request.kibeom.DiscussionCommentRequestDto;
import com.project.mvc.entity.DiscussReply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class DiscussReplyMapperTest {

    @Autowired
    DiscussReplyMapper discussReplyMapper;


    @Test
    @DisplayName("댓글 저장")
    void insertTest() {
        //given
        DiscussionCommentRequestDto dto = DiscussionCommentRequestDto.builder()
                .discussionNo(5)
                .email("hgb926@naver.com")
                .nickname("기범")
                .discussionReplyContent("내..")
                .build();
        //when
        boolean flag = discussReplyMapper.insert(dto);
        //then
        System.out.println("dto = " + dto);
        assertTrue(flag);
    }


    @Test
    @DisplayName("전체 조회")
    void findAllTest() {
        //given
        long no = 5;
        //when
        List<DiscussReply> all = discussReplyMapper.findAll(no);
        //then
        System.out.println("all = " + all);
        assertTrue(all.size() > 0);
    }






}