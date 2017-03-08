package bwt.tools.ticketgenerator;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.ByteBuffer;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPrintPage;

import bwt.tools.barcodegenerator.BWTBarcodeGenerator;

public class BWTTicketGenerator {
	private static BWTTicketGenerator instance = null;

	protected BWTTicketGenerator() {
	}

	public static BWTTicketGenerator getInstance() {
		if (instance == null) {
			instance = new BWTTicketGenerator();
		}
		return instance;
	}

	public static void createTicket(String barcodeNumber, String ticketElements)
			throws MalformedURLException, IOException, InterruptedException {
		BWTBarcodeGenerator generator = BWTBarcodeGenerator.getInstance();

		try {

			Document document = new Document();
			FileOutputStream fileOutputStream = new FileOutputStream("C:\\sana\\cx.pdf");
			PdfWriter pw = PdfWriter.getInstance(document, fileOutputStream);
			Rectangle one = new Rectangle(914, 283);
			document.setPageSize(one);
			document.open();
			// String barcode = generator.createBarcode(barcodeNumber);
			byte[] code128BarcodePng = generator.getCode128BarcodePng(barcodeNumber);
			Image image1 = Image.getInstance(code128BarcodePng);
			image1.setRotationDegrees(90);

			image1.setAbsolutePosition(2, 5);

			// Image image2 = Image.getInstance(code128BarcodePng);
			// image2.setAbsolutePosition(810, 30);
			// image2.setRotationDegrees(90);

			// document.add(image2);

			document.add(image1);

			PdfContentByte cb = pw.getDirectContent();
			PdfPTable invoiceMetaDataTable0 = new PdfPTable(1);

			String[] elementList = ticketElements.split("\\@");
			for (int i = 0; i < elementList.length; i++) {
				invoiceMetaDataTable0 = new PdfPTable(1);
				String[] elementDetail = elementList[i].split("-");
				invoiceMetaDataTable0.setTotalWidth(Float.parseFloat(elementDetail[0]));
				invoiceMetaDataTable0.getDefaultCell().setBorderWidth(0);
				invoiceMetaDataTable0.addCell(elementDetail[3]);
				invoiceMetaDataTable0.writeSelectedRows(0, 1, Float.parseFloat(elementDetail[1]),
						283 - Float.parseFloat(elementDetail[2]), cb);
			}
			// width-x-y-etkinlik adı

			document.close();
			fileOutputStream.close();

			FileInputStream fileInputStream = new FileInputStream("C:\\sana\\cx.pdf");
			byte[] pdfContent = new byte[fileInputStream.available()];
			fileInputStream.read(pdfContent, 0, fileInputStream.available());
			ByteBuffer buffer = ByteBuffer.wrap(pdfContent);

			final PDFFile pdfFile = new PDFFile(buffer);
			PDFPrintPage pages = new PDFPrintPage(pdfFile);
			PrinterJob printJob = PrinterJob.getPrinterJob();
			PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
			printJob.setJobName("1");
			Book book = new Book();

			book.append(pages, pageFormat, pdfFile.getNumPages());
			printJob.setPageable(book);
			Paper paper = new Paper();

			// paper has a default size, but i changed the size and
			// ImageableArea
			// for my specific needs
			paper.setSize(213, 679);
			paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());

			pageFormat.setPaper(paper);

			// using default printService here
			PrintService p = PrintServiceLookup.lookupDefaultPrintService();
			try {
				printJob.setPrintService(p);
				PrintRequestAttributeSet attr_set = new HashPrintRequestAttributeSet();
				// attr_set.add(MediaSizeName.ISO_A6);
				// attr_set.add(OrientationRequested.PORTRAIT);
				printJob.print(attr_set);
				fileInputStream.close();

			} catch (PrinterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
