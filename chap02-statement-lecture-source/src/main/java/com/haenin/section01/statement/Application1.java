package com.haenin.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.haenin.common.JDBCTemplate.close;
import static com.haenin.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {

        Connection con = getConnection();
        System.out.println("con = " + con); // 필기. DB와의 연동 확인

        Statement stmt = null; // 필기. SQL 및 쿼리 실행 결과를 싣고 dbms 오가는 트럭같은 개념
        ResultSet rset = null; // 필기. 쿼리(SELECT)의 결과(JAVA의 타입)

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery("SELECT * FROM EMPLOYEE");

            /* 필기. 다중행 조회 결과를 반복하며 추출 */
            // 필기. 한 행이 출력
            while (rset.next()) {
                /* 필기. 반복문에서는 단일행을 하나하나 컬럼별로 꺼내쓴다. */
                System.out.println(rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 필기. 모듈화
            /* 필기. 코드의 줄 수를 줄이고 가독성을 높이기 위해 모듈화 */
            close(rset);
            close(stmt);
            close(con);
        }
//            try {
//                rset.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            try {
//                stmt.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//            try {
//                con.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
}