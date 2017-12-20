package com.ecosys.imperium;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.econsys.Genriclibrery.CommonUtils;
import com.econsys.Genriclibrery.Driver;
import com.econsys.Genriclibrery.EconsysVariables;
import com.econsys.Projects.Basic;
import com.econsys.Projects.Login;
import com.econsys.Projects.Monorail;
import com.econsys.SmallWorks.ProjectMethods_Small_Works;
import com.econsys.UIobjectrepositary.*;

/**
 * @author bhanu.pk
 * New Link will processed to generate the clone of project and 
 * In this class New linking of projects will starts from Saved rtq to status of re-submitted quote
 * Test case of new link for GA
 */

public class NewLink extends Driver{

	ActionButtonsUi ab = PageFactory.initElements(Driver.driver(), ActionButtonsUi.class);
	RTQForm_Ui rtqUi = PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
	public static Preparequote prepare_Quoteui=PageFactory.initElements(Driver.driver(), Preparequote.class);
	Logger log = Logger.getLogger(NewLink.class);
	Basic basic = new Basic();
	EconsysVariables ev = new EconsysVariables();
	Login login = new Login();
	CommonUtils commonUtils = new CommonUtils();
	Monorail rtq=new Monorail();
	Imperium_Project_Methods imperium_Project_Methods = new Imperium_Project_Methods(); 
	Imperium_SmallWorks_Methods imperium_SmallWorks_Methods = new Imperium_SmallWorks_Methods(); 

	//New link in Saved RTQ form
	@Test(priority=1)
	public void newLinkSavedRTQ() throws IOException, InterruptedException, AWTException {

		login.url();
		login.loginSD();
		imperium_Project_Methods.rtqForm(ev.estimatedSize,ev.location);
		ab.getSavebutton().click();
		//open saved rtq
		rtqUi.getSaved_RTQ_Link().click();
		driver.findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]//td/div/button")).click();
		commonUtils.blindWait();
		driver.findElement(By.xpath("//td/span[contains(text(),'Edit')]")).click();
		//Click on new rtq link
		rtqUi.getNew_Link().click();
		String projectName = driver.findElement(By.xpath("//input[@id='st_ProjectName']")).getAttribute("value");
		//Comparing/Asserting Project names
		Thread.sleep(1500);
		commonUtils.assert_Test(projectName, ev.prjname1);
		//ab.getSubmitbutton().click();
	}

	//@Test(priority=2)
	public void newLinkAssignSalesLeader() throws IOException, InterruptedException {

		//log.info("This is from  newLinkAssignSalesLeader "+projName);
		//ab.getHome().click();
		login.loginSD();
		commonUtils.blindWait();
		rtqUi.getSaved_RTQ_Link().click();
		driver.findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]//td/div/button")).click();
		commonUtils.blindWait();

		driver.findElement(By.xpath("//td/span[contains(text(),'Edit')]")).click();
		ab.getComments().sendKeys("Submit");
		imperium_Project_Methods.submit_logout_QRnumberAlert();

		/*b.projectname();
		rtqUi.getNew_Link().click();
	    String projectName = driver.findElement(By.xpath("//input[@id='st_ProjectName']")).getAttribute("value");
	    log.info("Project name :"+projectName);

	    log.info("Expected project name :"+ev.projectName());
	    //Asserting Project names
	    cu.blindWait();
	    Thread.sleep(1500);
	    cu.assert_Test(projectName, ev.projectName());*/
	}

	@Test(priority=3)
	public void newLinkPrepareQuote() throws InterruptedException, IOException {

		imperium_Project_Methods.ASL();

		login.loginSL();
		basic.projectname();
		rtqUi.getNew_Link().click();
		String projectName = driver.findElement(By.xpath("//input[@id='st_ProjectName']")).getAttribute("value");
		log.info("Project name :"+projectName);

		log.info("Expected project name :"+ ev.prjname1);
		//Asserting Project names
		commonUtils.blindWait();
		Thread.sleep(1500);
		commonUtils.assert_Test(projectName, ev.prjname1);
	}

	@Test(priority=4)
	public void newLinkSubmitQuote() throws InterruptedException, IOException {
			
		imperium_Project_Methods.prepare_Quote(ev.overallSell, ev.locationrtq2);
		commonUtils.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),ev.exeCP2);
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();

		basic.pathdession(ev.eSizertq2,ev.locationrtq2);

		if((ev.ourformat.equals("No"))){
			log.info("In CL approval");
			Imperium_Project_Methods.clApproval();
		}
		//**********CP2 exe dession**************
		if(ev.exeCP2.equals("Yes") || ev.bidsheetauthorised.equals("No")||ev.ourformat.equals("No")){
			basic.boardApproval();
		}

		boolean elementexist=driver.findElements(By.cssSelector("input[id='_58_emailInput'][name='_58_login']")).size()>0;
		if(elementexist){
			login.loginSL();
		}
		basic.projectname();
		rtqUi.getNew_Link().click();
		String projectName = driver.findElement(By.xpath("//input[@id='st_ProjectName']")).getAttribute("value");
		log.info("Project name :"+projectName);

		log.info("Expected project name :"+ev.prjname1);
		//Asserting Project names
		Thread.sleep(1500);
		commonUtils.assert_Test(projectName, ev.prjname1);
	}

	@Test(priority=5)
	public void newLinkStatuOfSubmitQuote() throws IOException, InterruptedException {
		
		ProjectMethods_Small_Works.submit_Quoteform();
		
		basic.projectname();
		rtqUi.getNew_Link().click();
		String projectName = driver.findElement(By.xpath("//input[@id='st_ProjectName']")).getAttribute("value");
		log.info("Project name :"+projectName);

		log.info("Expected project name :"+ev.prjname1);
		//Asserting Project names
		commonUtils.blindWait();
		Thread.sleep(1500);
		commonUtils.assert_Test(projectName, ev.prjname1);
	}

	@Test(priority=6)
	public void newLinkStatuOfResubmitQuote() throws InterruptedException, IOException {
		
		login.loginSL();
		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType, ev.quoteStatusAmendBidSubmit);
		imperium_Project_Methods.prepareQuotecp2cp3(ev.locationrtq3);
		commonUtils.selectByVisibleText(prepare_Quoteui.getExecp3(),ev.exeCP3);
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();
		//Path
		basic.pathdessioncp2cp3_Mat(ev.eSizertq3,ev.locationrtq3);
		if((ev.cp2cp3ourformat.equals(ev.select_No))){
			log.info("In CL approval");
			Imperium_Project_Methods.clApproval();
		}
		if(ev.cp2cp3bidsheetauthorised.equals("No")||ev.exeCP3.equals("Yes")){
			 basic.boardApproval();
		}
		ProjectMethods_Small_Works.submit_Quoteform();

		commonUtils.waitForPageToLoad();
		basic.projectname();
		rtqUi.getNew_Link().click();
		String projectName = driver.findElement(By.xpath("//input[@id='st_ProjectName']")).getAttribute("value");
		log.info("Project name :"+projectName);

		log.info("Expected project name--"+ ev.prjname1);
		//Asserting Project names
		commonUtils.blindWait();
		Thread.sleep(1500);
		commonUtils.assert_Test(projectName, ev.prjname1);
	}
}
