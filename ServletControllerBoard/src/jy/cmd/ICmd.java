package jy.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICmd {
	//요청와 응답을 받아서 처리하고 Cmd를 반환하는 메소드
	String action(HttpServletRequest request, HttpServletResponse response);
}
