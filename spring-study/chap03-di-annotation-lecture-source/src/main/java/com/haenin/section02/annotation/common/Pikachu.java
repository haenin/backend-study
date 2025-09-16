package com.haenin.section02.annotation.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/* 설명. 같은 타입의 bean이 많을 경우 우선순위가 있는 bean을 설정가능
*       (@Primary) */
@Primary
@Component
public class Pikachu implements Pokemon{
    @Override
    public void attack() {
        System.out.println("피카츄 전기공격! ⚡️");
    }
}
