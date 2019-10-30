

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.WriteAbortedException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class CalendarMain extends JFrame{
	public static Calendar cal;
	
	private FileReader fr;
	private FileWriter fw;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
   //����â
   //Ķ���� Ŭ�������� ���� ��¥�� �޾ƿͼ�++++
   //Jframe�� �ش� ���� ��¥�� ǥ���ϰ�
   //��ư�� ������ ���� / �������� �Ѿ �� �ֵ�������
   private JLabel lblNorth;
   private String yearNorth;
   private String monthNorth;
   
   private JPanel pnlCenter;
   private JPanel pnlDate;
   private Vector<DatePanel> pnlList;
   private Dimension pnlDateSize;
   
   private JButton btnPreMonth;
   private JButton btnNextMonth;
   private JButton btnPreYear;
   private JButton btnNextYear;
   
   private JButton btnAdd;
   private JButton btnList;
   private JButton btnLogout;
   
   private JMenuBar mBar;
   private JMenu mFile;
   private JMenuItem miOpen;
   private JMenuItem miSave;
   private JMenuItem miExit;
   private JFileChooser fc;
   
   private int height = 600;
   private Dimension pnlSize = new Dimension(550,750);
   private Dimension daySize = new Dimension(80,30);
   
   private Login login;
   private String memberID;
   private TodoThingsManager tdtManager;
   
   public CalendarMain (TodoThingsManager tdtManger, String memberID,Login login){
	  this.memberID = memberID;
      this.login = login;
      this.tdtManager = tdtManger;
	  init();
      setDisplay();
      addListener();
      setFrame();
      markTodo(1);
      
      new Notice(this.tdtManager);
      
     
      /*
      tdtManager.addTodoThingEachDays(
    		  new TodoThing("ȿ����30©�ε�", "2019-12-27", "2019-12-31", "����������")
    		  );
      tdtManager.addTodoThingEachDays(
    		  new TodoThing("303002 BIRTH", "2019-04-27", "2019-04-27", "����������")
    		  );
      tdtManager.addTodoThingEachDays(
    		  new TodoThing("�ܸ��̻��ϻ�", "2019-12-01", "2019-12-08", "����������")
    		  );
      tdtManager.addTodoThingEachDays(
    		  new TodoThing("����¯30©�ε�", "2020-03-06", "2020-03-06", "����������")
    		  );
      tdtManager.addTodoThingEachDays(
    		  new TodoThing("������ �ش�", "2019-05-28", "2019-05-30", "����������")
    		  );
      */
   }

   
   private void init(){
	   
	 
	   try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}catch(Exception e){
				
			}
	 fc = new JFileChooser(".'");
     cal = Calendar.getInstance();//���糯¥�� �ҷ��´�
     yearNorth = String.valueOf(cal.get(Calendar.YEAR));//���� ���������� �ҷ���
     monthNorth = String.valueOf(cal.get(Calendar.MONTH)+1);//����������� �ҷ��� +1 �ؾ���
     lblNorth = new JLabel("Schedule " + yearNorth + "��  " + monthNorth + "��"); //�뽺�� ǥ�õ� ���̺�
     /////Center�� ���°�
     pnlCenter = new JPanel(new BorderLayout());
     //pnlList = new Vector<JPanel>();
     pnlDateSize = new Dimension(90,90);
    
     btnPreMonth = new JButton("��");
     btnNextMonth = new JButton("��");
     btnPreYear = new JButton("����");
     btnNextYear = new JButton("����");
     
      btnAdd = new JButton("�߰�");
      btnList = new JButton("��Ϻ���");
      btnLogout = new JButton("�α׾ƿ�");
      
      mBar = new JMenuBar();
      mFile = new JMenu("����");
      miOpen = new JMenuItem("����", 'O');
      miOpen.setIcon(new ImageIcon("open.png"));
      KeyStroke keys = KeyStroke.getKeyStroke(
     	KeyEvent.VK_O,
     	InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK);
      miOpen.setAccelerator(keys);
      miSave = new JMenuItem("����");
      miSave.setIcon(new ImageIcon("save.png"));
      KeyStroke key = KeyStroke.getKeyStroke(
     	KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
      miSave.setAccelerator(key);
      
      miExit = new JMenuItem("�α׾ƿ�");
      miExit.setIcon(new ImageIcon("door.png"));
      
      mFile.add(miOpen);
      mFile.add(miSave);
      mFile.addSeparator();
      mFile.add(miExit);
      
      mBar.add(mFile);
      
//    try {
  	  Map<String, Vector<TodoThing>> map = tdtManager.getTodoMap(); //�Ŵ����� �ִ� ���� ������
	      Collection<Vector<TodoThing>> vec = map.values();	// ���� '�����'�� Collection���� ��ȯ.
	      Object[] arr = vec.toArray();	// �װ� �ٽ� �迭�� ��ȯ.
	      for (int index = 0 ; index < arr.length ; index++) {
	    	  Vector<TodoThing> tdts = (Vector<TodoThing>) arr[index];	// �� �迭 ������ ���͸� ����
	    	  Object[] tdt = tdts.toArray();	// �� ���͸� �迭�� ��ȯ.
	    	  for (int innerIdx = 0 ; innerIdx < tdt.length ; innerIdx++) {	
	    		  ((TodoThing) tdt[innerIdx]).setIsWritten(true);	// ���߹ݺ����� ���鼭 Map ������ ��� TodoThing�� IsWriten�� true�� �ʱ�ȭ�Ѵ�.
	    	  }
	      } // ��������(190524) >> TodoThing.IsWriten�� private�� ����� getter&setter�� ����. �� Ŭ���� ���ο����� �ش� ����� getter&setter�� �����ϰ� ������.
//    } catch () {}
   }
   
   private void setDisplay(){
	     JPanel pnlNorth = new JPanel();
	     pnlNorth.setOpaque(true);
	     pnlNorth.setBackground(new Color(0xcedfe8));
	     lblNorth.setFont(new Font(Font.DIALOG,Font.BOLD,20));
	     lblNorth.setForeground(Color.black);
	     pnlNorth.add(btnPreYear);
	     pnlNorth.add(btnPreMonth);
	     pnlNorth.add(lblNorth);
	     pnlNorth.add(btnNextMonth);
	     pnlNorth.add(btnNextYear);
	     ///Center�� ������
	     JPanel pnlDay = new JPanel(new GridLayout(1,7));
	     //���� ǥ���� �迭
	     JLabel [] lblDayArr = new JLabel [7];
	     lblDayArr[0] = new JLabel("SUN",JLabel.CENTER);
	     lblDayArr[0].setForeground(Color.white);
	     lblDayArr[0].setOpaque(true);
	     lblDayArr[0].setBackground(new Color(0Xfa5f5f));
	     lblDayArr[1] = new JLabel("MON",JLabel.CENTER);
	     lblDayArr[2] = new JLabel("TUE",JLabel.CENTER);
	     lblDayArr[3] = new JLabel("WED",JLabel.CENTER);
	     lblDayArr[4] = new JLabel("THU",JLabel.CENTER);
	     lblDayArr[5] = new JLabel("FRI",JLabel.CENTER);
	     lblDayArr[6] = new JLabel("SAT",JLabel.CENTER);
	     lblDayArr[6].setForeground(Color.white);
	     lblDayArr[6].setOpaque(true);
	     lblDayArr[6].setBackground(new Color(0X5fa1fa));
	     
	     for(int i=0; i<7; i++){
	    	 if(i>0 && i<6){
	    		 lblDayArr[i].setOpaque(true);
	    	     lblDayArr[i].setBackground(new Color (0Xbcc3cc));
	    	 }
	    	lblDayArr[i].setPreferredSize(daySize);
	        lblDayArr[i].setBorder(new LineBorder(Color.WHITE));
	        pnlDay.add(lblDayArr[i]);
	     }
	     pnlCenter.add(pnlDay, BorderLayout.NORTH);
	     setDate();
	     
	      
	     JPanel pnlSouth = new JPanel();
	     pnlSouth.setOpaque(true);
	     pnlSouth.setBackground(new Color(0xcedfe8));
	     pnlSouth.add(btnAdd);
	     pnlSouth.add(btnList);
	     pnlSouth.add(btnLogout);
	     
	     setJMenuBar(mBar);
	     
	     add(pnlNorth, BorderLayout.NORTH);;
	     add(pnlCenter, BorderLayout.CENTER);
	     add(pnlSouth, BorderLayout.SOUTH);

	   }

   public void setDate(){
         pnlList = new Vector<DatePanel>();
         pnlDate= new JPanel(new GridLayout(0,7));
       
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 01);
        int dayNum = cal.get(Calendar.DAY_OF_WEEK);

        for(int i=0;i<dayNum-1; i++){
           JPanel pnlTemp= new JPanel();
           pnlTemp.setPreferredSize(pnlDateSize);
           pnlTemp.setBorder(new LineBorder(Color.WHITE));
           pnlDate.add(pnlTemp);  
        }
        //������ ���ִ� ��¥��� �г� �ֱ�
        int dayMax = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for(int i=0; i<dayMax; i++){//�ش���� �ִ� ��¥��
           DatePanel dtPnl = new DatePanel(i+1,tdtManager,this);
           pnlList.add(dtPnl);
        }
       
        for(int i=0; i<dayMax; i++){
           pnlDate.add(pnlList.get(i).getTempPnl());
        }
        //�� �����
        Calendar tempCal = Calendar.getInstance();
        tempCal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), dayMax);
        int lastDayNum = tempCal.get(Calendar.DAY_OF_WEEK);//��~�� : 1~7��ȯ
        for(int i=0; i<(7-lastDayNum); i++){
           JPanel pnlTemp= new JPanel();
           pnlTemp.setPreferredSize(pnlDateSize);
           pnlTemp.setBorder(new LineBorder(Color.WHITE));
           pnlDate.add(pnlTemp);  
        }
        
        pnlCenter.add(pnlDate, BorderLayout.CENTER);
        
        if(height%2 == 0){
        	height+=1;
        } else{
        	height-=1;
        }
        setSize(800,height);
        repaint();
        
        
   }
   private Vector<String> makeKeyList(){
	   Map temp = tdtManager.getTodoMap(); //�Ŵ����� �ִ� ���� ������
	   Set<String> keySet = temp.keySet();
	   Vector<String> keyList = new Vector<String>();
	   keyList.addAll(keySet);
	   Collections.sort(keyList);

	   return keyList;
   }
   
   public void markTodo(int order){
	   Map todoMap = tdtManager.getTodoMap();
	   Vector<String> keyList_1 = makeKeyList();
	  int calYear = cal.get(Calendar.YEAR);
	  int index = 0;
	  boolean yearFlag = true;
	  for(int i=0; i<keyList_1.size() && yearFlag; i++){
		  String keyYear = keyList_1.get(index).substring(0,4);
		  if(calYear == Integer.parseInt(keyYear)){
			  yearFlag = false;
		  }else{
			  index++;
		  }
		  if(index+1 == keyList_1.size()){
			  yearFlag = false;
		  }
		  
	  }
	  
	  if(index +1 != keyList_1.size() || keyList_1.size() == 1){
		  int calMonth = cal.get(Calendar.MONTH)+1;
		  Vector keyList_2 = new Vector<String>();
		  boolean monthFlag = true;
		  if(index == keyList_1.size()){
			monthFlag = false;  
		  }
		  for(int i=0; i<keyList_1.size() && monthFlag; i++){
			  String keyMonth = keyList_1.get(index).substring(5,7);
			  if(calMonth == Integer.parseInt(keyMonth)){
				  keyList_2.add(keyList_1.get(index));
				  index++;
			  }else if(calMonth < Integer.parseInt(keyMonth)){
				  monthFlag = false;
			  }else {
				  index++;
			  }
			  
			  if(index+1== keyList_1.size()){
				  monthFlag = false;
			  }
			  if(index == keyList_1.size()){
					monthFlag = false;  
			  }
			  
		  }
		  ///�ش���� Ű���� Ű����Ʈ2�� �� �����صװ� 
		  ///�� Ű���� ������ �� ���Ϳ� ������
		  for(int i=0; i<keyList_2.size(); i++){
			  Vector<TodoThing> vecTdt = (Vector<TodoThing>)todoMap.get(keyList_2.get(i));
			  if(order == 1){
				 setTodoThingInLabel(vecTdt);
			  }else if(order == 2){//�޷��� �Ѿ��
				 setAllIsWritten(vecTdt);
			  }else if(order == 3){//�ش�� ������Ʈ�ҋ� �������� �����
				  resetCalendar(vecTdt);
			  }
			  
		  }
	  }
	
	 
   }
   
   private void resetCalendar(Vector<TodoThing> vecTdt){
	   for(int i=0; i<vecTdt.size(); i++){
		   TodoThing tdt = vecTdt.get(i);
		   if(!tdt.isWritten){
			   int dateDifference = Utils.calBetweenDates(tdt);
			   
			   String startDate = tdt.getStartDate();
			   String year = startDate.substring(0,4);
			   String month = startDate.substring(5,7);
			   String date = startDate.substring(8,10);
			   
				Calendar startCal = Calendar.getInstance();
				startCal.set(Calendar.YEAR, Integer.parseInt(year));
				startCal.set(Calendar.MONTH, Integer.parseInt(month)-1);
				//�ڸ���¥�� Calendar��ü�� ��¥������ �ٲ����ؼ� -1
				startCal.set(Calendar.DATE, Integer.parseInt(date));
				
				int dayMax = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			   
				String strDate = Utils.getStringDate(startCal);
				int index = Integer.parseInt(date)-1;
				boolean flag = true;
				String todoString = tdt.getToDo();
				for(int j=0; j<=dateDifference && flag; j++){
				//���̺� �߰�
					if(index < dayMax){
						tdt.isWritten = true;
						startCal.add(Calendar.DAY_OF_YEAR, 1);//�Ϸ縦 ���Ѵ�
						strDate = Utils.getStringDate(startCal);
					}else{
						flag = false;
					}
					index++;
				}
		   }
	   }
   }
   
   
   private void setTodoThingInLabel(Vector<TodoThing> vecTdt){
	   //���ͻ����ŭ ���鼭 ���;��� �� ���ζ��� ���̺� ǥ��
	   for(int i=0; i<vecTdt.size(); i++){
		   TodoThing tdt = vecTdt.get(i);
			if(tdt.isWritten){//������ �ʾҴٸ�
	
				   int dateDifference = Utils.calBetweenDates(tdt);

				   String startDate = tdt.getStartDate();
				   String year = startDate.substring(0,4);
				   String month = startDate.substring(5,7);
				   String date = startDate.substring(8,10);
				   
					Calendar startCal = Calendar.getInstance();
					startCal.set(Calendar.YEAR, Integer.parseInt(year));
					startCal.set(Calendar.MONTH, Integer.parseInt(month)-1);
					//�ڸ���¥�� Calendar��ü�� ��¥������ �ٲ����ؼ� -1
					startCal.set(Calendar.DATE, Integer.parseInt(date));
					
					int dayMax = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
					//���� ���ִ� ���� �ִ� �ϼ���ŭ
					
					String strDate = Utils.getStringDate(startCal);
					int index = Integer.parseInt(date)-1;
					boolean flag = true;
					String todoString = tdt.getToDo();
					for(int j=0; j<=dateDifference && flag; j++){
					//���̺� �߰�
						if(index < dayMax){
							pnlList.get(index).setLblTextInCalendar(todoString);
							tdt.isWritten = false;
							startCal.add(Calendar.DAY_OF_YEAR, 1);//�Ϸ縦 ���Ѵ�
							strDate = Utils.getStringDate(startCal);
						}else{
							flag = false;
						}
						index++;
					}
				}
	   }
   }
   
   
   private void setAllIsWritten(Vector<TodoThing> vecTdt){
		//���� �̵��ϱ� ���� �ش���� ǥ���� �͵��� �� ����
	   for(int i=0; i<vecTdt.size(); i++){
		   if(!(vecTdt.get(i).isWritten)){//���� �����ٸ�
			   vecTdt.get(i).isWritten = true;
		   }
	   }
	}

   
   private void setTitleLbl(int year, int month){
	   yearNorth = String.valueOf(year);
	   monthNorth = String.valueOf(month);
	   
	   lblNorth.setText("Schedule " + yearNorth + "��  " + monthNorth + "��");
	   repaint();
   }
   
   private Calendar changeNextMonth(Calendar calendar){
      int year = calendar.get(Calendar.YEAR);
      int month = calendar.get(Calendar.MONTH);

      if(month == 11){
         year += 1;
         month = 0;
      }else{
         month += 1;
      }
      calendar.set(year, month, calendar.get(Calendar.DAY_OF_MONTH));
      setTitleLbl(year,month+1);
      
      return calendar;
   }
   
   private Calendar changePreMonth(Calendar calendar){
      int year = calendar.get(Calendar.YEAR);
      int month = calendar.get(Calendar.MONTH);
      
      if(month == 0){
         year -= 1;
         month = 11;
      }else{
         month -= 1;
      }
      calendar.set(year, month, calendar.get(Calendar.DAY_OF_MONTH));
      setTitleLbl(year,month+1);
      
      return calendar;
   }
   
   private Calendar changePreYear(Calendar calendar){
	   //Ķ������ �޾Ƽ� ������ �ٲ۴�
	   
	   int year = calendar.get(Calendar.YEAR);
	   int month = calendar.get(Calendar.MONTH);
	   year --;
	   
	   calendar.set(year,month,calendar.get(Calendar.DAY_OF_MONTH));
	   setTitleLbl(year,month+1);
	   
	   return calendar;
	   
   }
   
   private Calendar changeNextYear(Calendar calendar){
	   //Ķ������ �޾Ƽ� ������ �ٲ۴�
	   int year = calendar.get(Calendar.YEAR);
	   int month = calendar.get(Calendar.MONTH);
	   year ++;
	   
	   calendar.set(year,month,calendar.get(Calendar.DAY_OF_MONTH));
	   setTitleLbl(year,month+1);
	   
	   return calendar;
	   
   }
  
   public void removePnl(){
	   pnlCenter.remove(pnlDate);
   }
   
   private void addListener(){
      ActionListener moveBtnListener = new ActionListener() {
      
         @Override
         public void actionPerformed(ActionEvent ae) {
            Object src =  ae.getSource();
   
             if(src ==btnPreMonth){
                cal = changePreMonth(cal);
                pnlCenter.remove(pnlDate);
                setDate();
                markTodo(2);
                markTodo(1);
             }else if(src == btnNextMonth){
                cal = changeNextMonth(cal);
                pnlCenter.remove(pnlDate);
                setDate();
                markTodo(2);
                markTodo(1);
             }else if(src == btnPreYear){
            	 cal = changePreYear(cal);
            	 pnlCenter.remove(pnlDate);
            	 setDate();
            	 markTodo(2);
            	 markTodo(1);
             }else if(src == btnNextYear){
            	 cal = changeNextYear(cal);
            	 pnlCenter.remove(pnlDate);
            	 setDate();
            	 markTodo(2);
            	 markTodo(1);
             }
         }
      };
      btnLogout.addActionListener(moveBtnListener);
      btnPreMonth.addActionListener(moveBtnListener);
      btnNextMonth.addActionListener(moveBtnListener);
      btnPreYear.addActionListener(moveBtnListener);
      btnNextYear.addActionListener(moveBtnListener);
      
      
      ActionListener appBtnListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			Object src = ae.getSource();
			
			if(src == btnAdd){
				new Add(cal, tdtManager,CalendarMain.this);
				   cal = Calendar.getInstance();
			}else if(src == btnList){
				new List(tdtManager);
			}else if(src == btnLogout){
				Utils.writeTodo(tdtManager.getTodoMap(), "Data\\" + memberID + ".dat");
				new Login();
				dispose();
			}else if(src == miOpen){
				open();
			}else if(src == miSave){
				save();
				tdtManager.setChanged(true);
			}else if(src == miExit){
				new Login();
				dispose();
			}
		}
	};
      
      btnAdd.addActionListener(appBtnListener);
      btnList.addActionListener(appBtnListener);
      btnLogout.addActionListener(appBtnListener);
      miExit.addActionListener(appBtnListener);
      miOpen.addActionListener(appBtnListener);
      miSave.addActionListener(appBtnListener);
      
      
      
      WindowAdapter myWindowListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				if(!tdtManager.isChanged()){
					int result = JOptionPane.showConfirmDialog(
							CalendarMain.this,
							"�����Ͻðڽ��ϱ�?",
							"����",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE
							);
					if(result == JOptionPane.YES_OPTION) {
						save();
						tdtManager.setChanged(true);
						dispose();
					}else{
						dispose();
					}
				}else{
					dispose();
				}
			}
		};
		
		addWindowListener(myWindowListener);
   
   }
   
   public void open(){
	   Map<String,Vector<TodoThing>> mapTemp = new Hashtable<String,Vector<TodoThing>>();
	   int result = fc.showOpenDialog(this);
	   File f = fc.getSelectedFile();
	   String fileName = f.getPath();
	   FileInputStream fis = null;
	   ObjectInputStream ois = null;
		
	   try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			mapTemp = (Map<String,Vector<TodoThing>>)ois.readObject();
			tdtManager.getTodoMap().putAll(mapTemp);
			markTodo(2);
			markTodo(1);
	   }catch(WriteAbortedException e){
			e.printStackTrace();
		} catch(NotSerializableException e){
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			Utils.closeAll(ois, fis);
		}
	   
	   //�����ʿ� �߰�
	   
	   
   }
   
   public void save(){
	   Utils.writeTodo(tdtManager.getTodoMap(),"Data\\" + memberID + ".dat" );
   }
   
   
   private void setFrame(){
     setSize(800,height);
     setLocation(100,100);
     setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
     setVisible(true);
   }
   


public JPanel getPnlCenter() {
	return pnlCenter;
}


public void setPnlCenter(JPanel pnlCenter) {
	this.pnlCenter = pnlCenter;
}

   
   
}