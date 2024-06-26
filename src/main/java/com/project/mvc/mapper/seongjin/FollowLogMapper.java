package com.project.mvc.mapper.seongjin;

import com.project.mvc.dto.seongjin.FollowExistsDto;
import com.project.mvc.dto.seongjin.FollowListDto;
import com.project.mvc.dto.seongjin.FollowLogRequestDto;
import com.project.mvc.entity.FollowLog;
import com.project.mvc.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowLogMapper {

    List<FollowExistsDto> getList(FollowListDto dto);

    boolean follow(FollowLogRequestDto dto);

    boolean unfollow(FollowLogRequestDto dto);

    boolean followed(FollowLogRequestDto dto);
}
