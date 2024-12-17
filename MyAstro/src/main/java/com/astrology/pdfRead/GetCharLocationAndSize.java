package com.astrology.pdfRead;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class GetCharLocationAndSize extends PDFTextStripper{

	public GetCharLocationAndSize() throws IOException {
		super();
	}

	public static void main(String[] args) throws IOException {
		PDDocument document = null;
        String fileName = "C:\\\\DHARMARAJ\\\\RAJ\\\\ASTRO\\\\Program INPUT Chats\\\\DharmaRaj.jhd.pdf"; 
        try {
			document = PDDocument.load( new File(fileName) );
            PDFTextStripper stripper = new GetCharLocationAndSize();
            stripper.setSortByPosition( true );
            stripper.setStartPage( 0 );
            stripper.setEndPage( document.getNumberOfPages() );
            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
            stripper.writeText(document, dummy);
            } catch (IOException e) {
				e.printStackTrace();
            }
        	finally {
            if( document != null ) {
                try {
					document.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        	}
	}
	
	@Override
    protected void writeString(String string, List<TextPosition> textPositions) throws IOException {
        for (TextPosition text : textPositions) {
            System.out.println(text.getUnicode()+ " [(X=" + text.getXDirAdj() + ",Y=" +
                    text.getYDirAdj() + ") height=" + text.getHeightDir() + " width=" +
                    text.getWidthDirAdj() + "]");
        }
    } 

}
