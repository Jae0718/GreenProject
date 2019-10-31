package newSwingLotto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RepaintManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ShowPanel {
	private final int TF_SIZE = 6;

	private int correctCount;
	private int index;
	private JPanel pnlMain;
	private JTextField[] tfArr;
	private Vector<JLabel> lblVector;
	private JLabel lblResult;
	
	private Dimension showSize;
	
	
	public ShowPanel(int correctCount, int index){
		this.correctCount = correctCount;
		this.index = index;
		init();
		setDisplay();
	}
	private void init(){
		pnlMain = new JPanel(new BorderLayout());
		pnlMain.setPreferredSize(new Dimension(400,70));
		tfArr = new JTextField[TF_SIZE ];
		showSize = new Dimension(40,20);
		for(int i=0; i<TF_SIZE; i++){
			tfArr[i] = new JTextField();
			tfArr[i].setHorizontalAlignment(JTextField.CENTER);
			tfArr[i].setPreferredSize(showSize);
		}
		lblVector = new Vector<JLabel>();
		lblResult = new JLabel("",JLabel.CENTER);
	}
	private void setDisplay(){
		pnlMain.setBorder(new TitledBorder((index+1) + "번째 게임결과"));
		
		JPanel mainCenter = new JPanel(new GridLayout(2,1));
		JPanel pnl1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnl2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JLabel lblSelected = new JLabel("선택번호 : ");
		pnl1.add(lblSelected);
		for(int i=0 ;i<TF_SIZE; i++){
			pnl1.add(tfArr[i]);
		}
		
		JLabel lblCorrect = new JLabel("일치번호 : ");
		pnl2.add(lblCorrect);
		
		//레이블 만들기
		for(int i=0; i<correctCount; i++){
			lblVector.add(new JLabel());
			lblVector.get(i).setOpaque(true);
			lblVector.get(i).setBackground(Color.YELLOW);
			lblVector.get(i).setPreferredSize(showSize);
			lblVector.get(i).setBorder(new LineBorder(Color.GRAY, 1));
			pnl2.add(lblVector.get(i));
		}
		
		mainCenter.add(pnl1);
		mainCenter.add(pnl2);
		
		JPanel mainEast = new JPanel();
		lblResult.setBorder(new LineBorder(Color.GRAY,1));
		lblResult.setOpaque(true);
		lblResult.setBackground(Color.PINK);
		lblResult.setPreferredSize(new Dimension(70,55));
		lblResult.setFont(new Font(Font.DIALOG, Font.BOLD, 32));
		mainEast.add(lblResult);
		
		JPanel pnlMain1 = new JPanel();
		pnlMain1.add(mainCenter);
		pnlMain1.add(mainEast);
		pnlMain.add(pnlMain1, BorderLayout.CENTER);

		
	}
	
	
	//getter&&setter
	
	public JPanel getPnlMain() {
		return pnlMain;
	}
	public void setPnlMain(JPanel pnlMain) {
		this.pnlMain = pnlMain;
	}
	public JTextField[] getTfArr() {
		return tfArr;
	}
	public void setTfArr(JTextField[] tfArr) {
		this.tfArr = tfArr;
	}
	public Vector<JLabel> getLblVector() {
		return lblVector;
	}
	public void setLblVector(Vector<JLabel> lblVector) {
		this.lblVector = lblVector;
	}
	public JLabel getLblResult() {
		return lblResult;
	}
	public void setLblResult(JLabel lblResult) {
		this.lblResult = lblResult;
	}
	
	
}




















