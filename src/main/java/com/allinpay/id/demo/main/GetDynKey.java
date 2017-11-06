package com.allinpay.id.demo.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

import com.allinpay.id.demo.domain.RequestData;
import com.allinpay.id.demo.domain.ResponseData;
import com.allinpay.id.demo.util.Constants;
import com.allinpay.id.demo.util.DESedeUtil;
import com.allinpay.id.demo.util.MD5Util;
import com.allinpay.id.demo.util.OrderUtil;
import com.allinpay.id.demo.util.TransMsgUtil;
import com.allinpay.id.demo.util.XmlUtil;



public class GetDynKey {
	
	public static String getAbsolutePath(){
		File file = new File("");
		String home = file.getAbsolutePath();
		if (home.indexOf(":") == -1) {
			home = "/" + home;
		}
		return home;
	}

	public static String getDynKey() throws Exception{
		
		System.setProperty("javax.net.ssl.keyStore", getAbsolutePath()+"/certs/server.keystore");
        System.setProperty("javax.net.ssl.keyStorePassword","111111");
        System.setProperty("javax.net.ssl.keyStoreType", "JKS");
        
		System.setProperty("javax.net.ssl.trustStore", getAbsolutePath()+"/certs/server.keystore");
		System.setProperty("javax.net.ssl.trustStorePassword","111111");
		HttpsURLConnection.setDefaultHostnameVerifier(new TrustAnyVerifier());
		
//		String requestUrl ="https://123.206.54.253:8443/auth-web/trans/getDynKey";
		
//		System.setProperty("javax.net.ssl.keyStore", "C:/certs/server.keystore");
//        System.setProperty("javax.net.ssl.keyStorePassword","123456");
//        System.setProperty("javax.net.ssl.keyStoreType", "JKS");
//        
//		System.setProperty("javax.net.ssl.trustStore", "C:/certs/server.keystore");
//		System.setProperty("javax.net.ssl.trustStorePassword","123456");
//		HttpsURLConnection.setDefaultHostnameVerifier(new TrustAnyVerifier());
//		
//		String requestUrl ="https://192.168.2.204:8443/auth-web/trans/getDynKey";
		
		String resultString = "";
		HttpsURLConnection httpURLConnection = null;
		URL url;
		try {
			//发送请求
			url = new URL(Constants.URL_DYN);
			httpURLConnection = (HttpsURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(50000);
			httpURLConnection.setReadTimeout(50000);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setRequestProperty("Content-type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			httpURLConnection.setRequestMethod("POST");

			PrintStream out = null;
			try {
				httpURLConnection.connect();
				out = new PrintStream(httpURLConnection.getOutputStream(), false,
						"UTF-8");
				out.print(getRandomPwdReq());
				out.flush();
			} catch (Exception e) {
				throw e;
			} finally {
				if (out != null) {
					out.close();
				}
			}

			//接收响应
			InputStream in = null;
			StringBuilder sb = new StringBuilder(1024);
			BufferedReader br = null;
			String temp = null;
			try {
				if (200 == httpURLConnection.getResponseCode()) {
					in = httpURLConnection.getInputStream();
					br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
					while ((temp = br.readLine()) != null) {
						sb.append(temp);
					}
				} else {
					in = httpURLConnection.getErrorStream();
					br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
					while ((temp = br.readLine()) != null) {
						sb.append(temp);
					}
				}

				resultString = sb.toString();
				System.out.println("应答报文:" + resultString);
				try{
					JSONObject jsonObject = new JSONObject(resultString);
					System.out.println(jsonObject.getString("status"));
					System.out.println(jsonObject.getString("msg"));
					/*
					 * 返回值前加数字，用于判断返回内容为动态密钥还是错误信息
					 * 00:正确信息
					 * 01:错误信息
					 * */
					return "01" + "状态：" + jsonObject.getString("status") + "信息：" + jsonObject.getString("msg");
				} catch(Exception e){
//					System.out.println("应答报文不是JSON格式，进行正常处理！");
				}
				
				resultString = new String(DESedeUtil.decryptMode(Base64.decodeBase64(resultString.substring(29)), Constants.DES3_KEY, Constants.ENCODING_GBK), Constants.ENCODING_GBK);
				
				ResponseData response = XmlUtil.converyToJavaBean(resultString, ResponseData.class);
				System.out.println("秘钥随机数：" + response.getRandom());
				String dyn3DesKey = MD5Util.md5(Constants.DES3_KEY + response.getRandom(), Constants.ENCODING_GBK).substring(4, 28);
				System.out.println("秘钥：" + dyn3DesKey);
				Constants.DYN_3DES_KEY = dyn3DesKey;
				Constants.RANDOM = response.getRandom();
				/*
				 * 返回值前加数字，用于判断返回内容为动态密钥还是错误信息
				 * 00:正确信息
				 * 01:错误信息
				 * */
				return "00" + dyn3DesKey;
			}catch (Exception e) {
				throw e;
			} finally {
				if (br != null) {
					br.close();
				}
				if (in != null) {
					in.close();
				}
				if (httpURLConnection != null) {
					httpURLConnection.disconnect();
				}
			}
		}catch (Exception e1) {
			throw e1;
		}
	}
	
	public static String getRandomPwdReq() throws Exception {
		String result = "";
		
		RequestData request = new RequestData();
		request.setCharCode(Constants.ENCODING_UTF8);
		request.setVersion("1.0.0");
		request.setTradeType("0413");
		request.setChnlId(Constants.CHANNEL_ID);
		request.setUserId(Constants.CHANNEL_ID);
		request.setTradeSource("1");
		request.setOrderId(OrderUtil.getDateTimeTemp() + OrderUtil.getRandomStr(2));
		request.setTimeStamp(OrderUtil.getDateTimeTemp());
		request.setMd5ConSec(TransMsgUtil.genMd5ConSec(request));
		
		String xmlStr = XmlUtil.convertToXml(request);
		System.out.println("组装XML报文");
		System.out.println(xmlStr);
		
		byte[] xmlByte = DESedeUtil.encryptMode(xmlStr.getBytes(Constants.ENCODING_UTF8), Constants.DES3_KEY, Constants.ENCODING_UTF8);
		result = Constants.CHANNEL_ID + Constants.CHANNEL_ID + "1" + Base64.encodeBase64String(xmlByte);
		result = TransMsgUtil.addZeroForNum(String.valueOf(xmlByte.length + 25), 4) + result;
		System.out.println("请求报文：" + result);
		return result;
	}
	
	
}
