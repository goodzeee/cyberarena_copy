package com.project.mvc.mapper.kibeom;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DiscussReplyMapper {

    // 토론의 댓글 등록
    boolean insert(DiscussionCommentRequestDto dto);
}
