package cn.tarena.fh.service.impl;

import cn.tarena.fh.mapper.LeaveBillMapper;
import cn.tarena.fh.pojo.CommitLog;
import cn.tarena.fh.pojo.LeaveBill;
import cn.tarena.fh.pojo.User;
import cn.tarena.fh.service.CommitLogService;
import cn.tarena.fh.service.LeaveBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/4.
 */
@Service
public class LeaveBillServiceImpl implements LeaveBillService {

    @Autowired
    private LeaveBillMapper leaveBillMapper;
    @Autowired
    private CommitLogService commitLogService;
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveLeaveBill(LeaveBill leaveBill){
        leaveBillMapper.insert(leaveBill);
        CommitLog commitLog=new CommitLog();
        commitLog.setUserId(leaveBill.getUser().getUserId());
        commitLog.setUsername(leaveBill.getUser().getUsername());
        commitLog.setState(1);
        commitLog.setOperationContent(commitLog.getUsername()+"提交请假条");
        commitLog.setCommitTime(new Date());
        commitLogService.saveCommit(commitLog);

    }

    @Override
    public LeaveBill findLeaveBillById(Integer id) {
        return leaveBillMapper.findOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteLeaveBillById(Integer id) {
        LeaveBill leaveBill = leaveBillMapper.findOne(id);
        CommitLog commitLog=new CommitLog();
        commitLog.setUserId(leaveBill.getUser().getUserId());
        commitLog.setUsername(leaveBill.getUser().getUsername());
        commitLog.setState(2);
        commitLog.setOperationContent(leaveBill.getUser().getUsername()+"删除一个请假条");
        commitLog.setCommitTime(new Date());
        commitLogService.saveCommit(commitLog);
        leaveBillMapper.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateLeaveBill(LeaveBill leaveBill) {
        leaveBill.setLeaveDate(new Date());
        leaveBillMapper.update(leaveBill);
    }
}
