package com.haenin.section01.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.haenin.common.JDBCTemplate.close;
import static com.haenin.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;

        /* 설명.
        *   DML(insert, update, delete 작업 시에는 변환결과가 int값이 된다.(ResultSet X)
        *   그리고 조회에서는 executeQuery() DML에서는 executeUpdate()를 사용한다.
        *  */
        // 필기. executeUpdate() -> 커맨드
        int result = 0;

        String query = "INSERT INTO TBL_MENU\n" +
                "(MENU_NAME, MENU_PRICE, CATEGORY_CODE, ORDERABLE_STATUS)\n" +
                "VALUES\n"+
                "(?, ?, ?, ?)";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,"커미맛식혜");
            pstmt.setInt(2, 2000);
            pstmt.setInt(3, 6);
            pstmt.setString(4,"Y");

            result = pstmt.executeUpdate();
            if(result>0){
                System.out.println("insert 결괴: " + result + "개의 행이 추가됨");

                /* 설명. JDBCTemplate에서 수동 커밋 설정 이후 ... */
                con.commit(); //필기. 수동 커밋
            }else{
                System.out.println("insert 실패");
                con.commit(); //필기. 수동 롤백
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }
    }
}
