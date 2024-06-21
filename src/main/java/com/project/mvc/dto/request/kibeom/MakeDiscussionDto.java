package com.project.mvc.dto.request.kibeom;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MakeDiscussionDto {

    private String email;
    private String mediaName;
    private long mediaNo;
    private String discussionTitle;
    private String discussionOffer;
    /*
    (#{email}, #{mediaNo},  #{discussionTitle}, #{discussionOffer})
     */
}
