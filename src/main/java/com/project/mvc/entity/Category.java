package com.project.mvc.entity;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    private int categoryNo;
    private String category;
}
