

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.WriteAbortedException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Utils {

	public static void writeTodo(Map<String, Vector<TodoThing>> map, String fileName) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(fileName,false);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(map);
			oos.flush();
			oos.reset();
		} catch(NotSerializableException e){
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			closeAll(oos, fos);
		}
	}
	
	public static Map<String, Vector<TodoThing>> readTodo(String fileName) {
		Map<String, Vector<TodoThing>> obj = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			obj = (Map<String,Vector<TodoThing>>)ois.readObject();
		}catch(WriteAbortedException e){
			e.printStackTrace();
		} catch(NotSerializableException e){
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			closeAll(ois, fis);
		}
		return obj;
	}
	
	public static void closeAll(Closeable... c) {
		for(Closeable temp : c) {
			try {
				temp.close();
			} catch(Exception e){}
		}
	}
	
	public static String getStringDate(Calendar cal){
		int yearTemp = cal.get(Calendar.YEAR);
		int monthTemp = cal.get(Calendar.MONTH);
		int dateTemp = cal.get(Calendar.DATE);
		
		String monthTempString = String.valueOf(monthTemp);
		String dateTempString = String.valueOf(dateTemp);
		
		if(monthTemp < 10 && monthTemp != 0){
			monthTempString = "0" + monthTemp;
		}else if(monthTemp == 0){
			monthTempString = "12";
			yearTemp --;
		}
		if(dateTemp < 10){
			dateTempString = "0" + dateTemp;
		}
		String yearTempString = String.valueOf(yearTemp);
		String strDate = yearTempString + "-" + monthTempString + "-" + dateTempString;
		
		return strDate;
	}
	
	public static int calBetweenDates(TodoThing tdt) {
		String startDateString = tdt.getStartDate();
		String endDateString = tdt.getEndDate();

		int result = 0;
		try {
			// String을 Date로 만든다
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = format.parse(startDateString);
			Date endDate = format.parse(endDateString);

			long calDate = startDate.getTime() - endDate.  getTime();
			long calDateDays = calDate / (24 * 60 * 60 * 1000);

			result = (int) Math.abs(calDateDays);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Calendar getCalendar(TodoThing tdt){
		String startDate = tdt.getStartDate();
		//그 사이의 날짜 정보구하기
		String year = startDate.substring(0,4);
		String month = startDate.substring(5,7);
		String date = startDate.substring(8,10);
		//String sumDate = year + month + date;
		
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month));
		cal.set(Calendar.DATE, Integer.parseInt(date));
		
		return cal;
	}
	
	public static Calendar getCalendarUseString(String inputDate){

		String year = inputDate.substring(0,4);
		String month = inputDate.substring(5,7);
		String date = inputDate.substring(8,10);
		//String sumDate = year + month + date;
		
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month));
		cal.set(Calendar.DATE, Integer.parseInt(date));
		
		return cal;
	}
	
	
	
}
