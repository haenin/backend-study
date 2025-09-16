package com.haenin.filter.section01;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/* 설명.
*   @WebFilter라는 어노테이션을 통해 필터 설정을 할 수 있지만
*   요청시 필터의 동작 순서를 정하기 위해서
*   Servlet에서는 web.xml을 통한 설정 시 작성된 순서로만 바꿔줄 수 있다.
*   (즉 web.xml에 작성한 filter mapping의 순서에 따라 동작한다.)
*   */
//@WebFilter(filterName = "firstFilter")
//@WebFilter(filterName = "firstFilter", urlPatterns = "/first/*")
@WebFilter("/first/*")
public class FirstFilter implements Filter {

    public FirstFilter() {
        System.out.println("Filter 기본 생성자 호출");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("FirstFilter 인스턴스 생성!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("FirstFilter의 doFilter() 호출 돰...");
        // filterchain 매개변수
        /* 설명. FilterChain에서 제공하는 doFilter를 통해 다음 필터 또는 서블릿으로 진행하라는 의미 */
        chain.doFilter(request, response); // 다음 필터가 있으면 다음 필터로 가세요
        // 작성하지 않으면 서블릿으로 가지 않음
        System.out.println("First: 서블릿 다녀온 이후 client로 응답 전");
    }

    @Override
    public void destroy() {
        System.out.println("FirstFIlter 삭제 작전!");
    }
}
