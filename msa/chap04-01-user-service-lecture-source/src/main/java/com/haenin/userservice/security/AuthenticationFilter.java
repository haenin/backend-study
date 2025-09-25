package com.haenin.userservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haenin.userservice.dto.RequestLoginDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/*
 * 설명. JWT(Json Web Token)의 구조
 *
 * 설명.
 *  1. 헤더(Header)
 *    - typ: 토큰의 타입 지정(JWT)
 *    - alg: 해싱 알고리즘으로 Verify Signature에서 사용 됨
 * 설명.
 *  2. 내용 또는 정보(Payload)
 *    - 토큰에 담을 정보가 들어 있음
 *    - 담는 정보의 한 조각을 클레임(claim - name과 value의 쌍으로 구성)이라 부름
 *       a. 등록된 클레임(registered claim)
 *          : 토큰에 대한 정보가 담김
 *            (iss: 토큰 발급자(issuer)
 *             sub: 토큰 제목(subject)
 *             aud: 토큰 대상자(audience)
 *             exp: 토큰의 만료 시간(expiration)
 *             nbf: 토큰 활성화(발급) 날짜(not before)
 *             iat: 토큰 활성화(발급) 시간(issued at))
 * 설명.
 *       b. 공개 클레임(public claim)
 *          : 사용자 정의 클레임으로 공개용 정보를 위해 사용(충돌 방지를 위해 URI로 구성)
 * 설명.
 *       c. 비공개 클레임(private claim)
 *         : 사용자 정의 클레임으로 서버(JWT 발급자)와 클라이언트 사이에 임의로 지정한 정보를 저장
 *            (충돌 발생 우려가 있어 조심해서 사용할 것)
 * 설명.
 *  3. 서명(Verify Signature)
 *    - Header 인코딩 값과 Payload 인코딩 값을 합쳐서 비밀 키로 해쉬(헤더의 해싱 알고리즘으로)하여 생성
 */

// 인증을 위한 필터
@Slf4j
public class AuthenticationFilter
            extends UsernamePasswordAuthenticationFilter {

    private Environment env;

    public AuthenticationFilter(
            AuthenticationManager authenticationManager,
            Environment env) {
        /* 설명. 우리가 만든 프로바이더를 알고있는 매니저를 인지시킴 */
        // 그래야 토큰을 매니저한테 던지고 매니저가 프로바이더한테 던짐
        super(authenticationManager);
        this.env = env;
    }

    // 필터에서 가장 먼저 실행되는 메서드
    // 인증 시도 메서드
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            RequestLoginDTO creds = new ObjectMapper() // 스프링 전용 매퍼
                    .readValue(request.getInputStream(), RequestLoginDTO.class); // 사용자가 던진 JSON문자열을 파싱해서 dto에 담아줌
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(                    // 현재는 권한 주지 않음
                            creds.getEmail(), creds.getPwd(),new ArrayList<>()) // 권한은 여러개 일수 있어서 컬렉션 타입으로 되어있다.
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // 로그인 성공
    // 돌아오는 길에 실행되는 메서드
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            // 인증에 성공한 객체가 담겨 돌아옴
                                            Authentication authResult)
            throws IOException, ServletException {
        log.info("로그인 성공 이후 spring securityr가 " +
                "Authentication 객체로 관리되며 넘어옴: " +
            "{}", authResult);

        /* 설명. JWT 토큰 제작 */
        // 로그인 한 애들한테 토큰 줄거임
        /* 설명. 1.JWT 토큰 제작을 위한 재료 추출 */

        // principle -> 스프링이 제공하는 유저타입으로 타입변환
        /* 설명. 프로바이더에서 반환한 내용 중
        *       User의 내용은 principal로 저장되어 있음 */
        /* 설명. 토큰의 payload에
         *       (id, 가진 권한들, 만료시간)을 담을 예정*/
        String id = ((User)authResult.getPrincipal()).getUsername();
        log.info("회원의 아이디(이메일): {}",id);

        List<String> roles = authResult.getAuthorities().stream()
//                .map(role -> role.getAuthority())
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        log.info("List<String> 형태로 뽑아낸 " +
                "로그인 한 회원의 권한들: {}",roles);
        log.info("만료 시간: {},", env.getProperty("token.expiration_time"));

        /* 설명. 2. 재료를 활용한 JWT 토큰 제작
        *       -> 라이브러 추가*/
        Claims claims = Jwts.claims().setSubject(id);
        claims.put("auth",roles);

        String token = Jwts.builder()
                // 등록된 클레임 + 비공개 클레임
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()
                        + Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();
        response.addHeader("token", token);
        /* 설명. 로그인에 성공한 사람의 정보(Authentication 객체)를
        *       활용하고 싶다면(Controller) */
        /* 설명. 1. 코드 상에서 */
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        /* 설명. 2. 매개변수에서 */
        // @AuthenticationPrincipal UserDetails user
    }
}
