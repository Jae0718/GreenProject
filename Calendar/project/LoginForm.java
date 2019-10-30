package kr.ac.green;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame {
	
	private JLabel lblId;
	private JLabel lblPw;
	private JButton btnLogin;
	private JButton btnJoin;
	//회원가입시 입력한 정보를 담을 데이터클래스 리스트
	protected Vector<Data> list;
	private File infoFile;
	
	
	private JTextField tfPw;
	
	public LoginForm(){
		loadList();
		init();
		setDisplay();
		addListener();
		setFrame();
		saveList();
	}
	private void init(){
		lblId = new JLabel("ID :                ");
		lblPw = new JLabel("Password : ");
		tfId = new JTextField(10);
		tfPw = new JPasswordField(10);
		btnLogin = new JButton("Login");
		btnJoin = new JButton("Join");
		list = new Vector<Data>();
		
	}
	private void setDisplay(){
		JPanel pnlCenter = new JPanel(new GridLayout(2,1));
		JPanel pnl1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl1.add(lblId);
		pnl1.add(tfId);
		JPanel pnl2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl2.add(lblPw);
		pnl2.add(tfPw);
		pnlCenter.add(pnl1);
		pnlCenter.add(pnl2);
		
		JPanel pnlSouth = new JPanel();
		JPanel pnl3 = new JPanel();
		pnl3.add(btnLogin);
		pnl3.add(btnJoin);
		pnlSouth.add(pnl3);
		
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}

	private void addListener(){
		//login누르면 information
		//join 누르면 joinform
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//아이디와 비밀번호가 데이터내용과 일치하는지 확인
				if(isCorrect()){
					new Information(LoginForm.this);
					tfId.setText("");
					tfPw.setText("");
				}else{
					showMessage("그런사람 없습니다");
				}
			}
		});
		
		btnJoin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new JoinForm(LoginForm.this);
			}
		});
	}
	
	private void saveList(){
		//파일 불러와서 리스트에 있는 내용 쓰기
		infoFile = new File("c:\\temp\\infoFile.txt");
		
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try{
			fw = new FileWriter(infoFile,true);
			pw = new PrintWriter(fw);
			
			for(Data temp : list){
				pw.println("ID:" + temp.getId());
				pw.println("PW:" + temp.getPw());
				pw.println("Name:" + temp.getName());
				pw.println("Nick:" + temp.getNick());
				pw.println("Gender:" + temp.getGender());
			}
			
			pw.flush();
			
		}catch(IOException e){
			
		}finally{
			try{
				pw.close();
				fw.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private void loadList(){
		//파일에 있는 내용 리스트로 가져오기
		
		
	}
	public void setInfo(String id, String pw, String name, String nick, String gender){
		list.add(new Data(id,pw,name,nick,gender));
		
		for(int i=0; i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	public int searchId(){
		for(int i=0;i<list.size();i++){
			//해당하는 list를 찾는다
			if(tfId.getText().equals(list.get(i).getId())){
				return i;
			}
		}
		return -1;
	}
	private boolean isCorrect(){
		int num = searchId();
		if(num == -1){//아예 없는경우
			return false;
		}
		return list.get(num).getPw().equals(tfPw.getText());
	}
	
	public void removeInfo(int num){
		list.remove(num);
	}
	
	public boolean isExist(String id){
		//id중복확인
		for(int i=0; i<list.size() ;i++){
			if(list.get(i).getId().equals(id)){
				return true;
			}
		}
		return false;
	}
	
	private void showMessage(String message){
		JOptionPane.showConfirmDialog(this, message , "알림", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void setFrame(){
		setTitle("LoginForm");
		pack();
		setLocation(1000,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		new LoginForm();
	}

	public JLabel getLblId() {
		return lblId;
	}
	public void setLblId(JLabel lblId) {
		this.lblId = lblId;
	}
	private JTextField tfId;
	public JTextField getTfId() {
		return tfId;
	}
	public void setTfId(JTextField tfId) {
		this.tfId = tfId;
	}
	public JTextField getTfPw() {
		return tfPw;
	}
	public void setTfPw(JTextField tfPw) {
		this.tfPw = tfPw;
	}
}
