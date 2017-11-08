package com.dimogo.open.myjobs.notification;

import java.util.Map;

/**
 * JOB通知接口
 * Created by Ethan Xiao on 2017/4/18.
 */
public interface NotificationProcessor {

	void dispatch(Map<String, String> paras);

}
