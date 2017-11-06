/**
 *  IdCardImageCertificate.java -- Administrator
 *
 *  2016年10月18日-下午2:34:50
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
 * @time 2016年10月18日下午2:34:50
 */
public class IdCardImageCertificate extends Input {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idCardImagePositive;
	private JTextField idCardImageNegative;

	/**
	 * @param buttonName
	 */
	public IdCardImageCertificate(String buttonName) {
		super(buttonName);
		JLabel labelTitle = new JLabel("身份证图片");
		labelTitle.setBounds(200, 46, 100, 25);
		contentPane.add(labelTitle);
		
		JLabel labelPath = new JLabel("eg:   C:/Users/xx/Desktop/1.jpg");
		labelPath.setBounds(150, 60, 250, 70);
		contentPane.add(labelPath);
		/**
		 * 身份证正面图片
		 */
		JLabel label = new JLabel("正面路径：");
		label.setBounds(46, 130, 67, 15);
		contentPane.add(label);

		idCardImagePositive = new JTextField();
		idCardImagePositive.setBounds(140, 130, 250, 21);
		contentPane.add(idCardImagePositive);
		idCardImagePositive.setColumns(10);

		/**
		 * 身份证反面图片
		 */
		JLabel jLabel = new JLabel("反面路径：");
		jLabel.setBounds(46, 180, 67, 15);
		contentPane.add(jLabel);

		idCardImageNegative = new JTextField();
		idCardImageNegative.setBounds(140, 180, 250, 21);
		contentPane.add(idCardImageNegative);
		idCardImageNegative.setColumns(10);

		JButton doTrans = new JButton(buttonName);
		doTrans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String imagebase2 = null;
				String imagebase3 = null;
				try {
					File image2 = new File(idCardImagePositive.getText().trim());
					InputStream is2;
					is2 = new FileInputStream(image2);
					BufferedImage bi2 = ImageIO.read(is2);
					imagebase2 = Base64Util.encodeBase64(bi2);
					is2.close();
					
					File image3 = new File(idCardImageNegative.getText().trim());
					InputStream is3 = new FileInputStream(image3);
					BufferedImage bi3 = ImageIO.read(is3);
					imagebase3 = Base64Util.encodeBase64(bi3);
					is3.close();
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("positive", imagebase2);
				jsonObject.put("negative", imagebase3);
				ResponseData response = null;
				Map<String, String> map = new HashMap<String, String>();
				params = jsonObject.toString();
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
