

import java.io.Serializable;


public class TodoThing implements Serializable{
	private static final long serialVersionUID = 22;

	private String toDo;
	private String startDate;
	private String endDate;
	private String toDoInfo;
	private boolean isDone;
	
	public boolean isWritten;
	public boolean isChecked;
	
	public TodoThing (String toDo,String startDate, String endDate,String toDoInfo){
		this.toDo = toDo;
		this.endDate = endDate;
		this.startDate = startDate;
		this.toDoInfo = toDoInfo;
		isDone = true;
		isWritten = true;
		isChecked = true;
	}
	
	public TodoThing(String startDate,String endDate){
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public void editTodoThing(String toDo,String startDate, String endDate,String toDoInfo){
		setToDo(toDo);
		setStartDate(startDate);
		setEndDate(endDate);
		setToDoInfo(toDoInfo);
	}

	
	public void setIsWritenTrue(){
		this.isWritten = true;
	}

	public void setIsCheckedTrue(){
		this.isChecked = true;
	}
	@Override
	public String toString() {
		return toDo + "/" + startDate + "/" + endDate;
	}

	public String getToDo() {
		return toDo;
	}

	public void setToDo(String toDo) {
		this.toDo = toDo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getToDoInfo() {
		return toDoInfo;
	}

	public void setToDoInfo(String toDoInfo) {
		this.toDoInfo = toDoInfo;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	public boolean getIsWritten() {
		return isWritten;
	}
	public void setIsWritten(boolean isWritten) {
		this.isWritten = isWritten;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	

}
