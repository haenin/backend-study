package com.haenin.filter.section02.uses;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/member/*")
public class PasswordEncryptFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("암호와 필터 실행됨...");
        /* 설명. 기존의 Request 객체가 아닌 우리가 getParamter()를 재정의한 Request 객체로 교체 */
        RequestWrapper wrapper = new RequestWrapper((HttpServletRequest)request);
        chain.doFilter(wrapper,response);

    }
}
