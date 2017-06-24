package cn.tarena.fh.pojo;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/2.
 */
public class DiaryEntry {
    private Integer orderNo;  //排序号
    private String userId;
    private String username;  //用户名
    private String deptName;  //部门名称
    private Date createTime;  //日期
    private String workContent; //工作内容
    private String outcome;     //工作结果
    private String remark;     //备注信息


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DiaryEntry{" +
                "orderNo=" + orderNo +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", deptName='" + deptName + '\'' +
                ", createTime=" + createTime +
                ", workContent='" + workContent + '\'' +
                ", outcome='" + outcome + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
