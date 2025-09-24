package com.haenin.userservice.dto;

import lombok.Data;

@Data
public class ResponseRegistUserDTO {
    private String email;
    private String name;
    // 회원가입 이후 생성된 회원 고유 번호
    private String userId;
}
