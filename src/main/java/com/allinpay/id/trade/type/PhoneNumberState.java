/**
 *  PhoneNumberState.java -- Administrator
 *
 *  2016年11月3日-下午3:33:26
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
 * @time   2016年11月3日下午3:33:26
 */
public class PhoneNumberState extends Input {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField inputName;
	private JTextField inputCpn;
	private JTextField inputId;
	protected String accNo = null;
	protected String name = null;
	protected String certificateCode = null;
	protected String nbr = null;


	/**
	 * @param buttonName
	 */
	public PhoneNumberState(String buttonName) {
		super(buttonName);
		/**
		 * 姓名
		 * */
		JLabel label = new JLabel("姓名：");
		label.setBounds(46, 46, 67, 15);
		contentPane.add(label);

		inputName = new JTextField();
		inputName.setBounds(123, 43, 253, 21);
		contentPane.add(inputName);
		inputName.setColumns(10);

		/**
		 * 手机号
		 * */
		JLabel jLabel = new JLabel("手机号：");
		jLabel.setBounds(46, 91, 67, 15);
		contentPane.add(jLabel);
		
		inputCpn = new JTextField();
		inputCpn.setBounds(123, 88, 253, 21);
		contentPane.add(inputCpn);
		inputCpn.setColumns(10);
		
		/**
		 * 身份证号
		 * */
		JLabel label_1 = new JLabel("身份证号：");
		label_1.setBounds(46, 136, 67, 15);
		contentPane.add(label_1);
		
		inputId = new JTextField();
		inputId.setBounds(123, 133, 253, 21);
		contentPane.add(inputId);
		inputId.setColumns(10);
		
		JButton doTrans = new JButton(buttonName);
		doTrans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				certificateCode = inputId.getText().trim();
				name = inputName.getText().trim();
				nbr = inputCpn.getText().trim();
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
		doTrans.setBounds(101, 320, 93, 23);
		contentPane.add(doTrans);
	}

}
