package com.lcy.consumer;

import java.util.List;
import java.util.Vector;

/**多消费者，分多个消息子队列
 * Created by lcy on 2018/3/22.
 */
public class Consumers {
	private static Consumers mConsumers = new Consumers();

	private static List<SubConsumer> subMsgQueues;

	public static Consumers getInstance() {
		return mConsumers;
	}

	public void init() {
		initSubMsgQueues(Runtime.getRuntime().availableProcessors() * 2);
		DispatchMessageTask dispatchMessageTask = new DispatchMessageTask();
		Thread thread = new Thread(dispatchMessageTask, "消息分发线程");
		thread.start();
	}

	public void initSubMsgQueues(int initialCapacity) {
		subMsgQueues = new Vector<>(initialCapacity);
		for(int i = 0; i < 5; i++) {
			SubConsumer subConsumer = new SubConsumer(i + 1);
			synchronized (subMsgQueues) {
				subMsgQueues.add(subConsumer);
			}
		}
	}

	/**
	 * 分发消息，负责把消息从大队列塞到小队列里
	 *
	 * @author tengfei.fangtf
	 */
	static class DispatchMessageTask implements Runnable {
		@Override
		public void run() {
			SubConsumer subQueue;
			for (;;) {
				// 如果没有数据，则阻塞在这里
				Message msg = MsgQueueFactory.getMessageQueue().take();
				// 如果为空，则表示没有Session机器连接上来，
				// 需要等待，直到有Session机器连接上来
				while ((subQueue = getInstance().getSubQueue()) == null) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
				// 把消息放到小队列里
				try {
					Thread.sleep(100);
					System.out.println(Thread.currentThread().getName() + "===" + "分发任务" + msg.getMsgId());
					subQueue.put(msg);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	/**
	 * 均衡获取一个子队列。
	 *
	 * @return
	 */
	public SubConsumer getSubQueue() {
		int errorCount = 0;
		for (;;) {
			if (null == subMsgQueues || subMsgQueues.isEmpty()) {
				return null;
			}
			int index = (int) (System.nanoTime() % subMsgQueues.size());
			try {
				System.out.println("获取子队列===" + (index + 1));
				return subMsgQueues.get(index);
			} catch (Exception e) {
				// 出现错误表示，在获取队列大小之后，队列进行了一次删除操作
				e.printStackTrace();
				System.out.println("获取子队列出现错误");
				if ((++errorCount) < 3) {
					continue;
				}
			}
		}
	}
}
