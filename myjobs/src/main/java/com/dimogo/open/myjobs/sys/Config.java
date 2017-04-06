package com.dimogo.open.myjobs.sys;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Ethan Xiao on 2017/4/6.
 */
public class Config {
	private static Properties sysProperties;

	private static String getSysProperties(String key) {
		if (sysProperties == null) {
			synchronized (Config.class) {
				if (sysProperties == null) {
					sysProperties = new Properties();
					try {
						sysProperties.load(Config.class.getResourceAsStream("/conf/sys.properties"));
					} catch (IOException e) {
						sysProperties = null;
						return null;
					}
				}
			}
		}
		return sysProperties.getProperty(key);
	}

	public static String getZKServers() {
		return getSysProperties("zk.servers");
	}

	public static int getZKConnTimeout() {
		try {
			return Integer.parseInt(getSysProperties("zk.conn.timeout"));
		} catch (Throwable e) {
			return 10000;
		}
	}

	public static int getZKSessionTimeout() {
		try {
			return Integer.parseInt(getSysProperties("zk.session.timeout"));
		} catch (Throwable e) {
			return 10000;
		}
	}
}
