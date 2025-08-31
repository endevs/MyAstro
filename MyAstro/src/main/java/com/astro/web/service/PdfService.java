package com.astro.web.service;

import com.astro.web.model.Chart;
import com.astrology.MultipDivCharts.CompleteChart;
import com.astrology.RuleEngine.RuleResult;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class PdfService {

    public byte[] generatePdf(Chart chart, Map<String, List<RuleResult>> results, String chartImagePath) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Define colors
            Color headerColor = new Color(70, 130, 180); // Steel Blue
            Color borderColor = new Color(0, 0, 128); // Navy
            Color textColor = Color.DARK_GRAY;
            Color ruleHeaderColor = new Color(240, 248, 255); // Alice Blue

            // Draw border
            float margin = 30;
            float width = page.getMediaBox().getWidth() - 2 * margin;
            float height = page.getMediaBox().getHeight() - 2 * margin;
            contentStream.setStrokingColor(borderColor);
            contentStream.setLineWidth(1.5f);
            contentStream.addRect(margin, margin, width, height);
            contentStream.stroke();

            // Header
            contentStream.setNonStrokingColor(headerColor);
            contentStream.addRect(margin, page.getMediaBox().getHeight() - margin - 80, width, 80);
            contentStream.fill();

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 28);
            contentStream.setNonStrokingColor(Color.WHITE);
            contentStream.newLineAtOffset(margin + 20, page.getMediaBox().getHeight() - margin - 45);
            contentStream.showText("Natal Chart Report");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 16);
            contentStream.newLineAtOffset(margin + 20, page.getMediaBox().getHeight() - margin - 70);
            contentStream.showText("For: " + chart.getName());
            contentStream.endText();

            // Chart Image
            PDImageXObject pdImage = PDImageXObject.createFromFile(chartImagePath, document);
            float imageWidth = width - 40; // a little padding
            float scale = imageWidth / pdImage.getWidth();
            float imageHeight = pdImage.getHeight() * scale;
            float imageX = margin + 20;
            float imageY = page.getMediaBox().getHeight() - margin - 80 - imageHeight - 20;
            contentStream.drawImage(pdImage, imageX, imageY, imageWidth, imageHeight);

            // Rule Results
            float yPosition = imageY - 30;

            for (Map.Entry<String, List<RuleResult>> entry : results.entrySet()) {
                yPosition -= 20;

                // Check for new page
                if (yPosition < margin + 40) {
                    contentStream.close();
                    page = new PDPage();
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = page.getMediaBox().getHeight() - margin - 20;
                }

                // Category Header
                contentStream.setNonStrokingColor(ruleHeaderColor);
                contentStream.addRect(margin, yPosition - 15, width, 20);
                contentStream.fill();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                contentStream.setNonStrokingColor(textColor);
                contentStream.newLineAtOffset(margin + 10, yPosition);
                contentStream.showText(entry.getKey().toUpperCase());
                contentStream.endText();
                yPosition -= 25;

                for (RuleResult result : entry.getValue()) {
                    String[] descriptionLines = result.getDescription().split("\\n");
                    for (String line : descriptionLines) {
                        String text = String.format("- %s", line);

                        if (yPosition < margin + 20) {
                            contentStream.close();
                            page = new PDPage();
                            document.addPage(page);
                            contentStream = new PDPageContentStream(document, page);
                            yPosition = page.getMediaBox().getHeight() - margin - 20;
                        }

                        yPosition = writeWrappedText(contentStream, text, PDType1Font.HELVETICA, 10, margin + 10, yPosition, width - 20);
                    }
                    yPosition -= 10;
                }
            }
            contentStream.close();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            return baos.toByteArray();
        }
    }

    private float writeWrappedText(PDPageContentStream contentStream, String text, PDType1Font font, float fontSize, float x, float y, float maxWidth) throws IOException {
        float yPosition = y;
        List<String> lines = new java.util.ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            float wordWidth = font.getStringWidth(word) / 1000 * fontSize;
            float lineWidth = font.getStringWidth(line.toString()) / 1000 * fontSize;

            if (lineWidth + wordWidth > maxWidth) {
                lines.add(line.toString());
                line = new StringBuilder(word + " ");
            } else {
                line.append(word + " ");
            }
        }
        lines.add(line.toString());

        for (String l : lines) {
            contentStream.beginText();
            contentStream.setFont(font, fontSize);
            contentStream.setNonStrokingColor(Color.DARK_GRAY);
            contentStream.newLineAtOffset(x, yPosition);
            contentStream.showText(l);
            contentStream.endText();
            yPosition -= 15;
        }
        return yPosition;
    }
}
