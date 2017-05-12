package com.dimogo.open.myjobs.manager.admin.provider;

import com.dimogo.open.myjobs.dto.UserDTO;
import com.dimogo.open.myjobs.manager.admin.service.MyJobsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ethanx on 2017/5/10.
 */
public class MyJobsAuthenticationProvider implements AuthenticationProvider {

	private MyJobsService service;

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserDTO user = service.findUser(authentication.getName());
		if (user == null) {
			throw new BadCredentialsException("Invalid User Name!");
		}
		if (!user.getPassword().equals(authentication.getCredentials())) {
			throw new BadCredentialsException("Invalid User Password!");
		}
		if (CollectionUtils.isEmpty(user.getRoles())) {
			throw new BadCredentialsException("No Authenticated User!");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(user.getRoles().size());
		for (String auth : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(auth));
		}
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword(), authorities);
		return token;
	}

	public boolean supports(Class<?> aClass) {
		return true;
	}

	public void setService(MyJobsService service) {
		this.service = service;
	}
}
