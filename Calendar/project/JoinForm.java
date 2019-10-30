package kr.ac.green;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class JoinForm extends JDialog {
	private JLabel lblIdJ;
	private JLabel lblPwJ;
	private JLabel lblReJ;
	private JLabel lblNameJ;
	private JLabel lblNickJ;
	private JTextField tfIdJ;
	private JPasswordField tfPwJ;
	private JPasswordField tfReJ;
	private JTextField tfNameJ;
	private JTextField tfNickJ;
	private JLabel lblGender;
	private JLabel lblMale;
	private JLabel lblFemale;
	private JRadioButton male;
	private JRadioButton female;
	private JButton btnJoin;
	private JButton btnCancel;

	//부모객체 생성
	private LoginForm owner;

	public JoinForm(LoginForm owner){
		super(owner, "JoinForm",false);
		this.owner = owner;
		
		init();
		setDisplay();
		addListener();
		setFrame();
	}
	private void init(){
		lblIdJ = new JLabel("ID :                ");
		lblPwJ = new JLabel("Password :  ");
		lblReJ = new JLabel("Retry :            ");
		lblNameJ = new JLabel("Name :           ");
		lblNickJ = new JLabel("Nickname :     ");
		tfIdJ = new JTextField(10);
		tfPwJ = new JPasswordField(10);
		tfReJ = new JPasswordField(10);
		tfNameJ = new JTextField(10);
		tfNickJ = new JTextField(10);
		lblGender = new JLabel("Gender");
		lblMale = new JLabel("Male");
		lblFemale = new JLabel("Female");
		male = new JRadioButton();
		female = new JRadioButton();
		btnJoin = new JButton("Join");
		btnCancel = new JButton ("Cancel");
	}
	private void setDisplay(){
		ButtonGroup group = new ButtonGroup();
		group.add(male);
		group.add(female);
		
		JPanel pnlNorthJ = new JPanel(new GridLayout(5,1));
		JPanel pnlIdJ = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlIdJ.add(lblIdJ);
		JPanel pnlPwJ = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlPwJ.add(lblPwJ);
		JPanel pnlReJ = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlReJ.add(lblReJ);
		JPanel pnlNameJ = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlNameJ.add(lblNameJ);
		JPanel pnlNickJ = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlNickJ.add(lblNickJ);
		
		JPanel pnlJ1 = new JPanel();
		pnlJ1.add(pnlIdJ);
		pnlJ1.add(tfIdJ);
		JPanel pnlJ2 = new JPanel();
		pnlJ2.add(pnlPwJ);
		pnlJ2.add(tfPwJ);
		JPanel pnlJ3 = new JPanel();
		pnlJ3.add(pnlReJ);
		pnlJ3.add(tfReJ);
		JPanel pnlJ4 = new JPanel();
		pnlJ4.add(pnlNameJ);
		pnlJ4.add(tfNameJ);
		JPanel pnlJ5 = new JPanel();
		pnlJ5.add(pnlNickJ);
		pnlJ5.add(tfNickJ);
		
		pnlNorthJ.add(pnlJ1);
		pnlNorthJ.add(pnlJ2);
		pnlNorthJ.add(pnlJ3);
		pnlNorthJ.add(pnlJ4);
		pnlNorthJ.add(pnlJ5);
		
		
		JPanel pnlCenterJ = new JPanel();
		TitledBorder tBorder = new TitledBorder("Gender");
		JPanel pnlBtn1 = new JPanel();
		pnlBtn1.add(lblMale);
		pnlBtn1.add(male);
		JPanel pnlBtn2 = new JPanel();
		pnlBtn2.add(lblFemale);
		pnlBtn2.add(female);
		pnlCenterJ.setBorder(tBorder);
		pnlCenterJ.add(pnlBtn1);
		pnlCenterJ.add(pnlBtn2);
		
		JPanel pnlSouthJ = new JPanel();
		JPanel pnlbtnJPanel = new JPanel();
		pnlbtnJPanel.add(btnJoin);
		pnlbtnJPanel.add(btnCancel);
		pnlSouthJ.add(pnlbtnJPanel);
		
		add(pnlNorthJ, BorderLayout.NORTH);
		add(pnlCenterJ, BorderLayout.CENTER);
		add(pnlSouthJ, BorderLayout.SOUTH);
	}
	private void addListener(){
		btnJoin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//리스트에 정보를 넣는다.
				addData();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//창닫기
				dispose();
			}
		});
	}
	
	private void addData(){
		String id = tfIdJ.getText();
		id.trim();
		String pw = tfPwJ.getText();
		String re = tfReJ.getText();
		String name = tfNameJ.getText();
		String nick = tfNickJ.getText();
		String gender = "male";
	
		if(female.isSelected()){
			gender = "female";
		}
		if(
				id.length()==0 || 
				pw.length() == 0 || 
				name.length()== 0 ||
				nick.length() == 0 ||
				gender.length() == 0
			){
			showMessage("전부다 입력하세요");
		}else{
			if(pw.equals(re)){
				if(!owner.isExist(id)){//존재하지 않으면 데이터에 값을 넣는다
					owner.setInfo(
							id, 
							tfPwJ.getText(), 
							tfNameJ.getText(), 
							tfNickJ.getText(), 
							gender
							);
					
					dispose();
				}else{
					showMessage("이미 존재하는 ID입니다");
				}
			}else{
				showMessage("다시 똑같이 입력하세요");
			}
		}
	}
	
	private void showMessage(String message){
		JOptionPane.showConfirmDialog(this, message , "알림", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}
	private void setFrame(){
		setTitle("JoinForm");
		pack();
		setLocation(1000,500);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

}
