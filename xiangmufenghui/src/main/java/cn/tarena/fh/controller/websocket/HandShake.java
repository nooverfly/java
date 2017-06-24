
package cn.tarena.fh.controller.websocket;

import cn.tarena.fh.pojo.Message;
import cn.tarena.fh.pojo.MessageUser;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


/**
 * Socket建立连接（握手）和断开
 */
public class HandShake implements HandshakeInterceptor {

	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		System.out.println("Websocket:用户[ID:" + ((ServletServerHttpRequest) request).getServletRequest().getSession(false).getAttribute("messageUser") + "]已经建立连接");
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);


			MessageUser messageUser = (MessageUser) session.getAttribute("messageUser");
			String uid = messageUser.getId();
			List<Message> messageListOne = (List<Message>)session.getAttribute("messageListOne");

			MessageUser messageToUser = (MessageUser) session.getAttribute("messageToUser");


			if (uid != null) {
				// 标记用户
				attributes.put("messageUser", messageUser);//用户对象信息传送到websession

				attributes.put("messageListOne",messageListOne);//将未读取的消息传送过去
				attributes.put("messageToUser",messageToUser);//将接受信息的对象传递过去
			}else {
				System.out.println("错误退出***********************");
				return false;
			}
	}
		return true;
	}

	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
	}

}
