package com.dimogo.open.myjobs.context;

import com.dimogo.open.myjobs.utils.JobInstanceUtils;
import com.dimogo.open.myjobs.utils.JobUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

/**
 * Created by Ethan Xiao on 2017/4/3.
 */
public class MyJobLauncher extends SimpleJobLauncher {
	@Override
	public JobExecution run(Job job, JobParameters jobParameters) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		if (JobInstanceUtils.allInstancesAreRunning(job.getName())) {
			throw new JobExecutionAlreadyRunningException("All Instances Are Running");
		}
		if (jobParameters.getString("mergeJobParas", "true").equalsIgnoreCase("true")) {
			//merge job parameter
			try {
				jobParameters = JobUtils.mergeConfJobParas(job.getName(), jobParameters);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return super.run(job, jobParameters);
	}
}
