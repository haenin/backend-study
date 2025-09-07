package com.haenin.section03.annotationconfig.subsection02.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "section03/annotationconfig/subsection02/spring-context.xml");

        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println);
    }
}

