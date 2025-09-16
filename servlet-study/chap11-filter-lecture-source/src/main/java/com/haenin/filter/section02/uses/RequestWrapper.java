package com.haenin.filter.section02.uses;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.SortedMap;

// 나만의 http 서블릿을 만들려면 HttpServletRequestWrapper를 상속받아야 한다
// HttpServletReques 불가능
public class RequestWrapper extends HttpServletRequestWrapper {
    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String key) {
        String value = "";
        if ("password".equals(key)) {

            /* 설명. key값으로 'password'가 넘오오면 Bcrypt 암호와 진행 */
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            value = passwordEncoder.encode(super.getParameter(key));
            System.out.println("암호화된 값: " + value);

        } else {
            value = super.getParameter(key);
        }
        return value;
    }
}
