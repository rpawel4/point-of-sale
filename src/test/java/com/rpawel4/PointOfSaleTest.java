package com.rpawel4;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import com.rpawel4.barcode.BasicCodeValidator;
import com.rpawel4.barcode.CodeValidator;
import com.rpawel4.devices.display.Display;
import com.rpawel4.devices.display.DisplayedMessage;
import com.rpawel4.devices.display.LCDDisplay;
import com.rpawel4.devices.printer.BasicPrinter;
import com.rpawel4.devices.printer.Printer;
import com.rpawel4.devices.scanner.BarcodesScanner;
import com.rpawel4.devices.scanner.CodesScanner;
import com.rpawel4.product.Product;
import com.rpawel4.product.ProductRepository;
import com.rpawel4.product.ProductService;
import com.rpawel4.receipt.ReceiptService;

public class PointOfSaleTest {

	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	private PointOfSale pos;

	@Before
	public void setUp() {
		ReceiptService receiptService = new ReceiptService();
		Display display = new LCDDisplay(receiptService);

		Printer printer = new BasicPrinter(receiptService);

		CodesScanner codesScanner = new BarcodesScanner();

		ProductRepository productRepo = new ProductRepository() {

			@Override
			public Optional<Product> findByBarCode(String barcode) {

				Product product = null;

				switch (barcode) {
				case "111111111111":
					product = new Product();
					product.setId(1L);
					product.setName("Tomato");
					product.setBarcode("111111111111");
					product.setPrice(new BigDecimal("1.99"));
					break;

				case "222222222222":
					product = new Product();
					product.setId(2L);
					product.setName("Milk");
					product.setBarcode("222222222222");
					product.setPrice(new BigDecimal("1.80"));
					break;

				case "333333333333":
					product = new Product();
					product.setId(3L);
					product.setName("Cheese");
					product.setBarcode("333333333333");
					product.setPrice(new BigDecimal("4.49"));
					break;

				}

				return Optional.ofNullable(product);

			}
		};
		CodeValidator codeValidator = new BasicCodeValidator();
		ProductService productService = new ProductService(productRepo, codeValidator);
		pos = new PointOfSale(display, printer, codesScanner, productService);
		pos.serveNewClient();
	}

	@Test
	public void canAddProductByValidCode() {
		String expectedLog = "LCD: Tomato 1.99" + System.lineSeparator();

		pos.scan("111111111111");

		assertEquals(expectedLog, systemOutRule.getLog());
	}

	@Test
	public void canPrintInvalidCodeMessage() {
		String expectedLog = "LCD: " + DisplayedMessage.INVALID.getMessage() + System.lineSeparator();

		pos.scan("");
		pos.scan("123123123aaa");

		assertEquals(expectedLog + expectedLog, systemOutRule.getLog());
	}

	@Test
	public void canPrintProductNotFoud() {
		String expectedLog = "LCD: " + DisplayedMessage.NOT_FOUND.getMessage() + System.lineSeparator();

		pos.scan("333333333334");

		assertEquals(expectedLog, systemOutRule.getLog());
	}

}
