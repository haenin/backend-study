package com.haenin.lifecycle.section01.xml;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

public class LifeCycleTestServlet extends HttpServlet {
   /* 설명. 기본 생성자 */
    public LifeCycleTestServlet(){
        System.out.println("xml방식 서블릿 기본 생성자 실행!");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("xml 매핑 init() 메소드 호출!");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("xml 매핑 service() 메소드 호출!");
    }

    @Override
    public void destroy() {
        System.out.println("xml 매핑 destory() 메소드 호출!");
    }


}
