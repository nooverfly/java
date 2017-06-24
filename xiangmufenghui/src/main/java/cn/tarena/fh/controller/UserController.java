package cn.tarena.fh.controller;


import cn.tarena.fh.pojo.Dept;
import cn.tarena.fh.pojo.Role;
import cn.tarena.fh.pojo.User;
import cn.tarena.fh.pojo.UserInfo;
import cn.tarena.fh.service.DeptService;
import cn.tarena.fh.service.RoleService;
import cn.tarena.fh.service.UserInfoService;
import cn.tarena.fh.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private UserInfoService userInfoService;


	@Autowired
	private RoleService roleService;


	@RequestMapping("/toUserlist")
	public String toUserList(Model model){
		
		List<User> userList=userService.findAll();
		System.out.println();
		model.addAttribute("userList", userList);
		
		return "user/userList";
	}

	@RequestMapping("/muserList")
	@ResponseBody
	public String muserList() throws JsonProcessingException {

		List<User> userList=userService.findAll();
		String json = new ObjectMapper().writeValueAsString(userList);
		System.out.println(json);
		return json;
	}
	

	//状态的启用
	@RequestMapping("userStart")
	public String toStart(@RequestParam(value="userId",required=true)String[] userIds){
		
		int state = 1;
		userService.updateState(userIds,state);
		
		//重定向到用户列表页面
		return "redirect:/toUserlist";
		
		
	}
	
	//状态的停用
	@RequestMapping("userStop")
	public String toStop(@RequestParam(value="userId",required=true)String[] userIds){
		
		int state = 0;
		userService.updateState(userIds,state);
		
		//重定向到用户列表页面
		return "redirect:/toUserlist";

	}

	//用户查看
	@RequestMapping("userToview")
	public String toview(@RequestParam(required=true)String userId, Model model){

		User user = userService.findOne(userId);
		model.addAttribute("user", user);
		//转向用户的查看页面
		return "/user/userView";

	}

	//用户新增页面跳转
	@RequestMapping("userTocreate")
	public String tocreate(Model model){
		//1 准备部门列表信息
		List<Dept> deptList = deptService.findAll();

		//2.准备上级领导
		List<UserInfo> managerList = userInfoService.findManagerList();

		model.addAttribute("deptList", deptList);
		model.addAttribute("managerList", managerList);
		return "user/userCreate";

	}

	@RequestMapping("userSave")
	public String saveUser(User user, MultipartFile photo, HttpServletRequest request) throws IOException {

		//多表操作时 需要保证事务的一致性
		//FileOutputStream outputStream =
		//		new FileOutputStream(new File("C:\\java\\idea\\project\\fh1.3\\src\\main\\webapp\\staticfile\\image\\head\\"+photo.getOriginalFilename()));

		//outputStream.write(photo.getBytes());
		//user.getUserInfo().setHeadUrl(photo.getOriginalFilename());
		byte[] bytes = photo.getBytes();
		//BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File("C:\\java\\idea\\project\\fh1.3\\src\\main\\webapp\\staticfile\\image\\head\\" +photo.getOriginalFilename())));
		BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(request.getRealPath("/") +"\\staticfile\\image\\head\\"+photo.getOriginalFilename())));

		stream.write(bytes);
		stream.close();
		user.setHeadUrl("staticfile/image/head/"+photo.getOriginalFilename());
		user.getUserInfo().setName(user.getUsername());
		System.out.println(user.toString());
		userService.saveUser(user);

		//页面应该重定向到list页面
		return "redirect:/toUserlist";
	}

	//修改用户
	@RequestMapping("userToupdate")
	public String toupdate(String userId,Model model){
		User user = userService.findOne(userId);   //查询用户信息

		//准备下拉列表
		List<Dept> deptList = deptService.findAll();
		List<UserInfo> managerList = userInfoService.findManagerList();
		model.addAttribute("user", user);
		model.addAttribute("deptList", deptList);
		model.addAttribute("managerList", managerList);

		//为了页面快速获取上级领导的Id
		String managerId = null;
		if(user.getUserInfo().getManager() !=null){
			managerId =  user.getUserInfo().getManager().getUserInfoId();
		}

		model.addAttribute("mId",managerId);
		return "/user/userUpdate";

	}

	//用户删除
	@RequestMapping("userDelete")
	public String toDelete(@RequestParam(value="userId",required=true)String[] userIds){
		
		userService.deleteUser(userIds);
		
		return "redirect:/toUserlist";
	}

	@RequestMapping("userUpdate")
	public String updateUser(User user) throws IOException {
		user.setUpdateTime(new Date());

		return  "redirect:/toUserlist";
	}


	//转向用户角色页面
	/**
	 * 	1.根据userId查询用户的角色信息(userRoleList)
	 2.查询全部的角色信息
	 3.用户的信息与角色的全部进行做匹配,如果roleId一致,则为Role对象中添加属性checked,并设置为true.
	 * @param userId
	 * @param model
	 * @return
	 * @throws JsonProcessingException
	 */


	@RequestMapping("userRole")
	public String touserRole(@RequestParam(required=true)String userId,Model model) throws JsonProcessingException{

		//根userId查询角色信息
		List<String> userRoleList = userService.findRoleIdByUserId(userId);

		//准备角色信息
		List<Role> roleList = roleService.findAll();


		//循环遍历roleList 设置checked属性
		for (Role role : roleList) {
			if(userRoleList.contains(role.getRoleId())){
				//证明用户由此权限
				role.setChecked(true);
			}
		}



		//将roleList转化为JSON串
		String zTreeJSON = new ObjectMapper().writeValueAsString(roleList);
		System.out.println(zTreeJSON);

		model.addAttribute("zTreeJSON", zTreeJSON);

		//将用户的userid 传递到页面中,方便以后插件时使用
		model.addAttribute("userId", userId);


		//转向分配页面
		return "/user/userRole";

	}


	//用户角色分配的保存

	@RequestMapping("saveUserRole")
	public String saveUserRole(String[] roleIds,String userId){

		userService.saveUserRole(roleIds,userId);
		//System.out.println(Arrays.toString(roleIds));

		return "redirect:/toUserlist";

	}

	@RequestMapping("/getHeadImg")
	public String getHeadImg(String url){
		return "forward:/"+url;
	}
}
