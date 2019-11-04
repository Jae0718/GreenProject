package jy.cmd;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmdFactory {

	private static Hashtable<String, ICmd> cmds;
	
	public static void init(){
		
		cmds = new Hashtable<String, ICmd>();
		//Ȩȭ������ �̵�
		cmds.put("/home.do", new MoveCmd("home"));
		//����Ʈ ����
		cmds.put("/list.do", new GetListCmd());
		//�� ���� ����
		cmds.put("/info.do", new InfoCmd());
		//�Է�ȭ�� ����
		cmds.put("/input.do", new MoveCmd("input"));
		//�����ͺ��̽��� �� �ֱ�
		cmds.put("/insert.do", new InsertCmd());
		//�˻�ȭ�� ����
		cmds.put("/search.do", new MoveCmd("search"));
		//�˻��ϱ�
		cmds.put("/find.do", new SearchCmd());
		//���� �����ϱ�
		cmds.put("/update.do", new ModifyCmd());
		//���� �����ϱ�
		cmds.put("/delete.do", new RemoveCmd());
		//cmds.put("/modify.do", new ResultCmd("modify"));
		//cmds.put("/remove.do", new ResultCmd("remove"));
		//cmds.put("/doModify.do", new ModifyCmd());
		//cmds.put("/doRemove.do", new RemoveCmd());
	}
	
	public static String bindActino(String cmd,
			HttpServletRequest request, HttpServletResponse response){
		//�ʿ��� Cmd�� �´� �������̽��� actino�޼ҵ��� ���� ��ȯ = nextpage
		
		return cmds.get(cmd).action(request, response);
	}
}
