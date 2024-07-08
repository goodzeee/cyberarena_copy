package com.project.mvc.dto.response.kibeom;

import com.project.mvc.entity.DiscussionStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ReviewAsideListDto {
    private long mediaNo;
    private String reviewText;

}
