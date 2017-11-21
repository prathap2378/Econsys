package com.econsys.testCases;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import POC.econsys.Listener;

import com.econsys.Genriclibrery.CommonUtils;
import com.econsys.Genriclibrery.Driver;
import com.econsys.Genriclibrery.EconsysVariables;
import com.econsys.Listeners.TestListener;
import com.econsys.Projects.*;
import com.econsys.SmallWorks.ProjectMethods_Small_Works;
import com.econsys.SmallWorks.SmallWorks;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.ActionButtonsUi;
import com.econsys.UIobjectrepositary.CosCommitQuoteStatusUi;
import com.econsys.UIobjectrepositary.Preparequote;
import com.econsys.UIobjectrepositary.smallWorkPageElements;

/**
 * @author bhanu.pk
 *This class consists of all the methods of Do not proceed and Reject of all tasks 
 */
public class ActionButtonsCPApprovalsSW extends Driver{
	CommonUtils commonutils = PageFactory.initElements(Driver.driver() , CommonUtils.class);
	ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	CosCommitQuoteStatusUi ccq_Ui=PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	Preparequote quote_form = PageFactory.initElements(Driver.driver(), Preparequote.class);
	private static Logger log=Logger.getLogger(ActionButtonsCPApprovalsSW.class.getName());
	smallWorkPageElements sWp= PageFactory.initElements(Driver.driver(),smallWorkPageElements.class);

	Login login = new Login();
	TestListener name = new TestListener();
	Basic b = new Basic();
	Workbook wb= new Workbook();
	SmallWorks sw = new SmallWorks();
	Monorail monorail = new Monorail();
	TaskCP3CP4 taskCP3_CP4 = new TaskCP3CP4();
	TasksCP4toCP5 cp4_cp5 = new TasksCP4toCP5();
	ProjectMethods_Small_Works swMethods = new ProjectMethods_Small_Works();
	EconsysVariables ev=new EconsysVariables();


	//Do not proceed scenario from CP2 to CP5
	@Test
	public void cp2_doNotProceed() throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException {
		//DonotProceed donotproceed = new DonotProceed(); 

		swMethods.smallWorkForm2();
		commonutils.selectByVisibleText(quote_form.getExpliciteapprovalatgateway2(), "Yes");
		ab.getSubmitbutton().click();
		login.logout();
		//user board to Reject
		login.loginboard();
		b.projectname_ReviewApproval();
		ab.getComments().sendKeys("Reject @ CP2");
		ab.getRejectbutton().click();
		login.logout();

		//User login Sales leader to submit
		login.loginSL();
		b.projectname();
		commonutils.selectByVisibleText(quote_form.getBidSheetAuthorised(), "Yes");
		ab.getComments().sendKeys("Quote form small works");
		commonutils.selectByVisibleText(quote_form.getExpliciteapprovalatgateway2(),"Yes");
		ab.getSubmitbutton().click();
		login.logout();
		//User login as board to do do not proceed
		login.loginboard();
		b.projectname_ReviewApproval();
		ab.getComments().sendKeys("Do not proceed at CP2");
		ab.getDonotproccedbutton().click();
		String allert = driver.findElement(By.xpath("//div[@class='modal-body']")).getTagName();
		commonutils.blindWait();
		ab.getDonot_Proceed_allret_Ok().click();
		System.out.println(allert+"project deleted");
		//donotproceed.search();
		Thread.sleep(200);
		if(driver.findElement(By.xpath("//div[contains(text(),'Your request has been processed successfully ')]")).isDisplayed()){
			log.info("pass cp2 do not proceed");
		}
		login.logout();
	}

	@Test(priority=1)
	public void cp3_doNotProceed() throws IOException, InterruptedException, AWTException, ClassNotFoundException, SQLException {
		//Do_not_Proceed_and_Reject donotproceed = new Do_not_Proceed_and_Reject();  
		login.url();
		swMethods.smallWorkForm2();
		//commonutils.wait(500);
		commonutils.selectByVisibleText(quote_form.getExpliciteapprovalatgateway2(), "No");
		ab.getSubmitbutton().click();
		login.logout();

		monorail.submitQuote();

		//login.loginSL();
		b.projectname();
		Thread.sleep(200);
		commonutils.selectByVisibleText(driver.findElement(By.xpath("//select[@id='st_quoteStatus']")),"Amend Bid");
		ab.getComments().sendKeys("Amend bid");
		ab.getSubmitbutton().click();
		//login as Sales leader to resubmit the quote form
		monorail.prepareQuotecp2cp3();
		commonutils.selectByVisibleText(quote_form.getExecp3(),"Yes");
		quote_form.getQuoteprepared().click();
		login.logout();

		//login as Board user to reject at cp3
		login.loginboard();
		b.projectname_ReviewApproval();
		ab.getComments().sendKeys("Reject @ cp3");
		ab.getRejectbutton().click();
		login.logout();

		//login as Sales leader to resubmit the quote form
		login.loginSL();
		monorail.prepareQuotecp2cp3();
		commonutils.selectByVisibleText(quote_form.getExecp3(),"Yes");
		quote_form.getQuoteprepared().click();
		login.logout();

		login.loginboard();
		b.projectname_ReviewApproval();
		ab.getComments().sendKeys("Do not proceed at CP3");
		ab.getDonotproccedbutton().click();
		String allert = driver.findElement(By.xpath("//div[@class='modal-body']")).getTagName();
		Thread.sleep(500);
		ab.getDonot_Proceed_allret_Ok().click();
		System.out.println(allert+"project deleted");
		Thread.sleep(200);
		if(driver.findElement(By.xpath("//div[contains(text(),'Your request has been processed successfully ')]")).isDisplayed()){
			log.info("pass cp3 do not proceed");
		}
		login.logout();
	}
	@Test(priority=2)
	public void cp4_doNotProceed() throws IOException, InterruptedException, AWTException, ClassNotFoundException, SQLException {
		ActionButtonsCPApprovalsSW donot_proceed = new ActionButtonsCPApprovalsSW();
		login.url();
		swMethods.smallWorkForm2();
		commonutils.selectByVisibleText(quote_form.getExpliciteapprovalatgateway2(), "No");
		ab.getSubmitbutton().click();
		login.logout();

		donot_proceed.submitQuote_CP4();
	}
	@Test(priority=3)
	public void cp5_doNotProceed() throws IOException, InterruptedException, AWTException, ClassNotFoundException, SQLException {
		log.info("cp5_doNotProceed");
		swMethods.smallWorkForm2();
		commonutils.selectByVisibleText(quote_form.getExpliciteapprovalatgateway2(), "No");
		ab.getSubmitbutton().click();
		login.logout();
		//monorail.board();
		monorail.submitQuote();
		b.projectname();
		commonutils.waitForPageToLoad();
		commonutils.selectByVisibleText(ccq_Ui.getQuoteStatus(),"Customer Commitment Received");
		commonutils.waitForPageToLoad();
		commonutils.selectByVisibleText(ccq_Ui.getCustomerCommitmentType(),"Sub-Contract Received - Under Review");
		ccq_Ui.getUploadDoc_StatusofSubmitQuote().click();
		ab.getLinkFileCheckbox().click();
		ab.getAdd_LinkfilePopup().click();
		ab.getComments().sendKeys("Customer Commitment recived-PO Received - Under Review");
		ab.getSubmitbutton().click();
		taskCP3_CP4.customercommit();
		//Cp4 explicit Approval
		commonutils.selectByVisibleText(ccq_Ui.getExeCP4(), "No");
		ccq_Ui.getSubmit().click();
		login.logout();

		//monorail.boardcp4();

		cp4_cp5.apointkeystaf();

		cp4_cp5.commerSuit();
		//submit sales to operaton
		swMethods.salesToOperation();
		commonutils.selectByVisibleText(sWp.getExe_cp5_sw(),"Yes");
		sWp.getSubmitBtn().click();
		login.logout();

		//Operation Acceptance reject Project leader
		swMethods.operation_acceptance();
		ab.getRejectbutton().click();
		login.logout();
		//submit sales to operaton
		login.loginSL();
		b.edit_Salesto_Operation();
		ab.getComments().sendKeys("Sales to Operation handover");
		ab.getSubmitbutton().click();
		login.logout();

		swMethods.operation_acceptance();
		//exeCP5=wb.getXLData(8, 9, 0);
		commonutils.selectByVisibleText(sWp.getExe_cp5_sw_OperatonAcceptance(),"Yes");
		ab.getAcceptOperationAcceptance().click();
		login.logout();

		//Board Reject @ cp5
		login.loginboard();
		b.projectname_ReviewApproval();
		ab.getComments().sendKeys("Do not proceed at CP5");
		ab.getRejectbutton().click();
		login.logout();
		//submit sales to operaton
		login.loginSL();
		b.edit_Salesto_Operation();
		ab.getComments().sendKeys("Sales to Operation handover");
		ab.getSubmitbutton().click();
		login.logout();

		swMethods.operation_acceptance();
		//exeCP5=wb.getXLData(8, 9, 0);
		commonutils.selectByVisibleText(sWp.getExe_cp5_sw_OperatonAcceptance(),"Yes");
		ab.getAcceptOperationAcceptance().click();
		login.logout();

		login.loginboard();
		b.projectname_ReviewApproval();
		ab.getComments().sendKeys("Do not proceed at CP5");
		ab.getDonotproccedbutton().click();
		String allert = driver.findElement(By.xpath("//div[@class='modal-body']")).getTagName();
		Thread.sleep(1000);
		ab.getDonot_Proceed_allret_Ok().click();
		System.out.println(allert+"project deleted");

		Thread.sleep(200);
		if(driver.findElement(By.xpath("//div[contains(text(),'Your request has been processed successfully ')]")).isDisplayed()){
			log.info("pass cp5 do not proceed");
		}
		login.logout();
	}

	public void submitQuote_CP4() throws IOException, InterruptedException{
		monorail.submitQuote();
		//login.loginSL();
		b.projectname();
		commonutils.waitForPageToLoad();
		commonutils.selectByVisibleText(ccq_Ui.getQuoteStatus(),"Customer Commitment Received");

		//Customer Commitment recived
		commonutils.waitForPageToLoad();
		commonutils.selectByVisibleText(ccq_Ui.getCustomerCommitmentType(),"PO Received - Under Review");
		ccq_Ui.getUploadDoc_StatusofSubmitQuote().click();
		ab.getLinkFileCheckbox().click();
		ab.getAdd_LinkfilePopup().click();
		ab.getComments().sendKeys("Customer Commitment recived-PO Received - Under Review");
		ab.getSubmitbutton().click();
		//Customer Commitment
		taskCP3_CP4.customercommit();
		//Cp4 explicit Approval
		commonutils.selectByVisibleText(ccq_Ui.getExeCP4(), "Yes");
		ccq_Ui.getSubmit().click();
		login.logout();
		//Cancel
		login.loginboard();
		b.projectname_ReviewApproval();
		ab.getCancelbutton().click();
		//Save
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[@id='groupApprovals']")).click();
		commonutils.waitForPageToLoad();
		driver().findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]/td/a[contains(text(),'Open')]")).click();
		ab.getComments().sendKeys("Save CP4 form");
		ab.getSavebutton().click();
		//Reject
		commonutils.waitForPageToLoad();
		driver.findElement(By.xpath("//label[@id='myApprovals']")).click();
		commonutils.waitForPageToLoad();
		driver().findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]/td/a[contains(text(),'Open')]")).click();
		ab.getComments().sendKeys("Reject @ CP4");
		ab.getRejectbutton().click();
		login.logout();

		//login as Sales leader to re-submit customer commitment task(CCA)
		login.loginSL();
		b.projectname();
		ab.getComments().sendKeys("Customet commecement submit Slaes");
		commonutils.selectByVisibleText(ccq_Ui.getExeCP4(), "Yes");
		ccq_Ui.getSubmit().click();
		login.logout();

		//login as Operational leader to re-submit customer commitment task(CCA)
		login.loginOD();
		b.projectname();
		ab.getComments().sendKeys("Customet commecement submit Ops");
		commonutils.selectByVisibleText(ccq_Ui.getExeCP4(), "Yes");
		ccq_Ui.getSubmit().click();
		login.logout();

		//CP4 board
		login.loginboard();
		b.projectname_ReviewApproval();
		ab.getComments().sendKeys("Do not proceed at CP4");
		ab.getDonotproccedbutton().click();
		String allert = driver.findElement(By.xpath("//div[@class='modal-body']")).getTagName();
		Thread.sleep(500);
		ab.getDonot_Proceed_allret_Ok().click();
		System.out.println(allert+"project deleted");
		Thread.sleep(200);
		if(driver.findElement(By.xpath("//div[contains(text(),'Your request has been processed successfully ')]")).isDisplayed()){
			log.info("pass cp4 do not proceed");
		}
		login.logout();
	}
}
