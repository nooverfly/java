package cn.tarena.fh.mapper;

import cn.tarena.fh.pojo.MUser;
import cn.tarena.fh.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface UserMapper extends BaseMapper<User> {
	//public List<User> findAll();

	public void updateState(@Param("userIds") String[] userIds, @Param("state") int state, @Param("updateTime") Date updateTime);

	public void deleteUser(String[] userIds);

	public void updateUser(User user);//111111

	//public User findOne(String userId);

	public void saveUser(User user);

	@Insert("insert into role_user_p(role_id,user_id) values(#{roleId},#{userId})")
	public void saveUserRole(@Param("roleId") String roleId, @Param("userId") String userId);

	@Delete("delete from role_user_p where user_id = #{userId}")
	public void deleteUserRoleByUserId(String userId);

	@Select("select role_id from role_user_p where user_id = #{userId}")
	public List<String> findRoleIdByUserId(String userId);

	public User findUserByUP(@Param("userName") String userName, @Param("password") String password);
	
	
	//为页面的session中的数据作准备 使用配置为文件查询
	public User findUserByUserName(String userName);
	
	
	public List<String> findModuleListByUserId(String userId);

    //根据部门名称查询用户
    public List<User> findByDeptName(String deptName);

	MUser findUserByMUP(@Param("userName")String userName, @Param("password")String password);
}
