package cn.tarena.fh.service;

import cn.tarena.fh.pojo.Notice;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */

public interface NoticeService {

    public List<Notice> findAll();

    public Notice findNoticeByNoticeId(String noticeId);

    public void updateNoticeByNoticeId(Notice notice, String noticeId);

    public void saveNotice(Notice notice);

    public void deleteNotice(String[] noticeIds);

    List<Notice> findNoticeByNoticeTitleOrCreateBy(String message);
}
