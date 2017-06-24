package cn.tarena.fh.pojo;

import java.util.Date;

/**
 * 公告表
 * Created by Administrator on 2017/6/2.
 */
public class Notice {
    private Integer id;         //序号
    private String noticeId;    //公告ID
    private String noticeTitle;     //公告标题
    private String noticeMessage;   //公告内容
    private Date createTime;        //公告创建时间
    private String createBy;        //公告创建人

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeMessage() {
        return noticeMessage;
    }

    public void setNoticeMessage(String noticeMessage) {
        this.noticeMessage = noticeMessage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeId='" + noticeId + '\'' +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeMessage='" + noticeMessage + '\'' +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                '}';
    }
}
