package com.wangyao.backend.service;

import com.wangyao.backend.bean.Category;
import com.wangyao.backend.bean.Good;
import com.wangyao.backend.bean.Picture;
import com.wangyao.backend.bean.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
public interface GoodService {
    //增商品(回调id)
    public int addGood(Good good);
    //改商品
    public boolean updateGood(Good good);
    //删商品
    public boolean deleteGood(Good good);
    //查商品,通过商品ID
    public Good getGoodById(int id);
    //查所有商品
    public List<Good> getAllGood();
    //查所有商品,通过userId
    public List<Good> getAllGoodByUserId(int userId);
    //查所有商品和他的用户
    public List<Good> getAllGoodWithUser();

    //增标签
    public boolean addTag(Tag tag);
    //改标签
    public boolean updateTag(Tag tag);
    //删标签
    public boolean deleteTag(Tag tag);
    //查标签,通过标签ID
    public Tag getTagById(int id);
    //查所有标签
    public List<Tag> getAllTag();

    //增种类
    public boolean addCategory(Category category);
    //改种类
    public boolean updateCategory(Category category);
    //删种类
    public boolean deleteCategory(Category category);
    //查种类,通过标签ID
    public Category getCategoryById(int id);
    //查所有标签
    public List<Category> getAllCategory();
    



    //绑定good内Taglist中所有的tag,建立good与tag的关系表
    public int bindGoodAndTagByGood(Good good);




//    public int unbindGoodAndTagByGood(Good good);
}
