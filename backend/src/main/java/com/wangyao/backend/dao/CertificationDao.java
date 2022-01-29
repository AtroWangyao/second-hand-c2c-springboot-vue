package com.wangyao.backend.dao;

import com.wangyao.backend.bean.Certification;
import com.wangyao.backend.bean.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//，它用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean。
public interface CertificationDao {
    //增
    public int insert(Certification certification);
    //改
    public int update(Certification certification);
    //删
    public int delete(Certification certification);
    //查,通过ID
    public Certification getCertificationById(int id);
    //查所有
    public List<Certification> getAllCertification();
    //查该用户下的验证
    public Certification getAllCertificationByUserId(int userId);
}
