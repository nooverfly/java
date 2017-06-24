package cn.tarena.fh.service;

import cn.tarena.fh.pojo.CommitLog;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */
public interface CommitLogService {
    //查询所有操作信息
    public List<CommitLog> findAll();
    //根据userId查询操作信息
    public List<CommitLog> findOne(String userId);
    //插入数据
    public void saveCommit(CommitLog commitLog);
}
