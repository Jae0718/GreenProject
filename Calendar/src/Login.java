
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
	Properties prop = new Properties();	
	private JLabel img;
	private JLabel lblID;
	private JLabel lblPW;
	private JTextField txtFieldID;
	private JPasswordField pwFieldPW;
	private JButton btnLogin;
	private JButton btnJoin;
	
	public Login(){
		init();
		setDisplay();
		addListener();
	}
	
	public void init() {
		img = new JLabel();
		lblID = new JLabel("ID     ");
		lblPW = new JLabel("PW  ");
		txtFieldID = new JTextField(15);
		pwFieldPW = new JPasswordField(15);
		btnLogin = new JButton("로그인");
		btnJoin = new JButton("회원가입");
	}
	
	public void setTextFiled(){
		txtFieldID.setText("");
		pwFieldPW.setText("");
	}
	
	
	public void addListener() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object src = e.getSource();
				if (src == btnLogin) {
					Properties prop = new Properties();
					FileInputStream flInStrm = null;
					ObjectInputStream objInStrm = null;
					
					String memberID = txtFieldID.getText();
					String memberPW = String.valueOf(pwFieldPW.getPassword());

					File dir = new File("Data");
					if (dir.isFile() || !dir.exists()) {
						dir.mkdir();
					}

					File propFl = new File(dir, "Memberships.properties");
					try {
						if (!propFl.exists()) {
							propFl.createNewFile();
						}
						flInStrm = new FileInputStream(propFl);
						prop.load(flInStrm);
					} catch (Exception ex) {
					} finally {
						try {
							flInStrm.close();
						} catch (Exception ex) {}
					}
					
					if (!prop.containsKey(memberID)) {	// 이 ID가 가입되어있지 않을 경우.
						showErrorMessage("존재하지 않는 ID입니다.");
					} else if (!prop.getProperty(memberID).equals(memberPW)) {	// 비밀번호가 틀릴 경우.
						showErrorMessage("비밀번호가 다릅니다.");
					} else {	// 로그인 성공.
						TodoThingsManager tdtManager = new TodoThingsManager();
						tdtManager.setTodoMap(Utils.readTodo("Data\\" + memberID + ".dat"));
						new CalendarMain(tdtManager, memberID, Login.this);
						dispose();
						// 그 계정의 파일을 기반으로 새 창을 띄우고 로그인 창 종료.
					}
				}
				else {	// src == btnJoin
					new Join(Login.this);
				}
			}
		};
		
		WindowAdapter myWindowListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				int result = JOptionPane.showConfirmDialog(
						Login.this,
						"종료하시겠습니까?",
						"종료", 
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE
					);
					if(result == JOptionPane.YES_OPTION) {
						System.exit(NORMAL);
					}
			}
		};
		
		btnLogin.addActionListener(listener);
		btnJoin.addActionListener(listener);
		addWindowListener(myWindowListener);
	}
	
	
	
	public void setDisplay() {
		JPanel pnlCenter = new JPanel(new GridLayout(2, 1)); 
		
		JPanel pnlNorth = new JPanel();
		img.setIcon(new ImageIcon("lion.png"));
		pnlNorth.add(img);
		
		JPanel pnlSouth = new JPanel();
		JPanel pnlTxtFieldID = new JPanel();
		pnlTxtFieldID.setOpaque(true);
		pnlTxtFieldID.setBackground(new Color(0xC6EFEF));
		JPanel pnlPwFieldPW = new JPanel();
		pnlPwFieldPW.setOpaque(true);
		pnlPwFieldPW.setBackground(new Color(0xC6EFEF));
		
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
		
		pnlCenter.add(pnlTxtFieldID);
		pnlCenter.add(pnlPwFieldPW);
		pnlTxtFieldID.add(lblID);
		pnlTxtFieldID.add(txtFieldID);
		pnlPwFieldPW.add(lblPW);
		pnlPwFieldPW.add(pwFieldPW);
		
		pnlSouth.add(btnLogin);
		pnlSouth.add(btnJoin);
		pnlSouth.setOpaque(true);
		pnlSouth.setBackground(new Color(0xC6EFEF));
		
		setTitle("Login");
		pack();
		setLocation(600,200);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	
	public void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(Login.this, message, "에러", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void main(String[] args) {
		new Login();
	}
	
	
	
}