package com.dimogo.open.myjobs.utils;

import com.dimogo.open.myjobs.types.UserRoleType;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.ui.ModelMap;

/**
 * Created by ethanx on 2017/5/12.
 */
public class AuthUtils {

	public static void setClusterAuthentication(SecurityContextHolderAwareRequestWrapper request, ModelMap model) {
		if (request.isUserInRole(UserRoleType.ROLE_SUPPER.name())) {
			model.addAttribute("isClusterAdmin", true);
			model.addAttribute("isClusterSupper", true);
			return;
		}
		if (request.isUserInRole(UserRoleType.ROLE_CLUSTER_ADMIN.name())) {
			model.addAttribute("clusterAdmin", true);
			model.addAttribute("isClusterSupper", false);
			return;
		}
		model.addAttribute("clusterAdmin", false);
		model.addAttribute("isClusterSupper", false);
	}
}
