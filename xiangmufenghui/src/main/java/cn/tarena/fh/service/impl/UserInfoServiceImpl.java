package cn.tarena.fh.service.impl;


import cn.tarena.fh.mapper.UserInfoMapper;
import cn.tarena.fh.pojo.UserInfo;
import cn.tarena.fh.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	
	@Override
	public List<UserInfo> findManagerList() {

		return userInfoMapper.findManagerList();
	}

}
