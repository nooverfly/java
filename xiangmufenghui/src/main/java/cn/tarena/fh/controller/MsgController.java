package cn.tarena.fh.controller;

import cn.tarena.fh.controller.websocket.MyWebSocketHandler;
import cn.tarena.fh.pojo.Message;
import cn.tarena.fh.pojo.User;
import cn.tarena.fh.service.MessageService;
import cn.tarena.fh.service.UserService;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/msg")
public class MsgController {

	@Resource
    MyWebSocketHandler handler;

	Map<String, User> users = new HashMap<String, User>();

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	// 从数据库得到一些数据
	@ModelAttribute
	public void setReqAndRes() {
		List<User>  user = userService.findAll();
		for(User u : user){
			users.put(u.getUserId(),u);

		}


		/*User u1 = new User();
		u1.setUserId("1");
		u1.setUsername("张三");
		users.put(u1.getUserId(), u1);

		User u2 = new User();
		u2.setUserId("2");
		u2.setUsername("李四");
		users.put(u2.getUserId(), u2);*/

	}

	// 用户登录
	@RequestMapping(value = "doin", method = RequestMethod.POST)
	public ModelAndView doin(User user, HttpServletRequest request) {
		System.out.println(user.getUsername()+"*************");
		User user1 = userService.findOne(user.getUserId());
		request.getSession().setAttribute("uid", user1.getUserId());//得到登录后的id
		request.getSession().setAttribute("name", user1.getUsername());//得到该登录者的名字
		request.getSession().setAttribute("users",users);
		List<Message> messageList =messageService.findMessageOne(user1.getUserId());//得到所有未收到的信息

		request.getSession().setAttribute("messageList", messageList);//登录后的未接受的信息

		return new ModelAndView("redirect:/allUser.jsp");//跳转到寻找聊天用户的界面
	}






	// 发送对象
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView doLogin(User user, HttpServletRequest request) {
		request.getSession().setAttribute("toId", user.getUserId());//得到聊天者的id
		request.getSession().setAttribute("toName", users.get(user.getUserId()).getUsername());//得到聊天者的name

		return new ModelAndView("redirect:talk");//跳转到聊天界面
	}

	// 跳转到交谈聊天页面
	@RequestMapping(value = "talk", method = RequestMethod.GET)
	public ModelAndView talk() {
		return new ModelAndView("views/talk");
	}

	// 跳转到发布广播页面
	@RequestMapping(value = "broadcast", method = RequestMethod.GET)
	public ModelAndView broadcast() {
		return new ModelAndView("views/broadcast");
	}

	// 发布系统广播（群发）
	@ResponseBody
	@RequestMapping(value = "broadcast", method = RequestMethod.POST)
	public void broadcast(String text) throws IOException {
		Message msg = new Message();
		msg.setDate(new Date());
		msg.setFromId("-1");
		msg.setFromName("系统广播");
		msg.setToId("0");
		msg.setText(text);
		handler.broadcast(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
	}

}