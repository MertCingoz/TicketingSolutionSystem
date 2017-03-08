package bwt.tools.barcodegenerator;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Random;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

public class BWTBarcodeGenerator {
	private static BWTBarcodeGenerator instance = null;

	protected BWTBarcodeGenerator() {
	}

	public static BWTBarcodeGenerator getInstance() {
		if (instance == null) {
			instance = new BWTBarcodeGenerator();
		}
		return instance;
	}

	public String createBarcode(String eventId) {
		StringBuilder barcodeString = new StringBuilder();
		Random randomGenerator = new Random();
		barcodeString.append(eventId);
		String randomFiveDigit = String.valueOf(randomGenerator.nextInt(100000));
		barcodeString.append(randomFiveDigit.toString());
		for (int i = randomFiveDigit.length(); i < 5; i++) {
			barcodeString.append(String.valueOf(randomGenerator.nextInt(10)));
		}
		randomFiveDigit = String.valueOf(randomGenerator.nextInt(100000));
		barcodeString.append(randomFiveDigit.toString());
		for (int i = randomFiveDigit.length(); i < 5; i++) {
			barcodeString.append(String.valueOf(randomGenerator.nextInt(10)));
		}
		return barcodeString.toString();
	}

	public byte[] getCode128BarcodePng(String barcode) {
		DefaultConfigurationBuilder builder = new DefaultConfigurationBuilder();
		// byte[] arr = null;
		// OutputStream out = null;
		ByteArrayOutputStream bao = null;
		try {
			Configuration cfg = builder.buildFromFile(new File("C:\\barcode-config.xml"));
			BarcodeGenerator gen = BarcodeUtil.getInstance().createBarcodeGenerator(cfg);
			// out = new java.io.FileOutputStream(new
			// File("C:\\Temp\\".concat(barcode.concat(".png"))));
			bao = new ByteArrayOutputStream();
			BitmapCanvasProvider provider = new BitmapCanvasProvider(bao, "image/x-png", 90,
					BufferedImage.TYPE_BYTE_GRAY, false, 0);
			gen.generateBarcode(provider, barcode);
			provider.finish();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return bao.toByteArray();

	}

}
