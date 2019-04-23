package com.rpawel4.product;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.rpawel4.barcode.CodeValidator;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepo;
	@Mock
	private CodeValidator codeValidator;

	private ProductService productService;

	@Before
	public void test() {
		this.productService = new ProductService(productRepo, codeValidator);
	}

	@Test(expected = InvalidCodeException.class)
	public void shouldThrowInvalidCodeException() {
		String invalidBarcode = "123asd121";

		when(codeValidator.isCodeValid(invalidBarcode)).thenReturn(false);

		productService.getProduct(invalidBarcode); // exception should be thrown here
	}

	@Test(expected = ProductNotFoundException.class)
	public void shouldThrowProductNotFoundException() {
		String validBarcode = "123456789012";
		Product product = null;
		Optional<Product> optionalProd = Optional.ofNullable(product);

		when(codeValidator.isCodeValid(validBarcode)).thenReturn(true);
		when(productRepo.findByBarCode(validBarcode)).thenReturn(optionalProd);

		productService.getProduct(validBarcode); // exception should be thrown here

	}
	
	@Test
	public void canFindProductByValidCode() {
		String validBarcode = "123456789012";
		Product expectedProduct = new Product();
		expectedProduct.setBarcode(validBarcode);
		Optional<Product> optionalProd = Optional.of(expectedProduct);
		
		when(codeValidator.isCodeValid(validBarcode)).thenReturn(true);
		when(productRepo.findByBarCode(validBarcode)).thenReturn(optionalProd);
		
		Product actualProduct = productService.getProduct(validBarcode);
		assertEquals(expectedProduct, actualProduct);
	}

}
