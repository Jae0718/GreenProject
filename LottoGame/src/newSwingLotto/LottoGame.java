package newSwingLotto;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class LottoGame extends JFrame{
	private final int LOTTO_SIZE = 6;
	
	private int input;
	private StoreLotto stLotto;
	
	private ReadyPanel[] rdPnlArr;
	private JButton btnStart;
	private JButton btnCancel;
	private JButton btnAutoExtract;
	
	public LottoGame(int input){
		setInput(input);
		init();
		addListener();
		setDisplay();
		setFrame();
	}
	private void init(){
		stLotto = new StoreLotto(input);
		rdPnlArr = new ReadyPanel[input];
		//레디패널배열에 들어갈 패널 초기화
		for(int i=0; i<input; i++){
			rdPnlArr[i] = new ReadyPanel();
			rdPnlArr[i].getPnlMain().setBorder(new TitledBorder((i+1) + "번째 게임입니다."));
		}
		btnStart = new JButton("Start");
		btnCancel = new JButton("Cancel");
		btnAutoExtract = new JButton("AutoExtract");

	}
	
	private void setDisplay(){
		//레디패널을 센터패널에 넣기
		JPanel pnlCenter = new JPanel(new GridLayout(0,1));
		for(int i=0; i<input; i++){
			pnlCenter.add(rdPnlArr[i].getPnlMain());
		}
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnStart);
		pnlSouth.add(btnCancel);
		pnlSouth.add(btnAutoExtract);
		
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}
	private void addListener(){
		//추출누르면 자동or수동 판단 -> 자동 : 랜덤숫자저장 / 수동 : pick숫자 저장
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				//눌러진 버튼의 인덱스 검색
				Object src = ae.getSource();
				int index = -1;
				for(int i=0; i<input; i++){
					if(src == rdPnlArr[i].getBtnExtract()){
						index = i;
					}
				}
				if(rdPnlArr[index].getBtnAuto().isSelected()){
					//자동이 체크되어있으면 stLotto의 자동메소드를 호출 -> lotto번호를 받아와서 tf에 추가
					stLotto.autoNumber(index);
					for(int i=0; i<LOTTO_SIZE; i++){
						String text = String.valueOf(stLotto.getLottoList().get(index)[i]);
						rdPnlArr[index].getTfArray()[i].setText(text);
					}
					
				}else if(rdPnlArr[index].getBtnManu().isSelected()){
					//수동이 체크되어있으면 stLotto의 수동메소드를 호출 -> lotto번호를 받아와서 tf에 추가
					new ManuPop(LottoGame.this,stLotto,index);
				}else{
					showMessage("올바른 접근이 아닙니다");
				}
			}
				
			
		};
		//각 추출버튼에 리스너 연결
		for(int i=0; i<input; i++){
			rdPnlArr[i].getBtnExtract().addActionListener(listener);
		}
		
		ActionListener btnListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object src = ae.getSource();
				
				if(src == btnStart){
					new Result(stLotto, input);
					
				}else if(src == btnCancel){
					dispose();
					
				}else if(src == btnAutoExtract){
					for(int j=0; j<input; j++){
						//stLotto에 있는 자동번호생성 메소드(인덱스 번호 포함) 호출
						stLotto.autoNumber(j);
						for(int k=0; k<LOTTO_SIZE; k++){
							//stLotto에서 가져온 로또리스트의 번호 하나하나를 텍스트 필드에 연결
							String text = String.valueOf(stLotto.getLottoList().get(j)[k]);
							rdPnlArr[j].getTfArray()[k].setText(text);
						}
					}
				}else{
					showMessage("잘못된 접근입니다.");
				}
				
			}
		};
		//각 버튼에 리스너 연결
		btnStart.addActionListener(btnListener);
		btnCancel.addActionListener(btnListener);
		btnAutoExtract.addActionListener(btnListener);
		
	}
	private void setFrame(){
		setTitle("LottoGame");
		pack();
		//setSize(300,130);
		setLocation(500,100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void showMessage(String message){
		JOptionPane.showConfirmDialog(this, message , "알림" ,JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}
	
	//getter&&setter
	
	public int getInput() {
		return input;
	}
	public void setInput(int input) {
		this.input = input;
	}
	public ReadyPanel[] getPnlArr() {
		return rdPnlArr;
	}
	public void setPnlArr(ReadyPanel[] pnlArr) {
		this.rdPnlArr = pnlArr;
	}
	

}
