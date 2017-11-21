package com.econsys.testCases;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import POC.econsys.ActionButtons;

import com.econsys.Genriclibrery.CommonUtils;
import com.econsys.Genriclibrery.Driver;
import com.econsys.Genriclibrery.EconsysVariables;
import com.econsys.Projects.Basic;
import com.econsys.Projects.Login;
import com.econsys.Projects.Monorail;
import com.econsys.SmallWorks.ProjectMethods_Small_Works;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.ActionButtonsUi;
import com.econsys.UIobjectrepositary.AllPages;
import com.econsys.UIobjectrepositary.RTQForm_Ui;
import com.econsys.UIobjectrepositary.SadminUi;

public class VerifyDeletion_SamllWorks extends Driver {
	
Logger log=Logger.getLogger(VerifyDeletion_Projects.class.getName());
ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
AllPages allPages= PageFactory.initElements(Driver.driver(), AllPages.class);
RTQForm_Ui nrtq=PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
SadminUi allTabs = PageFactory.initElements(Driver.driver(), SadminUi.class);
Workbook wb = new Workbook();
Login login= new Login();
EconsysVariables ev= new EconsysVariables();
ProjectMethods_Small_Works proMethods = new ProjectMethods_Small_Works();
ActionButtons actionBtns= new ActionButtons();
CommonUtils cu= new CommonUtils();
Basic basic = new Basic();
static Monorail rtq=new Monorail();
ActionButtonsCPApprovalsSW dr= new ActionButtonsCPApprovalsSW();

@Test
//Verification of project deletion in task level
public void smallWorksDelete_Task() throws IOException, InterruptedException, AWTException, ClassNotFoundException, SQLException{

	boolean flag=false;
	
	flag=proMethods.smallworkForm();
	log.info("Flag : "+flag);
	if(flag){
		Reporter.log("Small Works flow changed to Project, end this test");
	}
	else{
		//cp2 board approval
		if((ev.ourformat.equals("No"))||(ev.bidsheetauthorised.equals("No"))){
			basic.boardApproval();
	}
	
	login.loginSL();
	cu.waitForPageToLoad();
	ab.getViewalltasks().click();
	cu.blindWait();
	driver.findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]//div/button[starts-with(@id,'actn-btn-')]")).click();
	cu.blindWait();
	log.info("Option Btn clicked");
	allPages.getDeleteOption().click();
	log.info("Clicked Delete Option");
	cu.blindWait();
	allPages.getOk_PopUpBtn().click();
	cu.waitForPageToLoad();
	//Get coment box ID
	log.info("get coment box id");
	String commentBoxIid = driver.findElement(By.xpath("//td[@title="+ev.projectName()+"]/parent::tr")).getAttribute("id");
	log.info("comments");
	log.info(commentBoxIid);
	driver.findElement(By.xpath(".//textarea[@id='x_cmt_"+commentBoxIid+"']")).sendKeys("Delete this task");

	log.info("cemments loged");
	cu.blindWait();
	driver.findElement(By.xpath("//div[@id='"+commentBoxIid+"_docDiv']//tr/td[1]/a")).click();
	log.info("saved");
		
	login.logout();
	
	//Verify in project archive
	login.user();
	allPages.getProArchLink().click();
	cu.waitForPageToLoad();
	basic.search();
	Assert.assertEquals(driver.findElement(By.xpath("//td[@title='Deleted']")).getText(), "Deleted");
 }
}
@Test
//Verification of project deletion in all projects
public void smallWorksdelete_AllProjects() throws IOException, InterruptedException, AWTException, ClassNotFoundException, SQLException{
	
boolean flag=false;
	
	flag=proMethods.smallworkForm();
	log.info("Flag : "+flag);
	if(flag){
		Reporter.log("Small Works flow changed to Project, end this test");
	}
	else{
		//cp2 board approval
		if((ev.ourformat.equals("No"))||(ev.bidsheetauthorised.equals("No"))){
			basic.boardApproval();
	}
		
	login.user();
	cu.waitForPageToLoad();
	allTabs.getAllProjects().click();
	
	log.info("search all projects");
	basic.search();
	driver.findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]//div/button[starts-with(@id,'actn-btn-')]")).click();
	
	driver.findElement(By.xpath("//td/span[contains(text(),'Delete')]")).click();
	cu.blindWait();
	//Delete alert pop up-okay
	allPages.getOk_PopUpBtn().click();
	cu.blindWait();
	//Get coment box ID
	log.info("get coment box id");
	String commentBoxIid = driver.findElement(By.xpath("//td[@title="+ev.projectName()+"]/parent::tr")).getAttribute("id");
	log.info("comments");
	log.info(commentBoxIid);
	driver.findElement(By.xpath(".//textarea[@id='x_cmt_"+commentBoxIid+"']")).sendKeys("Delete this task");

	log.info("cemments loged");
	cu.blindWait();
	driver.findElement(By.xpath("//div[@id='"+commentBoxIid+"_docDiv']//tr/td[1]/a")).click();
	log.info("saved");
	cu.blindWait();
	allPages.getProArchLink().click();
	cu.waitForPageToLoad();
	basic.search();
	log.info("Searched project");
	Assert.assertEquals(driver.findElements(By.xpath("//td[@title='Deleted']")).get(0).getText(), "Deleted");
	log.info("Asserted");
}
}
@Test
//Delete Saved rtq from savedRTQ grid
public void smallWorksSave_Delete_SavedRTQSW() throws IOException, InterruptedException, AWTException{
	proMethods.smallWorkForm1();
	ab.getSave_RTQ().click();
	login.logout();
	//Admin
	login.user();
	cu.waitForPageToLoad();
	ab.getAdmin().click();
	cu.waitForPageToLoad();
	ab.getSavedRTQ().click();
	//Action button
	driver.findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]//div/button[starts-with(@id,'actn-btn-')]")).click();
	allPages.getDeleteOption().click();
	
	cu.blindWait();
	//Delete aleart pop up-ok
	allPages.getOk_PopUpBtn().click();
}
}
