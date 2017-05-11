package com.dimogo.open.myjobs.manager.admin.provider;

import com.dimogo.open.myjobs.manager.admin.service.MyJobsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by ethanx on 2017/5/10.
 */
public class MyJobsAuthenticationProvider implements AuthenticationProvider {

	private MyJobsService service;

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return null;
	}

	public boolean supports(Class<?> aClass) {
		return true;
	}

	public void setService(MyJobsService service) {
		this.service = service;
	}
}
