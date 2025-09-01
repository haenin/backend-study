package com.haenin.section01.insert;

import java.sql.Connection;
import java.sql.SQLException;

import static com.haenin.common.JDBCTemplate.*;

public class MenuService {
    /* 설명. Service 계층은 Connection 객체 생성 및 소멸(하나의 트랜잭션 단위) 및
    *       비즈니스 로직, 트랜잭션 처리(commit/rollback)를 담당*/
    // service부터 트랜잭션 단위 -> 서비스계층 메소드 하나당 사용자 요청을 처리함
    public void registMenu(Menu menu) {
        Connection con = getConnection();
        MenuRepository repository = new MenuRepository();
        int result = repository.insertMenu(menu, con);

        /* 설명. DML 작업은 기본적으로 트랜잭션당 하나의 DML 작업이더라도 성공/실패에 대란 로직 작성 */
        if(result == 1){
            /* 설명. insert 성공 시 commit */
            commit(con);
        }else {
            /* 설명. insert 실패 시 rollback */
            rollback(con);
            System.out.println();
        }
        close(con);
    }
}
