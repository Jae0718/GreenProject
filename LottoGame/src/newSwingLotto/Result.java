package newSwingLotto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Result extends JFrame{
	private final int LOTTO_SIZE = 6;
	
	private int input;
	private StoreLotto stLotto;

	private Integer[] winNum;
	private int bonusNum;
	
	private ShowPanel[] showPnlArr;
	private JTextField[] tfWinNum;
	private JTextField tfBonusNum;
	private JButton btnClose;
	private JButton btnTrace;
	
	public Result(StoreLotto stLotto, int input){
		setInput(input);
		setStLotto(stLotto);
		init();
		makeWinNum();
		addListener();
		setDisplay();
		setFrame();
	}
	private void init(){
		winNum = new Integer[LOTTO_SIZE];
		
		tfWinNum = new JTextField[6];
		tfBonusNum = new JTextField();
		showPnlArr = new ShowPanel[input];
		btnClose = new JButton("Close");
		btnTrace = new JButton("Trace");
	}
	
	private void makeWinNum(){
		//랜덤으로 번호만들기
		winNum = stLotto.makeNumber();
		
		Random r = new Random();
		bonusNum = r.nextInt(45)+1;
		//보너스 번호가 중복일때 다시 설정후 처음부터 다시 중복검사
		for(int i=0; i<LOTTO_SIZE; i++){
			while(winNum[i] == bonusNum){
				bonusNum = r.nextInt(45)+1;
				i=0;
			}
		}
	}
	
	private void addListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent qe) {
				Object src = qe.getSource();
				if(src == btnTrace){
					traceSecond();
				}else if(src == btnClose){
					dispose();
				}else{
					showMessage("잘못된 접근입니다");
				}
				
			}
		};
		
		btnTrace.addActionListener(listener);
		btnClose.addActionListener(listener);
		
	}
	private void setDisplay(){
		JPanel pnlNorth = new JPanel();
		JLabel lblNorth = new JLabel("Result", JLabel.CENTER);
		lblNorth.setFont(new Font(Font.DIALOG, Font.BOLD, 77));
		pnlNorth.add(lblNorth);

		
		JPanel pnlCenter = new JPanel(new GridLayout(0,1));
		//pnlInfo -> 당첨번호 패널
		JPanel pnlInfo = new JPanel();
		JPanel pnlInfo1 = new JPanel();
		pnlInfo1.setBorder(new TitledBorder("당첨번호"));
		for(int i=0; i<6; i++){
			tfWinNum[i] = new JTextField();
			tfWinNum[i].setEditable(false);
			tfWinNum[i].setHorizontalAlignment(JTextField.CENTER);
			tfWinNum[i].setOpaque(true);
			tfWinNum[i].setBackground(Color.YELLOW);
			tfWinNum[i].setPreferredSize(new Dimension(60,55));
			tfWinNum[i].setFont(new Font(Font.DIALOG, Font.BOLD, 45));
			tfWinNum[i].setText(String.valueOf(winNum[i]));
			pnlInfo1.add(tfWinNum[i]);
		}
		
		JPanel pnlInfo2 = new JPanel();
		tfBonusNum.setHorizontalAlignment(JTextField.CENTER);
		tfBonusNum.setOpaque(true);
		tfBonusNum.setBackground(Color.RED);
		tfBonusNum.setPreferredSize(new Dimension(60,55));
		tfBonusNum.setFont(new Font(Font.DIALOG, Font.BOLD, 45));
		tfBonusNum.setText(String.valueOf(bonusNum));
		pnlInfo2.add(tfBonusNum);
		pnlInfo2.setBorder(new TitledBorder("보너스"));
		pnlInfo.add(pnlInfo1);
		pnlInfo.add(pnlInfo2);
		pnlCenter.add(pnlInfo);
		
		
		for(int i=0; i<input; i++){
			Vector<Integer> correctNumList = new Vector<Integer>();
			correctNumList = compareNum(i);
			//input개 만큼 뽑은 번호를 알리는 패널 만들기
			showPnlArr[i] = new ShowPanel(correctNumList.size(), i);
			
			//stLotto에 저장되어있는 로또 번호를 순서대로 텍스트필드에 넣기
			for(int j=0; j<LOTTO_SIZE; j++){
				String text = String.valueOf(stLotto.getLottoList().get(i)[j]);
				showPnlArr[i].getTfArr()[j].setText(text);
			}
			pnlCenter.add(showPnlArr[i].getPnlMain());
			
			//레이블 사이즈만큼 돌려서 레이블 셋텍스트
			for(int j=0;j<correctNumList.size(); j++){
				String text = String.valueOf(correctNumList.get(j));
				showPnlArr[i].getLblVector().get(j).setHorizontalAlignment(JLabel.CENTER);
				showPnlArr[i].getLblVector().get(j).setText(text);
			}
			
			showResult(i,correctNumList);
		}
		
		//패널에 일치하는 숫자를 넘겨주기
		add(pnlCenter, BorderLayout.CENTER);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnClose);
		pnlSouth.add(btnTrace);
		
		JScrollPane scroll = new JScrollPane(pnlCenter);
		add(pnlNorth, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}
	
	private void showResult(int index, Vector<Integer> correctNumList){
		boolean isBonus = false;
		//먼저 보너스 검색
		for(int i=0; i<LOTTO_SIZE;i++){
			if(bonusNum == stLotto.getLottoList().get(index)[i]){
				isBonus = true;
			}
		}
		//보너스일때 아닐때로 구분해서 결과출력
		if(isBonus){
			if(correctNumList.size()==5){
				showPnlArr[index].getLblResult().setText("2등");
			}else if(correctNumList.size()==4){
				showPnlArr[index].getLblResult().setText("4등");
			}else if(correctNumList.size()==3){
				showPnlArr[index].getLblResult().setText("5등");
			}else{
				showPnlArr[index].getLblResult().setText("꽝");
			}
		}else{
			if(correctNumList.size()==6){
				showPnlArr[index].getLblResult().setText("1등");
			}else if(correctNumList.size()==5){
				showPnlArr[index].getLblResult().setText("3등");
			}else if(correctNumList.size()==4){
				showPnlArr[index].getLblResult().setText("4등");
			}else if(correctNumList.size()==3){
				showPnlArr[index].getLblResult().setText("5등");
			}else{
				showPnlArr[index].getLblResult().setText("꽝");
			}
		}
	}
	
	private Vector<Integer> compareNum(int index){
		//당첨번호랑 비교해서 일치하면 lblArr에 출력
		Vector<Integer> correctNumList = new Vector<Integer>();

		for(int i=0; i<LOTTO_SIZE;i++){
			if(winNum[i] == stLotto.getLottoList().get(index)[i]){
				correctNumList.add(winNum[i]);
			}
		}
		return correctNumList;
	}
	
	private void traceSecond(){
		//무한으로 당첨번호를 생성해서 
		//내 번호중 하나가 2등이상이 될때까지 생성
		
		Integer[] winLotto = new Integer[LOTTO_SIZE];
		
		boolean isBonus = false;
		boolean flag = false;
		int countTemp = 0;
		
		while(!flag){
			//당첨번호 만들기
			winLotto = stLotto.makeNumber();
			Random r = new Random();
			int winBonus = r.nextInt(45)+1;
			for(int i=0; i<LOTTO_SIZE; i++){
				while(winLotto[i] == winBonus){
					winBonus = r.nextInt(45)+1;
					i=0;
				}
			}
			
			//input번호 개수 만큼 
			for(int i=0; i<input; i++){
				int countCorrect = 0;
				//각로또 번호를 돌면서 당첨번호랑 보너스번호랑 일치하는지 체크
				for(int j=0; j<LOTTO_SIZE; j++){
					if(stLotto.getLottoList().get(i)[j] == winBonus){
						isBonus = true;
					}
				}
				
				for(int j=0; j<LOTTO_SIZE; j++){
					//일치하는 개수 카운트
					for(int k=0; k<LOTTO_SIZE; k++){
						if(stLotto.getLottoList().get(i)[j] == winLotto[k]){
							countCorrect ++;
						}
					}
					countTemp++;	
				}
				
	
				if(countCorrect == 6){
					flag = true;
					String str = String.format("%,d" , countTemp );
					showMessage(str + "번 시도끝에" + (i+1) +"번째 게임이1등 당첨입니다.");
					isBonus = false;
					
				}else if(isBonus){
					if(countCorrect == 5){
						flag = true;
						String str = String.format("%,d" , countTemp );
						showMessage(str + "번 시도끝에" + (i+1) +"번째 게임이2등 당첨입니다.");
					}
				}
				countCorrect = 0;
				isBonus = false;
					
			}//input 포문의 끝

		
		}//while문의 끝
		
	}

	private void setFrame(){
		setTitle("Result");
		setSize(550,850);
		setLocation(500,100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
	public StoreLotto getStLotto() {
		return stLotto;
	}
	public void setStLotto(StoreLotto stLotto) {
		this.stLotto = stLotto;
	}
	
	
}
