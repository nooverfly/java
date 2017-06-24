package cn.tarena.fh.service.impl;

import cn.tarena.fh.mapper.LogInfoMapper;
import cn.tarena.fh.pojo.LogInfo;
import cn.tarena.fh.service.LogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */
@Service
public class LogInfoServiceImpl implements LogInfoService {
    @Autowired
    private LogInfoMapper logInfoMapper;

    @Override
    public List<LogInfo> findAll() {
        return logInfoMapper.findAll();
    }

    @Override
    public List<LogInfo> findOne(String userId) {
        return logInfoMapper.findOne(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void toLogin(LogInfo logInfo) {
        logInfo.setLoginTime(new Date());
        logInfoMapper.toLogin(logInfo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void toLogout(LogInfo logInfo) {
        logInfo.setLogoutTime(new Date());
        logInfoMapper.toLogout(logInfo);
    }

}
