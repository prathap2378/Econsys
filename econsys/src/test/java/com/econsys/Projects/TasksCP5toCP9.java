package com.econsys.Projects;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.econsys.Genriclibrery.*;
import com.econsys.SmallWorks.ProjectMethods_Small_Works;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.*;
/**
 * TasksCP5toCP9 class provides methods of all the tasks between CP5 to CP9 that is  
 * Project Delivery Plan(PDP)
 * Project Delivery Review
 * Obtain Practical Completion
 * Post Practical Completion
 * 
 * @author  Bhanu Prathap k
 * @version 1.0
 * @since   2015-03-31
 */
public class TasksCP5toCP9 extends Driver {
	/*page factory class*/
	static CommonUtils cu=PageFactory.initElements(Driver.driver(), CommonUtils.class);
	static PDPui pdp_ui=PageFactory.initElements(Driver.driver(),PDPui.class);
	static ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	static ProjectMethods_Small_Works proMethod=PageFactory.initElements(Driver.driver(), ProjectMethods_Small_Works.class);
	AppointkeystaffandCommerSuitUi ak=PageFactory.initElements(Driver.driver(), AppointkeystaffandCommerSuitUi.class);
	static DeliveryReviewUi deliveryReviewUi = PageFactory.initElements(driver(), DeliveryReviewUi.class);
	static Logger log = Logger.getLogger(TasksCP5toCP9.class.getName());
	/*import classes*/
	static Workbook wb=new Workbook();
	static Login login=new Login();
	static Basic b=new Basic();
	EconsysVariables ev = new EconsysVariables();
	String filepath=System.getProperty("user.dir");
	Monorail monorail = new Monorail();
	ProjectMethods_Small_Works projectMethods_Small_Works = new ProjectMethods_Small_Works();
	static Alerts alerts = new Alerts();

	public static void main(String[] args) {
		TasksCP5toCP9 test=new TasksCP5toCP9();
		try {
			//test.pdp();
			test.deveryreview();
			//test.obtainpracticalcomplition();
			//test.postpracticalcomplition();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}


	/*Project delivery plan(PDP) task*/
	@Test(priority=0)
	public void pdp() throws IOException, InterruptedException {
		login.url();

		login.loginPL();
		String taskName = PropertiesUtil.getPropValues("PDP");
		b.projectTaskName(taskName);

		List<WebElement> deletebutton=driver().findElements(By.xpath("//a[starts-with(@id,'delete_')]"));
		for(int i=0;i<deletebutton.size();i++){
			Thread.sleep(500);
			deletebutton.get(i).click();
			Thread.sleep(500);
			driver().findElement(By.xpath("//div[div[contains(text(),'Are you sure, you want to delete?')]]/div/a[1]")).click();
		}

		/*pdp.getPlandoc().sendKeys(filepath+"\\Documentsuploded\\plandoc.docx");*/
		Thread.sleep(500);
		pdp_ui.getProject_Programme_document().click();
		ProjectMethods_Small_Works.linktoFileupload();

		/*Value forecast grid details and adding milestone values*/
		cu.blindWait();
		pdp_ui.getAddnewvalueforcast().click();
		driver().findElement(By.xpath("//input[@id='milestoneDate']")).click();
		pdp_ui.getDate().click();
		String milestonevalue = wb.getXLData(1,5, 2);
		pdp_ui.getContractWorks().sendKeys(""+milestonevalue);
		ab.getPop_Comments().sendKeys("Addnewvalueforcast");
		cu.blindWait();
		pdp_ui.getSavemilestone().click();

		/*Mile stone Comments
	  cu.waitForPageToLoad();
	  driver.findElement(By.xpath("//textarea[starts-with(@id,'x_cmt_prodAppForcGrid_Row')]")).sendKeys("milestone");
	  driver.findElement(By.linkText("Save")).click();*/

		/*Mile stone document*/
		cu.blindWait();
		driver().findElement(By.xpath("//input[@id='fileList_flm_milestoneDocument']")).click();
		ProjectMethods_Small_Works.linktoFileupload();

		/*Production schedule document*/	
		cu.blindWait();
		/*pdp_ui.getProductionscheduledoc().click();
	  ProjectMethods_Small_Works.linktoFileupload();*/

		ab.getComments().sendKeys("PDP");

		//cu.selectByVisibleText(pdp.getExecp6(),ev.execp6);
		/* Thread.sleep(1000);
	  ab.getSubmitbutton().click();
	  Thread.sleep(1000);*/
		//login.logout();
	}
	//Matrix pdp chnged from other orgs used in Small works also and in 4eg
	public void pdp_Matrix() throws IOException, InterruptedException {
		try{
			login.url();

			login.loginPL();
			String taskName = PropertiesUtil.getPropValues("PDP");
			b.projectTaskName(taskName);

			List<WebElement> deletebutton=driver().findElements(By.xpath("//a[starts-with(@id,'delete_')]"));
			for(int i=0;i<deletebutton.size();i++){
				Thread.sleep(500);
				deletebutton.get(i).click();
				Thread.sleep(500);
				driver().findElement(By.xpath("//div[div[contains(text(),'Are you sure, you want to delete?')]]/div/a[1]")).click();
			}

			/*pdp.getPlandoc().sendKeys(filepath+"\\Documentsuploded\\plandoc.docx");*/
			Thread.sleep(500);
			pdp_ui.getProject_Programme_document().click();
			ProjectMethods_Small_Works.linktoFileupload();

			//payment terms
			log.info("commmercial suite payments ");
			cu.selectByIndex(ak.getDraftCommercialSuitProduced(),2);
			/*if(ev.draftproduced.equals("Yes")){
		  ak.getPayment_Cycle_Document().click();
		  ProjectMethods_Small_Works.linktoFileupload();
	  }*/
			ak.getPayment_Terms().sendKeys("15");
			ak.getDays_From().sendKeys("3");
			log.info("completed");

			/*Value forecast grid details and adding milestone values*/
			cu.blindWait();
			pdp_ui.getAddnewvalueforcast().click();
			//pdp_ui.getMilestone().sendKeys("Milestone");
			pdp_ui.getInvoiceNumber().sendKeys("15");
			driver().findElement(By.xpath("//input[@id='milestoneDate']")).click();
			pdp_ui.getDate().click();
			String milestonevalue = wb.getXLData(1,5, 2);
			pdp_ui.getContractWorks().sendKeys(""+milestonevalue);
			//pdp_ui.getVariation().sendKeys("5000");
			//pdp_ui.getClaim().sendKeys("6000");
			pdp_ui.getCommentsMilestone().sendKeys("Application is added...");
			//ab.getPop_Comments().sendKeys("Addnewvalueforcast");
			pdp_ui.getSavemilestone().click();

			cu.blindWait();
			Thread.sleep(1000);
			driver().findElement(By.xpath("//input[@id='fileList_flm_milestoneDocument']")).click();
			ProjectMethods_Small_Works.linktoFileupload();

			//Select production of schedule
			cu.selectByIndex(driver.findElement(By.xpath("//*[@id='scheduleOfRatesNeeded']")),2);

			ab.getComments().sendKeys("PDP");
		}
		catch(WebDriverException e){
			System.out.println(e);
		}
	}

	public void pdp_4eg() throws IOException, InterruptedException{
		try{
			login.url();

			login.loginPL();
			String taskName = PropertiesUtil.getPropValues("PDP");
			b.projectTaskName(taskName);

			List<WebElement> deletebutton=driver().findElements(By.xpath("//a[starts-with(@id,'delete_')]"));
			for(int i=0;i<deletebutton.size();i++){
				Thread.sleep(500);
				deletebutton.get(i).click();
				Thread.sleep(500);
				driver().findElement(By.xpath("//div[div[contains(text(),'Are you sure, you want to delete?')]]/div/a[1]")).click();
			}

			/*pdp.getPlandoc().sendKeys(filepath+"\\Documentsuploded\\plandoc.docx");*/
			Thread.sleep(500);
			pdp_ui.getProject_Programme_document().click();
			ProjectMethods_Small_Works.linktoFileupload();

			/*Value forecast grid details and adding milestone values*/
			cu.blindWait();
			pdp_ui.getAddnewvalueforcast().click();
			//pdp_ui.getMilestone().sendKeys("Milestone");
			pdp_ui.getInvoiceNumber().sendKeys("15");
			driver().findElement(By.xpath("//input[@id='milestoneDate']")).click();
			pdp_ui.getDate().click();
			String milestonevalue = wb.getXLData(1,5, 2);
			pdp_ui.getContractWorks().sendKeys(""+milestonevalue);
			//pdp_ui.getVariation().sendKeys("5000");
			//pdp_ui.getClaim().sendKeys("6000");
			pdp_ui.getCommentsMilestone().sendKeys("Application is added...");
			//ab.getPop_Comments().sendKeys("Addnewvalueforcast");
			pdp_ui.getSavemilestone().click();

			cu.blindWait();
			Thread.sleep(1000);
			driver().findElement(By.xpath("//input[@id='fileList_flm_milestoneDocument']")).click();
			ProjectMethods_Small_Works.linktoFileupload();

			//Select production of schedule
			cu.selectByIndex(driver.findElement(By.xpath("//*[@id='scheduleOfRatesNeeded']")),2);

			ab.getComments().sendKeys("PDP");
		}
		catch(WebDriverException e){
			System.out.println(e);
		}
	}
	/*Delivery review task*/
	@Test(priority=1)
	public void deveryreview() throws IOException, InterruptedException{

		login.url();
		String deliveryReview_dission=wb.getXLData(10,12,0);

		/*login as PL to *********** submit*/
		if(deliveryReview_dission.equals("Submit")){
			login.loginPL();
			String taskName = PropertiesUtil.getPropValues("delivery_Review");
			b.projectTaskName(taskName);

			/*driver.findElement(By.xpath("//input[@id='flm_att_0multifile_hidden_mdrProgDocument']")).sendKeys(filepath+"\\Documentsuploded\\Project programme document 1.txt");
	  driver.findElement(By.xpath("//input[@id='flm_att_0multifile_hidden_mdrPlanDocument']")).sendKeys(filepath+"\\Documentsuploded\\Plan document 1.txt");
	  driver.findElement(By.xpath("//input[@id='flm_att_0multifile_hidden_mdrMilestoneDocument']")).sendKeys(filepath+"\\Documentsuploded\\Milestone Document 1.txt");
	  driver.findElement(By.xpath("//input[@id='flm_att_0multifile_hidden_mdrRiskRegisterupdated']")).sendKeys(filepath+"\\Documentsuploded\\Updated Risk and Opportunity Register 2.txt");
	  driver.findElement(By.xpath("//input[@id='flm_att_0multifile_hidden_mdrPanelProductionSchedule']")).sendKeys(filepath+"\\Documentsuploded\\Production Schedule 1.txt");*/

			//Programme document
			driver.findElement(By.id("mdrProgDocument-dropzone")).click();
			cu.blindWait();
			ProjectMethods_Small_Works.uploadFile("Project programme document 1.txt");

			if(alerts.upload_mdrProgDocument()<1){
				log.info("files not uploaded for programme document");
				driver.findElement(By.id("mdrProgDocument-dropzone")).click();
				cu.blindWait();
				ProjectMethods_Small_Works.uploadFile("Project programme document 1.txt");
				log.info("uploaded again");
			}
			//Mile stone document
			Thread.sleep(4000);
			cu.WaitForElementIDPresent("mdrMilestoneDocument-dropzone");
			driver.findElement(By.id("mdrMilestoneDocument-dropzone")).click();
			ProjectMethods_Small_Works.uploadFile("Plan document 1.txt");

			if(alerts.upload_mdrMilestoneDocument()<1){
				log.info("files not uploaded for upload_mdrMilestoneDocument document");
				cu.WaitForElementIDPresent("mdrMilestoneDocument-dropzone");
				driver.findElement(By.id("mdrMilestoneDocument-dropzone")).click();
				ProjectMethods_Small_Works.uploadFile("Plan document 1.txt");
				log.info("uploaded again");
			}
			//Panel production doc
			/*Thread.sleep(4000);
	  cu.WaitForElementIDPresent("mdrPanelProductionSchedule-dropzone");
	  driver.findElement(By.id("mdrPanelProductionSchedule-dropzone")).click();
	  proMethod.uploadFile("Production Schedule 1.txt");

	  if(alerts.upload_mdrPanelProductionSchedule()<1){
		  log.info("files not uploaded for upload_mdrPanelProductionSchedule document");
		  cu.WaitForElementIDPresent("mdrPanelProductionSchedule-dropzone");
		  driver.findElement(By.id("mdrPanelProductionSchedule-dropzone")).click();
		  proMethod.uploadFile("Production Schedule 1.txt");
		  log.info("uploaded again");
	  }*/

			cu.waitForPageToLoad();
			ab.getComments().sendKeys("Delivery review submit");
			cu.blindWait();

			log.info("alerts.upload_mdrProgDocument :"+alerts.upload_mdrProgDocument());
			log.info("alerts.upload_mdrMilestoneDocument :"+alerts.upload_mdrMilestoneDocument());
			//log.info("alerts.upload_mdrPanelProductionSchedule :"+alerts.upload_mdrPanelProductionSchedule());

			if(alerts.upload_mdrProgDocument()<1||alerts.upload_mdrMilestoneDocument()<1){
				//ab.getSubmitbutton().click();
				log.info("files Madatory error ************");
				cu.blindWait();
				driver().findElement(By.xpath("//div/a[contains(text(),'OK')]")).click();
				log.info("Mandatory alert accepted*********");
			}
			else{
				log.info("No mandatory alert###########");
				ab.getSubmitbutton().click();  
			}

			Thread.sleep(500);
			login.logout();
		}

		/*login as PL for ************ Monthly Review*/
		if(deliveryReview_dission.equals("Monthly Review")){
			login.loginPL();
			String taskName = PropertiesUtil.getPropValues("delivery_Review");
			b.projectTaskName(taskName);

			/*driver.findElement(By.id("mdrProgDocument-dropzone")).click();
	  proMethod.uploadFile("Project programme document 2.txt");

	  if(alerts.upload_mdrProgDocument()<1){
		  log.info("files not uploaded for upload_mdrProgDocument document");
		  cu.WaitForElementIDPresent("mdrPanelProductionSchedule-dropzone");
		  driver.findElement(By.id("mdrProgDocument-dropzone")).click();
		  proMethod.uploadFile("Project programme document 2.txt");
		  log.info("uploaded again");
	  }*/

			Thread.sleep(4000);
			driver.findElement(By.id("mdrMilestoneDocument-dropzone")).click();
			ProjectMethods_Small_Works.uploadFile("Milestone Document 2.txt");

			if(alerts.upload_mdrProgDocument()<1){
				log.info("files not uploaded for upload_mdrProgDocument document");
				//cu.WaitForElementIDPresent("mdrPanelProductionSchedule-dropzone");
				driver.findElement(By.id("mdrProgDocument-dropzone")).click();
				ProjectMethods_Small_Works.uploadFile("Project programme document 2.txt");
				log.info("uploaded again");
			}

			/*Thread.sleep(4000);
	  driver.findElement(By.id("mdrPanelProductionSchedule-dropzone")).click();
	  proMethod.uploadFile("Production Schedule 2.txt");*/

			ab.getComments().sendKeys("Delivery review to monthly review");

			//Checking file uploaded are not
			if(alerts.upload_mdrProgDocument()<1||alerts.upload_mdrMilestoneDocument()<1){
				driver().findElement(By.xpath("//input[@value='To Monthly Review']")).click();
				log.info("file Madatory error ************");
				cu.blindWait();
				driver().findElement(By.xpath("//div/a[contains(text(),'OK')]")).click();
				log.info("Mandatory alert accepted*********");
			}
			else{
				log.info("No mandatory alert###########");
				driver().findElement(By.xpath("//input[@value='To Monthly Review']")).click();  
			}
			cu.waitForPageToLoad();
			login.logout();
		}

		/*Login as ****** OD*/
		login.loginOD();
		b.projectname_ReviewApproval();
		ab.getComments().sendKeys("Approved review");
		ab.getApprove_Button().click();
		login.logout();

		/*login as PL *********** Project Completed*/
		TasksCP5toCP9.projectCompleted();

		/*Login as ****** OD*/
		login.loginOD();
		b.projectname_ReviewApproval();
		ab.getComments().sendKeys("Project completed-yes");
		ab.getApprove_Button().click();
		login.logout();
	}
	//Delivery review Project complete
	public static void projectCompleted() throws IOException, InterruptedException{
		/*login as PL *********** Project Completed*/
		login.loginPL();
		String taskName = PropertiesUtil.getPropValues("delivery_Review");
		b.projectTaskName(taskName);

		Thread.sleep(4000);
		driver.findElement(By.id("mdrProgDocument-dropzone")).click();
		ProjectMethods_Small_Works.uploadFile("Project programme document 3.txt");
		log.info("DR_ProgDocument uploaded");
		Thread.sleep(4000);
		driver.findElement(By.id("mdrMilestoneDocument-dropzone")).click();
		ProjectMethods_Small_Works.uploadFile("Milestone Document 3.txt");
		/*Thread.sleep(4000);
	  driver.findElement(By.id("mdrPanelProductionSchedule-dropzone")).click();
	  proMethod.uploadFile("Production Schedule 3.txt");*/
		Thread.sleep(500);
		ab.getComments().sendKeys("Delivery review project completed with out changes");

		//Checking file uploads
		if(alerts.upload_mdrProgDocument()<1||alerts.upload_mdrMilestoneDocument()<1){
			ab.getProjectCompletedbutton().click();
			log.info("file Madatory error '''''''''''''");
			cu.blindWait();
			alerts.getAlert_Accept_Ok().click();
			log.info("Mandatory alert accepted---------");
		}
		else{
			log.info("No mandatory alert*************");
			ab.getProjectCompletedbutton().click();  
		}
		//Delivery review project completion alert
		cu.blindWait();
		deliveryReviewUi.getDataChange_Alert().click();
		login.logout();

	}

	/*Obtain Practical Completion task*/
	public void obtainpracticalcomplition() throws IOException, InterruptedException{


		login.loginPL();
		String taskName = PropertiesUtil.getPropValues("obtain_Practical_Completion");
		b.projectTaskName(taskName);

		cu.selectByVisibleText(pdp_ui.getOpc_certificateobtained(), ev.certificateobtained);
		if(ev.certificateobtained.equals("Yes")){
			pdp_ui.getOpcdoc_certificateobtained().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		cu.selectByVisibleText(pdp_ui.getOpc_retationapplied(), ev.retationapplied);
		if(ev.retationapplied.equals("Yes")){
			pdp_ui.getOpcdoc_retentinApplied().click();
			ProjectMethods_Small_Works.linktoFileupload();
			//select retention on date
			cu.waitForPageToLoad();
			//Retention On only for GA
			int retaionOn = driver.findElements(By.xpath("//input[@id='dt_finalRetentionOn']")).size();
			if(retaionOn>0){
				driver.findElement(By.xpath("//input[@id='dt_finalRetentionOn']")).click();
				pdp_ui.getDate().click();
			}
		}
		cu.selectByVisibleText(pdp_ui.getOpc_onmSubmitted(), ev.onmSubmitted);
		if(ev.onmSubmitted.equals("Yes")){
			pdp_ui.getOpcdoc_onmSubmitted().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		cu.selectByVisibleText(pdp_ui.getOpc_snagListIdentified(), ev.snagListIdentified);
		if(ev.snagListIdentified.equals("Yes")){
			pdp_ui.getOpcdoc_snagListIdentified().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		cu.selectByVisibleText(pdp_ui.getOpc_internalCompletionDocument(), ev.internalCompletionDocument);
		if(ev.internalCompletionDocument.equals("Yes")){
			pdp_ui.getOpcdoc_internalCompletionDocument().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}

		ab.getComments().sendKeys("Obtain practical complition");

		/*cu.selectByVisibleText(pdp_ui.getOpc_cp8(), ev.execp8);
	  ab.getSubmitbutton().click();
	  login.logout();*/
	}

	/*Post Practical completion task*/
	public void postpracticalcomplition() throws IOException, InterruptedException{

		login.loginPL();
		//Thread.sleep(1000);
		String taskName = PropertiesUtil.getPropValues("post_Practical_Completion");
		b.projectTaskName(taskName);
		//Thread.sleep(1000);

		cu.selectByVisibleText(pdp_ui.getPpc_finalAccountAgreement(), ev.finalAccountAgreement);
		if(ev.finalAccountAgreement.equals("Yes")){
			pdp_ui.getPpcdoc_finalAccountAgreement().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		cu.selectByVisibleText(pdp_ui.getPpc_finalRetentionPaid(), ev.finalRetentionPaid);
		if(ev.finalRetentionPaid.equals("Yes")){
			pdp_ui.getPpcdoc_finalRetentionPaid().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		cu.selectByVisibleText(pdp_ui.getPpc_projectDocumentArchived(), ev.projectDocumentArchived);
		if(ev.projectDocumentArchived.equals("Yes")){
			pdp_ui.getPpcdoc_projectDocumentArchived().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		cu.selectByVisibleText(pdp_ui.getPpc_projectDebrief(), ev.projectDebrief);
		cu.selectByVisibleText(pdp_ui.getPpc_subContractAccountSettled(), ev.subContractAccountSettled);
		if(ev.subContractAccountSettled.equals("Yes")){
			pdp_ui.getPpcdoc_subContractAccountSettled().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		cu.selectByVisibleText(pdp_ui.getPpc_closureofProject(), ev.closureofProject);
		if(ev.closureofProject.equals("Yes")){
			pdp_ui.getPpcdoc_closureofProject().click();
			ProjectMethods_Small_Works.linktoFileupload();
			Thread.sleep(1000);
		}
		cu.selectByVisibleText(pdp_ui.getPpc_bondsGuaranteesResolved(), ev.bondsGuarantees_Resolved);
		JavascriptExecutor js=(JavascriptExecutor)Driver.driver();
		js.executeScript("scroll(0,350)");//TO Scroll down
		if(ev.bondsGuarantees_Resolved.equals("Yes")){
			pdp_ui.getPpcdoc_bondsGuaranteesResolved().click();
			ProjectMethods_Small_Works.linktoFileupload();
			Thread.sleep(1000);
		}
		ab.getComments().sendKeys("Post practical complition");
		/*cu.selectByVisibleText(pdp_ui.getPpc_cp9(), ev.execp9);
	  ab.getSubmitbutton().click();
	  login.logout();*/
		//driver.close();
	}
}
