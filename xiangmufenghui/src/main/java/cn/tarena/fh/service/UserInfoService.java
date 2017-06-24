package cn.tarena.fh.service;


import cn.tarena.fh.pojo.UserInfo;

import java.util.List;

public interface UserInfoService {
	
	//查询上级列表信息
	public List<UserInfo> findManagerList();
}	
