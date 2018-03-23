package com.lcy;

import com.lcy.consumer.Message;
import com.lcy.consumer.MsgQueueFactory;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        //DefaultThreadPool defaultThreadPool = new DefaultThreadPool(10);

        /*Runnable job = new Runnable() {
            @Override
            public void run() {
                try {
                    long mills = (long) (500 + Math.random() * 300);
                    Thread.sleep(mills);
                    System.out.println(Thread.currentThread().getName() + "====" + mills + "ms");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        /*Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        };

        long start = System.currentTimeMillis();
        for(int i = 0 ; i < 100; i++) {
            defaultThreadPool.execute(job);
        }
        System.out.println(System.currentTimeMillis() - start + "ms");
        /*Callable callable = new Callable() {
            @Override
            public Object call() {
                try {
                    long mills = (long) (500 + Math.random() * 300);
                    Thread.sleep(mills);
                    System.out.println(Thread.currentThread().getName() + "====" + mills + "ms");
                    return mills;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };*/

        //int corePoolsize = Runtime.getRuntime().availableProcessors() * 2;
        //ExecutorService executorService = Executors.newFixedThreadPool(5);

        //long start = System.currentTimeMillis();
        /*for(int i = 0 ; i < 100; i++) {
            executorService.execute(job);
        }
*/
        //System.out.println(System.currentTimeMillis() - start + "ms");
        //1.启动子队列
        MsgQueueFactory.getConsumers().init();

        for(int i = 0; i < 1; i++) {
            MsgQueueFactory.getMessageQueue().put(new Message(i,"content_"+i,"type_"+i));
        }
    }
}
