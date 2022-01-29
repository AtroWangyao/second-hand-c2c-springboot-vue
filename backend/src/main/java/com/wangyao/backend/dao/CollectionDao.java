package com.wangyao.backend.dao;

import com.wangyao.backend.bean.Collection;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//，它用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean。
public interface CollectionDao {
    //增
    public int insert(Collection collection);
    //改
    public int update(Collection collection);
    //删
    public int delete(Collection collection);
    //查,通过ID
    public Collection getCollectionById(int id);
    //查所有,通过用户ID
    public List<Collection> getAllCollectionByUserId(int userId);
    //查所有,通过用户ID和商品ID
    public Collection getCollectionByUserIdAndGoodId(@Param("userId") int userId, @Param("goodId") int goodId);


}
