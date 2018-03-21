package com.lcy.sync;

/**
 * Created by lcy on 2018/3/19.
 */
public class mySync {
	private static class RunnableTest implements Runnable {
		//private AtomicInteger tick = new AtomicInteger(10);
		private int tick = 10;

		@Override
		public void run() {
			while (true) {
					if (tick <= 0) break;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				synchronized (this) {
					System.out.println(Thread.currentThread().getName() + "=========" + tick--);//.getAndDecrement());
				}
			}
		}
	}

	public static void main(String[] args) {
		RunnableTest runnableTest = new RunnableTest();
		new Thread(runnableTest).start();
		new Thread(runnableTest).start();
	}
}
