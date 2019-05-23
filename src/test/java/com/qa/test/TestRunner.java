package com.qa.test;

import org.testng.TestNG;

public class TestRunner {

	public static TestNG testNG;

	public static void main(String[] args) {
		testNG = new TestNG();
		testNG.setTestClasses(new Class[] { ReadExcelTest.class });
		testNG.run();
	}

}
