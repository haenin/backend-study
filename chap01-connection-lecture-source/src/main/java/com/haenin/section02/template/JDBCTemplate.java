package com.haenin.section02.template;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {

    public static Connection getConnection() {
        Connection con = null;
        Properties prop = new Properties(); // 필기. 키와 벨류가 스트링인 맵
        /* 설명. mysql 드라이버 클래스의 풀 네임을 통해 드라이버 등록(메모리에 등록) */
        try {
            prop.load(new FileReader(
                    "src/main/java/com/haenin/section01/connection/jdbc-config.properties"));
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);

            System.out.println("Connection 객체: " + con);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /* 설명. Connection 객체를 닫으면 안됨 */
        // 필기. 닫혀있지 않는 connection 객체를 넘겨준다.
        return con;
    }
    public static void close(Connection con) {
        if(con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }
}

