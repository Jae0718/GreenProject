

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Notice extends JDialog {
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JButton btnOk;
	private Vector<NoticePanel> noticeVector;
	
	private Calendar todayCal;
	private Vector<TodoThing> undoneList;
	private TodoThingsManager tdtManager;
	
	public Notice(TodoThingsManager tdtManager) {
		//super(owner, "알림창", true);
		this.tdtManager = tdtManager;
		init();
		setDisplay();
		addListener();
		showDlg();
	}
	
	private void init() {
		todayCal = Calendar.getInstance();
		
		lbl2 = new JLabel();
		lbl3 = new JLabel();
		btnOk = new JButton("확인");
		noticeVector = new Vector<NoticePanel>();
		undoneList = new Vector<TodoThing>();
	}
	private void setDisplay() {
		TitledBorder title = new TitledBorder("");
		JPanel pnlNorth = new JPanel();
		JLabel lbl = new JLabel("[알림] "+
				todayCal.get(Calendar.YEAR) +"년 " +
				(todayCal.get(Calendar.MONTH)+1) +"월 " +
				todayCal.get(Calendar.DATE) +"일 " 
				+"의 할일");
		lbl.setFont(new Font("굴림", Font.BOLD, 15));
		pnlNorth.setOpaque(true);
		pnlNorth.setBackground(new Color(0xcedfe8));
		pnlNorth.add(lbl);
		
		JPanel pnlCenter = new JPanel(new GridLayout(0,1));
		pnlCenter.setBorder(title);
		pnlCenter.setOpaque(true);
		pnlCenter.setBackground(new Color(0xcedfe8));
		//undoneList를 차례대로 추가할 패널
		undoneList = searchTdt(todayCal);
		if(undoneList == null){
			title.setTitle("등록된 할일이 없습니다");
		}else{
			//undoneList만큼 돌려서 레이블 추가
			for(int i=0 ; i<undoneList.size(); i++){
				noticeVector.add(new NoticePanel());
				noticeVector.get(i).setTextFiled(undoneList.get(i).getToDo());
				JPanel pnlTemp = noticeVector.get(i).getPnlCenter();
				pnlTemp.setOpaque(true);
				pnlTemp.setBackground(new Color(0xcedfe8));
				pnlCenter.add(pnlTemp);
			}
			if(undoneList.size() == 0){
				title.setTitle("등록된 할일이 없습니다");
			}else{
				title.setTitle("할일이 " + undoneList.size() +"개 남았습니다");
			}
		}
		///undoneList크기만큼 만들고 그 내용을 표시한다.
		
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnOk);
		pnlSouth.setOpaque(true);
		pnlSouth.setBackground(new Color(0xcedfe8));
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

	}
	
	
	private Vector<TodoThing> searchTdt(Calendar cal){

		Map<String,Vector<TodoThing>> todoMap  = new Hashtable<String,Vector<TodoThing>>();
		todoMap = tdtManager.getTodoMap();
		
		if(todoMap.size() != 0){
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE));
			String key = Utils.getStringDate(cal);
			Vector<TodoThing> undoneListTemp = new Vector<TodoThing>();
			if(todoMap.containsKey(key)){
				Vector<TodoThing> vecTdt = todoMap.get(key);
				for(int i=0; i<vecTdt.size(); i++){
					if(vecTdt.get(i).isDone()){
						undoneListTemp.add(vecTdt.get(i));
					}
				}
				return undoneListTemp;
			}else{
				return undoneListTemp;
			}
		}else{
			return null;
		}
	}	
		
	
	
	private void addListener() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	private void showDlg() {
		setTitle("알림");
		pack();
		setLocation(300, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	

}
