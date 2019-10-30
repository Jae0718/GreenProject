

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.swing.event.DocumentListener;

public class TodoThingsManager implements Serializable{
	// TodoThing객체를 관리
	private Map<String, Vector<TodoThing>> todoMap;// map은 사람당 하나씩
	private boolean isChanged = true;
	public TodoThingsManager() {
		makeMap();
	}

	private void makeMap() {
		todoMap = new Hashtable<String, Vector<TodoThing>>();
	}

	

	
	 //달을 변경할때 모든 할일들의 isWriiten값을 원래대로 돌려놓는다 

	public void addTodoThingEachDays(TodoThing tdt){
		isChanged = false;
		//두날짜 사이의 차이를 계산
		int dateDifference = Utils.calBetweenDates(tdt);
		
		Calendar cal = Utils.getCalendar(tdt);

		String strDate = Utils.getStringDate(cal);
		
		for(int i=0; i<dateDifference+1; i++){
			addTodoThing(strDate,tdt);
			cal.add(Calendar.DAY_OF_YEAR, 1);//하루를 더한다
			strDate = Utils.getStringDate(cal);
		}

	}
	
	public void addTodoThing(String fullDate,TodoThing tdt) {
		
		if(todoMap.containsKey(fullDate)){//해당하는 날짜가 맵에 존재하면 tdt을 벡터에 추가
			todoMap.get(fullDate).add(tdt);
		}else{//없으면 해당날짜 키를 만들고 밸류로 벡터에 추가
			Vector<TodoThing> tdtVector = new Vector<TodoThing>();
			todoMap.put(fullDate,tdtVector);
			todoMap.get(fullDate).add(tdt);
		}
	}
	
	public void deleteTodoThing(TodoThing tdt, int index){
		isChanged = false;
		
		int size = Utils.calBetweenDates(tdt);
		Calendar mapCal = Utils.getCalendar(tdt);
		for(int i= 0; i<size+1; i++){
			String mapKey = Utils.getStringDate(mapCal);
			for(int j=0; j<todoMap.get(mapKey).size(); j++){
				if(todoMap.get(mapKey).get(j).equals(tdt)){//같으면
					index = j;
				}
			}
			todoMap.get(mapKey).remove(index);
			if(todoMap.get(mapKey).size() == 0){
				//맵에서 지우기
				todoMap.remove(mapKey);
			}
			mapCal.add(Calendar.DAY_OF_MONTH,1);
		}
	}



	public Map<String, Vector<TodoThing>> getTodoMap() {
		return todoMap;
	}

	public void setTodoMap(Map<String, Vector<TodoThing>> todoMap) {
		this.todoMap = todoMap;
	}

	public boolean isChanged() {
		return isChanged;
	}

	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}
	
	
}





















