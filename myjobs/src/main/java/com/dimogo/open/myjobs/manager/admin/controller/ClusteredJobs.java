package com.dimogo.open.myjobs.manager.admin.controller;

import com.alibaba.fastjson.JSON;
import com.dimogo.open.myjobs.dto.ClusteredJobInfo;
import com.dimogo.open.myjobs.dto.ExecutorInfo;
import com.dimogo.open.myjobs.dto.JobHistoryDTO;
import com.dimogo.open.myjobs.manager.admin.service.MyJobsService;
import com.dimogo.open.myjobs.types.UserRoleType;
import com.dimogo.open.myjobs.utils.AuthUtils;
import com.dimogo.open.myjobs.utils.JobUtils;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
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
	public String clusteredJobs(ModelMap model, SecurityContextHolderAwareRequestWrapper request, @RequestParam(defaultValue = "0") int startJob,
	                            @RequestParam(defaultValue = "20") int pageSize) {
		AuthUtils.setClusterAuthentication(request, model);
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
	public String getClusteredJob(ModelMap model, SecurityContextHolderAwareRequestWrapper request, @PathVariable String jobName) {
		AuthUtils.setClusterAuthentication(request, model);
		ClusteredJobInfo job = service.findJob(jobName);
		model.addAttribute("jobInfo", job);
		model.addAttribute("jobParameters", JobUtils.jsonToParameterList(JSON.parseObject(job.getParas())));
		model.addAttribute("jobExecutors", service.listJobExecutors(jobName));
		model.addAttribute("jobExecutions", service.listJobExecutions(jobName));
		return "clusteredjob";
	}

	@RequestMapping(value = "/clusteredjob/{jobName}/", method = RequestMethod.POST)
	public String setClusteredJob(ModelMap model, @RequestParam("jobName") String jobName,
	                              SecurityContextHolderAwareRequestWrapper request,
	                              @RequestParam("cron") String cron,
	                              @RequestParam("maxInstances") int maxInstances,
	                              @RequestParam("paras") String paras) {
		ClusteredJobInfo job = new ClusteredJobInfo();
		job.setJobName(jobName);
		job.setCron(cron);
		job.setMaxInstances(maxInstances);
		job.setParas(paras);
		service.updateJob(job);
		return getClusteredJob(model, request, jobName);
	}

	@RequestMapping(value = "/deleteclusteredjob/{jobName}", method = RequestMethod.GET)
	public String deleteClusteredJob(ModelMap model, SecurityContextHolderAwareRequestWrapper request, @PathVariable("jobName") String jobName) {
		AuthUtils.setClusterAuthentication(request, model);
		ClusteredJobInfo job = service.findJob(jobName);
		model.addAttribute("jobInfo", job);
		model.addAttribute("jobParameters", JobUtils.jsonToParameterList(JSON.parseObject(job.getParas())));
		model.addAttribute("jobExecutors", service.listJobExecutors(jobName));
		model.addAttribute("jobExecutions", service.listJobExecutions(jobName));

		boolean delete = service.deleteJob(jobName);
		job.setExists(!delete);
		model.addAttribute("deleted", delete);
		return "clusteredjob";
	}

	@RequestMapping(value = "/pausejobtrigger/{jobName}", method = RequestMethod.GET)
	public String pauseJobTrigger(ModelMap model, SecurityContextHolderAwareRequestWrapper request, @PathVariable("jobName") String jobName) {
		service.pauseTrigger(jobName);
		return getClusteredJob(model, request, jobName);
	}

	@RequestMapping(value = "/resumejobtrigger/{jobName}", method = RequestMethod.GET)
	public String resumeJobTrigger(ModelMap model, SecurityContextHolderAwareRequestWrapper request, @PathVariable("jobName") String jobName) {
		service.resumeTrigger(jobName);
		return getClusteredJob(model, request, jobName);
	}

	@RequestMapping(value = "/stopclusteredjob/{jobName}", method = RequestMethod.GET)
	public String stopClusteredJob(ModelMap model, SecurityContextHolderAwareRequestWrapper request, @PathVariable("jobName") String jobName) {
		service.stopJob(jobName);
		model.addAttribute("stopJob", true);
		return getClusteredJob(model, request, jobName);
	}

	@RequestMapping(value = "/history/{jobName}", method = RequestMethod.GET)
	public String getExecutionHistory(ModelMap model, @PathVariable("jobName") String jobName,
	                                  SecurityContextHolderAwareRequestWrapper request,
	                                  @RequestParam(defaultValue = "0") int start,
	                                  @RequestParam(defaultValue = "20") int pageSize) {
		AuthUtils.setClusterAuthentication(request, model);
		int totalHistories = service.countExecutionHistory(jobName);
		List<JobHistoryDTO> histories = service.listExecutionHistory(jobName, start, pageSize);
		model.addAttribute("jobName", jobName);
		model.addAttribute("histories", histories);
		model.addAttribute("startHistory", start == 0 && totalHistories > 0 ? start + 1 : start);
		model.addAttribute("endHistory", start + histories.size());
		model.addAttribute("totalHistories", totalHistories);
		if ((start + histories.size()) < totalHistories) {
			model.addAttribute("next", start + histories.size());
		}
		if (start > 0) {
			model.addAttribute("previous", start - pageSize);
		}
		return "history";
	}

	@RequestMapping(value = "/cleanhistory/{jobName}/", method = RequestMethod.GET)
	public String cleanExecutionHistory(ModelMap model, SecurityContextHolderAwareRequestWrapper request, @PathVariable("jobName") String jobName) {
		service.cleanExecutionHistory(jobName);
		return getClusteredJob(model, request, jobName);
	}

	public void setService(MyJobsService service) {
		this.service = service;
	}
}
