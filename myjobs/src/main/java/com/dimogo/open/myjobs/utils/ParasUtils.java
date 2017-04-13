package com.dimogo.open.myjobs.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;

import java.util.Map;

/**
 * Created by Ethan Xiao on 2017/4/7.
 */
public class ParasUtils {

	public static JobParameters mergeConfParas(String job, JobParameters jobParameters) {
		String paras = loadParas(job);
		if (StringUtils.isBlank(paras)) {
			return jobParameters;
		}
		Map<String, JobParameter> userParas = jobParameters.getParameters();
		JSONObject confParas = JSON.parseObject(paras);
		for (String para : confParas.keySet()) {
			if (userParas.containsKey(para)) {
				continue;
			}
			userParas.put(para, new JobParameter(confParas.getString(para)));
		}
		return new JobParameters(userParas);
	}

	public static String loadParas(String job) {
		ZkClient zkClient = ZKUtils.newClient();
		try {
			String data = zkClient.readData(ZKUtils.buildJobParasPath(job), true);
			return data;
		} finally {
			zkClient.close();
		}
	}

	public static void setParas(String job, String paras) {
		ZkClient zkClient = ZKUtils.newClient();
		try {
			String path = ZKUtils.buildJobParasPath(job);
			if (zkClient.exists(path)) {
				zkClient.writeData(path, paras);
				return;
			}
			zkClient.create(path, paras, CreateMode.PERSISTENT);
		} finally {
			zkClient.close();
		}
	}
}
