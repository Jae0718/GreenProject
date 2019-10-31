package newSwingLotto;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


public class ReadyPanel extends JFrame {
	private final int TF_SIZE = 6;
	
	private JPanel pnlMain;
	private JButton btnExtract;
	private JRadioButton btnAuto;
	private JRadioButton btnManu;
	private JTextField[] tfArray;
	
	//생성자
	public ReadyPanel(){
		init();
		setDisplay();
	}
	
	private void init(){
		pnlMain = new JPanel();
		btnExtract = new JButton("추출");
		btnAuto = new JRadioButton("자동",true);
		btnManu = new JRadioButton("수동");
		ButtonGroup group = new ButtonGroup();
		group.add(btnAuto);
		group.add(btnManu);
		tfArray = new JTextField[TF_SIZE];
		//텍스트필드 배열에 텍스트필드 초기화
		for(int i=0; i<TF_SIZE; i++){
			tfArray[i] = new JTextField(5);
		}
	}
	
	private void setDisplay(){
		JPanel pnl1 = new JPanel();
		JPanel pnl2 = new JPanel();
		JPanel pnl3 = new JPanel();
		
		pnl1.add(btnAuto);
		pnl1.add(btnManu);
		
		pnl2.add(btnExtract);
		
		for(int i=0; i<TF_SIZE; i++){
			pnl3.add(tfArray[i]);
		}
		
		pnlMain.add(pnl1);
		pnlMain.add(pnl2);
		pnlMain.add(pnl3);
	}
	
	
	//getter&&setter
	
	public JPanel getPnlMain() {
		return pnlMain;
	}

	public void setPnlMain(JPanel pnlMain) {
		this.pnlMain = pnlMain;
	}

	public JButton getBtnExtract() {
		return btnExtract;
	}

	public void setBtnExtract(JButton btnExtract) {
		this.btnExtract = btnExtract;
	}

	public JRadioButton getBtnAuto() {
		return btnAuto;
	}

	public void setBtnAuto(JRadioButton btnAuto) {
		this.btnAuto = btnAuto;
	}

	public JRadioButton getBtnManu() {
		return btnManu;
	}

	public void setBtnManu(JRadioButton btnManu) {
		this.btnManu = btnManu;
	}

	public JTextField[] getTfArray() {
		return tfArray;
	}

	public void setTfArray(JTextField[] tfArray) {
		this.tfArray = tfArray;
	}
}
