package com.project.mvc.mapper.kibeom;

import com.project.mvc.dto.request.kibeom.DiscussionCommentRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
                .discussionReplyContent("내일 뭐하지?")
                .build();
        //when
        boolean flag = discussReplyMapper.insert(dto);
        //then
        System.out.println("dto = " + dto);
        assertTrue(flag);
    }


}