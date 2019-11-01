package com.bootstrap.test.service;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bootstrap.test.dao.CommentDAOImpl;
import com.bootstrap.test.dao.ContentDAOImpl;
import com.bootstrap.test.dto.Content;
import com.bootstrap.test.dto.UpdateContentDTO;
@Service
public class ContentServiceImpl implements IContentService {
   
   @Inject
   private ContentDAOImpl contentDao;
   @Inject
   private CommentDAOImpl commentDao;
   
   @Override
   public Content viewContent(Map<String, Integer> map) throws Exception {
      return contentDao.viewContent(map);
   }
   
   @Override
   public void insertContent(Content content) throws Exception {
      contentDao.insertContent(content);
   }

   @Override
   public void deleteContent(int bcode) throws Exception {
      int ccode = contentDao.selectContent(bcode);
      if(ccode != 0) {
         commentDao.deleteComment(ccode);
         contentDao.deleteContent(bcode);
      }
   }
   
   //이게 진짜임
   @Override
   public void deleteContentAndComment(Map<String,Integer> map) throws Exception {
      commentDao.deleteComment(map.get("ccode"));
      contentDao.deleteContent(map);
   }

   @Override
   public void updateContent(UpdateContentDTO ucd) throws Exception {
      contentDao.updateContent(ucd);
   }

   @Override
   public Content selectContentByCcode(Map<String, Integer> map) throws Exception {
      return contentDao.selectContentByCcode(map);
   }

   @Override
   public void updateChits(Map<String, Integer> map) throws Exception {
      contentDao.updateChits(map);
   }

   
   
}