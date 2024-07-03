package com.project.mvc.dto.response.kibeom;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
@EqualsAndHashCode
public class DiscussResponseDto {

    private String nickname;
    private String discussionTitle;
    private String discussionOffer;
    private LocalDateTime discussionCreatedAt;
    private String formattedDiscussionCreatedAt; // 포맷된 문자열을 저장할 필드 추가
    private long reviewNo;
    private long discussionNo;
    private long viewCount;
    private int replyCount;
    private String mediaTitle;


    public DiscussResponseDto(DiscussFindAllDto dto) {
        this.nickname = dto.getNickname();
        this.discussionTitle = dto.getDiscussionTitle();
        this.discussionOffer = makeShortContent(dto.getDiscussionOffer());
        this.discussionCreatedAt = dto.getDiscussionCreatedAt();
        this.formattedDiscussionCreatedAt = dateFormatting(dto.getDiscussionCreatedAt()); // 포맷된 문자열 할당
        this.reviewNo = dto.getReviewNo();
        this.discussionNo = dto.getDiscussionNo();
        this.viewCount = dto.getViewCount();
        this.replyCount = dto.getReplyCount();
        this.mediaTitle = dto.getMediaTitle();
    }

    private String dateFormatting(LocalDateTime regDateTime) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy. MM. dd");
        return regDateTime.format(pattern);
    }

    private String makeShortContent(String content) {
        return (content.length() > 30) ? content.substring(0, 30) + "..." : content;
    }

    private String makeShortTitle(String title) {
        return (title.length() > 5) ? title.substring(0, 5) + "..." : title;
    }
}
