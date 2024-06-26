package com.project.mvc.dto.seongjin;

import lombok.*;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowEmailListDto {
    private List<FollowExistsDto> emailList;
}
