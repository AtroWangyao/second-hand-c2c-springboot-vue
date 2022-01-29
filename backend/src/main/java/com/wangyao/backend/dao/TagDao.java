package com.wangyao.backend.dao;

import com.wangyao.backend.bean.City;
import com.wangyao.backend.bean.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//，它用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean。
public interface TagDao {
    //增
    public int insert(Tag good);
    //改
    public int update(Tag good);
    //删
    public int delete(Tag good);
    //查,通过ID
    public Tag getTagById(int id);
    //查所有
    public List<Tag> getAllTag();
}
