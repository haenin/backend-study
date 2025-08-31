package com.haenin.section02.prepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.haenin.common.JDBCTemplate.close;
import static com.haenin.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {
            // 필기. stmt = con.createStatement();
            // 필기. 얘는 쿼리를 가지고 객체를 생성
            pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE");
            rset = pstmt.executeQuery();

            while (rset.next()) {
                System.out.println(rset.getString("EMP_ID") + "사번의 "
                + rset.getString("EMP_NAME") +"사원");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt); //필기. 이미 만들어놓은 statement의 자식이기에 다형성으로 메소드 실행
            /* 설명. PreparedStatement는 Statement의 자식 클래스라 다형성에 의해 기존 close()활용 가능 */
            close(con);
        }
    }
}
