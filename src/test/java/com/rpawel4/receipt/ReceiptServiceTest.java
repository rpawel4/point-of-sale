package com.rpawel4.receipt;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.rpawel4.product.Product;

public class ReceiptServiceTest {

	private ReceiptService receiptService;

	private Receipt receipt;
	private Item item;
	private Item item2;

	@Before
	public void setUp() {

		this.receiptService = new ReceiptService();

		Product product1 = new Product();
		product1.setName("prod1");
		product1.setPrice(new BigDecimal("19.90"));
		this.item = new Item(product1, new BigDecimal("0.198"));

		Product product2 = new Product();
		product2.setName("prod2");
		product2.setPrice(new BigDecimal("20.06"));
		this.item2 = new Item(product2, BigDecimal.ONE);

		this.receipt = new Receipt();
		receipt.addItem(item);
		receipt.addItem(item2);
	}

	@Test
	public void canGetItemTotalPrice() {
		// 19.90 x 0.198 = 3.9402 So expected value is 3.94
		BigDecimal expectedValue = new BigDecimal("3.94");

		BigDecimal actualValue = receiptService.getItemTotalPrice(item);

		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void canGetTotalPrice() {
		BigDecimal expectedValue = new BigDecimal("24.00");

		BigDecimal actualValue = receiptService.getTotalPrice(receipt);

		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void canBuildReceipt() {
		StringBuilder sb = new StringBuilder();
		sb.append("prod1  0.198 x19.90  3.94");
		sb.append(System.lineSeparator());
		sb.append("prod2  1 x20.06  20.06");
		sb.append(System.lineSeparator());
		sb.append("TOTAL PRICE  24.00");
		
		String avtualValue = receiptService.buildReceipt(receipt);
		
		assertEquals(sb.toString(), avtualValue);
	}

}
