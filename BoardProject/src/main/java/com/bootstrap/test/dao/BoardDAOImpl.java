package com.bootstrap.test.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bootstrap.test.dto.Board;
import com.bootstrap.test.dto.Comment;
import com.bootstrap.test.dto.Content;
import com.bootstrap.test.pagination.Pagination;
import com.bootstrap.test.pagination.PaginationDto;

@Repository
public class BoardDAOImpl implements IBoardDAO{
   @Inject
   private SqlSession session;
   
   private static final String Namespace = "com.bootstrap.test.mapper.boardMapper";
   
   
   @Override
   public List<Board> selectBoard() throws Exception {
      return session.selectList(Namespace + ".selectBoard");
   }
   @Override
   public List<String> selectBoardTitle() throws Exception {
      return session.selectList(Namespace + ".selectBoardTitle");
   }

   @Override
   public List<Content> selectContent(PaginationDto pDto) throws Exception {
      return session.selectList(Namespace + ".selectContent", pDto);
   }
   
   @Override
   public List<Content> selectContent(String btitle) throws Exception {
      return session.selectList(Namespace + ".selectContent", btitle);
   }

   @Override
   public int selectBoardCode(String btitle) throws Exception {
      return session.selectOne(Namespace + ".selectBoardCode", btitle);
   }

   @Override
   public void insertContent(Content content) throws Exception {
      session.insert(Namespace + ".insertContent", content);
   }

   @Override
   public int countContent(String btitle) throws Exception {
      return session.selectOne(Namespace + ".countContent",btitle);
   }

   @Override
   public int selectBcount(int bcode) throws Exception {
      return session.selectOne(Namespace + ".selectBcount", bcode);
   }

   @Override
   public int updateBcount(Map<String, Integer> map) throws Exception {
      return session.update(Namespace + ".updateBcount", map);
   }

   @Override
   public int getBoardListCnt(String btitle) throws Exception {
      return session.selectOne(Namespace + ".getBoardListCnt", btitle);
   }

   @Override
   public Content viewContent(Map<String, Integer> map) throws Exception {
      return session.selectOne(Namespace + ".viewContent", map);
   }

   @Override
   public void updateChits(int ccode) throws Exception {
      session.update(Namespace + ".updateChits", ccode);
   }

   @Override
   public List<Comment> selectComment(int bcode) throws Exception {
      return session.selectList(Namespace + ".selectComment", bcode);
   }

   @Override
   public void insertComment(Comment comment) throws Exception {
      
   }
   @Override
   public void insertBoard(Map<String, String> map) throws Exception {
      session.insert(Namespace +".insertBoard", map);
   }
   @Override
   public void deleteBoard(String btitle) throws Exception {
      session.delete(Namespace + ".deleteBoard", btitle);
   }
   
   
}