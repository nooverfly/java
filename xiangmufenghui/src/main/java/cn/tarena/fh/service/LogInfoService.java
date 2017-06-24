package cn.tarena.fh.service;

import cn.tarena.fh.pojo.LogInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */
public interface LogInfoService {
    //查询logInfo表所有信息
    public List<LogInfo> findAll();
    //根据userId查询登入登出信息
    public List<LogInfo> findOne(String userId);

    //存储登录信息
    public void toLogin(LogInfo logInfo);

    //退出时存储登出信息
    public void toLogout(LogInfo logInfo);
}
