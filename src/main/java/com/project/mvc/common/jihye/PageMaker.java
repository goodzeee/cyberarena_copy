package com.project.mvc.common.jihye;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

// 역할 : 페이지 화면 렌더링에 필요한 정보들을 계산
@Getter
@ToString
@EqualsAndHashCode
public class PageMaker {

    private static final int PAGE_COUNT = 10;
    private int begin, end, finalPage;

    private boolean prev, next;

    private int totalCount;

    private boolean firstPage;

    private Page pageInfo;

    public PageMaker (Page page, int totalCount) {
        this.pageInfo = page;
        this.totalCount = totalCount;
        makePageInfo(); // end & begin 세팅
    }

    private void makePageInfo() {

        this.end = (int)Math.ceil(pageInfo.getPageNo() / (double)PAGE_COUNT) * PAGE_COUNT;

        this.begin = this.end - PAGE_COUNT + 1;

        this.finalPage = (int)Math.ceil((double) totalCount / pageInfo.getAmount());

        if (finalPage < this.end) {
            this.end = finalPage;
        }

        this.prev = begin != 1;

        this.next = this.end < finalPage;

        this.firstPage = begin != 1;

    }

}
