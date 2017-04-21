package com.dimogo.open.myjobs.listener;

import com.dimogo.open.myjobs.dto.JobExecutionDTO;
import com.dimogo.open.myjobs.utils.ID;
import com.dimogo.open.myjobs.utils.ZKUtils;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Ethan Xiao on 2017/4/4.
 */
public class JobStatusListener implements JobExecutionListener {
	private static final String EXECUTION_ID = "execution.id";
	private Map<UUID, ZkClient> zkClients = new LinkedHashMap<UUID, ZkClient>();

	public void beforeJob(JobExecution jobExecution) {
		synchronized (this) {
			try {
				ZkClient zkClient = ZKUtils.newClient();
				UUID executionId = UUID.randomUUID();
				ZKUtils.create(zkClient, ZKUtils.buildJobExecutionsPath(jobExecution.getJobInstance().getJobName()), null, CreateMode.PERSISTENT);

				JobExecutionDTO jobExecutionDTO = new JobExecutionDTO();
				jobExecutionDTO.setExecutorId(ID.ExecutorID.toString());
				jobExecutionDTO.setExecutionId(executionId.toString());
				jobExecutionDTO.setJobId(jobExecution.getJobInstance().getId());
				jobExecutionDTO.setJobInstanceId(jobExecution.getJobInstance().getInstanceId());
				jobExecutionDTO.setJobName(jobExecution.getJobInstance().getJobName());

				zkClient.createEphemeral(ZKUtils.buildJobExecutionPath(jobExecution.getJobInstance().getJobName(), executionId.toString()), jobExecutionDTO);
				jobExecution.getExecutionContext().put(EXECUTION_ID, executionId);
				zkClients.put(executionId, zkClient);
			} catch (Exception e) {
				throw new RuntimeException("before job exception", e);
			}
		}
	}

	public void afterJob(JobExecution jobExecution) {
		synchronized (this) {
			UUID executionId = (UUID) jobExecution.getExecutionContext().get(EXECUTION_ID);
			try {
				ZkClient zkClient = zkClients.get(executionId);
				if (zkClient != null) {
					zkClient.close();
				}
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				zkClients.remove(executionId);
			}
		}
	}
}
