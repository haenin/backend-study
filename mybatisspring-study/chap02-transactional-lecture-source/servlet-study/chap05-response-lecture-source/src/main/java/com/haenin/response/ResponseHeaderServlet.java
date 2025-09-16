package com.haenin.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

@WebServlet("/headers")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        long currentTime = System.currentTimeMillis();
        out.print("<h1>" + currentTime + "</h1>");
        out.print("<h1>" + new java.util.Date(currentTime) + "</h1>");
        out.flush();
        out.close();

        Collection<String> responseHeaders = resp.getHeaderNames();
        Iterator<String> iter = responseHeaders.iterator();
        while (iter.hasNext()) {
            String headerName = iter.next();
            out.print(headerName +": " + resp.getHeader(headerName));
        }
    }
}
