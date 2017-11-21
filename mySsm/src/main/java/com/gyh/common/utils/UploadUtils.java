package com.gyh.common.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gyh.utils.date.DateUtil;
import com.gyh.utils.io.file.FileUtil;

/**
 * @author gyh
 * 文件上传路径处理
 */
@Component
public class UploadUtils {
	
	@Value("#{prop['uploadBasePath_win']}")
	private String basePath_win;
	@Value("#{prop['uploadBasePath_Linux']}")
	private String basePath_linux;
	
	private String separator = System.getProperty("file.separator");

	/**
	 * 获取系统配置的文件保存基础路径
	 * @return
	 */
	public String getupLoadBasePath() {
		String basePath = null;
		String os = System.getProperty("os.name");  
		if(os != null && os.toLowerCase().startsWith("win")){  
			basePath = basePath_win; 
		}  else{
			basePath = basePath_linux;
		}
		return basePath;
	}
	
	/**
	 * 获取文件上传的全路径，并创建路径
	 * @param fileName 文件名称
	 * @return
	 */
	public String getUploadRealPath(String raltPath,String fileName) {
		String allPath = null;
		String os = System.getProperty("os.name");  
		if(os != null && os.toLowerCase().startsWith("win")){  
			allPath = basePath_win; 
		}  else{
			allPath = basePath_linux ;
		}
		allPath +=  separator + raltPath ;
		FileUtil.mkdir(allPath);
		allPath += fileName;
		return allPath;
	}
	/**
	 * 获取文件相对路径
	 * @return
	 */
	public String getUploadReltPath() {
		Date today = new Date();
		//相对
		String today_ = DateUtil.dateToDateString(today, DateUtil.DATAFORMAT_STR);
		return today_ + separator;
	}
}
