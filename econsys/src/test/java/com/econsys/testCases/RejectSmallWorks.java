package com.econsys.testCases;

import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.econsys.Genriclibrery.CommonUtils;
import com.econsys.Genriclibrery.Driver;
import com.econsys.Projects.*;
import com.econsys.SmallWorks.SmallWorks;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.ActionButtonsUi;
import com.econsys.UIobjectrepositary.CosCommitQuoteStatusUi;
import com.econsys.UIobjectrepositary.Preparequote;

/**
 * @author bhanu.pk
 *Reject small works class contains the rejection of task at each board approval 
 *ie is cp2, cp3, cp4 and cp5, cp6, cp8, cp9
 */
public class RejectSmallWorks extends Driver{
	
	ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	CommonUtils cu = PageFactory.initElements(Driver.driver() , CommonUtils.class);
	Preparequote quoteform = PageFactory.initElements(Driver.driver(), Preparequote.class);
	Preparequote pq=PageFactory.initElements(Driver.driver(), Preparequote.class);
	CosCommitQuoteStatusUi ccq=PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	//Imported class
	Login login = new Login();
	SmallWorks sw= new SmallWorks();
	Basic b = new Basic();
	Workbook wb = new Workbook();
	Monorail m =new Monorail();
	String exeCP2;
	String filepath=System.getProperty("user.dir");
	
//@Test
public void cp2_Reject() throws IOException, InterruptedException, AWTException {
	
	//Quote form details small works  
	  sw.quoteForm();
	  cu.selectByVisibleText(quoteform.getExpliciteapprovalatgateway2(),"Yes");
	  ab.getSubmitbutton().click();
	  login.logout();
	  
	  //Login as board
	  login.loginboard();
	  b.projectname_ReviewApproval();
	  Thread.sleep(500);
	  ab.getComments().sendKeys("Reject this project at CP2");
	  ab.getRejectbutton().click();
	  
	  
	  Thread.sleep(200);
	  if(driver.findElement(By.xpath("//div[contains(text(),'Your request has been processed successfully ')]")).isDisplayed()){
		  //log.info("pass cp5 do not proceed");
	  }
	  login.logout();
	  /*login.user();
	  ab.getViewalltasks().click();
	  b.projectname();
	  Thread.sleep(500);
	  projectName1 = driver.findElement(By.xpath("//input[@id='st_ProjectName']")).getAttribute("value");
	  System.out.println(projectName1+"project Rejected at CP2");
	  Assert.assertEquals(projectName1, projectName);*/
  }
  
//@Test(priority=1)
public void cp3_Reject() throws IOException, InterruptedException, AWTException{
	  //sw.quoteForm();
	  //cu.wait(500);
	login.url();
		login.loginSL();
		ab.getViewalltasks().click();
		cu.waitForPageToLoad();
		Thread.sleep(500);
		String nprjname = "Reject CP2_SW 2";
		String exactXpathForProject = "//tr[td[@title='"+nprjname+"']]/td[10]/a[text()='Open']";
		driver().findElement(By.xpath(exactXpathForProject)).click();
		cu.selectByVisibleText(quoteform.getBidSheetAuthorised(), "Yes");
		ab.getComments().sendKeys("ok submit again");

	  ab.getSubmitbutton().click();
	  login.logout();
	  
	  //monorail.board();
	  m.submitQuote();
	  
	  //login.loginSL();
	  b.edit_StatusofSubmitedQuote();
	  Thread.sleep(200);
	  cu.selectByVisibleText(driver.findElement(By.xpath("//select[@id='st_quoteStatus']")),"Amend Bid");
	  ab.getComments().sendKeys("Amend bid");
	  ab.getSubmitbutton().click();
	  m.prepareQuotecp2cp3();
	  login.loginboard();
	  b.projectname_ReviewApproval();
	  ab.getComments().sendKeys("Reject @ CP3");
	  ab.getRejectbutton().click();
	  System.out.println("rejected");
	  Thread.sleep(200);
	  if(driver.findElement(By.xpath("//div[contains(text(),'Your request has been processed successfully ')]")).isDisplayed()){
		 // log.info("pass cp3 do not proceed");
	  }
	  login.logout();
  }
@Test(priority=2)
public void cp4_Reject() throws IOException, InterruptedException{
	login.url();
	login.loginSL();
	String cp2cp3ourformat=wb.getXLData(13, 5, 0);
	String cp2cp3bidsheetauthorised=wb.getXLData(15,5, 0);
	 cu.waitForPageToLoad();		 	 
	 ab.getViewalltasks().click();
	 String nprjname = "File upload SW";
	 driver.findElement(By.xpath("//tr[td[@title='"+nprjname+"']]/td[10]/a[text()='Open']")).click();
	 
	 cu.selectByVisibleText(pq.getCp2cp3gaformat(), cp2cp3ourformat);
	 /*pq.getCp2cp3riskregisterdoc().sendKeys(filepath+"\\Documentsuploded\\cp2-ap3 Risk Register (Example).xlsx");
	 pq.getCp2cp3quotedoc().sendKeys(filepath+"\\Documentsuploded\\cp2-cp3 quote doc.xlsx");*/
	 
	 driver().findElement(By.xpath("//div[@id='upl_fl_flm_bidSheet0']/div[2]/a")).click();
	 pq.getCp2cp3biddoc().click();
	 ab.getLinkFileCheckbox().click();
	 ab.getAdd_LinkfilePopup().click();
	 cu.selectByVisibleText(pq.getCp2cp3bidsheet(), cp2cp3bidsheetauthorised);
	 ab.getComments().sendKeys("cp2-cp3 prepare quote");
	 cu.selectByVisibleText(pq.getExecp3(),"No");
	 pq.getQuoteprepared().click();
	 login.logout();
	 
	 m.submitQuote();
	 login.logout();
	// m.statusQuoteresubmit("PO Received - Under Review");
	 
	//Cp4 explicit Approval
	 cu.selectByVisibleText(ccq.getExeCP4(), "Yes");
	 ccq.getSubmit().click();
	 login.logout();
	  
	 //CP4 board 
	  login.loginboard();
	  b.projectname_ReviewApproval();
	  ab.getComments().sendKeys("Reject @ CP4");
	  ab.getRejectbutton().click();

	  Thread.sleep(200);
	  if(driver.findElement(By.xpath("//div[contains(text(),'Your request has been processed successfully ')]")).isDisplayed()){
		//  log.info("pass cp4 do not proceed");
	  }
	  login.logout();
}
  }
  

