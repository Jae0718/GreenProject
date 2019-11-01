package com.bootstrap.test.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootstrap.test.dto.Person;
import com.bootstrap.test.service.PersonServiceImpl;

@Controller
public class PersonController {
   @Inject PersonServiceImpl personService;
   
   @RequestMapping(value="viewMyInfo")
   public String viewMyInfo(Model model, HttpServletRequest request) {
      request.setAttribute("login", request.getSession().getAttribute("login"));
      model.addAttribute("page", "myInfo");
      return "main.jsp";
   }
   
   @RequestMapping(value="updateInfo")
   public String updateInfo(Model model,HttpServletRequest request,
         @RequestParam(value="pid") String pid,
         @RequestParam(value="pname") String pname,
         @RequestParam(value="pemail") String pemail) throws Exception {
      Person person = new Person(pid,pname, pemail);
      personService.updateInfo(person);
      
      HttpSession session = request.getSession();
      Person login = personService.selectPerson(pid);
      session.removeAttribute("login");
      session.setAttribute("login", login);
      model.addAttribute("login", login);
      model.addAttribute("page", "myInfo");
      return "main.jsp";
   }
   
   @RequestMapping(value="updatePw")
   public String updateInfo(Model model,HttpServletRequest request,
         @RequestParam(value="pid") String pid,
         @RequestParam(value="new_ppw") String ppw
         ) throws Exception {
      Person person = new Person(pid,ppw);
      personService.updatePw(person);
      
      HttpSession session = request.getSession();
      Person login = personService.selectPerson(pid);
      session.removeAttribute("login");
      session.setAttribute("login", login);
      model.addAttribute("login", login);
      model.addAttribute("page", "myInfo");
      return "main.jsp";
   }
   
   @RequestMapping(value="deletePerson")
   public String deletePerson(Model model, HttpServletRequest request,
         @RequestParam(value="pid") String pid) throws Exception {
      request.setAttribute("login", request.getSession().getAttribute("login"));
      personService.deletePerson(pid);
      return "redirect:/";
   }
   
}