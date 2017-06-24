package cn.tarena.fh.pojo;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/2.
 */
public class CommitLog {
    private Integer orderNo;     //排序号
    private String userId;       //用户Id
    private String username;    //用户名
    private Integer state;      //操作类型  1:增,2:删除 3:修改
    private String operationContent;  //操作内容
    private Date commitTime;   //操作时间

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    @Override
    public String toString() {
        return "CommitLog{" +
                "orderNo=" + orderNo +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", state=" + state +
                ", operationContent='" + operationContent + '\'' +
                ", commitTime=" + commitTime +
                '}';
    }
}
