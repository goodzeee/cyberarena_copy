package com.project.mvc.common.jihye;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter //@Setter
@ToString @EqualsAndHashCode
//@NoArgsConstructor
@AllArgsConstructor
public class Page {

    private int pageNo;
    private int amount;

    // 기본 생성자로 리뷰 페이지 초기값 주기
    public Page() {
        this.pageNo = 1;
        this.amount = 5;
    }

    // setter로 리뷰 페이지 제한 주기
    public void setPageNo(int pageNo) {
        if (pageNo < 1 || pageNo > Integer.MAX_VALUE) {
            this.pageNo = 1;
            return;
        }
        this.pageNo = pageNo;
    }

    // 한 페이지에 보이는 리뷰글 5 초과 50 미만 범위 setting
    public void setAmount(int amount) {
        if (amount < 5 || amount > 50) {
            this.pageNo = 5;
            return;
        }
        this.amount = amount;
    }

    public int getPageStart() {
        System.out.println("getPageStart call !");
        return (this.pageNo - 1) * this.amount;
    }
}
