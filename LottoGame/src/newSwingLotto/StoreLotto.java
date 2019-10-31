package newSwingLotto;

import java.awt.Container;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;


public class StoreLotto{
	//��ȣ�� �Է¹��� ����ŭ ���� �����ϰ� �ִ�.
	private final int LOTTO_SIZE = 6;
	
	private int input;
	//�ζǸ���Ʈ�� �ڵ� or�������� ���� ��ȣ���� �����ϰ� �ִ� ���� 
	//�̶� �ڵ� or�������� ������� ��ȣ�� �迭(Integer[])���·� ����
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
	//�ڵ� ���ý� ����Ǵ� �޼ҵ�(�ε��� ����)
	public void autoNumber(int index){
		Integer[] lotto = new Integer[LOTTO_SIZE];
		lotto = makeNumber();
		lottoList.set(index,lotto);
	}

	//������ ���� ����� �ִ� �޼ҵ� : ��ȯ�� : Integer[]
	public Integer[] makeNumber(){
		Set<Integer> lottoSet = new HashSet<Integer>();//�ߺ����� ���� ����
		Integer[] lottoTemp = new Integer[ LOTTO_SIZE];
		
		Random r = new Random();
		while(lottoSet.size() !=  LOTTO_SIZE){
			lottoSet.add( r.nextInt(45)+1);
		}
		
		lottoTemp = lottoSet.toArray(new Integer[0]);//List�� Array�� ��ȯ
		Arrays.sort(lottoTemp);//����

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
