package com.wangyao.backend.dao;

import com.wangyao.backend.bean.Picture;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//，它用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean。
public interface PictureDao {
    //增
    public int insert(Picture picture);
    //改
    public int update(Picture picture);
    //删
    public int delete(Picture picture);
    //查,通过ID
    public Picture getPictureById(int id);
    //查所有
    public List<Picture> getAllPicture();

    public boolean insertPicsetByGoodIdAndPictureId(@Param("goodId") int goodId, @Param("pictureId") int pictureId);
}
