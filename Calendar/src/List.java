
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class List extends JFrame {
	
	private Dimension lblSize1 = new Dimension(130, 18);
	private Dimension lblSize2 = new Dimension(280, 18);
	private Dimension lblSize3 = new Dimension(70, 18);
	
	private JLabel lblTitle;
	private JTextField tfStartDate;
	private JTextField tfEndDate;
	private JLabel lblCenter;
	private JButton btnSearch;
	private JLabel lblDate;
	private JLabel lblWork;
	private JLabel lblDday;
	private JPanel pnlContent;
	private Vector<ListPanel> pnlVector;
	
	
	private TodoThingsManager tdtManager;
	
	public List(TodoThingsManager tdtManager) {
		this.tdtManager = tdtManager;
		init();
		setDisplay();
		addListener();
		showFrame();
	}
	
	private void init() {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}catch(Exception e){

			}
		lblTitle = new JLabel("Event List");
		lblTitle.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
		lblTitle.setForeground(Color.GRAY);
		tfStartDate = new JTextField("ex)20190521",10);
		tfStartDate.setForeground(Color.GRAY);
		tfEndDate = new JTextField("ex)20190531",10);
		tfEndDate.setForeground(Color.GRAY);
		lblCenter = new JLabel("~");
		btnSearch = new JButton("조회");
		lblDate = new JLabel("시작일 ~ 종료일",JLabel.CENTER);
		lblDate.setFont(new Font("굴림", Font.BOLD, 15));
		lblDate.setForeground(Color.DARK_GRAY);
		lblWork = new JLabel("할 일",JLabel.CENTER);
		lblWork.setFont(new Font("굴림", Font.BOLD, 15));
		lblWork.setForeground(Color.DARK_GRAY);
		lblDday = new JLabel("D-day",JLabel.CENTER);
		lblDday.setFont(new Font("굴림", Font.BOLD, 15));
		lblDday.setForeground(Color.DARK_GRAY);
		
		pnlVector = new Vector<ListPanel>();
		
		lblDate.setPreferredSize(lblSize1);
		lblWork.setPreferredSize(lblSize2);
		lblDday.setPreferredSize(lblSize3);
	}

	private void setDisplay() {
		JPanel pnlNorth = new JPanel();
		pnlNorth.add(lblTitle);
		JPanel pnlMain = new JPanel();
		JPanel pnlCenter = new JPanel(new BorderLayout());
		
		JPanel pnlInfo = new JPanel();
		pnlInfo.setBorder(new TitledBorder(new LineBorder(Color.GRAY,2), "기간입력"));
		JPanel pnlInfo1 = new JPanel();
		pnlInfo1.add(tfStartDate);		
		JPanel pnlInfo2 = new JPanel();
		pnlInfo2.add(lblCenter);
		JPanel pnlInfo3 = new JPanel();
		pnlInfo3.add(tfEndDate);
		
		JPanel pnlBtn = new JPanel();
		pnlBtn.add(btnSearch);
		
		pnlInfo.add(pnlInfo1);
		pnlInfo.add(pnlInfo2);
		pnlMain.add(pnlInfo);
		pnlMain.add(pnlBtn);
		pnlInfo.add(pnlInfo3);

		pnlCenter.add(pnlMain);

		
		pnlContent = new JPanel(new GridLayout(0,1));
		pnlContent.setBorder(new LineBorder(Color.GRAY,1));
		JPanel pnlTitle = new JPanel(new BorderLayout());
		JPanel pnlTitle1 = new JPanel();
		JPanel pnlTitle2 = new JPanel();
		pnlTitle1.add(lblDate);
		pnlTitle1.add(lblWork);
		pnlTitle2.add(lblDday);
		
		
		pnlTitle.add(pnlTitle1, BorderLayout.CENTER);
		pnlTitle.add(pnlTitle2, BorderLayout.EAST);
		pnlContent.add(pnlTitle);
		

		for(int i=0; i<4; i++) {
			pnlVector.add(new ListPanel());
			pnlContent.add(pnlVector.get(i).getPnlMain());
		}
		
		JPanel pnlSouth = new JPanel(new BorderLayout());
		pnlSouth.add(pnlContent, BorderLayout.CENTER);
		
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

	}
	
	private String changeForm(String input){
		String year = input.substring(0,4);
		String month = input.substring(4,6);
		String date = input.substring(6,8);
		
		String fulldate = year + "-" + month +"-" + date;
		
		return fulldate;
	}
	
	private void searchTodoThing(String startDate, String endDate, int order){
		TodoThing tdtTemp = new TodoThing(startDate,endDate);
		//inputDate의 기간내에 시작 or 진행중인 일정을 보여준다 (상태도 보여주기?)
		//map가져와서 inputDate를 키값으로 검색
		Map<String,Vector<TodoThing>>todoMap = tdtManager.getTodoMap();
		//입력받은 시작날짜와 종료날짜 사이에 있는 날짜들의 모든 할일을 표시한다 -> 표시한놈은 isChecked false
		int dayDifference = Utils.calBetweenDates(tdtTemp);
		
		Calendar calTemp = Utils.getCalendar(tdtTemp);//시작날짜로 캘린더를 만듬
		int count = 0;
		int index = 0;
		for(int i=0; i<dayDifference+1; i++){//날짜 차이만큼 돌면서
			//각 키값에 있는 할일을 불러온다
			String calTempString = Utils.getStringDate(calTemp);
			Vector<TodoThing> vecTdt = todoMap.get(calTempString);
			//벡터를 가져와서 안에 들어있는 TodoThing의 정보를 
			//List의 textFiled에 기록(클래스의 메소드로)한다
			//유틸즈를 통해서 startDate의 정보를 담고있는 캘린더 객체 생성
			//넘기는거 가능
			
			if(vecTdt != null){
				for(int j=0; j<vecTdt.size(); j++){//벡터 사이즈만큼 돌면서
					TodoThing tdtSearchTemp = vecTdt.get(j);//하나씩 꺼내서
					int dDay = calDday(tdtSearchTemp.getStartDate());
					if(order == 1){
						if(tdtSearchTemp.isChecked){
							if(index>3){
								pnlVector.add(new ListPanel());
								pnlContent.add(pnlVector.get(i).getPnlMain());
							}
							pnlVector.get(index).setTextFiled(
									tdtSearchTemp.getStartDate(),
									tdtSearchTemp.getEndDate(), 
									tdtSearchTemp.getToDo(),
									dDay);
							count ++;
							index++;
							tdtSearchTemp.setChecked(false);
						}
					}else{//초기화하는거
						index++;
						tdtSearchTemp.setChecked(true);
					}
					
					
					
					
				}
			}
			calTemp.add(Calendar.DAY_OF_YEAR,1);

		}
		
	}
	private int calDday(String startDate){
		int result = 0;
		try{
		Calendar todayCal = Calendar.getInstance();
		int yearTemp = todayCal.get(Calendar.YEAR);
		int monthTemp = todayCal.get(Calendar.MONTH)+1;
		int dateTemp = todayCal.get(Calendar.DATE);
		todayCal.set(yearTemp,monthTemp,dateTemp);
		
		
		Calendar goalCal = Utils.getCalendarUseString(startDate);
		
		String todayString =  Utils.getStringDate(todayCal);
		String goalString = Utils.getStringDate(goalCal);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = format.parse(todayString);
		Date goal = format.parse(goalString);
		
		long calDate = today.getTime() - goal.getTime();
		long calDateDays = calDate / (24*60*60*1000);
		
		result = (int)calDateDays;
		}catch(ParseException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	private void addListener() {
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				String startDate = tfStartDate.getText();
				String endDate = tfEndDate.getText();
				searchTodoThing(startDate,endDate,2);
				searchTodoThing(startDate,endDate,1);
				
				
			}
		});
		
		MouseListener mListener = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				//텍스트필드에 마우스 클릭하면 예시 문장이 사라짐
				Object src = me.getSource();
				if(src == tfStartDate){
					tfStartDate.setText("");
					tfStartDate.setForeground(Color.BLACK);
				}else if(src == tfEndDate){
					tfEndDate.setText("");
					tfEndDate.setForeground(Color.BLACK);
				}
			}
			
		};
		tfStartDate.addMouseListener(mListener);
		tfEndDate.addMouseListener(mListener);
		
		FocusListener fListener = new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent fe) {
				//포커스를 잃으면 형식을 바꾼다.
				Object src = fe.getSource();
				if(src == tfStartDate){
					if(tfStartDate.getText().length() != 0){
						String strTemp = changeForm(tfStartDate.getText());	// setText할 예정인 String.
						try {
							Calendar calStart = Utils.getCalendarUseString(strTemp);	// startDate의 cal.
							Calendar calEnd = Utils.getCalendarUseString(tfEndDate.getText());	// (tf에 입력되어있는) endDate의 cal
							
							if (calStart.compareTo(calEnd) > 0) {	// cal끼리 compareTo, 시작일이 종료일보다 크면!
								JOptionPane.showMessageDialog(List.this, "시작일이 종료일보다 나중입니다!", "에러", JOptionPane.ERROR_MESSAGE);
								tfStartDate.setText("");
							} else {	// 정상적일 경우.
								tfStartDate.setText(strTemp);	// setText.
							}
						} catch (Exception ex) {	// 예외 >> tfEndDate에 올바른 값이 입력되지 않았을 경우에 발생함.
							ex.printStackTrace();
							tfStartDate.setText(strTemp);	// compareTo 과정 생략하고 setText.
						}
					}
					
				} else if(src == tfEndDate) {
					if(tfEndDate.getText().length() != 0){
						String strTemp = changeForm(tfEndDate.getText());	// setText할 예정인 String.
						try {
							Calendar calEnd = Utils.getCalendarUseString(strTemp);	// endDate의 cal.
							Calendar calStart = Utils.getCalendarUseString(tfStartDate.getText());	// (tf에 입력되어있는) startDate의 cal.
							
							if (calEnd.compareTo(calStart) < 0) {	// cal끼리 compareTo, 종료일이 시작일보다 작으면!
								JOptionPane.showMessageDialog(List.this, "종료일이 시작일보다 먼저입니다!", "에러", JOptionPane.ERROR_MESSAGE);
								tfEndDate.setText("");
							} else {	// 정상적일 경우.
								tfEndDate.setText(strTemp);	// setText.
							}
						} catch (Exception ex) {	// 예외 >> tfStartDate에 올바른 값이 입력되지 않았을 경우에 발생함.
							ex.printStackTrace();
							tfEndDate.setText(strTemp);	// compareTo 과정 생략하고 setText.
						}						
					}
				}
			}
		};
		
		tfStartDate.addFocusListener(fListener);
		tfEndDate.addFocusListener(fListener);
	}
	
	private void showFrame() {
		setTitle("목록");
		pack();
		setLocation(500,200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	
}
