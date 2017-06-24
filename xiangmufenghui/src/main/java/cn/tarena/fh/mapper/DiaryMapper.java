package cn.tarena.fh.mapper;

import cn.tarena.fh.pojo.DiaryEntry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */
public interface DiaryMapper {
    @Insert("insert into diary_entry (username,user_id,dept_name,create_time,work_content,outcome,remark) values (#{username},#{userId},#{deptName},#{createTime},#{workContent},#{outcome},#{remark})")
    public void saveDiary(DiaryEntry diaryEntry);
    @Select("select * from diary_entry where user_id=#{userId}")
    public List<DiaryEntry> findByUserId(String userId);
    @Select("select * from diary_entry where dept_name=#{deptName}")
    public List<DiaryEntry> findByDeptName(String deptName);
    @Select("select * from diary_entry where user_id=#{userId} and create_time>=#{startTime} and create_time<=#{endTime}")
    public List<DiaryEntry> findByTime(@Param("userId") String userId,@Param("startTime") Date startTime,@Param("endTime") Date endTime);
}
