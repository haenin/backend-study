package com.haenin.userservice.dto;

import lombok.Data;

@Data
public class ResponseFindUserDTO {
    private String email;
    private String name;
    private String userId;

}
