package com.gyh.common.exception;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CustomException() {
		super();
	}
	
	public CustomException(String s) {
		super(s);
	}
	
	public CustomException(Throwable t) {
		super(t);
	}
	
	public CustomException(String msg, Throwable t) {
		super(msg, t);
	}

}
