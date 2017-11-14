package com.econsys.TestData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.support.PageFactory;

import com.econsys.Genriclibrery.*;
import com.econsys.Projects.*;
import com.econsys.UIobjectrepositary.Preparequote;

public class Workbook {

	private static Logger log = Logger.getLogger(Workbook.class.getName());
	public static Preparequote prepare_Quoteui = PageFactory.initElements(Driver.driver(), Preparequote.class);
	String filepath=System.getProperty("user.dir");
	String testdata_ExcelPath=filepath+"//src//main//java//com//econsys//TestData//Monorail_testdata.xls";
	HSSFSheet Firstpage=null;
	public String setcellvalue=null;
	CommonUtils commonUtils = new CommonUtils();

	//Method for Getting last Row number
	public int getrowNum(int sheetName){
		int rownum=0;
		try {

			FileInputStream file = new FileInputStream(testdata_ExcelPath);
			HSSFWorkbook wb = new HSSFWorkbook(file);
			rownum=wb.getSheetAt(sheetName).getLastRowNum();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return rownum;
	}


	public String getXLData(int rownum, int ceelnum,int sheetname) throws IOException{

		//File intilization
		FileInputStream file = new FileInputStream(testdata_ExcelPath);

		//Converting file as work book
		HSSFWorkbook wb = new HSSFWorkbook(file);
		//Getting sheet from work book
		HSSFSheet Sheetnumber=wb.getSheetAt(sheetname);
		//log.info("Sheet name : "+wb.getSheetAt(0));
		String cellvalue = "";
		HSSFRow hssfRow = Sheetnumber.getRow(rownum);

		if(hssfRow!=null){
			HSSFCell cell = hssfRow.getCell(ceelnum);
			if(cell!=null){
				cell.setCellType(cell.CELL_TYPE_STRING);
				cellvalue = cell.getStringCellValue();
			}else{
				System.out.println("Cell null");
				System.out.println("Row Num :"+rownum);
				System.out.println("Cell Num :"+ceelnum);
				System.out.println("Sheet Num :"+sheetname);
			}
		}else{
			System.out.println("Row null");
			System.out.println("Row Num :"+rownum);
			System.out.println("Cell Num :"+ceelnum);
			System.out.println("Sheet Num :"+sheetname);
		}

		return cellvalue;
	}

	/*public double getXLDataint(int rownum, int ceelnum,int sheetname) throws IOException{

		//The configering from the log4j xml file
		  DOMConfigurator.configure("log4j.xml");
		//file path from properties file
	      String testdataXLpath=filepath+"//src//com//testdata//in//Monorail_testdata.xls";
		//File intilization
		  FileInputStream file = new FileInputStream(testdataXLpath);
			if(file.equals(true)){
			log.info("excel file exists: "+file);
			}
			else{
			log.info("excel file does not exists: "+file);
			}
			//Converting file as work book
			HSSFWorkbook wb = new HSSFWorkbook(file);
			//Getting sheet from work book
			HSSFSheet Firstpage=wb.getSheetAt(sheetname);
			log.info("Sheet name : "+wb.getSheetAt(0));
			double cellvalue= Firstpage.getRow(rownum).getCell(ceelnum).getNumericCellValue();
			return cellvalue;
		}*/

	public void setExcelData(int sheetNumber,int rowNum,int colNum,String Data) throws IOException{

		//File intilization*/
		FileInputStream file = new FileInputStream(testdata_ExcelPath);

		//Converting file as work book
		HSSFWorkbook wb = new HSSFWorkbook(file);

		//Getting sheet from work book
		HSSFSheet sheet=wb.getSheetAt(sheetNumber);

		//Get cell from sheet
		Row row =sheet.getRow(rowNum);
		Cell cell=row.createCell(colNum);

		cell.setCellValue(Data);

		//wright to excel
		FileOutputStream outfile = new FileOutputStream(testdata_ExcelPath);

		wb.write(outfile);
		//wb.clone();
		outfile.close();
	}

	/*public void setExcelData(String sheetName,int rowNum,int colNum,String Data) throws Exception{

		FileInputStream fis=new FileInputStream(testdataXLpath);
		workbook=Workbook.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).getCell(colNum).setCellValue(Data);


	}
	 */
	public static void main(String[] args) throws IOException {
		Workbook workbook = new Workbook();
		workbook.getXLData(1, 3,0);
		workbook.setExcelData(1, 1, 2, "Proname");
	}

	//Reading cost and sell values
	public void prepareQuote_BidInfoDetails(){
		try {
			System.out.println("filepath--------"+filepath);
			String testdataXLpath=filepath+"\\src\\main\\java\\com\\econsys\\TestData\\Monorail_testdata.xls";
			FileInputStream file;

			file = new FileInputStream(testdataXLpath);
			HSSFWorkbook wb = new HSSFWorkbook(file);
			int sheetNumber = 2;
			HSSFSheet sheet = wb.getSheetAt(sheetNumber);

			Iterator rowIterator = sheet.rowIterator();	

			while(rowIterator.hasNext()){
				HSSFRow row = (HSSFRow) rowIterator.next();
				int rowNum = row.getRowNum();

				if (rowNum !=0) {
					if(row!=null && (rowNum==0 || rowNum==1 || rowNum==2)){
						String quoteType = null;
						double Cost = 0;
						double sell = 0;

						if(row.getCell(0)!=null){
							quoteType= row.getCell(0).getStringCellValue();
						}
						if(row.getCell(1)!=null){
							Cost= row.getCell(1).getNumericCellValue();
						}
						if(row.getCell(2)!=null){
							sell = row.getCell(2).getNumericCellValue();
						}
						try {
							commonUtils.blindWait();
							prepare_Quoteui.getAddnewpopup().click();
							commonUtils.blindWait();
							prepare_Quoteui.getCostCodeCategorytextfield().sendKeys(quoteType);
							commonUtils.waitForPageToLoad();
							prepare_Quoteui.getCost().sendKeys(""+Cost);
							prepare_Quoteui.getSell().sendKeys(""+sell);
							commonUtils.blindWait();
							prepare_Quoteui.getSaveAddcostsellpopup().click();
							commonUtils.blindWait();
							commonUtils.waitForPageToLoad();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

