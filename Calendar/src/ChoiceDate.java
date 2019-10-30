

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChoiceDate extends JDialog{
	static final int START = 1;
	static final int END= 2;
	
	private JLabel lbl;
	private JButton btnPre;
	private JButton btnNext;
	private String year;
	private String month;
	private Vector<JButton> btnList;
	private JPanel pnlMain;
	private JPanel pnlMain2;
	
	private Calendar cal;//날짜를 받아와서
	private Dimension btnSize;
	private int order;
	private Add add;
	private PopupEdit popupEdit;
	private int isWhat;
	
	
	public ChoiceDate(Calendar cal,PopupEdit popupEdit,int src, int order){
		this.popupEdit = popupEdit;
		this.cal = cal;
		init();
		addListener();
		setDisplay();
		setFrame();
		
		isWhat = src;

	}
	
	public ChoiceDate(Calendar cal, Add add,int src, int order){
		this.add = add;
		this.cal = cal;
		this.order = order;
		init();
		addListener();
		setDisplay();
		setFrame();
		
		isWhat = src;

	}
	private void init(){
		lbl = new JLabel(year + "년  " + (month) + "월 ");
		btnPre = new JButton("◀");
		btnNext = new JButton("▶");
		btnList = new Vector<JButton>();
		btnSize = new Dimension(20,10);
	}
	
	private void setDisplay(){
		JPanel pnlNorth = new JPanel();
		year = String.valueOf(cal.get(Calendar.YEAR));
		month = String.valueOf(cal.get(Calendar.MONTH)+1);
		
		lbl.setText(year + "년  " + (month) + "월 ");
		
		pnlNorth.add(btnPre);
		pnlNorth.add(lbl);
		pnlNorth.add(btnNext);
		
		pnlMain = new JPanel(new BorderLayout());
		
		JPanel pnlMain1 = new JPanel(new GridLayout(0,7));
		JLabel [] lblDayArr = new JLabel [7];
	     lblDayArr[0] = new JLabel("일",JLabel.CENTER);
	     lblDayArr[1] = new JLabel("월",JLabel.CENTER);
	     lblDayArr[2] = new JLabel("화",JLabel.CENTER);
	     lblDayArr[3] = new JLabel("수",JLabel.CENTER);
	     lblDayArr[4] = new JLabel("목",JLabel.CENTER);
	     lblDayArr[5] = new JLabel("금",JLabel.CENTER);
	     lblDayArr[6] = new JLabel("토",JLabel.CENTER);
	     
		for(int i=0; i<7; i++){
			pnlMain1.add(lblDayArr[i]);
		}
		////////////////////////////////////////////////////////////////////////////////////////////
		
		pnlMain2 = new JPanel(new GridLayout(0,7));
		int dayNum = cal.get(Calendar.DAY_OF_WEEK);
        for(int i=0;i<dayNum-1; i++){
        	//앞빈공간 생성
        	JPanel pnlTemp = new JPanel();
        	pnlTemp.setPreferredSize(btnSize);
        	pnlMain2.add(pnlTemp);
        }
		
		int dayMax = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for(int i=0; i<dayMax; i++){
			btnList.add(new JButton(String.valueOf(i+1)));
			pnlMain2.add(btnList.get(i));
		}
		
		addBtnListener(dayMax);
		
		Calendar tempCal = Calendar.getInstance();
        tempCal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), dayMax);
        int lastDayNum = tempCal.get(Calendar.DAY_OF_WEEK);//일~월 : 1~7반환
        for(int i=0; i<(7-lastDayNum); i++){
        	//뒤빈공간 생성
        	JPanel pnlTemp = new JPanel();
        	pnlTemp.setPreferredSize(btnSize);
        	pnlMain2.add(pnlTemp);
        }
		
		pnlMain.add(pnlMain1, BorderLayout.NORTH);
		pnlMain.add(pnlMain2, BorderLayout.CENTER);

		
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlMain, BorderLayout.CENTER);
		
	}
	

	
	private void changeNext(){
	      int year = cal.get(Calendar.YEAR);
	      int month = cal.get(Calendar.MONTH);
	      
	      if(month == 11){
	         year += 1;
	         month = 0;
	      }else{
	         month += 1;
	      }
	      cal.set(year, month, cal.get(Calendar.DAY_OF_MONTH));
	      
	     lbl.setText(year + "년  " + (month+1) + "월 ");
	   }
	   
	private void changePre(){
	      int year = cal.get(Calendar.YEAR);
	      int month = cal.get(Calendar.MONTH);
	      
	      if(month == 0){
	         year -= 1;
	         month = 11;
	      }else{
	         month -= 1;
	      }
	      cal.set(year, month, cal.get(Calendar.DAY_OF_MONTH));
	      lbl.setText(year + "년  " + (month+1) + "월 ");
	  }
	
	
	
	private void addBtnListener(int dayMax){
		
		ActionListener btnListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//각버튼의 날짜정보를 가져와서 연월(->cal)과 같이 텍스트필드에 표시
				Object src = ae.getSource();
				JButton btnTemp = (JButton)src;
				
				int year = cal.get(Calendar.YEAR);
				int date = Integer.parseInt(btnTemp.getText());
				int month = cal.get(Calendar.MONTH)+1;
				//10이하면 0붙여서 표시
				String dateString = String.valueOf(date);
				String monthString = String.valueOf(month);
				if(month < 10){
					monthString = "0" + month;
				}
				if(date<10){
					dateString = "0" + date;
				}
				
				if(order == 1){
					if(isWhat == START){
						add.setStartTextField(year, monthString, dateString );
					}else if(isWhat == END){
						add.setEndTextField(year, monthString, dateString);
					}
				}else{
					if(isWhat == START){
						popupEdit.setStartTextField(year, monthString, dateString );
					}else if(isWhat == END){
						popupEdit.setEndTextField(year, monthString, dateString);
					}
				}
				
				dispose();
			}
		};
		
		for(int i=0; i<dayMax; i++){
			btnList.get(i).addActionListener(btnListener);
		}

	
	}

	
	private void setBtn(){
		btnList.removeAllElements();//리스트비우고
		pnlMain2.removeAll();//패널을 비운다
		
		//change메소드로 cal은 넘어간 날짜임.
		int dayNum = cal.get(Calendar.DAY_OF_WEEK);
        for(int i=0;i<dayNum-1; i++){
        	//앞빈공간 생성
        	JPanel pnlTemp = new JPanel();
        	pnlTemp.setPreferredSize(btnSize);
        	pnlMain2.add(pnlTemp);
        }
		
        int dayMax = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for(int i=0; i<dayMax; i++){
			btnList.add(new JButton(String.valueOf(i+1)));
			pnlMain2.add(btnList.get(i));
		}
		
		Calendar tempCal = Calendar.getInstance();
        tempCal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), dayMax);
        int lastDayNum = tempCal.get(Calendar.DAY_OF_WEEK);//일~월 : 1~7반환
        for(int i=0; i<(7-lastDayNum); i++){
        	//뒤빈공간
        	JPanel pnlTemp = new JPanel();
        	pnlTemp.setPreferredSize(btnSize);
        	pnlMain2.add(pnlTemp);
        }
		
		pnlMain.add(pnlMain2, BorderLayout.CENTER);
		addBtnListener(dayMax);
	}
	
	
	private void addListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object src = ae.getSource();
				
				if(src == btnNext){
					//다음달로
					changeNext();
					setBtn();
				}else if(src == btnPre){
					//이전달로
					changePre();
					setBtn();
				}
			}
		};
		btnNext.addActionListener(listener);
		btnPre.addActionListener(listener);
	}
	
	
	private void setFrame(){
		setTitle("날짜선택");
		setLocation(100,200);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	

}









