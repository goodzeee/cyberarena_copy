package com.project.mvc.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private String email;
    private String nickname;
    private String password;
    private String auth;
    private LocalDateTime regDate;
    private String sessionId;
    private LocalDateTime limitTime;
    private boolean verified;
    private String verifyCode;
}
