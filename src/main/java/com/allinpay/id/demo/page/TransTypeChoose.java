/**
 *  TransTypeChoose.java -- Mz
 *
 *  2016年8月12日-上午9:32:24
 */
package com.allinpay.id.demo.page;

import java.io.IOException;

import com.allinpay.id.demo.main.GetDynKey;
import com.allinpay.id.demo.util.Constants;
import com.allinpay.id.trade.type.*;

/**
 * @author Mz
 * 
 * @time 2016年8月12日上午9:32:24
 */
public class TransTypeChoose {
	/**
	 * @param tradeType
	 */
	public TransTypeChoose() {

		/**
		 * 0405: 卡号 + 姓名 0407: 卡号 + 身份证号 0409 ： 卡号 + 姓名 + 身份证号 0411 ： 卡号 + 姓名 +
		 * 身份证号 0413: 动态密钥 0415: 交易查询 0417: 姓名 + 身份证号 0419: 姓名 + 手机号 0421： 姓名 +
		 * 手机号 + 身份证号 0435: 火眼 0437: 学历认证  0461:工商信息查询
		 */
		System.out.println("交易类型：" + Constants.TRADE_TYPE);
		if ("0405".equals(Constants.TRADE_TYPE)) {
			AccnoName accnoName = new AccnoName("验证");
			accnoName.setVisible(true);
		} else if ("0407".equals(Constants.TRADE_TYPE)) {
			AccnoID accnoId = new AccnoID("验证");
			accnoId.setVisible(true);
		} else if ("0409".equals(Constants.TRADE_TYPE)) {
			AccnoNameID accnoNameId = new AccnoNameID("验证");
			accnoNameId.setVisible(true);
		} else if ("0411".equals(Constants.TRADE_TYPE)) {
			AccnoNameCpnID accnoNameCphID = new AccnoNameCpnID("验证");
			accnoNameCphID.setVisible(true);
		} else if ("0413".equals(Constants.TRADE_TYPE)) {
			try {
				GetDynKey.getDynKey();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ResultPage result = null;
			try {
				result = new ResultPage(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result.setVisible(true);
		} else if ("0415".equals(Constants.TRADE_TYPE)) {
			TransQueryPage transQuery = new TransQueryPage();
			transQuery.setVisible(true);
		} else if ("0417".equals(Constants.TRADE_TYPE)) {
			NameID nameId = new NameID("验证");
			nameId.setVisible(true);
		} else if ("0433".equals(Constants.TRADE_TYPE)) {
			NameID nameId = new NameID("验证");
			nameId.setVisible(true);
		} else if ("0419".equals(Constants.TRADE_TYPE)) {
			NameCpn nameCpn = new NameCpn("验证");
			nameCpn.setVisible(true);
		} else if ("0421".equals(Constants.TRADE_TYPE)) {
			NameCpnID nameCpnID = new NameCpnID("验证");
			nameCpnID.setVisible(true);
		} else if ("0435".equals(Constants.TRADE_TYPE)) {
			FireEye fireEye = new FireEye("查询");
			fireEye.setVisible(true);
		} else if ("0437".equals(Constants.TRADE_TYPE)) {
			DegreeCertificate degreeCertificate = new DegreeCertificate("查询");
			degreeCertificate.setVisible(true);
		} else if ("0441".equals(Constants.TRADE_TYPE)) {
			PhoneNumberState phoneNumberState = new PhoneNumberState("查询");
			phoneNumberState.setVisible(true);
		} else if ("0443".equals(Constants.TRADE_TYPE)) {
			IdCardImageCertificate idCardImageCertificate = new IdCardImageCertificate("验证");
			idCardImageCertificate.setVisible(true);
		} else if ("0445".equals(Constants.TRADE_TYPE)) {
			LowImageCompare lowImageCompare = new LowImageCompare("验证");
			lowImageCompare.setVisible(true);
		} else if ("0447".equals(Constants.TRADE_TYPE)) {
			HighImageCompare highImageCompare = new HighImageCompare("验证");
			highImageCompare.setVisible(true);
		} else if ("0601".equals(Constants.TRADE_TYPE)) {
			AccnoNameIDPhoneCVN2Validate accnoNameIDPhoneCVN2Validate = new AccnoNameIDPhoneCVN2Validate("验证");
			accnoNameIDPhoneCVN2Validate.setVisible(true);
		} else if ("0439".equals(Constants.TRADE_TYPE)) {
			BadInformation badInformation = new BadInformation("验证");
			badInformation.setVisible(true);
		} else if ("0461".equals(Constants.TRADE_TYPE)) {
			Business bis = new Business("验证");
			bis.setVisible(true);
		} else if ("0449".equals(Constants.TRADE_TYPE)) {
			FlightInfo fi = new FlightInfo("验证");
			fi.setVisible(true);
		} else if ("0451".equals(Constants.TRADE_TYPE)) {
			HighClassInfo fci = new HighClassInfo("验证");
			fci.setVisible(true);
		} else if ("0453".equals(Constants.TRADE_TYPE)) {
			TakePlaneInfo tpi = new TakePlaneInfo("验证");
			tpi.setVisible(true);
		} else if ("0455".equals(Constants.TRADE_TYPE)) {
			CrossBorderTravelInfo cbti = new CrossBorderTravelInfo("验证");
			cbti.setVisible(true);
		} else if ("0457".equals(Constants.TRADE_TYPE)) {
			Company4 cp4 = new Company4("验证");
			cp4.setVisible(true);
		} else if ("0459".equals(Constants.TRADE_TYPE)) {
			BondInfo bi = new BondInfo("验证");
			bi.setVisible(true);
		} else if ("0463".equals(Constants.TRADE_TYPE)) {
			KfzxNameIDCard bai = new KfzxNameIDCard("验证");
			bai.setVisible(true);
		} else if ("0465".equals(Constants.TRADE_TYPE)) {
			KfzxNameIDCardPhoto knip = new KfzxNameIDCardPhoto("验证");
			knip.setVisible(true);
		} else if ("0467".equals(Constants.TRADE_TYPE)) {
			KfzxNameIDCardAddress knia = new KfzxNameIDCardAddress("验证");
			knia.setVisible(true);
		} else if ("0469".equals(Constants.TRADE_TYPE)) {
			KfzxNameIDCardPhotoAddress knipa = new KfzxNameIDCardPhotoAddress("验证");
			knipa.setVisible(true);
		}
	}

}
