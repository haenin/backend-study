package com.haenin.transactional.section01.annotation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
// 엔티티 -> db에 친한애
public class Order {
    private int orderCode;
    private String orderDate;
    private String orderTime;
    private int totalOrderPrice;

    // 자바는 로컬데이트 타입이라 타입을 맞춰줘서 생성자 만듬
    /* 설명. OrderDTO에 있던 값을
    *       Order Entity로 옮기기 위한 생성자*/
    public Order(LocalDate orderDate, LocalTime orderTime, int totalOrderPrice) {
        this.orderDate = orderDate.format(
                DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.orderTime = orderTime.format(
                DateTimeFormatter.ofPattern("HH:mm:ss"));;
        this.totalOrderPrice = totalOrderPrice;
    }
}
