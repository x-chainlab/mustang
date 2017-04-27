package com.dimogo.open.myjobs.sys;

import com.alibaba.fastjson.JSON;
import com.dimogo.open.myjobs.dto.RuntimeInfo;
import com.dimogo.open.myjobs.utils.ZKUtils;
import org.I0Itec.zkclient.ZkClient;

/**
 * Created by Ethan Xiao on 2017/4/27.
 */
public class ExecutorResourceMonitor implements Runnable {
	public void run() {
		ZkClient zkClient = ZKUtils.newClient();
		String path = ZKUtils.buildExecutorIDPath();
		while (true) {
			try {
				RuntimeInfo runtimeInfo = new RuntimeInfo();
				runtimeInfo.setup();
				zkClient.writeData(path, JSON.toJSONString(runtimeInfo));
				Thread.sleep(Config.getResourceMonitorInterval());
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}
