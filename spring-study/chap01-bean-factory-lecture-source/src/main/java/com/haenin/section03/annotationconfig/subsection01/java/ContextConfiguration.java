package com.haenin.section03.annotationconfig.subsection01.java;

import com.haenin.common.MemberDAO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 설정파일이자 콩이 됨
@Configuration("config")

/* 설명. 1. 기본적으로는 설정파일이 있는 패키지 및 하위만 Scan
    하지만 beanPackage를 바꾸면 다른 범위도 가능하다. */
//@ComponentScan(basePackages = "com.haenin") // 이 패키지 안에 컴포넌트 어노테이션을 스캔
/* 설명. 2. 범위 및 필터를 적용해서 제외하고자 하는 bean을 등록하는 경우(excludeFilters) */
//@ComponentScan(basePackages = "com.haenin",
//                excludeFilters = {
//        @ComponentScan.Filter(
//                /* 설명. 2-1. 타입으로 설정 */
////                type = FilterType.ASSIGNABLE_TYPE,
////                classes = {MemberDAO.class}
//                /* 설명. 2-2. 어노테이션 종류로 설정 */
//                  type = FilterType.ANNOTATION,
//                  classes =  {org.springframework.stereotype.Repository.class}
//        )
//                })
/* 설명. 3. 범위 및 필터를 착용해서 포함(등록)하고자 하는 bean을 등록하는 경우(includeFilters) */
@ComponentScan(basePackages = "com.haenin", // 현재 범위의 bean들은 일단 모두 off
                useDefaultFilters = false,
                includeFilters = {
                    @ComponentScan.Filter(
                            type = FilterType.ASSIGNABLE_TYPE,
                            classes = {MemberDAO.class} // MemberDAO 타입만 등록
                    )
                })
public class ContextConfiguration {

}
