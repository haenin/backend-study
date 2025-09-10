package com.haenin.chap06;

import org.springframework.stereotype.Service;

@Service
public class InterceptorTestService {

    public void test() {
        System.out.println("Service bean을 활용한 메소드 호출");
    }
}
