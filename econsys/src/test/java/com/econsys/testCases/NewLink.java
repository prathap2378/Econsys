package com.econsys.testCases;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.econsys.Genriclibrery.CommonUtils;
import com.econsys.Genriclibrery.Driver;
import com.econsys.Genriclibrery.EconsysVariables;
import com.econsys.Projects.Basic;
import com.econsys.Projects.Login;
import com.econsys.Projects.Monorail;
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
	Basic b = new Basic();
	EconsysVariables ev = new EconsysVariables();
	Login login = new Login();
	CommonUtils cu = new CommonUtils();
	Monorail rtq=new Monorail();
	Logger log = Logger.getLogger(NewLink.class);
	@Test(priority=1)

	public void newLinkSavedRTQ() throws IOException, InterruptedException, AWTException {
		//Save the RTQ form
		login.url();
		login.loginSD();
		b.rtqForm(ev.estimatedSize,ev.location);
		ab.getSavebutton().click();
		//open saved rtq
		rtqUi.getSaved_RTQ_Link().click();
		driver.findElement(By.xpath("//tr[td[@title=\""+ev.projectName()+"\"]]//td/div/button")).click();
		cu.blindWait();
		driver.findElement(By.xpath("//td/span[contains(text(),'Edit')]")).click();
		//Click on new rtq link
		rtqUi.getNew_Link().click();
	    String projectName = driver.findElement(By.xpath("//input[@id='st_ProjectName']")).getAttribute("value");
	    //Comparing/Asserting Project names
	    Thread.sleep(1500);
	    cu.assert_Test(projectName, ev.projectName());
	    //ab.getSubmitbutton().click();
	}
  
	@Test(priority=2)
	public void newLinkAssignSalesLeader() throws IOException, InterruptedException {
		//log.info("This is from  newLinkAssignSalesLeader "+projName);
		//ab.getHome().click();
		login.loginSD();
		cu.blindWait();
		rtqUi.getSaved_RTQ_Link().click();
		driver.findElement(By.xpath("//tr[td[@title=\""+ev.projectName()+"\"]]//td/div/button")).click();
		cu.blindWait();
		
		driver.findElement(By.xpath("//td/span[contains(text(),'Edit')]")).click();
		ab.getComments().sendKeys("Submit");
		ab.getSubmitbutton().click();
		b.projectname();
		rtqUi.getNew_Link().click();
	    String projectName = driver.findElement(By.xpath("//input[@id='st_ProjectName']")).getAttribute("value");
	    log.info("Project name :"+projectName);
	    
	    log.info("Expected project name :"+ev.projectName());
	    //Asserting Project names
	    cu.blindWait();
	    Thread.sleep(1500);
	    cu.assert_Test(projectName, ev.projectName());
	}

	@Test(priority=3)
	public void newLinkPrepareQuote() throws InterruptedException, IOException {
			
			rtq.ASL();
			login.loginSD();
			
			b.projectname();
			rtqUi.getNew_Link().click();
		    String projectName = driver.findElement(By.xpath("//input[@id='st_ProjectName']")).getAttribute("value");
		    log.info("Project name :"+projectName);
		    
		    log.info("Expected project name :"+ev.projectName());
		    //Asserting Project names
		    cu.blindWait();
		    Thread.sleep(1500);
		    cu.assert_Test(projectName, ev.projectName());
			
	}

	@Test(priority=4)
	public void newLinkSubmitQuote() throws InterruptedException, IOException {
		rtq.prepare_Quote();
		cu.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),ev.exeCP2);
		prepare_Quoteui.getQuoteprepared().click();
		//login.logout();
		
		b.pathdession(ev.estimatedSize,ev.location);
		//**********CP2 exe dession**************
		if((ev.ourformat.equals("No"))||(ev.bidsheetauthorised.equals("No"))||(ev.exeCP2.equals("Yes"))){
			b.boardApproval();
		}
		b.projectname();
		rtqUi.getNew_Link().click();
	    String projectName = driver.findElement(By.xpath("//input[@id='st_ProjectName']")).getAttribute("value");
	    log.info("Project name :"+projectName);
	    
	    log.info("Expected project name :"+ev.projectName());
	    //Asserting Project names
	    cu.blindWait();
	    Thread.sleep(1500);
	    cu.assert_Test(projectName, ev.projectName());
		
	}
  
@Test(priority=5)
	public void newLinkStatuOfSubmitQuote() throws IOException, InterruptedException {
		rtq.submitQuote();
		b.projectname();
		rtqUi.getNew_Link().click();
	    String projectName = driver.findElement(By.xpath("//input[@id='st_ProjectName']")).getAttribute("value");
	    log.info("Project name :"+projectName);
	    
	    log.info("Expected project name :"+ev.projectName());
	    //Asserting Project names
	    cu.blindWait();
	    Thread.sleep(1500);
	    cu.assert_Test(projectName, ev.projectName());
	}

	@Test(priority=6)
	public void newLinkStatuOfResubmitQuote() throws InterruptedException, IOException {
		login.loginSD();
  rtq.statusofSubmitQuote("", "Amend Bid");
  rtq.prepareQuotecp2cp3();
	 cu.selectByVisibleText(prepare_Quoteui.getExecp3(),ev.exeCP3);
	 prepare_Quoteui.getQuoteprepared().click();
	 login.logout();
	 //Path
	 b.pathdessioncp2cp3(ev.estimatedSize,ev.location);
	 
	 if((ev.ourformat.equals("Yes")&&ev.cp2cp3ourformat.equals("No"))||(ev.cp2cp3bidsheetauthorised.equals("No"))||(ev.exeCP3.equals("Yes"))){
		 
		 b.boardApproval();
	 }
	 rtq.resubmitQuote();
     b.projectname();
     rtqUi.getNew_Link().click();
	    String projectName = driver.findElement(By.xpath("//input[@id='st_ProjectName']")).getAttribute("value");
	    log.info("Project name :"+projectName);
	    
	    log.info("Expected project name :"+ev.projectName());
	    //Asserting Project names
	    cu.blindWait();
	    Thread.sleep(1500);
	    cu.assert_Test(projectName, ev.projectName());
	}
}
