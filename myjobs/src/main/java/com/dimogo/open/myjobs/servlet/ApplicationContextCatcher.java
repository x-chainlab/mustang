package com.dimogo.open.myjobs.servlet;

import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Ethan Xiao on 2017/4/2.
 */
public class ApplicationContextCatcher {
	private static class ApplicationContextCatcherHolder {
		private static ApplicationContextCatcher instance = new ApplicationContextCatcher();
	}

	private WebApplicationContext context;

	private ApplicationContextCatcher() {

	}

	public static ApplicationContextCatcher getInstance() {
		return ApplicationContextCatcherHolder.instance;
	}

	static void set(WebApplicationContext context) {
		getInstance().context = context;
	}

	public WebApplicationContext get() {
		return context;
	}
}
