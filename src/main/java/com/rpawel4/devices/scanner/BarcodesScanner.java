package com.rpawel4.devices.scanner;

public class BarcodesScanner implements CodesScanner {

	@Override
	public String scanCode(String barcode) {
		return barcode;
	}

}
