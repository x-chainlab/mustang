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
			JobUtils.sendJobNotification(jobName, NotificationType.RunJob.name(), message);
		} catch (Exception e) {
			throw new JobExecutionException(e.getMessage(), e);
		}
	}
}