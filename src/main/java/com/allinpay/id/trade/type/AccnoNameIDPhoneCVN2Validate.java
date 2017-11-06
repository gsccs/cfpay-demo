/**
 *  AccnoNameIDPhoneCVN2Validate.java -- Administrator
 *
 *  2016年11月15日-上午11:01:40
 */
package com.allinpay.id.trade.type;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.allinpay.id.demo.main.ApiTrans;
import com.allinpay.id.demo.page.ResultPage;
import com.allinpay.id.demo.domain.ResponseData;

/**
 * @author Administrator
 * 
 * @time 2016年11月15日上午11:01:40
 */
public class AccnoNameIDPhoneCVN2Validate extends Input {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField inputAccNo;
	private JTextField inputName;
	private JTextField inputCpn;
	private JTextField inputId;
	private JTextField inputCVN2;
	private JTextField inputExpired;

	/**
	 * @param buttonName
	 */
	public AccnoNameIDPhoneCVN2Validate(String buttonName) {
		super(buttonName);
		/**
		 * 卡号
		 */
		JLabel label = new JLabel("卡号：");
		label.setBounds(46, 46, 67, 15);
		contentPane.add(label);

		inputAccNo = new JTextField();
		inputAccNo.setBounds(123, 43, 253, 21);
		contentPane.add(inputAccNo);
		inputAccNo.setColumns(10);

		/**
		 * 姓名
		 */
		JLabel jLabel = new JLabel("姓名：");
		jLabel.setBounds(46, 91, 67, 15);
		contentPane.add(jLabel);

		inputName = new JTextField();
		inputName.setBounds(123, 88, 253, 21);
		contentPane.add(inputName);
		inputName.setColumns(10);

		/**
		 * 手机号
		 */
		JLabel label_1 = new JLabel("手机号：");
		label_1.setBounds(46, 136, 67, 15);
		contentPane.add(label_1);

		inputCpn = new JTextField();
		inputCpn.setBounds(123, 133, 253, 21);
		contentPane.add(inputCpn);
		inputCpn.setColumns(10);

		/**
		 * 身份证号
		 */
		JLabel label_2 = new JLabel("身份证号：");
		label_2.setBounds(46, 181, 67, 15);
		contentPane.add(label_2);

		inputId = new JTextField();
		inputId.setBounds(123, 178, 253, 21);
		contentPane.add(inputId);
		inputId.setColumns(10);

		/**
		 * CVN2
		 */
		JLabel label_3 = new JLabel("CVN2：");
		label_3.setBounds(46, 226, 67, 15);
		contentPane.add(label_3);

		inputCVN2 = new JTextField();
		inputCVN2.setBounds(123, 223, 253, 21);
		contentPane.add(inputCVN2);
		inputCVN2.setColumns(10);

		/**
		 * 有效期
		 */
		JLabel label_4 = new JLabel("有效期：");
		label_4.setBounds(46, 271, 67, 15);
		contentPane.add(label_4);

		inputExpired = new JTextField();
		inputExpired.setBounds(123, 268, 253, 21);
		contentPane.add(inputExpired);
		inputExpired.setColumns(10);

		JButton doTrans = new JButton(buttonName);
		doTrans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				accNo = inputAccNo.getText().trim();
				certificateCode = inputId.getText().trim();
				name = inputName.getText().trim();
				nbr = inputCpn.getText().trim();
				cvn2 = inputCVN2.getText().trim();
				expired = inputExpired.getText().trim();
				ResponseData response = null;
				Map<String, String> map = new HashMap<String, String>();
				map.put("nbr", nbr);
				map.put("accNo", accNo);
				map.put("certificateCode", certificateCode);
				map.put("name", name);
				map.put("cvn2", cvn2);
				map.put("expired", expired);
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
					e1.printStackTrace();
				}
				result.setVisible(true);
				setVisible(false);
			}
		});
		doTrans.setBounds(101, 320, 93, 23);
		contentPane.add(doTrans);
	}

}
