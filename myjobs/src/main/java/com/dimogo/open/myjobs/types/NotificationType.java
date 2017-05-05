package com.dimogo.open.myjobs.types;

import com.dimogo.open.myjobs.notification.NotificationProcessor;
import com.dimogo.open.myjobs.servlet.ApplicationContextCatcher;

import java.util.Map;

/**
 * Created by Ethan Xiao on 2017/4/15.
 */
public enum NotificationType {
	RunJob("runJobProcessor"),
	StopJob("stopJobProcessor"),
	PauseTrigger("pauseTriggerProcessor"),
	ResumeTrigger("resumeTriggerProcessor"),
	;

	private NotificationProcessor dispatcher;

	NotificationType() {

	}

	NotificationType(String dispatcherName) {
		this.dispatcher = (NotificationProcessor) ApplicationContextCatcher.getInstance().get().getBean(dispatcherName);
	}

	NotificationType(NotificationProcessor dispatcher) {
		this.dispatcher = dispatcher;
	}

	public void dispatch(Map<String, String> paras) {
		if (dispatcher == null) {
			return;
		}
		dispatcher.dispatch(paras);
	}
}
