package com.project.mvc.dto.seongjin;


import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePasswordDto {
    private String email;
    @Setter
    private String password;
}
