package com.haenin.section01.aop;

import lombok.*;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    // null이 들어갈거 같으면 래퍼클래스르 사용해야함
    private Long id; // 래퍼 클래스 쓰는 이유 id가 많을 경우
    private String name;
}
