package com.project.mvc.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowLog {
    private long followNo;
    private String userEmail;
    private String targetEmail;
    private LocalDateTime followCreatedAt;

}
