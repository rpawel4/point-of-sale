package com.rpawel4.product;

import com.rpawel4.devices.display.DisplayedMessage;

public class InvalidCodeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidCodeException() {
		super(DisplayedMessage.INVALID.getMessage());
	}
}
