package com.haenin.section02.update;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.haenin.common.JDBCTemplate.close;

public class MenuRepository {
    public int updateMenu(Menu menu, Connection con){
        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();
        try {
            prop.loadFromXML(
                    new FileInputStream(
                            "src/main/java/com/haenin/section02/update/menu-mapper.xml"));
            String query = prop.getProperty("updateMenu");
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, menu.getMenuName());
            pstmt.setInt(2, menu.getMenuPrice());
            pstmt.setInt(3, menu.getMenuCode());
            result = pstmt.executeUpdate();
       } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;
    }
}
