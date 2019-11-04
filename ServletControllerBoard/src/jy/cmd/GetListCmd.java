package jy.cmd;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.dao.StuDao;
import jy.dto.Student;

public class GetListCmd implements ICmd {

	@Override
	public String action(HttpServletRequest request,HttpServletResponse response) {
		
		String temp = request.getParameter("pageNum");
		int pageNum = 1;
		if(temp !=null){
			pageNum = Integer.parseInt(temp);
		}
		int perPage = 3;
		
		StuDao dao = StuDao.getInstance();
		Connection con = dao.connect();
		
		int totalCount = dao.getTotalCount(con,perPage);
		Student[] list = dao.getList(con, pageNum, perPage);
		
		request.setAttribute("list", list);
		request.setAttribute("total", totalCount);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("nextOrder", "list.jsp");
		dao.disconnect(con);
		
		return "list.jsp";
	}
	
}
