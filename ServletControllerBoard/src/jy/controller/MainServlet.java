package jy.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.cmd.CmdFactory;

/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		CmdFactory.init();
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	private void doAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		//명령어 받기 위한 서브스트링 작업
		String str = request.getRequestURI();
		String cmd = str.substring(request.getContextPath().length());
		
		//CMD객체들의 리턴값이 order로 들어감
		String order = CmdFactory.bindActino(cmd, request, response);
		System.out.println("cmd:" + cmd);
		
		request.setAttribute("nextOrder", order);
		String nextPage = "template.jsp";
		System.out.println("order:" + order);
		
		if(request.getAttribute("isRedirect") != null){
			response.sendRedirect(nextPage);
		}else{
			request.getRequestDispatcher(nextPage).forward(request, response);
		}
		
	}
}
