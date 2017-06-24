package cn.tarena.fh.controller;

import cn.tarena.fh.pojo.Module;
import cn.tarena.fh.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ModuleController {
	
	@Autowired
	private ModuleService moduleService;
	
	
	@RequestMapping("/tomodulelist")
	public String findAll(Model model){
		List<Module>  moduleList = moduleService.findAll();
		model.addAttribute("moduleList", moduleList);
		
		//跳转到列表页面中
		return "module/moduleList";
	}
	


	//状态的改变
	@RequestMapping("moduleStart")
	public String toStart(@RequestParam(value="moduleId",required=true)String[] moduleIds){
		int state = 1; //启用
		moduleService.updateState(moduleIds,state);
		
		//跳转到列表页面
		return "redirect:/tomodulelist";
	}
	
	@RequestMapping("moduleStop")
	public String toStop(@RequestParam(value="moduleId",required=true)String[] moduleIds){
		int state = 0;  //停用
		moduleService.updateState(moduleIds,state);
		return "redirect:/tomodulelist";
	}
	
	
	@RequestMapping("moduleDelete")
	public String toDelete(@RequestParam(value="moduleId",required=true)String[] moduleIds){
		
		moduleService.deleteModules(moduleIds);
		
		return "redirect:/tomodulelist";
	}
	
	@RequestMapping("/moduleTocreate")
	public String tocreate(Model model){
		
		//为页面准备上级模块列表
		List<Module> moduleList = moduleService.findAll();
		model.addAttribute("moduleList", moduleList);
		
		//跳转到模块新增页面
		return "/module/moduleCreate";
	}
	
	
	//模块的新增
	@RequestMapping("saveModule")
	public String saveModule(Module module){
		
		moduleService.saveModule(module);
		
		//跳转到模块列表页面
		return "redirect:/tomodulelist";
	}
	
	
	@RequestMapping("moduleToupdate")
	public String updateModule(String moduleId,Model model){
		
		//根据模块id查询模块信息
		Module module = moduleService.findOne(moduleId);
		
		//准备上级模块列表  排除自己之外的
		List<Module> moduleList = moduleService.findParentModuleList(moduleId);
		
		model.addAttribute("module", module);
		model.addAttribute("moduleList", moduleList);
		
		//跳转到修改页面
		return "/module/moduleUpdate";
	}
	
	
	@RequestMapping("updateModlue")
	public String updateModule(Module module){
		
		moduleService.updateModule(module);
		
		
		return "redirect:/tomodulelist";
	}

	@RequestMapping("moduleToview")
	public String toViewRole(String moduleId,Model model){
		Module module=moduleService.findOne(moduleId);

		model.addAttribute("module",module);
		return  "/module/moduleView";
	}


}
