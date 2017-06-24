package cn.tarena.fh.mapper;


import cn.tarena.fh.pojo.UserInfo;

import java.util.List;

public interface UserInfoMapper {
	public List<UserInfo> findManagerList();

	public void saveUserInfo(UserInfo info);
}
