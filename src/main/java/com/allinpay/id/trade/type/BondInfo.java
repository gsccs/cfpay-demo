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

public class BondInfo extends Input {

	private JTextField name;
	private JTextField idCard;
	private JTextField param;
	
	public BondInfo(String buttonName) {
		super(buttonName);
		JLabel label = new JLabel("名称：");
		label.setBounds(46, 46, 67, 15);
		contentPane.add(label);
		
		name = new JTextField();
		name.setBounds(123, 43, 253, 21);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel label2 = new JLabel("身份证号：");
		label2.setBounds(46, 91, 67, 15);
		contentPane.add(label2);
		
		idCard = new JTextField();
		idCard.setBounds(123, 88, 253, 21);
		contentPane.add(idCard);
		idCard.setColumns(10);
		
		JLabel label3 = new JLabel("查询机构：");
		label3.setBounds(46, 136, 67, 15);
		contentPane.add(label3);
		
		param = new JTextField();
		param.setBounds(123, 133, 253, 21);
		contentPane.add(param);
		param.setColumns(10);
		
		JButton doTrans = new JButton(buttonName);
		doTrans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String card = idCard.getText().trim();	
				String trim = name.getText().trim();
				String credit = param.getText().trim();
				ResponseData response = null;
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", trim); //查询姓名
				map.put("params", credit); //查询机构
				map.put("certificateCode", card); //查询身份证号
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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
