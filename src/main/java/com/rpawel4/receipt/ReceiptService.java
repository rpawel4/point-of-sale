package com.rpawel4.receipt;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.rpawel4.product.Product;

public class ReceiptService {
	
	public BigDecimal getItemTotalPrice(Item item) {
		Product product = item.getProduct();
		BigDecimal itemTotalPrice = product.getPrice().multiply(item.getQuantity());
		itemTotalPrice = itemTotalPrice.setScale(2, RoundingMode.HALF_UP);
		return itemTotalPrice;
	}
	
	public BigDecimal getTotalPrice(Receipt receipt) {
		BigDecimal totalPrice = BigDecimal.ZERO;
		
		for(Item item : receipt.getItems()) {
			totalPrice = totalPrice.add(getItemTotalPrice(item));
		}
		
		return totalPrice;
	}
	
	public String buildReceipt(Receipt receipt) {
		StringBuilder sb = new StringBuilder();
		
		for (Item item : receipt.getItems()) {
			Product product = item.getProduct();
			sb.append(product.getName());
			sb.append("  ");
			sb.append(item.getQuantity());
			sb.append(" x");
			sb.append(product.getPrice());
			sb.append("  ");
			sb.append(getItemTotalPrice(item));
			sb.append(System.lineSeparator());
		}
		sb.append("TOTAL PRICE  ");
		sb.append(getTotalPrice(receipt));

		return sb.toString();
	}

}
