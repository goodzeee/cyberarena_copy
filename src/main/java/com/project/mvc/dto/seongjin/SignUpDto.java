package com.project.mvc.dto.seongjin;

import com.project.mvc.entity.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min=2, max=8)
    private String nickname;

    @NotBlank
    private String password;

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .nickname(this.nickname)
                .password(this.password)
                .build();
    }
}
