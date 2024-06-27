package com.project.mvc.dto.request.jihye;


import com.project.mvc.entity.DiscussionStatus;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
// 댓글 등록시 클라이언트에게 받아야할 필요한 데이터 모음집
public class ReviewPostDto {

    private String reviewText; // 리뷰 내용

//    @NotNull(message = "별점을 입력하세요.")
    private double userRating; // 별점

//    @NotNull(message = "토론신청 여부를 입력하세요.")
    private DiscussionStatus discussionStatus;  // 토론신청 여부

    private long mediaNo; // 리뷰 남길 특정 미디어 번호

    private String nickname;  // 리뷰 작성자

    private String email; // 리뷰 이메일 계정

}
