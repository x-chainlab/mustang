package com.dimogo.open.myjobs.manager.admin.controller;

import com.dimogo.open.myjobs.manager.admin.service.MyJobsService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created by ethanx on 2017/5/10.
 */
@Controller
public class Admin {
	@Resource(name = "clusteredJobService")
	private MyJobsService service;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap model, @RequestParam(required=false, defaultValue = "", value = "error") String error) {
		if ("loginFailure".equalsIgnoreCase(error)) {
			model.addAttribute("error", "Invalid user name/password");
		}
		return "index";
	}

	public void setService(MyJobsService service) {
		this.service = service;
	}
}
