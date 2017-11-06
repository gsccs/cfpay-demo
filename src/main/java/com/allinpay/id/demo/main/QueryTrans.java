package com.allinpay.id.demo.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;

import com.allinpay.id.demo.domain.RequestData;
import com.allinpay.id.demo.domain.ResponseData;
import com.allinpay.id.demo.util.Constants;
import com.allinpay.id.demo.util.DESedeUtil;
import com.allinpay.id.demo.util.TransMsgUtil;
import com.allinpay.id.demo.util.XmlUtil;



public class QueryTrans {

	public static ResponseData queryTrans(String userId, String orderId, String timeStamp) throws Exception{
		System.setProperty("javax.net.ssl.keyStore", "/certs/server.keystore");
        System.setProperty("javax.net.ssl.keyStorePassword","111111");
        System.setProperty("javax.net.ssl.keyStoreType", "JKS");
        
		System.setProperty("javax.net.ssl.trustStore", "/certs/server.keystore");
		System.setProperty("javax.net.ssl.trustStorePassword","111111");
		HttpsURLConnection.setDefaultHostnameVerifier(new TrustAnyVerifier());
		
		String requestUrl ="https://123.206.54.253:8443/auth-web/trans/transQuery";
		
//		System.setProperty("javax.net.ssl.keyStore", "C:/certs/server.keystore");
//        System.setProperty("javax.net.ssl.keyStorePassword","123456");
//        System.setProperty("javax.net.ssl.keyStoreType", "JKS");
//        
//		System.setProperty("javax.net.ssl.trustStore", "C:/certs/certs/server.keystore");
//		System.setProperty("javax.net.ssl.trustStorePassword","123456");
//		HttpsURLConnection.setDefaultHostnameVerifier(new TrustAnyVerifier());
//		
//		String requestUrl ="https://192.168.2.204:8443/auth-web/trans/apiTrans";
		
		String resultString = "";
		HttpsURLConnection httpURLConnection = null;
		URL url;
		try {
			//发送请求
			url = new URL(requestUrl);
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
				out.print(getTransReq(userId, orderId, timeStamp));
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
				System.out.println(resultString);
				if(null == resultString || "".equals(resultString.trim()) || resultString.length() < 29) {
					return null;
				} else {
					resultString = new String(DESedeUtil.decryptMode(Base64.decodeBase64(resultString.substring(29)), Constants.DYN_3DES_KEY, Constants.ENCODING_UTF8), Constants.ENCODING_UTF8);
				}				
				System.out.println(resultString);
				ResponseData response = XmlUtil.converyToJavaBean(resultString, ResponseData.class);

				System.out.println("交易结果：" + response.getResultCode() + response.getResultDesc());
				return response;
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
	
	public static String getTransReq(String userId, String orderId, String timeStamp) throws Exception {
		String result = "";
		
		RequestData request = new RequestData();
		request.setCharCode(Constants.ENCODING_UTF8);
		request.setVersion("1.0.0");
		request.setTradeType("0415");
		request.setChnlId(Constants.CHANNEL_ID);
		request.setUserId(userId);
		request.setTradeSource("1");
		request.setOrderId(orderId);
		request.setTimeStamp(timeStamp);
		request.setMd5ConSec(TransMsgUtil.genMd5ConSec(request));
		
		String xmlStr = XmlUtil.convertToXml(request);
		System.out.println("组装XML报文");
		System.out.println(xmlStr);
		
		byte[] xmlByte = DESedeUtil.encryptMode(xmlStr.getBytes(Constants.ENCODING_UTF8), Constants.DYN_3DES_KEY, Constants.ENCODING_UTF8);
		result = TransMsgUtil.appendLeft(userId, " ", 12) + Constants.CHANNEL_ID + "1" + Base64.encodeBase64String(xmlByte);
		result = TransMsgUtil.addZeroForNum(String.valueOf(xmlByte.length + 25), 4) + result;
		
		return result;
	}
	
	
}
