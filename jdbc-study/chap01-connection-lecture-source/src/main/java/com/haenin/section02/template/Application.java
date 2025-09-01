package com.haenin.section02.template;

import java.sql.Connection;

import static com.haenin.section02.template.JDBCTemplate.getConnection;
import static com.haenin.section02.template.JDBCTemplate.close;
// 필기. JDBCTemplate을 모듈화 하였다
// 필기. 메소드명까지 임포트
// 필기. 메소드일때는 static메소드랑 임포트할때도 static이어야 가능
public class Application {
    public static void main(String[] args) {
        /* 설명. main 로직에서 Connection 객체가 필요한 순간에 한줄 코딩으로 받아내기*/
//        Connection con = JDBCTemplate.getConnection();
        Connection con = getConnection();
        // 필기. close가 메소드안에있기 그걸 지우고 떄문에 닫는거는 내가해야한다
        System.out.println("con = " + con);

        // 필기. connection 객체를 활용한 구문(SQL문을 통해 DB와의 CURD)
        close(con);

    }
}
