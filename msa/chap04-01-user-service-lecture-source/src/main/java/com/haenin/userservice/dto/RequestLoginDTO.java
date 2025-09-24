package com.haenin.userservice.dto;

import lombok.Data;

@Data
public class RequestLoginDTO {
    private String email;
    private String pwd;
}
