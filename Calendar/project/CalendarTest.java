package kr.ac.green;

import java.util.Calendar;
import java.util.Scanner;

public class CalendarTest {

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Calendar cal = Calendar.getInstance();
		//getInstance 를 통해서 현재 시간을 불러온다
		int year,month,dayNum;
		
		System.out.println("년도 입력");
		year = sc.nextInt();
		
		System.out.println("월 입력");
		month = sc.nextInt();
		
		cal.set(year, month-1,1);
		
		dayNum = cal.get(Calendar.DAY_OF_WEEK);//일 ~ 월 : 1 ~ 7로 반환 /예) 화->3
		System.out.println("일\t월\t화\t수\t목\t금\t토\t");
		
		for(int i=0; i<dayNum-1; i++){//1이 시작할 위치선택
			System.out.println("\t");
		}
		
		for(int i=0; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){//해당 month의 최대 날짜 구하기
			if(dayNum %7 != 0){//7로 나누어 떨어지지 않으면
				System.out.print(i+1 + "\t");//그냥출력
			}else if(dayNum%7 == 0){//7로 나누어 떨어지면
				System.out.println(i);//한칸 아래로
			}
			dayNum++;//요일증가
			
		}
		
//		if(dayNum%7 != 1){//일요일이 아니라면 줄띄우기
//			System.out.println();
//		}
		System.out.println("-----------------------");
		

	}

}
