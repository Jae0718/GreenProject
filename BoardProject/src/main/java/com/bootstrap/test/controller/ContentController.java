package com.bootstrap.test.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootstrap.test.dto.Content;
import com.bootstrap.test.dto.Person;
import com.bootstrap.test.dto.UpdateContentDTO;
import com.bootstrap.test.service.BoardServiceImpl;
import com.bootstrap.test.service.ContentServiceImpl;

@Controller
public class ContentController {

   @Inject
   private ContentServiceImpl contentService;
   @Inject
   private BoardServiceImpl boardService;
   
   @RequestMapping(value="/board/write/insertContent")
   public String insertContent(HttpServletRequest request,HttpSession session, Model model,
         @RequestParam(value="ctitle") String ctitle,
         @RequestParam(value="selectedBoard") String btitle,
         @RequestParam(value="ccontent") String ccontent) throws Exception {
      request.setAttribute("login", session.getAttribute("login"));
      
      int bcode = boardService.selectBoardCode(btitle);
      //�۹�ȣ�� �Խ����� �۰��� + 1�� ����
      int ccode = boardService.selectBcount(bcode) + 1;
      Map<String, Integer> map = new HashMap<String, Integer>();
      map.put("bcode", bcode);
      map.put("ccode", ccode);
      boardService.updateBcount(map);
      //�α��� ���� �����ͼ� �ۼ��� ���� ������
      Person person = (Person)request.getSession().getAttribute("login");
      int pcode = person.getPcode();
      String pid = person.getPid();
      Content content = new Content(ccode,bcode,pcode, pid, ctitle, ccontent);
      contentService.insertContent(content);
      //���������� ǥ�ø� ���ؼ� 
      request.setAttribute("page", "view");
      model.addAttribute("boardTitle", btitle);
      //List<Content> contentList = service.selectContent(btitle);
      //model.addAttribute("contentList", contentList);
      model.addAttribute("content", content);
      
      return "main.jsp";
   }
   
   @RequestMapping(value="/board/viewBoard")
   public String viewContent(HttpServletRequest request, HttpSession session, Model model,
         @RequestParam(value="ccode") int ccode,
         @RequestParam(value="boardTitle") String btitle) throws Exception {
      request.setAttribute("login", session.getAttribute("login"));
      //btitle�� ccode�� �޾Ƽ� contentList���� select�ؼ� Content��ü�� ������
      int bcode = boardService.selectBoardCode(btitle);
      Map<String, Integer>map2 = new HashMap<String, Integer>();
      map2.put("ccode", ccode);
      map2.put("bcode", bcode);
      Content content = contentService.viewContent(map2);
      
      Map<String, Integer>map1 = new HashMap<String, Integer>();
      int updateChits = content.getChits()+1;
      map1.put("ccode", ccode);
      map1.put("chits", updateChits);
      contentService.updateChits(map1);
      content.setChits(updateChits);
      
      
      model.addAttribute("content", content);
      //������������ ��߰�
      model.addAttribute("page", "view");
      model.addAttribute("boardTitle", btitle);
      return "main.jsp";
   }
   
   @RequestMapping(value="/board/deleteContent")
   public String deleteContent(HttpServletRequest request,Model model,
         @RequestParam(value="ccode") String ccode,
         @RequestParam(value="boardTitle") String btitle) throws Exception{
      Map<String,Integer> map = new HashMap<String, Integer>();
      map.put("ccode", Integer.parseInt(ccode));
      map.put("bcode",boardService.selectBoardCode(btitle));
      contentService.deleteContentAndComment(map);
      model.addAttribute("boardTitle", btitle);
      return "redirect:/board/moveBoard";
   }
   
   @RequestMapping(value="/board/updateContent")
   public String updateContent(Model model,
         @RequestParam(value="ccode")String ccode_temp,
         @RequestParam(value="ccontent")String ccontent,
         @RequestParam(value="boardTitle")String btitle
         ) throws Exception{
      //������ �������� �ٷ� ǥ�� 
      System.out.println("�ɴϱ�?");
      int ccode = Integer.parseInt(ccode_temp);
      int bcode = boardService.selectBoardCode(btitle);
      
      UpdateContentDTO ucd = new UpdateContentDTO(bcode, ccode, ccontent);
      contentService.updateContent(ucd);
      Map<String, Integer> map = new HashMap<String, Integer>();
      map.put("bcode", bcode);
      map.put("ccode", ccode);
      Content content = contentService.selectContentByCcode(map);
      model.addAttribute("content", content);
      model.addAttribute("page", "view");
      return "main.jsp";
   }
   
}