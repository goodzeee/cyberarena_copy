package com.project.mvc.service.seongjin;

import com.project.mvc.dto.seongjin.FollowListDto;
import com.project.mvc.dto.seongjin.FollowLogRequestDto;
import com.project.mvc.dto.seongjin.LoginUserInfoDto;
import com.project.mvc.mapper.seongjin.FollowLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowLogService {

    private final FollowLogMapper followLogMapper;

    public List<String> getFollowList(HttpSession session, boolean type) {
        LoginUserInfoDto user = (LoginUserInfoDto)session.getAttribute("login");
        if(user == null) {
            return null;
        }
        return followLogMapper.getList(FollowListDto.builder()
                .userEmail(user.getEmail())
                .type(type)
                .build());
    }

    public boolean follow(FollowLogRequestDto dto) {
        return followLogMapper.follow(dto);
    }

    public boolean unfollow(FollowLogRequestDto dto) {
        return followLogMapper.unfollow(dto);
    }

}
