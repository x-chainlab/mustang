package com.dimogo.open.myjobs.notification;

import com.dimogo.open.myjobs.context.ExecutionManager;
import com.dimogo.open.myjobs.types.NotificationParaType;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 停止JOB的通知处理器
 * Created by ethanx on 2017/5/4.
 */
public class StopJobProcessor implements NotificationProcessor {
	public void dispatch(Map<String, String> paras) {
		String jobName = paras.get(NotificationParaType.JobName.name());
		if (StringUtils.isBlank(jobName)) {
			return;
		}
		ExecutionManager.getInstance().stopJobExecutions(jobName);
	}
}
