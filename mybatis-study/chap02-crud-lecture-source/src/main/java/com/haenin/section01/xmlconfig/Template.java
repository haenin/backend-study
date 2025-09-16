package com.haenin.section01.xmlconfig;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/* 설명. SqlSession 객체를 반환하는 Mybatis 관련 코드 모음(모듈화) */
// 커넥션 객체 달라할때 주는 클래스
public class Template {
    private static SqlSessionFactory sqlSessionFactory;

    /* 설명. Lazy Singleton 방식으로 작성 */
    public static SqlSession getSqlSession(){
        if(sqlSessionFactory == null){
            // lazy singleton
            String resource
                    = "com/haenin/section01/xmlconfig/mybatis-config.xml";
            try {
                InputStream inputStream
                        = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        // 싱글톤하게 생성된 Factory에서
        // ConnectionPool에 있는 SqlSession 반환
        return sqlSessionFactory.openSession(false);
    }
}
