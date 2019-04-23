package com.rpawel4;

import java.math.BigDecimal;
import com.rpawel4.devices.display.Display;
import com.rpawel4.devices.display.DisplayedMessage;
import com.rpawel4.devices.printer.Printer;
import com.rpawel4.devices.scanner.CodesScanner;
import com.rpawel4.product.InvalidCodeException;
import com.rpawel4.product.Product;
import com.rpawel4.product.ProductNotFoundException;
import com.rpawel4.product.ProductService;
import com.rpawel4.receipt.Item;
import com.rpawel4.receipt.Receipt;

public class PointOfSale {

	private static final String EXIT = "exit";

	private Display display;
	private Printer printer;
	private CodesScanner codesScanner;
	private ProductService productService;

	private Receipt receipt;

	public PointOfSale(Display display, Printer printer, CodesScanner codesScanner, ProductService productService) {

		this.display = display;
		this.printer = printer;
		this.codesScanner = codesScanner;
		this.productService = productService;
	}

	public void scan(String input) {
		if (input.equals(EXIT)) {
			finishCientService();
		} else {
			String barcode = codesScanner.scanCode(input);
			serveScannedCode(barcode);
		}
	}

	private void serveScannedCode(String barcode) {

		try {
			Product product = productService.getProduct(barcode);
			addProduct(product, BigDecimal.ONE);

		} catch (InvalidCodeException e) {
			e.printStackTrace();
			display.printMessage(DisplayedMessage.INVALID);
		} catch (ProductNotFoundException ex) {
			ex.printStackTrace();
			display.printMessage(DisplayedMessage.NOT_FOUND);
		}

	}

	public void addProduct(Product product, BigDecimal quantity) {
		receipt.addItem(new Item(product, quantity));
	}

	public void serveNewClient() {
		receipt = new Receipt();
	}

	public void finishCientService() {
		printer.printReceipt(receipt);
		display.printTotalPrice(receipt);
	}

}
