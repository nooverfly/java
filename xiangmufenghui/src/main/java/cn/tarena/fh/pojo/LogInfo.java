package cn.tarena.fh.pojo;

import java.util.Date;

/**
 * 登陆日志
 * Created by Administrator on 2017/6/1.
 */
public class LogInfo {
    private Integer orderNo;    //序号
    private String userId;      //用户Id
    private String number;      //编号
    private String username;    //用户名
    private Date loginTime;     //登录时间
    private Date logoutTime;    //登出时间

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

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "LogInfo{" +
                "orderNo=" + orderNo +
                ", userId='" + userId + '\'' +
                ", number='" + number + '\'' +
                ", username='" + username + '\'' +
                ", loginTime=" + loginTime +
                ", logoutTime=" + logoutTime +
                '}';
    }
}
