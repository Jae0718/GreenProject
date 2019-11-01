package com.bootstrap.test.dao;

import java.util.List;

import com.bootstrap.test.dto.Comment;

public interface ICommentDAO {
   public List<Comment> selectComment(int ccode) throws Exception;
   public void insertComment(Comment comment) throws Exception;
   
   public void deleteComment(int ccode) throws Exception;
}