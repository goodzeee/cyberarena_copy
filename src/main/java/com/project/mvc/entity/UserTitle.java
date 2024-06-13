package com.project.mvc.entity;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTitle {
    private int userTitleNo;
    private String email;
    private String titleName;
}
