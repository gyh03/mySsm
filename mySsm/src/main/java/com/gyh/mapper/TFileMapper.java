package com.gyh.mapper;

import com.gyh.bean.TFile;
import com.gyh.bean.TFileExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TFileMapper {
	
    Integer countByExample(TFileExample example);

    Integer deleteByExample(TFileExample example);

    Integer deleteByPrimaryKey(Integer id);

    Integer insert(TFile record);

    Integer insertSelective(TFile record);

    List<TFile> selectByExample(TFileExample example);

    TFile selectByPrimaryKey(Integer id);

    Integer updateByExampleSelective(@Param("record") TFile record, @Param("example") TFileExample example);

    Integer updateByExample(@Param("record") TFile record, @Param("example") TFileExample example);

    Integer updateByPrimaryKeySelective(TFile record);

    Integer updateByPrimaryKey(TFile record);
    
    Integer insertFiles(Map<String, Object> param)throws Exception;
}