package com.dimogo.open.myjobs.manager.admin.controller;

import com.dimogo.open.myjobs.dto.MasterInfo;
import com.dimogo.open.myjobs.dto.User2DTO;
import com.dimogo.open.myjobs.dto.UserDTO;
import com.dimogo.open.myjobs.manager.admin.service.MyJobsService;
import com.dimogo.open.myjobs.types.UserRoleType;
import com.dimogo.open.myjobs.utils.AuthUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Ethan Xiao on 2017/4/19.
 */
@Controller
public class Master {
	@Resource(name = "clusteredJobService")
	private MyJobsService service;

	@RequestMapping(value = "/master", method = RequestMethod.GET)
	public String master(ModelMap model, Authentication authentication, SecurityContextHolderAwareRequestWrapper request) {
		AuthUtils.setClusterAuthentication(request, model);
		MasterInfo masterInfo = service.findMaster();
		model.addAttribute("master", masterInfo);
		String username = (String) authentication.getPrincipal();
		UserDTO user = service.findUser(username);
		String password = user.getPassword();
		List<User2DTO> user2DTOs = service.listUsers();
		UserRoleType[] userRoleTypes = UserRoleType.values();
		model.addAttribute("users", user2DTOs);
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		model.addAttribute("roles", userRoleTypes);
		return "master";
	}

	@RequestMapping(value = "/updateUserPassword", method = RequestMethod.POST)
	public String updateUserPassword(ModelMap model, Authentication authentication, @RequestParam String username, @RequestParam String password, SecurityContextHolderAwareRequestWrapper request) {
		if (StringUtils.isBlank(password)) {
			model.addAttribute("message", "User password can not be empty.");
			return master(model, authentication, request);
		}
		List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) authentication.getAuthorities();
		List<String> roleList = new ArrayList<String>();
		for (GrantedAuthority grantedAuthority : grantedAuthorities) {
			roleList.add(grantedAuthority.toString());
		}
		boolean success = service.updateUser(username, password, roleList);
		model.addAttribute("message", success ? "Updated user password." : "Update user password failed.");
		return master(model, authentication, request);
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(ModelMap model, Authentication authentication, SecurityContextHolderAwareRequestWrapper request, @RequestParam String username, @RequestParam String password, @RequestParam String authority) {
		UserDTO user = service.findUser(username);
		if (user != null) {
			model.addAttribute("message", "User " + user.getUserName() + " already existing.");
			return master(model, authentication, request);
		}
		List<String> roleList = new ArrayList<String>();
		roleList.add(authority);
		boolean success = service.updateUser(username, password, roleList);
		model.addAttribute("message", success ? "Added user " + username : "Add user failed");
		return master(model, authentication, request);
	}

	@RequestMapping(value = "/userDetail/{username}", method = RequestMethod.GET)
	public String userDetail(@PathVariable String username, ModelMap model, SecurityContextHolderAwareRequestWrapper request, Authentication authentication) {
		User2DTO user2DTO = new User2DTO();
		UserDTO user = service.findUser(username);
		user2DTO.setRoles(user.getRoles().toString());
		user2DTO.setUsername(user.getUserName());
		user2DTO.setPassword(user.getPassword());
		UserRoleType[] userRoleTypes = UserRoleType.values();
		model.addAttribute("roles", userRoleTypes);
		model.addAttribute("user", user2DTO);
		return "userDetail";
	}

	@RequestMapping(value="/deleteUser/{username}/", method = RequestMethod.GET)
	public String deleteUser(ModelMap model, Authentication authentication, SecurityContextHolderAwareRequestWrapper request, @PathVariable String username) {
		service.deleteUser(username);
		return master(model, authentication, request);
	}

	@RequestMapping(value = "/updateUserDetail", method = RequestMethod.POST)
	public String updateUserDetail(ModelMap model, Authentication authentication, @RequestParam String username, @RequestParam String password, @RequestParam String authority, SecurityContextHolderAwareRequestWrapper request) {
		List<String> roleList = new ArrayList<String>();
		roleList.add(authority);
		service.updateUser(username, password, roleList);
		return master(model, authentication, request);
	}

	public void setService(MyJobsService service) {
		this.service = service;
	}
}
