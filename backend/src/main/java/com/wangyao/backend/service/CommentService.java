package com.wangyao.backend.service;

import com.wangyao.backend.bean.Comment;

import java.util.List;

public interface CommentService {
    //增评论
    public boolean addComment(Comment comment);
    //改评论
    public boolean updateComment(Comment comment);
    //删评论
    public boolean deleteComment(Comment comment);
    //查评论,通过评论ID
    public Comment getCommentById(int id);
    //查所有评论
    public List<Comment> getAllComment();
    //查商品下的所有评论
    public List<Comment> getAllCommentByGoodId(int goodId);
}
