package com.dimogo.open.myjobs.manager.admin.service;

import com.dimogo.open.myjobs.dto.ClusteredJobInfo;
import com.dimogo.open.myjobs.dto.ExecutorInfo;
import com.dimogo.open.myjobs.dto.NotificationInfo;

import java.util.List;

/**
 * Created by Ethan Xiao on 2017/4/19.
 */
public interface MyJobsService {

	List<ClusteredJobInfo> listJobs(int start, int pageSize);

	int countJobs();

	List<ExecutorInfo> listExecutors(int start, int pageSize);

	int countExecutors();

	List<NotificationInfo> listNotifications(int start, int pageSize);

	int countNotifications();

}
