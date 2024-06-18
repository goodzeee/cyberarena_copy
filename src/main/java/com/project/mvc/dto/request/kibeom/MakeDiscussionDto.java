package com.project.mvc.dto.request.kibeom;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MakeDiscussionDto {

    private String email;
    private long mediaNo;
    private long reviewNo;
    private String discussionTitle;
    private String discussionOffer;
    /*
    (#{email}, #{mediaNo}, #{reviewNo}, #{discussionTitle}, #{discussionOffer})
     */
}
