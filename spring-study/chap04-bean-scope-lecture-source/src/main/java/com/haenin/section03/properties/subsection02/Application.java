package com.haenin.section03.properties.subsection02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

public class Application {
    public static void main(String[] args) {
        /* 설명.
         *   i18n 소프트웨어의 국제화(Internationalization에서 첫 글자만
         *   i 와 마지막 글자인 n 사이의 알파벳 갯수를 고려)란?
         *   극제화를 하기 위해서는 지켜야 할 것이 많다.
         *   1. 언어, 지역별 번역
         *   2. OS/Platform별 인코딩
         *   3. 문자열 치환 방법
         *   4. 국제화 UI(문자열 크기 변환, 폰트, 아이콘 등)
         *   5. 쓰기 방뱡의 차이
         *   6. 숫자, 공백, 화폐, 날짜, 주소, 측정 단위 등
         *   7. 타임존, 썸머타임 등 시각
         *   8. 문자열 정렬 방법
         * */

        ApplicationContext context =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String error404Message = context.getMessage("error.404", null, Locale.KOREA);
        String error500Message = context.getMessage
                ("error.500", new Object[]{"여러분", new java.util.Date()}, Locale.KOREA);

        System.out.println("I18N 404 한국 버전: " + error404Message);
        System.out.println("I18N 500 한국 버전: " + error500Message);

        String error404MessageUS = context.getMessage("error.404", null, Locale.US);
        String error500MessageUS= con]
        text.getMessage
                ("error.500", new Object[]{"you", new java.util.Date()}, Locale.US);

        System.out.println("I18N 404 미국 버전: " + error404MessageUS);
        System.out.println("I18N 500 미국 버전: " + error500MessageUS);
    }
}