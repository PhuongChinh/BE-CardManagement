package com.example.demo.response;

public class MessageResp {
	private int code;
	private String message;
	private Object result;
	
	
	public MessageResp(int code, String message, Object result) {
		super();
		this.code = code;
		this.message = message;
		this.result = result;
	}
	
	public MessageResp() {
		super();
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	
}
