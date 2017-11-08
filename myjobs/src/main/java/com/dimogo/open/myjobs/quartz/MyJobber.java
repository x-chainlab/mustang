package com.dimogo.open.myjobs.quartz;

import com.dimogo.open.myjobs.types.NotificationParaType;
import com.dimogo.open.myjobs.types.NotificationType;
import com.dimogo.open.myjobs.utils.JobInstanceUtils;
import com.dimogo.open.myjobs.utils.JobUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Master节点的Quartz Job，当Master节点上观察到有Spring Batch Job需要执行时，通过MyJobber向ZK发送启动通知。
 * 每个Spring Batch Job都有在Master节点进行计划，由Quartz定时唤醒，由Slave处理通知。
 * Created by Ethan Xiao on 2017/4/15.
 */
public class MyJobber implements Job {
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String jobName = context.getJobDetail().getKey().getName();
		if (JobInstanceUtils.allInstancesAreRunning(jobName)) {
			throw new JobExecutionException("All Instances of " + jobName + " Are Running");
		}

		Map<String, String> message = new LinkedHashMap<String, String>();
		message.put(NotificationParaType.NotificationType.name(), NotificationType.RunJob.name());
		message.put(NotificationParaType.JobName.name(), jobName);

		try {
			JobUtils.sendJobNotification(jobName, NotificationType.RunJob.name(), message, null);
		} catch (Exception e) {
			throw new JobExecutionException(e.getMessage(), e);
		}
	}
}