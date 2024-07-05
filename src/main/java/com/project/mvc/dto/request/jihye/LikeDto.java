package com.project.mvc.dto.request.jihye;


import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeDto {

    private long reviewNo;
    private String email;
    private boolean isLiked;
//    private LocalDateTime likeCreatedAt;
}
