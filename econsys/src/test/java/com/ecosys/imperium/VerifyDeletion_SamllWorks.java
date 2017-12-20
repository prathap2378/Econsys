package com.ecosys.imperium;

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

import com.econsys.Genriclibrery.*;
import com.econsys.Projects.*;
import com.econsys.SmallWorks.ProjectMethods_Small_Works;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.*;

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
	CommonUtils commonUtils = new CommonUtils();
	Basic basic = new Basic();
	static Monorail rtq=new Monorail();
	Imperium_SmallWorks_Methods imperium_SmallWorks_Methods = new Imperium_SmallWorks_Methods();

	//Verification of project deletion in task level	
	@Test
	public void smallWorksDelete_Task() throws IOException, InterruptedException, AWTException, ClassNotFoundException, SQLException{

		boolean flag=false;

		imperium_SmallWorks_Methods.smallworkForm();
		flag=imperium_SmallWorks_Methods.submit_SW_QuoteForm();
		log.info("Flag : "+flag);
		if(flag){

			Reporter.log("Small Works flow changed to Project, end this test");
		}else{
			//cp2 CL approval
			if((ev.ourformat.equals("No"))){
				Imperium_Project_Methods.clApproval();
			}
			if(ev.exeCP2.equalsIgnoreCase("Yes") || ev.bidsheetauthorised.equals("No")){
				basic.boardApproval();
			}

			login.loginSL();
			commonUtils.waitForPageToLoad();
			ab.getViewalltasks().click();
			commonUtils.blindWait();
			driver.findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]//div/button[starts-with(@id,'actn-btn-')]")).click();
			commonUtils.blindWait();
			log.info("Option Btn clicked");
			allPages.getDeleteOption().click();
			log.info("Clicked Delete Option");
			commonUtils.blindWait();
			allPages.getOk_PopUpBtn().click();
			commonUtils.waitForPageToLoad();
			//Get coment box ID
			log.info("get coment box id");
			String commentBoxIid = driver.findElement(By.xpath("//td[@title="+ev.projectName()+"]/parent::tr")).getAttribute("id");
			log.info("comments");
			log.info(commentBoxIid);
			driver.findElement(By.xpath(".//textarea[@id='x_cmt_"+commentBoxIid+"']")).sendKeys("Delete this task");

			log.info("cemments loged");
			commonUtils.blindWait();
			driver.findElement(By.xpath("//div[@id='"+commentBoxIid+"_docDiv']//tr/td[1]/a")).click();
			log.info("saved");

			login.logout();

			//Verify in project archive
			login.loginOrgAdmin();
			allPages.getProArchLink().click();
			commonUtils.waitForPageToLoad();
			basic.search();
			Assert.assertEquals(driver.findElement(By.xpath("//td[@title='Deleted']")).getText(), "Deleted");
		}
	}
	
	//Verification of project deletion in all projects
	@Test
	public void smallWorksdelete_AllProjects() throws IOException, InterruptedException, AWTException, ClassNotFoundException, SQLException{

		boolean flag=false;

		imperium_SmallWorks_Methods.smallworkForm();
		flag=imperium_SmallWorks_Methods.submit_SW_QuoteForm();
		log.info("Flag : "+flag);
		if(flag){

			Reporter.log("Small Works flow changed to Project, end this test");
		}else{

			login.loginOrgAdmin();
			commonUtils.waitForPageToLoad();
			allTabs.getAllProjects().click();

			log.info("search all projects");
			basic.search();
			driver.findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]//div/button[starts-with(@id,'actn-btn-')]")).click();

			driver.findElement(By.xpath("//td/span[contains(text(),'Delete')]")).click();
			commonUtils.blindWait();
			//Delete alert pop up-okay
			allPages.getOk_PopUpBtn().click();
			commonUtils.blindWait();
			//Get coment box ID
			log.info("get coment box id");
			String commentBoxIid = driver.findElement(By.xpath("//td[@title="+ev.projectName()+"]/parent::tr")).getAttribute("id");
			log.info("comments");
			log.info(commentBoxIid);
			driver.findElement(By.xpath(".//textarea[@id='x_cmt_"+commentBoxIid+"']")).sendKeys("Delete this task");

			log.info("cemments loged");
			commonUtils.blindWait();
			driver.findElement(By.xpath("//div[@id='"+commentBoxIid+"_docDiv']//tr/td[1]/a")).click();
			log.info("saved");
			commonUtils.blindWait();
			allPages.getProArchLink().click();
			commonUtils.waitForPageToLoad();
			basic.search();
			log.info("Searched project");
			Assert.assertEquals(driver.findElements(By.xpath("//td[@title='Deleted']")).get(0).getText(), "Deleted");
			log.info("Asserted");
		}
	}
	
	//Delete Saved rtq from savedRTQ grid
	@Test
	public void smallWorksSave_Delete_SavedRTQSW() throws IOException, InterruptedException, AWTException, ClassNotFoundException, SQLException{

		imperium_SmallWorks_Methods.smallworkForm();
		ab.getSave_RTQ().click();
		login.logout();
		//Admin
		login.loginOrgAdmin();
		commonUtils.waitForPageToLoad();
		ab.getAdmin().click();
		commonUtils.waitForPageToLoad();
		ab.getSavedRTQ().click();
		//Action button
		driver.findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]//div/button[starts-with(@id,'actn-btn-')]")).click();
		allPages.getDeleteOption().click();

		commonUtils.blindWait();
		//Delete aleart pop up-ok
		allPages.getOk_PopUpBtn().click();
	}
}
