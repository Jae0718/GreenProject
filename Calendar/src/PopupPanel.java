
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopupPanel extends JFrame {
	private JPanel pnlMain;
	private JCheckBox cbCheck;
	private JLabel lblCheck;
	private Popup popup;
	private CalendarMain cdMain;
	private int index;

	public PopupPanel(int index, Popup popup, CalendarMain cdMain) {
		this.popup = popup;
		this.index = index;
		this.cdMain = cdMain;
		init();
		setDisplay();
		addListener();
	}
	private void init() {
		pnlMain = new JPanel();
		cbCheck = new JCheckBox();
		lblCheck = new JLabel();
		lblCheck.setPreferredSize(new Dimension(220,25));	
	}
	private void setDisplay() {
		pnlMain.add(cbCheck);
		pnlMain.add(lblCheck);
		//pnlMain.setOpaque(true);
		//pnlMain.setBackground(new Color(0x9cc2d6));
		add(pnlMain, BorderLayout.CENTER);
	}
	
	public void setLabelText(String todo){
		lblCheck.setText(todo);
	}

	
	public void  setCheckBox(boolean isDone){
		if(isDone){//안했을때
			if(cbCheck.isSelected()){//체크박스가 선택되어있다면
				cbCheck.setSelected(false);
			}
		}else{//했을때
			cbCheck.setSelected(true);
		}
	}
	
	
	private void addListener(){
		lblCheck.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent me) {
				//레이블이 클릭되면 PopupEdit표시
				new PopupEdit(index, popup,cdMain);
			}
		});		
		
	}
	
	public JPanel getPnlMain() {
		return pnlMain;
	}
	public void setPnlMain(JPanel pnlMain) {
		this.pnlMain = pnlMain;
	}
	public JLabel getLblCheck() {
		return lblCheck;
	}
	public void setLblCheck(JLabel lblCheck) {
		this.lblCheck = lblCheck;
	}
	
	
}