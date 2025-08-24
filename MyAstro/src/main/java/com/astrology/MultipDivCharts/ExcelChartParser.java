package com.astrology.MultipDivCharts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelChartParser {

    public static List<ChartData> parse(String filePath) throws IOException {
        List<ChartData> chartDataList = new ArrayList<>();
        try (FileInputStream excelFile = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(excelFile)) {

            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            // Skip header row
            if (iterator.hasNext()) {
                iterator.next();
            }

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                DataFormatter formatter = new DataFormatter();

                String birthTime = formatter.formatCellValue(currentRow.getCell(0));
                String birthPlace = formatter.formatCellValue(currentRow.getCell(1));
                String dob = formatter.formatCellValue(currentRow.getCell(2));
                String name = formatter.formatCellValue(currentRow.getCell(3));

                String[] housePositions = new String[12];
                for (int i = 0; i < 12; i++) {
                    housePositions[i] = formatter.formatCellValue(currentRow.getCell(i + 4));
                }

                chartDataList.add(new ChartData(name, dob, birthTime, birthPlace, housePositions));
            }
        }
        return chartDataList;
    }
}
