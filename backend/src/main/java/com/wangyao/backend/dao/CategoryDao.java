package com.wangyao.backend.dao;

import com.wangyao.backend.bean.Category;
import com.wangyao.backend.bean.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//，它用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean。
public interface CategoryDao {
    //增
    public int insert(Category good);
    //改
    public int update(Category good);
    //删
    public int delete(Category good);
    //查,通过ID
    public Category getCategoryById(int id);
    //查所有
    public List<Category> getAllCategory();
}
