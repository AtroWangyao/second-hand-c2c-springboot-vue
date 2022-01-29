package com.wangyao.backend.controller;

import com.alibaba.fastjson.JSON;
import com.wangyao.backend.bean.City;
import com.wangyao.backend.bean.Comment;
import com.wangyao.backend.service.AddressService;
import com.wangyao.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @RequestMapping("/add")
    public String addComment(@RequestBody Comment comment)
    {
        System.out.println(comment);
        if(commentService.addComment(comment) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/update")
    public String updateComment(@RequestBody Comment comment)
    {
        System.out.println(comment);
        if(commentService.updateComment(comment) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/getCommentListByGoodId")
    public Object getCommentListByGoodId(@RequestParam("goodId")int id)
    {
        List<Comment> commentList = commentService.getAllCommentByGoodId(id);
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("commentList",commentList);
        return JSON.toJSONString(res);
    }
}
