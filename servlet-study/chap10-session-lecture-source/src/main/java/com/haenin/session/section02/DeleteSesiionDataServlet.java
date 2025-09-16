package com.haenin.session.section02;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/delete")
public class DeleteSesiionDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();

        System.out.println("attribute 무효화 전 firstName" +
                session.getAttribute("fristName"));

        System.out.println("attribute 무효화 전 lastName" +
                session.getAttribute("lastName"));

        /* 설명. HttpSession 객체의 Attribute 값들을 무효화
        *       (로그아웃 개념으로 많이 쓰게 된다.)*/
        session.invalidate(); // 로그아웃같이 사물함안에 모든 attribute 삭제

        System.out.println("attribute 무효화 후 firstName" +
                session.getAttribute("fristName"));

        System.out.println("attribute 무효화 후 lastName" +
                session.getAttribute("lastName"));
    }
}
