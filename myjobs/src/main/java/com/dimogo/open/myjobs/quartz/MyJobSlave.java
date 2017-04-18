package com.dimogo.open.myjobs.quartz;

import com.dimogo.open.myjobs.utils.ZKUtils;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Ethan Xiao on 2017/4/17.
 */
public class MyJobSlave implements Runnable {
	public void run() {
		ZkClient zkClient = ZKUtils.newClient();
		try {
			BlockingQueue<List<String>> queue = new LinkedBlockingQueue<List<String>>(100);
			MyJobDispatcher dispatcher = new MyJobDispatcher(queue);
			new Thread(dispatcher).start();

			String path = ZKUtils.Path.Notifications.build();
			while (true) {
				List<String> notifications = zkClient.watchForChilds(path);
				if (CollectionUtils.isEmpty(notifications)) {
					continue;
				}
				queue.offer(notifications);
			}
		} finally {
			zkClient.close();
		}
	}
}
