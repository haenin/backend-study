package com.haenin.userservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseOrderDTO {
    private String orderDate;
    private String orderTime;

    // 이름이 같아야 통신이 된다.
    private List<OrderMenuDTO> orderMenus;
}
