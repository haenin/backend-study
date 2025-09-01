package com.haenin.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.haenin.common.JDBCTemplate.close;

public class MenuRepository {

    public int insertMenu(Menu menu, Connection con) {
        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();
        try {
            prop.loadFromXML(
                    new FileInputStream("src/main/java/com/haenin/section01/insert/menu-mapper.xml"));
            String query = prop.getProperty("insertMenu");
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, menu.getMenuName());
            pstmt.setInt(2, menu.getMenuPrice());
            pstmt.setInt(3, menu.getCategoryCode());
            pstmt.setString(4, menu.getOrderableStatus());

            result = pstmt.executeUpdate();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;
    }
}
