package com.qa.readexcel;

public class ReadExcelPojo {

	private String date;
	private String project;
	private String SubCategory;
	private double timeInHours;
	private String empName;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getSubCategory() {
		return SubCategory;
	}
	public void setSubCategory(String subCategory) {
		SubCategory = subCategory;
	}
	public double getTimeInHours() {
		return timeInHours;
	}
	public void setTimeInHours(double timeInHours) {
		this.timeInHours = timeInHours;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	@Override
	public String toString() {
		return "ReadExcelPojo [date=" + date + ", project=" + project + ", SubCategory=" + SubCategory
				+ ", timeInHours=" + timeInHours + ", empName=" + empName + "]";
	}
	
}
