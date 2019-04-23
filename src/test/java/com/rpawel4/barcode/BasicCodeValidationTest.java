package com.rpawel4.barcode;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BasicCodeValidationTest {

	private BasicCodeValidator basicCodeValidator;
	
	@Before
	public void setUp() {
		this.basicCodeValidator = new BasicCodeValidator();
	}
	
	@Test
	public void canRejectEmptyCode() {
		String code = "";
		
		assertFalse(basicCodeValidator.isCodeValid(code));
	}
	
	@Test
	public void canRejectTooShortCode() {
		int min = BasicCodeValidator.MIN_CODE_LENGTH;
		StringBuilder code = new StringBuilder();
		for(int i = 0; i < min - 1; i++) {
			code.append("9");
		}
		
		assertFalse(basicCodeValidator.isCodeValid(code.toString()));
	}
	
	@Test
	public void canRejectTooLongCode() {
		int max = BasicCodeValidator.MAX_CODE_LENGTH;
		StringBuilder code = new StringBuilder();
		for(int i = 0; i < max + 1; i++) {
			code.append("9");
		}
		
		assertFalse(basicCodeValidator.isCodeValid(code.toString()));
	}
	
	@Test
	public void canConfirmValidCode() {
		String validCode = "123456789012";
		
		assertTrue(basicCodeValidator.isCodeValid(validCode));
	}

}
