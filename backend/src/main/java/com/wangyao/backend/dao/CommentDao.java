package com.wangyao.backend.dao;

import com.wangyao.backend.bean.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//，它用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean。
public interface CommentDao {
    //增
    public int insert(Comment comment);
    //改
    public int update(Comment comment);
    //删
    public int delete(Comment comment);
    //查,通过ID
    public Comment getCommentById(int id);
    //查所有
    public List<Comment> getAllComment();
    //查该商品下的所有,通过商品id
    public List<Comment> getAllCommentByGoodId(int goodId);
}
