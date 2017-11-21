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
import com.econsys.Projects.ReviewInvolve;
import com.econsys.SmallWorks.ProjectMethods_Small_Works;
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

public class ActionsReviewApproval extends Driver{

	CommercialReviewInvolve ricommercial = PageFactory.initElements(Driver.driver(), CommercialReviewInvolve.class);
	EngReviewInvoveUi riEngineering = PageFactory.initElements(Driver.driver(), EngReviewInvoveUi.class);
	ActionButtonsUi ab = PageFactory.initElements(Driver.driver(), ActionButtonsUi.class);
	Preparequote prepare_Quoteui = PageFactory.initElements(Driver.driver(), Preparequote.class);
	Logger log = Logger.getLogger(ActionsReviewApproval.class);
	//Class imported
	Login login = new Login();
	Monorail monorail = new Monorail();
	Basic basic = new Basic();
	Workbook wb = new Workbook();
	EconsysVariables ev = new EconsysVariables();
	CommonUtils commonUtils = new CommonUtils();
	Imperium_Project_Methods imperium_Project_Methods =  new Imperium_Project_Methods();
	Imperium_SmallWorks_Methods imperium_SmallWorks_Methods = new Imperium_SmallWorks_Methods();

	@Test(priority=1)
	public void InvolveActions() throws IOException, InterruptedException, AWTException {

		login.url();
		//Monorail.monorailTestFlow(e_Size,location);
		commonUtils.waitForPageToLoad();
		boolean elementexist=driver.findElements(By.cssSelector("input[id='_58_emailInput'][name='_58_login']")).size()>0;
		if(elementexist)
			login.user(); 

		//****Initiation of rtq form*********
		imperium_Project_Methods.rtqForm(ev.estimatedSize500_, ev.location_other);
		imperium_Project_Methods.submit_logout_QRnumberAlert();
		//***********CP1 explicit approval decision************
		if((ev.estimatedSize500_.equals(ev.estimatedSize500_))||(ev.location_other.equals(ev.location_other))) {
			basic.boardApproval();
		}
		//Assign Sales Leader
		imperium_Project_Methods.ASL();

		//Prepare Quote
		imperium_Project_Methods.prepare_Quote(ev.overallSell ,ev.locationrtq2);
		commonUtils.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),ev.select_No);
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();

		if(ev.estimatedSize500_.equals(ev.estimatedSize500_)){
			log.info("Engineering Involvement path");
			login.loginEL();
			Thread.sleep(1000);
			ab.getReviewinvolvetasks().sendKeys(Keys.RETURN); 
			basic.projectname_Involves();
			commonUtils.blindWait();
			ab.getCancelbutton().click();

			basic.projectname_Involves();
			commonUtils.blindWait();
			ab.getComments().sendKeys("Engineering involvement save...");
			ab.getSavebutton().click();
			log.info("Engineering involvement saved");
			//Reject engineering involvement Need to wright new prepare quote
			basic.projectname_Involves();
			commonUtils.blindWait();
			ab.getComments().sendKeys("Engineering involvement rejection");
			ab.getRejectbutton().click();
			login.logout();
		}
		if(ev.location_other.equals(ev.location_other)){
			log.info("Commercial Involvement path");
			login.loginCL();

			ab.getReviewinvolvetasks().sendKeys(Keys.RETURN);	 
			basic.projectname_Involves();
			commonUtils.blindWait();
			ab.getCancelbutton().click();

			basic.projectname_Involves();
			commonUtils.blindWait();
			ab.getComments().sendKeys("Commercial involvement");
			ab.getSavebutton().click();
			log.info("Commercial involvement saved");

			//Reject commercial involvement Need to wright new prepare quote
			basic.projectname_Involves();
			commonUtils.blindWait();
			ab.getComments().sendKeys("Commercial involvement rejection");
			ab.getRejectbutton().click();
			login.logout();
		}
		imperium_Project_Methods.prepare_Quote2();
		commonUtils.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),ev.select_No);
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();

		basic.pathdession(ev.estimatedSize500_, ev.location_other);
		if((ev.cp2cp3ourformat.equals("No"))){
			log.info("In CL approval");
			Imperium_Project_Methods.clApproval();
		}
		//**********CP2 exe dession**************
		if(ev.exeCP2.equals("Yes") || ev.bidsheetauthorised.equals("No")||ev.ourformat.equals("No")){
			basic.boardApproval();
		}
		ProjectMethods_Small_Works.submit_Quoteform();
		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType, ev.quoteStatusAmendBidSubmit);

		//Re-Prepare Quote
		imperium_Project_Methods.prepareQuotecp2cp3(ev.location_other);
		commonUtils.selectByVisibleText(prepare_Quoteui.getExecp3(),ev.select_No);
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();

		if(ev.estimatedSize500_.equals(ev.estimatedSize500_)){
			
			log.info("Engineering Involvement path");
			login.loginEL();
			Thread.sleep(1000);
			ab.getReviewinvolvetasks().sendKeys(Keys.RETURN); 
			basic.projectname_Involves();
			commonUtils.blindWait();
			ab.getCancelbutton().click();

			basic.projectname_Involves();
			commonUtils.blindWait();
			ab.getComments().sendKeys("Engineering involvement save...");
			ab.getSavebutton().click();
			log.info("Engineering involvement saved");
			//Reject engineering involvement Need to wright new prepare quote
			basic.projectname_Involves();
			commonUtils.blindWait();
			ab.getComments().sendKeys("Engineering involvement rejection");
			ab.getRejectbutton().click();
			login.logout();
		}
		if(ev.location_other.equals(ev.location_other)){
			log.info("Commercial Involvement path");
			login.loginCL();

			ab.getReviewinvolvetasks().sendKeys(Keys.RETURN);	 
			basic.projectname_Involves();
			commonUtils.blindWait();
			ab.getCancelbutton().click();

			basic.projectname_Involves();
			commonUtils.blindWait();
			ab.getComments().sendKeys("Commercial involvement");
			ab.getSavebutton().click();
			log.info("Commercial involvement saved");

			//Reject commercial involvement Need to wright new prepare quote
			basic.projectname_Involves();
			commonUtils.blindWait();
			ab.getComments().sendKeys("Commercial involvement rejection");
			ab.getRejectbutton().click();
			login.logout();
		}
		login.loginSL();
		monorail.prepareQuotecp2cp3();
		commonUtils.selectByVisibleText(prepare_Quoteui.getExecp3(),ev.exeCP3);
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();
		basic.pathdessioncp2cp3(ev.estimatedSize, ev.location);
		log.info("review involvment actions are sucessfull...");
	}

	@Test(priority=2)
	public void reviewActions() throws IOException, InterruptedException, AWTException {
		String estimatedSize = wb.getXLData(6, 3, 1);
		String location =  wb.getXLData(7, 3, 1);
		//login.url();
		commonUtils.waitForPageToLoad();
		boolean elementexist=driver.findElements(By.cssSelector("input[id='_58_emailInput'][name='_58_login']")).size()>0;
		if(elementexist)
			login.user(); 

		//****Initiation of rtq form*********
		basic.rtqForm(estimatedSize,location);
		basic.submit_Logout();

		//***********CP1 exe dession************
		if((estimatedSize.equals("D 500+"))||(location.equals("Other"))) {
			basic.boardApproval();
		}
		//		Assign sales leader
		monorail.ASL();

		//Prepare Quote
		monorail.prepare_Quote();
		commonUtils.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),ev.exeCP2);
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();

		if(estimatedSize.equals("C 250-500k")){
			log.info("Engineering review path");
			login.loginEL();
			//cancel
			Thread.sleep(1000);
			ab.getReviewinvolvetasks().sendKeys(Keys.RETURN); 
			basic.projectname_Reviews();
			commonUtils.blindWait();
			ab.getCancelbutton().click();
			//save
			basic.projectname_Reviews();
			commonUtils.blindWait();
			ab.getComments().sendKeys("Engineering involvement save...");
			ab.getSavebutton().click();
			log.info("Engineering involvement saved");
			//Reject
			basic.projectname_Reviews();
			commonUtils.blindWait();
			ab.getComments().sendKeys("Engineering involvement rejection");
			ab.getRejectbutton().click();
			login.logout();
		}
		if(location.equals("South East")){
			log.info("Commercial Involvement path");
			login.loginCL();
			//cancel
			ab.getReviewinvolvetasks().sendKeys(Keys.RETURN);	 
			basic.projectname_Reviews();
			commonUtils.blindWait();
			ab.getCancelbutton().click();
			//save
			basic.projectname_Reviews();
			commonUtils.blindWait();
			ab.getComments().sendKeys("Commercial involvement");
			ab.getSavebutton().click();
			log.info("Commercial involvement saved");

			//Reject
			basic.projectname_Reviews();
			commonUtils.blindWait();
			ab.getComments().sendKeys("Commercial involvement rejection");
			ab.getRejectbutton().click();
			login.logout();
		}
		monorail.prepare_Quote();
		commonUtils.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),ev.exeCP2);
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();

		basic.pathdession(estimatedSize, location);
		//**********CP2 exe dession**************
		if((ev.ourformat.equals("No"))||(ev.bidsheetauthorised.equals("No"))||(ev.exeCP2.equals("Yes"))){
			basic.boardApproval();
		}
		monorail.submitQuote();
		monorail.statusofSubmitQuote(ev.customerCommitmentType,ev.quoteStatusAmendBid);

		monorail.prepareQuotecp2cp3();
		commonUtils.selectByVisibleText(prepare_Quoteui.getExecp3(),ev.exeCP3);
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();

		if(estimatedSize.equals("C 250-500k")){
			log.info("Engineering Involvement path");
			login.loginEL();
			Thread.sleep(1000);
			ab.getReviewinvolvetasks().sendKeys(Keys.RETURN); 
			basic.projectname_Reviews();
			commonUtils.blindWait();
			ab.getCancelbutton().click();

			basic.projectname_Reviews();
			commonUtils.blindWait();
			ab.getComments().sendKeys("Engineering involvement save...");
			ab.getSavebutton().click();
			log.info("Engineering involvement saved");
			//Reject engineering involvement Need to wright new prepare quote
			basic.projectname_Reviews();
			commonUtils.blindWait();
			ab.getComments().sendKeys("Engineering involvement rejection");
			ab.getRejectbutton().click();
			login.logout();
		}
		if(location.equals("South East")){
			log.info("Commercial Involvement path");
			login.loginCL();

			ab.getReviewinvolvetasks().sendKeys(Keys.RETURN);	 
			basic.projectname_Reviews();
			commonUtils.blindWait();
			ab.getCancelbutton().click();

			basic.projectname_Reviews();
			commonUtils.blindWait();
			ab.getComments().sendKeys("Commercial involvement");
			ab.getSavebutton().click();
			log.info("Commercial involvement saved");

			//Reject commercial involvement Need to wright new prepare quote
			basic.projectname_Reviews();
			commonUtils.blindWait();
			ab.getComments().sendKeys("Commercial involvement rejection");
			ab.getRejectbutton().click();
			login.logout();
		}
		login.loginSL();
		monorail.prepareQuotecp2cp3();
		commonUtils.selectByVisibleText(prepare_Quoteui.getExecp3(),ev.exeCP3);
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();
		basic.pathdessioncp2cp3(ev.estimatedSize, ev.location);
		log.info("review actions are sucessfull...");

	}
}
