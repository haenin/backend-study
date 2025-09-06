package com.haenin.section02.annotation.common;

import org.springframework.stereotype.Component;

@Component
public class Charamander implements Pokemon {
    @Override
    public void attack() {
        System.out.println("파이리 불공격! 🔥");
    }
}
