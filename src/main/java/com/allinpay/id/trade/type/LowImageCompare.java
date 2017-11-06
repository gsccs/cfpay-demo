/**
 *  LowImageCompare.java -- Administrator
 *
 *  2016年10月24日-上午9:32:26
 */
package com.allinpay.id.trade.type;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.JSONObject;

import com.allinpay.id.demo.main.ApiTrans;
import com.allinpay.id.demo.page.ResultPage;
import com.allinpay.id.demo.domain.ResponseData;
import com.allinpay.id.demo.util.Base64Util;

/**
 * @author Administrator
 *  
 * @time   2016年10月24日上午9:32:26
 */
public class LowImageCompare extends Input {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField lowImage;
	private JTextField inputName;
	private JTextField inputId;

	/**
	 * @param buttonName
	 */
	public LowImageCompare(String buttonName) {
		super(buttonName);
		JLabel labelTitle = new JLabel("低清人像图片:");
		labelTitle.setBounds(200, 46, 100, 25);
		contentPane.add(labelTitle);
		
		JLabel labelPath = new JLabel("eg:   C:/Users/xx/Desktop/1.jpg");
		labelPath.setBounds(150, 60, 250, 70);
		contentPane.add(labelPath);
		
		/**
		 * 低清图片
		 */
		JLabel label = new JLabel("图片路径：");
		label.setBounds(46, 130, 67, 15);
		contentPane.add(label);

		lowImage = new JTextField();
		lowImage.setBounds(140, 130, 250, 21);
		contentPane.add(lowImage);
		lowImage.setColumns(10);
		
		
		JLabel labelName = new JLabel("姓名：");
		labelName.setBounds(46, 170, 67, 15);
		contentPane.add(labelName);

		inputName = new JTextField();
		inputName.setBounds(140, 170, 250, 21);
		contentPane.add(inputName);
		inputName.setColumns(10);

		JLabel label_1 = new JLabel("身份证号：");
		label_1.setBounds(46, 210, 67, 15);
		contentPane.add(label_1);

		inputId = new JTextField();
		inputId.setBounds(140, 210, 250, 21);
		contentPane.add(inputId);
		inputId.setColumns(10);

		JButton doTrans = new JButton(buttonName);
		doTrans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String imagebase2 = null;
				try {
					File image2 = new File(lowImage.getText().trim());
					InputStream is2;
					is2 = new FileInputStream(image2);
					BufferedImage bi2 = ImageIO.read(is2);
					imagebase2 = Base64Util.encodeBase64(bi2);
					is2.close();
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("image", imagebase2);
				ResponseData response = null;
				Map<String, String> map = new HashMap<String, String>();
				params = jsonObject.toString();
				map.put("params", params);
				certificateCode = inputId.getText().trim();
				map.put("certificateCode", certificateCode);
				name = inputName.getText().trim();
				map.put("name", name);
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
