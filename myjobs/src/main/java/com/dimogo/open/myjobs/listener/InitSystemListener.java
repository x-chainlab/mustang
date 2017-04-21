package com.dimogo.open.myjobs.listener;

import com.dimogo.open.myjobs.quartz.MyJobMaster;
import com.dimogo.open.myjobs.quartz.MyJobSlave;
import com.dimogo.open.myjobs.utils.ZKUtils;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Ethan Xiao on 2017/4/6.
 */
public class InitSystemListener implements ServletContextListener {
	private ZkClient zkClient;

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			zkClient = ZKUtils.newClient();
			ZKUtils.create(zkClient, ZKUtils.Path.Root.build(), null, CreateMode.PERSISTENT);
			ZKUtils.create(zkClient, ZKUtils.Path.MyJobs.build(), null, CreateMode.PERSISTENT);
			ZKUtils.create(zkClient, ZKUtils.Path.Jobs.build(), null, CreateMode.PERSISTENT);
			ZKUtils.create(zkClient, ZKUtils.Path.Executors.build(), null, CreateMode.PERSISTENT);
			ZKUtils.create(zkClient, ZKUtils.Path.Notifications.build(), null, CreateMode.PERSISTENT);
			zkClient.createEphemeral(ZKUtils.buildExecutorIDPath());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		if (zkClient != null) {
			zkClient.close();
		}
	}
}
