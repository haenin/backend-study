package com.haenin.userservice.dto;

import lombok.Data;

@Data
public class OrderMenuDTO {
    private int orderCode;
    private int menuCode;
    private int orderAmount;

}
