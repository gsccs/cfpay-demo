/**
 *  NameCpn.java -- Mz
 *
 *  2016年9月5日-下午2:21:05
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
 * @author Mz
 *  
 * @time   2016年9月5日下午2:21:05
 */
public class NameCpn extends Input {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField inputName;
	private JTextField inputCpn;
	protected String accNo = null;
	protected String name = null;
	protected String certificateCode = null;
	protected String nbr = null;

	/**
	 * @param buttonName
	 */
	public NameCpn(String buttonName) {
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
		
		JButton doTrans = new JButton(buttonName);
		doTrans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
