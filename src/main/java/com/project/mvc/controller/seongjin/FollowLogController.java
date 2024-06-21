package com.project.mvc.controller.seongjin;

import com.project.mvc.dto.seongjin.FollowLogRequestDto;
import com.project.mvc.dto.seongjin.LoginUserInfoDto;
import com.project.mvc.service.seongjin.FollowLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowLogController {

    private final FollowLogService followLogService;

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<?> list(HttpServletRequest request, String type) {
        List<String> emailList = followLogService.getFollowList(request.getSession(), (type.equals("follow")));


        return ResponseEntity
                .ok()
                .body(emailList);
    }

    @GetMapping("/follow")
    @ResponseBody
    public ResponseEntity<?> following(FollowLogRequestDto dto) {
        boolean flag = followLogService.follow(dto);

        return ResponseEntity
                .ok()
                .body(flag);
    }

    @GetMapping("/unfollow")
    @ResponseBody
    public ResponseEntity<?> unfollowing(FollowLogRequestDto dto) {
        boolean flag = followLogService.unfollow(dto);

        return ResponseEntity
                .ok()
                .body(flag);
    }


}
