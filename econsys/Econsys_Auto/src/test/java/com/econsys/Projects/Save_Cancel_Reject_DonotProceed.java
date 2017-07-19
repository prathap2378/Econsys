package com.econsys.Projects;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.econsys.Genriclibrery.CommonUtils;
import com.econsys.Genriclibrery.Driver;
import com.econsys.Genriclibrery.EconsysVariables;
import com.econsys.SmallWorks.Donot_Proceed_and_Reject;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.ActionButtonsUi;
import com.econsys.UIobjectrepositary.CosCommitQuoteStatusUi;
import com.econsys.UIobjectrepositary.PDPui;
import com.econsys.UIobjectrepositary.Preparequote;

public class Save_Cancel_Reject_DonotProceed extends Driver {
	//Page factory classes
	CommonUtils cu=PageFactory.initElements(Driver.driver(), CommonUtils.class);
	ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	public Preparequote prepare_Quoteui=PageFactory.initElements(Driver.driver(), Preparequote.class);
	CosCommitQuoteStatusUi ccq_Ui=PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	private static Logger log=Logger.getLogger(Donot_Proceed_and_Reject.class.getName());
	static PDPui pdp_Ui=PageFactory.initElements(Driver.driver(),PDPui.class);
	
	//Import classes
	Login login = new Login();
	Basic b = new Basic();
	Workbook wb = new Workbook();
	Monorail monorail = new Monorail();
	TaskCP3CP4 taskCP3_CP4 = new TaskCP3CP4();
	Donot_Proceed_and_Reject donot_proceed = new Donot_Proceed_and_Reject();
	EconsysVariables ev = new EconsysVariables();
	TasksCP4toCP5 cp4_cp5 = new TasksCP4toCP5();
	TasksCP5toCP9 cp5_cp9 = new TasksCP5toCP9();
	//Deliveryreview_ProjectCompleted deliveryReview = new Deliveryreview_ProjectCompleted();
	
//@Test
  public void reject_DonotPro_CP1() throws IOException, InterruptedException, AWTException {
	  login.url();
	  login.user();
	  b.rtqForm(wb.getXLData(20, 4, 1),wb.getXLData(20, 5, 1));
	  b.submit_Logout();
	  
	  //login board user--Cancel
	  login.loginboard();
	  b.projectname_ReviewApproval();
	  ab.getCancelbutton().click();
	  //Save
	  Thread.sleep(500);
	  driver.findElement(By.xpath("//label[@id='groupApprovals']")).click();
	  cu.waitForPageToLoad();
	  driver().findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]/td[13]//a[contains(text(),'Open')]")).click();
	  ab.getComments().sendKeys("Save CP1 form");
	  ab.getSavebutton().click();
	  //Reject
	  cu.waitForPageToLoad();
	  driver.findElement(By.xpath("//label[@id='myApprovals']")).click();
	  driver().findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]/td[13]//a[contains(text(),'Open')]")).click();
	  ab.getComments().sendKeys("Reject at cp1");
	  driver.findElement(By.xpath("//input[@id='reject_button']")).click();
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
//@Test
 public void reject_DonotPro_CP2() throws IOException, InterruptedException, AWTException {
	  login.url();
	  login.loginSL();
	  //RTQ form
	  b.rtqForm(wb.getXLData(20, 6, 1),wb.getXLData(20, 7, 1));
	  b.submit_Logout();
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
	  driver().findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]/td[13]//a[contains(text(),'Open')]")).click();
	  ab.getComments().sendKeys("Save CP2 form");
	  ab.getSavebutton().click();
	  //Reject
	  driver.findElement(By.xpath("//label[@id='myApprovals']")).click();
	  driver().findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]/td[13]//a[contains(text(),'Open')]")).click();
	  ab.getComments().sendKeys("Reject at cp2");
	  ab.getRejectbutton().click();
	  login.logout();
	
	  //login as user submitted prepare quote
	  login.loginSL();
	  b.projectname();
	  cu.selectByVisibleText(prepare_Quoteui.getBidSheetAuthorised(),"No");
	  ab.getComments().sendKeys("submit again");
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
//@Test
public void reject_DonotPro_CP3() throws IOException, InterruptedException, AWTException {
	  login.url();
	  login.loginSL();
	  //RTQ form
	  b.rtqForm(wb.getXLData(20, 6, 1),wb.getXLData(20, 7, 1));
	  b.submit_Logout();
	  //Prepare quote
	  monorail.prepare_Quote();
      cu.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),"No");
      prepare_Quoteui.getQuoteprepared().click();
      login.logout();
      //Status of submit quote
      monorail.submitandresubmit();
      //login.loginSL();
	  b.projectname();
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
	  driver().findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]/td[13]//a[contains(text(),'Open')]")).click();
	  ab.getComments().sendKeys("Save CP3 form");
	  ab.getSavebutton().click();
	  //Reject
	  driver.findElement(By.xpath("//label[@id='myApprovals']")).click();
	  driver().findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]/td[13]//a[contains(text(),'Open')]")).click();
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
 //@Test
 public void reject_DonotPro_CP4() throws IOException, InterruptedException, AWTException {
	  login.url();
	  login.loginSL();
	  //RTQ form
	  b.rtqForm(wb.getXLData(20, 6, 1),wb.getXLData(20, 7, 1));
	  b.submit_Logout();
	  //Prepare quote
	  monorail.prepare_Quote();
      cu.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),"No");
      prepare_Quoteui.getQuoteprepared().click();
      login.logout();
      //'Submit Quote and Status of Submit Quote'
      donot_proceed.submitQuote_CP4();
  }
 //@Test
  public void reject_DonotPro_CP5() throws IOException, InterruptedException, AWTException {
	  login.url();
	  /*login.loginSL();
	  //RTQ form
	  b.rtqForm(wb.getXLData(20, 6, 1),wb.getXLData(20, 7, 1));
	  b.submit_Logout();
	  //Prepare quote
	  monorail.prepare_Quote();
      cu.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),"No");
      prepare_Quoteui.getQuoteprepared().click();
      login.logout();
      monorail.submitandresubmit();
	  b.projectname();
	  cu.selectByVisibleText(ccq_Ui.getQuoteStatus(),"Customer Commitment Received");
	  cu.selectByVisibleText(ccq_Ui.getCustomerCommitmentType(),"Sub-Contract Received - Under Review");
	  ccq_Ui.getUploadDoc_StatusofSubmitQuote().click();
	  ab.getLinkFileCheckbox().click();
	  ab.getAdd_LinkfilePopup().click();
	  ab.getComments().sendKeys("Customer Commitment recived-Sub-Contract Received - Under Review");
	  ab.getSubmitbutton().click();
	  taskCP3_CP4.customercommit();
	  //Cp4 explicit Approval
	  cu.selectByVisibleText(ccq_Ui.getExeCP4(), "No");
	  ccq_Ui.getSubmit().click();
	  login.logout();
	  
	  cp4_cp5.apointkeystaf();*/
	  
	  cp4_cp5.commerSuit();
	  
	  cp4_cp5.salestoOperation();
	  cu.blindWait();
	  ab.getSubmitbutton().click();
	  login.logout();
	  
	  //Operation acceptance "Cancel" by project lead
	  login.loginPL();
	  b.projectname();
	  ab.getCancelbutton().click();
	  //Operation acceptance "Save" by project lead 
	  b.projectname();
	  ab.getComments().sendKeys("Save at Operation acceptance");
	  ab.getSavebutton().click();
	  //Operation Acceptance 'Reject' by project lead
	  b.projectname();
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
	  driver.findElement(By.xpath("//input[@id='approve']")).click();
	  System.out.println("Approve @ OA");
	  login.logout();
	  
	  //CP5 "Cancel" by board
	  login.loginboard();
	  b.projectname_ReviewApproval();
	  ab.getCancelbutton().click();
	  //CP5 "Save" by board
	  b.projectName_Board_Byme();
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
	  b.projectname();
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
  @BeforeTest
  public void cancelSaveReject_CP6_to_CP9() throws IOException, InterruptedException, AWTException {
	  login.url();
	  login.loginSL();
	  //RTQ form
	  b.rtqForm(wb.getXLData(20,6, 1),wb.getXLData(20, 7, 1));
	  b.submit_Logout();
	  //Prepare quote
	  monorail.prepare_Quote();
      cu.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),"No");
      prepare_Quoteui.getQuoteprepared().click();
      login.logout();
      monorail.submitandresubmit();
	  b.projectname();
	  cu.selectByVisibleText(ccq_Ui.getQuoteStatus(),"Customer Commitment Received");
	  cu.selectByVisibleText(ccq_Ui.getCustomerCommitmentType(),"Sub-Contract Received - Under Review");
	  ccq_Ui.getUploadDoc_StatusofSubmitQuote().click();
	  ab.getLinkFileCheckbox().click();
	  ab.getAdd_LinkfilePopup().click();
	  ab.getComments().sendKeys("Customer Commitment recived-Sub-Contract Received - Under Review");
	  ab.getSubmitbutton().click();
	  taskCP3_CP4.customercommit();
	  //Cp4 explicit Approval
	  cu.selectByVisibleText(ccq_Ui.getExeCP4(), "No");
	  ccq_Ui.getSubmit().click();
	  login.logout();
	  
	  cp4_cp5.apointkeystaf();
	  
	  cp4_cp5.commerSuit();
	  
	  cp4_cp5.salestoOperation();
	  cu.blindWait();
	  ab.getSubmitbutton().click();
	  login.logout();
	  
	  //Operation acceptance "Approve" by Project lead
	  cp4_cp5.operationAcceptance();
	  System.out.println("Approve operation acceptance");
	  driver.findElement(By.xpath("//input[@id='approve']")).click();
	  System.out.println("Approve @ OA");
	  login.logout();
	  
	  cp5_cp9.pdp();
	  cu.selectByVisibleText(pdp_Ui.getExecp6(),"Yes");
	  cu.blindWait();
	  ab.getSubmitbutton().click();
	  cu.blindWait();
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
	  
	  login.loginPL();
	  b.projectname();
	  cu.selectByVisibleText(pdp_Ui.getExecp6(),"Yes");
	  ab.getComments().sendKeys("Submit again");
	  ab.getSubmitbutton().click();
	  login.logout();
	  
	  monorail.boardcp6();
	  
	  Save_Cancel_Reject_DonotProceed s = new Save_Cancel_Reject_DonotProceed();
	  s.saveCancelReject_CP7();
	  s.saveCancelReject_CP8();
	  s.saveCancelReject_CP9();
  }
	
  public void saveCancelReject_CP7() throws IOException, InterruptedException {
	  
	  login.url();
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
	  ab.getRejectButton_ODapproval().click();
	  login.logout();
	  
	  login.loginPL();
	  b.projectname();
	  ab.getComments().sendKeys("Submit again");
	  ab.getSubmitbutton().click();
	  login.logout();
	  
	  login.loginOD();
	  b.projectname_ReviewApproval();
	  ab.getComments().sendKeys("Approved project complerted");
	  ab.getBoardapprovebutton().click();
	  login.logout();
  }
  public void saveCancelReject_CP8() throws IOException, InterruptedException {
	  login.url();
	  
	  cp5_cp9.obtainpracticalcomplition();
	  cu.selectByVisibleText(pdp_Ui.getOpc_cp8(), ev.execp8);
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
	  b.projectname();
	  ab.getComments().sendKeys("Submit again cp8 approval");
	  ab.getSubmitbutton().click();
	  
	  login.loginboard();
	  monorail.boardcp6();
	  
  }

  public void saveCancelReject_CP9() throws IOException, InterruptedException {
	  cp5_cp9.postpracticalcomplition();
	  
	  cu.selectByVisibleText(pdp_Ui.getPpc_cp9(), ev.execp9);
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
	  b.projectname();
	  ab.getComments().sendKeys("Submit again cp9 approval");
	  ab.getSubmitbutton().click();
	  
	  login.loginboard();
	  monorail.boardcp6();
  }

}
