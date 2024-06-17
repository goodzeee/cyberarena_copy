package com.project.mvc.dto.seongjin;

import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutoLoginDto {

    private String sessionId;
    private LocalDateTime limitTime;
    private String email;
}
