package com.wangyao.backend.service.impl;

import com.wangyao.backend.bean.Recommend;
import com.wangyao.backend.dao.RecommendDao;
import com.wangyao.backend.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private RecommendDao recommendDao;


    @Override
    public boolean addRecommend(Recommend recommend) {
        return recommendDao.insert(recommend)>0;
    }

    @Override
    public boolean updateRecommend(Recommend recommend) {
        return recommendDao.update(recommend)>0;
    }

    @Override
    public boolean deleteRecommend(Recommend recommend) {
        return recommendDao.delete(recommend)>0;
    }

    @Override
    public boolean recreateRecommend(Recommend[] recommendList) {

        int length = recommendList.length;
        int type = recommendList[0].getType();//因为传入的数组最少有1个,并且他们的type都是相同的,所以随便取一个.
        try{
            recommendDao.deleteByType(type); //先删掉所有该推荐类型的推荐信息
            for(int i = 0;i < length;i++){
                recommendDao.insert(recommendList[i]);//然后再把推荐信息插进去
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<Recommend> getAllRecommendByType(int type) {
        return recommendDao.getAllRecommendByType(type);
    }
}
