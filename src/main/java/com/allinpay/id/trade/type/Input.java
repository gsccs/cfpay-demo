/**
 *  Input.java -- Mz
 *
 *  2016年8月12日-上午9:38:26
 */
package com.allinpay.id.trade.type;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.allinpay.id.demo.page.WelcomePage;
import com.allinpay.id.demo.util.Constants;

/**
 * @author Mz
 * 
 * @time 2016年8月12日上午9:38:26
 */
public abstract class Input extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JButton doBack;
	
	protected String accNo = null;
	protected String name = null;
	protected String certificateCode = null;
	protected String nbr = null;
	protected String cvn2 = null;
	protected String expired = null;//有效期
	protected String params = null;

	public Input(String buttonName) {
		setTitle("输入验证要素");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbltj = new JLabel("用户：" + Constants.USER_ID);
		lbltj.setBounds(46, 10, 127, 15);
		contentPane.add(lbltj);

		JLabel lbltj_1 = new JLabel("渠道：" + Constants.CHANNEL_ID);
		lbltj_1.setBounds(255, 10, 127, 15);
		contentPane.add(lbltj_1);

		doBack = new JButton("返回");
		doBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomePage welcome = new WelcomePage();
				welcome.setVisible(true);
				setVisible(false);
			}
		});
		doBack.setBounds(282, 320, 93, 23);
		contentPane.add(doBack);
	}
	
//	public abstract Result getResult();
}
