package com.econsys.testCases;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.econsys.Genriclibrery.CommonUtils;
import com.econsys.Genriclibrery.Driver;
import com.econsys.Genriclibrery.EconsysVariables;
import com.econsys.Projects.Basic;
import com.econsys.Projects.Login;
import com.econsys.UIobjectrepositary.*;

//com.econsys.AllPages.VerifyDelegation
public class VerifyDelegation extends Driver{
	Logger log=Logger.getLogger(VerifyDelegation.class.getName());
	Login login = new Login();
	CommonUtils cu= new CommonUtils();
	Basic basic = new Basic();
	ActionButtonsCPApprovalsSW dr= new ActionButtonsCPApprovalsSW();
	EconsysVariables ev= new EconsysVariables();
	AllPages allPages=PageFactory.initElements(Driver.driver(), AllPages.class);
	ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	RTQForm_Ui nrtq=PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
@Test
public void verify_AllProjectsDelegation() throws IOException, InterruptedException, AWTException{
	login.user();
	cu.waitForPageToLoad();
	allPages.getAllProjects().click();
	int allProjects_Count = driver.findElements(By.xpath("//div[@id='gview_delegatestaticGrid']//tr")).size();
	log.info("allProjectsData : "+allProjects_Count);
	if(allProjects_Count>2){
		String row_Id = driver.findElement(By.xpath("//div[@id='gview_delegatestaticGrid']//tr[2]")).getAttribute("id");
		
		driver.findElement(By.xpath("//button[@id='actn-btn-"+row_Id+"']")).click();
		cu.blindWait();
		driver.findElement(By.xpath("//td[@onclick='delegateTask("+row_Id+")']")).click();
		
		cu.blindWait();
		nrtq.getComments().sendKeys("Verifying Delegation....");
		String selecte_User=cu.selectByIndex(allPages.getSelect_Delegate(), 1);
		log.info("Selected User"+selecte_User);
		allPages.getDelegateBtn().click();
		cu.blindWait();
		//dr.search();
		String Ddlegated_User=allPages.getDelegatedOwner_AllPro().getAttribute("title");
		log.info("Actual Delegated User"+Ddlegated_User);
		
		Assert.assertEquals(Ddlegated_User,selecte_User);
	}
	else{
		basic.rtqForm(ev.estimatedSize,ev.location);
	    //rtq submit externally
	    ab.getSubmitbutton().click();
	    cu.waitForPageToLoad();
		allPages.getAllProjects().click();
		basic.search();
		basic.actionButton();
		allPages.getDelegate_option().click();
		nrtq.getComments().sendKeys("Verifying Delegation....");
		String selecte_User=cu.selectByIndex(allPages.getSelect_Delegate(), 1);
		log.info("Selected User"+selecte_User);
		allPages.getDelegateBtn().click();
		cu.blindWait();
		basic.search();
		String Ddlegated_User=allPages.getDelegatedOwner_AllPro().getAttribute("title");
		log.info("Actual Delegated User"+Ddlegated_User);
		
		Assert.assertEquals(Ddlegated_User,selecte_User);
	}
			
}
@Test
public void verify_SavedRTQDelegation() throws IOException, InterruptedException, AWTException{
	 log.info("login as admin saved rtq deligation");
	 login.user();
	 log.info("loged in");
	 cu.waitForPageToLoad();
	 allPages.getAdminLink().click();
	 allPages.getSaveRTQlink().click();
	 int savedRTQ_Count = driver.findElements(By.xpath("//div[@id='gview_delegateGrid']//tr")).size();
	 log.info("savedRTQ_Count :"+savedRTQ_Count);
	 if(savedRTQ_Count>2){
		String row_Id = driver.findElement(By.xpath("//div[@id='gview_delegateGrid']//tr[2]")).getAttribute("id");
		
		driver.findElement(By.xpath("//button[@id='actn-btn-"+row_Id+"']")).click();
		cu.blindWait();
		driver.findElement(By.xpath("//span[@onclick='delegateRtq("+row_Id+")']")).click();
		
		String selecte_User=cu.selectByIndex(allPages.getSavedRTQDelegate_select(), 1);
		log.info("Selected User for Delegation :"+selecte_User);
		nrtq.getComments().sendKeys("Verifying Delegation....");
		allPages.getDelegateBtn().click();
		//dr.search();
		String delegated_User = allPages.getDelegateOwners().getAttribute("title");
		log.info("Assigned to User after delegation :"+delegated_User);
		Assert.assertEquals(delegated_User,selecte_User);
		 
	 }
	 else{
	 basic.rtqForm(ev.estimatedSize,ev.location);
	 ab.getSave_RTQ().click();
	 allPages.getAdminLink().click();
	 allPages.getSaveRTQlink().click();
	 basic.search();
	 basic.actionButton();
	 allPages.getDelegateOption_savedRTQ().click();
	 String selecte_User=cu.selectByIndex(allPages.getSavedRTQDelegate_select(), 1);
	 log.info("Selected User for Delegation :"+selecte_User);
	 nrtq.getComments().sendKeys("Verifying Delegation....");
	 allPages.getDelegateBtn().click();
	 basic.search();
	 String delegated_User = allPages.getDelegateOwners().getAttribute("title");
	 log.info("Assigned to User after delegation :"+delegated_User);
	 Assert.assertEquals(delegated_User,selecte_User);
	 }
}
}
