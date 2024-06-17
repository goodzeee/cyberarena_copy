package com.project.mvc.mapper;

import com.project.mvc.entity.Media;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MediaMapper {

    List<Media> findAll();

}
