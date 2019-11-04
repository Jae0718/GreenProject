package jy.cmd;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.dao.StuDao;
import jy.dto.Student;

public class ModifyCmd implements ICmd {

	@Override
	public String action(HttpServletRequest request,
			HttpServletResponse response) {
		
		StuDao dao = StuDao.getInstance();
		
		int sid = Integer.parseInt(request.getParameter("sid"));
		String sname = request.getParameter("sname");
		String stel = request.getParameter("stel");
		int sgrade = Integer.parseInt(request.getParameter("sgrade"));
		String sclass = request.getParameter("sclass");
		Student stu = new Student(sid,sname,stel,sgrade,sclass);
		
		Connection con = dao.connect();
		dao.update(con, stu);
		System.out.println(String.valueOf(sid).toString());
		System.out.println(dao.find(con, "sid", String.valueOf(sid)).toString());
		
		request.setAttribute("stu", dao.find(con, "sid", String.valueOf(sid))[0]);
		dao.disconnect(con);
		return "info.jsp";
	}
	
}
