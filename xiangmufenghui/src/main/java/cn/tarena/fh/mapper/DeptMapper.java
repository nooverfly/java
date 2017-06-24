package cn.tarena.fh.mapper;


import cn.tarena.fh.pojo.Dept;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper extends BaseMapper<Dept> {
	//查询全部部门信息
	//public List<Dept> findAll();
	
	//将参数封装为一个Map
	public void updateState(@Param("deptIds") String[] deptIds, @Param("state") int state);
	
	//根据deptId 删除部门信息
	public void deleteDepts(String[] deptIds);
	
	//部门的新增
	public void saveDept(Dept dept);
	
	//根据Id查询数据
	//public Dept findOne(String deptId);
	
	//修改部门信息
	public void updateDept(Dept dept);
}
