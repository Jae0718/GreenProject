package com.bootstrap.test.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootstrap.test.dto.Person;
import com.bootstrap.test.service.BoardServiceImpl;
import com.bootstrap.test.service.PersonServiceImpl;


@Controller
public class LoginController {
   
   @Inject
   private PersonServiceImpl personService;
   @Inject
   private BoardServiceImpl boardService;
   
   @RequestMapping(value="logout")
   public String logout(HttpServletRequest request) throws Exception {
      request.getSession().removeAttribute("login");
      return "redirect:/";
   }
   
   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String main(HttpServletRequest request) throws Exception {
      request.getSession().removeAttribute("login");
      return "login.jsp";
   }
   
   @RequestMapping("goLogin.do")
   public String goLogin(@RequestParam(value="pid") String pid,
         @RequestParam(value="ppw") String ppw,
         HttpServletRequest request, Model model) throws Exception{
      Person person = personService.selectPerson(pid);
      
      if(person == null) {
         model.addAttribute("msg", "존재하지 않는 아이디");
         return "login.jsp";
      }else {
         if(person.getPpw().equals(ppw)) {
            //model.addAttribute("person", person);
            HttpSession httpSession = request.getSession();
            httpSession.removeAttribute("login");
            httpSession.setAttribute("login", person);
            return "redirect:main";
         }else {
            model.addAttribute("msg", "패스워드 불일치");
            return "login.jsp";
         }
      }
   }
   
   @RequestMapping("join")
   public String join() {
      return "join.jsp";
   }
   
   @RequestMapping("checkId.do")
   public String checkId(String pid, Model model) throws Exception {
      Person person = (Person)personService.selectPerson(pid);
      if(person == null) {
         model.addAttribute("msg", pid + "로 가입 가능");
         model.addAttribute("checkedId", pid);
      }else {
         model.addAttribute("msg", pid + ": 이미 존재하는 아이디 ");
      }
      return "join.jsp";
   }
   
   @RequestMapping("goJoin.do")
   public String goJoin(String pid, String ppw, String pname, String pemail) throws Exception{
      Person person = new Person(pid, ppw, pname, pemail);
      System.out.println("갑자기?");
      personService.insertPerson(person);
      return "redirect:/";
   }
   
   @RequestMapping("nonMember")
   public String nonMember(HttpServletRequest request) {
      return "redirect:main";
   }
   
   @RequestMapping("main")
   public String goMain(HttpServletRequest request,HttpSession session,Model model) throws Exception {
      //로그인 정보 유지
      request.setAttribute("login", session.getAttribute("login"));
      //게시판 정보 가져가기
      int bcount = boardService.selectBoardTitle().size();
      model.addAttribute("bcount", bcount);

      return "main.jsp";
   }
   
}