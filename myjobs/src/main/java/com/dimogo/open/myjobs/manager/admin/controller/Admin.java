package com.dimogo.open.myjobs.manager.admin.controller;

import com.dimogo.open.myjobs.manager.admin.service.MyJobsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ethanx on 2017/5/10.
 */
@Controller
public class Admin {

	private MyJobsService service;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	public void setService(MyJobsService service) {
		this.service = service;
	}
}
