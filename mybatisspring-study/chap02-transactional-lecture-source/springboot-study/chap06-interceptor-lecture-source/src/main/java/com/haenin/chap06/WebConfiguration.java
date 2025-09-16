package com.haenin.chap06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* 설명. WebMvcConfigurer 관련 설정:
     Interceptor 추가 및 static(정적) 리소스 호출 경로 등록 설정 */
@Configuration  // 인터셉터 콩으로 관리하고 설정파일로 특별한 방식으로 등록해줘야함
public class WebConfiguration implements WebMvcConfigurer {
    private StopwatchInterceptor stopwatchInterceptor;

    // 생성자 주입
    @Autowired
    public WebConfiguration(StopwatchInterceptor stopwatchInterceptor) {
        this.stopwatchInterceptor = stopwatchInterceptor;
    }

    /* 설명. 인터셉터 추가 및 동작 요청 패턴 설정 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(stopwatchInterceptor)
               .excludePathPatterns("/css/**");
    }

    /* 설명. 정적 리소스 호출을 위한 설정 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:static/css/")
                .setCachePeriod(100); // 응답 속도 향상을 위한 캐싱 시간 설정
    }
}
