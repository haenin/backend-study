package com.haenin.section02.javaconfig;


import com.haenin.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext
                        (ContextConfiguration.class);

        MemberDTO member = context.getBean(MemberDTO.class);
        System.out.println("member = " + member);
    }
}
