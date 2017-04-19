package com.dimogo.open.myjobs.manager.admin.menu;

import org.springframework.batch.admin.web.resources.BaseMenu;

/**
 * Created by Ethan Xiao on 2017/4/19.
 */
public class ClusteredJobs extends BaseMenu {
	public ClusteredJobs() {
		super("/clusteredjobs", "Clustered Jobs", 5);
	}
}
