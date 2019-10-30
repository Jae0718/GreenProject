

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ListPanel extends JFrame {
	private JPanel pnlMain;
	private JTextField tfStart;
	private JLabel lblDay;
	private JTextField tfEnd;
	private JTextField tfTodo;
	private JTextField tfDday;
	
	public ListPanel() {
		init();
		setDisplay();
	}
	
	private void init() {
		pnlMain = new JPanel();
		tfStart = new JTextField(6);
		lblDay = new JLabel("~");
		tfEnd = new JTextField(6);
		tfTodo= new JTextField(25);
		tfDday = new JTextField(5);
	}
	
	private void setDisplay() {
		JPanel pnl1 = new JPanel(new GridLayout(0,1));
		JPanel pnl2 = new JPanel(new GridLayout(0,1));
		JPanel pnl3 = new JPanel(new GridLayout(0,1));
		JPanel pnl4 = new JPanel(new GridLayout(0,1));
		
		pnl1.add(tfStart);
		pnl2.add(tfEnd);
		pnl3.add(tfTodo);
		pnl4.add(tfDday);
		
		pnlMain.add(pnl1);
		pnlMain.add(lblDay);
		pnlMain.add(pnl2);
		pnlMain.add(pnl3);
		pnlMain.add(pnl4);
		add(pnlMain, BorderLayout.CENTER);
	}

	
	public void setTextFiled(String startDate, String endDate, String todo, int dDay){
		tfStart.setText(startDate);
		tfEnd.setText(endDate);
		tfTodo.setText(todo);
		if(dDay > 0){
			tfDday.setText(String.valueOf("+" + dDay));
		}else if(dDay<0){
			tfDday.setText(String.valueOf("-" + dDay));
		}else{
			tfDday.setText("today");
		}
		
	}
	
	public JPanel getPnlMain() {
		return pnlMain;
	}

	public void setPnlMain(JPanel pnlMain) {
		this.pnlMain = pnlMain;
	}
}
