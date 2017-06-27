package com.dimogo.open.myjobs.types;

/**
 * Created by ethanx on 2017/5/12.
 */
public enum UserRoleType {
	ROLE_SUPPER("SUPPER"),
	ROLE_NON_CLUSTER_ADMIN("NON_CLUSTER_ADMIN"),
	ROLE_CLUSTER_ADMIN("CLUSTER_ADMIN"),
	ROLE_CLUSTER_MONITOR("CLUSTER_MONITOR"),
	;

	private String alias;

	private UserRoleType(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return this.alias;
	}
}
