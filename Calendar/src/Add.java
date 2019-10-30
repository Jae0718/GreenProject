

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
		btnSave = new JButton("����");
		btnCancel = new JButton("���");
		todayCal = Calendar.getInstance();
	}
	private void setDisplay() {

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(new TitledBorder("�� ��"));
		pnlNorth.add(tfWork);
		
		JPanel pnlCenter = new JPanel(new BorderLayout());
		JPanel pnlInfo = new JPanel();
		JPanel pnlInfo1 = new JPanel();
		pnlInfo1.setBorder(new TitledBorder("������"));
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
		pnlInfo3.setBorder(new TitledBorder("������"));
		pnlInfo3.add(tfEndDate);
		
		pnlInfo.add(pnlInfo1);
		pnlInfo.add(pnlInfo2);
		pnlInfo.add(pnlInfo3);
		
		JPanel pnlArea = new JPanel();
		pnlArea.setBorder(new TitledBorder("����"));
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
		String str = year + "-" + month+ "-" + date;	// setText�� ������ String.
		try {
			Calendar calStart = Utils.getCalendarUseString(str);	// startDate�� cal.
			Calendar calEnd = Utils.getCalendarUseString(tfEndDate.getText());	// (tf�� �ԷµǾ��ִ�) endDate�� cal.
			
			if (calStart.compareTo(calEnd) > 0) {	// cal���� compareTo, �������� �����Ϻ��� ũ��!
				JOptionPane.showMessageDialog(this, "�������� �����Ϻ��� �����Դϴ�!", "����", JOptionPane.ERROR_MESSAGE);
			} else {	// �������� ���.
				tfStartDate.setText(str);	// setText.
			}
		} catch (Exception ex) {	// ���� >> tfEndDate�� �ùٸ� ���� �Էµ��� �ʾ��� ��쿡 �߻���.
			tfStartDate.setText(str);	// compareTo ���� �����ϰ� setText.
		}
	}
	
	public void setEndTextField(int year, String month, String date){
		String str = year + "-" + month+ "-" + date;	// setText�� ������ String.
		try {
			Calendar calEnd = Utils.getCalendarUseString(str);	// endDate�� cal.
			Calendar calStart = Utils.getCalendarUseString(tfStartDate.getText());	// (tf�� �ԷµǾ��ִ�) startDate�� cal.
			
			if (calEnd.compareTo(calStart) < 0) {	// cal���� compareTo, �������� �����Ϻ��� ������!
				JOptionPane.showMessageDialog(this, "�������� �����Ϻ��� �����Դϴ�!", "����", JOptionPane.ERROR_MESSAGE);
			} else {	// �������� ���.
				tfEndDate.setText(str);	// setText.
			}
		} catch (Exception ex) {	// ���� >> tfStartDate�� �ùٸ� ���� �Էµ��� �ʾ��� ��쿡 �߻���.
			tfEndDate.setText(str);	// compareTo ���� �����ϰ� setText.
		}
	}
	
	private void addListener() {
		ActionListener btnListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object src = ae.getSource();
				if(src == btnSave){
					//����(���� / ��¥ 2��/ ����)�� 
					//Ŭ������ �����ϰ� �׳����� ���Ͽ� ����
					String todo = tfWork.getText();
					String startDate = tfStartDate.getText();
					String endDate = tfEndDate.getText();
					String toDoInfo = taContent.getText();
					
					if(todo.length() == 0 || startDate.length() == 0 || endDate.length() == 0) {
						showErrorMessage("������ �Է��� �ּ���");
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
		JOptionPane.showMessageDialog(Add.this, message, "����", JOptionPane.ERROR_MESSAGE);
	}
	
	private void showFrame() {
		setTitle("�߰�");
		pack();
		setLocation(500,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	

}
