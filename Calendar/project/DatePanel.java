package kr.ac.green;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class DatePanel {
	
	private Dimension pnlDateSize;
	private int index;
	
	private JPanel tempPnl;
	private JLabel pnlLblDate;
	private JLabel pnlLblTodo1;
	private JLabel pnlLblTodo2;
	private JLabel pnlLblTodo3;
	
	public DatePanel(int index){
		this.index = index;

		init();
	}
	private void init(){
		pnlDateSize = new Dimension(90,90);
		tempPnl = new JPanel(new GridLayout(4,0));
		pnlLblDate = new JLabel("",JLabel.LEFT);
        pnlLblTodo1 = new JLabel("");
        pnlLblTodo2 = new JLabel("");
        pnlLblTodo3 = new JLabel("");
        tempPnl.setPreferredSize(pnlDateSize);
        tempPnl.add(pnlLblDate);
        pnlLblDate.setText(String.valueOf(index));
        tempPnl.add(pnlLblTodo1);
        tempPnl.add(pnlLblTodo2);
        tempPnl.add(pnlLblTodo3);
        tempPnl.setBorder(new LineBorder(Color.WHITE));
	}
	public JPanel getTempPnl() {
		return tempPnl;
	}
	public void setTempPnl(JPanel tempPnl) {
		this.tempPnl = tempPnl;
	}
	public JLabel getPnlLblDate() {
		return pnlLblDate;
	}
	public void setPnlLblDate(JLabel pnlLblDate) {
		this.pnlLblDate = pnlLblDate;
	}
	public JLabel getPnlLblTodo1() {
		return pnlLblTodo1;
	}
	public void setPnlLblTodo1(JLabel pnlLblTodo1) {
		this.pnlLblTodo1 = pnlLblTodo1;
	}
	public JLabel getPnlLblTodo2() {
		return pnlLblTodo2;
	}
	public void setPnlLblTodo2(JLabel pnlLblTodo2) {
		this.pnlLblTodo2 = pnlLblTodo2;
	}
	public JLabel getPnlLblTodo3() {
		return pnlLblTodo3;
	}
	public void setPnlLblTodo3(JLabel pnlLblTodo3) {
		this.pnlLblTodo3 = pnlLblTodo3;
	}
	
	
	
}
