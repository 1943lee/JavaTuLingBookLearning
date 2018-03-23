package com.lcy.consumer;

/**消息队列工厂，储存单例对象
 * Created by lcy on 2018/3/22.
 */
public class MsgQueueFactory {
	private static MsgQueueManager msgQueueManager = new MsgQueueManager();

	public static MsgQueueManager getMessageQueue() {
		return msgQueueManager;
	}

	private static Consumers consumers = new Consumers();

	public static Consumers getConsumers() {
		return consumers;
	}
}
