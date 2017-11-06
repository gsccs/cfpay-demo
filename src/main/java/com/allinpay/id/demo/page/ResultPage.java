package com.allinpay.id.demo.page;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import com.allinpay.id.demo.domain.ResponseData;
import com.allinpay.id.demo.util.Base64Util;
import com.allinpay.id.demo.util.Constants;

public class ResultPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public ResultPage(ResponseData response) throws IOException {
		setTitle("结果");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		if ("0433".equals(Constants.TRADE_TYPE)) {
			if (null == response) {
				JLabel label = new JLabel("系统异常！");
				label.setBounds(143, 132, 120, 60);
				contentPane.add(label);
			} else {
				JLabel label = new JLabel("订单号：");
				label.setBounds(54, 46, 54, 15);
				contentPane.add(label);

				JLabel orderId = new JLabel(response.getOrderId());
				orderId.setBounds(135, 46, 208, 15);
				contentPane.add(orderId);

				JLabel label_1 = new JLabel("应答码：");
				label_1.setBounds(54, 78, 54, 15);
				contentPane.add(label_1);

				JLabel resultCode = new JLabel(response.getResultCode());
				resultCode.setBounds(135, 78, 208, 15);
				contentPane.add(resultCode);

				JLabel label_2 = new JLabel("应答描述：");
				label_2.setBounds(54, 114, 67, 15);
				contentPane.add(label_2);

				if (response.getResultDesc().length() < 100) {
					JLabel resultDesc = new JLabel(response.getResultDesc());
					resultDesc.setBounds(135, 114, 208, 15);
					contentPane.add(resultDesc);
				} else {
					/** 图像处理 */
					InputStream inputStream = Base64Util.decodeBase64(response.getResultDesc());
					FileOutputStream fos = new FileOutputStream("pic.jpg");

					byte[] b = new byte[1024];

					while ((inputStream.read(b)) != -1) {
						fos.write(b);
					}

					inputStream.close();
					fos.close();

					JPanel jPanel = new JPanel() {

						private static final long serialVersionUID = 1L;

						protected void paintComponent(Graphics g) {
							FileInputStream fileInputStream = null;
							try {
								fileInputStream = new FileInputStream("pic.jpg");
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}

							Image image = null;
							try {
								image = ImageIO.read(fileInputStream);
								fileInputStream.close();
							} catch (IOException e) {
								e.printStackTrace();
							}

							g.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), this);
							this.setSize(image.getWidth(this), image.getHeight(this));
						}
					};
					jPanel.setBounds(54, 154, 500, 500);
					jPanel.setVisible(true);
					contentPane.add(jPanel);
				}
			}
		} else if("0469".equals(Constants.TRADE_TYPE)){
			JLabel label_1 = new JLabel("应答码：");
			label_1.setBounds(54, 78, 54, 15);
			contentPane.add(label_1);

			JLabel resultCode = new JLabel(response.getResultCode());
			resultCode.setBounds(135, 78, 208, 15);
			contentPane.add(resultCode);

			JLabel label_2 = new JLabel("应答描述：");
			label_2.setBounds(54, 114, 67, 15);
			contentPane.add(label_2);
			
			String[] split = response.getResultDesc().split("@#@");
			
			/** 图像处理 */
			InputStream inputStream = Base64Util.decodeBase64(split[0]);
			FileOutputStream fos = new FileOutputStream("pic.jpg");

			byte[] b = new byte[1024];

			while ((inputStream.read(b)) != -1) {
				fos.write(b);
			}

			inputStream.close();
			fos.close();

			JPanel jPanel = new JPanel() {

				private static final long serialVersionUID = 1L;

				protected void paintComponent(Graphics g) {
					FileInputStream fileInputStream = null;
					try {
						fileInputStream = new FileInputStream("pic.jpg");
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}

					Image image = null;
					try {
						image = ImageIO.read(fileInputStream);
						fileInputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

					g.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), this);
					this.setSize(image.getWidth(this), image.getHeight(this));
				}
			};
			JLabel resultDesc = new JLabel(split[1]);
			resultDesc.setBounds(135, 50, 208, 140);
			contentPane.add(resultDesc);
			
			jPanel.setBounds(54, 154, 500, 500);
			jPanel.setVisible(true);
			contentPane.add(jPanel);
			
		} else if("0461".equals(Constants.TRADE_TYPE)){
			JLabel label_1 = new JLabel("应答码：");
			label_1.setBounds(54, 78, 54, 15);
			contentPane.add(label_1);

			JLabel resultCode = new JLabel(response.getResultCode());
			resultCode.setBounds(135, 78, 208, 15);
			contentPane.add(resultCode);

			JLabel label_2 = new JLabel("应答描述：");
			label_2.setBounds(54, 114, 67, 15);
			contentPane.add(label_2);
			
			String[] split = response.getResultDesc().split(",");
			StringBuffer sb = new StringBuffer();
			sb.append("<html>公司名称："+split[0]+"<br/>").append("法人姓名："+split[1]+"<br/>").
			append("注册号："+split[2]+"<br/>").append("地址："+split[3]+"<br/></html>");
			
			JLabel resultDesc = new JLabel(sb.toString());
			resultDesc.setBounds(135, 90, 208, 140);
			contentPane.add(resultDesc);
			
		} else if ("0413".equals(Constants.TRADE_TYPE)) {
			JLabel label_4 = new JLabel("秘钥随机数：");
			label_4.setBounds(54, 149, 87, 15);
			contentPane.add(label_4);

			JLabel label_5 = new JLabel(Constants.RANDOM);
			label_5.setBounds(135, 149, 218, 15);
			contentPane.add(label_5);
		} else if ("0437".equals(Constants.TRADE_TYPE)) {
			JLabel label = new JLabel("订单号：");
			label.setBounds(54, 46, 54, 15);
			contentPane.add(label);

			JLabel orderId = new JLabel(response.getOrderId());
			orderId.setBounds(135, 46, 208, 15);
			contentPane.add(orderId);

			JLabel label_1 = new JLabel("应答码：");
			label_1.setBounds(54, 78, 54, 15);
			contentPane.add(label_1);

			JLabel resultCode = new JLabel(response.getResultCode());
			resultCode.setBounds(135, 78, 208, 15);
			contentPane.add(resultCode);

			JLabel label_2 = new JLabel("应答描述：");
			label_2.setBounds(54, 114, 67, 15);
			contentPane.add(label_2);
			
			if(response.getResultCode().equals("00")){
				org.json.JSONObject object = new JSONObject(response.getResultDesc());
				StringBuffer sb = new StringBuffer();
				sb.append("<html>毕业院校："+object.getString("graduate")+"<br/>").append("毕业时间："+object.getString("graduatetime")+"<br/>").
				append("毕业结论："+object.getString("studyresult")+"<br/>").append("专业："+object.getString("specialityname")+"<br/>").
				append("入学年份："+object.getString("enroldate")+"<br/>").append("学历："+object.getString("educationdegree")+"<br/>").
				append("学历类型："+object.getString("studystyle")+"<html>");
	
				if(object.has("photo")){
					/** 图像处理 */
					InputStream inputStream = Base64Util.decodeBase64(object.getString("photo"));
					FileOutputStream fos = new FileOutputStream("pic.jpg");
		
					byte[] b = new byte[1024];
		
					while ((inputStream.read(b)) != -1) {
						fos.write(b);
					}
		
					inputStream.close();
					fos.close();
		
					JPanel jPanel = new JPanel() {
		
						private static final long serialVersionUID = 1L;
		
						protected void paintComponent(Graphics g) {
							FileInputStream fileInputStream = null;
							try {
								fileInputStream = new FileInputStream("pic.jpg");
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}
		
							Image image = null;
							try {
								image = ImageIO.read(fileInputStream);
								fileInputStream.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
		
							g.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), this);
							this.setSize(image.getWidth(this), image.getHeight(this));
						}
					};
					
					jPanel.setBounds(140, 230, 500, 500);
					jPanel.setVisible(true);
					contentPane.add(jPanel);
				}
				JLabel resultDesc = new JLabel(sb.toString());
				resultDesc.setBounds(135, 100, 208, 140);
				contentPane.add(resultDesc);
			}else{
				JLabel resultDesc = new JLabel(response.getResultDesc());
				resultDesc.setBounds(135, 100, 208, 140);
				contentPane.add(resultDesc);
			}
		} else if ("0439".equals(Constants.TRADE_TYPE)) {
			JLabel label = new JLabel("订单号：");
			label.setBounds(54, 46, 54, 15);
			contentPane.add(label);

			JLabel orderId = new JLabel(response.getOrderId());
			orderId.setBounds(135, 46, 208, 15);
			contentPane.add(orderId);

			JLabel label_1 = new JLabel("应答码：");
			label_1.setBounds(54, 78, 54, 15);
			contentPane.add(label_1);

			JLabel resultCode = new JLabel(response.getResultCode());
			resultCode.setBounds(135, 78, 208, 15);
			contentPane.add(resultCode);

			JLabel label_2 = new JLabel("应答描述：");
			label_2.setBounds(54, 114, 67, 15);
			contentPane.add(label_2);
			
			JSONObject obj = new JSONObject(response.getResultDesc());
			
			JLabel resultDesc = new JLabel(obj.get("criminalmsg").toString());
			resultDesc.setBounds(135, 114, 208, 15);
			contentPane.add(resultDesc);
			
			JLabel label_3 = new JLabel("案发时间：");
			label_3.setBounds(54, 150, 67, 15);
			contentPane.add(label_3);
			
			JLabel criminalTimes = new JLabel(obj.get("criminaltimes").toString());
			criminalTimes.setBounds(135, 150, 208, 15);
			contentPane.add(criminalTimes);
			
		} else if ("0453".equals(Constants.TRADE_TYPE)){
			JLabel label_1 = new JLabel("应答码：");
			label_1.setBounds(54, 78, 54, 15);
			contentPane.add(label_1);

			JLabel resultCode = new JLabel(response.getResultCode());
			resultCode.setBounds(135, 78, 208, 15);
			contentPane.add(resultCode);

			JLabel label_2 = new JLabel("应答描述：");
			label_2.setBounds(54, 114, 67, 15);
			contentPane.add(label_2);
			String desc = response.getResultDesc();
			desc = "<html>"+desc+"</html>";
			JLabel resultDesc = new JLabel(desc);
			resultDesc.setBounds(135, 50, 208, 140);
			contentPane.add(resultDesc);
			
		} else if ("0435".equals(Constants.TRADE_TYPE)) {
			JLabel label_1 = new JLabel("应答码：");
			label_1.setBounds(54, 78, 54, 15);
			contentPane.add(label_1);

			JLabel resultCode = new JLabel(response.getResultCode());
			resultCode.setBounds(135, 78, 208, 15);
			contentPane.add(resultCode);

			JLabel label_2 = new JLabel("应答描述：");
			label_2.setBounds(54, 114, 67, 15);
			contentPane.add(label_2);
			if("00".equals(response.getResultCode())){
				org.json.JSONObject object = new JSONObject(response.getResultDesc());
				StringBuffer sb = new StringBuffer();
				sb.append("<html>卡号："+object.get("cardNos")+"<br/>").append("身份证号："+object.get("certIds")+"<br/>").
				append("手机号："+object.get("mobiles")+"<br/>");
				JSONObject obj = (org.json.JSONObject) object.get("indexes");
				if(!obj.isNull("cardExpSumN12")){
					sb.append("卡片消费水平："+obj.getString("cardExpSumN12")+"<br/>");
				}
				if(!obj.isNull("cardExpCountN12")){
					sb.append("卡片消费频度："+obj.getString("cardExpCountN12")+"<br/>");
				}
				if(!obj.isNull("cardExpAvgN12")){
					sb.append("卡片消费强度："+obj.getString("cardExpAvgN12")+"<br/>");
				}
				if(!obj.isNull("houseExpSumN12")){
					sb.append("房产类消费水平："+obj.getString("houseExpSumN12")+"<br/>");
				}
				if(!obj.isNull("taxExpSumN12")){
					sb.append("纳税类消费水平："+obj.getString("taxExpSumN12")+"<br/>");
				}
				if(!obj.isNull("isrExpSumN12")){
					sb.append("保险类消费水平："+obj.getString("isrExpSumN12")+"<br/>");
				}
				if(!obj.isNull("medExpSumN12")){
					sb.append("医疗类消费水平："+obj.getString("medExpSumN12")+"<br/>");
				}
				sb.append("</html>");
				JLabel resultDesc = new JLabel(sb.toString());
				resultDesc.setBounds(135, 100, 230, 200);
				contentPane.add(resultDesc);
			} else {
				JLabel resultDesc = new JLabel(response.getResultDesc());
				resultDesc.setBounds(135, 100, 208, 200);
				contentPane.add(resultDesc);
			} 
		} else {
			if (null == response) {
				if("0415".equals(Constants.TRADE_TYPE)){
					JLabel label_2 = new JLabel("应答描述：");
					label_2.setBounds(54, 114, 67, 15);
					contentPane.add(label_2);

					JLabel label_3 = new JLabel("未查询到交易记录");
					label_3.setBounds(135, 114, 208, 15);
					contentPane.add(label_3);
				}else{
					JLabel label = new JLabel("系统异常！");
					label.setBounds(143, 132, 120, 60);
					contentPane.add(label);
				}
			} else {
				JLabel label = new JLabel("订单号：");
				label.setBounds(54, 46, 54, 15);
				contentPane.add(label);

				JLabel orderId = new JLabel(response.getOrderId());
				orderId.setBounds(135, 46, 208, 15);
				contentPane.add(orderId);

				JLabel label_1 = new JLabel("应答码：");
				label_1.setBounds(54, 78, 54, 15);
				contentPane.add(label_1);

				JLabel resultCode = new JLabel(response.getResultCode());
				resultCode.setBounds(135, 78, 208, 15);
				contentPane.add(resultCode);

				JLabel label_2 = new JLabel("应答描述：");
				label_2.setBounds(54, 114, 67, 15);
				contentPane.add(label_2);

				JLabel label_3 = new JLabel(response.getResultDesc());
				label_3.setBounds(135, 114, 208, 15);
				contentPane.add(label_3);
			}
		}

		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ("0405".equals(Constants.TRADE_TYPE) || "0407".equals(Constants.TRADE_TYPE)
						|| "0409".equals(Constants.TRADE_TYPE) || "0411".equals(Constants.TRADE_TYPE)
						|| "0421".equals(Constants.TRADE_TYPE)) {
					// InputPage input = new InputPage();
					// input.setVisible(true);
					setVisible(false);
					new TransTypeChoose();
				} else if ("0415".equals(Constants.TRADE_TYPE)) {
					TransQueryPage transQuery = new TransQueryPage();
					transQuery.setVisible(true);
					setVisible(false);
				} else if ("0413".equals(Constants.TRADE_TYPE)) {
					WelcomePage welcome = new WelcomePage();
					welcome.setVisible(true);
					setVisible(false);
				} else if ("0417".equals(Constants.TRADE_TYPE)) {
					TransNameIdQueryPage transNameIdQueryPage = new TransNameIdQueryPage();
					transNameIdQueryPage.setVisible(true);
					setVisible(false);
				} else {
					setVisible(false);
					new TransTypeChoose();
				}
			}
		});
		button.setBounds(155, 427, 93, 23);
		contentPane.add(button);
	}

	private void JSONObject(String resultDesc) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		System.out.println("ResultPage is OK");
		ResponseData responseData = new ResponseData();
		responseData.setTradeType("0433");
		responseData.setOrderId("ffff");
		try {
			ResultPage resultPage = new ResultPage(responseData);
			resultPage.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
