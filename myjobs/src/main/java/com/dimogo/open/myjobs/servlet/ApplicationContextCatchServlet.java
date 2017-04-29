package com.dimogo.open.myjobs.servlet;

import com.dimogo.open.myjobs.quartz.MyJobMaster;
import com.dimogo.open.myjobs.quartz.MyJobSlave;
import com.dimogo.open.myjobs.sys.ExecutorResourceMonitor;
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

		Thread masterThread = new Thread(new MyJobMaster());
		Thread slaveThread = new Thread(new MyJobSlave());
		Thread monitorThread = new Thread(new ExecutorResourceMonitor());
		masterThread.start();
		slaveThread.start();
		monitorThread.start();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

}
