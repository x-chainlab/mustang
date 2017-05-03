package com.dimogo.open.myjobs.quartz;

import com.dimogo.open.myjobs.utils.ID;
import com.dimogo.open.myjobs.utils.ZKUtils;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Created by ethanx on 2017/5/3.
 */
public class MyJobExecutor implements Runnable {
	public void run() {
		ZkClient zkClient = null;
		try {
			zkClient = ZKUtils.newClient();
			String executorsPath = ZKUtils.Path.Executors.build();
			String executorPath = ZKUtils.buildExecutorIDPath();
			String id = ID.ExecutorID.toString();
			while (true) {
				try {
					zkClient.createEphemeral(executorPath);
				} catch (ZkNodeExistsException e) {
					//executor registered
				} catch (Throwable e) {
					continue;
				}
				while (true) {
					List<String> executors = zkClient.watchForChilds(executorsPath);
					if (CollectionUtils.isEmpty(executors) || !executors.contains(id)) {
						//executor not in list
						break;
					}
				}
			}
		} finally {
			if (zkClient != null) {
				zkClient.close();
			}
		}
	}
}
