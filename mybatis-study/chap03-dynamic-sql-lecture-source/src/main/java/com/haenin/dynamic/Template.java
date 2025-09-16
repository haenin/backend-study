package com.haenin.dynamic;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Template {
    private static SqlSessionFactory sqlSessionFactory;

    /* 설명. Lazy Singleton 방식으로 작성 */
    public static SqlSession getSqlSession(){
        if(sqlSessionFactory == null){
            // lazy singleton
            String resource
                    = "config/mybatis-config.xml";
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
