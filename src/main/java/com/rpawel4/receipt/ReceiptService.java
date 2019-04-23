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

}
