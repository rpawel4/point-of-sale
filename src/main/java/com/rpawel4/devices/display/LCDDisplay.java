package com.rpawel4.devices.display;

import com.rpawel4.product.Product;
import com.rpawel4.receipt.Receipt;
import com.rpawel4.receipt.ReceiptService;

public class LCDDisplay implements Display {
	
	private ReceiptService receiptService;
	
	public LCDDisplay(ReceiptService receiptService) {
		this.receiptService = receiptService;
	}

	@Override
	public void printProduct(Product product) {
		System.out.println("LCD: "+product.getName()+" "+product.getPrice());
	}

	@Override
	public void printMessage(DisplayedMessage message) {
		System.out.println("LCD: "+message);
	}

	@Override
	public void printTotalPrice(Receipt receipt) {
		System.out.println("LCD: "+receiptService.getTotalPrice(receipt));
	}

}
