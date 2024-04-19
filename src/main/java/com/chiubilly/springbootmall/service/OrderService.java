package com.chiubilly.springbootmall.service;

import com.chiubilly.springbootmall.dto.CreateOrderRequest;
import com.chiubilly.springbootmall.dto.OrderQueryParams;
import com.chiubilly.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);


    Order getOrderById(Integer orderId);
    Integer createOrder(Integer orderId, CreateOrderRequest createOrderRequest);

}
