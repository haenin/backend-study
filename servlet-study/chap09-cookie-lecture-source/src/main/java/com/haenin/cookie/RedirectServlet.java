package com.haenin.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies(); // 배열 반환
        String firstName = "";
        String lastName = "";
        for(Cookie cookie : cookies){
            System.out.println(cookie.getName() + ":" + cookie.getValue());

            if ("firstName".equals(cookie.getName())){
                firstName = cookie.getValue();
            }else if("lastName".equals(cookie.getName())){
                lastName = cookie.getValue();
            }
        }

        StringBuilder responseText = new StringBuilder();
        responseText.append("<h3>당신의 이름은 ")
                .append(firstName)
                .append("<br>그리고 성은 ")
                .append(lastName)
                .append("</h3>");

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(responseText);
        out.flush();
        out.close();
    }
}
