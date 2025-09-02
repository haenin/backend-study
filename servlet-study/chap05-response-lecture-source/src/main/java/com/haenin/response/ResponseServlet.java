package com.haenin.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/response")
public class ResponseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* 설명. HttpServletResponse를 활용해서 서버에서 페이지 만들어 응답 */
        StringBuilder responseText = new StringBuilder();
        responseText.append("<!DOCTYPE html>\n")
                    .append("<head>\n")
                    .append("</head>\n")
                    .append("<body>\n")
                    .append("<h1>Servlet response</h1>\n")
                    .append("</body>\n")
                    .append("</html>");

        /* 설명. MIME 타입과 인코딩 방식을 설정해 주어야 한다.
        *       JakartaEE 이후 생략은 가능하지만 작성해 주는 것이 좋다.*/
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("UTF-8");
        // 한줄 코딩 가능
        resp.setContentType("text/html; charset =UTF-8");
        PrintWriter out = resp.getWriter();
        // 스트림으로 출력
        out.print(responseText);
        out.flush(); // 내부적으로 버퍼를 사용하기 때문
        // 톰캣은 8000바이트를 넘어서면 8000바이트마다 끊어서 flush
        out.close();
        // 스트림 닫음
    }
}
