package com.chiubilly.springbootmall.dao;

import com.chiubilly.springbootmall.model.Order;
import com.chiubilly.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {


    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);
    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
