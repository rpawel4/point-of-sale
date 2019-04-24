package com.rpawel4.devices.printer;

import com.rpawel4.receipt.Receipt;
import com.rpawel4.receipt.ReceiptService;

public class BasicPrinter implements Printer {
	
	private ReceiptService receiptService;
	
	public BasicPrinter(ReceiptService receiptService) {
		this.receiptService = receiptService;
	}

	@Override
	public void printReceipt(Receipt receipt) {

		System.out.println("PRINTER:");
		System.out.println(receiptService.buildReceipt(receipt));
	}

	

}
