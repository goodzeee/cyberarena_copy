package com.project.mvc.dto.zyo;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class DiscussRenderingDto {
    //user
    private String nickname;
    private String email;
    //discussion
    private long discussionNo;
    private String discussionTitle;
    private LocalDateTime discussionCreatedAt;
    private long viewCount;
    //media
    private long mediaNo;
    private String mediaTitle;
    private String imageUrl;

    private String format;


    public void dateFormatting() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("YYYY. MM. dd");
        this.format =  pattern.format(this.discussionCreatedAt);
    }
}