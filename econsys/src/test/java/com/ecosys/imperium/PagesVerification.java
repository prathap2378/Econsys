package com.ecosys.imperium;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.econsys.Genriclibrery.CommonUtils;
import com.econsys.Genriclibrery.Driver;
import com.econsys.Projects.Login;
import com.econsys.UIobjectrepositary.AllPages;
import com.relevantcodes.extentreports.ExtentTest;

public class PagesVerification extends Driver{
	
	Logger log=Logger.getLogger(PagesVerification.class.getName());
	Login login= new Login();
	AllPages allPages= PageFactory.initElements(Driver.driver(), AllPages.class);
	CommonUtils cu=PageFactory.initElements(Driver.driver(), CommonUtils.class);
	ExtentTest test;
	  private SoftAssert softAssert = new SoftAssert();

	@Test
	public void verifyAllPages() throws IOException, InterruptedException{
	
		login.url();
		login.user();
		cu.waitForPageToLoad();
	
	//My account page verification
	Actions act = new Actions(driver);
	WebElement welcome=driver.findElement(By.xpath("//li[@id='_145_userAvatar']"));
	act.moveToElement(welcome).perform();
	allPages.getMyAccount().click();
	cu.blindWait();
	boolean s = driver.findElement(By.xpath("//*[contains(text(),'Account Details')]")).isDisplayed();
	log.info("My account page is displayed "+s);
	
	//All pages verification
	allPages.getAllProjects().click();
	log.info("All Projects");
	cu.waitForPageToLoad();
	boolean allp = Driver.driver().findElement(By.xpath("//div[contains(text(),'All Projects')]")).isDisplayed();
	
	//Details of all projects
	int cout = driver.findElements(By.xpath("//div[@id='gview_delegatestaticGrid']//tr")).size();
	if(cout>2){
		String row_Id = driver.findElement(By.xpath("//div[@id='gview_delegatestaticGrid']//tr[2]")).getAttribute("id");
		driver.findElement(By.xpath("//button[@id='actn-btn-"+row_Id+"']")).click();
		driver.findElement(By.xpath("//td[@onclick='viewDetails("+row_Id+")']")).click();
		
		int guidenceNotesdisplayed = driver.findElements(By.xpath("//div[@id='processView_Div']")).size();
		
		if(guidenceNotesdisplayed>0){
			log.info("Projects loading in All Projects********");
			softAssert.assertEquals(allPages.getGuidanceNotesaccordion().isDisplayed(),true);
		}else{
			log.info("Projects Not loading in All Projects----------");
		}
	}else {
		log.info("No data in All Projects table");
	}
		//allPages.getOptionBtn().click();
	//allPages.getViewOption().click();
	
	
	//My Opportunities
	allPages.getMyOpplink().click();
	log.info("My oppr");
	cu.waitForPageToLoad();
	boolean myOpp = Driver.driver().findElement(By.xpath("//div[contains(text(),'My Opportunities')]")).isDisplayed();
	log.info("myOpp is displayed : "+myOpp);
	//allPages.getOptionBtn().click();
	//allPages.getViewOption().click();
	
	//Details
	if(cout>2){
		String row_Id = driver.findElement(By.xpath("//div[@id='gview_delegatestaticGrid']//tr[2]")).getAttribute("id");
		driver.findElement(By.xpath("//button[@id='actn-btn-"+row_Id+"']")).click();
		driver.findElement(By.xpath("//td[@onclick='viewDetails("+row_Id+")']")).click();
		
		int guidenceNotesdisplayed = driver.findElements(By.xpath("//div[@id='processView_Div']")).size();
		
		if(guidenceNotesdisplayed>0){
			log.info("Projects loading in My Opportunities********");
			softAssert.assertEquals(allPages.getGuidanceNotesaccordion().isDisplayed(),true);
		}else{
			log.info("Projects Not loading in My Opportunities----------");
		}
	}else {
		log.info("No data in Myopportunities table");
	}
	
	
	//Project archive
	allPages.getProArchLink().click();
	log.info("Project archive");
	cu.waitForPageToLoad();
	boolean proArc = Driver.driver().findElement(By.xpath("//div[contains(text(),'Project Archive')]")).isDisplayed();
	int couuPro = driver.findElements(By.xpath("//div[@id='gview_info1']//tr")).size();
	if(couuPro>2){
		
		String rowID = driver.findElement(By.xpath("//div[@id='gview_info1']//tr[2]")).getAttribute("id");
		driver.findElement(By.xpath("//img[@onclick='viewDetails("+rowID+")']")).click();;
		int guidenceNotesdisplayed = driver.findElements(By.xpath("//div[@id='processView_Div']")).size();
		if(guidenceNotesdisplayed>0){
			log.info("Projects loading in Project archive");
		}else{
			log.info("Projects Not loading in Project archive????");
		}
	}else {
		log.info("No data in Project Archive table");
	}
	/*allPages.getdetailsLink().click();
	softAssert.assertEquals(allPages.getGuidanceNotesaccordion().isDisplayed(),true);*/

	//Executive Dash Board
	allPages.getExeDashLink().click();
	log.info("Exe dash board");
	cu.waitForPageToLoad();

	boolean exe = Driver.driver().findElement(By.xpath("//div[contains(text(),'Executive Dashboard')]")).isDisplayed();
	log.info("Exe is displayed : "+exe);
	/*allPages.getdetailsLink().click();
	softAssert.assertEquals(allPages.getGuidanceNotesaccordion().isDisplayed(),true);*/
	int exe_count = driver.findElements(By.xpath("//div[@id='gview_info1']//tr")).size();
	if(exe_count>2){
		
		cu.blindWait();
		String row_Id = driver.findElement(By.xpath("//div[@id='gview_info1']//tr[2]")).getAttribute("id");
		driver.findElement(By.xpath("//button[@id='actn-btn-"+row_Id+"']")).click();
		driver.findElement(By.xpath("//span[@id='details_"+row_Id+"']")).click();
		//softAssert.assertEquals(allPages.getGuidanceNotesaccordion().isDisplayed(),true);
	}else{
		log.info("No data in exeecutive dash board");
		//softAssert.assertEquals(allPages.getGuidanceNotesaccordion().isDisplayed(),true);
	}

	//Monthly Review
	Thread.sleep(1000);
	allPages.getReviewLink().click();
	cu.waitForPageToLoad();
	log.info("Review");
	boolean rev = Driver.driver().findElement(By.xpath("//div[contains(text(),'Review ')]")).isDisplayed();
	log.info("Review is displayed : "+rev);
	
	//Details
	int countPro = driver.findElements(By.xpath("//div[@id='gview_monthlyReviewGrid']//tr")).size();
	if(countPro>2){
	allPages.getreviewDetailsLink().click();
	softAssert.assertEquals(allPages.getReviewContent().isDisplayed(), true);
	}else {
		log.info("No data in Review table");
	}
	
	//Project Documents
	allPages.getProDocslink().click();
	cu.waitForPageToLoad();
	log.info("Project documents");
	softAssert.assertEquals(allPages.getProDoc().isDisplayed(),true);
	
	allPages.getAdminLink().click();
	cu.waitForPageToLoad();
	log.info("Saved rtq");
	cu.blindWait();
	allPages.getSaveRTQlink().click();
	cu.waitForPageToLoad();
	int n = driver.findElements(By.xpath("//table[@id='delegateGrid']//tr")).size();
	
	if(n>1){
	allPages.getOptionBtn().click();
	//allPages.getView_SavedRTQ().click();
	driver.findElement(By.xpath("//span[starts-with(@onclick,'viewRtq')]")).click();
	softAssert.assertEquals(allPages.getGuidanceNotesaccordion().isDisplayed(),true);
	}
	
	/*allPages.getAutoApplink().click();
	cu.waitForPageToLoad();
	log.info("Auto approval");
	softAssert.assertEquals(allPages.getAutoApproval().isDisplayed(),true);*/
	
	allPages.getBusinessRuleslink().click();
	cu.waitForPageToLoad();
	log.info("Business rules");
	softAssert.assertEquals(allPages.getBusinessRules().isDisplayed(),true);
	//Commercial link
	driver.findElement(By.xpath("//li[starts-with(@id,'_businessrules_')]/a[contains(text(),' Commercial ')]")).click();
	//Estimated link
	driver.findElement(By.xpath("//li[starts-with(@id,'_businessrules_')]/a[contains(text(),' Estimated Size Configuration ')]")).click();
	//Margin config link
	driver.findElement(By.xpath("//li[starts-with(@id,'_businessrules_')]/a[contains(text(),' Margin Configuration ')]")).click();
	//Customer commitment link
	driver.findElement(By.xpath("//li[starts-with(@id,'_businessrules_')]/a[contains(text(),' Customer Commitment ')]")).click();
	//Small works Link
	driver.findElement(By.xpath("//li[starts-with(@id,'_businessrules_')]/a[contains(text(),' Small Works ')]")).click();
	
	allPages.getUserListlink().click();
	cu.waitForPageToLoad();
	driver.findElement(By.xpath("//li[starts-with(@id,'_deligatertq_')]/a[contains(text(),' Role Management ')]")).click();
	log.info("user list");
	softAssert.assertEquals(allPages.getUserList().isDisplayed(),true);
	
	allPages.getGuidanceNoteslink().click();
	cu.waitForPageToLoad();
	log.info("Guidence notes");
	softAssert.assertEquals(allPages.getGuidanceNotes().isDisplayed(),true);
	
	allPages.getGridMasterlink().click();
	cu.waitForPageToLoad();
	driver.findElement(By.xpath("//li[starts-with(@id,'_curdgridMasters_')]/a[contains(text(),' Sub-Contractor Procurement ')]")).click();
	driver.findElement(By.xpath("//li[starts-with(@id,'_curdgridMasters_')]/a[contains(text(),' Cost Code ')]")).click();
	driver.findElement(By.xpath("//li[starts-with(@id,'_curdgridMasters_')]/a[contains(text(),' Sales to Operations Handover ')]")).click();
	driver.findElement(By.xpath("//li[starts-with(@id,'_curdgridMasters_')]/a[contains(text(),' Rule Elements ')]")).click();
	driver.findElement(By.xpath("//li[starts-with(@id,'_curdgridMasters_')]/a[contains(text(),' Small Works ')]")).click();
	log.info("Grid master");
	softAssert.assertEquals(allPages.getGridMasters().isDisplayed(),true);
	
	allPages.getDataReportlink().click();
	cu.waitForPageToLoad();
	log.info("Data report");
	softAssert.assertEquals(allPages.getDataReport().isDisplayed(),true);
	
	allPages.getSettingslink().click();
	cu.waitForPageToLoad();
	
	driver.findElement(By.xpath("//li[starts-with(@id,'_deligatertq_')]/a[contains(text(),' Dropbox ')]")).click();
	
	driver.findElement(By.xpath("//li[starts-with(@id,'_deligatertq_')]/a[contains(text(),' Prompts and Alerts Send out Time ')]")).click();
	log.info("settings");
	softAssert.assertEquals(allPages.getSettings().isDisplayed(),true);
	
	allPages.getPromptsAlerts().click();
	cu.waitForPageToLoad();
	log.info("PromptsAlerts");
	softAssert.assertEquals(allPages.getPromptsAlerts().isDisplayed(),true);
	
	//softAssert.assertAll();
}
/*	@Test(priority=2)
	public void testMyOpp() throws IOException, InterruptedException{
	login.loginsuperadminTestServer();
	allPages.getMyOpplink().click();
	cu.waitForPageToLoad();
	Assert.assertEquals(allPages.getMy_opportunities().getText(), "My Opportunities");
	
}
	@Test(priority=3)
	public void testProArchive() throws IOException, InterruptedException{
	login.loginsuperadminTestServer();
	allPages.getProArchLink().click();
	cu.waitForPageToLoad();
	Assert.assertEquals(allPages.getProArchive().getText(), "Project Archive");
	
}
	@Test(priority=4)
	public void testexeDashboard() throws IOException, InterruptedException{
	login.loginsuperadminTestServer();
	allPages.getExeDashLink().click();
	cu.waitForPageToLoad();
	Assert.assertEquals(allPages.getExeDashboard().getText(), "Executive Dashboard");
	
	}
	@Test(priority=5)
	public void testSavedRTQ() throws IOException, InterruptedException{
		login.loginsuperadminTestServer();
		allPages.getAdminLink().click();
		cu.waitForPageToLoad();
		allPages.getSaveRTQlink().click();
		cu.waitForPageToLoad();
		Assert.assertEquals(allPages.getSavedRTQ().getText(), "Saved RTQ");
	}
	
	@Test(priority=6)
	public void testAutoApproval() throws IOException, InterruptedException{
		login.loginsuperadminTestServer();
		allPages.getAutoApplink().click();
		cu.waitForPageToLoad();
		Assert.assertEquals(allPages.getAutoApproval().getText(), "Auto Approval Criteria");
	}
	@Test(priority=7)
	public void testBusinessRules() throws IOException, InterruptedException{
		login.loginsuperadminTestServer();
		allPages.getBusinessRuleslink().click();
		cu.waitForPageToLoad();
		Assert.assertEquals(allPages.getBusinessRules().getText(), "Business Rules");
	}
	@Test(priority=8)
	public void testUserList() throws IOException, InterruptedException{
		login.loginsuperadminTestServer();
		allPages.getUserListlink().click();
		cu.waitForPageToLoad();
		Assert.assertEquals(allPages.getUserList().getText(), "User List");
	}
	
	@Test(priority=9)
	public void testProjectDoc() throws IOException, InterruptedException{
		login.loginsuperadminTestServer();
		allPages.getProDocslink().click();
		cu.waitForPageToLoad();
		Assert.assertEquals(allPages.getProDoc().getText(), "Project Documents");
	}
	@Test(priority=10)
	public void testGuidanceNotes() throws IOException, InterruptedException{
		login.loginsuperadminTestServer();
		allPages.getGuidanceNoteslink().click();
		cu.waitForPageToLoad();
		Assert.assertEquals(allPages.getGuidanceNotes().getText(), "Guidance Notes");
	}
	@Test(priority=11)
	public void testGridMaster() throws IOException, InterruptedException{
		login.loginsuperadminTestServer();
		allPages.getGridMasterlink().click();
		cu.waitForPageToLoad();
		Assert.assertEquals(allPages.getGridMasters().getText(), "Masters");
	}
	@Test(priority=11)
	public void testDataReport() throws IOException, InterruptedException{
		login.loginsuperadminTestServer();
		allPages.getDataReportlink().click();
		cu.waitForPageToLoad();
		Assert.assertEquals(allPages.getDataReport().getText(), "Data Report");
	}
	
	@Test(priority=12)
	public void testSettings() throws IOException, InterruptedException{
		login.loginsuperadminTestServer();
		allPages.getSettingslink().click();
		cu.waitForPageToLoad();
		Assert.assertEquals(allPages.getSettings().getText(), "Settings/Preferences");
	}
*/
}


