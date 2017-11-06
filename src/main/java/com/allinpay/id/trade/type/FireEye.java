/**
 *  FireEye.java -- Mz
 *
 *  2016年9月9日-上午11:16:51
 */
package com.allinpay.id.trade.type;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

import com.allinpay.id.demo.main.ApiTrans;
import com.allinpay.id.demo.page.ResultPage;
import com.allinpay.id.demo.domain.ResponseData;

/**
 * @author Mz
 *  
 * @time   2016年9月9日上午11:16:51
 */
public class FireEye extends Input {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField inputId;
	private JTextField inputName;
	private JTextField inputCpn;
	private JCheckBox cardExpSum;//卡片消费水平
	private JCheckBox cardExpCount;//卡片消费频度
	private JCheckBox cardExpAvg;//卡片消费强度
	private JCheckBox houseExpSum;//房产类消费水平
	private JCheckBox taxExpSum;//纳税类消费水平
	private JCheckBox isrExpSum;//保险类消费水平
	private JCheckBox medExpSum;//医疗类消费水平
	/**
	 * @param buttonName
	 */
	public FireEye(String buttonName) {
		super(buttonName);
		setBounds(100, 100, 450, 500);
		int labelY = 46;
		int inputY = 46;
		
		JLabel label = new JLabel("卡号：");
		label.setBounds(46, labelY, 67, 15);
		contentPane.add(label);

		inputName = new JTextField();
		inputName.setBounds(123, inputY, 253, 21);
		contentPane.add(inputName);
		inputName.setColumns(10);

		labelY += 45;
		inputY += 45;
		
		JLabel label_1 = new JLabel("手机号：");
		label_1.setBounds(46, labelY, 67, 15);
		contentPane.add(label_1);

		inputCpn = new JTextField();
		inputCpn.setBounds(123, inputY, 253, 21);
		contentPane.add(inputCpn);
		inputCpn.setColumns(10);
        
		labelY += 45;
		inputY += 45;
		
		JLabel jLabel = new JLabel("身份证号：");
		jLabel.setBounds(46, inputY, 67, 15);
		contentPane.add(jLabel);

		inputId = new JTextField();
		inputId.setBounds(123, inputY, 253, 21);
		contentPane.add(inputId);
		inputId.setColumns(10);
		
		labelY += 45;
		inputY += 45;
				
		cardExpSum = new JCheckBox("卡片消费水平");// 创建复选按钮
		cardExpSum.setBounds(46, labelY, 150, 15);
        contentPane.add(cardExpSum);// 应用复选按钮
        
        cardExpCount = new JCheckBox("卡片消费频度");// 创建复选按钮
        cardExpCount.setBounds(200, labelY, 150, 15);
        contentPane.add(cardExpCount);// 应用复选按钮
        
        labelY += 45;
        cardExpAvg = new JCheckBox("卡片消费强度");// 创建复选按钮
        cardExpAvg.setBounds(46, labelY, 160, 15);
        contentPane.add(cardExpAvg);// 应用复选按钮
        
		houseExpSum = new JCheckBox("房产类消费水平");// 创建复选按钮
		houseExpSum.setBounds(200, labelY, 150, 15);
        contentPane.add(houseExpSum);// 应用复选按钮
        
        labelY += 45;
		taxExpSum = new JCheckBox("纳税类消费水平");// 创建复选按钮
		taxExpSum.setBounds(46, labelY, 150, 15);
        contentPane.add(taxExpSum);// 应用复选按钮
        
		isrExpSum = new JCheckBox("保险类消费水平");// 创建复选按钮
		isrExpSum.setBounds(200, labelY, 150, 15);
        contentPane.add(isrExpSum);// 应用复选按钮
        
        labelY += 45;
        medExpSum = new JCheckBox("医疗类消费水平");// 创建复选按钮
        medExpSum.setBounds(46, labelY, 150, 15);
        contentPane.add(medExpSum);// 应用复选按钮
        
        labelY += 45;
		JButton doTrans = new JButton(buttonName);
		doTrans.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ResponseData response = null;
				nbr = inputCpn.getText().trim();
				certificateCode = inputId.getText().trim();
				name = inputName.getText().trim();
				
				if(isNull(nbr)&&isNull(certificateCode)&&isNull(name)){
					JSONObject jsonObject = new JSONObject();
					JSONObject indexes = new JSONObject();
					if(cardExpSum.isSelected()){
						indexes.put("cardExpSumN12", "");
					}
					if(cardExpCount.isSelected()){
						indexes.put("cardExpCountN12", "");
					}
					if(cardExpAvg.isSelected()){
						indexes.put("cardExpAvgN12", "");
					}
					if(houseExpSum.isSelected()){
						indexes.put("houseExpSumN12", "");
					}
					if(taxExpSum.isSelected()){
						indexes.put("taxExpSumN12", "");
					}
					if(isrExpSum.isSelected()){
						indexes.put("isrExpSumN12", "");
					}
					if(medExpSum.isSelected()){
						indexes.put("medExpSumN12", "");
					}
					Date date=new Date();
					  DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
					  String time=format.format(date);
					jsonObject.put("reqId", time);
					jsonObject.put("isExpand", 1);
					jsonObject.put("isSingle", 0);
					JSONArray nbrs = new JSONArray();
					nbrs.put(nbr);
					jsonObject.put("nbr", nbrs);//手机
					JSONArray names = new JSONArray();
					names.put(name);
					jsonObject.put("accNo", names);//卡号
					JSONArray certificateCodes = new JSONArray();
					certificateCodes.put(certificateCode);
					jsonObject.put("certificateCode", certificateCodes);//身份证
					jsonObject.put("indexes", indexes);
					params = jsonObject.toString();
					Map<String, String> map = new HashMap<String, String>();
					map.put("params", params);
					try {
						response = ApiTrans.doTrans(map);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				} else {
					response = new ResponseData();
					response.setResultCode("03");
					response.setResultDesc("报文要素不完整,请检查");
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
		doTrans.setBounds(101, 400, 93, 23);
		doBack.setBounds(282, 400, 93, 23);
		contentPane.add(doTrans);
	}
	
	private boolean isNull(String string){
		if(string!=""&&string.length()>0){
			return true;
		}else{
			return false;
		}
	}

}
