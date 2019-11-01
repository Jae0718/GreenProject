package com.bootstrap.test.dao;

import java.util.Map;

import com.bootstrap.test.dto.Content;
import com.bootstrap.test.dto.UpdateContentDTO;

public interface IContentDAO {
   public void insertContent(Content content) throws Exception;
   public Content viewContent(Map<String, Integer> map) throws Exception;
   
   public void deleteContent(int bcode) throws Exception;
   public int selectContent(int bcode) throws Exception;
   public Content selectContentByCcode(Map<String, Integer> map) throws Exception;
   
   public void deleteContent(Map<String,Integer>map) throws Exception;   
   public void updateContent(UpdateContentDTO ucd) throws Exception;

   public void updateChits(Map<String, Integer> map) throws Exception;
}