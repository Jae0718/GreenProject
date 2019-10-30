package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class CalendarMain extends JFrame{
	private FileReader fr;
	private FileWriter fw;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
   //����â
   //Ķ���� Ŭ�������� ���� ��¥�� �޾ƿͼ�
   //Jframe�� �ش� ���� ��¥�� ǥ���ϰ�
   //��ư�� ������ ���� / �������� �Ѿ �� �ֵ�������
   private Calendar cal;
   private JLabel lblNorth;
   private String yearNorth;
   private String monthNorth;
   
   private JPanel pnlCenter;
   private JPanel pnlDate;
   private Vector<DatePanel> pnlList;
   private Dimension pnlDateSize;
   
   private JButton btnPreMonth;
   private JButton btnNextMonth;
   
   private JButton btnAdd;
   private JButton btnList;
   private JButton btnLogout;

   private int height = 600;
   private Dimension pnlSize = new Dimension(550,750);
   private Dimension daySize = new Dimension(80,30);
   public CalendarMain (){
	  fileInit();
      init();
      setDisplay();
      addListener();
      setFrame();
   }
   private void fileInit(){
	   try{
		   fr = new FileReader("data.dat");
		   ois = new ObjectInputStream(fr);
	   
	   }catch(FileNotFoundException e){
		   e.printStackTrace();
	   }finally{
		  try{
			  closeAll(fis);
		  }catch(Exception e){}
	   }
   }
   
   private void init(){
	   try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}catch(Exception e){

			} 
	   
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
     
      btnAdd = new JButton("�߰�");
      btnList = new JButton("��Ϻ���");
      btnLogout = new JButton("�α׾ƿ�");
   }
   private void setDisplay(){
     JPanel pnlNorth = new JPanel();
     lblNorth.setFont(new Font(Font.DIALOG,Font.BOLD,30));
     pnlNorth.add(lblNorth);
     
     ///Center�� ������
     JPanel pnlDay = new JPanel(new GridLayout(1,7));
     //���� ǥ���� �迭
     JLabel [] lblDayArr = new JLabel [7];
     lblDayArr[0] = new JLabel("SUN",JLabel.CENTER);
     lblDayArr[0].setOpaque(true);
     lblDayArr[0].setBackground(Color.RED);
     lblDayArr[1] = new JLabel("MON",JLabel.CENTER);
     lblDayArr[2] = new JLabel("TUE",JLabel.CENTER);
     lblDayArr[3] = new JLabel("WED",JLabel.CENTER);
     lblDayArr[4] = new JLabel("THU",JLabel.CENTER);
     lblDayArr[5] = new JLabel("FRI",JLabel.CENTER);
     lblDayArr[6] = new JLabel("SAT",JLabel.CENTER);
     lblDayArr[6].setOpaque(true);
     lblDayArr[6].setBackground(Color.BLUE);
     
     for(int i=0; i<7; i++){
    	 if(i>0 && i<6){
    		 lblDayArr[i].setOpaque(true);
    	     lblDayArr[i].setBackground(Color.GRAY);
    	 }
    	lblDayArr[i].setPreferredSize(daySize);
        lblDayArr[i].setBorder(new LineBorder(Color.WHITE));
        pnlDay.add(lblDayArr[i]);
     }
     pnlCenter.add(pnlDay, BorderLayout.NORTH);
     setDate();
     
     JPanel pnlWest = new JPanel();
     pnlWest.add(btnPreMonth);
     JPanel pnlEast = new JPanel();
     pnlEast.add(btnNextMonth);
      
     JPanel pnlSouth = new JPanel();
     pnlSouth.add(btnAdd);
     pnlSouth.add(btnList);
     pnlSouth.add(btnLogout);
     
     add(pnlNorth, BorderLayout.NORTH);
     add(pnlEast, BorderLayout.EAST);
     add(pnlWest, BorderLayout.WEST);
     add(pnlCenter, BorderLayout.CENTER);
     add(pnlSouth, BorderLayout.SOUTH);
   }

   private void setDate(){
         pnlList = new Vector<DatePanel>();
         pnlDate= new JPanel(new GridLayout(0,7));
        //�� ������� ��temp�����- ���� temp�� �������� -> �������� �ϰ͵� �ȳ��´ٴ� �� �˷��ֱ� ���ؼ�
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
           DatePanel dtPnl = new DatePanel(i+1);
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

   }
   
   private void setTitleLbl(int year, int month){
	   yearNorth = String.valueOf(year);
	   monthNorth = String.valueOf(month);
	   
	   lblNorth.setText("Schedule " + yearNorth + "��  " + monthNorth + "��");
	   repaint();
   }
   
   private void changeNext(){
      int year = cal.get(Calendar.YEAR);
      int month = cal.get(Calendar.MONTH);
      
      if(month == 11){
         year += 1;
         month = 0;
      }else{
         month += 1;
      }
      cal.set(year, month, cal.get(Calendar.DAY_OF_MONTH));
      
      setTitleLbl(year,month+1);
   }
   
   private void changePre(){
      int year = cal.get(Calendar.YEAR);
      int month = cal.get(Calendar.MONTH);
      
      if(month == 0){
         year -= 1;
         month = 11;
      }else{
         month -= 1;
      }
      cal.set(year, month, cal.get(Calendar.DAY_OF_MONTH));
      setTitleLbl(year,month+1);
   }
   
   private void addListener(){
      ActionListener btnListener = new ActionListener() {
      
         @Override
         public void actionPerformed(ActionEvent ae) {
            Object src =  ae.getSource();
   
             if(src ==btnPreMonth){
                changePre();
                pnlCenter.remove(pnlDate);
                setDate();
             }else if(src == btnNextMonth){
                changeNext();
                pnlCenter.remove(pnlDate);
                setDate();
             }
         }
      };
      
      btnPreMonth.addActionListener(btnListener);
      btnNextMonth.addActionListener(btnListener);
      
      btnAdd.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			new Add(cal);
		}
		
		
	});
   
   }
   
   public static void closeAll(Closeable... c){
		for(Closeable temp : c){
			try{
				temp.close();
			}catch(Exception e){}
		}
	}
   
   
   private void setFrame(){
     setSize(800,height);
     setLocation(100,100);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     setVisible(true);
   }
   public static void main(String[] args) {
      new CalendarMain();
   }

}