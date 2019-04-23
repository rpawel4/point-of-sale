package com.rpawel4.devices.printer;

import com.rpawel4.product.Product;
import com.rpawel4.receipt.Item;
import com.rpawel4.receipt.Receipt;
import com.rpawel4.receipt.ReceiptService;

public class BasicPrinter implements Printer {
	
	private ReceiptService receiptService;
	
	public BasicPrinter(ReceiptService receiptService) {
		this.receiptService = receiptService;
	}

	@Override
	public void printReceipt(Receipt receipt) {

		System.out.println("PRINTER: "+buildReceipt(receipt));
	}

	public StringBuilder buildReceipt(Receipt receipt) {
		StringBuilder sb = new StringBuilder();
		
		for (Item item : receipt.getItems()) {
			Product product = item.getProduct();
			sb.append(product.getName());
			sb.append("  ");
			sb.append(item.getQuantity());
			sb.append(" x");
			sb.append(product.getPrice());
			sb.append("  ");
			sb.append(receiptService.getItemTotalPrice(item));
			sb.append(System.lineSeparator());
		}
		sb.append("TOTAL PRICE  ");
		sb.append(receiptService.getTotalPrice(receipt));

		return sb;
	}

}
