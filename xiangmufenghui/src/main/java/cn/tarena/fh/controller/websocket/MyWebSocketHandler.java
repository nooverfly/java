package cn.tarena.fh.controller.websocket;

import cn.tarena.fh.pojo.Message;
import cn.tarena.fh.pojo.MessageUser;
import cn.tarena.fh.service.MessageService;
import cn.tarena.fh.service.MessageUserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

//import javax.websocket.server.ServerEndpoint;//实现多例的基础,还在还没实现所以注释还在

/**
 * Socket处理器
 */
@Component
public class MyWebSocketHandler implements WebSocketHandler {
	public static final Map<String, WebSocketSession> userSocketSessionMap; //map里面一个存储用户id,一个存session

	public static final Map<String, String> userToMap;//存放每一个窗口的用户和对象
	@Autowired
	private MessageService messageService;

	@Autowired
	private MessageUserService messageUserService;

	static {
		userSocketSessionMap = new HashMap<String, WebSocketSession>();
		userToMap = new HashMap<String, String>();
	}

	/**
	 * 建立连接后
	 */
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		MessageUser messageUser = (MessageUser) session.getAttributes().get("messageUser");//通过session得到存储其中的用户id
		String uid = messageUser.getId();
		String toId = (String) session.getAttributes().get("toId");
		MessageUser messageToUser = (MessageUser) session.getAttributes().get("messageToUser");

		System.out.println(uid+":"+messageToUser.getId());
		if (userSocketSessionMap.get(uid) == null) {
			userSocketSessionMap.put(uid, session);//如果id不为null ,将其存入到map中
			userToMap.put(uid, messageToUser.getId());//如果id不为null ,将打开的窗口存入,用户和窗口对准的用户
		}

		//点击聊天窗口后出现未读的信息
		List<Message> messageListOne = (List<Message>)session.getAttributes().get("messageListOne");
		if( messageListOne != null) {
			for (Message m : messageListOne) {
				sendMessageToUser(uid, new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(m)));
			}
			session.getAttributes().remove("messageListOne");
		}

	}

	/**
	 * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
	 */
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

		if(message.getPayloadLength()==0)return;//如果消息的长度为0,则当做没有接受到消息
		Gson gson = new Gson();
		Message msg = gson.fromJson(message.getPayload().toString(),Message.class);//得到消息
		msg.setDate(new Date());//得到接受消息的时间

		String toId = msg.getToId();//是通过msg得到要发送消息的id
		msg.setState(0);


		String fromId = msg.getFromId();
		String oldId =  userToMap.get(fromId);//得到接受消息的id用户;此用户在它的页面下开点开窗口的id
		String oldId2 =  userToMap.get(oldId);
		//查询该用户是否登录
		MessageUser messageToUser = messageUserService.findOne(msg.getToName());
		Integer state = messageToUser.getState();


		//检查发送对象是否在线
		if(userSocketSessionMap.get(toId)!=null && oldId2.equals(fromId)){//已经打开窗口发送
			System.out.println("oldId="+oldId+"=====**********nonull********fromId="+fromId+"***");//在线时将信息发送出去，并改变信息状态
			sendMessageToUser(toId, new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
			msg.setState(1);
		}else if(state==1 ){//登录了,但是没有打开对应的窗口
			Message msgSystem = new Message();
			msgSystem.setDate(new Date());
			msgSystem.setToId(msg.getFromId());
			msgSystem.setFromId("0");
			msgSystem.setFromName("系统通知");
			msgSystem.setToName(msg.getToName());
			msgSystem.setText("对方暂时忙,你的信息我们已经推送!");

			sendMessageToUser(msg.getFromId(), new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msgSystem)));

		}else {
			Message msgSystem = new Message();
			msgSystem.setDate(new Date());
			msgSystem.setToId(msg.getFromId());
			msgSystem.setFromId("0");
			msgSystem.setFromName("系统通知");
			msgSystem.setToName(msg.getToName());
			msgSystem.setText("对不起对方不在线,你的信息我们将会在对方上线后及时推送!");

			TextMessage textMessage = new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msgSystem));
			sendMessageToUser(msg.getFromId(),textMessage);
		}
//将所有的消息都保存到数据库中;用message表存起来;
		messageService.saveMessage(msg);

	}

	/**
	 * 消息传输错误处理
	 */
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		Iterator<Entry< String, WebSocketSession>> it = userSocketSessionMap
				.entrySet().iterator();
		// 移除Socket会话
		while (it.hasNext()) {
			Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				userToMap.remove(entry.getKey());
				System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
				break;
			}
		}
	}

	/**
	 * 关闭连接后
	 */
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		System.out.println("Websocket:" + session.getId() + "已经关闭");  //这里得到是当前开窗口的id.
		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap
				.entrySet().iterator();
		// 移除Socket会话
		while (it.hasNext()) {
			Entry<String, WebSocketSession> entry = it.next();
			if (entry.getValue().getId().equals(session.getId())) {
				userSocketSessionMap.remove(entry.getKey());
				userToMap.remove(entry.getKey());
				System.out.println("Socket会话已经移除:用户ID" + entry.getKey());//这里用户的id
				break;
			}
		}
	}

	/**
	 * 是否分段发送消息
	 * @return
	 */
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给所有在线用户发送消息
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void broadcast(final TextMessage message) throws IOException {
		Iterator<Entry<String, WebSocketSession>> it = userSocketSessionMap
				.entrySet().iterator();

		// 多线程群发
		while (it.hasNext()) {

			final Entry<String, WebSocketSession> entry = it.next();

			if (entry.getValue().isOpen()) {
				// entry.getValue().sendMessage(message);
				new Thread(new Runnable() {

					public void run() {
						try {
							if (entry.getValue().isOpen()) {
								entry.getValue().sendMessage(message);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}).start();
			}

		}
	}

	/**
	 * 给某个用户发送消息
	 * 
	 *
	 * @param message
	 * @throws IOException
	 */
	public void sendMessageToUser(String uid, TextMessage message)
			throws IOException {
		WebSocketSession session = userSocketSessionMap.get(uid);//通过id得到要给哪个session发送信息
		if (session != null && session.isOpen()) {//如果这个session存在且是在线的,就将信息发送给他
			session.sendMessage(message);
		}
	}

}

