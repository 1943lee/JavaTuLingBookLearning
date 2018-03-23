package com.lcy.consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

/**总消息队列管理
 * Created by lcy on 2018/3/22.
 */
public class MsgQueueManager implements IMsgQueue {
	/**
	 * 消息总队列
	 */
	private final BlockingQueue<Message> messageQueue;

	public MsgQueueManager() {
		messageQueue = new LinkedTransferQueue<>();
	}

	public void put(Message msg) {
		try {
			messageQueue.put(msg);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public Message take() {
		try {
			return messageQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
