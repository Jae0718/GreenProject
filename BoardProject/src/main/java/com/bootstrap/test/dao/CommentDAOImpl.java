package com.bootstrap.test.dao;

import java.util.List;


import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bootstrap.test.dto.Comment;

@Repository
public class CommentDAOImpl implements ICommentDAO{
   @Inject
   private SqlSession session;
   
   private static final String Namespace = "com.bootstrap.test.mapper.commentMapper";
   
   @Override
   public List<Comment> selectComment(int ccode) throws Exception {
      return session.selectList(Namespace + ".selectComment", ccode);
   }

   @Override
   public void insertComment(Comment comment) throws Exception {
      session.insert(Namespace + ".insertComment", comment);
   }

   @Override
   public void deleteComment(int ccode) throws Exception {
      session.delete(Namespace + ".deleteComment", ccode);
   }
   
   
}