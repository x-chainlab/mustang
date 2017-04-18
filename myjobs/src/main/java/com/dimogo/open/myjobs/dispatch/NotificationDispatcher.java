package com.dimogo.open.myjobs.dispatch;

import java.util.Map;

/**
 * Created by Ethan Xiao on 2017/4/18.
 */
public interface NotificationDispatcher {

	void dispatch(Map<String, String> paras);

}
