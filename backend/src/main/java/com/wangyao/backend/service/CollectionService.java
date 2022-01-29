package com.wangyao.backend.service;

import com.wangyao.backend.bean.Collection;

import java.util.List;

public interface CollectionService {
    //增收藏
    public boolean addCollection(Collection collection);
    //改收藏
    public boolean updateCollection(Collection collection);
    //删收藏
    public boolean deleteCollection(Collection collection);
    //查收藏,通过收藏ID
    public Collection getCollectionById(int id);
    //查用户下的所有收藏
    public List<Collection> getAllCollectionByUserId(int userId);
    //是否存在收藏
    public boolean existCollection(int userId,int goodId);
    //查收藏
    public Collection getCollection(int userId, int goodId);
}
