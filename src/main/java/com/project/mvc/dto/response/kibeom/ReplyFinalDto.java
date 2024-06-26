package com.project.mvc.dto.response.kibeom;

import com.project.mvc.dto.seongjin.LoginUserInfoDto;
import lombok.*;

import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ReplyFinalDto {

    private List<DiscussReplyResponseDto> dtoList;
    private LoginUserInfoDto loginUserDto;

}
