package com.dimogo.open.myjobs.utils;

import com.dimogo.open.myjobs.sys.Config;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;

/**
 * Created by Ethan Xiao on 2017/4/6.
 */
public class ZKUtils {

	public enum Path {
		Root("/mustang", null),
		MyJobs("/myjobs", Root),
		Jobs("/jobs", MyJobs),
		Executors("/executors", MyJobs),
		Notifications("/notifications", MyJobs),
		Master("/master", MyJobs),
		MasterNode("/node", Master),
		;

		private String path;
		private Path parent;

		Path(String path, Path parent) {
			this.parent = parent;
			this.path = path;
		}

		static void buildPath(Path path, StringBuilder builder) {
			if (path.parent != null) {
				buildPath(path.parent, builder);
			}
			builder.append(path.path);
		}

		public String build() {
			StringBuilder builder = new StringBuilder();
			this.buildPath(this, builder);
			return builder.toString();
		}
	}

	public static ZkClient newClient() {
		return new ZkClient(Config.getZKServers(), Config.getZKSessionTimeout(), Config.getZKConnTimeout(), new SerializableSerializer());
	}

	public static void create(ZkClient zkClient, String path, Object data, CreateMode mode) throws Exception {
		try {
			zkClient.create(path, data, mode);
		} catch (Exception e) {
			if (e instanceof ZkNodeExistsException) {
				return;
			}
			throw e;
		}
	}

	public static String buildJobPath(String job) {
		return Path.Jobs.build() + "/" + job;
	}

	public static String buildJobParasPath(String job) {
		return buildJobPath(job) + "/paras";
	}

	public static String buildJobCronPath(String job) {
		return buildJobPath(job) + "/cron";
	}

	public static String buildNotificationPath(String notification) {
		return Path.Notifications.build() + "/" + notification;
	}

	public static String buildNotificationLockPath(String notification) {
		return buildNotificationPath(notification) + "/locked";
	}

	public static String buildJobExecutorsPath(String job) {
		return Path.Jobs.build() + "/" + job + "/executors";
	}

	public static String buildJobExecutorPath(String job, String executor) {
		return buildJobExecutorsPath(job) + "/" + executor;
	}

	public static String buildExecutorIDPath() {
		return Path.Executors.build() + "/" + ID.ExecutorID;
	}

	public static String buildJobExecutionsPath(String job) {
		return Path.Jobs.build() + "/" + job + "/executions";
	}

	public static String buildJobExecutionPath(String job, String id) {
		return buildJobExecutionsPath(job) + "/" + id;
	}

	public static String buildJobInstancesPath(String job) {
		return buildJobPath(job) + "/instances";
	}

	public static int countJobExecutions(ZkClient zkClient, String job) {
		return zkClient.countChildren(buildJobExecutionsPath(job));
	}

	public static class ZkExclusiveLock {

		private ZkClient zkClient;

		public ZkExclusiveLock() {
			this.zkClient = newClient();
		}

		public void unlock() {
			try {
				this.zkClient.close();
			} catch (Throwable e) {

			}
		}

		public ZkClient getZkClient() {
			return zkClient;
		}

		public boolean lock(String lockPath, String lockedPath) {
			return tryLock(lockPath, lockedPath, -1);
		}

		public boolean tryLock(String lockPath, String lockedPath, long timeout) {
			try {
				final long exp = timeout > 0 ? System.currentTimeMillis() + timeout : -1;
				while (exp == -1 || System.currentTimeMillis() < exp) {
					try {
						create(zkClient, lockPath, null, CreateMode.PERSISTENT);
						zkClient.createEphemeral(lockedPath, ID.ExecutorID);
						return true;
					} catch (Throwable e) {
						if (e instanceof ZkNodeExistsException) {
							zkClient.watchForChilds(lockPath);
						}
						throw e;
					}
				}
				return false;
			} catch (Throwable e) {
				throw new RuntimeException("trying to lock path " + lockPath + " exception", e);
			}
		}
	}

}
