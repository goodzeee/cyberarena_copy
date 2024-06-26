package com.project.mvc.mapper.kibeom;

import com.project.mvc.common.jihye.Page;
import com.project.mvc.dto.request.kibeom.DiscussionModifyDto;
import com.project.mvc.dto.request.kibeom.MakeDiscussionDto;
import com.project.mvc.dto.response.kibeom.DiscussFindAllDto;
import com.project.mvc.entity.Discussion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiscussionMapper {

    // 토론 등록
    boolean insert(MakeDiscussionDto dto);

    // 토론 목록 조회
    List<DiscussFindAllDto> findAll(Page page);

    // 토론 개별 조회
    Discussion findOne(long discussionNo);

    // 토론 삭제
    boolean delete(long discussionNo);

    // 조회수 상승
    void updateViewCount(long discussionNo);

    // 총 게시물 수 조회
    int count();

    // 토론 삭제
    boolean remove(long dno);

    // 수정
    boolean modify(DiscussionModifyDto dto);



}
