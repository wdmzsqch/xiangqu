/**
 * 
 */
package com.hz21city.xiangqu.common;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletResponse;

import com.daoshun.exception.NullParameterException;
import com.daoshun.exception.ShankeException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.utils.MyX509TrustManager;

import net.sf.json.JSONObject;

public class CommonUtils {
	/**
	 * 读取配置文件
	 */
	public static Properties properties = new Properties();

	static {
		try {
			String path = "config.properties";
			InputStream inStream = CommonUtils.class.getClassLoader().getResourceAsStream(path);
			if (inStream == null) {
				inStream = CommonUtils.class.getClassLoader().getResourceAsStream("/" + path);
			}
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 文件访问路径
	public static String getFileRootUrl() {
		return properties.getProperty("fileRootUrl");
	}

	public static String getLocationPath() {
		return properties.getProperty("uploadFilePath");
	}

	public static String getLotteryProp() {
		return properties.getProperty("lotteryProp");
	}

	public static String getWebRootUrl() {
		return properties.getProperty("webRootUrl");
	}

	public static String getAppID() {
		return properties.getProperty("appid");
	}

	public static String getAppKEY() {
		return properties.getProperty("appkey");
	}

	public static String getSiteName() {
		return properties.getProperty("siteName");
	}
	
	public static String getTicketFilePath(){
		return properties.getProperty("uploadTicketFilePath");
	}

	/**
	 * 检查路径是否存在，不存在则创建路径
	 * 
	 * @param path
	 */
	public static void checkPath(String path) {
		String[] paths = null;
		if (path.contains("/")) {
			paths = path.split(File.separator);
		} else {
			paths = path.split(File.separator + File.separator);
		}
		if (paths == null || paths.length == 0) {
			return;
		}
		String pathdir = "";
		for (String string : paths) {
			pathdir = pathdir + string + File.separator;
			File file = new File(pathdir);
			if (!file.exists()) {
				file.mkdir();
			}
		}
	}

	/**
	 * 判断String是否为空
	 * 
	 */
	public static boolean isEmptyString(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * String -> int
	 * 
	 * @param String
	 * @return int
	 */
	public static int parseInt(String string, int def) {
		if (isEmptyString(string))
			return def;
		int num = def;
		try {
			num = Integer.parseInt(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	/**
	 * String -> long
	 * 
	 * @param String
	 * @return long
	 */
	public static long parseLong(String string, long def) {
		if (isEmptyString(string))
			return def;
		long num;
		try {
			num = Long.parseLong(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	/**
	 * String -> double
	 * 
	 * @param String
	 * @return long
	 */
	public static double parseDouble(String string, double def) {
		if (isEmptyString(string))
			return def;
		double num;
		try {
			num = Double.parseDouble(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	/**
	 * String -> float
	 * 
	 * @param String
	 * @return long
	 */
	public static float parseFloat(String string, float def) {
		if (isEmptyString(string))
			return def;
		float num;
		try {
			num = Float.parseFloat(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	/**
	 * String -> float
	 * 
	 * @param String
	 * @return long
	 */
	public static float parseFloat(String string, float def, int digit) {
		if (isEmptyString(string))
			return def;
		float num;
		try {
			num = Float.parseFloat(string);
		} catch (Exception e) {
			num = def;
		}
		if (digit > 0 && num != def) {
			BigDecimal bigDecimal = new BigDecimal(num);
			float twoDecimalP = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			return twoDecimalP;
		} else {
			return num;
		}
	}

	/**
	 * @param date
	 * @param string
	 * @return
	 */
	public static String time2Str(Date date, String string) {
		SimpleDateFormat sdFormat;
		if (isEmptyString(string)) {
			sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			sdFormat = new SimpleDateFormat(string);
		}
		try {
			return sdFormat.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * MD5加密
	 * 
	 * @param 需要加密的String
	 * @return 加密后String
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			// return new String(str).toUpperCase();
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 格式化时间- String转换Date "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param 需格式化的时间
	 * @return 格式化后的时间
	 */
	public static Date str2Date(String date, String format) {
		if (isEmptyString(date))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static double twoDecimalP(Double d) {
		BigDecimal bigDecimal = new BigDecimal(d);
		double twoDecimalP = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return twoDecimalP;
	}

	public static float twoDecimalP(Float d) {
		BigDecimal bigDecimal = new BigDecimal(d);
		float twoDecimalP = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		return twoDecimalP;
	}

	public static String makeOrderNo() {
		String orderno = time2Str(new Date(), "yyyyMMddHHmmss");
		for (int i = 0; i < 3; i++) {
			Random rad = new Random();
			orderno = orderno + rad.nextInt(10);
		}
		return orderno;
	}

	/**
	 * 发送https请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
		} catch (Exception e) {
		}
		return jsonObject;
	}

	/**
	 * 判断参数是否为空
	 * 
	 */
	public static void validateEmpty(String value) throws NullParameterException {
		if (value == null || value.length() == 0) {
			throw new NullParameterException();
		}
	}

	/**
	 * @param date
	 * @param string
	 * @return
	 */
	public static String getTimeFormat(Date date, String string) {
		SimpleDateFormat sdFormat;
		if (isEmptyString(string)) {
			sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			sdFormat = new SimpleDateFormat(string);
		}
		try {
			return sdFormat.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	public static int hasNext(List<?> a) {
		if (a != null && a.size() > 0) {
			return 1;
		}
		return 0;
	}

	public static String sendSms(String moblie, String content) {
		String data = "";
		StringBuilder url = new StringBuilder();
		url.append("http://ums.zj165.com:8888/sms/Api/Send.do?SpCode=");
		url.append("005660");
		url.append("&LoginName=");
		url.append("hz_esy");
		url.append("&Password=");
		url.append("esy1227");
		url.append("&UserNumber=");
		url.append(moblie);
		url.append("&SerialNumber=");
		url.append("&ScheduleTime=");
		url.append("&f=");
		url.append("1");
		url.append("&MessageContent=");
		try {
			url.append(java.net.URLEncoder.encode(content, "GBK"));
			HttpURLConnection conn = (HttpURLConnection) new URL(url.toString()).openConnection();
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			data = br.readLine();
			isr.close();
			is.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public static String softwareSerialNo = properties.getProperty("softwareSerialNo");// 软件序列号,请通过亿美销售人员获取
	public static String key = properties.getProperty("key");// 序列号首次激活时自己设定

	/**
	 * 发送短信、可以发送定时和即时短信 sendSMS(String[] mobiles,String smsContent, String
	 * addSerial, int smsPriority) 1、mobiles 手机数组长度不能超过1000 2、smsContent
	 * 最多500个汉字或1000个纯英文
	 * 、请客户不要自行拆分短信内容以免造成混乱、亿美短信平台会根据实际通道自动拆分、计费以实际拆分条数为准、亿美推荐短信长度70字以内
	 * 3、addSerial 附加码(长度小于15的字符串) 用户可通过附加码自定义短信类别,或添加自定义主叫号码( 联系亿美索取主叫号码列表)
	 * 4、优先级范围1~5，数值越高优先级越高(相对于同一序列号) 5、其它短信发送请参考使用手册自己尝试使用
	 */
	public static String YMsendSms(String moblie, String content) {
		String data = "";
		StringBuilder url = new StringBuilder();
		url.append("http://hprpt2.eucp.b2m.cn:8080/sdkproxy/sendsms.action?cdkey=");
		url.append(softwareSerialNo);
		url.append("&password=");
		url.append("755152");
		url.append("&phone=");
		url.append(moblie);
		url.append("&addserial=");
		url.append("&message=");
		try {
			url.append(java.net.URLEncoder.encode("【享趣100】" + content, "UTF-8"));
			HttpURLConnection conn = (HttpURLConnection) new URL(url.toString()).openConnection();
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String tmp;
			while ((tmp = br.readLine()) != null) {
				data = data + tmp;
			}
			isr.close();
			is.close();
			conn.disconnect();
			System.out.println("sms: mobile"+moblie+"  data:" + data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public static void setMaptoJson(HttpServletResponse response, Map<String, Object> map) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();
		String json = gson.toJson(map);
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setResponseStr(String responseString, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(responseString);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成二维码
	 * 
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public static String creatQrcode(String contentstr) {
		try {
			String path = CommonUtils.getLocationPath() + CommonUtils.getTimeFormat(new Date(), "yyyyMMdd") + File.separator;
			checkPath(path);
			String servicename = CommonUtils.getTimeFormat(new Date(), "yyyyMMddhhmmssSSS") + "_" + (int) (Math.random() * 100) + ".png";
			String content = contentstr;
			Hashtable<EncodeHintType, Object> hintMap = new Hashtable<EncodeHintType, Object>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 800, 800, hintMap);
			int CrunchifyWidth = byteMatrix.getWidth();
			BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();
			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
			graphics.setColor(Color.BLACK);
			for (int i = 0; i < CrunchifyWidth; i++) {
				for (int j = 0; j < CrunchifyWidth; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
			// File fileone = new
			// File(CommonUtils.getLocationPath()+"qrbckg.jpg");
			// BufferedImage ImageOne = ImageIO.read(fileone);
			// int width = ImageOne.getWidth();//图片宽度
			// int height = ImageOne.getHeight();//图片高度
			// //从图片中读取RGB
			// int[] ImageArrayOne = new int[width*height];
			// ImageArrayOne =
			// ImageOne.getRGB(0,0,width,height,ImageArrayOne,0,width);
			//
			// int widthTwo = image.getWidth();//图片宽度
			// int heightTwo = image.getHeight();//图片高度
			// int[] ImageArrayTwo = new int[widthTwo*heightTwo];
			// ImageArrayTwo =
			// image.getRGB(0,0,widthTwo,heightTwo,ImageArrayTwo,0,widthTwo);
			//
			// BufferedImage ImageNew = new
			// BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
			// ImageNew.setRGB(0,0,width,height,ImageArrayOne,0,width);//设置左半部分的RGB
			// ImageNew.setRGB(485,515,widthTwo,heightTwo,ImageArrayTwo,0,widthTwo);//设置右半部分的RGB
			File file1 = new File(path, servicename);
			// 创键编码器，用于编码内存中的图象数据。
			ImageIO.write(image, "png", file1);
			return CommonUtils.getTimeFormat(new Date(), "yyyyMMdd") + "/" + servicename;
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成随即密码
	 * 
	 * @param pwd_len
	 *            生成的密码的总长度
	 * @return 密码的字符串
	 */
	public static String genRandomNum(int pwd_len) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 62;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
				'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	/**
	 * 生成随即密码(纯数字)
	 * 
	 * @param pwd_len
	 *            生成的密码的总长度
	 * @return 密码的字符串
	 */
	public static String getRandomNum(int pwd_len) {
		// 10是因为数组是从0开始的 10个数字
		final int maxNum = 10;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	/**
	 * * 获取指定日期是星期几 参数为null时表示获取当前日期是星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekOfDate(Date date) {
		String[] weekOfDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekOfDays[w];
	}

	/**
	 * 发起https请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JsonObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JsonObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象
			TrustManager[] tm = { (TrustManager) new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			JsonParser jp = new JsonParser();
			jsonObject = (JsonObject) jp.parse(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static String filterEmoji(String source) {
		// return source.replaceAll("[\\uE000-\\uEFFF]", "");
		return source.replaceAll("[^\\u0000-\\uFFFF]", "");
	}

	public static String frontCompWidth(long sourceDate, int formatLength) {
		String newString = String.format("%0" + formatLength + "d", sourceDate);
		return newString;
	}

	public static String unicode2String(String unicode) {
		StringBuffer string = new StringBuffer();
		String[] hex = unicode.split("\\\\u");
		for (int i = 1; i < hex.length; i++) {
			// 转换出每一个代码点
			int data = Integer.parseInt(hex[i], 16);
			// 追加成string
			string.append((char) data);
		}
		return string.toString();
	}

	public static String string2Unicode(String string) {
		StringBuffer unicode = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {
			// 取出每一个字符
			char c = string.charAt(i);
			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}
		return unicode.toString();
	}
	
	public static void creatWaterQrcode(String contentstr, String ticketNum) {
		try {
			String path = CommonUtils.getTicketFilePath() + CommonUtils.getTimeFormat(new Date(), "yyyyMMdd") + File.separator;
			checkPath(path);
			String servicename = ticketNum + ".png";
			String content = contentstr;
			Hashtable<EncodeHintType, Object> hintMap = new Hashtable<EncodeHintType, Object>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 800, 800, hintMap);
			int CrunchifyWidth = byteMatrix.getWidth();
			BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();
			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
			graphics.setColor(Color.BLACK);
			for (int i = 0; i < CrunchifyWidth; i++) {
				for (int j = 0; j < CrunchifyWidth; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
			File file1 = new File(path, servicename);
			// 创键编码器，用于编码内存中的图象数据。
			ImageIO.write(image, "png", file1);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void isInDate(Date date) throws ShankeException {  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String strDate = sdf.format(date);  
	    String strDateBegin = "09:00";
	    String strDateEnd = "20:00";
	    // 截取当前时间时分秒  
	    int strDateH = Integer.parseInt(strDate.substring(11, 13));  
	    int strDateM = Integer.parseInt(strDate.substring(14, 16));  
//	    int strDateS = Integer.parseInt(strDate.substring(17, 19));  
	    // 截取开始时间时分秒  
	    int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));  
	    int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));  
//	    int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));  
	    // 截取结束时间时分秒  
	    int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));  
	    int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));  
//	    int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));  
	    if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {  
	        if (strDateH > strDateBeginH && strDateH < strDateEndH) {// 当前时间小时数在开始时间和结束时间小时数之间   
	        } else if (strDateH == strDateBeginH && strDateM >= strDateBeginM) {// 当前时间小时数等于开始时间小时数，分钟数大于等于开始分钟  , 秒钟数大于等于开始秒数
	        } else if (strDateH >= strDateBeginH && strDateH == strDateEndH && strDateM <= strDateEndM) {// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数  ，秒钟数小于等于开始秒数  
	        }else {  
	        	throw new ShankeException();
	        }  
	    } else {  
	    	throw new ShankeException();
	    }
	}

	public static void GetImageStr(String content, HttpServletResponse response) {
		try {
			Hashtable<EncodeHintType, Object> hintMap = new Hashtable<EncodeHintType, Object>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 800, 800, hintMap);
			int CrunchifyWidth = byteMatrix.getWidth();
			BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();
			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
			graphics.setColor(Color.BLACK);
			for (int i = 0; i < CrunchifyWidth; i++) {
				for (int j = 0; j < CrunchifyWidth; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
			// 创键编码器，用于编码内存中的图象数据。
			ImageIO.write(image, "png", response.getOutputStream());
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}
