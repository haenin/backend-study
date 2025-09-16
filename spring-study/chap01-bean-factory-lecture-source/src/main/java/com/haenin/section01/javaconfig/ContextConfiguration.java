package com.haenin.section01.javaconfig;

import com.haenin.common.MemberDAO;
import com.haenin.common.MemberDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//pojo, @configuration -> 콩으로 관리되는 설정파일이 됨
@Configuration
public class ContextConfiguration {
    // bean은 스프링이 호출해준다.
    // 반환값을 가지고 콩으로 만들어 놓는다.
    // pojo에서 이제 bean이 된 것
//    @Bean("member") 이거도 가능
    /* 설명. 메소드명이 기본 bean의 이름이 되고,
        다른 이름을 하고 싶으면 어노테이션에 이름을 작성할 수 있다. */
    @Bean(name = "member")
    public MemberDTO
    getMember(){
        return new MemberDTO(1, "user01", "pass01", "홍길동");
    }
}
