package cn.tarena.fh.mapper;

import cn.tarena.fh.pojo.Module;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ModuleMapper extends BaseMapper<Module> {
	
	//public List<Module> findAll();

	public void updateState(@Param("moduleIds") String[] moduleIds, @Param("state") int state);

	public void deleteModules(String[] moduleIds);
	
	public void saveModule(Module module);
	
	//public Module findOne(String moduleId);
	
	@Select("select * from module_p where module_id not in (#{moduleId})")
	public List<Module> findParentModuleList(String moduleId);

	public void updateModule(Module module);
	
	@Select("select module_id from role_module_p where role_id = #{roleId}")
	public List<String> findRoleModuleListByRoleId(String roleId);

	public void deleteRoleModuleByModuleIds(String[] moduleIds);
}
