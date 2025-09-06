package com.haenin.section01.xmlconfig;

import com.haenin.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context =
                new GenericXmlApplicationContext(
                        "section01/xmlconfig/spring-context.xml");

        /* 설명. new MemberDTO(1, "홍길동", "010-123-1234", "hong@gmail.com",
            new PersonalAccount(20, "110-234-345656780", 0)); */

        MemberDTO member = context.getBean(MemberDTO.class);
        System.out.println("member = " + member);
    }
}
