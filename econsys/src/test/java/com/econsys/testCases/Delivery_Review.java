package com.econsys.testCases;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.econsys.Genriclibrery.*;
import com.econsys.Projects.*;
import com.econsys.SmallWorks.*;
import com.econsys.UIobjectrepositary.*;

/**
 * @author bhanu.pk
 * Delivery review all actions Submit, Monthly review and Project completed
 */
public class Delivery_Review extends Driver{
	
	Logger log = Logger.getLogger(Delivery_Review.class.getName());
	CommonUtils commonutils = PageFactory.initElements(Driver.driver() , CommonUtils.class);
	ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	CosCommitQuoteStatusUi ccq=PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	Preparequote quote_form = PageFactory.initElements(Driver.driver(), Preparequote.class);
	smallWorkPageElements sWp= PageFactory.initElements(Driver.driver(),smallWorkPageElements.class);
	ProjectMethods_Small_Works project_Methods_small_Works = new ProjectMethods_Small_Works();
	PDPui pdp_Ui=PageFactory.initElements(Driver.driver(),PDPui.class);
	Login login = new Login();
	SmallWorks sw = new SmallWorks();
	Monorail monorail = new Monorail();
	Basic b = new Basic();
	TaskCP3CP4 cuc = new TaskCP3CP4();
	TasksCP4toCP5 cp4_cp5 = new TasksCP4toCP5();
	ProjectMethods_Small_Works swMethods = new ProjectMethods_Small_Works();
	
  @Test
  public void submit_inDeliveryReview() throws InterruptedException, IOException, AWTException, ClassNotFoundException, SQLException{
	  login.url();
	  project_Methods_small_Works.smallworkForm();
	  
	  //monorail.board();
	  monorail.submitQuote();
	  b.edit_StatusofSubmitedQuote();
	  commonutils.waitForPageToLoad();
	  commonutils.selectByVisibleText(ccq.getQuoteStatus(),"Customer Commitment Received");
	  commonutils.waitForPageToLoad();
	  commonutils.selectByVisibleText(ccq.getCustomerCommitmentType(),"Sub-Contract Received - Under Review");
	  ccq.getUploadDoc_StatusofSubmitQuote().click();
	  ab.getLinkFileCheckbox().click();
	  ab.getAdd_LinkfilePopup().click();
	  ab.getComments().sendKeys("Customer Commitment recived-PO Received - Under Review");
	  ab.getSubmitbutton().click();
	  cuc.customercommit();
	  //Cp4 explicit Approval
	  commonutils.selectByVisibleText(ccq.getExeCP4(), "No");
	  ccq.getSubmit().click();
	  login.logout();
	  
	  //monorail.boardcp4();
	  
	  cp4_cp5.apointkeystaf();
	  
	  cp4_cp5.commerSuit();
	  //submit sales to operaton
	  ProjectMethods_Small_Works.salesToOperation();
	  commonutils.selectByVisibleText(sWp.getExe_cp5_sw(),"No");
      sWp.getSubmitBtn().click();
      login.logout();
      //Operations Acceptance
      ProjectMethods_Small_Works.operation_acceptance();
      //exeCP5=wb.getXLData(8, 9, 0);
      commonutils.selectByVisibleText(sWp.getExe_cp5_sw_OperatonAcceptance(),"No");
      driver.findElement(By.xpath("//input[@id='approve']")).click();
	  login.logout();
      	  
	  project_Methods_small_Works.pdp();
      //Explicit approval selections and Actions
      commonutils.selectByVisibleText(pdp_Ui.getExecp6(),"No");
      sWp.getSubmitBtn().click();
      Thread.sleep(500);
      login.logout();
      //Delivery review ------ Submit
      project_Methods_small_Works.submit_Delivery_Review();
	  ab.getSubmitbutton().click();
	  login.logout();
	  //login user Operation director and -------Reject
	  login.loginOD();
	  b.projectname_ReviewApproval();
	  ab.getComments().sendKeys("Reject Od");
	  driver.findElement(By.xpath("//input[@value='Reject']")).click();
	  login.logout();
	  //Delivery review ------Monthly review
	  login.loginPL();
	  b.pnDeliveryReview();
	  ab.getComments().sendKeys("Monthly review now");
	  driver().findElement(By.xpath("//input[@value='Review']")).click();
	  login.logout();
	 
	  //login user Operation director and -------Approve
	  ProjectMethods_Small_Works.od_approval();
	  //Delivery review ------Project completed
	  project_Methods_small_Works.project_complted_DeliveryReview();
	  //login user Operation director and -------Approve  
	  ProjectMethods_Small_Works.od_approval();
  }
}
