package com.project.mvc.common.zyo;

import com.project.mvc.common.jihye.Page;
import lombok.*;

@Getter @Setter @ToString
//@EqualsAndHashCode
//@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Search extends Page {

    private int categoryNo;
    private String keyword;
    private String searchType;
    private String orderBy;
    private String order;
    private String type;

    public Search() {
        this.keyword = "";
    }
}
