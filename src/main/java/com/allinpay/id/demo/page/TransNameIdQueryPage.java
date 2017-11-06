/**
 *  TransNameIdQueryPage.java -- Mz
 *
 *  2016年8月11日-上午10:09:10
 */
package com.allinpay.id.demo.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.allinpay.id.demo.domain.ResponseData;
import com.allinpay.id.demo.main.ApiTrans;
import com.allinpay.id.demo.util.Constants;

/**
 * @author Mz
 * 
 * @time 2016年8月11日上午10:09:10
 */
public class TransNameIdQueryPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField inputUserName;

	private JTextField inputID;

	public TransNameIdQueryPage() {
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

		JLabel lblNewLabel = new JLabel("姓   名：");
		lblNewLabel.setBounds(54, 58, 93, 15);
		contentPane.add(lblNewLabel);

		inputUserName = new JTextField();
		inputUserName.setBounds(160, 55, 204, 21);
		contentPane.add(inputUserName);
		inputUserName.setColumns(10);

		JLabel label = new JLabel("身份证号 ：");
		label.setBounds(54, 106, 93, 15);
		contentPane.add(label);

		inputID = new JTextField();
		inputID.setBounds(160, 103, 204, 21);
		contentPane.add(inputID);
		inputID.setColumns(10);

		JButton doTrans = new JButton("验证");
		doTrans.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = inputUserName.getText().trim();
				String certificateCode = inputID.getText().trim();
				if (userName.equals("") || certificateCode.equals("")) {
					JOptionPane.showMessageDialog(contentPane, "输入信息为空，请检查!", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				}
				/**
				 * 交易查询
				 * 
				 * */
				
				String accNo = null;
				String nbr = null;
				String name = null;
				String params = null;
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
		doTrans.setBounds(96, 170, 93, 23);
		contentPane.add(doTrans);

		JButton doBack = new JButton("返回");
		doBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomePage welcome = new WelcomePage();
				welcome.setVisible(true);
				setVisible(false);
			}
		});
		doBack.setBounds(254, 170, 93, 23);
		contentPane.add(doBack);
	}
}
