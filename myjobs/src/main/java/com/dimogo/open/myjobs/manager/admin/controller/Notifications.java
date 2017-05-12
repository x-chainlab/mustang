package com.dimogo.open.myjobs.manager.admin.controller;

import com.dimogo.open.myjobs.dto.NotificationInfo;
import com.dimogo.open.myjobs.manager.admin.service.MyJobsService;
import com.dimogo.open.myjobs.utils.AuthUtils;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
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
public class Notifications {

	private MyJobsService service;

	@RequestMapping(value = "/notifications", method = RequestMethod.GET)
	public String executors(ModelMap model, @RequestParam(defaultValue = "0") int start,
	                        SecurityContextHolderAwareRequestWrapper request,
	                        @RequestParam(defaultValue = "20") int pageSize) {
		AuthUtils.setClusterAuthentication(request, model);
		int totalNotifications = service.countNotifications();
		List<NotificationInfo> notifications = service.listNotifications(start, pageSize);
		model.addAttribute("notifications", notifications);
		model.addAttribute("startNotification", start == 0 && totalNotifications > 0 ? start + 1 : start);
		model.addAttribute("endNotification", start + notifications.size());
		model.addAttribute("totalNotifications", totalNotifications);
		if ((start + notifications.size()) < totalNotifications) {
			model.addAttribute("next", start + notifications.size());
		}
		if (start > 0) {
			model.addAttribute("previous", start - pageSize);
		}
		return "notifications";
	}

	public void setService(MyJobsService service) {
		this.service = service;
	}
}
