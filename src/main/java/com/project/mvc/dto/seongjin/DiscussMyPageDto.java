package com.project.mvc.dto.seongjin;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class DiscussMyPageDto {
    private String nickname;
    private String discussionTitle;
    @Setter
    private String shortTitle;
    private long discussionNo;
    private String mediaTitle;
    private String email;
    private long mediaNo;
    private String imageUrl;
}
