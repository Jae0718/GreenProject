

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class Add extends JDialog {
	static final int START = 1;
	static final int END= 2;
	
	private JTextField tfWork;
	private JTextField tfStartDate;
	private JTextField tfEndDate;
	private JLabel lblCenter;
	private JTextArea taContent;
	private JButton btnSave;
	private JButton btnCancel;
	private Calendar cal;
	private Calendar todayCal;
	private TodoThingsManager tdtManager;
	private CalendarMain cdMain;
	private Popup popup;
	private int index;
	
	
	public Add(Calendar cal, TodoThingsManager tdtManager, CalendarMain cdMain, Popup popup, int index) {
		this.cal = cal;
		this.tdtManager = tdtManager;
		this.cdMain = cdMain;
		this.popup = popup;
		this.index = index;
		init();
		setDisplay();
		addListener();
		showFrame();
	}
	
	public Add(Calendar cal, TodoThingsManager tdtManager, CalendarMain cdMain) {
		this.cal = cal;
		this.tdtManager = tdtManager;
		this.cdMain = cdMain;
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
		
		tfWork = new JTextField(27);
		tfStartDate = new JTextField(10);
		tfEndDate = new JTextField(10);
		lblCenter = new JLabel("~");
		taContent = new JTextArea(8,37);
		btnSave = new JButton("저장");
		btnCancel = new JButton("취소");
		todayCal = Calendar.getInstance();
	}
	private void setDisplay() {

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(new TitledBorder("할 일"));
		pnlNorth.add(tfWork);
		
		JPanel pnlCenter = new JPanel(new BorderLayout());
		JPanel pnlInfo = new JPanel();
		JPanel pnlInfo1 = new JPanel();
		pnlInfo1.setBorder(new TitledBorder("시작일"));
		if(popup != null){
			String month = "";
			if(todayCal.get(Calendar.MONTH)+1 <10){
				month = "0" + (todayCal.get(Calendar.MONTH)+1) ;
			}
			String indexString = String.valueOf(index);
			if(index < 10){
				indexString = "0" + indexString;
			}
			tfStartDate.setText(todayCal.get(Calendar.YEAR) +"-"
					+ month+ "-" 
					+ indexString
					);
		}else{
			String month = "";
			if(todayCal.get(Calendar.MONTH)+1 <10){
				month = "0" + (todayCal.get(Calendar.MONTH)+1) ;
			}
			int dateInt = todayCal.get(Calendar.DATE);
			String dateString = String.valueOf(dateInt);
			if(dateInt < 10){
				dateString = "0" +  dateString;
			}
			tfStartDate.setText(todayCal.get(Calendar.YEAR) +"-"
					+ month+ "-" 
					+ dateString
					);
		}
		
		
		
		pnlInfo1.add(tfStartDate);		
		JPanel pnlInfo2 = new JPanel();
		pnlInfo2.add(lblCenter);
		JPanel pnlInfo3 = new JPanel();
		pnlInfo3.setBorder(new TitledBorder("종료일"));
		pnlInfo3.add(tfEndDate);
		
		pnlInfo.add(pnlInfo1);
		pnlInfo.add(pnlInfo2);
		pnlInfo.add(pnlInfo3);
		
		JPanel pnlArea = new JPanel();
		pnlArea.setBorder(new TitledBorder("내용"));
		JScrollPane scroll = new JScrollPane(taContent);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pnlArea.add(scroll);

		pnlCenter.add(pnlInfo, BorderLayout.NORTH);
		pnlCenter.add(pnlArea, BorderLayout.CENTER);
		
		JPanel pnlSouth = new JPanel();		
		pnlSouth.add(btnSave);
		pnlSouth.add(btnCancel);

		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

	}
	
	public void setStartTextField(int year, String month, String date){
		String str = year + "-" + month+ "-" + date;	// setText할 예정인 String.
		try {
			Calendar calStart = Utils.getCalendarUseString(str);	// startDate의 cal.
			Calendar calEnd = Utils.getCalendarUseString(tfEndDate.getText());	// (tf에 입력되어있는) endDate의 cal.
			
			if (calStart.compareTo(calEnd) > 0) {	// cal끼리 compareTo, 시작일이 종료일보다 크면!
				JOptionPane.showMessageDialog(this, "시작일이 종료일보다 나중입니다!", "에러", JOptionPane.ERROR_MESSAGE);
			} else {	// 정상적일 경우.
				tfStartDate.setText(str);	// setText.
			}
		} catch (Exception ex) {	// 예외 >> tfEndDate에 올바른 값이 입력되지 않았을 경우에 발생함.
			tfStartDate.setText(str);	// compareTo 과정 생략하고 setText.
		}
	}
	
	public void setEndTextField(int year, String month, String date){
		String str = year + "-" + month+ "-" + date;	// setText할 예정인 String.
		try {
			Calendar calEnd = Utils.getCalendarUseString(str);	// endDate의 cal.
			Calendar calStart = Utils.getCalendarUseString(tfStartDate.getText());	// (tf에 입력되어있는) startDate의 cal.
			
			if (calEnd.compareTo(calStart) < 0) {	// cal끼리 compareTo, 종료일이 시작일보다 작으면!
				JOptionPane.showMessageDialog(this, "종료일이 시작일보다 먼저입니다!", "에러", JOptionPane.ERROR_MESSAGE);
			} else {	// 정상적일 경우.
				tfEndDate.setText(str);	// setText.
			}
		} catch (Exception ex) {	// 예외 >> tfStartDate에 올바른 값이 입력되지 않았을 경우에 발생함.
			tfEndDate.setText(str);	// compareTo 과정 생략하고 setText.
		}
	}
	
	private void addListener() {
		ActionListener btnListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object src = ae.getSource();
				if(src == btnSave){
					//정보(할일 / 날짜 2개/ 내용)를 
					//클래스에 저장하고 그내용을 파일에 저장
					String todo = tfWork.getText();
					String startDate = tfStartDate.getText();
					String endDate = tfEndDate.getText();
					String toDoInfo = taContent.getText();
					
					if(todo.length() == 0 || startDate.length() == 0 || endDate.length() == 0) {
						showErrorMessage("내용을 입력해 주세요");
					}else{
						TodoThing tdt =new TodoThing(todo, startDate, endDate, toDoInfo);
						tdtManager.addTodoThingEachDays(tdt);
						if(popup != null) {
							popup.getVectorTodos().add(tdt);
							popup.resetTodo();
						}
						cdMain.markTodo(1);
						dispose();
					}
				}else if(src == btnCancel){
					dispose();
				}
			}
		};
		
		btnSave.addActionListener(btnListener);
		btnCancel.addActionListener(btnListener);
		
		MouseAdapter msAdpt = new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent ae) {
				Object src = ae.getSource();
				
				if(src == tfStartDate){
					new ChoiceDate(cal,Add.this,START,1);
				}else if(src == tfEndDate){
					new ChoiceDate(cal,Add.this, END,1);
				}				
			}
		};
		tfStartDate.addMouseListener(msAdpt);
		tfEndDate.addMouseListener(msAdpt);
		
	}
	
	public void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(Add.this, message, "에러", JOptionPane.ERROR_MESSAGE);
	}
	
	private void showFrame() {
		setTitle("추가");
		pack();
		setLocation(500,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	

}
