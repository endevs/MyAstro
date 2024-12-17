package com.astrology.pdfRead;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class GetWordsFromPDF extends PDFTextStripper{
	
	static List<String> words = new ArrayList<String>(); 
	
	public GetWordsFromPDF() throws IOException {
		super();
	}

	public static void main(String[] args) throws IOException {
		PDDocument document = null; 
		String fileName = "C:\\\\DHARMARAJ\\\\RAJ\\\\ASTRO\\\\Program INPUT Chats\\\\DharmaRaj.jhd.pdf"; 
		try {
            document = PDDocument.load( new File(fileName) );
            PDFTextStripper stripper = new GetWordsFromPDF();
            stripper.setSortByPosition( true );
            stripper.setStartPage( 0 );
            stripper.setEndPage( document.getNumberOfPages() );
            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
            stripper.writeText(document, dummy);
            
            // print words
            for(String word:words){
                System.out.println(word); 
            }
        }
        finally {
            if( document != null ) {
                document.close();
            }
        } 
	}
	
	protected void writeString(String str, List<TextPosition> textPositions) throws IOException {
        String[] wordsInStream = str.split(getWordSeparator());
        if(wordsInStream!=null){
            for(String word :wordsInStream){
                words.add(word);
            }
        }
    } 
	
}
