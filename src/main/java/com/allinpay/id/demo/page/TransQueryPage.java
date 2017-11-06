package com.allinpay.id.demo.page;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.allinpay.id.demo.domain.ResponseData;
import com.allinpay.id.demo.main.QueryTrans;
import com.allinpay.id.demo.util.Constants;

public class TransQueryPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputUserId;
	private JTextField inputOrderId;
	private JTextField inputTimeStamp;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public TransQueryPage() {
		setTitle("输入交易查询要素");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbltj = new JLabel("用户号：" + Constants.USER_ID);
		lbltj.setBounds(25, 10, 142, 15);
		contentPane.add(lbltj);
		
		JLabel lbltj_1 = new JLabel("渠道号：" + Constants.CHANNEL_ID);
		lbltj_1.setBounds(254, 10, 159, 15);
		contentPane.add(lbltj_1);
		
		JLabel lblNewLabel = new JLabel("交易用户ID：");
		lblNewLabel.setBounds(54, 58, 93, 15);
		contentPane.add(lblNewLabel);
		
		inputUserId = new JTextField();
		inputUserId.setBounds(175, 55, 204, 21);
		contentPane.add(inputUserId);
		inputUserId.setColumns(10);
		
		JLabel label = new JLabel("交易订单号：");
		label.setBounds(54, 106, 93, 15);
		contentPane.add(label);
		
		inputOrderId = new JTextField();
		inputOrderId.setBounds(175, 103, 204, 21);
		contentPane.add(inputOrderId);
		inputOrderId.setColumns(10);
		
		JLabel lblyyyymmddhhmmss = new JLabel("交易时间：");
		lblyyyymmddhhmmss.setBounds(54, 153, 93, 21);
		contentPane.add(lblyyyymmddhhmmss);
		
		inputTimeStamp = new JTextField();
		inputTimeStamp.setBounds(177, 153, 202, 21);
		contentPane.add(inputTimeStamp);
		inputTimeStamp.setColumns(10);
		
		JButton doQuery = new JButton("查询");
		doQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userId = inputUserId.getText();
				String orderId = inputOrderId.getText();
				String timeStamp = inputTimeStamp.getText();
				ResponseData response = null;
				try {
					response = QueryTrans.queryTrans(userId, orderId, timeStamp);
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
		doQuery.setBounds(96, 232, 93, 23);
		contentPane.add(doQuery);
		
		JButton doBack = new JButton("返回");
		doBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomePage welcome = new WelcomePage();
				welcome.setVisible(true);
				setVisible(false);
			}
		});
		doBack.setBounds(254, 232, 93, 23);
		contentPane.add(doBack);
	}

}
