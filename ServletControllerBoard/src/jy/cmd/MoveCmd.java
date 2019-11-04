package jy.cmd;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MoveCmd implements ICmd{
	private String page;
	
	public MoveCmd(String page) {
		this.page = page;
	}
	
	@Override
	public String action(HttpServletRequest request,
			HttpServletResponse response) {
		
		return page +".jsp";
	}
	
}
