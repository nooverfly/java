package cn.tarena.fh.mapper;

import cn.tarena.fh.controller.CommitLogController;
import cn.tarena.fh.pojo.CommitLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/6/2.
 */
public interface CommitLogMapper {
    @Select("select * from commit_log")
    public List<CommitLog> findAll();
    @Select("select * from commit_log where user_id=#{userId}")
    public List<CommitLog> findOne(String userId);
    @Insert("insert into commit_log (user_id,username,state,operation_content,commit_time) values(#{userId},#{username},#{state},#{operationContent},#{commitTime})")
    public void saveCommit(CommitLog commitLog);
}
