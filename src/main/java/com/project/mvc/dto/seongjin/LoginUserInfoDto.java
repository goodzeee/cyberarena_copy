package com.project.mvc.dto.seongjin;

import com.project.mvc.entity.User;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUserInfoDto {

    private String email;
    private String nickname;
    private String auth;

    public LoginUserInfoDto(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.auth = user.getAuth();
    }
}
