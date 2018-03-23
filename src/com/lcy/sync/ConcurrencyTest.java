package com.lcy.sync;

/**
 * Created by lcy on 2018/3/21.
 */
public class ConcurrencyTest {
	private static final long count = 1000_0000l;
	public static void main(String[] args) throws InterruptedException {
		concurrency();
		serial();
	}
	private static void concurrency() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				long a = 0;
				for (long i = 0; i < count; i++) {
					a += 5;
				}
			}
		});
		thread.start();
		long b = 0;
		for (long i = 0; i < count; i++) {
			b--;
		}
		long time = System.currentTimeMillis() - start;
		thread.join();
		System.out.println("concurrency :" + time+"ms,b="+b);
	}
	private static void serial() {
		long start = System.currentTimeMillis();
		long a = 0;
		for (long i = 0; i < count; i++) {
			a += 5;
		}
		long b = 0;
		for (long i = 0; i < count; i++) {
			b--;
		}
		long time = System.currentTimeMillis() - start;
		System.out.println("serial:" + time+"ms,b="+b+",a="+a);
	}
}
