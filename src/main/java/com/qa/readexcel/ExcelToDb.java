package com.qa.readexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelToDb {

	public static XSSFRow row;
	public static XSSFCell cell;
	public static int initDateCellNum = 3;
	public static int dtRow = 1;
	public static int dataRow = 2;

	public static Properties prop;

	final static Logger logger = Logger.getLogger(ExcelToDb.class);

	static {
		loadConfig();
	}

	public static List<ReadExcelPojo> read() throws IOException {
		final File filesList = new File("./TimeSheet");
		logger.info("Timesheet File(s) Location :  " + filesList);
		File[] files = filesList.listFiles();
		List<ReadExcelPojo> listofexcelobj = new ArrayList<ReadExcelPojo>();
		for (File file : files) {
			final String fname = file.getName().split("\\.")[0];
			final String name = fname.split(" ")[2].split("-")[0];
			logger.info("Procesing " + name + " TimeSheet" + "");
			FileInputStream myInput = new FileInputStream(file);
			@SuppressWarnings("resource")
			XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);
			XSSFSheet mySheet = myWorkBook.getSheet(prop.getProperty("month"));
			Row currRow = mySheet.getRow(dtRow);
			for (int i = initDateCellNum; i < currRow.getLastCellNum(); i++) {
				Cell cell1 = currRow.getCell(i);
				final String dateStr = new SimpleDateFormat("dd-MMM-yyyy").format(cell1.getDateCellValue());
				for (int j = dataRow; j <= mySheet.getLastRowNum(); j++) {
					final Cell projStr1 = mySheet.getRow(j).getCell(0);
					if (projStr1 == null) {
						continue;
					}
					final String projStr = mySheet.getRow(j).getCell(0).getStringCellValue();
					final String subProjStr = mySheet.getRow(j).getCell(1).getStringCellValue();
					final Cell hourCell = mySheet.getRow(j).getCell(i);
					if (hourCell == null) {
						continue;
					} else {
						hourCell.setCellType(CellType.STRING);
						if (hourCell.getStringCellValue().isEmpty()) {
							continue;
						}
						ReadExcelPojo obj = new ReadExcelPojo();
						obj.setDate(dateStr);
						obj.setProject(projStr);
						obj.setSubCategory(subProjStr);
						obj.setTimeInHours(Double.valueOf(hourCell.getStringCellValue()));
						obj.setEmpName(name);
						listofexcelobj.add(obj);
						logger.info(obj);
					}
				}
			}
			logger.info("Going to Insert in to Databse");
		}
		return listofexcelobj;

	}

	public static void loadConfig() {
		try (FileInputStream input = new FileInputStream("./config.properties")) {
			prop = new Properties();
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}