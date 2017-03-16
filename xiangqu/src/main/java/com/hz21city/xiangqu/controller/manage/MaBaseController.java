/**
 * 
 */
package com.hz21city.xiangqu.controller.manage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.hz21city.xiangqu.common.CommonUtils;

@Controller
@RequestMapping("/manage")
public class MaBaseController {
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
	
	public static String createFile(String fileName,String content, String title) throws Exception{
		String path = null;
		if(fileName != null){
			String savePath = CommonUtils.time2Str(new Date(), "yyyyMMdd") + File.separator;
			String tempPath = CommonUtils.getLocationPath() + savePath;
			CommonUtils.checkPath(tempPath);
			// 取得后缀名
			String extension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
			// 时间戳加两位随机数据
			String severPicName = CommonUtils.time2Str(new Date(), "yyyyMMddhhmmssSSS") + "_" + (int) (Math.random() * 100) + extension;
			// 上传文件保存路径
			File file = new File(tempPath);
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			File savefile = new File(file, severPicName);
				savefile.createNewFile();
				OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(savefile),"UTF-8");
				BufferedWriter out=new BufferedWriter(write);
				Pattern p = Pattern.compile("<img\\s*([^>]*)>");
				Matcher m = p.matcher(content);
				while (m.find()) { 
		             String s0 = m.group(); 
		             String str = s0.replaceAll(CommonUtils.getFileRootUrl()+CommonUtils.time2Str(new Date(), "yyyyMMdd")+"/", "");
		             content = content.replace(m.group(), str);
				 } 
				out.write("<head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><title>"+title+"</title></head>");
				out.write(content);
				out.close();
				if (savePath.contains("/")) {
					path = (savePath + severPicName).replaceAll(File.separator, "/");
				} else {
					path = (savePath + severPicName).replaceAll(File.separator + File.separator, "/");
				}
		}
		return path;
	}
}
