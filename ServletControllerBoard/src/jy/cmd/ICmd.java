package jy.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICmd {
	//��û�� ������ �޾Ƽ� ó���ϰ� Cmd�� ��ȯ�ϴ� �޼ҵ�
	String action(HttpServletRequest request, HttpServletResponse response);
}
