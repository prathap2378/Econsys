package com.ecosys.imperium;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.econsys.Genriclibrery.Alerts;
import com.econsys.Genriclibrery.CommonUtils;
import com.econsys.Genriclibrery.Driver;
import com.econsys.Genriclibrery.EconsysVariables;
import com.econsys.Projects.Basic;
import com.econsys.Projects.Login;
import com.econsys.Projects.Monorail;
import com.econsys.SmallWorks.ProjectMethods_Small_Works;
import com.econsys.UIobjectrepositary.ActionButtonsUi;
import com.econsys.UIobjectrepositary.Preparequote;
import com.econsys.UIobjectrepositary.RTQForm_Ui;

public class LinkProject extends Driver{

	Logger log = Logger.getLogger(LinkProject.class);
	ActionButtonsUi ab = PageFactory.initElements(Driver.driver(), ActionButtonsUi.class);
	RTQForm_Ui rtqUi = PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
	public static Preparequote prepare_Quoteui=PageFactory.initElements(Driver.driver(), Preparequote.class);
	Alerts alerts = PageFactory.initElements(Driver.driver(), Alerts.class);
	Basic basic = new Basic();
	EconsysVariables ev = new EconsysVariables();
	Login login = new Login();
	CommonUtils commonUtils = new CommonUtils();
	Monorail rtq=new Monorail();
	
	Imperium_Project_Methods imperium_Project_Methods = new Imperium_Project_Methods();
	Imperium_SmallWorks_Methods imperium_SmallWorks_Methods = new Imperium_SmallWorks_Methods();
	String link_projName;

	public void link_Unlinkaction() throws InterruptedException{

		link_projName = "createProject_to_Link";
		rtqUi.getLink_Projectpagebutton().click();//link the project
		commonUtils.blindWait();
		log.info("link_projName---"+link_projName);
		commonUtils.selectByVisibleText(rtqUi.getSelect_Project(), link_projName);//select the project
		rtqUi.getLink_Projectpopupbutton().click();//confiorm the project link
		commonUtils.blindWait();
		alerts.getAlert_Accept_OK().click();

		commonUtils.waitForElement(rtqUi.getShowMore(),500);
		rtqUi.getShowMore().click();//Click on show more
		commonUtils.waitForElement(rtqUi.getLinkedOppotunities(), 500);
		rtqUi.getLinkedOppotunities().click();//open linked opportunities accordion
		String projectName=rtqUi.getLinkedprojectName().getText();

		log.info("Project name :"+projectName);

		//Asserting Project names
		Thread.sleep(2000);
		commonUtils.assert_Test(projectName, link_projName);
		rtqUi.getUnLink_Project().click();	
		commonUtils.blindWait();
		alerts.getAlert_Accept_OK().click();//Click Ok to Unlink the project
		commonUtils.blindWait();
		alerts.getAlert_Accept_OK().click();
	}
	
	//@Test(priority=1)
	public void createProject_to_Link() throws IOException, InterruptedException, AWTException{

		login.url();
		login.loginSD();
		imperium_Project_Methods.rtqForm(ev.estimatedSize,ev.location);
		imperium_Project_Methods.submit_logout_QRnumberAlert();

		//link_projName = ev.prjname1;
		link_projName = "createProject_to_Link";
	}
	
	@Test(priority=2)
	public void linkProjectsavedRTQ() throws IOException, InterruptedException, AWTException {

		//Save RTQ form
		login.loginSL();
		imperium_Project_Methods.rtqForm(ev.estimatedSize,ev.location);
		ab.getSavebutton().click();
		//Open saved RTQ form
		rtqUi.getSaved_RTQ_Link().click();
		driver.findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]//td/div/button")).click();
		commonUtils.blindWait();
		driver.findElement(By.xpath("//td/span[contains(text(),'Edit')]")).click();
		//Link the project
		link_Unlinkaction();
	}
	
	/*//@Test (priority=3)
	public void linkProjectAssignSalesLeader() throws IOException, InterruptedException{
		
		login.url();
		login.loginSD();
		commonUtils.blindWait();
		rtqUi.getSaved_RTQ_Link().click();
		driver.findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]//td/div/button")).click();
		commonUtils.blindWait();

		driver.findElement(By.xpath("//td/span[contains(text(),'Edit')]")).click();
		ab.getComments().sendKeys("Submit");
		ab.getSubmitbutton().click();
		basic.projectname();
		link_Unlinkaction();
	}*/
	
	@Test(priority=3)
	public void linkProjectPrepareQuote() throws InterruptedException, IOException{

		try {
			login.loginSL();
			imperium_Project_Methods.rtqForm(ev.estimatedSize,ev.location);
			imperium_Project_Methods.submit_logout_QRnumberAlert();
			imperium_Project_Methods.ASL();
			login.loginSL();
			basic.projectname();
			link_Unlinkaction();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=4)
	public void linkProjectSubmitQuote() throws InterruptedException, IOException{
		
		imperium_Project_Methods.prepare_Quote(ev.overallSell, ev.locationrtq2);
		commonUtils.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),ev.exeCP2);
		prepare_Quoteui.getQuoteprepared().click();
		//login.logout();

		basic.pathdession(ev.eSizertq2,ev.locationrtq2);
		//Prepare quote CL approval for quote is not in our format
		if((ev.ourformat.equals("No"))){
			log.info("In CL approval");
			Imperium_Project_Methods.clApproval();
		}
		//**********CP2 exe dession**************
		if(ev.exeCP2.equals("Yes") || ev.bidsheetauthorised.equals("No")||ev.ourformat.equals("No")){
			basic.boardApproval();
		}
		basic.projectname();
		link_Unlinkaction();
	}
	
	@Test(priority=5)
	public void linkProjectStatusofSubmitQuote() throws IOException, InterruptedException{
		
		link_projName = "createProject_to_Link";
		ProjectMethods_Small_Works.submit_Quoteform();
		basic.projectname();
		link_Unlinkaction();
	}
	
	@Test(priority = 6)
	public void linkProjectStatusofResubmitQuote() throws IOException, InterruptedException{
		
		link_projName = "createProject_to_Link";
		login.loginSL();
		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType, ev.quoteStatusAmendBidSubmit);
		imperium_Project_Methods.prepareQuotecp2cp3(ev.locationrtq3);
		commonUtils.selectByVisibleText(prepare_Quoteui.getExecp3(),ev.exeCP3);
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();
		//Path
		basic.pathdessioncp2cp3(ev.estimatedSize,ev.location);

		if((ev.ourformat.equals("Yes")&&ev.cp2cp3ourformat.equals("No"))||(ev.cp2cp3bidsheetauthorised.equals("No"))||(ev.exeCP3.equals("Yes"))){

			basic.boardApproval();
		}
		rtq.resubmitQuote();
		basic.projectname();
		link_Unlinkaction();
	}
}
