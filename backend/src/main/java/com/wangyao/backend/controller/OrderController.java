package com.wangyao.backend.controller;

import com.alibaba.fastjson.JSON;
import com.wangyao.backend.bean.Category;
import com.wangyao.backend.bean.Order;
import com.wangyao.backend.bean.Picture;
import com.wangyao.backend.bean.Tag;
import com.wangyao.backend.service.OrderService;
import com.wangyao.backend.service.PictureService;
import com.wangyao.backend.util.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PictureService pictureService;


    @RequestMapping("/addOrder")
    public String addOrder(@RequestBody Order order) {
        System.out.println(order);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String number = sdf.format(order.getDate()) +  String.format("%06d",  order.getGoodId()) + String.format("%02d",  order.getDealType());//订单编号由日期,商品id和交易方式组成
        System.out.println(number);
        order.setNumber(number);
        if( orderService.addOrder(order) == true){
            return "suc";
        }else{
            return "def";
        }
    }
    @RequestMapping("/deleteOrder")
    public String deleteOrder(@RequestBody Order order) {
        System.out.println(order);
        if(orderService.deleteOrder(order) == true){
            return "suc";
        }else{
            return "def";
        }

    }
    @RequestMapping("/updateOrder")
    public String updateOrder(@RequestBody Order order)
    {
        System.out.println(order);
        if(orderService.updateOrder(order) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/allOrder")
    public Object allOrder(){
        List<Order> orderList = orderService.getAllOrder();
        System.out.println(orderList);
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("data",orderList);
        return JSON.toJSONString(res);
    }
    @RequestMapping("/allOrderByBuyerId")
    public Object allOrderByBuyerId(@RequestParam("buyerId")int buyerId){
        List<Order> orderList = orderService.getOrderByBuyerId(buyerId);
        System.out.println(orderList);
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("data",orderList);
        return JSON.toJSONString(res);
    }
    @RequestMapping("/allOrderBySellerId")
    public Object allOrderBySellerId(@RequestParam("sellerId")int sellerId){
        List<Order> orderList = orderService.getOrderBySellerId(sellerId);
        System.out.println(orderList);
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("data",orderList);
        return JSON.toJSONString(res);
    }
    @RequestMapping("/processingOrder")
    public Object processingOrder(@RequestParam("orderId")int orderId){

        if(orderService.processingOrder(orderId) == true)
        {
            System.out.println("suc");
            return "suc";
        }
        else
        {
            return "def";
        }
    }
}
