

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class Popup extends JFrame {
	private JLabel lblTitle;
	private JButton btnAdd;
	private JButton btnCancel;
	private String dateString;
	private String titleString;
	private TodoThingsManager tdtManager;
	private CalendarMain cdMain;
	private Vector<TodoThing> vectorTodos;	
	private Vector<PopupPanel> popVector;
	private int firstSize = 2;
	
	public Popup(String date,TodoThingsManager tdtManager, CalendarMain cdMain) {
		this.dateString = date;
		this.tdtManager = tdtManager;
		this.cdMain = cdMain;
		init();
		setDisplay();
		addListener();
		showFrame();
		setTodo();
	}
	
	private void init() {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}catch(Exception e){

			}
		titleString = getClickDate();
		lblTitle = new JLabel("Selected Date  - " + titleString);
		lblTitle.setFont(new Font(Font.DIALOG, Font.BOLD, 17));
		btnAdd = new JButton("�߰�");
		btnCancel = new JButton("���");
		popVector = new Vector<PopupPanel>();
		vectorTodos = new Vector<TodoThing>();
	}
	
	private void setDisplay() {
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.setOpaque(true);
		pnlNorth.setBackground(new Color(0xcedfe8));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(0xcedfe8));
		//lblTitle.setPreferredSize(new Dimension(210,30));
		pnlNorth.add(lblTitle);
		
		JPanel pnlCenter = new JPanel(new GridLayout(0,1));
		for(int i=0; i<4; i++) {
			popVector.add(new PopupPanel(i,this,cdMain));
			pnlCenter.add(popVector.get(i).getPnlMain());
		}
		JScrollPane scroll = new JScrollPane(pnlCenter);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setOpaque(true);
		pnlSouth.setBackground(new Color(0xcedfe8));
		pnlSouth.add(btnAdd);
		pnlSouth.add(btnCancel);
		
		add(pnlNorth, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}
	
	private String getClickDate(){
		int year = CalendarMain.cal.get(Calendar.YEAR);
		int month = CalendarMain.cal.get(Calendar.MONTH)+1;
		int dateInt = Integer.parseInt(dateString);
		String str = year + "��" + month + "��" + dateInt + "��";
		
		return str;
	}
	
	public void setTodo(){
		//map�� ������ �ش�Ű(Ŭ���� ��¥)�� �����ϴ� ���Ϳ� �ִ� todo�� �� ǥ���Ѵ�
		
		Hashtable <String, Vector<TodoThing>> mapTodo = 
					(Hashtable)tdtManager.getTodoMap();
		int year = CalendarMain.cal.get(Calendar.YEAR);
		int month = CalendarMain.cal.get(Calendar.MONTH)+1;
	
		int dateInt = Integer.parseInt(dateString);
		Calendar calTemp = Calendar.getInstance();
		calTemp.set(year,month,dateInt);

		String key = Utils.getStringDate(calTemp);
		if(mapTodo.containsKey(key)){
			//�����Ѵٸ�
			vectorTodos = mapTodo.get(key);//�����ͼ�
			//�ؽ�Ʈ�ʵ忡 ǥ��
			firstSize = vectorTodos.size();
			for(int i=0; i<firstSize; i++){
				TodoThing tdt = vectorTodos.get(i);
				popVector.get(i).setLabelText(tdt.getToDo());
				popVector.get(i).setCheckBox(tdt.isDone());
			}
		}
		
	}
	
	public void removeInVector(int index){
		vectorTodos.remove(index);
	}
	
	
	public void resetTodo(){
		if(vectorTodos.size() != 0){
			int size = vectorTodos.size();
			for(int i=0; i<size; i++){//���ͻ����ŭ ���鼭 ǥ��
				TodoThing tdt = vectorTodos.get(i);
				popVector.get(i).setCheckBox(tdt.isDone());
				popVector.get(i).setLabelText(tdt.getToDo());
			}
			for(int i=size; i<firstSize; i++){//�� ���Ĵ� ���̴�
				popVector.get(i).setLabelText(null);
			}
		}else{
			for(int i=0; i<firstSize; i++){//����� ���̸� �� ��
				popVector.get(i).setLabelText(null);
			}
		}
	}
	
	private void addListener() {
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object src = ae.getSource();
				if(src == btnCancel){
					//���� ���̺� ǥ�õǴ��� �������� ����
					dispose();
				}else if(src == btnAdd){
					new Add(CalendarMain.cal,tdtManager,cdMain,Popup.this,Integer.parseInt(dateString));
				}
			}
		};
		btnCancel.addActionListener(listener);
		btnAdd.addActionListener(listener);
		
	}
	private void showFrame() {
		setTitle("�ؾ� �� ��");
		setSize(350,300);
		setLocation(300, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public Vector<TodoThing> getVectorTodos() {
		return vectorTodos;
	}

	public void setVectorTodos(Vector<TodoThing> vectorTodos) {
		this.vectorTodos = vectorTodos;
	}

	public TodoThingsManager getTdtManager() {
		return tdtManager;
	}

	public void setTdtManager(TodoThingsManager tdtManager) {
		this.tdtManager = tdtManager;
	}
	
	
	

}
