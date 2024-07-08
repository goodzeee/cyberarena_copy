package com.project.mvc.dto.seongjin;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class ReviewAllowDto {
    private String nickname;
    private String email;
    private String reviewText;
    private long mediaNo;
    private long reviewNo;
    private String mediaTitle;
}
