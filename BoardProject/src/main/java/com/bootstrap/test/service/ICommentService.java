package com.bootstrap.test.service;

import java.util.List;

import com.bootstrap.test.dto.Comment;

public interface ICommentService {
   public List<Comment> selectComment(int ccode) throws Exception;
   public void insertComment(Comment comment) throws Exception;
}