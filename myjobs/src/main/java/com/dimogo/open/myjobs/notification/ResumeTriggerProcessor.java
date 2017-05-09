package com.dimogo.open.myjobs.notification;

import com.dimogo.open.myjobs.quartz.SchedulerManager;
import com.dimogo.open.myjobs.types.NotificationParaType;
import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;

import java.util.Map;

/**
 * Created by ethanx on 2017/5/4.
 */
public class ResumeTriggerProcessor implements NotificationProcessor {
	public void dispatch(Map<String, String> paras) {
		String jobName = paras.get(NotificationParaType.JobName.name());
		if (StringUtils.isBlank(jobName)) {
			return;
		}
		try {
			SchedulerManager.getInstance().resumeTrigger(jobName);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
