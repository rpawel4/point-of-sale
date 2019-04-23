package com.rpawel4.product;

import com.rpawel4.devices.display.DisplayedMessage;

public class ProductNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException() {
		super(DisplayedMessage.NOT_FOUND.getMessage());
	}
}
