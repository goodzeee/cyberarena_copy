package com.project.mvc.common.zyo;

import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Search {

    private String keyword;
    private String searchType;
    private String orderBy;
    private String order;
}
