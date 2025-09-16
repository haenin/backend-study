package com.haenin.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Beverage extends Product {
    private int capacity; // 용량(ml)

    /* 설명. 부모의 매개변수 있는 생성자 활용하는 코드는 직접 작성 */
    public Beverage(String name, int price, int capacity) {
        super(name, price);
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Beverage{" +
                "capacity=" + capacity +
                "}" + super.toString();
    }
}
