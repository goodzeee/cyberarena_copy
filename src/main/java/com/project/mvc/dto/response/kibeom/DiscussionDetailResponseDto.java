package com.project.mvc.dto.response.kibeom;

import com.project.mvc.entity.Discussion;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscussionDetailResponseDto {

//    private long discussionNo;
//    private String email;
    private long mediaNo;
//    private long reviewNo;
    private String discussionTitle;
    private String discussionOffer;
    private LocalDateTime discussionCreatedAt;
    private String formattedDiscussionCreatedAt; // 포맷된 문자열을 저장할 필드 추가
    @Setter
    private String nickname;


    public DiscussionDetailResponseDto(Discussion d) {
        this.mediaNo = d.getMediaNo();
        this.discussionTitle = d.getDiscussionTitle();
        this.discussionOffer = d.getDiscussionOffer();
        this.discussionCreatedAt = d.getDiscussionCreatedAt();
        this.formattedDiscussionCreatedAt = dateFormatting(d.getDiscussionCreatedAt());

    }

    private String dateFormatting(LocalDateTime regDateTime) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return pattern.format(regDateTime);
    }

}

/*

public class DiscussResponseDto {

    private LocalDateTime discussionCreatedAt;
    private String formattedDiscussionCreatedAt; // 포맷된 문자열을 저장할 필드 추가

    public DiscussResponseDto(DiscussFindAllDto dto) {
        this.discussionCreatedAt = dto.getDiscussionCreatedAt();
        this.formattedDiscussionCreatedAt = dateFormatting(dto.getDiscussionCreatedAt()); // 포맷된 문자열 할당
    }

    private String dateFormatting(LocalDateTime regDateTime) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return pattern.format(regDateTime);
    }

 */
