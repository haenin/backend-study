package com.haenin.userservice.security;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;
import java.util.Collections;

/* 설명. Spring Security 모듈 추가 후
        default 로그인 페이지 제거 및
        인가 설정*/
@Configuration
// WebSecurity -> 조립공장 + 인가
public class WebSecurity {

    private JwtAuthenticationProvider jwtAuthenticationProvider;
    // JWT 토큰의 payload에 만료시간 만들다가 추가
    private Environment env;
    private JwtUtil jwtUtil;

    @Autowired
    public WebSecurity(
            JwtAuthenticationProvider jwtAuthenticationProvider,
            Environment env,
            JwtUtil jwtUtil) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.env = env;
        this.jwtUtil = jwtUtil;
    }

    // 매니저 안에 여러개 프로바이더 등록
    /* 설명. 우리가 만든 프로바이더 bean으로 등록 */
    @Bean
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(Collections.singletonList(jwtAuthenticationProvider));
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http, JwtUtil jwtUtil) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests( authz ->
//                authz.requestMatchers("/**").permitAll() // 어떤 형로의 url경로를 허용
                authz.requestMatchers(HttpMethod.GET, "/health").permitAll()
                        .requestMatchers(HttpMethod.POST,"/users/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/test").permitAll()
                        .requestMatchers(HttpMethod.GET,"/acturator/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/acturator/**").permitAll()
//                        .requestMatchers("/acturator/**").permitAll() // 이렇게 하면 요청행위 관계없음
//

                        // 인가 관련
//                        .requestMatchers(HttpMethod.GET,"/users/**").hasRole("ENTERPRISE")
                        .requestMatchers(HttpMethod.GET,"/users/**").hasAnyRole("ENTERPRISE","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/test1/**","/test2/**").hasAnyRole("ENTERPRISE","ADMIN")


                    /*  .requestMatchers(HttpMethod.GET,"/user/**").hasRole("USER") // ROLE_USER
                        .requestMatchers("/**").permitAll() */
                        .anyRequest().authenticated())
                // 세션을 안쓰겟다
                /* 설명. Session 방식이 아닌 JWT Token 방식을 사용하겠다. */
                /* 설명. Session 방식이 아닌
                        JWT Token 방식으로 인증된 회원(Authentication)을
                         Local Thread로 저장하겠다.*/
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        /* 설명. 매니지를 지닌 필터 등록 */
        http.addFilter(getAuthenticationFilter(authenticationManager()));

        /* 설명. 로그인 이후 토큰을 들고 온다면
        *       JwtFilter를 추가해서 검증하도록 함 */
        http.addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // 필터 등록 메소드
    /* 설명. Filter를 등록하기 위해 사용하는 메소드 */
    private Filter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new AuthenticationFilter(authenticationManager, env);
    }
}
