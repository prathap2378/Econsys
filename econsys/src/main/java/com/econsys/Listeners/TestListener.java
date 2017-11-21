package com.econsys.Listeners;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import POC.econsys.Listener;

import com.econsys.Genriclibrery.CommonUtils;
import com.econsys.Genriclibrery.Driver;
import com.econsys.Genriclibrery.mailReport;
import com.econsys.Projects.Login;
import com.econsys.TestData.Workbook;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestListener extends Driver implements ITestListener,ISuiteListener,IInvokedMethodListener{
	private static Logger log=Logger.getLogger(TestListener.class.getName());
	Login login=new Login();
	CommonUtils cu= new CommonUtils();
	mailReport mreport=new mailReport();
	ExtentReports exReports;
	ExtentTest logger;
	Workbook wb = new Workbook();
	WebDriver driver;

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

	}
	public void onStart1(ISuite suite){

	}
	//On test suite start
	public void onStart(ISuite suite) {
		try {
			login.url();
			log.info("suite Name: "+suite);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("driver open ...@test suite start");
		log.info("This is before test suite....");

	}
	//On test suite finish
	public void onFinish(ISuite suite) {
		log.info("Suite execution ended ....");
		//driver.close();

		//mreport.sendmailReport("kaveri.r@quadwave.com");
	}

	//On @test start
	public void onTestStart(ITestResult result) {

		try {
			//Set project name to Test data excel sheet
			String projectName = result.getName().toString().trim();
			if (projectName.contentEquals("newLinkPrepareQuote")||projectName.contentEquals("newLinkAssignSalesLeader")||
					projectName.contentEquals("newLinkSubmitQuote")|| projectName.contentEquals("newLinkStatuOfSubmitQuote")|| 
					projectName.contentEquals("newLinkStatuOfResubmitQuote") || projectName.contentEquals("linkProjectAssignSalesLeader")||
					projectName.contentEquals("linkProjectPrepareQuote")|| projectName.contentEquals("linkProjectSubmitQuote")|| projectName.contentEquals("linkProjectStatusofSubmitQuote")
					|| projectName.contentEquals("linkProjectStatusofResubmitQuote")){
				log.info("For the test case---"+ projectName +" Project Name will remain same");
			}else{
				log.info("projectName***onTestStart :"+projectName); 
				wb.setExcelData(1, 1, 2,projectName);}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	//On @test success 
	public void onTestSuccess(ITestResult result) {

		log.info(result+" :Test has Passed");
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		boolean elementPresent = driver().findElements(By.xpath("//span[@class='nav-item-label']/span")).size()>0;
		if(elementPresent)
			try {
				//logout if element present
				login.logout();
				log.info("logged out ....");
			} catch (InterruptedException e) {
				e.printStackTrace();
				log.error(e);
			}
		else
			log.info("Not logged in to Application...");
	}
	//On @test failure
	public void onTestFailure(ITestResult result) {

		log.info(result+" :Test has failed");
		log.error("FAIL Test "+result.getName().toString().trim()+" was failed In "+result.getTestClass()+" class");
		log.error("This is the Exception thrown on test execution",result.getThrowable());
		String screenShotName_loc=System.getProperty("user.dir")+"\\ScreenShots\\"+"FAIL_"+result.getTestClass().getName()+"__"+result.getName().toString().trim();
		cu.screenShot(screenShotName_loc);

		try {
			cu.blindWait();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		boolean elementPresent=Driver.driver().findElements(By.xpath("//span[@class='nav-item-label']/span")).size()>0;
		if(elementPresent)

			try {
				//logout if element present
				login.logout();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		else
			log.info("Not logged in to Application...");
	}
	//On @test skipped
	public void onTestSkipped(ITestResult result) {

		log.info(result+"Test has skipped...");
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		boolean elementPresent=Driver.driver().findElements(By.xpath("//span[@class='nav-item-label']/span")).size()>0;
		if(elementPresent)

			try {
				login.logout();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		else
			log.info("Test has not logged in to Application...");
		Driver.driver().quit();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {


	}

	public void onFinish(ITestContext context) {


	}

}
