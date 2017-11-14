package com.ecosys.imperium;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.econsys.Genriclibrery.*;
import com.econsys.Projects.*;
import com.econsys.SmallWorks.ProjectMethods_Small_Works;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.*;

/**
 * @author bhanu.pk
 * Matrix Projects class consists of end to end(CP1-CP9) flow
 along with methods
 * Assign sales leader
 * Prepare_Quote
 * Prepare_Quote CP2-CP3
 * Submit Quote
 * Status of submit Quote/Resubmit quote
 * Board approval */

public class Imperium_Project_Methods extends Driver {

	private static Logger log = Logger.getLogger(Imperium_Project_Methods.class.getName());
	//Page UI classes
	public static Preparequote prepare_Quoteui = PageFactory.initElements(Driver.driver(), Preparequote.class);
	static CommonUtils commonUtils = PageFactory.initElements(Driver.driver(), CommonUtils.class);
	static RTQForm_Ui nrtq = PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
	static Assignsalesleader sla = PageFactory.initElements(Driver.driver(), Assignsalesleader.class);
	static CosCommitQuoteStatusUi ccq = PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	static ActionButtonsUi ab = PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	static Salestooperation so = PageFactory.initElements(Driver.driver(),Salestooperation.class);
	static AppointkeystaffandCommerSuitUi ak = PageFactory.initElements(Driver.driver(), AppointkeystaffandCommerSuitUi.class);
	static PDPui pdp_ui = PageFactory.initElements(Driver.driver(),PDPui.class);
	static Alerts alerts = PageFactory.initElements(Driver.driver(), Alerts.class);
	AppointkeystaffandCommerSuitUi appointKeyStaff_CommercialSuite_Uielements = PageFactory.initElements(Driver.driver(), AppointkeystaffandCommerSuitUi.class);
	static DeliveryReviewUi deliveryReviewUi = PageFactory.initElements(driver(), DeliveryReviewUi.class);
	static TandCverification tandCver = PageFactory.initElements(driver(), TandCverification.class);
	Actions actions = new Actions(driver);
	//imported classes
	static Workbook wb=new Workbook();
	static ReviewInvolve ri=new ReviewInvolve();
	static TasksCP4toCP5 g45=new TasksCP4toCP5();
	static TaskCP3CP4 g34=new TaskCP3CP4();
	static Basic basic = new Basic();
	static Monorail monorail = new Monorail();
	static Login login=new Login();
	static TasksCP5toCP9 pdop=new TasksCP5toCP9();
	static EconsysVariables ev = new EconsysVariables();
	static ProjectMethods_Small_Works projectMethods_Small_Works = new ProjectMethods_Small_Works();
	static boolean isCP2amber, isCP2green;
	static String filepath=System.getProperty("user.dir");
	static Monorail rtq=new Monorail();	
	//Variables
	String taskNameCP;
	static String productSpecified,Consultant,projectAddress,size=null;
	String isEnqueryOpentoAll,performanceBond,pCG,termsandconditionsadvised,endUserIndustrySector=null;
	String haveweworkedonthissitebefore,areweNamedSpecified,documentationReceived,typeofBuilding=null;
	String enquiryFormat,typeofProject=null;
	String sl,userName;
	{
		try {
			userName = wb.getXLData(1, 0,0);
			sl=wb.getXLData(10, 0, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	//******RTQ form inputs******
	public void rtqForm(String estimatedSize,String location) throws InterruptedException, IOException,AWTException {

		commonUtils.waitForPageToLoad();
		nrtq.getRtqLink().click();
		nrtq.getProjectLink().click();

		log.info("Project name basic: "+ev.projectName());
		nrtq.getProjectName().sendKeys(ev.projectName());

		String paddress=wb.getXLData(6,1,1);
		nrtq.getProjectAddres().sendKeys(paddress);

		String consultant=wb.getXLData(4,1,1);
		nrtq.getConsultantName().sendKeys(consultant);

		String customer=wb.getXLData(2,1,1);
		nrtq.getCustomerName().sendKeys(customer);

		// select Size
		commonUtils.selectByVisibleText(nrtq.getEstimatedSize(), estimatedSize);
		nrtq.getPoints().sendKeys(""+5);
		commonUtils.selectByIndex(nrtq.getNeworExis(), 1);
		commonUtils.selectByIndex(nrtq.getLeadSource(), 1);
		//anticipated date
		nrtq.getAnticipatedDate().click();
		driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/button")).click();
		//Engineering
		//productSpecified=wb.getXLData(12,1,1);
		commonUtils.selectByIndex(nrtq.getProductSpecifieddd(),1);

		//typeofBuilding=wb.getXLData(18, 1, 1);
		commonUtils.selectByIndex(nrtq.getTypeofBuildingdd(), 1);

		//endUserIndustrySector=wb.getXLData(14, 1, 1);
		commonUtils.selectByIndex(nrtq.getEndUserIndustrySectordd(), 1);

		//typeofProject=wb.getXLData(16, 1, 1);
		commonUtils.selectByIndex(nrtq.getTypeofProjectdd(), 1);

		//commercial
		enquiryFormat=wb.getXLData(3,2,1);
		commonUtils.selectByVisibleText(nrtq.getEnquiryFormatdd(), enquiryFormat);

		documentationReceived=wb.getXLData(5, 2, 1);
		commonUtils.selectByVisibleText(nrtq.getDocumentationReceiveddd(), documentationReceived);

		areweNamedSpecified=wb.getXLData(7, 2, 1);
		commonUtils.selectByVisibleText(nrtq.getGANameddd(), areweNamedSpecified);

		//select Location
		commonUtils.selectByVisibleText(nrtq.getLocationdd(), location);	  

		haveweworkedonthissitebefore=wb.getXLData(11, 2, 1);
		commonUtils.selectByVisibleText(nrtq.getPreviousExperienceAtSitedd(), haveweworkedonthissitebefore);

		termsandconditionsadvised=wb.getXLData(13, 2, 1);
		commonUtils.selectByVisibleText(nrtq.getTermsConditionsAdviseddd(), termsandconditionsadvised);

		pCG=wb.getXLData(15,2,1);
		commonUtils.selectByVisibleText(nrtq.getPCGdd(), pCG);

		performanceBond=wb.getXLData(17, 2,1);
		commonUtils.selectByVisibleText(nrtq.getPerformanceBond(), performanceBond);

		isEnqueryOpentoAll=wb.getXLData(19, 2, 1);
		commonUtils.selectByVisibleText(nrtq.getIsenqueryopentoall(), isEnqueryOpentoAll);

		String damage_Advised=wb.getXLData(22,1,1);
		commonUtils.selectByVisibleText(nrtq.getDamages_Advised(),damage_Advised);

		String retention_Advised=wb.getXLData(21,2,1);
		commonUtils.selectByVisibleText(nrtq.getRetention_Advised(),retention_Advised);
		if(retention_Advised.equalsIgnoreCase(ev.select_Yes)){
			driver.findElement(By.xpath("//input[@id='st_RetentionValue']")).sendKeys("10");
		}

		//File upload in RTQ fom
		nrtq.getUploaddocument().click();

		ProjectMethods_Small_Works project_Metods = new ProjectMethods_Small_Works();
		project_Metods.uploadFile("Logfails - Copy (19).txt");

		//uploading documents in case not uploaded for first time
		commonUtils.waitForPageToLoad();
		WebElement rtqDocs = driver.findElement(By.xpath("//div[@id='upload-container']"));
		int docsCount = rtqDocs.findElements(By.xpath(".//*")).size();
		log.info("docsCount RTQ :"+docsCount);
		if(docsCount<1){
			do{
				log.info("ReUpload document");
				nrtq.getUploaddocument().click();
				project_Metods.uploadFile("Milestone Document 1.txt");
				log.info("Uploaded");
			}while(docsCount<1);
		}
		else{
			commonUtils.waitForPageToLoad();
			String rtqComments=wb.getXLData(20,1,1);
			nrtq.getComments().sendKeys(rtqComments);
			//ab.getSavebutton().click();
		}
	}
	void submit_logout_QRnumberAlert() throws InterruptedException {
		ab.getSubmitbutton().click();
		commonUtils.blindWait();  
		//accept quote reference alert
		nrtq.getQouteRefrenceNumberAlert_Imperium().click();
		commonUtils.waitForPageToLoad();
		login.logout();
	}
	//Assign sales leader task
	public void ASL() throws InterruptedException, IOException {
		String userName = wb.getXLData(1, 0,0);
		String sl=wb.getXLData(10, 0, 0);
		if(!sl.equals(userName)){
			login.loginSD();
			commonUtils.blindWait();
			ab.getViewalltasks().click();
			String taskName = PropertiesUtil.getPropValues("ASL");
			basic.projectTaskName(taskName);
			commonUtils.selectByVisibleText(sla.getSalesleader(), ev.sl);
			sla.getComments().sendKeys("Asigne sales leader");
			//Allocate sales leader
			sla.getAllocate().click();
			login.logout();
		}
	}
	//********Prepare Quote task***********
	public void prepare_Quote(String overallSell,String locationrtq2) throws InterruptedException, IOException {

		login.loginSL();
		commonUtils.blindWait();
		String taskName = PropertiesUtil.getPropValues("prepare_Quote");
		basic.projectTaskName(taskName);
		commonUtils.blindWait();

		//RTQ 2 in prepare quote
		commonUtils.selectByVisibleText(nrtq.getLocationrtq2(), locationrtq2);

		commonUtils.selectByVisibleText(prepare_Quoteui.getQuotationonourFormat(), ev.ourformat);

		commonUtils.waitForPageToLoad();
		prepare_Quoteui.getQuotedocument_Linkfile().click();
		ProjectMethods_Small_Works.linktoFileupload();
		commonUtils.waitForPageToLoad();

		prepare_Quoteui.getRiskopportunityregister_Linkfile().click();
		ProjectMethods_Small_Works.linktoFileupload();

		commonUtils.selectByVisibleText(prepare_Quoteui.getBidSheetAuthorised(), ev.bidsheetauthorised);

		prepare_Quoteui.getBidsheet_linkfile().click();
		ProjectMethods_Small_Works.linktoFileupload();
		commonUtils.waitForPageToLoad();

		prepare_Quoteui.getOverallProjectCost().sendKeys(ev.overallCost);

		prepare_Quoteui.getOverallProjectSell().sendKeys(overallSell);

		wb.prepareQuote_BidInfoDetails();
		
		prepare_Quoteui.getComments().sendKeys("Prepare Quote");
	}
	//Prepare Quote2 used in action button verification TC- used on reject of prepare quote 
	public void prepare_Quote2() throws InterruptedException, IOException {

		login.loginSL();
		commonUtils.blindWait();
		String taskName = PropertiesUtil.getPropValues("prepare_Quote");
		basic.projectTaskName(taskName);
		commonUtils.blindWait();

		commonUtils.waitForPageToLoad();
		prepare_Quoteui.getQuotedocument_Linkfile().click();
		ProjectMethods_Small_Works.linktoFileupload();
		commonUtils.waitForPageToLoad();

		prepare_Quoteui.getBidsheet_linkfile().click();
		ProjectMethods_Small_Works.linktoFileupload();
		commonUtils.waitForPageToLoad();

		prepare_Quoteui.getComments().sendKeys("Prepare Quote on rejection...");
	}
	//Prepare quote Cp2-Cp3
	public void prepareQuotecp2cp3(String locationrtq3) throws IOException, InterruptedException{

		commonUtils.waitForPageToLoad();
		String taskName = PropertiesUtil.getPropValues("prepare_Revised_Quote");
		basic.projectTaskName(taskName);
		commonUtils.blindWait();
		commonUtils.selectByVisibleText(nrtq.getLocationrtq2(), locationrtq3);
		
		driver.findElement(By.xpath("//input[@id='fileList_flm_quoteDocument']")).click();
		ProjectMethods_Small_Works.linktoFileupload();

		commonUtils.selectByVisibleText(prepare_Quoteui.getCp2cp3gaformat(), ev.cp2cp3ourformat);
		prepare_Quoteui.getCp2cp3biddoc().click();
		ProjectMethods_Small_Works.linktoFileupload();
		commonUtils.selectByVisibleText(prepare_Quoteui.getCp2cp3bidsheet(), ev.cp2cp3bidsheetauthorised);
		ab.getComments().sendKeys("cp2-cp3 prepare quote");
	}
	public static void clApproval() throws IOException, InterruptedException {

		login.loginCL();
		commonUtils.blindWait();
		//b.projectname_ReviewApproval();
		ab.getComments().sendKeys("Prepare quote CL Approval...");
		//Authorize button
		driver.findElement(By.id("authorise")).click();
		commonUtils.waitForPageToLoad();
		login.logout();
	}

	public void salestoOperation() throws IOException, InterruptedException {

		login.loginSL();
		//Thread.sleep(1000);
		String sales = PropertiesUtil.getPropValues("salesto_Operation");
		basic.projectTaskName(sales);
		
		//sales to operation select radio buttons
		List<WebElement> radio_Buttons = driver().findElements(By.xpath("//input[@type='radio'][@value='NA']"));
		for(int i = 0;i<=radio_Buttons.size();i++){
			commonUtils.waitForPageToLoad();
			radio_Buttons.get(i).click();
		}
		commonUtils.selectByVisibleText(so.getMeeting(), ev.meeting);
		String customerCommitmentType= ev.customerCommitmentType;
		System.out.println(ev.customerCommitmentType);
		if(customerCommitmentType.equals("LOI Received - Under Review")||customerCommitmentType.equals("Email Received - Under Review")
				||customerCommitmentType.equals("Verbal Commitment Received - Under Review")){
			System.out.println(ev.customerCommitmentType+"is email check");
			int a = driver.findElements(By.xpath("//select[@id='st_cCChangeTaskOwner']")).size();
			System.out.println("size of elements "+a);
			if(a>0){
				System.out.println(ev.customerCommitmentType+"there is delegate");
				commonUtils.selectByVisibleText(so.getDelegateTaskto_PL(),ev.delegatetoPL);
			}
		}
		ab.getComments().sendKeys("Sales to operation");
	}
	public void operationAcceptance() throws IOException, InterruptedException{

		login.loginPL();
		String taskName = PropertiesUtil.getPropValues("operations_Acceptanceof_Handover");
		basic.projectTaskName(taskName);
		//List of Yes operation drop downs
		List<WebElement> opacce=driver().findElements(By.xpath("//select[starts-with(@id,'acceptanceStatus_')]"));
		for(int j=0;j<opacce.size();j++){
			commonUtils.waitForPageToLoad();	
			commonUtils.selectByVisibleText(opacce.get(j),"Yes");
		}
		//customer commitment type
		String customerCommitmentType= ev.customerCommitmentType;
		if(customerCommitmentType.equals("LOI Received - Under Review")||customerCommitmentType.equals("Email Received - Under Review")
				||customerCommitmentType.equals("Verbal Commitment Received - Under Review")){
			int a = driver.findElements(By.xpath("//select[@id='st_cCChangeTaskOwnerOPS']")).size();
			if(a>0){
				commonUtils.selectByVisibleText(driver.findElement(By.xpath("//select[@id='st_cCChangeTaskOwnerOPS']")),"Yes");
			}
		}
		commonUtils.selectByVisibleText(appointKeyStaff_CommercialSuite_Uielements.getMeetingwithsales(),ev.meetingwithSL);
		ab.getComments().sendKeys("Operations Acceptance");
	}
	public void pdp_() throws IOException, InterruptedException{
		try{
			login.url();

			login.loginPL();
			String taskName = PropertiesUtil.getPropValues("PDP");
			basic.projectTaskName(taskName);
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
			commonUtils.blindWait();
			pdp_ui.getAddnewvalueforcast().click();
			//pdp_ui.getMilestone().sendKeys("Milestone");
			pdp_ui.getInvoiceNumber().sendKeys("15");
			driver().findElement(By.xpath("//input[@id='milestoneDate']")).click();
			pdp_ui.getDate().click();
			String milestonevalue = wb.getXLData(1,5, 2);
			pdp_ui.getContractWorks().sendKeys(""+milestonevalue);
			pdp_ui.getCommentsMilestone().sendKeys("Application is added...");
			pdp_ui.getSavemilestone().click();

			commonUtils.blindWait();
			//driver().findElement(By.xpath("//input[@id='fileList_flm_milestoneDocument']")).click();
			//ProjectMethods_Small_Works.linktoFileupload();

			commonUtils.selectByVisibleText(pdp_ui.getReportIntverval_PDP(),ev.select_No);
			ab.getComments().sendKeys("PDP form...");
		}
		catch(WebDriverException e){
			System.out.println(e);
		}
	}

	public void deveryreview(String deliveryReview_dission) throws IOException, InterruptedException{

		login.url();
		/*login as PL to *********** submit*/
		if(deliveryReview_dission.equals("Submit")){
			login.loginPL();
			String taskName = PropertiesUtil.getPropValues("delivery_Review");
			basic.projectTaskName(taskName);

			//Programme document
			driver.findElement(By.id("mdrProgDocument-dropzone")).click();
			commonUtils.blindWait();
			ProjectMethods_Small_Works.uploadFile("Project programme document 1.txt");

			if(alerts.upload_mdrProgDocument()<1){
				log.info("files not uploaded for programme document");
				driver.findElement(By.id("mdrProgDocument-dropzone")).click();
				commonUtils.blindWait();
				ProjectMethods_Small_Works.uploadFile("Project programme document 1.txt");
				log.info("uploaded again");
			}
			//Mile stone document
			/*Thread.sleep(4000);
		  commonUtils.WaitForElementIDPresent("mdrMilestoneDocument-dropzone");
		  driver.findElement(By.id("mdrMilestoneDocument-dropzone")).click();
		  ProjectMethods_Small_Works.uploadFile("Plan document 1.txt");*/

			commonUtils.waitForPageToLoad();
			ab.getComments().sendKeys("Delivery review submit");
			commonUtils.blindWait();

			log.info("alerts.upload_mdrProgDocument :"+alerts.upload_mdrProgDocument());
			log.info("alerts.upload_mdrMilestoneDocument :"+alerts.upload_mdrMilestoneDocument());
			//log.info("alerts.upload_mdrPanelProductionSchedule :"+alerts.upload_mdrPanelProductionSchedule());

			if(alerts.upload_mdrProgDocument()<1||alerts.upload_mdrMilestoneDocument()<1){
				//ab.getSubmitbutton().click();
				log.info("files Madatory error ************");
				commonUtils.blindWait();
				driver().findElement(By.xpath("//div/a[contains(text(),'OK')]")).click();
				log.info("Mandatory alert accepted*********");
			}
			else{
				log.info("No mandatory alert###########");
				ab.getSubmitbutton().click();  
			}
			commonUtils.blindWait();		  
			login.logout();
		}

		//login as PL -- Monthly Review
		if(deliveryReview_dission.equals("Monthly Review")){
			login.loginPL();
			String taskName = PropertiesUtil.getPropValues("delivery_Review");
			basic.projectTaskName(taskName);

			/* Thread.sleep(4000);
		  driver.findElement(By.id("mdrMilestoneDocument-dropzone")).click();
		  ProjectMethods_Small_Works.uploadFile("Milestone Document 2.txt");*/

			if(alerts.upload_mdrProgDocument()<1){
				log.info("files not uploaded for upload_mdrProgDocument document");
				//cu.WaitForElementIDPresent("mdrPanelProductionSchedule-dropzone");
				driver.findElement(By.id("mdrProgDocument-dropzone")).click();
				ProjectMethods_Small_Works.uploadFile("Project programme document 2.txt");
				log.info("uploaded again");
			}

			ab.getComments().sendKeys("Delivery review to monthly review");

			//Checking file uploaded are not
			if(alerts.upload_mdrProgDocument()<1||alerts.upload_mdrMilestoneDocument()<1){
				driver().findElement(By.xpath("//input[@value='To Monthly Review']")).click();
				log.info("file Madatory error ************");
				commonUtils.blindWait();
				driver().findElement(By.xpath("//div/a[contains(text(),'OK')]")).click();
				log.info("Mandatory alert accepted*********");
			}
			else{
				log.info("No mandatory alert###########");
				driver().findElement(By.xpath("//input[@value='To Monthly Review']")).click();  
			}
			commonUtils.waitForPageToLoad();
			login.logout();
		}

		//Login as -- OD
		login.loginOD();
		basic.projectname_ReviewApproval();
		ab.getComments().sendKeys("Approved review");
		ab.getApprove_Button().click();
		login.logout();

		//login as PL -- Project Completed
		projectCompleted();

		//Login as -- OD
		login.loginOD();
		basic.projectname_ReviewApproval();
		ab.getComments().sendKeys("Project completed-yes");
		ab.getApprove_Button().click();
		login.logout();
	}
	//Delivery review Project complete
	public static void projectCompleted() throws IOException, InterruptedException{
		/*login as PL *********** Project Completed*/
		login.loginPL();
		String taskName = PropertiesUtil.getPropValues("delivery_Review");
		basic.projectTaskName(taskName);

		Thread.sleep(4000);
		driver.findElement(By.id("mdrProgDocument-dropzone")).click();
		ProjectMethods_Small_Works.uploadFile("Project programme document 3.txt");
		log.info("DR_ProgDocument uploaded");
		/*Thread.sleep(4000);
		  driver.findElement(By.id("mdrMilestoneDocument-dropzone")).click();
		  ProjectMethods_Small_Works.uploadFile("Milestone Document 3.txt");*/

		Thread.sleep(500);
		ab.getComments().sendKeys("Delivery review project completed with out changes");

		//Checking file uploads
		if(alerts.upload_mdrProgDocument()<1||alerts.upload_mdrMilestoneDocument()<1){
			ab.getProjectCompletedbutton().click();
			log.info("file Madatory error '''''''''''''");
			commonUtils.blindWait();
			alerts.getAlert_Accept_Ok().click();
			log.info("Mandatory alert accepted---------");
		}
		else{
			log.info("No mandatory alert*************");
			ab.getProjectCompletedbutton().click();  
		}
		//Delivery review project completion alert
		commonUtils.blindWait();
		deliveryReviewUi.getDataChange_Alert().click();
		login.logout();
	}
	/*Obtain Practical Completion task*/
	public void obtainpracticalcomplition() throws IOException, InterruptedException{

		login.loginPL();
		String taskName = PropertiesUtil.getPropValues("obtain_Practical_Completion");
		basic.projectTaskName(taskName);

		commonUtils.selectByVisibleText(pdp_ui.getOpc_certificateobtained(), ev.certificateobtained);
		if(ev.certificateobtained.equals("Yes")){
			pdp_ui.getOpcdoc_certificateobtained().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		commonUtils.selectByVisibleText(pdp_ui.getOpc_retationapplied(), ev.retationapplied);
		if(ev.retationapplied.equals("Yes")){
			pdp_ui.getOpcdoc_retentinApplied().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		commonUtils.selectByVisibleText(pdp_ui.getOpc_onmSubmitted(), ev.onmSubmitted);
		if(ev.onmSubmitted.equals("Yes")){
			pdp_ui.getOpcdoc_onmSubmitted().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		commonUtils.selectByVisibleText(pdp_ui.getOpc_snagListIdentified(), ev.snagListIdentified);
		if(ev.snagListIdentified.equals("Yes")){
			pdp_ui.getOpcdoc_snagListIdentified().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		commonUtils.selectByVisibleText(pdp_ui.getOpc_internalCompletionDocument(), ev.internalCompletionDocument);
		if(ev.internalCompletionDocument.equals("Yes")){
			pdp_ui.getOpcdoc_internalCompletionDocument().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		ab.getComments().sendKeys("Obtain practical complition");
	}
	/*Post Practical completion task*/
	public void postpracticalcomplition() throws IOException, InterruptedException{

		login.loginPL();
		//Thread.sleep(1000);
		String taskName = PropertiesUtil.getPropValues("post_Practical_Completion");
		basic.projectTaskName(taskName);
		//Thread.sleep(1000);
		commonUtils.selectByVisibleText(pdp_ui.getPpc_finalAccountAgreement(), ev.finalAccountAgreement);
		if(ev.finalAccountAgreement.equals("Yes")){
			pdp_ui.getPpcdoc_finalAccountAgreement().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		commonUtils.selectByVisibleText(pdp_ui.getPpc_finalRetentionPaid(), ev.finalRetentionPaid);
		if(ev.finalRetentionPaid.equals("Yes")){
			pdp_ui.getPpcdoc_finalRetentionPaid().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		commonUtils.selectByVisibleText(pdp_ui.getPpc_projectDocumentArchived(), ev.projectDocumentArchived);
		if(ev.projectDocumentArchived.equals("Yes")){
			pdp_ui.getPpcdoc_projectDocumentArchived().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		commonUtils.selectByVisibleText(pdp_ui.getPpc_projectDebrief(), ev.projectDebrief);
		commonUtils.selectByVisibleText(pdp_ui.getPpc_subContractAccountSettled(), ev.subContractAccountSettled);
		if(ev.subContractAccountSettled.equals("Yes")){
			pdp_ui.getPpcdoc_subContractAccountSettled().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		commonUtils.selectByVisibleText(pdp_ui.getPpc_closureofProject(), ev.closureofProject);
		if(ev.closureofProject.equals("Yes")){
			pdp_ui.getPpcdoc_closureofProject().click();
			ProjectMethods_Small_Works.linktoFileupload();
			Thread.sleep(1000);
		}
		commonUtils.selectByVisibleText(pdp_ui.getPpc_bondsGuaranteesResolved(), ev.bondsGuarantees_Resolved);
		JavascriptExecutor js=(JavascriptExecutor)Driver.driver();
		js.executeScript("scroll(0,350)");//TO Scroll down
		if(ev.bondsGuarantees_Resolved.equals("Yes")){
			pdp_ui.getPpcdoc_bondsGuaranteesResolved().click();
			ProjectMethods_Small_Works.linktoFileupload();
			Thread.sleep(1000);
		}
		ab.getComments().sendKeys("Post practical complition");
	}
	public void permission_to_Commence() {
		try {
			login.loginCL();
			basic.projectname();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} 
		driver.findElement(By.id("fileList_flm_ongoingOrderAcceptanceDocs")).click();
		try {
			ProjectMethods_Small_Works.linktoFileupload();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		ab.getComments().sendKeys("CL response...");
		tandCver.submitT_Creview.click();
		try {
			login.logout();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}