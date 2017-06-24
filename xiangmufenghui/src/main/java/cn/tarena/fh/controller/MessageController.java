package cn.tarena.fh.controller;

import cn.tarena.fh.controller.websocket.MyWebSocketHandler;
import cn.tarena.fh.pojo.Message;
import cn.tarena.fh.pojo.MessageUser;
import cn.tarena.fh.service.MessageService;
import cn.tarena.fh.service.MessageUserService;
import cn.tarena.fh.service.UserService;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class MessageController {

	@Resource
    MyWebSocketHandler handler;

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private MessageUserService messageUserService;


	// 聊天窗口
	@RequestMapping(value = "toTalk", method = RequestMethod.GET)
	public ModelAndView doLogin(String toId, HttpSession session) {

		Map<String, MessageUser> users = (Map<String, MessageUser>) session.getAttribute("users");
		MessageUser msgUser = (MessageUser)session.getAttribute("messageUser");

		MessageUser messageToUser = messageUserService.findOne(users.get(toId).getName());
		//得到消息接收对象的全部信息,判断是不是在线
		if (messageToUser.getState()==1) {
			Long startTime = messageToUser.getLongTime();
			if(startTime==null){ startTime=0L; }
			Long endTime = System.currentTimeMillis();
			if (endTime - startTime > 60 * 60 * 1000) {//用户上次访问时间已经超过1个小时,判断其掉线
				messageToUser.setState(0);
				messageUserService.updateStateOne(messageToUser.getName(), 0);
			}
		}
		session.setAttribute("messageToUser",messageToUser);//将接受信息的用户数据放到session中
		List<Message> messageListOne =  messageService.findByFromAndTo(toId,msgUser.getId());//查询这个窗口对象发送的未读信息
		messageService.updateMessageState(toId,msgUser.getId()); //将得到的所有的点击窗口后出现的信息全部改变状态为1:已读
		session.setAttribute("messageListOne",messageListOne);//将该对象发送的未读信息发送到窗口中

		return new ModelAndView("redirect:talk");//跳转到聊天界面
	}


	// 跳转到交谈聊天页面
	@RequestMapping(value = "talk", method = RequestMethod.GET)
	public ModelAndView talk() {
		return new ModelAndView("views/talk");
	}



	//跳转到私信空间
	@RequestMapping(value = "toMessageBoard")
	public  ModelAndView messageBoard( HttpSession session){
		MessageUser msgUser = (MessageUser)session.getAttribute("messageUser");
		List<Message> messageList = messageService.findMessageOne(msgUser.getId());//得到所有未收到的信息
		session.setAttribute("messageList", messageList);//保存到session中登录后显示未接受的信息

		return new ModelAndView("messageBoard");

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