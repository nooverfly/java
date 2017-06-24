package cn.tarena.fh.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息类
 * @author Goofy
 * @Date 2015年6月12日 下午7:32:39
 */
public class Message implements Serializable {


	//发送者
	private String fromId;
	//发送者名称
	private String fromName;
	//接收者
	private String toId;
	//接收者
	private String toName;
	//发送的文本
	private String text;
	//发送日期
	private Date date;

	//发送状态//0表示未发送，1表示已发送
	private Integer state;


	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
