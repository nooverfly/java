package cn.tarena.fh.service;

import cn.tarena.fh.pojo.DiaryEntry;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */
public interface DiaryService {
    //日记录入
    public void saveDiaryEntry(DiaryEntry diaryEntry);
    //根据userId查询日记信息
    public List<DiaryEntry> findByUserId(String userId);
    //根据部门名称查询所有日记记录
    public List<DiaryEntry> findByDeptName(String deptName);
    //根据时间查询日记
    public List<DiaryEntry> findByTime(String userId, Date startTime, Date endTime);
}
