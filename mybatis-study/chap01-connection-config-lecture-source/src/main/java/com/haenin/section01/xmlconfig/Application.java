package com.haenin.section01.xmlconfig;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Application {
    public static void main(String[] args) {
        String resource = "mybatis-config.xml";

        /* 설명. JDBC에서의 Connection 객체 같은 개념 */
        SqlSession session = null;
        // xml파일과 스트림 관계
        try {
            // 설계도
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 설계도 대로 공장 만들기
            SqlSessionFactory sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(inputStream);
            // 공장은 connection 객체를 찍어냄
            // false를 주어야 수동 커밋 가능 (두개를 다 만족해야 함)
            session = sqlSessionFactory.openSession(false); // false안하면 true가 기본값
            java.util.Date nowDate = session.selectOne("mapper.selectNow");
            System.out.println("nowDate = " + nowDate);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }
}
