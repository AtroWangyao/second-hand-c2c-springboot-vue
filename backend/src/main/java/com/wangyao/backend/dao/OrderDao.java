package com.wangyao.backend.dao;

import com.wangyao.backend.bean.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//，它用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean。
public interface OrderDao {
    //增
    public int insert(Order order);
    //改
    public int update(Order order);
    //删
    public int delete(Order order);
    //查,通过买家ID
    public List<Order> getOrderByBuyerId(int buyerId);
    //查,通过卖家ID
    public List<Order> getOrderBySellerId(int sellerId);
    //查,通过ID
    public Order getOrderById(int id);
    //查所有
    public List<Order> getAllOrder();
}
