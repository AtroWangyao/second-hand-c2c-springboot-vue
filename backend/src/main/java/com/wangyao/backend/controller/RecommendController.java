package com.wangyao.backend.controller;

import com.alibaba.fastjson.JSON;
import com.wangyao.backend.bean.Order;
import com.wangyao.backend.bean.Recommend;
import com.wangyao.backend.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("recommend")
public class RecommendController {
    @Autowired
    private RecommendService recommendService;
    @RequestMapping("/add")
    public String addRecommend(@RequestBody Recommend recommend)
    {
        System.out.println(recommend);
        if(recommendService.addRecommend(recommend) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/recreate")
    public String recreateRecommend(@RequestBody Recommend[] recommendList) //将原有的该type下的推荐清空,然后重新构建该type的推荐
    {
            //前端确保不会传空数组
            if(recommendService.recreateRecommend(recommendList) == true)
            {
                return "suc";
            }
            else
            {
                return "def";
            }
    }
    @RequestMapping("/delete")
    public String deleteRecommend(@RequestBody Recommend recommend)
    {
        System.out.println(recommend);
        if(recommendService.deleteRecommend(recommend) == true)
        {
            return "suc";
        }
        else
        {
            return "def";
        }
    }
    @RequestMapping("/allByType")
    public String allRecommendByType(@RequestParam("type")int type)
    {
        List<Recommend> recommendList = recommendService.getAllRecommendByType(type);
        System.out.println(recommendList);
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("data",recommendList);
        return JSON.toJSONString(res);
    }

}
