
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
		btnEdit = new JButton("����");
		btnDelete = new JButton("����");
		btnCancel = new JButton("Ȯ��");
		selectedTdt = popup.getVectorTodos().get(index);
		//������ ���ζ��� ���� ����
	}
	
	private void setDisplay() {

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(new TitledBorder("�� ��"));
		tfWork.setText(selectedTdt.getToDo());
		tfWork.setEditable(false);
		pnlNorth.add(tfWork);
		if(selectedTdt.isDone()){
			btnDone.setText("�Ϸ�");
		}else{
			btnDone.setText("�ٽ�����");
		}
		pnlNorth.add(btnDone);
		
		JPanel pnlCenter = new JPanel(new BorderLayout());
		JPanel pnlInfo = new JPanel();
		JPanel pnlInfo1 = new JPanel();
		pnlInfo1.setBorder(new TitledBorder("������"));
		tfStartDate.setText(selectedTdt.getStartDate());
		tfStartDate.setEditable(false);
		pnlInfo1.add(tfStartDate);		
		JPanel pnlInfo2 = new JPanel();
		pnlInfo2.add(lblCenter);
		JPanel pnlInfo3 = new JPanel();
		pnlInfo3.setBorder(new TitledBorder("������"));
		tfEndDate.setText(selectedTdt.getEndDate());
		tfEndDate.setEditable(false);
		pnlInfo3.add(tfEndDate);
		
		pnlInfo.add(pnlInfo1);
		pnlInfo.add(pnlInfo2);
		pnlInfo.add(pnlInfo3);
		
		JPanel pnlArea = new JPanel(new BorderLayout());
		pnlArea.setBorder(new TitledBorder("����"));
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
					if(btnEdit.getText().equals("����")) {	
						//������ ������ ������ �ǰ� 
						//�������
						setTextFeildEditable();
						btnEdit.setText("����");
					} else{
						//�����ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ� 
						//������ ������ ������ �ȴ�
						//������
						String todo = tfWork.getText();
						String startDate = tfStartDate.getText();
						String endDate = tfEndDate.getText();
						String toDoInfo = taContent.getText();
						String key = selectedTdt.getStartDate();
						//�ش簴ü�� �����ͼ� ���� �ٲٱ�
						if(startDate == key){
							selectedTdt.editTodoThing(todo, startDate, endDate, toDoInfo);
						}else{
							popup.getTdtManager().deleteTodoThing(selectedTdt, index);
							selectedTdt.editTodoThing(todo, startDate, endDate, toDoInfo);
							popup.getTdtManager().addTodoThingEachDays(selectedTdt);
						}
						//�ش�Ű���� ���͸� �ٽ�Ȯ���ؾ���
						btnEdit.setText("����");
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
				//���콺 Ŭ���ϸ� ���̽� â �߰�
				
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
				//������ �ش�tdt�� ���º���
				if(btnDone.getText() == "�Ϸ�"){
					selectedTdt.setDone(false);
					tfWork.setForeground(Color.GRAY);
					btnDone.setText("�ٽ�����");
					
				}else{//�ٽ������̸�
					selectedTdt.setDone(true);
					tfWork.setForeground(Color.BLACK);
					btnDone.setText("�Ϸ�");
				}
			}
		};
		btnDone.addActionListener(listenerForDone);
		
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
	
	private void setTextFeildEditable(){

		tfWork.setEditable(true);
		tfStartDate.setEditable(true);
		tfEndDate.setEditable(true);
		taContent.setEditable(true);
	}
	
	private void showFrame() {
		setTitle("�߰�");
		setSize(500,500);
		setLocation(500,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	
}
