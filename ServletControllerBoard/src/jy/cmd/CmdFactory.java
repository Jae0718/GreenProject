package jy.cmd;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmdFactory {

	private static Hashtable<String, ICmd> cmds;
	
	public static void init(){
		
		cmds = new Hashtable<String, ICmd>();
		//홈화면으로 이동
		cmds.put("/home.do", new MoveCmd("home"));
		//리스트 보기
		cmds.put("/list.do", new GetListCmd());
		//상세 정보 보기
		cmds.put("/info.do", new InfoCmd());
		//입력화면 띄우기
		cmds.put("/input.do", new MoveCmd("input"));
		//데이터베이스에 값 넣기
		cmds.put("/insert.do", new InsertCmd());
		//검색화면 띄우기
		cmds.put("/search.do", new MoveCmd("search"));
		//검색하기
		cmds.put("/find.do", new SearchCmd());
		//정보 수정하기
		cmds.put("/update.do", new ModifyCmd());
		//정보 삭제하기
		cmds.put("/delete.do", new RemoveCmd());
		//cmds.put("/modify.do", new ResultCmd("modify"));
		//cmds.put("/remove.do", new ResultCmd("remove"));
		//cmds.put("/doModify.do", new ModifyCmd());
		//cmds.put("/doRemove.do", new RemoveCmd());
	}
	
	public static String bindActino(String cmd,
			HttpServletRequest request, HttpServletResponse response){
		//맵에서 Cmd에 맞는 인터페이스의 actino메소드의 값을 반환 = nextpage
		
		return cmds.get(cmd).action(request, response);
	}
}
