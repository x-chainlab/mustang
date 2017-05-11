package com.dimogo.open.myjobs.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ethanx on 2017/5/11.
 */
public class UserDTO implements Serializable {
	private String userName;
	private String password;
	private List<String> roles;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public void setRole(String role) {
		if (this.roles == null) {
			this.roles = new ArrayList<String>();
		}
		this.roles.add(role);
	}
}
