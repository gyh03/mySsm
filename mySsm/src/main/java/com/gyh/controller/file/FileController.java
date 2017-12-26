package com.gyh.controller.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.gyh.bean.TFile;
import com.gyh.common.pojo.MessageCode;
import com.gyh.common.pojo.MessageResult;
import com.gyh.common.utils.UploadUtils;
import com.gyh.exception.InvalidCustomException;
import com.gyh.service.FileService;
import com.gyh.utils.IDGenerator;
import com.gyh.utils.io.file.FileUtil;

/**
 * @author gyh
 * 文件上传和下载
 */
@Controller
@RequestMapping("/file")
public class FileController {

	@Autowired
	private UploadUtils uploadUtils;
	@Autowired
	private FileService fileService;
	@Value("#{prop['baseUrl']}")
	private String baseUrl;

	//TODO 优化代码，把上传和保存数据库写在service中，先保存，上传不成功回滚sql
	/**
	 * 上传指定属性名为files的文件，允许多文件上传，但都是files对应的文件，
	 * 如果有多个表单属性需要上传文件，接口参数需要定义多个入参
	 * @param files
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/uploadOne",method=RequestMethod.POST)
	public Object uploadOneFile(MultipartFile[] files){
		if(files == null){
			throw new InvalidCustomException("文件不可为空");
		}
		long t1 = System.currentTimeMillis();
		MessageResult result = new MessageResult();
		
		try {
			//文件物理路径
			String uploadPath = null;
			//文件相对路径，用于nginx代理
			String reltPath = null;
			//文件原始名称
			String origName = null;
			//文件名称
			String fileName = null;
			List<TFile> tfiles = new ArrayList<>();
			for (MultipartFile file : files) {
				if(file !=null){
					origName = file.getOriginalFilename();
					fileName = IDGenerator.getTimeLongStr() +"."+ FileUtil.getPostfix(origName);
					reltPath = uploadUtils.getUploadReltPath();
					uploadPath = uploadUtils.getUploadRealPath(reltPath,fileName);
					reltPath += fileName;
					File newFile=new File(uploadPath);
				
					//文件上传
					file.transferTo(newFile);
					
					TFile f = new TFile();
					f.setCreatetime(new Date());
					f.setFileName(fileName);
					f.setFileRealPath(uploadPath);
					f.setFileReltPath(reltPath);
					f.setOrgiName(origName);
//					f.setUrl(url);//暂时不用
					tfiles.add(f);
				}
			}
			
			fileService.insertFiles(tfiles);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidCustomException(MessageCode.uploadError.getMsg()+":"+e.getMessage());
		}
		long t2 = System.currentTimeMillis();
		result.setCode(MessageCode.uploadSuccess.getCode());
		result.setSuccess(MessageCode.uploadSuccess.getFlag());
		result.setMsg(MessageCode.uploadSuccess.getMsg());
		result.setData((t2-t1));
		return result; 
	}
	/**
	 * 一次性获取表单中提交的多有文件
	 * 若某个属性允许上传多个文件，此方法只能获取指定属性上传的第一个文件，此时需要用上面的MultipartFile[]
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/uploadAll",method=RequestMethod.POST)
	public Object uploadAllFile(HttpServletRequest request){
		MessageResult result = new MessageResult();
		long startTime=System.currentTimeMillis();
		try {
			//将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
			CommonsMultipartResolver multipartResolver 	= new CommonsMultipartResolver(request.getSession().getServletContext());
			//检查form中是否有enctype="multipart/form-data"
			if(multipartResolver.isMultipart(request)){
				//将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
				//上传全路径
				String uploadPath = null;
				//文件相对路径，用于nginx代理
				String reltPath = null;
				//文件原始名称
				String origName = null;
				//文件名称
				String fileName = null;
				//表单属性名
				String paramName = null;
				List<TFile> tfiles = new ArrayList<>();
				//获取multiRequest 中所有的文件名
				Iterator<String> iter = multiRequest.getFileNames();
				while(iter.hasNext()){
					paramName = iter.next().toString();
					//一次遍历所有文件
					MultipartFile file = multiRequest.getFile(paramName);
					if(file!=null){
						origName = file.getOriginalFilename();
						fileName = IDGenerator.getTimeLongStr() +"."+ FileUtil.getPostfix(origName);
						reltPath = uploadUtils.getUploadReltPath() ;
						uploadPath =  uploadUtils.getUploadRealPath(reltPath,fileName);
						reltPath += fileName;
						//上传
						file.transferTo(new File(uploadPath));
						
						TFile f = new TFile();
						f.setCreatetime(new Date());
						f.setFileName(fileName);
						f.setFileRealPath(uploadPath);
						f.setFileReltPath(reltPath);
						f.setOrgiName(origName);
//						f.setUrl(url);//暂时不用
						tfiles.add(f);
					}
				}
				fileService.insertFiles(tfiles);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidCustomException(MessageCode.uploadError.getMsg()+":"+e.getMessage());
		}
		long  endTime=System.currentTimeMillis();
		result.setCode(MessageCode.uploadSuccess.getCode());
		result.setSuccess(MessageCode.uploadSuccess.getFlag());
		result.setMsg(MessageCode.uploadSuccess.getMsg());
		result.setData((endTime - startTime));
		return result; 
	}
}
