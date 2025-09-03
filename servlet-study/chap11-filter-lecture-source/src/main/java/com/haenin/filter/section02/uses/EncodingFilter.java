package com.haenin.filter.section02.uses;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/member/*")
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("인코딩 설정용 필터 지나감");
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        /* 설명.
        *   톰캣 10버전 이전에는 post 요청일 때는
        *   인코딩 설정을 하지 않으면 한글이 깨지곤 했다.
        *   우린 10버전의 톰캣이라 굳이 해주지 않아도 동작하지만
        *   협업에서 경우에 따라 필요할 수 도 있고
        *   추가로 다른 인코딩 설정으로 바꿀 때도 활용할 수 있다. */
        if("POST".equals(httpRequest.getMethod())){
            httpRequest.setCharacterEncoding("UTF-8");
        }
        chain.doFilter(httpRequest,response);
    }
}
