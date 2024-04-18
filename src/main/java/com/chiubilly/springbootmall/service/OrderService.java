package com.chiubilly.springbootmall.service;

import com.chiubilly.springbootmall.dto.CreateOrderRequest;
import com.chiubilly.springbootmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);
    Integer createOrder(Integer orderId, CreateOrderRequest createOrderRequest);

}
