package com.project.mvc.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Discussion {
    private long discussionNo;
    private String email;
    private long mediaNo;
    private long reviewNo;
    private String discussionTitle;
    private String discussionOffer;
    private LocalDateTime discussionCreatedAt;
}
