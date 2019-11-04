package jy.cmd;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.dao.StuDao;

public class SearchCmd implements ICmd{

	@Override
	public String action(HttpServletRequest request,
			HttpServletResponse response) {
		
		StuDao dao = StuDao.getInstance();
		String sValue = request.getParameter("sValue");
		String option = request.getParameter("option");
		System.out.println("dd" + sValue + option);
		Connection con = dao.connect();
		request.setAttribute("list", dao.find(con,option,sValue));
		System.out.println(dao.find(con,option,sValue).toString());
		dao.disconnect(con);
		
		return "search.jsp";
	}
	

}
