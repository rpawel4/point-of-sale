package com.rpawel4.barcode;

import java.util.regex.Pattern;

public class BasicCodeValidator implements CodeValidator{
	
	public static final int MIN_CODE_LENGTH = 12;
	public static final int MAX_CODE_LENGTH = 12;
	
	private Pattern pattern;
	
	public BasicCodeValidator() {
		this.pattern = Pattern.compile("[0-9]{"+MIN_CODE_LENGTH+","+MAX_CODE_LENGTH+"}");
	}
	
	@Override
	public boolean isCodeValid(String code) {
		return pattern.matcher(code).matches();
	}

}
