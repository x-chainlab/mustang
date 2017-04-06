package com.dimogo.open.myjobs.test;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * Created by Ethan Xiao on 2017/3/31.
 */
public class HelloJar implements Tasklet {
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("Hello JAR, My Jobs");
		return null;
	}
}
