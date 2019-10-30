package kr.ac.green;

import java.io.Serializable;


public class TodoThing implements Serializable{
	private String toDo;
	private String startDate;
	private String endDate;
	private String toDoInfo;
	
	public TodoThing (String toDo,String startDate, String endDate,String toDoInfo){
		this.toDo = toDo;
		this.endDate = endDate;
		this.startDate = startDate;
		this.toDoInfo = toDoInfo;
	}

	@Override
	public String toString() {
		return "할일 : " + toDo + 
				"시작날짜 : " + startDate + 
				"끝나는 날짜 : " + endDate +
				"할일 내용 : " + toDoInfo;
	}

}
