package com.haenin.section02.prepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.haenin.common.JDBCTemplate.close;
import static com.haenin.common.JDBCTemplate.getConnection;

public class Application2 {

    public static void main(String[] args) {
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 사번을 입력하세요: ");
        String empId = sc.nextLine();

        System.out.print("퇴사 여부까지 입력하세요: ");
        String entYn = sc.nextLine();

        try {

            /* 설명. ?(placeholder)를 사용하면 1. 가독성 증가(하나의 문자열), 2. 전처리 가능 3. Statement 대비 속도 향상*/
            pstmt = con.prepareStatement(
                    "select * from EMPLOYEE where EMP_ID = ? and ENT_YN = ?");
           
           /* 설명. ?인 placeholder의 위치별로 들어갈 값들은 setter를 이용해 매꿔준다. */
            pstmt.setString(1, empId);
            pstmt.setString(2, entYn);

            rset = pstmt.executeQuery();

            if(rset.next()){
                System.out.println("입력하신 " + empId +"번의 사원은 "
                            + rset.getString("EMP_NAME") + "이고, 월급은 "
                            + rset.getInt("SALARY") + "입니다. ");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }


    }
}
