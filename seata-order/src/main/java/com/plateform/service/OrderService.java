package com.plateform.service;

import com.plateform.api.entity.Order;

import java.math.BigDecimal;

public interface OrderService {

    public String addOrder(Order order);

    void updateOrderStatus(Long userId, BigDecimal money, Integer status);
}
