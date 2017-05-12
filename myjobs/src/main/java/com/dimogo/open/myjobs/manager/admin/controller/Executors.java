package com.dimogo.open.myjobs.manager.admin.controller;

import com.dimogo.open.myjobs.dto.ExecutorDetails;
import com.dimogo.open.myjobs.dto.ExecutorInfo;
import com.dimogo.open.myjobs.manager.admin.service.MyJobsService;
import com.dimogo.open.myjobs.utils.AuthUtils;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Ethan Xiao on 2017/4/19.
 */
@Controller
public class Executors {

	private MyJobsService service;

	@RequestMapping(value = "/executors", method = RequestMethod.GET)
	public String executors(ModelMap model, SecurityContextHolderAwareRequestWrapper request, @RequestParam(defaultValue = "0") int start,
	                        @RequestParam(defaultValue = "20") int pageSize) {
		AuthUtils.setClusterAuthentication(request, model);
		int totalExecutors = service.countExecutors();
		List<ExecutorInfo> executors = service.listExecutors(start, pageSize);
		model.addAttribute("executors", executors);
		model.addAttribute("startExecutor", start == 0 && totalExecutors > 0 ? start + 1 : start);
		model.addAttribute("endExecutor", start + executors.size());
		model.addAttribute("totalExecutors", totalExecutors);
		if ((start + executors.size()) < totalExecutors) {
			model.addAttribute("next", start + executors.size());
		}
		if (start > 0) {
			model.addAttribute("previous", start - pageSize);
		}
		return "executors";
	}

	@RequestMapping(value = "/executor/{executorId}/")
	public String executor(ModelMap model, SecurityContextHolderAwareRequestWrapper request, @PathVariable("executorId") String executorId) {
		AuthUtils.setClusterAuthentication(request, model);
		ExecutorDetails details = service.findExecutor(executorId);
		model.addAttribute("executor", details);
		if (details.getRuntime() != null) {
			model.addAttribute("cpus", details.getRuntime().getCpus());
			model.addAttribute("disks", details.getRuntime().getDisks());
			model.addAttribute("nets", details.getRuntime().getNets());
			model.addAttribute("ethernets", details.getRuntime().getEthernets());
			model.addAttribute("properties", details.getRuntime().getSystemProperties());
			model.addAttribute("env", details.getRuntime().getSystemEnv());
		}
		return "executor";
	}

	public void setService(MyJobsService service) {
		this.service = service;
	}
}
