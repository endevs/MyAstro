package com.astrology.pdfRead;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfReadMain {

	public static void main(String[] args) {
		 PDDocument doc;
		try {
			doc = PDDocument.load(new File("C:\\DHARMARAJ\\RAJ\\ASTRO\\Program INPUT Chats\\DharmaRaj.jhd.pdf"));
			 String text = new PDFTextStripper().getText(doc);;
	         System.out.println("Text in PDF\n---------------------------------");
	         System.out.println(text); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
