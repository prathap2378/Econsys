package com.ecosys.imperium;

import java.awt.AWTException;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.econsys.Genriclibrery.*;
import com.econsys.Projects.*;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.*;

public class CP_ApprovalActions_TC_Projects extends Driver {
	//Page factory classes
	ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	public Preparequote prepare_Quoteui=PageFactory.initElements(Driver.driver(), Preparequote.class);
	CosCommitQuoteStatusUi ccq_Ui=PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	private static Logger log=Logger.getLogger(CP_ApprovalActions_TC_Projects.class.getName());
	static PDPui pdp_Ui=PageFactory.initElements(Driver.driver(),PDPui.class);
	static Salestooperation SalestoOperation = PageFactory.initElements(Driver.driver(),Salestooperation.class);
	DeliveryReviewUi deliveryReviewUi = PageFactory.initElements(driver(), DeliveryReviewUi.class);
	//Import classes
	Login login = new Login();
	Basic basic = new Basic();
	Workbook wb = new Workbook();
	Monorail monorail = new Monorail();
	TaskCP3CP4 taskCP3_CP4 = new TaskCP3CP4();
	CP_ApprovalActions_TC_SmallWorks donot_proceed = new CP_ApprovalActions_TC_SmallWorks();
	EconsysVariables ev = new EconsysVariables();
	TasksCP4toCP5 cp4_cp5 = new TasksCP4toCP5();
	TasksCP5toCP9 cp5_cp9 = new TasksCP5toCP9();
	Imperium_Project_Methods imperiumProject_methods = new Imperium_Project_Methods();
	Imperium_SmallWorks_Methods imperium_SmallWorks_Methods = new Imperium_SmallWorks_Methods(); 
	CommonUtils commonUtils = new CommonUtils();
	CustomerCommitmentAcceptance_TC_Projects cca_TC_Projects = new CustomerCommitmentAcceptance_TC_Projects();
	//Deliveryreview_ProjectCompleted deliveryReview = new Deliveryreview_ProjectCompleted();

	@Test(priority=0)
	public void reject_DonotPro_CP1() throws IOException, InterruptedException, AWTException {
		login.url();
		commonUtils.waitForPageToLoad();
		login.user();
		imperiumProject_methods.rtqForm(ev.estimatedSize500_,ev.location_inside);
		imperiumProject_methods.submit_logout_QRnumberAlert();

		//login board user--Cancel
		login.loginboard();
		basic.projectname_ReviewApproval();
		ab.getCancelbutton().click();
		//Save
		Thread.sleep(500);
		basic.projectName_Board_ByGroupApprovals();
		ab.getComments().sendKeys("Save CP1 form");
		ab.getSavebutton().click();
		//Reject
		commonUtils.waitForPageToLoad();
		basic.projectName_Board_Byme();
		ab.getComments().sendKeys("Reject at cp1");
		driver.findElement(By.xpath("//input[@id='reject']")).click();
		login.logout();
		//login as user submitted rtq form
		login.loginSL();
		basic.projectname();
		ab.getComments().sendKeys("submit again");
		basic.submit_Logout();

		//login board user----Do not proceed
		login.loginboard();
		basic.projectname_ReviewApproval();
		ab.getComments().sendKeys("Do non proceed @ cp1");
		ab.getDonotproccedbutton().click();
		Thread.sleep(500);
		ab.getDonot_Proceed_allret_Ok().click();
		login.logout();
	}

	@Test(priority=1)
	public void reject_DonotPro_CP2() throws IOException, InterruptedException, AWTException {
		login.loginSL();
		//RTQ form
		imperiumProject_methods.rtqForm(ev.estimatedSize0to100k_,ev.location_inside);
		imperiumProject_methods.submit_logout_QRnumberAlert();

		//Assign Sales Leader
		imperiumProject_methods.ASL();
		//Prepare quote
		imperiumProject_methods.prepare_Quote(ev.overallSell,ev.locationrtq2);
		commonUtils.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),"Yes");
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();

		//login board user--Cancel  
		login.loginboard();
		basic.projectname_ReviewApproval();
		ab.getCancelbutton().click();
		//Save
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[@id='groupApprovals']")).click();
		driver().findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]/td/a[contains(text(),'Open')]")).click();
		ab.getComments().sendKeys("Save CP2 form");
		ab.getSavebutton().click();
		//Reject
		driver.findElement(By.xpath("//label[@id='myApprovals']")).click();
		driver().findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]/td/a[contains(text(),'Open')]")).click();
		ab.getComments().sendKeys("Reject at cp2");
		ab.getRejectbutton().click();
		login.logout();

		//login as user submitted prepare quote
		imperiumProject_methods.prepare_Quote2();
		commonUtils.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),"Yes");
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();

		//login board user----Do not proceed
		login.loginboard();
		basic.projectname_ReviewApproval();
		ab.getComments().sendKeys("Do non proceed @ cp2");
		ab.getDonotproccedbutton().click();
		commonUtils.blindWait();
		ab.getDonot_Proceed_allret_Ok().click();
		login.logout();

	}
	@Test(priority=2)
	public void reject_DonotPro_CP3() throws IOException, InterruptedException, AWTException {

		cca_TC_Projects.rtq_submitQuote();
		basic.edit_StatusofSubmitedQuote();
		Thread.sleep(200);
		commonUtils.selectByVisibleText(driver.findElement(By.xpath("//select[@id='st_quoteStatus']")),ev.quoteStatusAmendBid);
		ab.getComments().sendKeys("Amend bid");
		ab.getSubmitbutton().click();
		//cp2-cp3 prepare quote
		imperiumProject_methods.prepareQuotecp2cp3(ev.locationrtq3);
		commonUtils.selectByVisibleText(prepare_Quoteui.getExecp3(),"Yes");
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();
		//login board user--Cancel  
		login.loginboard();
		basic.projectname_ReviewApproval();
		ab.getCancelbutton().click();
		//Save
		driver.findElement(By.xpath("//label[@id='groupApprovals']")).click();
		driver().findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]/td/a[contains(text(),'Open')]")).click();
		ab.getComments().sendKeys("Save CP3 form");
		ab.getSavebutton().click();
		//Reject
		driver.findElement(By.xpath("//label[@id='myApprovals']")).click();
		driver().findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]/td/a[contains(text(),'Open')]")).click();
		ab.getComments().sendKeys("Reject at cp3");
		ab.getRejectbutton().click();
		login.logout();

		//login as user submitted re-prepare quote
		login.loginSL();
		imperiumProject_methods.prepareQuotecp2cp3(ev.locationrtq3);
		commonUtils.selectByVisibleText(prepare_Quoteui.getExecp3(),"Yes");
		ab.getComments().sendKeys("submit again");
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();

		//login board user----Do not proceed
		login.loginboard();
		basic.projectname_ReviewApproval();
		ab.getComments().sendKeys("Do non proceed @ cp3");
		ab.getDonotproccedbutton().click();
		Thread.sleep(500);
		ab.getDonot_Proceed_allret_Ok().click();
		login.logout();
	}
	@Test(priority=3)
	public void reject_DonotPro_CP4() throws IOException, InterruptedException, AWTException {
		cca_TC_Projects.rtq_submitQuote();
		//'Submit Quote and Status of Submit Quote'
		donot_proceed.actions_CP4();
	}
	@Test(priority=4)
	public void reject_DonotPro_CP5() throws IOException, InterruptedException, AWTException {

		cca_TC_Projects.rtq_submitQuote();

		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType, ev.quoteStatusCCARecived);
		String cp4 = ev.execp4; cp4="No"; String clarification = ev.clarification; clarification="Yes";
		if(cp4.equals("Yes") || (clarification.equals("No") &&
				(ev.customerCommitmentType.equalsIgnoreCase(ev.customerCommitmentType_PO) || 
						ev.customerCommitmentType.equalsIgnoreCase(ev.customerCommitmentType_SubCon)))){
			basic.boardApproval();
		}
		cp4_cp5.submitResponse();
		//Appoint Key staff
		imperium_SmallWorks_Methods.apointkeystaf(ev.selectasProject);

		//commercial suite
		imperium_SmallWorks_Methods.cummercialSuite_();

		//Sales to operation hand-over
		imperiumProject_methods.salestoOperation();
		ab.getSubmitbutton().click();
		login.logout();

		//Operation acceptance "Cancel" by project lead
		login.loginPL();
		basic.edit_OperationAcceptance();
		ab.getCancelbutton().click();
		//Operation acceptance "Save" by project lead 
		basic.edit_OperationAcceptance();
		ab.getComments().sendKeys("Save at Operation acceptance");
		ab.getSavebutton().click();
		//Operation Acceptance 'Reject' by project lead
		basic.edit_OperationAcceptance();
		ab.getComments().sendKeys("Reject at Operation acceptance");
		ab.getRejectbutton().click();
		login.logout();

		//Sales to Operation
		imperiumProject_methods.salestoOperation();
		commonUtils.blindWait();
		commonUtils.selectByVisibleText(SalestoOperation.getExeCP5(),ev.select_Yes);
		ab.getSubmitbutton().click();
		login.logout();

		//Operation acceptance "Approve" by Project lead
		cp4_cp5.operationAcceptance();
		ab.getAcceptOperationAcceptance().click();
		log.info("Approve Operation acceptance");
		login.logout();

		//CP5 "Cancel" by board
		login.loginboard();
		basic.projectname_ReviewApproval();
		ab.getCancelbutton().click();
		//CP5 "Save" by board
		basic.projectName_Board_ByGroupApprovals();
		ab.getComments().sendKeys("Save at cp5");
		ab.getSavebutton().click();
		//CP5 "Reject" by board
		basic.projectName_Board_Byme();
		ab.getComments().sendKeys("Reject at CP5");
		ab.getRejectbutton().click();
		login.logout();

		//Sales to Operation
		cp4_cp5.salestoOperation();
		commonUtils.selectByVisibleText(SalestoOperation.getExeCP5(),ev.select_Yes);
		ab.getSubmitbutton().click();
		login.logout();

		//Operation acceptance
		imperiumProject_methods.operationAcceptance();
		ab.getAcceptOperationAcceptance().click();
		login.logout();
		//CP5 do not proceed operation by board
		login.loginboard();
		basic.projectname_ReviewApproval();
		ab.getComments().sendKeys("Do not proceed at CP5");
		ab.getDonotproccedbutton().click();
		String allert = driver.findElement(By.xpath("//div[@class='modal-body']")).getTagName();
		Thread.sleep(1000);
		ab.getDonot_Proceed_allret_Ok().click();
		System.out.println(allert+"project deleted");
		Thread.sleep(200);
		if(driver.findElement(By.xpath("//div[contains(text(),'Your request has been processed successfully ')]")).isDisplayed()){
			log.info("cp5 do not proceed - Pass");
		}
		login.logout();
	}
	//CP_6_9
	@Test(priority=5)
	public void cancelSaveReject_CP6_to_CP9() throws IOException, InterruptedException, AWTException {

		cca_TC_Projects.rtq_submitQuote();

		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType,ev.quote_StatusCp2Cp3);

		cp4_cp5.submitResponse();
		imperium_SmallWorks_Methods.apointkeystaf(ev.selectasProject);

		imperium_SmallWorks_Methods.cummercialSuite_();

		imperiumProject_methods.salestoOperation();
		commonUtils.blindWait();
		basic.submit_Logout();

		//Operation acceptance "Approve" by Project lead
		imperiumProject_methods.operationAcceptance();
		driver.findElement(By.xpath("//input[@id='approve']")).click();
		login.logout();

		imperiumProject_methods.pdp_();
		commonUtils.selectByVisibleText(pdp_Ui.getExecp6(),ev.select_Yes);
		ab.getSubmitbutton().click();
		commonUtils.waitForPageToLoad();
		login.logout();

		//CP6 "Cancel" by board
		login.loginboard();
		basic.projectname_ReviewApproval();
		ab.getCancelbutton().click();
		//CP6 "Save" by board
		basic.projectName_Board_ByGroupApprovals();
		ab.getComments().sendKeys("Save at cp6");
		ab.getSavebutton().click();
		//CP6 "Reject" by board
		basic.projectName_Board_Byme();
		ab.getComments().sendKeys("Reject at CP6");
		ab.getRejectbutton().click();
		login.logout();
		//PDP
		login.loginPL();
		basic.pnpdp();
		commonUtils.selectByVisibleText(pdp_Ui.getExecp6(),ev.select_No);
		ab.getComments().sendKeys("Submit again");
		ab.getSubmitbutton().click();
		login.logout();
		//CP6 explicit decision
		if(ev.execp6.equals("Yes")){
			basic.boardApproval();
		}

		CP_ApprovalActions_TC_Projects s = new CP_ApprovalActions_TC_Projects();
		s.saveCancelReject_CP7();
		s.saveCancelReject_CP8();
		s.saveCancelReject_CP9();
	}

	public void saveCancelReject_CP7() throws IOException, InterruptedException {

		//deliveryReview.procomplete();
		TasksCP5toCP9.projectCompleted();
		//od Approval cancel by OD
		login.loginOD();
		basic.projectname_ReviewApproval();
		ab.getCancelbutton().click();

		//OD approval "Save" by board
		basic.projectName_Board_ByGroupApprovals();
		ab.getComments().sendKeys("Save at ODapproval");
		ab.getSavebutton().click();

		//OD approval "Reject" by OD
		basic.projectName_Board_Byme();
		ab.getComments().sendKeys("Reject at OD approval");
		ab.getRejectbutton().click();
		login.logout();

		login.loginPL();
		basic.pnDeliveryReview();
		ab.getComments().sendKeys("Project completed");
		ab.getProjectCompletedbutton().click();  

		//Delivery review project completion alert
		commonUtils.blindWait();
		deliveryReviewUi.getDataChange_Alert().click();
		login.logout();

		login.loginOD();
		basic.projectname_ReviewApproval();
		ab.getComments().sendKeys("Approved project complerted");
		ab.getApprove_Button().click();
		login.logout();
	}
	public void saveCancelReject_CP8() throws IOException, InterruptedException {

		imperiumProject_methods.obtainpracticalcomplition();
		commonUtils.selectByVisibleText(pdp_Ui.getOpc_cp8(), "Yes");
		ab.getSubmitbutton().click();
		login.logout();

		//CP8 "Cancel" by board
		login.loginboard();
		basic.projectname_ReviewApproval();
		ab.getCancelbutton().click();
		//CP8 "Save" by board
		basic.projectName_Board_ByGroupApprovals();
		ab.getComments().sendKeys("Save at cp8");
		ab.getSavebutton().click();
		//CP8 "Reject" by board
		basic.projectName_Board_Byme();
		ab.getComments().sendKeys("Reject at CP8");
		ab.getRejectbutton().click();
		login.logout();

		login.loginPL();
		String taskName = PropertiesUtil.getPropValues("obtain_Practical_Completion");
		basic.projectTaskName(taskName);
		ab.getComments().sendKeys("Submit again cp8 approval");
		commonUtils.selectByVisibleText(pdp_Ui.getOpc_cp8(), "Yes");
		ab.getSubmitbutton().click();
		login.logout();

		basic.boardApproval();

	}

	public void saveCancelReject_CP9() throws IOException, InterruptedException {
		imperiumProject_methods.postpracticalcomplition();

		commonUtils.selectByVisibleText(pdp_Ui.getPpc_cp9(), "Yes");
		ab.getSubmitbutton().click();
		pdp_Ui.getProjectSubmitCP9Alert().click();
		login.logout();

		//CP9 "Cancel" by board
		login.loginboard();
		basic.projectname_ReviewApproval();
		ab.getCancelbutton().click();
		//CP9 "Save" by board
		basic.projectName_Board_ByGroupApprovals();
		ab.getComments().sendKeys("Save at cp9");
		ab.getSavebutton().click();
		//CP8 "Reject" by board
		basic.projectName_Board_Byme();
		ab.getComments().sendKeys("Reject at CP9");
		ab.getRejectbutton().click();
		login.logout();

		login.loginPL();
		String taskName = PropertiesUtil.getPropValues("post_Practical_Completion");
		basic.projectTaskName(taskName);
		commonUtils.selectByVisibleText(pdp_Ui.getPpc_cp9(), "Yes");
		ab.getComments().sendKeys("Submit again cp9 approval");
		ab.getSubmitbutton().click();
		login.logout();

		basic.boardApproval();
	}
}