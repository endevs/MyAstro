package com.astrology.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.astrology.rules.Rule1;
import com.astrology.rules.Rule2;
import com.astrology.rules.Rule3;
import com.astrology.rules.Rule4;
import com.astrology.rules.Rule5;
import com.astrology.rules.Rule6;
import com.astrology.utils.Utils;
import com.astrology.vo.HousePlanetDetailsForPredictionVO;
import com.astrology.vo.NatalChart;
import com.astrology.vo.Planet;
import com.astrology.vo.Sign;


//BackUp As On - 15th June 2019, 8th March 2020

public class Main {
	
	static LinkedHashMap<Integer,Sign> signs = new LinkedHashMap<Integer,Sign>();
	static LinkedHashMap<String,Planet> planets = new LinkedHashMap<String,Planet>();
	static LinkedHashMap<String, NatalChart> natalChartS;
	static LinkedHashMap<Integer, HousePlanetDetailsForPredictionVO> housePlanetDetailsForPredictionList = new LinkedHashMap<Integer, HousePlanetDetailsForPredictionVO>();
	
	static LinkedHashMap<String, LinkedHashMap<String, NatalChart>> MasterNnatalChartS;//Name as Key and Chart
	
	public static void main(String[] args) {
		MasterNnatalChartS = new LinkedHashMap<String, LinkedHashMap<String, NatalChart>>();
		MasterNnatalChartS = collectNatalChartInputFromExcel();
		for(Map.Entry<String,LinkedHashMap<String, NatalChart>> masterLlistOfNatalChartS:MasterNnatalChartS.entrySet()){
			System.out.println("Chart Name = "+masterLlistOfNatalChartS.getKey());
			Utils.horoscopeRetrive(masterLlistOfNatalChartS.getValue());
			horoscopePrediction(masterLlistOfNatalChartS.getValue());
		}
	}//End of Main Method
	
	private static void horoscopePrediction(LinkedHashMap<String, NatalChart> natalChartS2) {
		//Rule 1 If a house lord placed in dostana like 6,8,12 from ASD then means house is lost
		StringBuffer sb = new StringBuffer();
		
		Rule1 rule1 = new Rule1();
		sb.append(Utils.printPredictionResult(rule1.execute(natalChartS2.get("D1")),"Rule-1"));
		
		Rule2 rule2 = new Rule2();
		sb.append(Utils.printPredictionResult(rule2.execute(natalChartS2.get("D1")),"Rule-2"));
		
		Rule3 rule3 = new Rule3();
		sb.append(Utils.printPredictionResult(rule3.execute(natalChartS2.get("D1")),"Rule-3"));
		
		Rule4 rule4 = new Rule4();
		sb.append(Utils.printPredictionResult(rule4.execute(natalChartS2.get("D1")),"Rule-4"));
		
		Rule5 rule5 = new Rule5();
		sb.append(Utils.printPredictionResult(rule5.execute(natalChartS2),"Rule-5"));
		
		Rule6 rule6 = new Rule6();
		sb.append(Utils.printPredictionResult(rule6.execute(natalChartS2),"Rule-6"));
		sb.append(Utils.printPredictionResult(rule6.execute_1(natalChartS2),"Rule-6_1"));
		sb.append(Utils.printPredictionResult(rule6.execute_2(natalChartS2),"Rule-6_2"));
		sb.append(Utils.printPredictionResult(rule6.execute_3(natalChartS2),"Rule-6_3"));
		sb.append(Utils.printPredictionResult(rule6.execute_4(natalChartS2),"Rule-6_4"));
		sb.append(Utils.printPredictionResult(rule6.execute_5(natalChartS2),"Rule-6_5"));
		sb.append(Utils.printPredictionResult(rule6.execute_6(natalChartS2),"Rule-6_6"));
		sb.append(Utils.printPredictionResult(rule6.execute_7(natalChartS2),"Rule-6_7"));
		
		//return rule1.execute(natalChart2);
		Utils.writeToFile("C:\\DHARMARAJ\\RAJ\\ASTRO\\Prediction File\\"+natalChartS2.get("D1").getNatalChartName()+".txt",sb);
	}
	private static final String FILE_NAME = "C:\\\\DHARMARAJ\\\\SOFTWARE\\\\My-eclipse-workspace\\\\Astro\\\\resources\\\\NatalChatInput\\\\Chat_InPut.xlsx";
	
	public static String dataFormatterCellValue(Cell cell){
		String var_name = null;
		DataFormatter formatter = new DataFormatter();
		var_name =  formatter.formatCellValue(cell);
		if(Utils.isNullOrEmpty(var_name)) {
			var_name = "-";
		}
		return var_name;
	}
	
	public static LinkedHashMap<String, LinkedHashMap<String, NatalChart>> collectNatalChartInputFromExcel() {
		LinkedHashMap<String, LinkedHashMap<String, NatalChart>>  localMasterNnatalChartS = new LinkedHashMap<String, LinkedHashMap<String, NatalChart>>();
		LinkedHashMap<String,NatalChart> natalCharts = null;
		String divisionSymbol = null;
		String natalChartName = null;
		Integer ascendant = null;
		
		String divisionSymbolD9 = null;
		Integer ascendantD9 = null;
		
		
		//SUN,MOON,MARS,MERCURY,JUPITER,VENUS,SATURN,RAHU,KETU
		LinkedHashMap<Integer, String[]> myPlanetsAtHouse = null;
		LinkedHashMap<Integer, String[]> myPlanetsAtHouseD9 = null;
		try {
			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
			@SuppressWarnings("resource")
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				
				if(currentRow.getRowNum() > 1) {
					//This variable include D1 and D9
					natalCharts = new LinkedHashMap<String,NatalChart>();
					//Chart Name
					natalChartName = dataFormatterCellValue(currentRow.getCell(0));
					
					//D1 Chart
					ascendant = Integer.parseInt(dataFormatterCellValue(currentRow.getCell(1)));
					divisionSymbol = dataFormatterCellValue(currentRow.getCell(2));
					
					myPlanetsAtHouse = new LinkedHashMap<Integer, String[]>();
					
					myPlanetsAtHouse.put(1, dataFormatterCellValue(currentRow.getCell(3)).split(","));
					myPlanetsAtHouse.put(2, dataFormatterCellValue(currentRow.getCell(4)).split(","));
					myPlanetsAtHouse.put(3, dataFormatterCellValue(currentRow.getCell(5)).split(","));
					myPlanetsAtHouse.put(4, dataFormatterCellValue(currentRow.getCell(6)).split(","));
					myPlanetsAtHouse.put(5, dataFormatterCellValue(currentRow.getCell(7)).split(","));
					myPlanetsAtHouse.put(6, dataFormatterCellValue(currentRow.getCell(8)).split(","));
					myPlanetsAtHouse.put(7, dataFormatterCellValue(currentRow.getCell(9)).split(","));
					myPlanetsAtHouse.put(8, dataFormatterCellValue(currentRow.getCell(10)).split(","));
					myPlanetsAtHouse.put(9, dataFormatterCellValue(currentRow.getCell(11)).split(","));
					myPlanetsAtHouse.put(10, dataFormatterCellValue(currentRow.getCell(12)).split(","));
					myPlanetsAtHouse.put(11, dataFormatterCellValue(currentRow.getCell(13)).split(","));
					myPlanetsAtHouse.put(12, dataFormatterCellValue(currentRow.getCell(14)).split(","));
					
					//D9 Chart
					divisionSymbolD9 = dataFormatterCellValue(currentRow.getCell(15));
					ascendantD9 = Integer.parseInt(dataFormatterCellValue(currentRow.getCell(16)));
					
					myPlanetsAtHouseD9 = new LinkedHashMap<Integer, String[]>();
					
					myPlanetsAtHouseD9.put(1, dataFormatterCellValue(currentRow.getCell(17)).split(","));
					myPlanetsAtHouseD9.put(2, dataFormatterCellValue(currentRow.getCell(18)).split(","));
					myPlanetsAtHouseD9.put(3, dataFormatterCellValue(currentRow.getCell(19)).split(","));
					myPlanetsAtHouseD9.put(4, dataFormatterCellValue(currentRow.getCell(20)).split(","));
					myPlanetsAtHouseD9.put(5, dataFormatterCellValue(currentRow.getCell(21)).split(","));
					myPlanetsAtHouseD9.put(6, dataFormatterCellValue(currentRow.getCell(22)).split(","));
					myPlanetsAtHouseD9.put(7, dataFormatterCellValue(currentRow.getCell(23)).split(","));
					myPlanetsAtHouseD9.put(8, dataFormatterCellValue(currentRow.getCell(24)).split(","));
					myPlanetsAtHouseD9.put(9, dataFormatterCellValue(currentRow.getCell(25)).split(","));
					myPlanetsAtHouseD9.put(10, dataFormatterCellValue(currentRow.getCell(26)).split(","));
					myPlanetsAtHouseD9.put(11, dataFormatterCellValue(currentRow.getCell(27)).split(","));
					myPlanetsAtHouseD9.put(12, dataFormatterCellValue(currentRow.getCell(28)).split(","));
					
					
					natalCharts.put(divisionSymbol, Utils.prepareNatalChartData(natalChartName,ascendant,myPlanetsAtHouse,divisionSymbol));
					natalCharts.put(divisionSymbolD9, Utils.prepareNatalChartData(natalChartName,ascendantD9,myPlanetsAtHouseD9,divisionSymbolD9));
					localMasterNnatalChartS.put(natalChartName, natalCharts);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return localMasterNnatalChartS;
	}
	
	public static LinkedHashMap<String,NatalChart> collectNatalChartInput() {
		
		LinkedHashMap<String,NatalChart> natalCharts = new LinkedHashMap<String,NatalChart>();
		//===========================================================================================DharmaRaj Chart=====================Start
		/*String natalChartName = "DharmaRaj";
		Integer ascendant = 11;
		String divisionSymbol = "D1";
		//SUN,MOON,MARS,MERCURY,JUPITER,VENUS,SATURN,RAHU,KETU
		LinkedHashMap<Integer, String[]> myPlanetsAtHouse = new LinkedHashMap<Integer, String[]>();
		myPlanetsAtHouse.put(1, new String[]{"-"});
		myPlanetsAtHouse.put(2, new String[]{"SUN","MERCURY"});
		myPlanetsAtHouse.put(3, new String[]{"MARS","VENUS"});
		myPlanetsAtHouse.put(4, new String[]{"-"});
		myPlanetsAtHouse.put(5, new String[]{"RAHU"});
		myPlanetsAtHouse.put(6, new String[]{"-"});
		myPlanetsAtHouse.put(7, new String[]{"-"});
		myPlanetsAtHouse.put(8, new String[]{"-"});
		myPlanetsAtHouse.put(9, new String[]{"SATURN"});
		myPlanetsAtHouse.put(10, new String[]{"JUPITER"});
		myPlanetsAtHouse.put(11, new String[]{"MOON,KETU"});
		myPlanetsAtHouse.put(12, new String[]{"-"});
		
		Integer ascendantD9 = 3;//3
		String divisionSymbolD9 = "D9";
		//SUN,MOON,MARS,MERCURY,JUPITER,VENUS,SATURN,RAHU,KETU
		LinkedHashMap<Integer, String[]> myPlanetsAtHouseD9 = new LinkedHashMap<Integer, String[]>();
		myPlanetsAtHouseD9.put(1, new String[]{"-"});
		myPlanetsAtHouseD9.put(2, new String[]{"-"});
		myPlanetsAtHouseD9.put(3, new String[]{"-"});
		myPlanetsAtHouseD9.put(4, new String[]{"MOON"});
		myPlanetsAtHouseD9.put(5, new String[]{"-"});
		myPlanetsAtHouseD9.put(6, new String[]{"RAHU","VENUS"});
		myPlanetsAtHouseD9.put(7, new String[]{"SATURN","JUPITER"});
		myPlanetsAtHouseD9.put(8, new String[]{"SUN"});
		myPlanetsAtHouseD9.put(9, new String[]{"-"});
		myPlanetsAtHouseD9.put(10, new String[]{"-"});
		myPlanetsAtHouseD9.put(11, new String[]{"MERCURY"});
		myPlanetsAtHouseD9.put(12, new String[]{"KETU","MARS"});*/
		
		//===========================================================================================DharmaRaj Chart=====================End
		
		
		//===========================================================================================Dally Chart=====================Start
		/*String natalChartName = "Dally";
		Integer ascendant = 11;
		String divisionSymbol = "D1";
		//SUN,MOON,MARS,MERCURY,JUPITER,VENUS,SATURN,RAHU,KETU
		LinkedHashMap<Integer, String[]> myPlanetsAtHouse = new LinkedHashMap<Integer, String[]>();
		myPlanetsAtHouse.put(1, new String[]{"JUPITER"});
		myPlanetsAtHouse.put(2, new String[]{"-"});
		myPlanetsAtHouse.put(3, new String[]{"RAHU"});
		myPlanetsAtHouse.put(4, new String[]{"MOON","SUN"});
		myPlanetsAtHouse.put(5, new String[]{"MERCURY","VENUS"});
		myPlanetsAtHouse.put(6, new String[]{"-"});
		myPlanetsAtHouse.put(7, new String[]{"-"});
		myPlanetsAtHouse.put(8, new String[]{"-"});
		myPlanetsAtHouse.put(9, new String[]{"KETU"});
		myPlanetsAtHouse.put(10, new String[]{"SATURN"});
		myPlanetsAtHouse.put(11, new String[]{"MARS"});
		myPlanetsAtHouse.put(12, new String[]{"-"});
		
		Integer ascendantD9 = 10;
		String divisionSymbolD9 = "D9";
		//SUN,MOON,MARS,MERCURY,JUPITER,VENUS,SATURN,RAHU,KETU
		LinkedHashMap<Integer, String[]> myPlanetsAtHouseD9 = new LinkedHashMap<Integer, String[]>();
		myPlanetsAtHouseD9.put(1, new String[]{"-"});
		myPlanetsAtHouseD9.put(2, new String[]{"-"});
		myPlanetsAtHouseD9.put(3, new String[]{"-",});
		myPlanetsAtHouseD9.put(4, new String[]{"MOON"});
		myPlanetsAtHouseD9.put(5, new String[]{"VENUS","RAHU"});
		myPlanetsAtHouseD9.put(6, new String[]{"JUPITER"});
		myPlanetsAtHouseD9.put(7, new String[]{"SUN"});
		myPlanetsAtHouseD9.put(8, new String[]{"-"});
		myPlanetsAtHouseD9.put(9, new String[]{"-"});
		myPlanetsAtHouseD9.put(10, new String[]{"SATURN"});
		myPlanetsAtHouseD9.put(11, new String[]{"KETU"});
		myPlanetsAtHouseD9.put(12, new String[]{"MERCURY","MARS"});*/
		//===========================================================================================Dally Chart=====================End
		
		
		//===========================================================================================Dally Chart=====================Start
				String natalChartName = "Dibya";
				Integer ascendant = 7;
				String divisionSymbol = "D1";
				//SUN,MOON,MARS,MERCURY,JUPITER,VENUS,SATURN,RAHU,KETU
				String[] a = {"MOON","MERCURY","VENUS","MARS"};
				LinkedHashMap<Integer, String[]> myPlanetsAtHouse = new LinkedHashMap<Integer, String[]>();
				myPlanetsAtHouse.put(1, new String[]{"-"});
				myPlanetsAtHouse.put(2, new String[]{"-"});
				myPlanetsAtHouse.put(3, new String[]{"-"});
				myPlanetsAtHouse.put(4, new String[]{"-"});
				myPlanetsAtHouse.put(5, new String[]{"KETU"});
				//myPlanetsAtHouse.put(6, new String[]{"MOON","MERCURY","VENUS","MARS"});
				myPlanetsAtHouse.put(6, a);
				myPlanetsAtHouse.put(7, new String[]{"SUN"});
				myPlanetsAtHouse.put(8, new String[]{"-"});
				myPlanetsAtHouse.put(9, new String[]{"-"});
				myPlanetsAtHouse.put(10, new String[]{"JUPITER"});
				myPlanetsAtHouse.put(11, new String[]{"SATURN","RAHU"});
				myPlanetsAtHouse.put(12, new String[]{"-"});
				
				Integer ascendantD9 = 9;
				String divisionSymbolD9 = "D9";
				//SUN,MOON,MARS,MERCURY,JUPITER,VENUS,SATURN,RAHU,KETU
				LinkedHashMap<Integer, String[]> myPlanetsAtHouseD9 = new LinkedHashMap<Integer, String[]>();
				myPlanetsAtHouseD9.put(1, new String[]{"-"});
				myPlanetsAtHouseD9.put(2, new String[]{"MARS"});
				myPlanetsAtHouseD9.put(3, new String[]{"-"});
				myPlanetsAtHouseD9.put(4, new String[]{"MOON"});
				myPlanetsAtHouseD9.put(5, new String[]{"KETU"});
				myPlanetsAtHouseD9.put(6, new String[]{"-"});
				myPlanetsAtHouseD9.put(7, new String[]{"-"});
				myPlanetsAtHouseD9.put(8, new String[]{"SUN"});
				myPlanetsAtHouseD9.put(9, new String[]{"SATURN"});
				myPlanetsAtHouseD9.put(10, new String[]{"VENUS","JUPITER"});
				myPlanetsAtHouseD9.put(11, new String[]{"RAHU"});
				myPlanetsAtHouseD9.put(12, new String[]{"MERCURY"});
				//===========================================================================================Dally Chart=====================End
		
		
		
		natalCharts.put(divisionSymbol, Utils.prepareNatalChartData(natalChartName,ascendant,myPlanetsAtHouse,divisionSymbol));
		natalCharts.put(divisionSymbolD9, Utils.prepareNatalChartData(natalChartName,ascendantD9,myPlanetsAtHouseD9,divisionSymbolD9));
		
		return natalCharts;
	}
	
	/*if(currentRow.getRowNum() > 0) {//Skip Fast Row
	Iterator<Cell> cellIterator = currentRow.iterator();
	//cellIterator.next().getStringCellValue();
	while (cellIterator.hasNext()) {
		Cell currentCell = cellIterator.next();
		System.out.println("getColumnIndex "+currentCell.getColumnIndex());
		if(currentCell.getColumnIndex() == 0) {
			//Name
			if (currentCell.getCellType() == CellType.STRING) {
                System.out.println(currentCell.getStringCellValue() + "-#-");
                localMasterNnatalChartS.put(currentCell.getStringCellValue(), natalCharts);
            }
		}
		if(currentCell.getColumnIndex() == 1) {
			//ASD
			if (currentCell.getCellType() == CellType.NUMERIC) {
				String SONum = String.valueOf((int)currentCell.getNumericCellValue());
                System.out.println( SONum + "-$-");
            }
		}
		
	}
}//End of If Condition
*/				
	//Read from Excel Sheet

}