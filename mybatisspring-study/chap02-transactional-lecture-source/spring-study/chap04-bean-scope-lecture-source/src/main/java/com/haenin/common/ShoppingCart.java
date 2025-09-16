package com.haenin.common;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

// 서비스 계층처럼 사용
/* 설명. add라는 메소드로 직접 상품을 담을 예정(의존성 주입X) */
@ToString
public class ShoppingCart {
    private final List<Product> items;
    // final -> NPE 방지
    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    /* 설명. 카트에 물품을 담는 기능 */
    public void addItem(Product item){
        this.items.add(item);
    }

    /* 설명. 카트에서 물픔을 꺼내는 기능 (한번에 다 꺼내기)*/
    public List<Product> getItems() {
        return items;
    }

}
