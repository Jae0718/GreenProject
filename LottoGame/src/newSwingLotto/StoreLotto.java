package newSwingLotto;

import java.awt.Container;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;


public class StoreLotto{
	//번호를 입력받은 값만큼 만들어서 저장하고 있다.
	private final int LOTTO_SIZE = 6;
	
	private int input;
	//로또리스트는 자동 or수동으로 만든 번호들을 저장하고 있는 벡터 
	//이때 자동 or수동으로 만들어진 번호는 배열(Integer[])형태로 저장
	private Vector<Integer[]> lottoList;
	
	public StoreLotto(int input){
		setInput(input);
		init();
	}
	
	private void init(){
		 lottoList = new Vector<Integer[]>();
		 
		 while(lottoList.size() != input){
			 lottoList.add(new Integer[LOTTO_SIZE]);
		 }

	}
	//자동 선택시 실행되는 메소드(인덱스 포함)
	public void autoNumber(int index){
		Integer[] lotto = new Integer[LOTTO_SIZE];
		lotto = makeNumber();
		lottoList.set(index,lotto);
	}

	//랜덤한 숫자 만들어 주는 메소드 : 반환값 : Integer[]
	public Integer[] makeNumber(){
		Set<Integer> lottoSet = new HashSet<Integer>();//중복없이 숫자 나열
		Integer[] lottoTemp = new Integer[ LOTTO_SIZE];
		
		Random r = new Random();
		while(lottoSet.size() !=  LOTTO_SIZE){
			lottoSet.add( r.nextInt(45)+1);
		}
		
		lottoTemp = lottoSet.toArray(new Integer[0]);//List를 Array로 변환
		Arrays.sort(lottoTemp);//정렬

		return lottoTemp;
	}
	
	//getter&&setter

	public int getInput() {
		return input;
	}

	public void setInput(int input) {
		this.input = input;
	}
	public Vector<Integer[]> getLottoList() {
		return lottoList;
	}
	public void setLottoList(Vector<Integer[]> lottoList) {
		this.lottoList = lottoList;
	}

//	public Vector<Integer> getCountCorrect() {
//		return countCorrect;
//	}
//
//	public void setCountCorrect(Vector<Integer> countCorrect) {
//		this.countCorrect = countCorrect;
//	}
	
	
}
