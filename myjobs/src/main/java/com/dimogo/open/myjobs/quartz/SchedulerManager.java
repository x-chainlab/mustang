package com.dimogo.open.myjobs.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by ethanx on 2017/5/5.
 */
public class SchedulerManager {
	private static class SchedulerManagerHolder {
		private static SchedulerManager instance = new SchedulerManager();
	}

	public static SchedulerManager getInstance() {
		return SchedulerManagerHolder.instance;
	}

	private Scheduler scheduler = null;

	private SchedulerManager() {

	}

	public Scheduler getScheduler() throws SchedulerException {
		if (scheduler != null && scheduler.isStarted()) {
			return scheduler;
		}
		throw new SchedulerException("The scheduler has been closed or not");
	}

	public void pauseTrigger(String jobName) throws SchedulerException {
		getScheduler().pauseTrigger(new TriggerKey(jobName));
	}

	public void resumeTrigger(String jobName) throws SchedulerException {
		getScheduler().resumeTrigger(new TriggerKey(jobName));
	}

	public Scheduler start() throws SchedulerException {
		if (scheduler == null) {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			return scheduler;
		} else if (scheduler.isStarted()) {
			return scheduler;
		}
		throw new SchedulerException("Unable to start the scheduler");
	}

	public void stop() throws SchedulerException {
		if (scheduler == null) {
			return;
		}
		if (scheduler.isShutdown()) {
			scheduler = null;
			return;
		}
		if (scheduler.isStarted()) {
			scheduler.shutdown(false);
			scheduler = null;
			return;
		}
		throw new SchedulerException("Unable to close the scheduler");
	}
}
