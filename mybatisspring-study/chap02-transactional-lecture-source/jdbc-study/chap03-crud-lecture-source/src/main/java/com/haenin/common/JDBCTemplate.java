package com.haenin.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {

    public static Connection getConnection() {
        Connection con = null;
        Properties prop = new Properties(); // 키와 벨류가 스트링인 맵

        try {
            prop.load(new FileReader(
                    "src/main/java/com/haenin/config/connection-info.properties"));
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            Class.forName(driver); /* 필기. 동적메모리할당 */
            con = DriverManager.getConnection(url, user, password);

            /* 설명. 수동 커밋 및 롤백 설정 */
            con.setAutoCommit(false);//필기. 수동 커밋 롤백
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
        // 닫혀있지 않는 connection 객체를 넘겨준다.
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
    public static void close(Statement stmt) {
        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void close(ResultSet rset) {
        if(rset != null) {
            try {
                rset.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void commit(Connection con){
        try {
            if(con != null) con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rollback(Connection con){
        try {
            if(con != null) con.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

