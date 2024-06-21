package com.project.mvc.dto.seongjin;

import com.project.mvc.entity.User;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowLogRequestDto {
    private String userEmail;
    private String targetEmail;
}
