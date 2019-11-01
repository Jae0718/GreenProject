package com.bootstrap.test.service;

import java.util.List;
import java.util.Map;

import com.bootstrap.test.dto.Board;
import com.bootstrap.test.dto.Comment;
import com.bootstrap.test.dto.Content;
import com.bootstrap.test.pagination.PaginationDto;

public interface IBoardService {
   public List<Board> selectBoard() throws Exception;
   public List<String> selectBoardTitle() throws Exception;
   public List<Content> selectContent(PaginationDto pDto) throws Exception;
   public List<Content> selectContent(String btitle) throws Exception;
   public int selectBoardCode(String btitle) throws Exception;
   public void insertContent(Content content) throws Exception;
   public int countContent(String btitle) throws Exception;
   public int selectBcount(int bcode)throws Exception;
   public int updateBcount(Map<String, Integer> map) throws Exception;
   public int getBoardListCnt(String btitle) throws Exception;
   public Content viewContent(Map<String, Integer> map) throws Exception;
   public void updateChits(int ccode) throws Exception;
   public List<Comment> selectComment(int bcode) throws Exception;
   
   public void insertBoard(Map<String,String> map) throws Exception;
   public void deleteBoard(String btitle) throws Exception;
}