package com.project.mvc.dto.seongjin;

import com.project.mvc.entity.User;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowListDto {
    private String userEmail;
    private boolean type;// true -> user가 팔로우한 대상리스트
                        // false -> user를 팔로우한 대상리스트
}
