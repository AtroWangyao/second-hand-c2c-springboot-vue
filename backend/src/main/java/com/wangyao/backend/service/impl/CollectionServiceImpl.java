package com.wangyao.backend.service.impl;

import com.wangyao.backend.bean.Collection;
import com.wangyao.backend.dao.CollectionDao;
import com.wangyao.backend.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionDao collectionDao;



    @Override
    public boolean addCollection(Collection collection) {
        return collectionDao.insert(collection) > 0;
    }

    @Override
    public boolean updateCollection(Collection collection) {
        return collectionDao.update(collection) > 0;
    }

    @Override
    public boolean deleteCollection(Collection collection) {
        return collectionDao.delete(collection) > 0;
    }

    @Override
    public Collection getCollectionById(int id) {
        return collectionDao.getCollectionById(id);
    }
    @Override
    public List<Collection> getAllCollectionByUserId(int userId) {
        return collectionDao.getAllCollectionByUserId(userId);
    }
    @Override
    public boolean existCollection(int userId,int goodId) {
        return collectionDao.getCollectionByUserIdAndGoodId(userId,goodId) != null;
    }

    @Override
    public Collection getCollection(int userId, int goodId) {
        return collectionDao.getCollectionByUserIdAndGoodId(userId,goodId);
    }

}
