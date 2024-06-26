package com.project.mvc.dto.seongjin;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowExistsDto {
    private String email;
    private boolean exist;
}
