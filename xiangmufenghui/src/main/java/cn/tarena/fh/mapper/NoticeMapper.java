package cn.tarena.fh.mapper;

import cn.tarena.fh.pojo.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */
public interface NoticeMapper {


    //查询所有的公告
    public List<Notice> findAll();

    //根据noticeId查询公告
    public Notice findNoticeByNoticeId(String noticeId);

    //更新公告
    public void updateNoticeByNoticeId(@Param("notice") Notice notice, @Param("noticeId") String noticeId);

    //保存公告
    public void saveNotice(Notice notice);

    //根据noticeId删除公告
    public void deleteNotice(String[] noticeIds);

    List<Notice> findNoticeByNoticeTitleOrCreateBy(String message);
}
