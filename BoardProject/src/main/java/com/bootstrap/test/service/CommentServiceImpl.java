package com.bootstrap.test.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bootstrap.test.dao.CommentDAOImpl;
import com.bootstrap.test.dto.Comment;

@Service
public class CommentServiceImpl implements ICommentService{
   @Inject
   private CommentDAOImpl dao;
   
   @Override
   public List<Comment> selectComment(int ccode) throws Exception {
      return dao.selectComment(ccode);
   }

   @Override
   public void insertComment(Comment comment) throws Exception {
      dao.insertComment(comment);
   }
}