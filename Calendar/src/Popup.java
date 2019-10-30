

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
		btnAdd = new JButton("추가");
		btnCancel = new JButton("취소");
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
		String str = year + "년" + month + "월" + dateInt + "일";
		
		return str;
	}
	
	public void setTodo(){
		//map을 가지고 해당키(클릭한 날짜)에 존재하는 벡터에 있는 todo를 다 표시한다
		
		Hashtable <String, Vector<TodoThing>> mapTodo = 
					(Hashtable)tdtManager.getTodoMap();
		int year = CalendarMain.cal.get(Calendar.YEAR);
		int month = CalendarMain.cal.get(Calendar.MONTH)+1;
	
		int dateInt = Integer.parseInt(dateString);
		Calendar calTemp = Calendar.getInstance();
		calTemp.set(year,month,dateInt);

		String key = Utils.getStringDate(calTemp);
		if(mapTodo.containsKey(key)){
			//존재한다면
			vectorTodos = mapTodo.get(key);//가져와서
			//텍스트필드에 표시
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
			for(int i=0; i<size; i++){//벡터사이즈만큼 돌면서 표시
				TodoThing tdt = vectorTodos.get(i);
				popVector.get(i).setCheckBox(tdt.isDone());
				popVector.get(i).setLabelText(tdt.getToDo());
			}
			for(int i=size; i<firstSize; i++){//그 이후는 널이다
				popVector.get(i).setLabelText(null);
			}
		}else{
			for(int i=0; i<firstSize; i++){//사이즈가 영이면 다 널
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
					//원래 레이블에 표시되던게 삭제되지 않음
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
		setTitle("해야 할 일");
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
