package com.rpawel4.devices.display;

public enum DisplayedMessage {

	NOT_FOUND("Product not found"),
	INVALID("Invalid bar-code");
	
	private String message;

	private DisplayedMessage(String message) {
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	
}
