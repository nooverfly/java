package cn.tarena.fh.pojo;

import java.util.Date;

/**
 * 值班表
 * Created by Administrator on 2017/6/3.
 */
public class Onduty {
    private Integer ondutyId;
    private String ondutyLeader;
    private Date ondutyTime;
    private Integer ondutyNum;
    private String remark;

    public String getOndutyLeader() {
        return ondutyLeader;
    }

    public void setOndutyLeader(String ondutyLeader) {
        this.ondutyLeader = ondutyLeader;
    }

    public Integer getOndutyId() {
        return ondutyId;
    }

    public void setOndutyId(Integer ondutyId) {
        this.ondutyId = ondutyId;
    }

    public Date getOndutyTime() {
        return ondutyTime;
    }

    public void setOndutyTime(Date ondutyTime) {
        this.ondutyTime = ondutyTime;
    }

    public Integer getOndutyNum() {
        return ondutyNum;
    }

    public void setOndutyNum(Integer ondutyNum) {
        this.ondutyNum = ondutyNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Onduty{" +
                "ondutyId=" + ondutyId +
                ", ondutyLeader='" + ondutyLeader + '\'' +
                ", ondutyTime=" + ondutyTime +
                ", ondutyNum=" + ondutyNum +
                ", remark='" + remark + '\'' +
                '}';
    }
}
