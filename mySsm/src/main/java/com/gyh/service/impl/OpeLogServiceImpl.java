package com.gyh.service.impl;

import com.gyh.bean.TOpeLogs;
import com.gyh.mapper.TOpeLogsMapper;
import com.gyh.service.OpeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpeLogServiceImpl implements OpeLogService {

    @Autowired
    private TOpeLogsMapper opeLogsMapper;

    @Override
    public int saveOpeLog(TOpeLogs log) {
        Integer i = opeLogsMapper.insertSelective(log);
        i = i==null?0:i;
        return i;
    }
}
