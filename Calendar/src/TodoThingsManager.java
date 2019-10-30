

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
	// TodoThing��ü�� ����
	private Map<String, Vector<TodoThing>> todoMap;// map�� ����� �ϳ���
	private boolean isChanged = true;
	public TodoThingsManager() {
		makeMap();
	}

	private void makeMap() {
		todoMap = new Hashtable<String, Vector<TodoThing>>();
	}

	

	
	 //���� �����Ҷ� ��� ���ϵ��� isWriiten���� ������� �������´� 

	public void addTodoThingEachDays(TodoThing tdt){
		isChanged = false;
		//�γ�¥ ������ ���̸� ���
		int dateDifference = Utils.calBetweenDates(tdt);
		
		Calendar cal = Utils.getCalendar(tdt);

		String strDate = Utils.getStringDate(cal);
		
		for(int i=0; i<dateDifference+1; i++){
			addTodoThing(strDate,tdt);
			cal.add(Calendar.DAY_OF_YEAR, 1);//�Ϸ縦 ���Ѵ�
			strDate = Utils.getStringDate(cal);
		}

	}
	
	public void addTodoThing(String fullDate,TodoThing tdt) {
		
		if(todoMap.containsKey(fullDate)){//�ش��ϴ� ��¥�� �ʿ� �����ϸ� tdt�� ���Ϳ� �߰�
			todoMap.get(fullDate).add(tdt);
		}else{//������ �ش糯¥ Ű�� ����� ����� ���Ϳ� �߰�
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
				if(todoMap.get(mapKey).get(j).equals(tdt)){//������
					index = j;
				}
			}
			todoMap.get(mapKey).remove(index);
			if(todoMap.get(mapKey).size() == 0){
				//�ʿ��� �����
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





















