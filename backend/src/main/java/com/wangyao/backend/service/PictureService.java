package com.wangyao.backend.service;

import com.wangyao.backend.bean.Category;
import com.wangyao.backend.bean.Picture;

import java.util.List;

public interface PictureService {
    //增图片
    public int addPicture(Picture picture);
    //改图片
    public boolean updatePicture(Picture picture);
    //删图片
    public boolean deletePicture(Picture picture);
    //查图片,通过图片ID
    public Picture getPictureById(int id);
    //查所有
    public List<Picture> getAllPicture();

    public boolean bindGoodAndPictureByGoodIdAndPictureId(int goodId, int pictureId);
}
