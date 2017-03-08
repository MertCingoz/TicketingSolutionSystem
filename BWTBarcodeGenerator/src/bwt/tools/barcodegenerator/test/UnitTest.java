package bwt.tools.barcodegenerator.test;

import bwt.tools.barcodegenerator.BWTBarcodeGenerator;

public class UnitTest {

	public static void main(String[] args) {
		BWTBarcodeGenerator generator = BWTBarcodeGenerator.getInstance();
		for (int i = 0; i < 100; i++) {
			//generator.getCode128BarcodePng(generator.createBarcode("000001"));
			System.out.println(generator.createBarcode("000001"));
		}

	}

}
