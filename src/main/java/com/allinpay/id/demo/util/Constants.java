package com.allinpay.id.demo.util;

public class Constants {

	/** GBK编码 */
	public static final String ENCODING_GBK = "GBK";
	/** UTF-8编码 */
	public static final String ENCODING_UTF8 = "UTF-8";
	/** 交易类型-动态秘钥 */
	public static final String TRANS_TP_DY_KEY = "dykey";
	/** 交易类型-实名验证֤ */
	public static final String TRANS_TP_ID_TRANS = "idtrans";
	/** 交易类型 */
	public static String TRADE_TYPE = "";
	/** 动态3DES秘钥 */
	public static String DYN_3DES_KEY = "";
	/** 动态秘钥随机数 */
	public static String RANDOM = "";
	
	public static final String URL_DYN = Constants.URL + "/auth-web/trans/getDynKey";
	
	public static final String URL_TRADE = Constants.URL + "/auth-web/trans/apiTrans";

		
	/** 版本信息 */
	public static final String VERSION_ID = "20161115";
	
	/** 请根据橙付分配的信息填写，如有问题可咨询橙付技术支持人员 **/
	/** 用户 */
	public static final String USER_ID = "testerA";
	/** 渠道 */
	public static final String CHANNEL_ID = "CF0000000005";
	/** 3DES秘钥 */
	public static final String DES3_KEY = "b8hfsrgh5c073b3c1np6s5ir";
	/** MD5秘钥 */
	public static final String MD5_KEY = "2a7bee7f437a27816673c3ecbbd2786c";
	
	//public static final String URL = "https://60.205.113.99:443";
	public static final String URL = "https://60.205.113.133:8489";
	
}
