package com.dimogo.open.myjobs.utils;

import com.dimogo.open.myjobs.types.UserRoleType;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.ui.ModelMap;

/**
 * Created by ethanx on 2017/5/12.
 */
public class AuthUtils {

	public static void setClusterAuthentication(SecurityContextHolderAwareRequestWrapper request, ModelMap model) {
		model.addAttribute("isClusterAdmin", request.isUserInRole(UserRoleType.ROLE_CLUSTER_ADMIN.getAlias()));
		model.addAttribute("isClusterSupper", request.isUserInRole(UserRoleType.ROLE_SUPPER.getAlias()));
	}
}
