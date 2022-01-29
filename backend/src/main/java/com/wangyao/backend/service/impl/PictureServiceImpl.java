package com.wangyao.backend.service.impl;

import com.wangyao.backend.bean.Picture;
import com.wangyao.backend.dao.PictureDao;
import com.wangyao.backend.service.GoodService;
import com.wangyao.backend.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureDao pictureDao;
    @Override
    public int addPicture(Picture picture) {
        return pictureDao.insert(picture) ;
    }

    @Override
    public boolean updatePicture(Picture picture) {
        return pictureDao.update(picture) > 0;
    }

    @Override
    public boolean deletePicture(Picture picture) {
        return pictureDao.delete(picture) > 0;
    }

    @Override
    public Picture getPictureById(int id) {
        return pictureDao.getPictureById(id);
    }

    @Override
    public List<Picture> getAllPicture() {
        return pictureDao.getAllPicture();
    }

    @Override
    public boolean bindGoodAndPictureByGoodIdAndPictureId(int goodId, int pictureId) {
        return pictureDao.insertPicsetByGoodIdAndPictureId(goodId,pictureId);
    }

}
