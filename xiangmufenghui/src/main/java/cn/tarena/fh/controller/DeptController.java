package cn.tarena.fh.controller;


import cn.tarena.fh.pojo.Dept;
import cn.tarena.fh.service.DeptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DeptController{

	@Autowired
	private  DeptService deptService;

	@RequestMapping("/todeptlist")
	public String findList(Model model){
		List<Dept> deptList = deptService.findAll();
		model.addAttribute("deptList",deptList);

		//跳转到部门列表页面
		return "/dept/deptList";
	}

	@RequestMapping("/mdeptlist")
	@ResponseBody
	public Object deptList() throws JsonProcessingException {
		System.out.println("*******************************************************");
		List<Dept> deptList = deptService.findAll();
		String json = new ObjectMapper().writeValueAsString(deptList);
		System.out.println(json);
		return json;
	}
	

	//状态的改变
	@RequestMapping("stop")
	public String toStop(@RequestParam(value="deptId",required=true) String[] deptIds){
		
		int state = 0;  //表示状态停用
		deptService.updateState(deptIds,state);
		
		//重定向到部门列表页面
		return "redirect:/todeptlist";
	}
	
	@RequestMapping("start")
	public String toStart(@RequestParam(value="deptId",required=true) String[] deptIds){
		int state = 1;  //表示状态停用
		deptService.updateState(deptIds,state);
		
		//重定向到部门列表页面
		return "redirect:/todeptlist";
	}
	
	//部门删除
	@RequestMapping("delete")
	public String toDelete(@RequestParam(value="deptId",required=true)String[] deptIds){
		
		deptService.deleteDepts(deptIds);
		
		return "redirect:/todeptlist";
	}
	
	
	//跳转部门新增页面
	@RequestMapping("tocreate")
	public String toCreate(Model model){
		
		//查询部门全部列表数据
		List<Dept> parentDeptList = deptService.findAll();
		model.addAttribute("parentDeptList", parentDeptList);
		
		return "/dept/deptCreate";
	}
	
	@RequestMapping("save")
	public String saveDept(Dept dept){
		
		deptService.saveDept(dept);
		
		return "redirect:/todeptlist";
	}
	
	@RequestMapping("toupdate")
	public String updateDept(String deptId,Model model){
		
		//准备修改数据
		Dept dept = deptService.findOne(deptId);
		
		//准备上级部门的下拉列表
		List<Dept> parentList = deptService.findAll();
		model.addAttribute("dept", dept);
		model.addAttribute("parentList", parentList);
		
		return "/dept/deptUpdate";
	}
	
	@RequestMapping("update")
	public String updateDept(Dept dept){
		
		deptService.updateDept(dept);
		
		return "redirect:/deptUpdate";
	}
	
	
	//部门查看
	@RequestMapping("toview")
	public String toView(String deptId,Model model){
		
		Dept dept = deptService.findOne(deptId);
		model.addAttribute("dept", dept);
		
		//跳转到查看页面
		return "/dept/deptView";
	}
	
}
