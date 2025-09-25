package com.haenin.orderservice.controller;

import com.haenin.orderservice.dto.OrderDTO;
import com.haenin.orderservice.dto.ResponseOrderDTO;
import com.haenin.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<List<ResponseOrderDTO>> getUserOrders(@PathVariable int userId){
        List<OrderDTO> orderDTOList = orderService.getOrderByUserId(userId);
        List<ResponseOrderDTO> returnValue = orderDTOToResponseOrder(orderDTOList);
        return ResponseEntity.ok().body(returnValue);
    }
    // 화면에 뿌릴 거는 따로 만들어보자
    private List<ResponseOrderDTO> orderDTOToResponseOrder(List<OrderDTO> orderDTOList) {

        List<ResponseOrderDTO> responseList = new ArrayList<>();

        for (OrderDTO orderDTO : orderDTOList) {
            ResponseOrderDTO responseOrder = new ResponseOrderDTO();
            responseOrder.setOrderDate(orderDTO.getOrderDate());
            responseOrder.setOrderTime(orderDTO.getOrderTime());
            responseOrder.setOrderMenus(orderDTO.getOrderMenus());

            responseList.add(responseOrder);
        }

        return responseList;
    }
}
