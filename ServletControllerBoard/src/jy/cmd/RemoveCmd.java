package jy.cmd;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.dao.StuDao;

public class RemoveCmd implements ICmd{

	@Override
	public String action(HttpServletRequest request,
			HttpServletResponse response) {
		
		StuDao dao = StuDao.getInstance();
		int sid = Integer.parseInt(request.getParameter("sid"));
		Connection con = dao.connect();
		dao.delete(con, sid);
		dao.disconnect(con);
		
		return "list.jsp";
	}
	
}
