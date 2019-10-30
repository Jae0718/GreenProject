

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NoticePanel extends JFrame {
	private JTextField tfList;
	private JPanel pnlCenter;
	
	public NoticePanel() {		
		init();
		setDisplay();
	}
	private void init() {
		tfList = new JTextField(30);
		tfList.setEditable(false);
		pnlCenter = new JPanel();
		
	}
	
	public void setTextFiled(String str){
		tfList.setText(str);
	}
	
	
	private void setDisplay() {
		pnlCenter.add(tfList);
	}
	public JPanel getPnlCenter() {
		return pnlCenter;
	}
	public void setPnlCenter(JPanel pnlCenter) {
		this.pnlCenter = pnlCenter;
	}
	public JTextField getTfList() {
		return tfList;
	}
	public void setTfList(JTextField tfList) {
		this.tfList = tfList;
	}


}
