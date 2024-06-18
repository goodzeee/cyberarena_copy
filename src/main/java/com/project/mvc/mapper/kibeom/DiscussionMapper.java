package com.project.mvc.mapper.kibeom;

import com.project.mvc.dto.response.kibeom.DiscussFindAllDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiscussionMapper {

    boolean insert();

    // 토론 생성
//    - 주제 입력
//    - 세부내용 입력
//    - 토론 생성 버튼 클릭

    List<DiscussFindAllDto> findAll();
}
