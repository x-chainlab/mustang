package com.dimogo.open.myjobs.manager.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.dimogo.open.myjobs.dto.ClusteredJobInfo;
import com.dimogo.open.myjobs.dto.ExecutorInfo;
import com.dimogo.open.myjobs.dto.NotificationInfo;
import com.dimogo.open.myjobs.manager.admin.service.MyJobsService;
import com.dimogo.open.myjobs.utils.ListUtils;
import com.dimogo.open.myjobs.utils.ZKUtils;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Ethan Xiao on 2017/4/19.
 */
public class MyJobsServiceImpl implements MyJobsService {

	private ZkClient zkClient;

	public MyJobsServiceImpl() {
		zkClient = ZKUtils.newClient();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		zkClient.close();
	}

	public List<ClusteredJobInfo> listJobs(int start, int pageSize) {
		List<String> allJobs = zkClient.getChildren(ZKUtils.Path.Jobs.build());
		if (CollectionUtils.isEmpty(allJobs)) {
			return new ArrayList<ClusteredJobInfo>(0);
		}
		List<String> jobs = ListUtils.subList(allJobs, start, (start + 1) * pageSize);
		if (CollectionUtils.isEmpty(allJobs)) {
			return new ArrayList<ClusteredJobInfo>(0);
		}
		List<ClusteredJobInfo> jobInfos = new ArrayList<ClusteredJobInfo>(jobs.size());
		for (String job : jobs) {
			int executors = zkClient.countChildren(ZKUtils.buildJobExecutorsPath(job));
			int executions = zkClient.countChildren(ZKUtils.buildJobExecutionsPath(job));
			Integer instances = zkClient.readData(ZKUtils.buildJobInstancesPath(job), true);
			String cron = zkClient.readData(ZKUtils.buildJobCronPath(job), true);
			String paras = zkClient.readData(ZKUtils.buildJobParasPath(job), true);

			ClusteredJobInfo jobInfo = new ClusteredJobInfo();
			jobInfo.setJobName(job);
			jobInfo.setExecutors(executors);
			jobInfo.setExecutions(executions);
			if (instances != null) {
				jobInfo.setMaxInstances(instances);
			}
			if (cron != null) {
				jobInfo.setCron(cron);
			}
			if (paras != null) {
				jobInfo.setParas(paras);
			}
			jobInfos.add(jobInfo);
		}
		return jobInfos;
	}

	public int countJobs() {
		return zkClient.countChildren(ZKUtils.Path.Jobs.build());
	}

	public List<ExecutorInfo> listExecutors(int start, int pageSize) {
		List<String> allExecutors = zkClient.getChildren(ZKUtils.Path.Executors.build());
		if (CollectionUtils.isEmpty(allExecutors)) {
			return new ArrayList<ExecutorInfo>(0);
		}
		List<String> executors = ListUtils.subList(allExecutors, start, (start + 1) * pageSize);
		if (CollectionUtils.isEmpty(executors)) {
			return new ArrayList<ExecutorInfo>(0);
		}
		List<ExecutorInfo> executorInfos = new ArrayList<ExecutorInfo>(executors.size());
		for (String executor : executors) {
			ExecutorInfo executorInfo = new ExecutorInfo();
			executorInfo.setId(executor);

			executorInfos.add(executorInfo);
		}
		return executorInfos;
	}

	public int countExecutors() {
		return zkClient.countChildren(ZKUtils.Path.Executors.build());
	}

	public List<NotificationInfo> listNotifications(int start, int pageSize) {
		List<String> allNotifications = zkClient.getChildren(ZKUtils.Path.Notifications.build());
		if (CollectionUtils.isEmpty(allNotifications)) {
			return new ArrayList<NotificationInfo>(0);
		}
		List<String> notifications = ListUtils.subList(allNotifications, start, start + pageSize);
		if (CollectionUtils.isEmpty(notifications)) {
			return new ArrayList<NotificationInfo>(0);
		}
		List<NotificationInfo> executorInfos = new ArrayList<NotificationInfo>(notifications.size());
		for (String notification : notifications) {
			NotificationInfo notificationInfo = new NotificationInfo();
			notificationInfo.setName(notification);
			Map<String, String> paras = zkClient.readData(ZKUtils.buildNotificationPath(notification), true);
			if (MapUtils.isNotEmpty(paras)) {
				notificationInfo.setParas(JSON.toJSONString(paras));
			}
			executorInfos.add(notificationInfo);
		}
		return executorInfos;
	}

	public int countNotifications() {
		return zkClient.countChildren(ZKUtils.Path.Notifications.build());
	}
}
