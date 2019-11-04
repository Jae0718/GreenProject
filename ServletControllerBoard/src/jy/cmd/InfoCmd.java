package jy.cmd;


import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.dao.StuDao;


public class InfoCmd implements ICmd{

	@Override
	public String action(HttpServletRequest request,HttpServletResponse response) {
		StuDao dao = StuDao.getInstance();
		String sid = request.getParameter("sid");
		
		Connection con = dao.connect();
		request.setAttribute("stu", dao.find(con,"sid",sid)[0]);
		dao.disconnect(con);
		
		return "info.jsp";
	}
	
}
