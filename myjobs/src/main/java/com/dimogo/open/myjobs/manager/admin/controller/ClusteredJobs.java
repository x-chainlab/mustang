package com.dimogo.open.myjobs.manager.admin.controller;

import com.alibaba.fastjson.JSON;
import com.dimogo.open.myjobs.dto.ClusteredJobInfo;
import com.dimogo.open.myjobs.dto.ExecutorInfo;
import com.dimogo.open.myjobs.manager.admin.service.MyJobsService;
import com.dimogo.open.myjobs.utils.JobUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ethan Xiao on 2017/4/19.
 */
@Controller
public class ClusteredJobs {

	private MyJobsService service;

	@RequestMapping(value = "/clusteredjobs", method = RequestMethod.GET)
	public String clusteredJobs(ModelMap model, @RequestParam(defaultValue = "0") int startJob,
	                        @RequestParam(defaultValue = "20") int pageSize) {
		int totalJobs = service.countJobs();
		List<ClusteredJobInfo> jobs = service.listJobs(startJob, pageSize);
		model.addAttribute("jobs", jobs);
		model.addAttribute("startJob", startJob == 0 && totalJobs > 0 ? startJob + 1 : startJob);
		model.addAttribute("endJob", startJob + jobs.size());
		model.addAttribute("totalJobs", totalJobs);
		if ((startJob + jobs.size()) < totalJobs) {
			model.addAttribute("next", startJob + jobs.size());
		}
		if (startJob > 0) {
			model.addAttribute("previous", startJob - pageSize);
		}
		return "clusteredjobs";
	}

	@RequestMapping(value = "/clusteredjob/{jobName}/", method = RequestMethod.GET)
	public String getClusteredJob(ModelMap model, @PathVariable String jobName) {
		ClusteredJobInfo job = service.findJob(jobName);
		model.addAttribute("jobInfo", job);
		model.addAttribute("jobParameters", JobUtils.jsonToParameterList(JSON.parseObject(job.getParas())));
		model.addAttribute("jobExecutors", service.listJobExecutors(jobName));
		model.addAttribute("jobExecutions", service.listJobExecutions(jobName));
		return "clusteredjob";
	}

	@RequestMapping(value = "/clusteredjob/{jobName}/", method = RequestMethod.POST)
	public String setClusteredJob(ModelMap model, @RequestParam("jobName") String jobName,
	                              @RequestParam("cron") String cron,
	                              @RequestParam("maxInstances") int maxInstances,
	                              @RequestParam("paras") String paras) {
		ClusteredJobInfo job = new ClusteredJobInfo();
		job.setJobName(jobName);
		job.setCron(cron);
		job.setMaxInstances(maxInstances);
		job.setParas(paras);
		service.updateJob(job);
		return getClusteredJob(model, jobName);
	}

	public void setService(MyJobsService service) {
		this.service = service;
	}
}
