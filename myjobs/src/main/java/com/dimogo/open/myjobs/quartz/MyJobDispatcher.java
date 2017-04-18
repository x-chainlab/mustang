package com.dimogo.open.myjobs.quartz;

import com.dimogo.open.myjobs.types.NotificationParaType;
import com.dimogo.open.myjobs.types.NotificationType;
import com.dimogo.open.myjobs.utils.ID;
import com.dimogo.open.myjobs.utils.ZKUtils;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Ethan Xiao on 2017/4/18.
 */
public class MyJobDispatcher implements Runnable {

	private BlockingQueue<List<String>> queue;

	public MyJobDispatcher(BlockingQueue<List<String>> queue) {
		this.queue = queue;
	}

	public void run() {
		ZkClient zkClient = ZKUtils.newClient();
		try {
			while (true) {
				try {
					List<String> notifications = this.queue.take();
					for (String notification : notifications) {
						String notificationPath = ZKUtils.buildNotificationPath(notification);
						if (!zkClient.exists(notificationPath)) {
							continue;
						}
						try {
							zkClient.createEphemeral(ZKUtils.buildNotificationLockPath(notification), ID.ExecutorID);
						} catch (Throwable e) {
							continue;
						}
						Map<String, String> notificationParas = zkClient.readData(notificationPath, true);
						if (notificationParas == null) {
							continue;
						}
						doDispatch(notificationParas);
						try {
							zkClient.deleteRecursive(notificationPath);
						} catch (Throwable e) {
							continue;
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}
		} finally {
			zkClient.close();
		}
	}

	private void doDispatch(Map<String, String> paras) {
		String type = paras.get(NotificationParaType.NotificationType.name());
		if (StringUtils.isBlank(type)) {
			return;
		}
		try {
			NotificationType notificationType = NotificationType.valueOf(type);
			notificationType.dispatch(paras);
		} catch (IllegalArgumentException e) {
			return;
		}
	}
}
