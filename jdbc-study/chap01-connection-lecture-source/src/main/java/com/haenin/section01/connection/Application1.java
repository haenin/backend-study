package com.haenin.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application1 {

    /* 목표. 해당 DBMS 경로와 계정에 맞는 Connection 객체를 생성할 수 있다.
        (feat.해당 DB Driver 라이브러리 필요) */
    public static void main(String[] args) {
        Connection con = null;

        /* 설명. mysql 드라이버 클래스의 풀 네임을 통해 드라이버 등록(메모리에 등록) */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 필기. 동적 메모리 할당
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/menudb",
                    "root",
                    "mariadb");
            System.out.println("Connection 객체: " + con);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(con != null) con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
