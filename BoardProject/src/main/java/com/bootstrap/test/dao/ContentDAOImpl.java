package com.bootstrap.test.dao;

import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bootstrap.test.dto.Content;
import com.bootstrap.test.dto.UpdateContentDTO;
@Repository
public class ContentDAOImpl implements IContentDAO {
   @Inject
   private SqlSession session;
   
   private static final String Namespace = "com.bootstrap.test.mapper.contentMapper";
                                 
   @Override
   public void insertContent(Content content) throws Exception {
      session.insert(Namespace + ".insertContent", content);
   }
   
   @Override
   public Content viewContent(Map<String, Integer> map) throws Exception {
      return session.selectOne(Namespace + ".viewContent", map);
   }

   @Override
   public void deleteContent(int bcode) throws Exception {
      session.delete(Namespace + ".deleteContent", bcode);
   }

   @Override
   public int selectContent(int bcode) throws Exception {
      if(session.selectOne(Namespace + ".selectContent", bcode) == null) {
         return 0;
      }else {
         return session.selectOne(Namespace + ".selectContent", bcode);
      }
   }

   @Override
   public void deleteContent(Map<String,Integer>map) throws Exception{
      session.delete(Namespace + ".deleteContent", map);
   }

   @Override
   public void updateContent(UpdateContentDTO ucd) throws Exception {
      session.update(Namespace + ".updateContent", ucd);
   }

   @Override
   public Content selectContentByCcode(Map<String, Integer> map) throws Exception {
      return session.selectOne(Namespace + ".selectContentByCcode", map);
   }

   @Override
   public void updateChits(Map<String, Integer> map) throws Exception {
      session.update(Namespace + ".updateChits", map);
   }
   
   
   
}