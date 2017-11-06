package com.allinpay.id.demo.main;

import java.util.HashMap;
import java.util.Map;

import com.allinpay.id.demo.domain.ResponseData;
import com.allinpay.id.demo.util.Constants;

public class TerminalMain {
	//银行卡号
	private static String acc_No = "6222222222222";
	//身份证号
	private static String cid_No="62282719850201XXXX";
	
	private static String uname = "张";
	

	public static void main(String[] args) throws Exception {
		GetDynKey.getDynKey();
		Constants.TRADE_TYPE = "0449";
		test_0449();
		test_0405();
		test_0407();
		test_0409();
		test_0411();
		
		//selectTradeType(tradeType);
		System.out.println(Constants.TRADE_TYPE);
		if(null == Constants.TRADE_TYPE) {
			System.out.println("交易类型错误！");
			return;
		}
		
	}
	
	//两要素认证，卡号+姓名
	private static void test_0405() throws Exception{
		String accNo = acc_No;
		String name = uname;
		Map<String, String> map = new HashMap<String, String>();
		map.put("nbr", null);
		map.put("accNo", accNo);
		map.put("certificateCode", null);
		map.put("name", name);
		map.put("params", null);
		ResponseData response = ApiTrans.doTrans(map);
		System.out.println("返回码" + response.getResultCode() + "，返回描述" + response.getResultDesc());
	}
	
	
	private static void test_0407() throws Exception{
		String accNo = acc_No;
		String certificateCode = cid_No;
		Map<String, String> map = new HashMap<String, String>();
		map.put("nbr", null);
		map.put("accNo", accNo);
		map.put("certificateCode", certificateCode);
		map.put("name", null);
		map.put("params", null);
		ResponseData response = ApiTrans.doTrans(map);
		System.out.println("返回码" + response.getResultCode() + "，返回描述" + response.getResultDesc());
	}
	
	private static void test_0409() throws Exception{
		String accNo = acc_No;
		String name = uname;
		String certificateCode = cid_No;
		Map<String, String> map = new HashMap<String, String>();
		map.put("nbr", null);
		map.put("accNo", accNo);
		map.put("certificateCode", certificateCode);
		map.put("name", name);
		map.put("params", null);
		ResponseData response = ApiTrans.doTrans(map);
		System.out.println("返回码" + response.getResultCode() + "，返回描述" + response.getResultDesc());
	}
	
	private static void test_0411() throws Exception{
		String accNo = acc_No;
		String name = uname;
		String certificateCode = cid_No;
		String nbr = "";
		Map<String, String> map = new HashMap<String, String>();
		map.put("nbr", nbr);
		map.put("accNo", accNo);
		map.put("certificateCode", certificateCode);
		map.put("name", name);
		map.put("params", null);
		ResponseData response = ApiTrans.doTrans(map);
		System.out.println("返回码" + response.getResultCode() + "，返回描述" + response.getResultDesc());
	}
	
	
	private static void test_0413() throws Exception{
		String random = GetDynKey.getDynKey();
		System.out.println("秘要随机数" + random);
	}
	
	private static void test_0415(String args[]) throws Exception{
		String userId = args[1];
		String orderId = args[2];
		String timeStamp = args[3];
		ResponseData response = QueryTrans.queryTrans(userId, orderId, timeStamp);
		System.out.println("返回码" + response.getResultCode() + "，返回描述" + response.getResultDesc());
	}
	
	
	private static void test_0449() throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("certificateCode", cid_No);
		map.put("name", uname);
		//map.put("params", null);
		ResponseData response = ApiTrans.doTrans(map);
		System.out.println("返回码" + response.getResultCode() + "，返回描述" + response.getResultDesc());
	}

	/**
	 * 根据交易类型参数选择相应的类型
	 * 
	 * @param tradeType
	 * @return
	 */
	public static String selectTradeType(String tradeType) {
		if("0".equals(tradeType)) {
			return "0405";
		} else if("1".equals(tradeType)) {
			return "0407";
		} else if("2".equals(tradeType)) {
			return "0409";
		} else if("3".equals(tradeType)) {
			return "0411";
		} else if("4".equals(tradeType)) {
			return "0413";
		} else if("5".equals(tradeType)) {
			return "0415";
		}
		return null;
	}
}
