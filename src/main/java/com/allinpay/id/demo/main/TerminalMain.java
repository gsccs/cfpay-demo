package com.allinpay.id.demo.main;

import java.util.HashMap;
import java.util.Map;

import com.allinpay.id.demo.domain.ResponseData;
import com.allinpay.id.demo.util.Constants;

public class TerminalMain {

	public static void main(String[] args) throws Exception {
		GetDynKey.getDynKey();
		args = new String[4];
		args[0]="2";
		args[1]="6226099310745903";
		args[2]="张晓东";
		args[3]="62282719850201411X";
		
		
		String tradeType = args[0];
		Constants.TRADE_TYPE = "0449";
		test_0449();
		
		//selectTradeType(tradeType);
		System.out.println(Constants.TRADE_TYPE);
		if(null == Constants.TRADE_TYPE) {
			System.out.println("交易类型错误！");
			return;
		}
		
	}
	
	//两要素认证，卡号+姓名
	private static void test_0405() throws Exception{
		String accNo = "6226099310745903";
		String name = "张晓东";
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
		String accNo = "6226099310745903";
		String certificateCode = "62282719850201411X";
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
		String accNo = "6226099310745903";
		String name = "张晓东";
		String certificateCode = "62282719850201411X";
		Map<String, String> map = new HashMap<String, String>();
		map.put("nbr", null);
		map.put("accNo", accNo);
		map.put("certificateCode", certificateCode);
		map.put("name", name);
		map.put("params", null);
		ResponseData response = ApiTrans.doTrans(map);
		System.out.println("返回码" + response.getResultCode() + "，返回描述" + response.getResultDesc());
	}
	
	private static void test_0411(String args[]) throws Exception{
		String accNo = args[1];
		String name = args[2];
		String certificateCode = args[3];
		String nbr = args[4];
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
		map.put("certificateCode", "62282719850201411X");
		map.put("name", "张晓东");
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
