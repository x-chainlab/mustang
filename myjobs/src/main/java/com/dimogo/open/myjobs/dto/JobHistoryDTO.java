package com.dimogo.open.myjobs.dto;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ethanx on 2017/5/8.
 */
public class JobHistoryDTO implements Serializable {
	private Date start;
	private Date end;
	private BatchStatus status;
	private ExitStatus exitStatus;

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public BatchStatus getStatus() {
		return status;
	}

	public void setStatus(BatchStatus status) {
		this.status = status;
	}

	public ExitStatus getExitStatus() {
		return exitStatus;
	}

	public void setExitStatus(ExitStatus exitStatus) {
		this.exitStatus = exitStatus;
	}
}
