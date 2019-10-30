
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Join extends JDialog {
	private JLabel lblID;
	private JLabel lblPW;
	private JLabel lblPW_Check;
	private JTextField txtFieldID;
	private JPasswordField pwFieldPW;
	private JPasswordField pwFieldPW_Check;	
	private JButton btnJoin;
	
	private Dimension lblSize = new Dimension(100, 18);
	
	public Join(JFrame owner) {
		super(owner, "ȸ������" , true);
		init();
		setDisplay();
		addListener();
		showFrame();
	}
	
	public void init() {
		lblID = new JLabel("ID");
		lblID.setFont(new Font(Font.DIALOG, Font.BOLD,15));
		lblID.setForeground(Color.GRAY);
		lblPW = new JLabel("PW");
		lblPW.setFont(new Font(Font.DIALOG, Font.BOLD,15));
		lblPW.setForeground(Color.GRAY);
		lblPW_Check = new JLabel("PW Ȯ��");
		lblPW_Check.setFont(new Font(Font.DIALOG, Font.BOLD,15));
		lblPW_Check.setForeground(Color.GRAY);
		txtFieldID = new JTextField(12);
		pwFieldPW = new JPasswordField(12);
		pwFieldPW_Check = new JPasswordField(12);
		btnJoin = new JButton("ȸ������");
		
		lblID.setPreferredSize(lblSize);
		lblPW.setPreferredSize(lblSize);
		lblPW_Check.setPreferredSize(lblSize);
		
	}
	
	public void setDisplay() {
		JPanel pnlCenter = new JPanel(new GridLayout(3, 1)); 
		JPanel pnlCenter1 = new JPanel();
		pnlCenter1.setOpaque(true);
		pnlCenter1.setBackground(new Color(0xC6EFEF));
		JPanel pnlCenter2 = new JPanel();
		pnlCenter2.setOpaque(true);
		pnlCenter2.setBackground(new Color(0xC6EFEF));
		JPanel pnlCenter3 = new JPanel();
		pnlCenter3.setOpaque(true);
		pnlCenter3.setBackground(new Color(0xC6EFEF));
		JPanel pnlSouth = new JPanel();
		pnlSouth.setOpaque(true);
		pnlSouth.setBackground(new Color(0xC6EFEF));

		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
		
		pnlCenter.add(pnlCenter1);
		pnlCenter.add(pnlCenter2);
		pnlCenter.add(pnlCenter3);
		pnlCenter1.add(lblID);
		pnlCenter1.add(txtFieldID);
		pnlCenter2.add(lblPW);
		pnlCenter2.add(pwFieldPW);
		pnlCenter3.add(lblPW_Check);
		pnlCenter3.add(pwFieldPW_Check);

		pnlSouth.add(btnJoin);
	}
// ========================================
	
	public void addListener() {
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Properties prop = new Properties();
				FileInputStream flInStrm = null;
				FileOutputStream flOutStrm = null;
				
				String memberID = txtFieldID.getText();
				String memberPW = String.valueOf(pwFieldPW.getPassword());
				String memberPW_Check = String.valueOf(pwFieldPW_Check.getPassword());
				
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
				
				if (prop.containsKey(memberID)) {	// ID(��, prop ������ key��)�� �ߺ��� ���.
					showDlgMessage("�ߺ��� ID�Դϴ�.", JOptionPane.ERROR_MESSAGE);
				} else if (!memberPW.equals(memberPW_Check)) {	// PW�� PWȮ���� �ٸ� ���.
					showDlgMessage("��й�ȣ�� Ȯ���ϼ���.", JOptionPane.ERROR_MESSAGE);
				} else {	// ����, ȸ������ ���� ����.
					try {
						prop.setProperty(memberID, memberPW);
						flOutStrm = new FileOutputStream(propFl);
						prop.store(flOutStrm, memberID + "�� ȸ������");
							// Memberships.properties�� ID�� PW ���.
						TodoThingsManager tdtMngr = new TodoThingsManager();
						Utils.writeTodo(tdtMngr.getTodoMap(), "Data\\" + memberID + ".dat");
							// �� ȸ���� TodoThingsManager ��ü ����(���ϸ� = ID) ����.
						showDlgMessage("������ �Ϸ�Ǿ����ϴ�.", JOptionPane.PLAIN_MESSAGE);
						dispose();
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						try {
							flOutStrm.close();
						} catch (Exception ex) {}
					}
				}
			}
		}
		);
	}
	
	public void showDlgMessage(String message, int type) {
		JOptionPane.showMessageDialog(Join.this, message, null, type);
	}
		
// ========================================	
	
	
	public void showFrame() {
		setSize(300, 200);
		setTitle("ȸ������");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	
	
	
	
}
