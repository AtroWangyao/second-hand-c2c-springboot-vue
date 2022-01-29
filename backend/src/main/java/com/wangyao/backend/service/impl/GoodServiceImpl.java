package com.wangyao.backend.service.impl;

import com.wangyao.backend.bean.*;
import com.wangyao.backend.dao.CategoryDao;
import com.wangyao.backend.dao.GoodDao;
import com.wangyao.backend.dao.PictureDao;
import com.wangyao.backend.dao.TagDao;
import com.wangyao.backend.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private GoodDao goodDao;
    @Autowired
    private TagDao tagDao;


    @Override
    public int addGood(Good good) {
        goodDao.insert(good);
        return good.getId();
    }

    @Override
    public boolean updateGood(Good good) {
        return goodDao.update(good) > 0;
    }

    @Override
    public boolean deleteGood(Good good) {
        return goodDao.delete(good) > 0;
    }

    @Override
    public Good getGoodById(int id) {
        return goodDao.getGoodById(id);
    }

    @Override
    public List<Good> getAllGood() {
        return goodDao.getAllGood();
    }

    @Override
    public List<Good> getAllGoodByUserId(int userId) {
        return goodDao.getAllGoodByUserId(userId);
    }

    @Override
    public List<Good> getAllGoodWithUser() {
        return goodDao.getAllGoodWithUser();
    }

    @Override
    public boolean addTag(Tag tag) {
        return tagDao.insert(tag) > 0;
    }

    @Override
    public boolean updateTag(Tag tag) {
        return tagDao.update(tag) > 0;
    }

    @Override
    public boolean deleteTag(Tag tag) {
        return tagDao.delete(tag) > 0;
    }

    @Override
    public Tag getTagById(int id) {
        return tagDao.getTagById(id);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagDao.getAllTag();
    }

    @Override
    public boolean addCategory(Category category) {
        return categoryDao.insert(category) > 0;
    }

    @Override
    public boolean updateCategory(Category category) {
        return categoryDao.update(category) > 0;
    }

    @Override
    public boolean deleteCategory(Category category) {
        return categoryDao.delete(category) > 0;
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryDao.getCategoryById(id);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryDao.getAllCategory();
    }



    @Override
    public int bindGoodAndTagByGood(Good good) {
        int sucRow = 0;
        int tagId;
        int goodId = good.getId();
        List<Tag> tagList = good.getTagList();
        for(int i=0;i<tagList.size();i++)
        {
            tagId = tagList.get(i).getId();
            if(goodDao.insertTagsetByGoodIdAndTagId(goodId,tagId) == true){
                sucRow++;
            }

        }
        return sucRow;
    }

//    @Override
//    public int unbindGoodAndTagByGood(Good good) {
//        int goodId = good.getId();
//        return goodDao.deleteTagsetByGoodId(goodId);
//    }
}
