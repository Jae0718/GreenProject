package com.bootstrap.test.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bootstrap.test.dao.BoardDAOImpl;
import com.bootstrap.test.dto.Board;
import com.bootstrap.test.dto.Comment;
import com.bootstrap.test.dto.Content;
import com.bootstrap.test.pagination.PaginationDto;

@Service
public class BoardServiceImpl implements IBoardService{
   @Inject
   private BoardDAOImpl dao;
   
   @Override
   public List<String> selectBoardTitle() throws Exception {
      return dao.selectBoardTitle();
   }

   @Override
   public List<Content> selectContent(PaginationDto pDto) throws Exception {
      return dao.selectContent(pDto);
   }
   
   @Override
   public List<Content> selectContent(String btitle) throws Exception {
      return dao.selectContent(btitle);
   }

   @Override
   public int selectBoardCode(String btitle) throws Exception {
      return dao.selectBoardCode(btitle);
   }

   @Override
   public void insertContent(Content content) throws Exception {
      dao.insertContent(content);
   }

   @Override
   public int countContent(String btitle) throws Exception {
      return dao.countContent(btitle);
   }

   @Override
   public int selectBcount(int bcode) throws Exception {
      return dao.selectBcount(bcode);
   }

   @Override
   public int updateBcount(Map<String, Integer> map) throws Exception {
      return dao.updateBcount(map);
   }

   @Override
   public int getBoardListCnt(String btitle) throws Exception {
      return dao.getBoardListCnt(btitle);
   }

   @Override
   public Content viewContent(Map<String, Integer> map) throws Exception {
      return dao.viewContent(map);
   }

   @Override
   public void updateChits(int ccode) throws Exception {
      dao.updateChits(ccode);
   }

   @Override
   public List<Comment> selectComment(int bcode) throws Exception {
      return dao.selectComment(bcode);
   }

   @Override
   public List<Board> selectBoard() throws Exception {
      return dao.selectBoard();
   }

   @Override
   public void insertBoard(Map<String, String> map) throws Exception {
      dao.insertBoard(map);
   }

   @Override
   public void deleteBoard(String btitle) throws Exception {
      dao.deleteBoard(btitle);
   }
   
   
   
}