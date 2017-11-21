package com.gyh.service;

import java.util.List;

import com.gyh.bean.TFile;

public interface FileService {
	
	
	/**
	 * 保存文件上传信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Integer insertFiles(List<TFile> files)throws Exception;
}
