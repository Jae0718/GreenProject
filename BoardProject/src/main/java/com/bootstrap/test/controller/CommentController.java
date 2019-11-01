package com.bootstrap.test.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootstrap.test.dto.Comment;
import com.bootstrap.test.dto.Person;
import com.bootstrap.test.service.CommentServiceImpl;
@Controller
public class CommentController {
   
   @Inject
   private CommentServiceImpl commentService;
   
   @RequestMapping(value="/board/addComment.do")
   @ResponseBody
   public String insertComment(HttpServletRequest request,
         @RequestParam(value="ccode") int ccode,
         @RequestParam(value="cmcontent") String cmcontent) throws Exception{
        HttpSession session = request.getSession();
        Person login = (Person)session.getAttribute("login");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm");
        String cmdate = sdf.format(new Date());
        Comment comment = new Comment(ccode, login.getPid(),  cmdate, cmcontent);
        commentService.insertComment(comment);
        request.setAttribute("login", session.getAttribute("login"));
        return "success";
    }
   
    @RequestMapping(value="/board/commentList.do", produces="application/json; charset=utf8")
    @ResponseBody
    public ResponseEntity selectCommentList(HttpServletRequest request,
          @RequestParam(value="ccode") String ccode_temp) throws Exception{
           HttpHeaders responseHeaders = new HttpHeaders();
           ArrayList<HashMap> hmlist = new ArrayList<HashMap>();
           
           // 해당 게시물 댓글
           int ccode = Integer.parseInt(ccode_temp);
           List<Comment> commentList = commentService.selectComment(ccode);
           
           if(commentList.size() > 0){
               for(int i=0; i<commentList.size(); i++){
                   HashMap hm = new HashMap();
                   hm.put("comment", commentList.get(i).getCmcontent());
                   hm.put("writer", commentList.get(i).getPid());
                   
                   hmlist.add(hm);
               }
           }
           JSONArray json = new JSONArray(hmlist);
           System.out.println(new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED).toString());
           return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
       }
}