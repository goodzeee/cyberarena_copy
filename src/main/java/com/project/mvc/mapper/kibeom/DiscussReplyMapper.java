package com.project.mvc.mapper.kibeom;

import com.project.mvc.dto.request.kibeom.DiscussionCommentRequestDto;
import com.project.mvc.entity.DiscussReply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiscussReplyMapper {

    // 토론의 댓글 등록
    boolean insert(DiscussionCommentRequestDto dto);

    List<DiscussReply> findAll(long discussionNo);

    String findNicknameByEmail(String email);
}
