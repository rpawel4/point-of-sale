package com.rpawel4.devices.display;

import com.rpawel4.product.Product;
import com.rpawel4.receipt.Receipt;

public interface Display { 
	
	public void printProduct(Product product);
	public void printMessage(DisplayedMessage message);
	public void printTotalPrice(Receipt receipt);
}
