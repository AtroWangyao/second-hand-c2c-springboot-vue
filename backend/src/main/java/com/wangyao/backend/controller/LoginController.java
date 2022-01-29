package com.wangyao.backend.controller;


import com.alibaba.fastjson.JSON;
import com.wangyao.backend.bean.User;

import com.wangyao.backend.dao.UserDao;
import com.wangyao.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController //它继承自@Controller注解。主要作用就是返回JSON格式的字符串。
public class LoginController {
    @Autowired //变量注入
    UserDao userDao;
    @Autowired //变量注入
    UserService userService;

    @RequestMapping("/login")
    public Object login(@RequestBody User user){//@RequestBody的作用其实是将json格式的数据转为java对象
        System.out.println(user);
        user = userService.getUserByLoginMessage(user.getLoginName(),user.getLoginPassword());
        System.out.println(user);
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("user",user);
        if(user != null){
            res.put("res","suc");
        }
        else{
            res.put("res","def");
        }
        return JSON.toJSONString(res);
    }
    @RequestMapping("/register")
    public String register(@RequestBody User user)
    {
        System.out.println(user);
        if(userService.addUser(user))
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
}
