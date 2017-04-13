package com.dimogo.open.myjobs.utils;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

/**
 * Created by Ethan Xiao on 2017/4/13.
 */
public class JobInstanceUtils {

	public static boolean allInstancesAreRunning(String job) {
		ZkClient zkClient = ZKUtils.newClient();
		try {
			Integer confInstances = zkClient.readData(ZKUtils.buildJobInstancesPath(job), true);
			if (confInstances == null || confInstances < 1) {
				return false;
			}
			return ZKUtils.countJobExecutions(zkClient, job) >= confInstances;
		} finally {
			zkClient.close();
		}
	}

	public static void setInstances(String job, Integer instances) {
		ZkClient zkClient = ZKUtils.newClient();
		try {
			String path = ZKUtils.buildJobInstancesPath(job);
			if (zkClient.exists(path)) {
				zkClient.writeData(path, instances);
				return;
			}
			zkClient.create(path, instances, CreateMode.PERSISTENT);
		} finally {
			zkClient.close();
		}
	}
}
