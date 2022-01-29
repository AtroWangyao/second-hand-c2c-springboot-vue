package com.wangyao.backend.service;

import com.wangyao.backend.bean.Recommend;

import java.util.List;

public interface RecommendService {
    //增
    public boolean addRecommend(Recommend recommend);
    //改
    public boolean updateRecommend(Recommend recommend);
    //删
    public boolean deleteRecommend(Recommend recommend);
    //重组
    boolean recreateRecommend(Recommend[] recommendList);
    //查全部,通过类型
    List<Recommend> getAllRecommendByType(int type);
}
