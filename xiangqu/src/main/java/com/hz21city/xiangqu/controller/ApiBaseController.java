/**
 * 
 */
package com.hz21city.xiangqu.controller;

import java.io.File;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hz21city.xiangqu.common.CommonUtils;

/**
 * 接口控制器基类
 * 
 * @author qiuch
 *
 */
@Controller
public class ApiBaseController {

	// 压缩高度
	private int compressHeight = 200;
	// 压缩宽度
	private int compressWidth = 200;
	// 压缩质量
	private float compressQuaity = 0.9f;
	// gson,设置时间格式，去除html标签
	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();
	// ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(Include.NON_NULL).setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).setTimeZone(TimeZone.getTimeZone("GMT+8:00"))
	// .setVisibility(PropertyAccessor.FIELD, Visibility.ANY).disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	
	protected String getFilePathString(MultipartFile file) {
		String savedPath = "";
		try {
			if (file != null) {
				// 取得当前上传文件的文件名称
				String myFileName = file.getOriginalFilename();
				// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
				if (!CommonUtils.isEmptyString(myFileName)) {
					String savePath = CommonUtils.time2Str(new Date(), "yyyyMMdd") + File.separator;
					String path = CommonUtils.getLocationPath() + savePath;
					// 定义上传路径
					CommonUtils.checkPath(path);
					File localOriginFile = new File(path + myFileName);
					file.transferTo(localOriginFile);
					String extension = myFileName.substring(myFileName.lastIndexOf(".")).toLowerCase();
					String filename = CommonUtils.time2Str(new Date(), "yyyyMMddhhmmssSSS") + "_" + (int) (Math.random() * 100) + extension;
					localOriginFile.renameTo(new File(path + filename));
					if (savePath.contains("/")) {
						savedPath = (savePath + filename).replaceAll(File.separator, "/");
					} else {
						savedPath = (savePath + filename).replaceAll(File.separator + File.separator, "/");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return savedPath;
	}

	protected String getFilePathString(HttpServletRequest request) {
		String savedPath = "";
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			try {
				while (iter.hasNext()) {
					// 取得上传文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if (file != null) {
						// 取得当前上传文件的文件名称
						String myFileName = file.getOriginalFilename();
						// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
						if (!CommonUtils.isEmptyString(myFileName)) {
							String savePath = CommonUtils.time2Str(new Date(), "yyyyMMdd") + File.separator;
							String path = CommonUtils.getLocationPath() + savePath;
							// 定义上传路径
							CommonUtils.checkPath(path);
							File localOriginFile = new File(path + myFileName);
							file.transferTo(localOriginFile);
							String extension = myFileName.substring(myFileName.lastIndexOf(".")).toLowerCase();
							String filename = CommonUtils.time2Str(new Date(), "yyyyMMddhhmmssSSS") + "_" + (int) (Math.random() * 100) + extension;
							localOriginFile.renameTo(new File(path + filename));
							if (savePath.contains("/")) {
								savedPath = (savePath + filename).replaceAll(File.separator, "/");
							} else {
								savedPath = (savePath + filename).replaceAll(File.separator + File.separator, "/");
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
		return savedPath;
	}
	
	public int getCompressHeight() {
		return compressHeight;
	}

	public void setCompressHeight(int compressHeight) {
		this.compressHeight = compressHeight;
	}

	public int getCompressWidth() {
		return compressWidth;
	}

	public void setCompressWidth(int compressWidth) {
		this.compressWidth = compressWidth;
	}

	public float getCompressQuaity() {
		return compressQuaity;
	}

	public void setCompressQuaity(float compressQuaity) {
		this.compressQuaity = compressQuaity;
	}
}
