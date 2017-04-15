package com.dimogo.open.myjobs.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by Ethan Xiao on 2017/4/15.
 */
public class MyJobber implements Job {
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("my jobber execute:" + context.getJobDetail().getKey().getName());
	}
}
