package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class Information extends JDialog {
	private JButton btnLogout;
	private JButton btnWithdraw;
	private JTextArea ta;
	private int num;
	
	//부모객체 생성
	private LoginForm owner;
	
	public Information(LoginForm owner){
		this.owner = owner;
		init();
		setDisplay();
		printData();
		addListener();
		setFrame();
	}
	private void init(){
		btnLogout = new JButton("Logout");
		btnWithdraw = new JButton ("Withdraw");
		ta = new JTextArea(10,30);
	}
	private void setDisplay(){
		JPanel pnlCenter = new JPanel();
		TitledBorder tborder = new TitledBorder("Ceck your Information");
		pnlCenter.setBorder(tborder);
		pnlCenter.add(ta);
		
		JPanel pnlSouth = new JPanel();
		JPanel pnlBtn = new JPanel();
		pnlBtn.add(btnLogout);
		pnlBtn.add(btnWithdraw);
		pnlSouth.add(pnlBtn);
		
		add(pnlCenter,BorderLayout.CENTER);
		add(pnlSouth,BorderLayout.SOUTH);
	}
	private void printData(){
		num = owner.searchId();
		ta.append(owner.list.get(num).toString());
	}
	private void addListener(){
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnWithdraw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//리스트에서 해당 정보삭제
				owner.removeInfo(num);
			}
		});
	}
	private void setFrame(){
		setTitle("Information");
		pack();
		setLocation(1000,500);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

}
