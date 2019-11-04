package jy.cmd;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.dao.StuDao;
import jy.dto.Student;

public class InsertCmd implements ICmd {

	@Override
	public String action(HttpServletRequest request,
			HttpServletResponse response) {
		
		StuDao dao = StuDao.getInstance();
		Connection con = dao.connect();
		String sname = request.getParameter("sname");
		String stel = request.getParameter("stel");
		int sgrade = Integer.parseInt(request.getParameter("sgrade"));
		String sclass = request.getParameter("sclass");
		
		Student stu = new Student(sname,stel,sgrade,sclass);
		dao.insert(con,stu);
		request.setAttribute("nextPage", "input");
		request.setAttribute("isRedirect", true);
		dao.disconnect(con);

		return "insert.jsp";
	}
	
}
