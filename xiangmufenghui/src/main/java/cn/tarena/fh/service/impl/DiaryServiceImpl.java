package cn.tarena.fh.service.impl;

import cn.tarena.fh.mapper.DiaryMapper;
import cn.tarena.fh.pojo.CommitLog;
import cn.tarena.fh.pojo.DiaryEntry;
import cn.tarena.fh.service.CommitLogService;
import cn.tarena.fh.service.DiaryService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */
@Service
public class DiaryServiceImpl implements DiaryService {
     @Autowired
     private DiaryMapper diaryMapper;
     @Autowired
     private CommitLogService commitLogService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveDiaryEntry(DiaryEntry diaryEntry) {
        diaryEntry.setCreateTime(new Date());
        diaryMapper.saveDiary(diaryEntry);
        CommitLog commitLog=new CommitLog();
        commitLog.setUserId(diaryEntry.getUserId());
        commitLog.setUsername(diaryEntry.getUsername());
        commitLog.setState(1);
        commitLog.setOperationContent("录入一条日志");
        commitLog.setCommitTime(new Date());
        commitLogService.saveCommit(commitLog);
    }

    @Override
    public List<DiaryEntry> findByUserId(String userId) {
        return diaryMapper.findByUserId(userId);
    }

    @Override
    public List<DiaryEntry> findByDeptName(String deptName) {
        return diaryMapper.findByDeptName(deptName);
    }

    @Override
    public List<DiaryEntry> findByTime(String userId, Date startTime, Date endTime) {
        return diaryMapper.findByTime(userId,startTime,endTime);
    }
}
