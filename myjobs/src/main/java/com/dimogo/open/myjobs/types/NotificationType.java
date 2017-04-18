package com.dimogo.open.myjobs.types;

import com.dimogo.open.myjobs.dispatch.NotificationDispatcher;
import com.dimogo.open.myjobs.servlet.ApplicationContextCatcher;

import java.util.Map;

/**
 * Created by Ethan Xiao on 2017/4/15.
 */
public enum NotificationType {
	RunJob("runJobDispatcher"),
	StopJob,
	;

	private NotificationDispatcher dispatcher;

	NotificationType() {

	}

	NotificationType(String dispatcherName) {
		this.dispatcher = (NotificationDispatcher) ApplicationContextCatcher.getInstance().get().getBean(dispatcherName);
	}

	NotificationType(NotificationDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public void dispatch(Map<String, String> paras) {
		if (dispatcher == null) {
			return;
		}
		dispatcher.dispatch(paras);
	}
}
