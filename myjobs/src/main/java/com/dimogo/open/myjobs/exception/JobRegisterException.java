package com.dimogo.open.myjobs.exception;

/**
 * Created by Ethan Xiao on 2017/4/6.
 */
public class JobRegisterException extends RuntimeException {
	public JobRegisterException(String message) {
		super(message);
	}

	public JobRegisterException(String message, Throwable cause) {
		super(message, cause);
	}
}
