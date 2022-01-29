package com.wangyao.backend.dao;

import com.wangyao.backend.bean.Recommend;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//，它用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean。
public interface RecommendDao {
    //增
    public int insert(Recommend recommend);
    //改
    public int update(Recommend recommend);
    //删
    public int delete(Recommend recommend);
    //删,通过类型
    public int deleteByType(int type);
    //查,通过ID
    public Recommend getRecommendById(int id);
    //查所有,通过类型
    public List<Recommend> getAllRecommendByType(int type);



}
