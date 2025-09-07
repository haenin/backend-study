package com.haenin.section03.properties.subsection02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ContextConfiguration {

    @Bean // 스프링이 다국어 처리를 지원
    public ReloadableResourceBundleMessageSource messageSource(){
        /* 설명. 접속하는 세션의 로케일(Locale)에 따라
            자동 재로딩하는 용도의 MessageSource 구현체 생성 */
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        /* 설명. 다국어 메시지를 읽어 올 설정파일(.properties)의
        *       이름과 경로를 설정*/
        /* 설명. 끝에 message는 경로가 아닌 파일의 접두사 */
        messageSource.setBasename("section03/properties/subsection02/message");

        /* 설명. 불러온 메시지를 설정한 해딩 시간동안 캐싱하기 위한 설정 */
        messageSource.setCacheSeconds(10); // 10초

        /* 설명. 기본 인코딩 셋을 설정 */
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

}
