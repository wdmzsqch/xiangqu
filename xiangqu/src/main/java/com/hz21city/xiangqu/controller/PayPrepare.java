package com.hz21city.xiangqu.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.common.Constants;
import com.hz21city.xiangqu.netdata.WeiXinEntity;
import com.hz21city.xiangqu.pojo.Order;
import com.hz21city.xiangqu.service.IOrderService;
import com.utils.GetWxOrderno;
import com.utils.RequestHandler;
import com.utils.Sha1Util;
import com.utils.TenpayUtil;


@Controller
@RequestMapping("/user")
public class PayPrepare {

	@Resource
	private IOrderService orderSerivce;
	 /** 
     * 校验信息是否是从微信服务器发过来的。 
     *  
     * @param weChat 
     * @param out 
	 * @throws IOException 
     */  
	@RequestMapping("/wxpayprepare")
    public void wxpayprepare(HttpServletRequest request, HttpServletResponse response) throws IOException {  
    	String orderid = request.getParameter("orderid");
		//共账号及商户相关参数
		String appid = Constants.WX_APPID;
		String backUri = CommonUtils.properties.getProperty("WxPayUrl");
		//授权后要跳转的链接所需的参数一般有会员号，金额，订单号之类，
		//最好自己带上一个加密字符串将金额加上一个自定义的key用MD5签名或者自己写的签名,
		//比如 Sign = %3D%2F%CS% 
		backUri = backUri+"?orderid="+orderid;
//		System.out.println(backUri);
		//URLEncoder.encode 后可以在backUri 的url里面获取传递的所有参数
		backUri = URLEncoder.encode(backUri);
		//scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
				"appid=" + appid+
				"&redirect_uri=" +
				 backUri+
				"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		response.sendRedirect(url);
//		System.out.println("redirectbforer");
    }  
    
	@RequestMapping("/wxpay")
	@ResponseBody
	public HashMap<String,Object> wxpay(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		HashMap<String,Object> result = new HashMap<String,Object>();
		//网页授权后获取传递的参数
//		System.out.println("redirectafter");
		String orderid = request.getParameter("orderid");
		Order order = orderSerivce.getOrderById(CommonUtils.parseLong(orderid, 0));
		double money = order.getTotalPrice();
		//金额转化为分为单位
		float sessionmoney =(float)money;
		String finalmoney = String.format("%.2f", sessionmoney);
		finalmoney = finalmoney.replace(".", "");
		
		String appid = Constants.WX_APPID;
		String appsecret = Constants.WX_APPSECRET;
		String partner = Constants.WX_PARTNER;
		String partnerkey = Constants.WX_PARTNERKEY;
		String openId =String.valueOf(request.getSession().getAttribute("openid"));
		//获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
			String currTime = TenpayUtil.getCurrTime();
			//8位日期
			String strTime = currTime.substring(8, currTime.length());
			//四位随机数
			String strRandom = TenpayUtil.buildRandom(4) + "";
			//10位序列号,可以自行调整。
			String strReq = strTime + strRandom;
			
			
			//商户号
			String mch_id = partner;
			//子商户号  非必输
			//String sub_mch_id="";
			//设备号   非必输
			String device_info="";
			//随机数 
			String nonce_str = strReq;
			//商品描述
			//String body = describe;
			
			//商品描述根据情况修改
			String body = "享趣订单支付";
			//附加数据
			String attach = order.getOrderNo();
			//商户订单号
			String out_trade_no = order.getOrderNo()+TenpayUtil.buildRandom(4);
			int intMoney = Integer.parseInt(finalmoney);
			
			//总金额以分为单位，不带小数点
			int total_fee = intMoney;
			//订单生成的机器 IP
			String spbill_create_ip = request.getRemoteAddr();
			//订 单 生 成 时 间   非必输
//			String time_start ="";
			//订单失效时间      非必输
//			String time_expire = "";
			//商品标记   非必输
//			String goods_tag = "";
			
			//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
			String notify_url =CommonUtils.properties.getProperty("WXNotifyUrl");
			String trade_type = "JSAPI";
			String openid = openId;
			//非必输
//			String product_id = "";
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("appid", appid);  
			packageParams.put("mch_id", mch_id);  
			packageParams.put("nonce_str", nonce_str);  
			packageParams.put("body", body);  
			packageParams.put("attach", attach);  
			packageParams.put("out_trade_no", out_trade_no);  
			
			
			//这里写的金额为1 分到时修改
			packageParams.put("total_fee", ""+total_fee);  
//			packageParams.put("total_fee", "finalmoney");  
			packageParams.put("spbill_create_ip", spbill_create_ip);  
			packageParams.put("notify_url", notify_url);  
			
			packageParams.put("trade_type", trade_type);  
			packageParams.put("openid", openid);  

			RequestHandler reqHandler = new RequestHandler(request, response);
			reqHandler.init(appid, appsecret, partnerkey);
			
			String sign = reqHandler.createSign(packageParams);
			String xml="<xml>"+
					"<appid>"+appid+"</appid>"+
					"<mch_id>"+mch_id+"</mch_id>"+
					"<nonce_str>"+nonce_str+"</nonce_str>"+
					"<sign>"+sign+"</sign>"+
					"<body><![CDATA["+body+"]]></body>"+
					"<attach>"+attach+"</attach>"+
					"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
					//金额，这里写的1 分到时修改
					"<total_fee>"+total_fee+"</total_fee>"+
					//"<total_fee>"+finalmoney+"</total_fee>"+
					"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
					"<notify_url>"+notify_url+"</notify_url>"+
					"<trade_type>"+trade_type+"</trade_type>"+
					"<openid>"+openid+"</openid>"+
					"</xml>";
//			System.out.println(xml);
			String allParameters = "";
			try {
				allParameters =  reqHandler.genPackage(packageParams);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
			String prepay_id="";
			try {
				HashMap map  = new GetWxOrderno().getPayNo(createOrderURL, xml );
				prepay_id = (String)map.get("prepay_id");
//				code_url = (String)map.get("code_url");
				if(prepay_id.equals("")){
					request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
					response.sendRedirect("error.jsp");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SortedMap<String, String> finalpackage = new TreeMap<String, String>();
			String appid2 = appid;
			String timestamp = Sha1Util.getTimeStamp();
			String nonceStr2 = nonce_str;
			String prepay_id2 = "prepay_id="+prepay_id;
			String packages = prepay_id2;
			finalpackage.put("appId", appid2);  
			finalpackage.put("timeStamp", timestamp);  
			finalpackage.put("nonceStr", nonceStr2);  
			finalpackage.put("package", packages);  
			finalpackage.put("signType", "MD5");
			String finalsign = reqHandler.createSign(finalpackage);
			result.put("appid", appid2);
			result.put("timeStamp", timestamp);
			result.put("nonceStr", nonceStr2);
			result.put("packagename", packages);
			result.put("sign", finalsign);
			return result;
	} 
   
	
	@RequestMapping("/getWxPreperSign")
	@ResponseBody
	public HashMap<String,Object> getWxPreperSign(String sign_backurl){
		String currTime = TenpayUtil.getCurrTime();
		//8位日期
		String strTime = currTime.substring(8, currTime.length());
		//四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		//10位序列号,可以自行调整。
		String nonce_str = strTime + strRandom;
		String timestamp = Sha1Util.getTimeStamp();
		String jsapi_ticket = new WeiXinEntity().getJsTicket();
		String appid = Constants.WX_APPID;
		String signstr = "jsapi_ticket="+jsapi_ticket+"&noncestr="+nonce_str+"&timestamp="+timestamp+"&url="+sign_backurl;
		String sign = Sha1Util.getSha1(signstr);
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("nonce_str", nonce_str);
		map.put("timestamp", timestamp);
		map.put("jsapi_ticket", jsapi_ticket);
		map.put("appid", appid);
		map.put("sign", sign);
		return map;
	}
}
