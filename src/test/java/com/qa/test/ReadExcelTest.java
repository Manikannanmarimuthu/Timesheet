package com.qa.test;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.testng.annotations.Test;

import com.qa.readexcel.ExcelToDb;
import com.qa.readexcel.JdbcSQLServerConnection;
import com.qa.readexcel.ReadExcelPojo;

public class ReadExcelTest extends JdbcSQLServerConnection {

	@Test
	public void readExcel() throws SQLException, IOException {
		String insertSql = "INSERT INTO TimeSheet(EmpName,DT,Project,SubCategory,TimeinHours)values (?,?,?,?,?)";
		conn = getConnection();
		logger.info("Database Connection Created");
		PreparedStatement ps = conn.prepareStatement(insertSql);
		List<ReadExcelPojo> dataList = ExcelToDb.read();
		logger.info("DB Insert is  inprogress");
		for (ReadExcelPojo readExcelPojo : dataList) {
			ps.setString(1, readExcelPojo.getEmpName());
			ps.setString(2, readExcelPojo.getDate());
			ps.setString(3, readExcelPojo.getProject());
			ps.setString(4, readExcelPojo.getSubCategory());
			ps.setDouble(5, readExcelPojo.getTimeInHours());
			ps.execute();
		}
		logger.info("Completed !");
	}

}