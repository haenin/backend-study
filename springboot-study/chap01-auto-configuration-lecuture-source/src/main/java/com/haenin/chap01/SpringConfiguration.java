package com.haenin.chap01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
    @Value("${test.value}")
    public String testValue;

    @Value("${test.age}")
    public String testAge;
    
    /* 설명. @Value는 시스템 환경변수도 불러올 수 있다. */
    @Value("${user}")
    public String userName;


    @Bean
    public Object propertyReadTest(){
        System.out.println("testValue = " + testValue);
        System.out.println("testAge = " + testAge);
        System.out.println("userName = " + userName);
        return new Object();
    }
}
