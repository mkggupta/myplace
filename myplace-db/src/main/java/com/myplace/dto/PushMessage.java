package com.myplace.dto;

public class PushMessage {
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	String Type;
	String url;
	String message;

}
