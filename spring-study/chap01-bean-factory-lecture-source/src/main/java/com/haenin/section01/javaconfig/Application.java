package com.haenin.section01.javaconfig;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
//        ApplicationContext -> 컨테이너 같은 개념

        /* 설명. ContextConfiguration으로 설정한 내용을 바탕으로 IOC 컨테이너 생성 */
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String[] beanNames = context.getBeanDefinitionNames();
        for(String beanName : beanNames){
            System.out.println("beanName = " + beanName);
        }
    }
}
