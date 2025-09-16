package com.haenin.transactional.section01.annotation;

import lombok.AllArgsConstructor;
import lombok.Data;

/* 설명. 하나의 메뉴에 대한 주문건을 담는 DTO */
@Data
@AllArgsConstructor
public class OrderMenuDTO {
    // 사용자가 주문하는 메뉴 번호
    private int menuCode;
    // 주문한 메뉴 수량
    private int orderAmount;
}
