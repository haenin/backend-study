package com.haenin.session.section01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/session")
public class SessionHandlerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        /* 설명.
        *   요청이 서블릿으로 들어올 때 HttpServletRequest에는 'JSESSIONID'라는 값이
        *   header에 같이 포함되어 넘어온다.
        *   그 때 해당 JSESSIONID에 맞는 HttpSession 객체를 생성해서
        *   헤당 사용자를 위한 전용 저장 공간을 제공하게 된다.
        *   (요청을 보낸 사람 전용 사물함 같은 개념)
        * */
        HttpSession session = req.getSession();
        System.out.println("session 객체의 default 만료 시간(초): "
                    + session.getMaxInactiveInterval());
        // 서버는 껐다 키기 때문에 ..
        // 10분으로 수정
        session.setMaxInactiveInterval(60 * 10); // 초 단위
        System.out.println("session 객체의 수정 후 만료시간(초): "
                + session.getMaxInactiveInterval());

        session.setAttribute("firstName",firstName);
        session.setAttribute("lastName",lastName);

        resp.sendRedirect("redirect");
    }
}
