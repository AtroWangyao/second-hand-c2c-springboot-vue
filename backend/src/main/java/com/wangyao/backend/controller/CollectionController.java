package com.wangyao.backend.controller;

import com.alibaba.fastjson.JSON;
import com.wangyao.backend.bean.Collection;
import com.wangyao.backend.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;
    @RequestMapping("/add")
    public String addCollection(@RequestBody Collection collection)
    {
        System.out.println(collection);
        if(collectionService.addCollection(collection) == true)
        {
            return JSON.toJSONString(collection);
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/update")
    public String updateCollection(@RequestBody Collection collection)
    {
        System.out.println(collection);
        if(collectionService.updateCollection(collection) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/delete")
    public String deleteCollection(@RequestBody Collection collection)
    {
        System.out.println(collection);
        if(collectionService.deleteCollection(collection) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/getCollectionListByUserId")
    public Object getCollectionListByUserId(@RequestParam("userId")int userId)
    {
        List<Collection> collectionList = collectionService.getAllCollectionByUserId(userId);
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("collectionList",collectionList);
        return JSON.toJSONString(res);
    }
    @RequestMapping("/exist")
    public Object exitCollection(@RequestParam("userId")int userId,@RequestParam("goodId")int goodId)
    {
        if( collectionService.existCollection(userId,goodId) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/get")
    public Object getCollection(@RequestParam("userId")int userId,@RequestParam("goodId")int goodId)
    {
        Collection collection = collectionService.getCollection(userId,goodId);
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("collection",collection);
        return JSON.toJSONString(res);
    }
}
