package com.haenin.filter.section01;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//@WebFilter(filterName = "secondFilter")
//@WebFilter(filterName = "secondFilter", urlPatterns = "/*")
//@WebFilter("/*")
public class SecondFilter implements Filter {
    public SecondFilter() {
        System.out.println("Second Filter 기본 생성자 호출");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("SecondFilter 인스턴스 생성!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("SecondFIlter doFilter() 호출 돰...");

        chain.doFilter(request,response); // 다음 필터가 없으면 서블릿 실행
        System.out.println("Second: 서블릿 다녀온 이후 client로 응답 전");
    }

    @Override
    public void destroy() {
        System.out.println("SecondFIlter 삭제 작전!");
    }
}
