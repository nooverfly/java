package cn.tarena.fh.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by overfly on 2017/6/5.
 */
public class ActTask implements Serializable {
    private String id;
    private String name;
    private Date createTime;
    private String assignee;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
}
