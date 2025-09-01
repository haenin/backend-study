package com.haenin.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.haenin.common.JDBCTemplate.close;
import static com.haenin.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {

        Connection con = getConnection();
        Statement stmt = null; 
        ResultSet rset = null; 
        
        /* 설명. Scanner를 활용해 사용자의 입력에 따른 사원 조회 */
        Scanner sc = new Scanner(System.in);
        System.out.print("조회하고자 하는 사원의 번호를 입력하세요: ");
        String empId =sc.nextLine();

        try {
            stmt = con.createStatement();
            // 필기. String이랑 쿼리안에 "" + empId + ""
            // 필기. 단점)  문자열로 끊고 이어야 함
            rset = stmt.executeQuery("SELECT * FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'");
            if(rset.next()){
                System.out.println("조회하신 "
                        + empId + "반의 사원은 " + rset.getString("EMP_NAME")+"입니다.");
            }else{
                System.out.println("해당 " + empId + "번의 사원은 존재하지 않습니다.");
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

    }
}