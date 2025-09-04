package com.haenin.session.section01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // toString과 다운캐스팅 둘다 가능
        String firstName = session.getAttribute("firstName").toString();
        String lastName = (String)session.getAttribute("lastName");

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
