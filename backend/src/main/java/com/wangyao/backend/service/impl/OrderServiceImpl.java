package com.wangyao.backend.service.impl;

import com.wangyao.backend.bean.Category;
import com.wangyao.backend.bean.Order;
import com.wangyao.backend.bean.Tag;
import com.wangyao.backend.dao.CategoryDao;
import com.wangyao.backend.dao.OrderDao;
import com.wangyao.backend.dao.TagDao;
import com.wangyao.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;



    @Override
    public boolean addOrder(Order order) {
       ;
        return  orderDao.insert(order) > 0;
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderDao.update(order) > 0;
    }

    @Override
    public boolean deleteOrder(Order order) {
        return orderDao.delete(order) > 0;
    }

    @Override
    public Order getOrderById(int id) {
        return orderDao.getOrderById(id);
    }

    @Override
    public List<Order> getOrderByBuyerId(int buyerId) {
        return orderDao.getOrderByBuyerId(buyerId);
    }

    @Override
    public List<Order> getOrderBySellerId(int sellerId) {
        return orderDao.getOrderBySellerId(sellerId);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderDao.getAllOrder();
    }

    @Override
    public boolean processingOrder(int orderId) {
        Order order = orderDao.getOrderById(orderId);
        if(order.getStatus() == 7){
            order.setStatus(9);//商家如果同意面交的话,直接进入到等待用户确认面交完成.
        }
        else if(order.getStatus() == 9){
            order.setStatus(4);//用户确认面交完成后 关闭订单.
        }
        else{
            order.setStatus(order.getStatus() + 1 ); //订单状态进行到下一步
        }

        return orderDao.update(order) >  0;
    }

}
