package kr.ac.green;

import java.util.Calendar;
import java.util.Scanner;

public class CalendarTest {

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Calendar cal = Calendar.getInstance();
		//getInstance �� ���ؼ� ���� �ð��� �ҷ��´�
		int year,month,dayNum;
		
		System.out.println("�⵵ �Է�");
		year = sc.nextInt();
		
		System.out.println("�� �Է�");
		month = sc.nextInt();
		
		cal.set(year, month-1,1);
		
		dayNum = cal.get(Calendar.DAY_OF_WEEK);//�� ~ �� : 1 ~ 7�� ��ȯ /��) ȭ->3
		System.out.println("��\t��\tȭ\t��\t��\t��\t��\t");
		
		for(int i=0; i<dayNum-1; i++){//1�� ������ ��ġ����
			System.out.println("\t");
		}
		
		for(int i=0; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){//�ش� month�� �ִ� ��¥ ���ϱ�
			if(dayNum %7 != 0){//7�� ������ �������� ������
				System.out.print(i+1 + "\t");//�׳����
			}else if(dayNum%7 == 0){//7�� ������ ��������
				System.out.println(i);//��ĭ �Ʒ���
			}
			dayNum++;//��������
			
		}
		
//		if(dayNum%7 != 1){//�Ͽ����� �ƴ϶�� �ٶ���
//			System.out.println();
//		}
		System.out.println("-----------------------");
		

	}

}
