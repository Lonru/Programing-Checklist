package Programing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class sms extends JFrame {

	private JLabel laPhone, laMessage;
	private JTextField tfPhone, tfMessage;
	Container c;
	JButton btn1;

	public sms() {
		initObject();
		initSetting();
		initBatch();

		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String phoneNum = tfPhone.getText();
				String message = tfMessage.getText();
				문자전송(phoneNum, message);
			}
		});

		setVisible(true);
	}

	private void initBatch() {
		c.add(laPhone);
		c.add(tfPhone);
		c.add(laMessage);
		c.add(tfMessage);
		c.add(btn1, BorderLayout.SOUTH);
	}

	private void initSetting() {
		c = getContentPane();
		GridLayout grid = new GridLayout(3, 2);
		grid.setVgap(5);
		c.setLayout(grid);
	}

	private void initObject() {
		setTitle("");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);

		laPhone = new JLabel("전화번호");
		laMessage = new JLabel("메시지");
		tfPhone = new JTextField("");
		tfMessage = new JTextField("");
		btn1 = new JButton("전송");
	}

	public static void 문자전송(String to, String body) {
		String api_key = "NCS6RK8PJK9AKQHL";
		String api_secret = "RQCVJUW419LZGTSBUU77YBEOPBCUOWET";
		Message coolsms = new Message(api_key, api_secret);

		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", to);
		params.put("from", "01045616988");
		params.put("type", "SMS");
		params.put("text", body);
		params.put("app_version", "test app 1.2"); // application name and version

		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		new sms();
	}
}