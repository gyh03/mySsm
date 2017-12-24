package com.gyh.mapper;

import com.gyh.bean.TOpeLogs;
import com.gyh.bean.TOpeLogsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TOpeLogsMapper {
    int countByExample(TOpeLogsExample example);

    int deleteByExample(TOpeLogsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TOpeLogs record);

    int insertSelective(TOpeLogs record);

    List<TOpeLogs> selectByExample(TOpeLogsExample example);

    TOpeLogs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TOpeLogs record, @Param("example") TOpeLogsExample example);

    int updateByExample(@Param("record") TOpeLogs record, @Param("example") TOpeLogsExample example);

    int updateByPrimaryKeySelective(TOpeLogs record);

    int updateByPrimaryKey(TOpeLogs record);
}