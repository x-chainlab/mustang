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

}
