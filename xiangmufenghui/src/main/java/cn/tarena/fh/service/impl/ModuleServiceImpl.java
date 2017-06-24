package cn.tarena.fh.service.impl;

import cn.tarena.fh.mapper.ModuleMapper;
import cn.tarena.fh.pojo.Module;
import cn.tarena.fh.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	private ModuleMapper moduleMapper;

	@Override
	public List<Module> findAll() {
		
		return moduleMapper.findAll();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateState(String[] moduleIds, int state) {
		
		moduleMapper.updateState(moduleIds,state);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteModules(String[] moduleIds) {
		
		//关联删除中间表数据
		moduleMapper.deleteRoleModuleByModuleIds(moduleIds);
		moduleMapper.deleteModules(moduleIds);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveModule(Module module) {
		
		//为module对象准备数据
		module.setModuleId(UUID.randomUUID().toString());
		module.setCreateTime(new Date());
		
		moduleMapper.saveModule(module);
		
	}

	@Override
	public Module findOne(String moduleId) {
		
		return moduleMapper.findOne(moduleId);
	}

	@Override
	public List<Module> findParentModuleList(String moduleId) {
		
		return moduleMapper.findParentModuleList(moduleId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateModule(Module module) {
		module.setUpdateTime(new Date());
		
		moduleMapper.updateModule(module);
	}

	@Override
	public List<String> findRoleModuleListByRoleId(String roleId) {
		
		return moduleMapper.findRoleModuleListByRoleId(roleId);
	}

}
