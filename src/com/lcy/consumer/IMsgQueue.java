package com.lcy.consumer;

/**消息队列接口
 * Created by lcy on 2018/3/22.
 */
public interface IMsgQueue {
	/**
	 * 插入消息
	 * @param msg
	 */
	void put (Message msg);

	/**
	 * 获取消息
	 * @return
	 */
	Message take ();
}
