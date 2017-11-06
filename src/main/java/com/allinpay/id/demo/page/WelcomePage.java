package com.allinpay.id.demo.page;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.allinpay.id.demo.main.GetDynKey;
import com.allinpay.id.demo.util.Constants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomePage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			String msg = GetDynKey.getDynKey();
			if (msg.substring(0, 2).equals("00")) {
				System.out.println("动态密钥请求成功！[" + msg + "]");
			} else if (msg.substring(0, 2).equals("01")) {
				System.out.println(msg.substring(2));
			} else {
				System.out.println(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomePage frame = new WelcomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WelcomePage() {
		int ya = 0;
		setTitle("欢迎您");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, y + 30, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		 * 模式：动态秘钥
		 */
		ya = 50;
		JRadioButton dynKey = new JRadioButton("动态秘钥");
		dynKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0413";
			}
		});
		buttonGroup.add(dynKey);
		dynKey.setBounds(85, ya, 308, 23);
		contentPane.add(dynKey);

		/*
		 * 模式：交易查询
		 */
		ya += 41;
		JRadioButton transQuery = new JRadioButton("交易查询");
		transQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0415";
			}
		});
		buttonGroup.add(transQuery);
		transQuery.setBounds(85, ya, 308, 23);
		contentPane.add(transQuery);
		
		/*
		 * 模式1：卡号+姓名
		 */
		ya += 41;
		JRadioButton model1 = new JRadioButton("模式1：卡号+姓名");
		model1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0405";
			}
		});
		buttonGroup.add(model1);
		model1.setBounds(85, ya, 308, 23);
		contentPane.add(model1);

		/*
		 * 模式2：卡号+身份证号
		 */
		ya += 41;
		JRadioButton model2 = new JRadioButton("模式2：卡号+身份证号");
		model2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0407";
			}
		});
		buttonGroup.add(model2);
		model2.setBounds(85, ya, 308, 23);
		contentPane.add(model2);

		/*
		 * 模式3：卡号+姓名+身份证号
		 */
		ya += 41;
		JRadioButton model3 = new JRadioButton("模式3：卡号+姓名+身份证号");
		model3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0409";
			}
		});
		buttonGroup.add(model3);
		model3.setBounds(85, ya, 308, 23);
		contentPane.add(model3);

		/*
		 * 模式4：卡号+姓名+手机号+身份证号
		 */
		ya += 41;
		JRadioButton model4 = new JRadioButton("模式4：卡号+姓名+手机号+身份证号");
		model4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0411";
			}
		});
		buttonGroup.add(model4);
		model4.setBounds(85, ya, 308, 23);
		contentPane.add(model4);

		/*
		 * 模式5：姓名+身份证号（无图）
		 */
		ya += 41;
		JRadioButton model5 = new JRadioButton("模式5：姓名+身份证号（无图）");
		model5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0417";
			}
		});
		buttonGroup.add(model5);
		model5.setBounds(85, ya, 308, 23);
		contentPane.add(model5);

		/*
		 * 模式6：姓名+身份证号（有图）
		 */
		ya += 41;
		JRadioButton modelq = new JRadioButton("模式6：姓名+身份证号（有图）");
		modelq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0433";
			}
		});
		buttonGroup.add(modelq);
		modelq.setBounds(85, ya, 308, 23);
		contentPane.add(modelq);

		/*
		 * 模式7：姓名+手机号(收费！)
		 */
		ya += 41;
		JRadioButton model6 = new JRadioButton("模式7：姓名+手机号");
		model6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0419";
			}
		});
		buttonGroup.add(model6);
		model6.setBounds(85, ya, 308, 23);
		contentPane.add(model6);

		/*
		 * 模式8：姓名+手机号+身份证号(收费！)
		 */
		ya += 41;
		JRadioButton model7 = new JRadioButton("模式8：姓名+手机号+身份证号");
		model7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0421";
			}
		});
		buttonGroup.add(model7);
		model7.setBounds(85, ya, 308, 23);
		contentPane.add(model7);

		
		int yb = 50;
		/*
		 * 模式9：火眼
		 */
		JRadioButton model8 = new JRadioButton("模式9：火眼");
		model8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0435";
			}
		});
		buttonGroup.add(model8);
		model8.setBounds(85 + 400, yb, 308, 23);
		contentPane.add(model8);

		/*
		 * 模式10： 学历认证
		 */
		yb += 41;
		JRadioButton model9 = new JRadioButton("模式10： 学历认证");
		model9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0437";
			}
		});
		buttonGroup.add(model9);
		model9.setBounds(85 + 400, yb, 308, 23);
		contentPane.add(model9);

		/*
		 * 模式11：身份证图像认证
		 */
		yb += 41;
		JRadioButton model11 = new JRadioButton("模式11：身份证图像认证");
		model11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0443";
			}
		});
		buttonGroup.add(model11);
		model11.setBounds(85 + 400, yb, 308, 23);
		contentPane.add(model11);

		/*
		 * 模式12：低清人像认证
		 */
		yb += 41;
		JRadioButton model12 = new JRadioButton("模式12：低清人像认证");
		model12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0445";
			}
		});
		buttonGroup.add(model12);
		model12.setBounds(85 + 400, yb, 308, 23);
		contentPane.add(model12);

		/*
		 * 模式13：高清人像认证
		 */
		yb += 41;
		JRadioButton model13 = new JRadioButton("模式13：高清人像认证");
		model13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0447";
			}
		});
		buttonGroup.add(model13);
		model13.setBounds(85 + 400, yb, 308, 23);
		contentPane.add(model13);
		
		/*
		 * 模式14：高清人像认证
		 */
		yb += 41;
		JRadioButton model14 = new JRadioButton("模式14：手机号在网状态查询");
		model14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0441";
			}
		});
		buttonGroup.add(model14);
		model14.setBounds(85 + 400, yb, 308, 23);
		contentPane.add(model14);
		
		/*
		 * 模式15：银行卡六要素
		 */
		yb += 41;
		JRadioButton model15 = new JRadioButton("模式15：卡号+姓名+身份证号+手机号+CVN2+有效期");
		model15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0601";
			}
		});
		buttonGroup.add(model15);
		model15.setBounds(85 + 400, yb, 350, 35);
		contentPane.add(model15);		
		
		/*
		 * 模式16： 不良信息查询
		 */
		yb += 41;
		JRadioButton model16 = new JRadioButton("模式16：不良信息查询(姓名+身份证号)");
		model16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0439";
			}
		});
		buttonGroup.add(model16);
		model16.setBounds(85 + 400, yb, 350, 35);
		contentPane.add(model16);	
		
		/*
		 * 模式17：工商信息查询
		 */
		yb += 41;
		JRadioButton model17 = new JRadioButton("模式17：工商信息查询");
		model17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0461";
			}
		});
		buttonGroup.add(model17);
		model17.setBounds(85 + 400, yb, 350, 35);
		contentPane.add(model17);
		
		/*
		 * 模式18：乘机记录认证
		 */
		yb += 41;
		JRadioButton model18 = new JRadioButton("模式18：乘机记录认证");
		model18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0449";
			}
		});
		buttonGroup.add(model18);
		model18.setBounds(85 + 400, yb, 350, 35);
		contentPane.add(model18);
		
		int yc = 0;
		/*
		 * 模式19：是否高舱认证
		 */
		yc += 41;
		JRadioButton model19 = new JRadioButton("模式19：是否高舱认证");
		model19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0451";
			}
		});
		buttonGroup.add(model19);
		model19.setBounds(885, yc, 350, 35);
		contentPane.add(model19);
		
		/*
		 * 模式20：航空出行认证
		 */
		yc += 41;
		JRadioButton model20 = new JRadioButton("模式20：航空出行认证");
		model20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0453";
			}
		});
		buttonGroup.add(model20);
		model20.setBounds(885, yc, 350, 35);
		contentPane.add(model20);
		
		/*
		 * 模式21：跨境出行认证 
		 */
		yc += 41;
		JRadioButton model21 = new JRadioButton("模式21：跨境出行认证");
		model21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0455";
			}
		});
		buttonGroup.add(model21);
		model21.setBounds(885, yc, 350, 35);
		contentPane.add(model21);
		
		/*
		 * 模式22：企业4要素认证 
		 */
//		yc += 41;
//		JRadioButton model22 = new JRadioButton("模式22：企业4要素认证");
//		model22.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Constants.TRADE_TYPE = "0457";
//			}
//		});
//		buttonGroup.add(model22);
//		model22.setBounds(885, yc, 350, 35);
//		contentPane.add(model22);
		
		/*
		 * 模式23：证券信息认证 
		 */
		yc += 41;
		JRadioButton model23 = new JRadioButton("模式22：证券信息认证");
		model23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0459";
			}
		});
		buttonGroup.add(model23);
		model23.setBounds(885, yc, 350, 35);
		contentPane.add(model23);
		
		/*
		 * 模式24：身份证户籍地址查询 
		 */
		yc += 41;
		JRadioButton model24 = new JRadioButton("模式23：身份证户籍地址查询");
		model24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0467";
			}
		});
		buttonGroup.add(model24);
		model24.setBounds(885, yc, 350, 35);
		contentPane.add(model24);
		
		/*
		 * 模式25：身份证照片+身份证户籍地查询
		 */
		yc += 41;
		JRadioButton model25 = new JRadioButton("模式24：身份证照片+身份证户籍地查询");
		model25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.TRADE_TYPE = "0469";
			}
		});
		buttonGroup.add(model25);
		model25.setBounds(885, yc, 350, 35);
		contentPane.add(model25);
		
		
		ya += 41;
		JButton nextStep = new JButton("下一步");
		nextStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TransTypeChoose();
				setVisible(false);
			}
		});
		nextStep.setBounds(300 + 250, ya, 118, 34);
		contentPane.add(nextStep);

		JLabel lbltj = new JLabel("用户：" + Constants.USER_ID);
		lbltj.setBounds(26, 10, 165, 31);
		contentPane.add(lbltj);

		JLabel lbltj_1 = new JLabel("渠道：" + Constants.CHANNEL_ID);
		lbltj_1.setBounds(950, 14, 158, 23);
		contentPane.add(lbltj_1);
		ya += 100;
		setBounds(100, 100, 1150, ya);
	}
}
