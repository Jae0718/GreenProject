

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
		//super(owner, "�˸�â", true);
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
		btnOk = new JButton("Ȯ��");
		noticeVector = new Vector<NoticePanel>();
		undoneList = new Vector<TodoThing>();
	}
	private void setDisplay() {
		TitledBorder title = new TitledBorder("");
		JPanel pnlNorth = new JPanel();
		JLabel lbl = new JLabel("[�˸�] "+
				todayCal.get(Calendar.YEAR) +"�� " +
				(todayCal.get(Calendar.MONTH)+1) +"�� " +
				todayCal.get(Calendar.DATE) +"�� " 
				+"�� ����");
		lbl.setFont(new Font("����", Font.BOLD, 15));
		pnlNorth.setOpaque(true);
		pnlNorth.setBackground(new Color(0xcedfe8));
		pnlNorth.add(lbl);
		
		JPanel pnlCenter = new JPanel(new GridLayout(0,1));
		pnlCenter.setBorder(title);
		pnlCenter.setOpaque(true);
		pnlCenter.setBackground(new Color(0xcedfe8));
		//undoneList�� ���ʴ�� �߰��� �г�
		undoneList = searchTdt(todayCal);
		if(undoneList == null){
			title.setTitle("��ϵ� ������ �����ϴ�");
		}else{
			//undoneList��ŭ ������ ���̺� �߰�
			for(int i=0 ; i<undoneList.size(); i++){
				noticeVector.add(new NoticePanel());
				noticeVector.get(i).setTextFiled(undoneList.get(i).getToDo());
				JPanel pnlTemp = noticeVector.get(i).getPnlCenter();
				pnlTemp.setOpaque(true);
				pnlTemp.setBackground(new Color(0xcedfe8));
				pnlCenter.add(pnlTemp);
			}
			if(undoneList.size() == 0){
				title.setTitle("��ϵ� ������ �����ϴ�");
			}else{
				title.setTitle("������ " + undoneList.size() +"�� ���ҽ��ϴ�");
			}
		}
		///undoneListũ�⸸ŭ ����� �� ������ ǥ���Ѵ�.
		
		
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
		setTitle("�˸�");
		pack();
		setLocation(300, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	

}
