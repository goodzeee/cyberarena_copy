package com.project.mvc.service.seongjin;

import com.project.mvc.dto.seongjin.*;
import com.project.mvc.entity.FollowLog;
import com.project.mvc.mapper.seongjin.FollowLogMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowLogService {

    private final FollowLogMapper followLogMapper;

    public List<FollowExistsDto> getFollowList(String email, boolean type) {

        if (email == null) {
            return null;
        }

        return
                followLogMapper.getList(FollowListDto.builder()
                        .userEmail(email)
                        .type(type)
                        .build());
    }


    public boolean follow(FollowLogRequestDto dto) {
        boolean flag = followLogMapper.followed(dto);
        System.out.println("flag = " + flag);
        System.out.println("dto = " + dto);
        if (flag) {
            return false;
        }
        return followLogMapper.follow(dto);
    }

    public boolean unfollow(FollowLogRequestDto dto) {

        return followLogMapper.unfollow(dto);
    }

    public boolean wasFollow(String email, String targetEmail) {
        return followLogMapper.followed(FollowLogRequestDto.builder()
                        .targetEmail(targetEmail)
                        .userEmail(email)
                .build());
    }
}
