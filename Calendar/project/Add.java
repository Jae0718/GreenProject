package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class Add extends JFrame {
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
	
	
	public Add(Calendar cal) {
		this.cal = cal;
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
		
	}
	private void setDisplay() {

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(new TitledBorder("할 일"));
		pnlNorth.add(tfWork);
		
		JPanel pnlCenter = new JPanel(new BorderLayout());
		JPanel pnlInfo = new JPanel();
		JPanel pnlInfo1 = new JPanel();
		pnlInfo1.setBorder(new TitledBorder("시작일"));
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
	
	public void setStartTextField(int year, int month, int date){
		tfStartDate.setText(year + "-" + month + "-" + date );
	}
	
	public void setEndTextField(int year, int month, int date){
		tfEndDate.setText(year + "-" + month + "-" + date );
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
					TodoThing tdt =new TodoThing(todo, startDate, endDate, toDoInfo);
					dispose();
					
					
				}
			}
		};
		
		btnSave.addActionListener(btnListener);
		
		MouseAdapter msAdpt = new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent ae) {
				Object src = ae.getSource();
				
				if(src == tfStartDate){
					JTextField tfTemp = (JTextField)src;
					new ChoiceDate(cal,Add.this,START);
					
				}else if(src == tfEndDate){
					new ChoiceDate(cal,Add.this, END);
				}				
			}
			
		};
		tfStartDate.addMouseListener(msAdpt);
		tfEndDate.addMouseListener(msAdpt);
		
	}
	private void showFrame() {
		setTitle("추가");
		pack();
		setLocation(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	

}
