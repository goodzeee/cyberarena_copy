package com.project.mvc.dto.request.jihye;


import com.project.mvc.entity.DiscussionStatus;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
// 댓글 등록시 클라이언트에게 받아야할 필요한 데이터 모음집
public class ReviewPostDto {

//    private long reviewNo;
    private String reviewText; // 리뷰 내용
//    @NotNull
    private double userRating; // 별점
    private DiscussionStatus discussionStatus;  // 토론신청 여부
//    @NotBlank
    private String email; // 리뷰 작성자
//    @NotNull
    private long mediaNo; // 특정 미디어 번호

}
