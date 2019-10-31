import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class OneOnOneChatPanel extends JFrame implements Protocol {
	private JTextArea chatArea;
	private JTextField chatField;
	private Client client;
	private User user;
	private User targetUser;

	/*
	 * 190704 ÀÌÀç¿µ Ãß°¡ ¸â¹öº¯¼ö Ãß°¡
	 */
	private JLabel lblInfo;
	private JButton btnSend;
	private JButton btnShake;

	public OneOnOneChatPanel(Client client, User user, User targetUser) {
		this.client = client;
		this.user = user;
		this.targetUser = targetUser;
		init();
		setDisplay();
		addListeners();
		showFrame();
	}

	private void init() {
		chatArea = new JTextArea(21, 43);
		chatArea.setEnabled(false);
		chatArea.setLineWrap(true);
		chatField = new JTextField(43);

		lblInfo = new JLabel(targetUser.getId() + " ´Ô°ú 1:1 ´ëÈ­Áß");
		btnSend = new JButton("Àü¼Û");
		btnShake = new JButton("ÇÑ¹ø Èçµé¾î º¼¶ó¿¹");
	}

	private void setDisplay() {
		JScrollPane scroll = new JScrollPane(chatArea);
		chatArea.setEditable(false);

		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JPanel pnlSouth = new JPanel();
		pnlSouth.add(chatField);
		pnlSouth.add(btnSend);
		pnlSouth.add(btnShake);

		add(lblInfo, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}

	private void addListeners() {

		ActionListener aListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!chatField.getText().equals(null)) {
					chatArea.append(user + " : " + chatField.getText() + "\n");
					String msg = chatField.getText();
					client.sendData(new SendData(SEND_ONEONONE_MSG, user,
							targetUser, msg));
					chatField.setText("");
				}
			}
		};

		chatField.addActionListener(aListener);
		btnSend.addActionListener(aListener);

		WindowListener listener = new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent e) {
				client.sendData(new SendData(ONEONONECHAT_OUT, user, targetUser));
			}
		};
		addWindowListener(listener);

		btnShake.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				client.sendData(new SendData(SHAKE_IT_BABY_ONE_ON_ONE_PLEASE,
						targetUser.getId(), user.getId()));
				shakeItDogBaby(user.getId());
			}
		});
	}

	public void shakeItDogBaby(String id) {
		chatArea.append(id + "´ÔÀÌ Èçµé¾î Á¦²¸ ¹ö·È½´ ~\n");
		btnSend.setEnabled(false);
		chatField.setEnabled(false);
		int count = 0;
		Point tempPoint = getLocation();
		while (count != 500) {
			setLocation((int) tempPoint.getX() + 3, (int) tempPoint.getY() + 3);
			pack();
			setLocation((int) tempPoint.getX(), (int) tempPoint.getY());
			pack();
			count++;
		}
		
		btnSend.setEnabled(true);
		chatField.setEnabled(true);
		revalidate();
		repaint();
		

	}

	public JTextField getChatField() {
		return chatField;
	}
	

	public void setChatField(JTextField chatField) {
		this.chatField = chatField;
	}

	public JTextArea getChatArea() {
		return chatArea;
	}

	public void setChatArea(JTextArea chatArea) {
		this.chatArea = chatArea;
	}

	private void showFrame() {
		setTitle(targetUser + "´Ô°ú 1:1 ´ëÈ­ Áß");
		pack();
		setLocation(100, 100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		chatField.requestFocus();

	}

}
