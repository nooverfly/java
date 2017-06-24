package cn.tarena.fh.service;


import cn.tarena.fh.pojo.Dept;

import java.util.List;

public interface DeptService {
	public List<Dept> findAll();

	public void updateState(String[] deptIds, int state);

	public void deleteDepts(String[] deptIds);

	public void saveDept(Dept dept);

	public Dept findOne(String deptId);

	public void updateDept(Dept dept);
}
