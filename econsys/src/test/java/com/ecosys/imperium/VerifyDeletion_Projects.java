package com.ecosys.imperium;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import POC.econsys.ActionButtons;
import com.econsys.Genriclibrery.*;
import com.econsys.Projects.*;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.*;
import com.econsys.testCases.ActionButtonsCPApprovalsSW;

public class VerifyDeletion_Projects extends Driver{

	Logger log=Logger.getLogger(VerifyDeletion_Projects.class.getName());
	ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	AllPages allPages= PageFactory.initElements(Driver.driver(), AllPages.class);
	RTQForm_Ui nrtq=PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
	SadminUi allTabs = PageFactory.initElements(Driver.driver(), SadminUi.class);
	
	Workbook wb = new Workbook();
	Login login= new Login();
	EconsysVariables ev= new EconsysVariables();
	Basic basic = new Basic();
	ActionButtons actionBtns= new ActionButtons();
	CommonUtils cu= new CommonUtils();
	static Monorail rtq=new Monorail();
	ActionButtonsCPApprovalsSW dr= new ActionButtonsCPApprovalsSW();
	Imperium_Project_Methods imperium_Project_Methods = new Imperium_Project_Methods();
	Imperium_SmallWorks_Methods imperium_SmallWorks_Methods = new Imperium_SmallWorks_Methods(); 

	//Verification of project deletion in task level
	@Test
	public void projectDelete_Task() throws IOException, InterruptedException, AWTException{

		login.user();
		//****intiation of rtq form*********
		imperium_Project_Methods.rtqForm(ev.estimatedSize,ev.location);
		imperium_Project_Methods.submit_logout_QRnumberAlert();
		
		//***********CP1 exe dession************
		if((ev.estimatedSize.equals(ev.estimatedSize500_))||(ev.location.equals(ev.location_other))) {
			basic.boardApproval();
		}
		imperium_Project_Methods.ASL();
		
		login.loginSL();
		ab.getViewalltasks().click();
		cu.blindWait();
		driver.findElement(By.xpath("//tr[td[text()="+ev.projectName()+"]]//button[starts-with(@id,'actn-btn')]")).click();
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

		//SD approval post project delete
		if(!ev.estimatedSize.equals(ev.estimatedSize0to100k_)){
			login.logout();
			login.loginSD();
			ab.getGroupapprovals().click();
			driver.findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]//a[starts-with(@onclick,'veiwTasks')]")).click();
			nrtq.getComments().sendKeys("Approve Deletion....");
			ab.getApprove_Button().click();//Approve button click
			cu.waitForPageToLoad();
		}

		login.logout();

		//Verify in project archive
		login.user();
		allPages.getProArchLink().click();
		cu.waitForPageToLoad();
		basic.search();
		Assert.assertEquals(driver.findElement(By.xpath("//td[@title='Deleted']")).getText(), "Deleted");
	}

	//Verification of project deletion in all projects
	@Test
	public void projectdelete_AllProjects() throws IOException, InterruptedException, AWTException{
		
		login.user();
		imperium_Project_Methods.rtqForm(ev.estimatedSize,ev.location);
		imperium_Project_Methods.submit_logout_QRnumberAlert();
		
		login.loginOrgAdmin();
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
		String commentBoxIid = driver.findElement(By.xpath("//td[@title="+ev.projectName()+"]/parent::tr")).getAttribute("id");
		driver.findElement(By.xpath(".//textarea[@id='x_cmt_"+commentBoxIid+"']")).sendKeys("Delete this task");

		cu.blindWait();
		driver.findElement(By.xpath("//div[@id='"+commentBoxIid+"_docDiv']//tr/td[1]/a")).click();
		cu.blindWait();
		allPages.getProArchLink().click();
		cu.waitForPageToLoad();
		basic.search();
		Assert.assertEquals(driver.findElements(By.xpath("//td[@title='Deleted']")).get(0).getText(), "Deleted");
		log.info("Asserted");
	}
	
	//Delete Saved rtq from savedRTQ grid
	@Test
	public void projectSave_Delete_SavedRTQ() throws IOException, InterruptedException, AWTException{
		login.url();
		login.user();
		imperium_Project_Methods.rtqForm(ev.estimatedSize,ev.location);
		ab.getSave_RTQ().click();
		
		//Admin
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
