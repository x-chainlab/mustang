package com.dimogo.open.myjobs.dto;

/**
 * Created by Ethan Xiao on 2017/4/28.
 */
public class ExecutorDetails {
	private String id;

	private RuntimeInfo runtime;

	public ExecutorDetails() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RuntimeInfo getRuntime() {
		return runtime;
	}

	public void setRuntime(RuntimeInfo runtime) {
		this.runtime = runtime;
	}
}
