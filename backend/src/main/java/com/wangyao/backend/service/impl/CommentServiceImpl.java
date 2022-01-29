package com.wangyao.backend.service.impl;

import com.wangyao.backend.bean.Comment;
import com.wangyao.backend.dao.CommentDao;
import com.wangyao.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;



    @Override
    public boolean addComment(Comment comment) {
        return commentDao.insert(comment) > 0;
    }

    @Override
    public boolean updateComment(Comment comment) {
        return commentDao.update(comment) > 0;
    }

    @Override
    public boolean deleteComment(Comment comment) {
        return commentDao.delete(comment) > 0;
    }

    @Override
    public Comment getCommentById(int id) {
        return commentDao.getCommentById(id);
    }

    @Override
    public List<Comment> getAllComment() {
        return commentDao.getAllComment();
    }
    @Override
    public List<Comment> getAllCommentByGoodId(int goodId) {
        return commentDao.getAllCommentByGoodId(goodId);
    }

}
