package com.project.mvc.dto.seongjin;

import lombok.*;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder @Setter
public class LoginDto {

    private String email;
    private String password;
    private boolean autoLogin; // 자동 로그인 체크 여부
}
