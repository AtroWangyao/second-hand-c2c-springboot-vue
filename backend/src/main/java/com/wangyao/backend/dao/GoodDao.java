package com.wangyao.backend.dao;

import com.wangyao.backend.bean.Good;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//，它用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean。
public interface GoodDao {
    //增
    public int insert(Good good);
    //改
    public int update(Good good);
    //删
    public int delete(Good good);
    //查,通过商品ID
    public Good getGoodById(int id);
    //查所有商品
    public List<Good> getAllGood();
    //查所有商品和他的用户
    public List<Good> getAllGoodWithUser();
    //增标签商品关系表,通过双方ID
    public boolean insertTagsetByGoodIdAndTagId(@Param("goodId") int goodId, @Param("tagId") int tagId);

    List<Good> getAllGoodByUserId(int userId);
}
