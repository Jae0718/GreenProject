package com.bootstrap.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootstrap.test.dto.Board;
import com.bootstrap.test.dto.Content;
import com.bootstrap.test.pagination.Pagination;
import com.bootstrap.test.pagination.PaginationDto;
import com.bootstrap.test.service.BoardServiceImpl;

@Controller
public class BoardController {
   @Inject
   private BoardServiceImpl boardService;

   
   @RequestMapping(value="board")
   public String board(Model model,HttpServletRequest request,HttpSession session) throws Exception {
      request.setAttribute("login", session.getAttribute("login"));
      
      List<Board> boardList = boardService.selectBoard();
      model.addAttribute("boardList", boardList);
      model.addAttribute("page", "board");
      return "main.jsp";
   }
   
   @RequestMapping(value="board/moveBoard")
   public String moveBoard(HttpServletRequest request, HttpSession session, Model model,
         @RequestParam(value="destnPage", defaultValue = "1") int destnPage,
         @RequestParam(value="boardTitle")String boardTitle) throws Exception {
      //로그인정보
      request.setAttribute("login", session.getAttribute("login"));
      //페이징
      request.setAttribute("destnPage", destnPage);
      int listCnt = boardService.countContent(boardTitle);
      //페이징하는 객체 생성
      Pagination pagination = new Pagination(destnPage, listCnt);
      //메소드 pageInfo를 이용해서 객체에 정보를 셋팅
      System.out.println(pagination.toString());
      model.addAttribute("pagination", pagination);
      //해당 객체에서 정보를 뽑아서 쿼리문실행 -> contentList를 받아야함
      //contentList는 startList ~ startList+listsize만큼 받아온다
      PaginationDto pDto = new PaginationDto(pagination.getStartList(), pagination.getListSize(), boardTitle);
      List<Content> contentList = boardService.selectContent(pDto);
      System.out.println(pDto.toString());
      System.out.println("사이즈" + contentList.size());
      System.out.println(contentList.toString());
      model.addAttribute("contentList", contentList);
      //다음페이지에서 보드가 뜨게
      model.addAttribute("page", "board");
      model.addAttribute("boardTitle", boardTitle);
      
      return "main.jsp";
   }
   
   @RequestMapping("board/write")
   public String write(HttpServletRequest request,HttpSession session, Model model) throws Exception {
      request.setAttribute("login", session.getAttribute("login"));
      
      String page = request.getParameter("page");
      request.setAttribute("page", page);
      List<String> boardList = boardService.selectBoardTitle();
      model.addAttribute("boardList", boardList);
      
      return "main.jsp";
   }
   
   @RequestMapping("/board/editBoard")
   public String boardModify(Model model,HttpServletRequest request) throws Exception{
      request.setAttribute("login", request.getSession().getAttribute("login"));
      List<String> boardList = boardService.selectBoardTitle();
      model.addAttribute("boardList", boardList);
      model.addAttribute("page", "editBoard");
      return "main.jsp";
   }
   
   @RequestMapping(value="/board/deleteBoard.do")
   @ResponseBody
   public String deleteBoard(HttpServletRequest request,
         @RequestParam(value="btitle")String btitle) throws Exception {
      boardService.deleteBoard(btitle);
      return "success";
   }
   
   @RequestMapping(value="/board/insertBoard.do")
   @ResponseBody
   public String insertBoard(HttpServletRequest request,
         @RequestParam(value="addTitle") String addTitle,
         @RequestParam(value="addInfo") String addInfo) throws Exception {
      System.out.println(addTitle + addInfo);
      Map<String,String> map = new HashMap<String, String>();
      map.put("btitle", addTitle);
      map.put("binfo", addInfo);
      boardService.insertBoard(map);
      return "success";
   }
   
   @RequestMapping(value="/board/boardList.do", produces="application/json; charset=utf8")
    @ResponseBody
    public ResponseEntity selectBoardList_ajax(HttpServletRequest request) throws Exception{
           HttpHeaders responseHeaders = new HttpHeaders();
           ArrayList<HashMap> hmlist = new ArrayList<HashMap>();
           
           List<Board> boardList = boardService.selectBoard();
           
           if(boardList.size() > 0){
               for(int i=0; i<boardList.size(); i++){
                   HashMap hm = new HashMap();  
                   hm.put("title", boardList.get(i).getBtitle());
                   hm.put("info", boardList.get(i).getBinfo());
                   hmlist.add(hm);
               }
           }
           JSONArray json = new JSONArray(hmlist);
           request.setAttribute("login", request.getSession().getAttribute("login"));
           return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
       }
}