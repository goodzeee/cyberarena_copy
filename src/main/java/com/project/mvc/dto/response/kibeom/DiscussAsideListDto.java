package com.project.mvc.dto.response.kibeom;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class DiscussAsideListDto {
    private long discussionNo;
    private String discussionTitle;
    private long viewCount;
}
