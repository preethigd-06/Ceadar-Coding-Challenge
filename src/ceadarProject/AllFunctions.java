package ceadarProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import boPackage.BOClass;
import commonUtils.CommonUtils;
import constants.Constants;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


public class AllFunctions {
	public BOClass getTableData(BOClass boClass) {
		List<String> header = new ArrayList<String>();
		HashMap<String, String> hashMap = null;
		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String,String>>();
		try {
			boClass.setStatus(true);
			File file = new File(Constants.filePath);  
			FileInputStream fis = new FileInputStream(file);  
			@SuppressWarnings("resource")
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(0);  
			Iterator<Row> itr = sheet.iterator();
			if(itr.hasNext()) {  
				Row row = itr.next();  
				Iterator<Cell> cellIterator = row.cellIterator();  
				while (cellIterator.hasNext())   
				{
					Cell cell = cellIterator.next();  
					header.add(String.valueOf(cell.getStringCellValue()));
				}  
			}
			while (itr.hasNext())                 
			{
				Row row = itr.next();
				if( row.getLastCellNum() > 0) {
					hashMap = new HashMap<String, String>();
					Iterator<Cell> cellIterator = row.cellIterator();  
					while (cellIterator.hasNext())   
					{
						Cell cell = cellIterator.next();  
						if(CommonUtils.isNotNullOrEmpty(cell.getStringCellValue())) {
							hashMap.put(header.get(cell.getColumnIndex()), String.valueOf(cell.getStringCellValue()));
						}
					}
					if(!hashMap.isEmpty()) {
						dataList.add(hashMap);
					}
				}
			}
			boClass.setHeader(header);
			boClass.setDataList(dataList);
		}catch(Exception e) {
			e.printStackTrace();
			boClass.setStatus(false);
			boClass.setErrMsg(e.getMessage());
		}
		return boClass;
	}
	public BOClass saveTableData(BOClass boClass) {
		String arr[];
		String temp = "";
		JsonParser parser = new JsonParser();
		int rowCount = 0;
		int colCount = 0;
		Row row = null;
		try {
			Path path = Paths.get(Constants.filePath);
			Files.deleteIfExists(path);
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet("Java Books");
	        FileOutputStream outputStream = new FileOutputStream(Constants.filePath);
			JsonElement dataElement = parser.parse(boClass.getSaveData());
			JsonArray dataArr = dataElement.getAsJsonArray();
			temp = boClass.getStrHeader();
			arr = temp.substring(1, temp.length()-2).replaceAll("\"", "").split(",");
			row = sheet.createRow(rowCount++);
			for(String t:arr) {
				Cell cell = row.createCell(colCount++);
				cell.setCellValue(t);
			}
			for(JsonElement e : dataArr) {
				row = sheet.createRow(rowCount++);
				colCount = 0;
				JsonArray dataArray = e.getAsJsonArray();
				for (int j=0;j<dataArray.size();j++) {
					Cell cell = row.createCell(colCount++);
					cell.setCellValue(org.jsoup.parser.Parser.unescapeEntities(dataArray.get(j).toString().substring(1,dataArray.get(j).toString().length()-1),true).replace("\\\"", "\""));
				}
			}
			 workbook.write(outputStream);
			 boClass.setStatus(true);
		}catch(Exception e) {
			e.printStackTrace();
			boClass.setStatus(false);
			boClass.setErrMsg(e.getMessage());
		}
		return boClass;
	}
}
