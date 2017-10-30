package com.econsys.testCases;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.econsys.Genriclibrery.*;
import com.econsys.Projects.*;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.*;


public class ActionButtonsCPApprovalsProjects extends Driver {
	//Page factory classes
	CommonUtils cu=PageFactory.initElements(Driver.driver(), CommonUtils.class);
	ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	public Preparequote prepare_Quoteui=PageFactory.initElements(Driver.driver(), Preparequote.class);
	CosCommitQuoteStatusUi ccq_Ui=PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	private static Logger log=Logger.getLogger(ActionButtonsCPApprovalsProjects.class.getName());
	DeliveryReviewUi deliveryReviewUi = PageFactory.initElements(driver(), DeliveryReviewUi.class);
	static PDPui pdp_Ui=PageFactory.initElements(Driver.driver(),PDPui.class);
	
	//Import classes
	Login login = new Login();
	Basic b = new Basic();
	Workbook wb = new Workbook();
	Monorail monorail = new Monorail();
	TaskCP3CP4 taskCP3_CP4 = new TaskCP3CP4();
	ActionButtonsCPApprovalsSW donot_proceed = new ActionButtonsCPApprovalsSW();
	EconsysVariables ev = new EconsysVariables();
	TasksCP4toCP5 cp4_cp5 = new TasksCP4toCP5();
	TasksCP5toCP9 cp5_cp9 = new TasksCP5toCP9();
	//Deliveryreview_ProjectCompleted deliveryReview = new Deliveryreview_ProjectCompleted();
	
@Test(priority=0)
  public void reject_DonotPro_CP1() throws IOException, InterruptedException, AWTException {
	  login.url();
	  cu.waitForPageToLoad();
	  login.user();
	  b.rtqForm(wb.getXLData(3, 3, 1),wb.getXLData(4, 3, 1));
	  b.submit_Logout();
	  
	  //login board user--Cancel
	  login.loginboard();
	  b.projectname_ReviewApproval();
	  ab.getCancelbutton().click();
	  //Save
	  Thread.sleep(500);
	  b.projectName_Board_ByGroupApprovals();
	  ab.getComments().sendKeys("Save CP1 form");
	  ab.getSavebutton().click();
	  //Reject
	  cu.waitForPageToLoad();
	  b.projectName_Board_Byme();
	  ab.getComments().sendKeys("Reject at cp1");
	  driver.findElement(By.xpath("//input[@id='reject']")).click();
	  login.logout();
	  //login as user submitted rtq form
	  login.loginSL();
	  b.projectname();
	  ab.getComments().sendKeys("submit again");
	  b.submit_Logout();
	
	  //login board user----Do not proceed
	  login.loginboard();
	  b.projectname_ReviewApproval();
	  ab.getComments().sendKeys("Do non proceed @ cp1");
	  ab.getDonotproccedbutton().click();
	  Thread.sleep(500);
	  ab.getDonot_Proceed_allret_Ok().click();
	  login.logout();
  	  }
@Test(priority=1)
 public void reject_DonotPro_CP2() throws IOException, InterruptedException, AWTException {
	  login.url();
	  login.loginSL();
	  //RTQ form
	  b.rtqForm(wb.getXLData(9, 3, 1),wb.getXLData(10, 3, 1));
	  b.submit_Logout();
	  monorail.ASL();
	  //Prepare quote
	  monorail.prepare_Quote();
      cu.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),"Yes");
      prepare_Quoteui.getQuoteprepared().click();
      login.logout();
      
      //login board user--Cancel  
      login.loginboard();
	  b.projectname_ReviewApproval();
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
	  monorail.prepare_Quote();
	  ab.getComments().sendKeys("submit again");
	  cu.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),"Yes");
      prepare_Quoteui.getQuoteprepared().click();
	  login.logout();
	  
	  //login board user----Do not proceed
	  login.loginboard();
	  b.projectname_ReviewApproval();
	  ab.getComments().sendKeys("Do non proceed @ cp2");
	  ab.getDonotproccedbutton().click();
	  Thread.sleep(500);
	  ab.getDonot_Proceed_allret_Ok().click();
	  login.logout();
	  
  }
@Test(priority=2)
public void reject_DonotPro_CP3() throws IOException, InterruptedException, AWTException {
	  login.url();
	  login.loginSL();
	  //RTQ form
	  b.rtqForm(wb.getXLData(9, 3, 1),wb.getXLData(10, 3, 1));
	  b.submit_Logout();
	  monorail.ASL();
	  //Prepare quote
	  monorail.prepare_Quote();
      cu.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),"No");
      prepare_Quoteui.getQuoteprepared().click();
      login.logout();
      //Status of submit quote
      monorail.submitQuote();
      //login.loginSL();
	  b.edit_StatusofSubmitedQuote();
	  Thread.sleep(200);
	  cu.selectByVisibleText(driver.findElement(By.xpath("//select[@id='st_quoteStatus']")),"Amend Bid");
	  ab.getComments().sendKeys("Amend bid");
	  ab.getSubmitbutton().click();
      //cp2-cp3 prepare quote
	  monorail.prepareQuotecp2cp3();
      cu.selectByVisibleText(prepare_Quoteui.getExecp3(),"Yes");
      prepare_Quoteui.getQuoteprepared().click();
	  login.logout();
      //login board user--Cancel  
      login.loginboard();
	  b.projectname_ReviewApproval();
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
	  monorail.prepareQuotecp2cp3();
	  cu.selectByVisibleText(prepare_Quoteui.getExecp3(),"Yes");
	  ab.getComments().sendKeys("submit again");
      prepare_Quoteui.getQuoteprepared().click();
	  login.logout();
	  
	  //login board user----Do not proceed
	  login.loginboard();
	  b.projectname_ReviewApproval();
	  ab.getComments().sendKeys("Do non proceed @ cp3");
	  ab.getDonotproccedbutton().click();
	  Thread.sleep(500);
	  ab.getDonot_Proceed_allret_Ok().click();
	  login.logout();
  }
@Test(priority=3)
 public void reject_DonotPro_CP4() throws IOException, InterruptedException, AWTException {
	  login.url();
	  login.loginSL();
	  //RTQ form
	  b.rtqForm(wb.getXLData(9, 3, 1),wb.getXLData(10, 3, 1));
	  b.submit_Logout();
	  monorail.ASL();
	  //Prepare quote
	  monorail.prepare_Quote();
      cu.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),"No");
      prepare_Quoteui.getQuoteprepared().click();
      login.logout();
      //'Submit Quote and Status of Submit Quote'
      donot_proceed.submitQuote_CP4();
  }
 //@Test(priority=4)
  public void reject_DonotPro_CP5() throws IOException, InterruptedException, AWTException {
	  login.url();
	  login.loginSL();
	  //RTQ form
	  b.rtqForm(wb.getXLData(9, 3, 1),wb.getXLData(10, 3, 1));
	  b.submit_Logout();
	  monorail.ASL();
	  //Prepare quote
	  monorail.prepare_Quote();
      cu.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),"No");
      prepare_Quoteui.getQuoteprepared().click();
      login.logout();
      monorail.submitQuote();
	  
      monorail.statusQuotesubmit(ev.customerCommitmentType, ev.quoteStatusCCARecived);
	  
	  cp4_cp5.apointkeystaf();
	  
	  cp4_cp5.commerSuit();
	  
	  cp4_cp5.salestoOperation();
	  cu.blindWait();
	  ab.getSubmitbutton().click();
	  login.logout();
	  
	  //Operation acceptance "Cancel" by project lead
	  login.loginPL();
	  b.edit_OperationAcceptance();
	  ab.getCancelbutton().click();
	  //Operation acceptance "Save" by project lead 
	  b.edit_OperationAcceptance();
	  ab.getComments().sendKeys("Save at Operation acceptance");
	  ab.getSavebutton().click();
	  //Operation Acceptance 'Reject' by project lead
	  b.edit_OperationAcceptance();
	  ab.getComments().sendKeys("Reject at Operation acceptance");
	  ab.getRejectbutton().click();
	  login.logout();
	  
	  //Sales to Operation
	  cp4_cp5.salestoOperation();
	  cu.blindWait();
	  cu.selectByVisibleText(driver.findElement(By.xpath("//select[@id='explicit_approval_sales']")),"Yes");
	  ab.getSubmitbutton().click();
	  login.logout();
	  System.out.println("login with pl");
	  
	  //Operation acceptance "Approve" by Project lead
	  cp4_cp5.operationAcceptance();
	  System.out.println("Approve operation acceptance");
	  ab.getAcceptOperationAcceptance().click();
	  System.out.println("Approve @ OA");
	  login.logout();
	  
	  //CP5 "Cancel" by board
	  login.loginboard();
	  b.projectname_ReviewApproval();
	  ab.getCancelbutton().click();
	  //CP5 "Save" by board
	  b.projectName_Board_ByGroupApprovals();
	  ab.getComments().sendKeys("Save at cp5");
	  ab.getSavebutton().click();
	  //CP5 "Reject" by board
	  b.projectName_Board_Byme();
	  ab.getComments().sendKeys("Reject at CP5");
	  ab.getRejectbutton().click();
	  login.logout();
	  
	  //Sales to Operation
	  cp4_cp5.salestoOperation();
	  cu.blindWait();
	  cu.selectByVisibleText(driver.findElement(By.xpath("//select[@id='explicit_approval_sales']")),"Yes");
	  ab.getSubmitbutton().click();
	  login.logout();
	  
	  //Operation acceptance
	  login.loginPL();
	  b.edit_OperationAcceptance();
	  ab.getComments().sendKeys("Approve at Operation acceptance");
	  driver.findElement(By.xpath("//input[@id='approve']")).click();
	  login.logout();
	  
	  //CP5 do not proceed operation by board
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
//CP_6_9
  @Test(priority=5)
  public void cancelSaveReject_CP6_to_CP9() throws IOException, InterruptedException, AWTException {
	  login.url();
	  login.loginSL();
	  //RTQ form
	  b.rtqForm(ev.estimatedSize,ev.location);
	  b.submit_Logout();
	//***********CP1 exe dession************
			if((ev.estimatedSize.equals("D 500+"))||(ev.location.equals("Other"))) {
				b.boardApproval();
			}
	  monorail.ASL();
	  //Prepare quote
	  monorail.prepare_Quote();
      cu.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),"No");
      prepare_Quoteui.getQuoteprepared().click();
      login.logout();
      
      monorail.submitQuote();
	  
      monorail.statusQuotesubmit(ev.customerCommitmentType,ev.quote_StatusCp2Cp3);
      
	  cp4_cp5.apointkeystaf();
	  
	  cp4_cp5.commerSuit();
	  
	  cp4_cp5.salestoOperation();
	  cu.blindWait();
	  b.submit_Logout();
	  
	  //Operation acceptance "Approve" by Project lead
	  cp4_cp5.operationAcceptance();
	  driver.findElement(By.xpath("//input[@id='approve']")).click();
	  login.logout();
	  
	  cp5_cp9.pdp();
	  cu.selectByVisibleText(pdp_Ui.getExecp6(),"Yes");
	  ab.getSubmitbutton().click();
	  login.logout();
	  
	  //CP6 "Cancel" by board
	  login.loginboard();
	  b.projectname_ReviewApproval();
	  ab.getCancelbutton().click();
	  //CP6 "Save" by board
	  b.projectName_Board_ByGroupApprovals();
	  ab.getComments().sendKeys("Save at cp6");
	  ab.getSavebutton().click();
	  //CP6 "Reject" by board
	  b.projectName_Board_Byme();
	  ab.getComments().sendKeys("Reject at CP6");
	  driver.findElement(By.xpath("//input[@value='Reject']")).click();
	  login.logout();
	  //PDP
	  login.loginPL();
	  b.pnpdp();
	  cu.selectByVisibleText(pdp_Ui.getExecp6(),"Yes");
	  ab.getComments().sendKeys("Submit again");
	  ab.getSubmitbutton().click();
	  login.logout();
	  
	  b.boardApproval();
	  
	  ActionButtonsCPApprovalsProjects s = new ActionButtonsCPApprovalsProjects();
	  s.saveCancelReject_CP7();
	  s.saveCancelReject_CP8();
	  s.saveCancelReject_CP9();
  }
	
  public void saveCancelReject_CP7() throws IOException, InterruptedException {
	  
	  //deliveryReview.procomplete();
	  TasksCP5toCP9.projectCompleted();
	  //od Approval cancel by OD
	  login.loginOD();
	  b.projectname_ReviewApproval();
	  ab.getCancelbutton().click();
	  
	  //OD approval "Save" by board
	  b.projectName_Board_ByGroupApprovals();
	  ab.getComments().sendKeys("Save at ODapproval");
	  ab.getSavebutton().click();
	  
	  //OD approval "Reject" by OD
	  b.projectName_Board_Byme();
	  ab.getComments().sendKeys("Reject at OD approval");
	  ab.getRejectbutton().click();
	  login.logout();
	  
	  login.loginPL();
	  b.pnDeliveryReview();
	  ab.getComments().sendKeys("Project completed");
	  ab.getProjectCompletedbutton().click();  
	  
 	  //Delivery review project completion alert
	  cu.blindWait();
	  deliveryReviewUi.getDataChange_Alert().click();
	  login.logout();
	  
	  login.loginOD();
	  b.projectname_ReviewApproval();
	  ab.getComments().sendKeys("Approved project complerted");
	  ab.getApprove_Button().click();
	  login.logout();
  }
  public void saveCancelReject_CP8() throws IOException, InterruptedException {
	  login.url();
	  
	  cp5_cp9.obtainpracticalcomplition();
	  cu.selectByVisibleText(pdp_Ui.getOpc_cp8(), "Yes");
	  ab.getSubmitbutton().click();
	  login.logout();
	  
	  //CP8 "Cancel" by board
	  login.loginboard();
	  b.projectname_ReviewApproval();
	  ab.getCancelbutton().click();
	  //CP8 "Save" by board
	  b.projectName_Board_ByGroupApprovals();
	  ab.getComments().sendKeys("Save at cp8");
	  ab.getSavebutton().click();
	  //CP8 "Reject" by board
	  b.projectName_Board_Byme();
	  ab.getComments().sendKeys("Reject at CP8");
	  ab.getRejectbutton().click();
	  login.logout();
	  
	  login.loginPL();
	  String taskName = PropertiesUtil.getPropValues("obtain_Practical_Completion");
	  b.projectTaskName(taskName);
	  ab.getComments().sendKeys("Submit again cp8 approval");
	  cu.selectByVisibleText(pdp_Ui.getOpc_cp8(), "Yes");
	  ab.getSubmitbutton().click();
	  login.logout();
	  
	  b.boardApproval();
	  
  }

  public void saveCancelReject_CP9() throws IOException, InterruptedException {
	  cp5_cp9.postpracticalcomplition();
	  
	  cu.selectByVisibleText(pdp_Ui.getPpc_cp9(), "Yes");
	  ab.getSubmitbutton().click();
	  login.logout();
	  
	  //CP9 "Cancel" by board
	  login.loginboard();
	  b.projectname_ReviewApproval();
	  ab.getCancelbutton().click();
	  //CP9 "Save" by board
	  b.projectName_Board_ByGroupApprovals();
	  ab.getComments().sendKeys("Save at cp9");
	  ab.getSavebutton().click();
	  //CP8 "Reject" by board
	  b.projectName_Board_Byme();
	  ab.getComments().sendKeys("Reject at CP9");
	  ab.getRejectbutton().click();
	  login.logout();
	  
	  login.loginPL();
	  String taskName = PropertiesUtil.getPropValues("post_Practical_Completion");
	  b.projectTaskName(taskName);
	  cu.selectByVisibleText(pdp_Ui.getPpc_cp9(), "Yes");
	  ab.getComments().sendKeys("Submit again cp9 approval");
	  ab.getSubmitbutton().click();
	  login.logout();

	  b.boardApproval();
  }

}
