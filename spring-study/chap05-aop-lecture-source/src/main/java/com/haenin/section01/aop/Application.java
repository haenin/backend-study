package com.haenin.section01.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(
                        ContextConfiguration.class);

        System.out.println("=== 회원 전체 조회 ===");
        MemberService memberService = context.getBean("memberService",MemberService.class);

        memberService.findAllMembers().forEach(System.out::println);

        System.out.println("=== 회원 한명 조회 ===");
        System.out.println(memberService.findMemberBy(1));
        /* 설명. AfterThrowing Adivce 확인용 요청 */
//        System.out.println(memberService.findMemberBy(3));
        // AfterReturning 시 추가된 MemberDTO 객체가 있어 3으로 테스트시 에러발생 확인
    }
}
