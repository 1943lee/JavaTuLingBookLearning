package com.lcy.consumer;

/**消息实体
 * Created by lcy on 2018/3/22.
 */
public class Message {
	public Message(int msgId, String msgContent, String msgType) {
		this.msgId = msgId;
		this.msgContent = msgContent;
		this.msgType = msgType;
	}
	private int msgId;
	private String msgContent;
	private String msgType;

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
}
