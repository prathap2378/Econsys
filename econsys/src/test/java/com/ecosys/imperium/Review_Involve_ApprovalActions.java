package com.ecosys.imperium;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.econsys.Genriclibrery.CommonUtils;
import com.econsys.Genriclibrery.Driver;
import com.econsys.Genriclibrery.EconsysVariables;
import com.econsys.Projects.Basic;
import com.econsys.Projects.Login;
import com.econsys.Projects.Monorail;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.ActionButtonsUi;
import com.econsys.UIobjectrepositary.CommercialReviewInvolve;
import com.econsys.UIobjectrepositary.EngReviewInvoveUi;
import com.econsys.UIobjectrepositary.Preparequote;

/**
 * @author bhanu.pk
 * Engineering and commercial review involvement action buttons
 * Reject approve save cancel and form validation
 */

public class Review_Involve_ApprovalActions extends Driver{
	
	CommercialReviewInvolve ricommercial = PageFactory.initElements(Driver.driver(), CommercialReviewInvolve.class);
	EngReviewInvoveUi riEngineering = PageFactory.initElements(Driver.driver(), EngReviewInvoveUi.class);
	ActionButtonsUi ab = PageFactory.initElements(Driver.driver(), ActionButtonsUi.class);
	Preparequote prepareQuoteUI = PageFactory.initElements(Driver.driver(), Preparequote.class);
	Logger log = Logger.getLogger(Review_Involve_ApprovalActions.class);
	//Class imported
	Login login = new Login();
	Monorail monorail = new Monorail();
	Basic b = new Basic();
	Workbook wb = new Workbook();
	EconsysVariables ev = new EconsysVariables();
	CommonUtils cu = new CommonUtils();

	@Test
	public void InvolveActions() throws IOException, InterruptedException, AWTException {
	  String estimatedSize = wb.getXLData(3, 3, 1);
	  String location =  wb.getXLData(4, 3, 1);
	  
	  login.url();
		//Monorail.monorailTestFlow(e_Size,location);
		cu.waitForPageToLoad();
		boolean elementexist=driver.findElements(By.cssSelector("input[id='_58_emailInput'][name='_58_login']")).size()>0;
		if(elementexist)
		login.user(); 
		
		//****Initiation of rtq form*********
	    b.rtqForm(estimatedSize,location);
	    b.submit_Logout();
	    
		//***********CP1 exe dession************
		if((estimatedSize.equals("D 500+"))||(location.equals("Other"))) {
			b.boardApproval();
		}
//		String userName = wb.getXLData(1, 0,0);
//		String sl=wb.getXLData(10, 0, 0);
//		
//		if(!sl.equals(userName)){
		monorail.ASL();
			
		
		//Prepare Quote
		monorail.prepare_Quote();
		cu.selectByVisibleText(prepareQuoteUI.getExpliciteapprovalatgateway2(),ev.exeCP2);
		prepareQuoteUI.getQuoteprepared().click();
		login.logout();
		
		if(estimatedSize.equals("D 500+")){
			System.out.println("Engineering Involvement path");
			login.loginEL();
			  Thread.sleep(1000);
			  ab.getReviewinvolvetasks().sendKeys(Keys.RETURN); 
			  b.projectname_Involves();
			  cu.blindWait();
			  ab.getCancelbutton().click();
			  
			  b.projectname_Involves();
			  cu.blindWait();
			  ab.getComments().sendKeys("Engineering involvement save...");
			  ab.getSavebutton().click();
			  log.info("Engineering involvement saved");
			  //Reject engineering involvement Need to wright new prepare quote
			  b.projectname_Involves();
			  cu.blindWait();
			  ab.getComments().sendKeys("Engineering involvement rejection");
			  ab.getRejectbutton().click();
			  login.logout();
		}
		if(location.equals("Other")){
			System.out.println("Commercial Involvement path");
			login.loginCL();
			  
			  ab.getReviewinvolvetasks().sendKeys(Keys.RETURN);	 
			  b.projectname_Involves();
			  cu.blindWait();
			  ab.getCancelbutton().click();
			  
			  b.projectname_Involves();
			  cu.blindWait();
			  ab.getComments().sendKeys("Commercial involvement");
			  ab.getSavebutton().click();
			  log.info("Commercial involvement saved");
			  
			//Reject commercial involvement Need to wright new prepare quote
			  b.projectname_Involves();
			  cu.blindWait();
			  ab.getComments().sendKeys("Commercial involvement rejection");
			  ab.getRejectbutton().click();
			  login.logout();
		}
		monorail.prepare_Quote();
		cu.selectByVisibleText(prepareQuoteUI.getExpliciteapprovalatgateway2(),ev.exeCP2);
		prepareQuoteUI.getQuoteprepared().click();
		login.logout();
		
		b.pathdession(estimatedSize, location);
		//**********CP2 exe dession**************
		if((ev.ourformat.equals("No"))||(ev.bidsheetauthorised.equals("No"))||(ev.exeCP2.equals("Yes"))){
			b.boardApproval();
		}
		monorail.submitQuote();
		monorail.statusofSubmitQuote(ev.customerCommitmentType,ev.quoteStatusAmendBid);
		
		//Re-Prepare Quote
		monorail.prepareQuotecp2cp3();
		cu.selectByVisibleText(prepareQuoteUI.getExecp3(),ev.exeCP3);
		prepareQuoteUI.getQuoteprepared().click();
		login.logout();
		
		if(estimatedSize.equals("D 500+")){
			System.out.println("Engineering Involvement path");
			login.loginEL();
			  Thread.sleep(1000);
			  ab.getReviewinvolvetasks().sendKeys(Keys.RETURN); 
			  b.projectname_Involves();
			  cu.blindWait();
			  ab.getCancelbutton().click();
			  
			  b.projectname_Involves();
			  cu.blindWait();
			  ab.getComments().sendKeys("Engineering involvement save...");
			  ab.getSavebutton().click();
			  log.info("Engineering involvement saved");
			  //Reject engineering involvement Need to wright new prepare quote
			  b.projectname_Involves();
			  cu.blindWait();
			  ab.getComments().sendKeys("Engineering involvement rejection");
			  ab.getRejectbutton().click();
			  login.logout();
		}
		if(location.equals("Other")){
			System.out.println("Commercial Involvement path");
			login.loginCL();
			  
			  ab.getReviewinvolvetasks().sendKeys(Keys.RETURN);	 
			  b.projectname_Involves();
			  cu.blindWait();
			  ab.getCancelbutton().click();
			  
			  b.projectname_Involves();
			  cu.blindWait();
			  ab.getComments().sendKeys("Commercial involvement");
			  ab.getSavebutton().click();
			  log.info("Commercial involvement saved");
			  
			//Reject commercial involvement Need to wright new prepare quote
			  b.projectname_Involves();
			  cu.blindWait();
			  ab.getComments().sendKeys("Commercial involvement rejection");
			  ab.getRejectbutton().click();
			  login.logout();
		}
		login.loginSL();
		monorail.prepareQuotecp2cp3();
		cu.selectByVisibleText(prepareQuoteUI.getExecp3(),ev.exeCP3);
		prepareQuoteUI.getQuoteprepared().click();
		login.logout();
		b.pathdessioncp2cp3(ev.estimatedSize, ev.location);
		log.info("review involvment actions are sucessfull...");
  }
  
@Test
  public void reviewActions() throws IOException, InterruptedException, AWTException {
	  String estimatedSize = wb.getXLData(6, 3, 1);
	  String location =  wb.getXLData(7, 3, 1);
	  //login.url();
		cu.waitForPageToLoad();
		boolean elementexist=driver.findElements(By.cssSelector("input[id='_58_emailInput'][name='_58_login']")).size()>0;
		if(elementexist)
		login.user(); 
		
		//****Initiation of rtq form*********
	    b.rtqForm(estimatedSize,location);
	    b.submit_Logout();
	    
		//***********CP1 exe dession************
		if((estimatedSize.equals("D 500+"))||(location.equals("Other"))) {
			b.boardApproval();
		}
//		Assign sales leader
		monorail.ASL();
			
		//Prepare Quote
		monorail.prepare_Quote();
		cu.selectByVisibleText(prepareQuoteUI.getExpliciteapprovalatgateway2(),ev.exeCP2);
		prepareQuoteUI.getQuoteprepared().click();
		login.logout();
		
		if(estimatedSize.equals("C 250-500k")){
			System.out.println("Engineering review path");
			login.loginEL();
			//cancel
			  Thread.sleep(1000);
			  ab.getReviewinvolvetasks().sendKeys(Keys.RETURN); 
			  b.projectname_Reviews();
			  cu.blindWait();
			  ab.getCancelbutton().click();
			  //save
			  b.projectname_Reviews();
			  cu.blindWait();
			  ab.getComments().sendKeys("Engineering involvement save...");
			  ab.getSavebutton().click();
			  log.info("Engineering involvement saved");
			  //Reject
			  b.projectname_Reviews();
			  cu.blindWait();
			  ab.getComments().sendKeys("Engineering involvement rejection");
			  ab.getRejectbutton().click();
			  login.logout();
		}
		if(location.equals("South East")){
			System.out.println("Commercial Involvement path");
			login.loginCL();
			  //cancel
			  ab.getReviewinvolvetasks().sendKeys(Keys.RETURN);	 
			  b.projectname_Reviews();
			  cu.blindWait();
			  ab.getCancelbutton().click();
			  //save
			  b.projectname_Reviews();
			  cu.blindWait();
			  ab.getComments().sendKeys("Commercial involvement");
			  ab.getSavebutton().click();
			  log.info("Commercial involvement saved");
			  
			//Reject
			  b.projectname_Reviews();
			  cu.blindWait();
			  ab.getComments().sendKeys("Commercial involvement rejection");
			  ab.getRejectbutton().click();
			  login.logout();
		}
		monorail.prepare_Quote();
		cu.selectByVisibleText(prepareQuoteUI.getExpliciteapprovalatgateway2(),ev.exeCP2);
		prepareQuoteUI.getQuoteprepared().click();
		login.logout();
		
		b.pathdession(estimatedSize, location);
		//**********CP2 exe dession**************
		if((ev.ourformat.equals("No"))||(ev.bidsheetauthorised.equals("No"))||(ev.exeCP2.equals("Yes"))){
			b.boardApproval();
		}
		monorail.submitQuote();
		monorail.statusofSubmitQuote(ev.customerCommitmentType,ev.quoteStatusAmendBid);
		
		monorail.prepareQuotecp2cp3();
		cu.selectByVisibleText(prepareQuoteUI.getExecp3(),ev.exeCP3);
		prepareQuoteUI.getQuoteprepared().click();
		login.logout();
		
		if(estimatedSize.equals("C 250-500k")){
			System.out.println("Engineering Involvement path");
			login.loginEL();
			  Thread.sleep(1000);
			  ab.getReviewinvolvetasks().sendKeys(Keys.RETURN); 
			  b.projectname_Reviews();
			  cu.blindWait();
			  ab.getCancelbutton().click();
			  
			  b.projectname_Reviews();
			  cu.blindWait();
			  ab.getComments().sendKeys("Engineering involvement save...");
			  ab.getSavebutton().click();
			  log.info("Engineering involvement saved");
			  //Reject engineering involvement Need to wright new prepare quote
			  b.projectname_Reviews();
			  cu.blindWait();
			  ab.getComments().sendKeys("Engineering involvement rejection");
			  ab.getRejectbutton().click();
			  login.logout();
		}
		if(location.equals("South East")){
			System.out.println("Commercial Involvement path");
			login.loginCL();
			  
			  ab.getReviewinvolvetasks().sendKeys(Keys.RETURN);	 
			  b.projectname_Reviews();
			  cu.blindWait();
			  ab.getCancelbutton().click();
			  
			  b.projectname_Reviews();
			  cu.blindWait();
			  ab.getComments().sendKeys("Commercial involvement");
			  ab.getSavebutton().click();
			  log.info("Commercial involvement saved");
			  
			//Reject commercial involvement Need to wright new prepare quote
			  b.projectname_Reviews();
			  cu.blindWait();
			  ab.getComments().sendKeys("Commercial involvement rejection");
			  ab.getRejectbutton().click();
			  login.logout();
		}
		login.loginSL();
		monorail.prepareQuotecp2cp3();
		cu.selectByVisibleText(prepareQuoteUI.getExecp3(),ev.exeCP3);
		prepareQuoteUI.getQuoteprepared().click();
		login.logout();
		b.pathdessioncp2cp3(ev.estimatedSize, ev.location);
		log.info("review actions are sucessfull...");

  }
}
