package com.haenin.section03.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.haenin.common.JDBCTemplate.close;
import static com.haenin.common.JDBCTemplate.getConnection;

public class Application {
    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;

        /* 설명. soft delete */
//        String query = "UPDATE TBL_MENU SET ORDERABLE_STATUS = 'N' WHERE MENU_CODE =?"

       /* 설명. hard delete */
        String query = "DELETE FROM TBL_MENU WHERE MENU_CODE = ?";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, 24);

            result =pstmt.executeUpdate();
            if(result == 1){
                con.commit();
            }else{
                con.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }
    }
}
