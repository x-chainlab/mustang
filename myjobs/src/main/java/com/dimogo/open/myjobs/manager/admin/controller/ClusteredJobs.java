package com.dimogo.open.myjobs.manager.admin.controller;

import com.dimogo.open.myjobs.dto.ClusteredJobInfo;
import com.dimogo.open.myjobs.manager.admin.service.MyJobsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Ethan Xiao on 2017/4/19.
 */
@Controller
public class ClusteredJobs {

	private MyJobsService service;

	@RequestMapping(value = "/clusteredjobs", method = RequestMethod.GET)
	public String executors(ModelMap model, @RequestParam(defaultValue = "0") int startJob,
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

	public void setService(MyJobsService service) {
		this.service = service;
	}
}
