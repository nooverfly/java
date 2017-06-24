package cn.tarena.fh.mapper;

import cn.tarena.fh.pojo.LogInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */
public interface LogInfoMapper {
    public List<LogInfo> findAll();

    public List<LogInfo> findOne(String userId);
    @Insert("insert into loginfo (number,user_id,username,login_time) values(#{number},#{userId},#{username},#{loginTime})")
    public void toLogin(LogInfo logInfo);
    @Update("update loginfo set logout_time=#{logoutTime} where number=#{number}")
    public void toLogout(LogInfo logInfo);
}
