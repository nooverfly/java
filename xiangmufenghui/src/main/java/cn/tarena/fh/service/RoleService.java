package cn.tarena.fh.service;

import cn.tarena.fh.pojo.Role;

import java.util.List;

public interface RoleService {
	public List<Role> findAll();

	public void deleteRoles(String[] roleIds);

	public void saveRole(Role role);

	public Role findOne(String roleId);

	public void updateRole(Role role);

	public void saveRoleModule(String roleId, String[] moduleIds);
}
