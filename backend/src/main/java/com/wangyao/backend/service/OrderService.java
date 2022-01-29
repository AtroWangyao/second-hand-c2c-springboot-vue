package com.wangyao.backend.service;

import com.wangyao.backend.bean.Category;
import com.wangyao.backend.bean.Order;
import com.wangyao.backend.bean.Tag;

import java.util.List;

public interface OrderService {
    //增订单
    public boolean addOrder(Order order);
    //改订单
    public boolean updateOrder(Order order);
    //删订单
    public boolean deleteOrder(Order order);
    //查订单,通过订单ID
    public Order getOrderById(int id);
    //查,通过买家ID
    public List<Order> getOrderByBuyerId(int buyerId);
    //查,通过卖家ID
    public List<Order> getOrderBySellerId(int sellerId);
    //查所有订单
    public List<Order> getAllOrder();

    boolean processingOrder(int orderId);
}
