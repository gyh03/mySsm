package com.gyh.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyh.bean.TFile;
import com.gyh.exception.InvalidCustomException;
import com.gyh.mapper.TFileMapper;
import com.gyh.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	private TFileMapper filemapper;

	@Override
	public Integer insertFiles(List<TFile> files) throws Exception {
		if(CollectionUtils.isEmpty(files)){
			throw new InvalidCustomException("文件信息不可为空");
		}
		Map<String, Object> map =new HashMap<>();
		map.put("list", files);
		return filemapper.insertFiles(map);
	}

}
