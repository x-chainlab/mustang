package com.dimogo.open.myjobs.notification;

import java.util.Map;

/**
 * Created by Ethan Xiao on 2017/4/18.
 */
public interface NotificationProcessor {

	void dispatch(Map<String, String> paras);

}
