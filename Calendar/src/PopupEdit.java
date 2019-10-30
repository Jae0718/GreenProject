
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class PopupEdit extends JFrame {
	
	static final int START = 1;
	static final int END= 2;
	
	private JTextField tfWork;
	private JButton btnDone;
	private JTextField tfStartDate;
	private JTextField tfEndDate;
	private JLabel lblCenter;
	private JTextArea taContent;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnCancel;
	private int index;
	private Popup popup;
	private TodoThing selectedTdt;
	private CalendarMain cdMain;

	public PopupEdit(int index, Popup popup, CalendarMain cdMain) {
		this.index = index;
		this.popup = popup;
		this.cdMain = cdMain;
		init();
		setDisplay();
		addListener();
		showFrame();
		cdMain.markTodo(3);
	}
	
	private void init() {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}catch(Exception e){

			}
		tfWork = new JTextField(27);
		btnDone = new JButton();
		tfStartDate = new JTextField(10);
		tfEndDate = new JTextField(10);
		lblCenter = new JLabel("~");
		taContent = new JTextArea(8,37);
		btnEdit = new JButton("편집");
		btnDelete = new JButton("삭제");
		btnCancel = new JButton("확인");
		selectedTdt = popup.getVectorTodos().get(index);
		//선택한 투두띵의 정보 저장
	}
	
	private void setDisplay() {

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(new TitledBorder("할 일"));
		tfWork.setText(selectedTdt.getToDo());
		tfWork.setEditable(false);
		pnlNorth.add(tfWork);
		if(selectedTdt.isDone()){
			btnDone.setText("완료");
		}else{
			btnDone.setText("다시진행");
		}
		pnlNorth.add(btnDone);
		
		JPanel pnlCenter = new JPanel(new BorderLayout());
		JPanel pnlInfo = new JPanel();
		JPanel pnlInfo1 = new JPanel();
		pnlInfo1.setBorder(new TitledBorder("시작일"));
		tfStartDate.setText(selectedTdt.getStartDate());
		tfStartDate.setEditable(false);
		pnlInfo1.add(tfStartDate);		
		JPanel pnlInfo2 = new JPanel();
		pnlInfo2.add(lblCenter);
		JPanel pnlInfo3 = new JPanel();
		pnlInfo3.setBorder(new TitledBorder("종료일"));
		tfEndDate.setText(selectedTdt.getEndDate());
		tfEndDate.setEditable(false);
		pnlInfo3.add(tfEndDate);
		
		pnlInfo.add(pnlInfo1);
		pnlInfo.add(pnlInfo2);
		pnlInfo.add(pnlInfo3);
		
		JPanel pnlArea = new JPanel(new BorderLayout());
		pnlArea.setBorder(new TitledBorder("내용"));
		taContent.setText(selectedTdt.getToDoInfo());
		taContent.setEditable(false);
		JScrollPane scroll = new JScrollPane(taContent);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pnlArea.add(scroll, BorderLayout.CENTER);
		JPanel pnlBtn = new JPanel();
		pnlBtn.add(btnEdit);
		pnlBtn.add(btnDelete);;
		pnlArea.add(pnlBtn,BorderLayout.SOUTH);

		pnlCenter.add(pnlInfo, BorderLayout.NORTH);
		pnlCenter.add(pnlArea, BorderLayout.CENTER);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnCancel);

		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

	}
	private void addListener() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object src = ae.getSource();
				if(src == btnEdit) {
					if(btnEdit.getText().equals("편집")) {	
						//편집을 누르면 저장이 되고 
						//편집기능
						setTextFeildEditable();
						btnEdit.setText("저장");
					} else{
						//편집★★★★★★★★★★★★★★★★★★★★★★★★★★★★ 
						//저장을 누르면 편집이 된다
						//저장기능
						String todo = tfWork.getText();
						String startDate = tfStartDate.getText();
						String endDate = tfEndDate.getText();
						String toDoInfo = taContent.getText();
						String key = selectedTdt.getStartDate();
						//해당객체를 가져와서 내용 바꾸기
						if(startDate == key){
							selectedTdt.editTodoThing(todo, startDate, endDate, toDoInfo);
						}else{
							popup.getTdtManager().deleteTodoThing(selectedTdt, index);
							selectedTdt.editTodoThing(todo, startDate, endDate, toDoInfo);
							popup.getTdtManager().addTodoThingEachDays(selectedTdt);
						}
						//해당키값의 벡터를 다시확인해야함
						btnEdit.setText("편집");
					}
				}else if(src == btnDelete){
					popup.getTdtManager().deleteTodoThing(selectedTdt, index);
				}else{
					popup.resetTodo();
					cdMain.removePnl();
					cdMain.setDate();
					cdMain.markTodo(2);
					cdMain.markTodo(1);
					dispose();
				}
			}
		};
		
		btnEdit.addActionListener(listener);
		btnDelete.addActionListener(listener);
		btnCancel.addActionListener(listener);
		
		
		MouseAdapter msAdpt = new MouseAdapter(){

			@Override
			public void mousePressed(MouseEvent me) {
				//마우스 클릭하면 초이스 창 뜨게
				
				Object src = me.getSource();
				JTextField tfTemp = (JTextField)src;
				
				if(tfTemp.isEditable()){
					Calendar	editCal = Calendar.getInstance();
					
					if(src == tfStartDate){
						new ChoiceDate(editCal, PopupEdit.this, START,2);
					}else if(src == tfEndDate){
						new ChoiceDate(editCal, PopupEdit.this, END ,2);
					}
				};				
			}
			
		};
		
		tfStartDate.addMouseListener(msAdpt);
		tfEndDate.addMouseListener(msAdpt);
		
		ActionListener listenerForDone = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//누르면 해당tdt의 상태변경
				if(btnDone.getText() == "완료"){
					selectedTdt.setDone(false);
					tfWork.setForeground(Color.GRAY);
					btnDone.setText("다시진행");
					
				}else{//다시진행이면
					selectedTdt.setDone(true);
					tfWork.setForeground(Color.BLACK);
					btnDone.setText("완료");
				}
			}
		};
		btnDone.addActionListener(listenerForDone);
		
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
	
	private void setTextFeildEditable(){

		tfWork.setEditable(true);
		tfStartDate.setEditable(true);
		tfEndDate.setEditable(true);
		taContent.setEditable(true);
	}
	
	private void showFrame() {
		setTitle("추가");
		setSize(500,500);
		setLocation(500,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	
}
