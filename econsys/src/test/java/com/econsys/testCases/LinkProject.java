package com.econsys.testCases;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.econsys.Genriclibrery.Alerts;
import com.econsys.Genriclibrery.CommonUtils;
import com.econsys.Genriclibrery.Driver;
import com.econsys.Genriclibrery.EconsysVariables;
import com.econsys.Projects.Basic;
import com.econsys.Projects.Login;
import com.econsys.Projects.Monorail;
import com.econsys.UIobjectrepositary.ActionButtonsUi;
import com.econsys.UIobjectrepositary.Preparequote;
import com.econsys.UIobjectrepositary.RTQForm_Ui;

public class LinkProject extends Driver{
	ActionButtonsUi ab = PageFactory.initElements(Driver.driver(), ActionButtonsUi.class);
	RTQForm_Ui rtqUi = PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
	public static Preparequote prepare_Quoteui=PageFactory.initElements(Driver.driver(), Preparequote.class);
	Basic b = new Basic();
	EconsysVariables ev = new EconsysVariables();
	Login login = new Login();
	CommonUtils cu = new CommonUtils();
	Monorail rtq=new Monorail();
	public static String link_projName;
	Logger log = Logger.getLogger(LinkProject.class);
	Alerts alerts = new Alerts();
	public void linkUnlinkaction() throws InterruptedException{
		rtqUi.getLink_Projectpagebutton().click();
		cu.selectByVisibleText(rtqUi.getSelect_Project(), link_projName);
		rtqUi.getLink_Projectpopupbutton().click();
		cu.blindWait();
		cu.blindWait();
		rtqUi.getManageAccprdion().click();//To show all accordion
		rtqUi.getLinkedOppotunities().click();
		String projectName=rtqUi.getLinkedprojectName().getText();
		
	    log.info("Project name :"+projectName);
	    
	   
	    //Asserting Project names
	    cu.blindWait();
	    Thread.sleep(1500);
	    cu.assert_Test(projectName, link_projName);
	    rtqUi.getUnLink_Project().click();	
	    cu.blindWait();
	    alerts.getAlert_Accept_Ok().click();//Click Ok to Unlink the project
	    cu.blindWait();
	    alerts.getAlert_Accept_Ok().click();
	}
  //@Test(priority=1)
	public void createLinkProject() throws IOException, InterruptedException, AWTException{
		login.url();
		login.loginSD();
		b.rtqForm(ev.estimatedSize,ev.location);
		
		ab.getSubmitbutton().click();
		link_projName=ev.projectName();
		System.out.println("Link Project Name :"+link_projName);
  }
  @Test(priority=2)
  public void linkProjectsavedRTQ() throws IOException, InterruptedException, AWTException {
	  login.url();
		login.loginSD();
		b.rtqForm(ev.estimatedSize,ev.location);
		
		ab.getSavebutton().click();
		
		rtqUi.getSaved_RTQ_Link().click();
		
		driver.findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]//td/div/button")).click();
		cu.blindWait();
		
		driver.findElement(By.xpath("//td/span[contains(text(),'Edit')]")).click();
		rtqUi.getLink_Projectpagebutton().click();
		cu.selectByVisibleText(rtqUi.getSelect_Project(), link_projName);
		rtqUi.getLink_Projectpopupbutton().click();
		cu.blindWait();
		alerts.getAlert_Accept_Ok().click();
		cu.blindWait();
		rtqUi.getLinkedOppotunities().click();
		String projectName=rtqUi.getLinkedprojectName().getText();
		
	    log.info("Project name :"+projectName);
	    
	   
	    //Asserting Project names
	    cu.blindWait();
	    Thread.sleep(1500);
	    cu.assert_Test(projectName, link_projName);
	    rtqUi.getUnLink_Project().click();	
	    cu.blindWait();
	    alerts.getAlert_Accept_Ok().click();//Click Ok to Unlink the project
	    cu.blindWait();
	    alerts.getAlert_Accept_Ok().click();
  }
  @Test (priority=3)
  public void linkProjectAssignSalesLeader() throws IOException, InterruptedException{
	  login.url();
	  login.loginSD();
		cu.blindWait();
		rtqUi.getSaved_RTQ_Link().click();
		driver.findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]//td/div/button")).click();
		cu.blindWait();
		
		driver.findElement(By.xpath("//td/span[contains(text(),'Edit')]")).click();
		ab.getComments().sendKeys("Submit");
		ab.getSubmitbutton().click();
		b.projectname();
		linkUnlinkaction();
  }
  	@Test(priority=4)
  public void linkProjectPrepareQuote() throws InterruptedException, IOException{
	  	rtq.ASL();
		login.loginSD();
		b.projectname();
		linkUnlinkaction();
		
  }
  	@Test(priority=5)
  	public void linkProjectSubmitQuote() throws InterruptedException, IOException{
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
		linkUnlinkaction();
  	}
  	@Test(priority=6)
  	public void linkProjectStatusofSubmitQuote() throws IOException, InterruptedException{
  		rtq.submitQuote();
		b.projectname();
		linkUnlinkaction();
  	}
  	@Test(priority=7)
  	public void linkProjectStatusofResubmitQuote() throws IOException, InterruptedException{
  		login.loginSD();
  	  rtq.statusofSubmitQuote("", ev.quoteStatusAmendBid);
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
  		linkUnlinkaction();
  	}
}
