package com.haenin.springdatajpa.common;

import org.springframework.data.domain.Page;

public class Pagination {

    public static PagingButtonInfo getPagingButtonInfo(Page page) {
        // 인덱스 개념 -> 실제 페이지에 보여질 번호의 개념으로 다시 변경
        int currentPage = page.getNumber() + 1;
        // 한 페이지에 보일 버튼의 갯수
        int defaultButtonCount = 10;
        // 한 페이지에 보여질 첫 버튼
        int startPage;
        // 한 페이지에 보여질 마지막 버튼
        int endPage;

        // 1.0이면 올림을 하지 않음
//        startPage = (int)(Math.ceil((double)currentPage / defaultButtonCount)-1)
//                * defaultButtonCount + 1;
        startPage = (int)(currentPage / (double)defaultButtonCount +0.9 -1)
                * defaultButtonCount + 1;
        endPage = startPage + defaultButtonCount - 1;

        /* 설명. 이 아래쪽 조건들은 마지막 버튼이
        *       축소되어야 할 2가지 경우이다. */

        /* 설명. 현재 페이지 버튼 그룹의 마지막 버튼이
        *       전체의 마지막 페이지를 넘어가면
        *       마지막 페이지가 endPage가 된다. */
        // totalPage가 마지막 페이지보다 작으면
        if(page.getTotalPages() < endPage){
        // totalPage가 마지막 페이지 버튼이 된다.
            endPage = page.getTotalPages();
        }
        // 한 페이지가 다 안차면 0으로 나옴
        /* 설명. 게시글이 없거나 한 페이지가
                차지도 않을만큼의 item이 존재할 때 */
        // 1페이지도 안된다면
        if(page.getTotalPages() == 0){
        // startPage가 곧 endPage가 된다. -> 1페이비 버튼만 표시
            endPage = startPage;
        }

        return new PagingButtonInfo(currentPage, startPage, endPage);
    }
}
