package cn.tarena.fh.service.impl;

import cn.tarena.fh.mapper.CommitLogMapper;
import cn.tarena.fh.pojo.CommitLog;
import cn.tarena.fh.service.CommitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */
@Service
public class CommitLogServiceImpl implements CommitLogService {
    @Autowired
    private CommitLogMapper commitLogMapper;
    @Override
    public List<CommitLog> findAll() {
         return commitLogMapper.findAll();

    }

    @Override
    public List<CommitLog> findOne(String userId) {
        return commitLogMapper.findOne(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveCommit(CommitLog commitLog) {
        commitLogMapper.saveCommit(commitLog);
    }
}
