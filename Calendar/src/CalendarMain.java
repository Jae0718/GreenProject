

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
   //메인창
   //캘린더 클래스에서 오늘 날짜를 받아와서++++
   //Jframe에 해당 월일 날짜를 표시하고
   //버튼을 눌러서 전월 / 다음월로 넘어갈 수 있도록하자
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
    		  new TodoThing("효섭띠30짤인디", "2019-12-27", "2019-12-31", "열심히하자")
    		  );
      tdtManager.addTodoThingEachDays(
    		  new TodoThing("303002 BIRTH", "2019-04-27", "2019-04-27", "열심히하자")
    		  );
      tdtManager.addTodoThingEachDays(
    		  new TodoThing("꿀리미생일뿅", "2019-12-01", "2019-12-08", "열심히하자")
    		  );
      tdtManager.addTodoThingEachDays(
    		  new TodoThing("원도짱30짤인디", "2020-03-06", "2020-03-06", "열심히하자")
    		  );
      tdtManager.addTodoThingEachDays(
    		  new TodoThing("할일을 준다", "2019-05-28", "2019-05-30", "열심히하자")
    		  );
      */
   }

   
   private void init(){
	   
	 
	   try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}catch(Exception e){
				
			}
	 fc = new JFileChooser(".'");
     cal = Calendar.getInstance();//현재날짜를 불러온다
     yearNorth = String.valueOf(cal.get(Calendar.YEAR));//현재 연도정보를 불러옴
     monthNorth = String.valueOf(cal.get(Calendar.MONTH)+1);//현재월정보를 불러옴 +1 해야함
     lblNorth = new JLabel("Schedule " + yearNorth + "년  " + monthNorth + "월"); //노스에 표시될 레이블
     /////Center에 들어가는거
     pnlCenter = new JPanel(new BorderLayout());
     //pnlList = new Vector<JPanel>();
     pnlDateSize = new Dimension(90,90);
    
     btnPreMonth = new JButton("◀");
     btnNextMonth = new JButton("▶");
     btnPreYear = new JButton("◀◀");
     btnNextYear = new JButton("▶▶");
     
      btnAdd = new JButton("추가");
      btnList = new JButton("목록보기");
      btnLogout = new JButton("로그아웃");
      
      mBar = new JMenuBar();
      mFile = new JMenu("파일");
      miOpen = new JMenuItem("열기", 'O');
      miOpen.setIcon(new ImageIcon("open.png"));
      KeyStroke keys = KeyStroke.getKeyStroke(
     	KeyEvent.VK_O,
     	InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK);
      miOpen.setAccelerator(keys);
      miSave = new JMenuItem("저장");
      miSave.setIcon(new ImageIcon("save.png"));
      KeyStroke key = KeyStroke.getKeyStroke(
     	KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
      miSave.setAccelerator(key);
      
      miExit = new JMenuItem("로그아웃");
      miExit.setIcon(new ImageIcon("door.png"));
      
      mFile.add(miOpen);
      mFile.add(miSave);
      mFile.addSeparator();
      mFile.add(miExit);
      
      mBar.add(mFile);
      
//    try {
  	  Map<String, Vector<TodoThing>> map = tdtManager.getTodoMap(); //매니저에 있는 맵을 가져옴
	      Collection<Vector<TodoThing>> vec = map.values();	// 맵의 '밸류들'을 Collection으로 변환.
	      Object[] arr = vec.toArray();	// 그걸 다시 배열로 변환.
	      for (int index = 0 ; index < arr.length ; index++) {
	    	  Vector<TodoThing> tdts = (Vector<TodoThing>) arr[index];	// 그 배열 내부의 벡터를 선언
	    	  Object[] tdt = tdts.toArray();	// 그 벡터를 배열로 변환.
	    	  for (int innerIdx = 0 ; innerIdx < tdt.length ; innerIdx++) {	
	    		  ((TodoThing) tdt[innerIdx]).setIsWritten(true);	// 이중반복문이 돌면서 Map 내부의 모든 TodoThing의 IsWriten을 true로 초기화한다.
	    	  }
	      } // 수정사항(190524) >> TodoThing.IsWriten을 private로 만들고 getter&setter로 만듬. 이 클래스 내부에서도 해당 멤버에 getter&setter로 접근하게 수정함.
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
	     ///Center에 들어갈내용
	     JPanel pnlDay = new JPanel(new GridLayout(1,7));
	     //요일 표시할 배열
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
        //정보가 들어가있는 알짜배기 패널 넣기
        int dayMax = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for(int i=0; i<dayMax; i++){//해당월의 최대 날짜수
           DatePanel dtPnl = new DatePanel(i+1,tdtManager,this);
           pnlList.add(dtPnl);
        }
       
        for(int i=0; i<dayMax; i++){
           pnlDate.add(pnlList.get(i).getTempPnl());
        }
        //뒤 빈공간
        Calendar tempCal = Calendar.getInstance();
        tempCal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), dayMax);
        int lastDayNum = tempCal.get(Calendar.DAY_OF_WEEK);//일~월 : 1~7반환
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
	   Map temp = tdtManager.getTodoMap(); //매니저에 있는 맵을 가져옴
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
		  ///해당월의 키값을 키리스트2에 다 저장해뒀고 
		  ///그 키값을 가지고 각 벡터에 접근함
		  for(int i=0; i<keyList_2.size(); i++){
			  Vector<TodoThing> vecTdt = (Vector<TodoThing>)todoMap.get(keyList_2.get(i));
			  if(order == 1){
				 setTodoThingInLabel(vecTdt);
			  }else if(order == 2){//달력이 넘어갈때
				 setAllIsWritten(vecTdt);
			  }else if(order == 3){//해당달 업데이트할떄 이전내용 지우기
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
				//자른날짜가 Calendar객체의 날짜정보로 바뀌어야해서 -1
				startCal.set(Calendar.DATE, Integer.parseInt(date));
				
				int dayMax = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			   
				String strDate = Utils.getStringDate(startCal);
				int index = Integer.parseInt(date)-1;
				boolean flag = true;
				String todoString = tdt.getToDo();
				for(int j=0; j<=dateDifference && flag; j++){
				//레이블에 추가
					if(index < dayMax){
						tdt.isWritten = true;
						startCal.add(Calendar.DAY_OF_YEAR, 1);//하루를 더한다
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
	   //벡터사이즈만큼 돌면서 벡터안의 각 투두띵을 레이블에 표시
	   for(int i=0; i<vecTdt.size(); i++){
		   TodoThing tdt = vecTdt.get(i);
			if(tdt.isWritten){//써지지 않았다면
	
				   int dateDifference = Utils.calBetweenDates(tdt);

				   String startDate = tdt.getStartDate();
				   String year = startDate.substring(0,4);
				   String month = startDate.substring(5,7);
				   String date = startDate.substring(8,10);
				   
					Calendar startCal = Calendar.getInstance();
					startCal.set(Calendar.YEAR, Integer.parseInt(year));
					startCal.set(Calendar.MONTH, Integer.parseInt(month)-1);
					//자른날짜가 Calendar객체의 날짜정보로 바뀌어야해서 -1
					startCal.set(Calendar.DATE, Integer.parseInt(date));
					
					int dayMax = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
					//지금 떠있는 달의 최대 일수만큼
					
					String strDate = Utils.getStringDate(startCal);
					int index = Integer.parseInt(date)-1;
					boolean flag = true;
					String todoString = tdt.getToDo();
					for(int j=0; j<=dateDifference && flag; j++){
					//레이블에 추가
						if(index < dayMax){
							pnlList.get(index).setLblTextInCalendar(todoString);
							tdt.isWritten = false;
							startCal.add(Calendar.DAY_OF_YEAR, 1);//하루를 더한다
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
		//월을 이동하기 전에 해당월에 표시한 것들을 다 리셋
	   for(int i=0; i<vecTdt.size(); i++){
		   if(!(vecTdt.get(i).isWritten)){//만약 써졌다면
			   vecTdt.get(i).isWritten = true;
		   }
	   }
	}

   
   private void setTitleLbl(int year, int month){
	   yearNorth = String.valueOf(year);
	   monthNorth = String.valueOf(month);
	   
	   lblNorth.setText("Schedule " + yearNorth + "년  " + monthNorth + "월");
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
	   //캘린더를 받아서 연도를 바꾼다
	   
	   int year = calendar.get(Calendar.YEAR);
	   int month = calendar.get(Calendar.MONTH);
	   year --;
	   
	   calendar.set(year,month,calendar.get(Calendar.DAY_OF_MONTH));
	   setTitleLbl(year,month+1);
	   
	   return calendar;
	   
   }
   
   private Calendar changeNextYear(Calendar calendar){
	   //캘린더를 받아서 연도를 바꾼다
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
							"저장하시겠습니까?",
							"저장",
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
	   
	   //기존맵에 추가
	   
	   
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