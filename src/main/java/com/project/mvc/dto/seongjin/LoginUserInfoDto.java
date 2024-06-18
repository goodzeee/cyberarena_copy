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
    private String nickName;
    private String auth;

    public LoginUserInfoDto(User user) {
        this.email = user.getEmail();
        this.nickName = user.getNickname();
        this.auth = user.getAuth();
    }
}
