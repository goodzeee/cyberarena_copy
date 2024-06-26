package com.project.mvc.dto.seongjin;

import com.project.mvc.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUserInfoDto {

    private String email;
    private String nickname;
    private String auth;
    private String regDate;

    public LoginUserInfoDto(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.auth = user.getAuth();
        this.regDate = user.getRegDate().format(DateTimeFormatter.ofPattern("YYYY년 MM월 dd일"));
    }
}
