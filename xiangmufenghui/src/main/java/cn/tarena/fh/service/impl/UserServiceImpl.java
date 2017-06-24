package cn.tarena.fh.service.impl;


import cn.tarena.fh.mapper.UserInfoMapper;
import cn.tarena.fh.mapper.UserMapper;
import cn.tarena.fh.pojo.MUser;
import cn.tarena.fh.pojo.User;
import cn.tarena.fh.pojo.UserInfo;
import cn.tarena.fh.service.UserService;
import cn.tarena.fh.tool.MD5Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//import cn.tarena.fh.tool.MD5Hash;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private UserInfoMapper userInfoMapper;
	
	@Override
	public List<User> findAll() {
		
		return userMapper.findAll();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateState(String[] userIds, int state) {
		
		Date updateTime = new Date();
		userMapper.updateState(userIds,state,updateTime);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUser(String[] userIds) {
		
		userMapper.deleteUser(userIds);
	}

	@Override
	public User findOne(String userId) {
		
		return userMapper.findOne(userId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUser(User user) {
		//获取UUID
		String userId = UUID.randomUUID().toString();
		
		UserInfo info = new UserInfo();
		info = user.getUserInfo();

		String password = MD5Hash.getMd5Hash(user.getPassword(), user.getUsername());
		user.setUserId(userId);
		user.setCreateTime(new Date());
		user.setPassword(password);
		info.setUserInfoId(userId);
		info.setCreateTime(new Date());
		
		userMapper.saveUser(user);
		userInfoMapper.saveUserInfo(info);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUserRole(String[] roleIds, String userId) {
		
		//为了防止重复的插入  先userId删除表数据
		userMapper.deleteUserRoleByUserId(userId);
		
		//循环插入数据
		for (String roleId : roleIds) {
			userMapper.saveUserRole(roleId,userId);
		}
		
		
	}

	@Override
	public List<String> findRoleIdByUserId(String userId) {
		
		return userMapper.findRoleIdByUserId(userId);
	}

	@Override
	public User findUserByUP(String userName, String password) {
		
		return userMapper.findUserByUP(userName,password);
	}

	@Override
	public User findUserByUserName(String userName) {
		
		return userMapper.findUserByUserName(userName);
	}

	
	//根据userId查询用户所管理的全部模块信息
	@Override
	public List<String> findModuleListByUserId(String userId) {
		
		
		return userMapper.findModuleListByUserId(userId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	@Override
	public List<User> findByDeptName(String deptName) {
		return userMapper.findByDeptName(deptName);
	}

	@Override
	public MUser findUserByMUP(String userName, String password) {
		return userMapper.findUserByMUP(userName,password);
	}

}
