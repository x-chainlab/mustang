package com.dimogo.open.myjobs.context;

import org.springframework.batch.core.JobExecution;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ethanx on 2017/5/4.
 */
public class ExecutionManager {

	private static class ExecutionManagerHolder {
		private static ExecutionManager instance = new ExecutionManager();
	}

	public static ExecutionManager getInstance() {
		return ExecutionManagerHolder.instance;
	}

	private List<JobExecution> executions = Collections.synchronizedList(new LinkedList<JobExecution>());

	private ExecutionManager() {

	}

	public void register(JobExecution execution) {
		executions.add(execution);
	}

	public void stopJobExecutions(String jobName) {
		Iterator<JobExecution> it = executions.iterator();
		JobExecution execution;
		while (it.hasNext()) {
			execution = it.next();
			if (jobName.equals(execution.getJobInstance().getJobName())) {
				execution.stop();
				it.remove();
			}
		}
	}
}
