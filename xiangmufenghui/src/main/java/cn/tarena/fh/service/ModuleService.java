package cn.tarena.fh.service;

import cn.tarena.fh.pojo.Module;

import java.util.List;

public interface ModuleService {
	
	public List<Module> findAll();

	public void updateState(String[] moduleIds, int state);

	public void deleteModules(String[] moduleIds);

	public void saveModule(Module module);

	public Module findOne(String moduleId);

	public List<Module> findParentModuleList(String moduleId);

	public void updateModule(Module module);

	public List<String> findRoleModuleListByRoleId(String roleId);
}
