package cn.tarena.fh.service.impl;

import cn.tarena.fh.mapper.NoticeMapper;
import cn.tarena.fh.pojo.Notice;
import cn.tarena.fh.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/6/2.
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> findAll() {
        return noticeMapper.findAll();
    }

    @Override
    public Notice findNoticeByNoticeId(String noticeId) {
        return noticeMapper.findNoticeByNoticeId(noticeId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateNoticeByNoticeId(Notice notice, String noticeId) {
        noticeMapper.updateNoticeByNoticeId(notice,noticeId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveNotice(Notice notice) {

        String noticeId = UUID.randomUUID().toString();
        notice.setNoticeId(noticeId);

        notice.setCreateTime(new Date());

        noticeMapper.saveNotice(notice);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteNotice(String[] noticeIds) {
        noticeMapper.deleteNotice(noticeIds);
    }

    @Override
    public List<Notice> findNoticeByNoticeTitleOrCreateBy(String message) {

        return noticeMapper.findNoticeByNoticeTitleOrCreateBy(message);

    }



}
