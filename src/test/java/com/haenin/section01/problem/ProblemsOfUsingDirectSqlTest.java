package com.haenin.section01.problem;

import org.junit.jupiter.api.*;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.UIManager.getInt;

public class ProblemsOfUsingDirectSqlTest {
    private Connection con;

    @BeforeEach
    void setConnection() throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/menudb";
        String user="root";
        String password="mariadb";

        Class.forName(driver);

        con = DriverManager.getConnection(url, user, password);
        con.setAutoCommit(false); // 수동커밋

    }

    @AfterEach
    void closeConnection() throws SQLException{
        con.rollback();
        con.close();
    }

    /* 설명.
    *   JDBC API를 이용해 직접 SQL을 다룰 때
    *   발생할 수 있는 문제점들
    *   1. 데이터 변환, SQL 작성, JDBC API 코드 등의 중복 작성(개발 시간 증가, 유지보수성 저하)
    *   2. SQL에 의존하여 개발
    *   3. 패러다임 불일치(상속, 연관관계, 객체 그래프 탐색)
    *   4. 동일성 보장 문제
    * */

    /* 설명. 1. 데이터 변환, SQL 작성,
        JDBC API 코드 등의 중복 작성(개발 시간 증가, 유지보수성 저하) */

    @DisplayName("직접 SQL을 작성하여 메뉴를 조회할 때 " +
            "발생하는 문제 확인")
    @Test
    void testDirectSelectSql() throws SQLException {

        // given
        String query = "SELECT MENU_CODE, MENU_NAME, MENU_PRICE, CATEGORY_CODE, ORDERABLE_STATUS FROM TBL_MENU";
        // when
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<Menu> menuList = new ArrayList<>();
        while(rs.next()){
            Menu menu = new Menu();
            menu.setMenuCode(rs.getInt("MENU_CODE"));
            menu.setMenuName(rs.getString("MENU_NAME"));
            menu.setMenuPrice(rs.getInt("MENU_PRICE"));
            menu.setCategoryCode(rs.getInt("CATEGORY_CODE"));
            menu.setOrderableStatus(rs.getString("ORDERABLE_STATUS"));

            menuList.add(menu);
        }
        // then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);

        rs.close();
        stmt.close();
    }
}
