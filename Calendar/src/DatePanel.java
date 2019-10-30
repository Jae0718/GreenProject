
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class DatePanel {
	
	private Dimension pnlDateSize;
	private int index;
	
	private JPanel tempPnl;
	private JLabel pnlLblDate;
	private JLabel [] lblArr;
	private int count = 0;
	private TodoThingsManager tdtManager;
	private CalendarMain cdMain;
	
	public DatePanel(int index,TodoThingsManager tdtManager, CalendarMain cdMain){
		this.index = index;
		this.tdtManager = tdtManager;
		this.cdMain = cdMain;
		init();
		addListener();
	}
	private void init(){
		pnlDateSize = new Dimension(90,90);
		tempPnl = new JPanel(new GridLayout(4,0));
		pnlLblDate = new JLabel("",JLabel.LEFT);
        lblArr = new JLabel[3];
        tempPnl.setPreferredSize(pnlDateSize);
        tempPnl.add(pnlLblDate);
        for(int i=0; i<3; i++){
        	lblArr[i] = new JLabel("");
        	tempPnl.add(lblArr[i]);
        }
        pnlLblDate.setText(String.valueOf(index));
        tempPnl.setBorder(new LineBorder(Color.WHITE));
	}
	
	public void setLblTextInCalendar(String todo){
		if(count == 0){
			lblArr[0].setText(todo);
			if(todo != null){
				lblArr[0].setOpaque(true);
				lblArr[0].setBackground(new Color(0x9cc2d6));
				count++;
			}
			

		}else if(count ==1){
			lblArr[1].setText(todo);
			if(todo != null){
				lblArr[1].setOpaque(true);
				lblArr[1].setBackground(new Color(0x71b4d7));
				count++;
			}

		}else if(count == 2){
			lblArr[2].setText(todo);
			if(todo != null){
				lblArr[2].setOpaque(true);
				lblArr[2].setBackground(new Color(0x1e8ec8));
				count++;
			}

		}else{

		}
	}
	
	
	private void addListener(){
		MouseListener mListener = new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {
				new Popup(pnlLblDate.getText(), tdtManager, cdMain);
			}
		};
		
		tempPnl.addMouseListener(mListener);
		
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

	
	
}
