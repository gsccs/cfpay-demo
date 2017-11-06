package com.allinpay.id.demo.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.codec.binary.Base64;

import org.json.JSONObject;

import com.allinpay.id.demo.domain.RequestData;
import com.allinpay.id.demo.domain.ResponseData;
import com.allinpay.id.demo.util.Constants;
import com.allinpay.id.demo.util.DESedeUtil;
import com.allinpay.id.demo.util.OrderUtil;
import com.allinpay.id.demo.util.TransMsgUtil;
import com.allinpay.id.demo.util.XmlUtil;

public class ApiTrans {
	
	private static String keyPwd = "111111";
	private static String trustPwd = "111111";
	
	
	public static ResponseData doTrans(Map<String, String> map) throws Exception {
		System.setProperty("javax.net.ssl.keyStore", "/certs/server.keystore");
		System.setProperty("javax.net.ssl.keyStorePassword", keyPwd);
		System.setProperty("javax.net.ssl.keyStoreType", "JKS");

		System.setProperty("javax.net.ssl.trustStore", "/certs/server.keystore");
		System.setProperty("javax.net.ssl.trustStorePassword", trustPwd);
		HttpsURLConnection.setDefaultHostnameVerifier(new TrustAnyVerifier());

		String resultString = "";
		HttpsURLConnection httpURLConnection = null;
		URL url;
		try {
			// 发送请求
			url = new URL(Constants.URL_TRADE);
			httpURLConnection = (HttpsURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(50000);
			httpURLConnection.setReadTimeout(50000);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
			httpURLConnection.setRequestMethod("POST");

			PrintStream out = null;
			try {
				httpURLConnection.connect();
				out = new PrintStream(httpURLConnection.getOutputStream(), false, "UTF-8");
				out.print(getTransReq(map));
				out.flush();
			} catch (Exception e) {
				throw e;
			} finally {
				if (out != null) {
					out.close();
				}
			}

			// 接收响应
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
				try {
					JSONObject jsonObject = new JSONObject(resultString);
					System.out.println(jsonObject.getString("status"));
					System.out.println(jsonObject.getString("msg"));
					/*
					 * 返回值前加数字，用于判断返回内容为动态密钥还是错误信息 00:正确信息 01:错误信息
					 */
					ResponseData responseData = new ResponseData();
					responseData.setResultCode(jsonObject.getString("status"));
					responseData.setResultDesc(jsonObject.getString("msg"));
					return responseData;
				} catch (Exception e) {
					// System.out.println("应答报文不是JSON格式，进行正常处理！");
				}

				if (null == resultString || "".equals(resultString.trim()) || resultString.length() < 29) {
					return null;
				} else {
					resultString = new String(DESedeUtil.decryptMode(Base64.decodeBase64(resultString.substring(29)),
							Constants.DYN_3DES_KEY, Constants.ENCODING_UTF8), Constants.ENCODING_UTF8);
				}
				ResponseData response = XmlUtil.converyToJavaBean(resultString, ResponseData.class);

				System.out.println(resultString);
				System.out.println("验证结果：" + response.getResultCode() + response.getResultDesc());
				return response;
			} catch (Exception e) {
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
		} catch (Exception e1) {
			throw e1;
		}
	}

	public static String getTransReq(Map<String, String> map) throws Exception {
		String result = "";

		RequestData request = new RequestData();
		request.setCharCode(Constants.ENCODING_UTF8);
		request.setVersion("1.0.0");
		request.setTradeType(Constants.TRADE_TYPE);
		request.setChnlId(Constants.CHANNEL_ID);
		request.setUserId(Constants.USER_ID);
		request.setTradeSource("1");
		request.setOrderId(OrderUtil.getDateTimeTemp() + OrderUtil.getRandomStr(2));
		request.setTimeStamp(OrderUtil.getDateTimeTemp());
		request.setAccNo(map.get("accNo"));
		request.setName(map.get("name"));
		request.setCertificateCode(map.get("certificateCode"));
		request.setNbr(map.get("nbr"));
		request.setCVN2(map.get("cvn2"));
		request.setExpired(map.get("expired"));
		request.setParams(map.get("params"));
		request.setBusinessName(map.get("businessName"));
		request.setMd5ConSec(TransMsgUtil.genMd5ConSec(request));

		String xmlStr = XmlUtil.convertToXml(request);
		System.out.println("组装XML报文");
		System.out.println(xmlStr);

		byte[] xmlByte = DESedeUtil.encryptMode(xmlStr.getBytes(Constants.ENCODING_UTF8), Constants.DYN_3DES_KEY,
				Constants.ENCODING_UTF8);
		result = TransMsgUtil.appendLeft(Constants.USER_ID, " ", 12) + Constants.CHANNEL_ID + "1"
				+ Base64.encodeBase64String(xmlByte);
		result = TransMsgUtil.addZeroForNum(String.valueOf(xmlByte.length + 25), 4) + result;
		System.out.println("请求报文：" + result);
		return result;
	}
}
