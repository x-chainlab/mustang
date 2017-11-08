package com.dimogo.open.myjobs.test;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * Created by Ethan Xiao on 2017/3/31.
 */
public class HelloTasklet implements Tasklet {

	private String message = "Hello Tasklet, My Jobs";

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		//Thread.sleep(3 * 60 * 1000);
		System.out.println(message);
		return null;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
