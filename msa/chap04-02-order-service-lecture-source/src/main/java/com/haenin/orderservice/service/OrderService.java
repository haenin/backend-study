package com.haenin.orderservice.service;

import com.haenin.orderservice.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrderByUserId(int userId);
}
