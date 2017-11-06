package com.allinpay.id.demo.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.allinpay.id.demo.domain.ResponseData;
import com.allinpay.id.demo.main.ApiTrans;
import com.allinpay.id.demo.util.Constants;

public class InputPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputAccNo;
	private JTextField inputName;
	private JTextField inputCertificateCode;
	private JTextField inputNbr;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public InputPage() {
		setTitle("输入验证要素");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("卡号：");
		label.setBounds(46, 46, 67, 15);
		contentPane.add(label);

		inputAccNo = new JTextField();
		inputAccNo.setBounds(123, 43, 253, 21);
		contentPane.add(inputAccNo);
		inputAccNo.setColumns(10);

		if ("0405".equals(Constants.TRADE_TYPE)) {
			JLabel label_1 = new JLabel("姓名：");
			label_1.setBounds(46, 91, 67, 15);
			contentPane.add(label_1);

			inputName = new JTextField();
			inputName.setBounds(123, 88, 253, 21);
			contentPane.add(inputName);
			inputName.setColumns(10);
		} else if ("0407".equals(Constants.TRADE_TYPE)) {
			JLabel label_2 = new JLabel("身份证号：");
			label_2.setBounds(46, 137, 67, 15);
			contentPane.add(label_2);

			inputCertificateCode = new JTextField();
			inputCertificateCode.setBounds(123, 134, 253, 21);
			contentPane.add(inputCertificateCode);
			inputCertificateCode.setColumns(10);
		} else if ("0409".equals(Constants.TRADE_TYPE)) {
			JLabel label_1 = new JLabel("姓名：");
			label_1.setBounds(46, 91, 67, 15);
			contentPane.add(label_1);

			inputName = new JTextField();
			inputName.setBounds(123, 88, 253, 21);
			contentPane.add(inputName);
			inputName.setColumns(10);
			JLabel label_2 = new JLabel("身份证号：");
			label_2.setBounds(46, 137, 67, 15);
			contentPane.add(label_2);

			inputCertificateCode = new JTextField();
			inputCertificateCode.setBounds(123, 134, 253, 21);
			contentPane.add(inputCertificateCode);
			inputCertificateCode.setColumns(10);
		} else if ("0411".equals(Constants.TRADE_TYPE)) {
			JLabel label_1 = new JLabel("姓名：");
			label_1.setBounds(46, 91, 67, 15);
			contentPane.add(label_1);

			inputName = new JTextField();
			inputName.setBounds(123, 88, 253, 21);
			contentPane.add(inputName);
			inputName.setColumns(10);
			JLabel label_2 = new JLabel("身份证号：");
			label_2.setBounds(46, 137, 67, 15);
			contentPane.add(label_2);

			inputCertificateCode = new JTextField();
			inputCertificateCode.setBounds(123, 134, 253, 21);
			contentPane.add(inputCertificateCode);
			inputCertificateCode.setColumns(10);
			JLabel label_3 = new JLabel("手机号：");
			label_3.setBounds(46, 179, 54, 15);
			contentPane.add(label_3);

			inputNbr = new JTextField();
			inputNbr.setBounds(123, 176, 253, 21);
			contentPane.add(inputNbr);
			inputNbr.setColumns(10);
		}

		JLabel lbltj = new JLabel("用户：" + Constants.USER_ID);
		lbltj.setBounds(46, 10, 127, 15);
		contentPane.add(lbltj);

		JLabel lbltj_1 = new JLabel("渠道：" + Constants.CHANNEL_ID);
		lbltj_1.setBounds(255, 10, 127, 15);
		contentPane.add(lbltj_1);

		JButton doTrans = new JButton("验证");
		doTrans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accNo = inputAccNo.getText();
				String name = null;
				String certificateCode = null;
				String nbr = null;
				String params = null;
				if (null != inputName) {
					name = inputName.getText();
				}
				if (null != inputCertificateCode) {
					certificateCode = inputCertificateCode.getText();
				}

				if (null != inputNbr) {
					nbr = inputNbr.getText();
				}

				ResponseData response = null;
				Map<String, String> map = new HashMap<String, String>();
				map.put("nbr", nbr);
				map.put("accNo", accNo);
				map.put("certificateCode", certificateCode);
				map.put("name", name);
				map.put("params", params);
				try {
					response = ApiTrans.doTrans(map);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				ResultPage result = null;
				try {
					result = new ResultPage(response);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				result.setVisible(true);
				setVisible(false);
			}
		});
		doTrans.setBounds(101, 248, 93, 23);
		contentPane.add(doTrans);

		JButton doBack = new JButton("返回");
		doBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomePage welcome = new WelcomePage();
				welcome.setVisible(true);
				setVisible(false);
			}
		});
		doBack.setBounds(282, 248, 93, 23);
		contentPane.add(doBack);
	}
}
