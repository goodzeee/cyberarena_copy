package com.project.mvc.dto.response.kibeom;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DiscussFindAllDto {
/*
    U.nickname AS nickname,
    D.discussion_title,
    D.discussion_offer,
    D.discussion_created_at,
    D.review_no,
    D.discussion_no
 */
    private String nickname;
    private String discussionTitle;
    private String discussionOffer;
    private LocalDateTime discussionCreatedAt;
    private long reviewNo;
    private long discussionNo;

    private long mediaNo;
    private long viewCount;
    private int replyCount;

    // 0628 추가 ↓
    private String mediaTitle;
}
