package com.dimogo.open.myjobs.servlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by Ethan Xiao on 2017/4/2.
 */
public class ApplicationContextCatchServlet extends HttpServlet {
	public ApplicationContextCatchServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ApplicationContextCatcher.getInstance().set(context);
	}

	@Override
	public void destroy() {
		super.destroy();
	}

}
