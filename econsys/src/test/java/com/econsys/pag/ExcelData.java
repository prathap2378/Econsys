package com.econsys.pag;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {

	public static void main(String[] args) throws IOException {
		writeToSheet(0,0,0);
		readFromsheet(0,0,0);
	}
	static void readFromsheet(int sheetNumber,int rowNumber,int cellNumber) throws IOException{

		String path = System.getProperty("user.dir");
		System.out.println("path---"+path);
		FileInputStream file = new FileInputStream(path+"/Documentsuploded/programme.xlsx");

		XSSFWorkbook xs = new XSSFWorkbook(file);
		XSSFSheet sheetx = xs.getSheetAt(sheetNumber);
		XSSFRow rowx = sheetx.getRow(rowNumber);
		XSSFCell cellx = rowx.getCell(cellNumber);
		cellx.setCellType(Cell.CELL_TYPE_STRING);
		String valuex = cellx.getStringCellValue();
		System.out.println("valuex---"+valuex);
		
		/*HSSFWorkbook hf = new HSSFWorkbook(file);
		HSSFSheet sheet = hf.getSheetAt(sheetNumber);
		HSSFRow row = sheet.getRow(rowNumber);
		HSSFCell cell = row.getCell(cellNumber);
		HSSFRichTextString value = cell.getRichStringCellValue();
		System.out.println("value ---"+value);*/
	}

	static void writeToSheet(int sheetNumber,int rowNumber,int cellNumber) throws IOException{
		
		String path = System.getProperty("user.dir");
		System.out.println("path---"+path);
		FileInputStream file = new FileInputStream(path+"/Documentsuploded/programme.xlsx");

		XSSFWorkbook xs = new XSSFWorkbook(file);
		XSSFSheet sheetx = xs.getSheetAt(sheetNumber);
		XSSFRow rowx = sheetx.getRow(rowNumber);
		XSSFCell cellx = rowx.createCell(cellNumber);
		cellx.setCellValue(12.23);

		FileOutputStream fileout = new FileOutputStream(path+"/Documentsuploded/programme.xlsx");
		xs.write(fileout);
		xs.close();
	}
}
