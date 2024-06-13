package com.project.mvc.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeLog {
    private long reviewLikeNo;
    private String email;
    private long reviewNo;
    private LocalDateTime likeCreatedAt;
}

