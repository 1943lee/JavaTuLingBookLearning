package com.lcy.consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by lcy on 2018/3/23.
 */
public class SubConsumer extends LinkedBlockingQueue<Message>{
	private Thread workerThread;

	public SubConsumer(int num) {
		super();
		Worker worker = new Worker();
		workerThread = new Thread(worker, "subConsumer_" + num);
		workerThread.start();
	}

	public BlockingQueue<Message> getQueue() {
		return this;
	}

	// 工作者，负责消费任务
	class Worker implements Runnable {
		public void run() {
			while (true) {
				Message message = null;
				synchronized (getQueue()) {
					try {
						message = getQueue().take();
					} catch (InterruptedException ex) {
						// 感知到外部对WorkerThread的中断操作，返回
						Thread.currentThread().interrupt();
						return;
					}
				}
				if(message != null) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "========="
							+ "id:" + message.getMsgId()
							+ ",content:" + message.getMsgContent()
							+ ",type:" + message.getMsgType());
				}
			}
		}
	}
}
