package com.haenin.common;

import lombok.NoArgsConstructor;

import java.util.Date;

// @Component가 없어도 콩으로 만들 수 있다.
@NoArgsConstructor
public class Bread extends Product{
    private java.util.Date bakedDate; // 생성 시간

    public Bread(String name, int price, Date bakedDate) {
        super(name, price);
        this.bakedDate = bakedDate;
    }

    @Override
    public String toString() {
        return "Bread{" +
                "bakedDate=" + bakedDate +
                "}" + super.toString();
    }
}
