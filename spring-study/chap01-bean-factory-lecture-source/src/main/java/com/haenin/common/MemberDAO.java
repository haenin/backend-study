package com.haenin.common;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/* 설명. @Repository도 @Component계열이라 bean이 된다. */
// 콩이된다. -> dao, db쪽에서 에러가발생하면 자바의 exception타입으로 바꿔주는 기능이 있다.
@Repository
// 콩이될겁니다. -> 스프링이 기본생성자를 호출함
//@Component
public class MemberDAO {
    public MemberDAO() {
        System.out.println("콩 될때 이거 쓰나?");
    }
}
