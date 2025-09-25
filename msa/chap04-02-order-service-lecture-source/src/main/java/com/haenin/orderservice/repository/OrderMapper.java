package com.haenin.orderservice.repository;

import com.haenin.orderservice.aggregate.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> selectOrderByUserId(int userId);

}
