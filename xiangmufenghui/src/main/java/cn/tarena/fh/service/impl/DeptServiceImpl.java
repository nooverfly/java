package cn.tarena.fh.service.impl;


import cn.tarena.fh.mapper.DeptMapper;
import cn.tarena.fh.pojo.Dept;
import cn.tarena.fh.service.CommitLogService;
import cn.tarena.fh.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
	
	@Autowired
	private DeptMapper deptMapper;

	@Autowired
	private CommitLogService commitLogService;
	
	@Override
	public List<Dept> findAll() {
		
		return deptMapper.findAll();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateState(String[] deptIds, int state) {
		
		deptMapper.updateState(deptIds,state);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteDepts(String[] deptIds) {
		
		deptMapper.deleteDepts(deptIds);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveDept(Dept dept) {
		
		dept.setCreateTime(new Date());  //添加新增日期
		deptMapper.saveDept(dept);
		
	}

	@Override
	public Dept findOne(String deptId) {
		
		return deptMapper.findOne(deptId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateDept(Dept dept) {
		dept.setUpdateTime(new Date());
		deptMapper.updateDept(dept);
		
	}

}
