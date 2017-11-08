package com.dimogo.open.myjobs.types;

import com.dimogo.open.myjobs.notification.NotificationProcessor;
import com.dimogo.open.myjobs.servlet.ApplicationContextCatcher;

import java.util.Map;

/**
 * Slave节点处理的通知类型
 * Created by Ethan Xiao on 2017/4/15.
 */
public enum NotificationType {
	RunJob("runJobProcessor", true, false, false),
	StopJob("stopJobProcessor", false, false, true),
	PauseTrigger("pauseTriggerProcessor", true, true, false),
	ResumeTrigger("resumeTriggerProcessor", true, true, false),
	;

	private NotificationProcessor dispatcher;
	private boolean forMaster;
	private boolean forAllSlaves;
	private boolean needLock;

	NotificationType(String dispatcherName, boolean needLock, boolean forMaster, boolean forAllSlaves) {
		this.dispatcher = (NotificationProcessor) ApplicationContextCatcher.getInstance().get().getBean(dispatcherName);
		this.needLock = needLock;
		this.forAllSlaves = forAllSlaves;
		this.forMaster = forMaster;
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

	public boolean isNeedLock() {
		return needLock;
	}

	public boolean isForMaster() {
		return forMaster;
	}

	public boolean isForAllSlaves() {
		return forAllSlaves;
	}
}
