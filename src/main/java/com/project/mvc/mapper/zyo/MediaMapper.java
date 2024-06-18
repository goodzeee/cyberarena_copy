package com.project.mvc.mapper.zyo;

import com.project.mvc.common.zyo.Search;
import com.project.mvc.entity.Media;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MediaMapper {

    // 미디어 목록 조회
    List<Media> findAll(Search media);

    // 미디어 상세 조회
    Media findOne(long mediaNo);
}
