package com.haenin.section01.connection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application2 {
    public static void main(String[] args) {
        Connection con = null;
        Properties prop = new Properties();

        /* 설명. mysql 드라이버 클래스의 풀 네임을 통해 드라이버 등록(메모리에 등록) */
        try {
            prop.load(
                    new FileReader(
                            "src/main/java/com/haenin/section01/connection/jdbc-config.properties"));
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            Class.forName(driver); // 필기. 동적 메모리 할당
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection2 객체: " + con);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
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