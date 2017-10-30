package com.ecosys.imperium;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.IModuleFactory;
import org.testng.Reporter;

import POC.econsys.RandomNumber;

import com.econsys.Projects.*; 
import com.econsys.UIobjectrepositary.*;
import com.econsys.matrix.MatrixProjects;
import com.econsys.Genriclibrery.*;
import com.econsys.SmallWorks.ProjectMethods_Small_Works;
import com.econsys.TestData.*;

public class Imperium_SmallWorks_Methods extends Driver{
	//Page factory
	Logger log = Logger.getLogger(Imperium_SmallWorks_Methods.class.getName());
	RTQForm_Ui rtq = PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
	static Preparequote prepare_Quoteui = PageFactory.initElements(Driver.driver(), Preparequote.class);
	static ActionButtonsUi ab = PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	static smallWorkPageElements smallWorks_PageElements = PageFactory.initElements(Driver.driver(),smallWorkPageElements.class);
	TandCverification TandCver = PageFactory.initElements(driver(), TandCverification.class);
	static Salestooperation sales = PageFactory.initElements(Driver.driver(), Salestooperation.class);
	static AppointkeystaffandCommerSuitUi appointKeyStaff_CommercialSuite_Ui = PageFactory.initElements(Driver.driver(), AppointkeystaffandCommerSuitUi.class);
	static CosCommitQuoteStatusUi CosCommit_Quote_StatusUi = PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	LoginPageui loginui = PageFactory.initElements(Driver.driver(), LoginPageui.class);
	PDPui pdp_Ui = PageFactory.initElements(Driver.driver(),PDPui.class);
	AppointkeystaffandCommerSuitUi appointKeyStaff_CommercialSuite_Uielements = PageFactory.initElements(Driver.driver(), AppointkeystaffandCommerSuitUi.class);
	//Import class
	static Login login = new Login();
	static Workbook wb = new Workbook();
	static TaskCP3CP4 g34=new TaskCP3CP4();
	static Basic basic = new Basic();
	Monorail monorail = new Monorail();
	static EconsysVariables ev = new EconsysVariables();
	boolean flag=false;
	static CommonUtils commonUtils = new CommonUtils();
	String filepath=System.getProperty("user.dir");
	String sell;

	public void smallworkForm() throws InterruptedException, IOException, ClassNotFoundException, SQLException{
		int rownum= wb.getrowNum(2);

		login.loginSL();
		rtq.getNewform().click();
		rtq.getSmallWorks().click();
		commonUtils.waitForPageToLoad();
		rtq.getProjectName().sendKeys(ev.projectName());
		rtq.getCustomerName().sendKeys(wb.getXLData(3, 1, 1));
		commonUtils.selectByIndex(rtq.getSmallWorksType(), 1);
		//Quote Info
		commonUtils.selectByVisibleText(prepare_Quoteui.getQuotationonourFormat(), wb.getXLData(2, 5, 0));
		prepare_Quoteui.getOverallProjectCost().sendKeys(wb.getXLData(1, 4, 2));
		sell = wb.getXLData(1, 5, 2);
		prepare_Quoteui.getOverallProjectSell().sendKeys(wb.getXLData(1, 5, 2));

		for(int i=1;i<=rownum;i++){
			prepare_Quoteui.getAddnewpopup().click();
			commonUtils.waitForPageToLoad();
			prepare_Quoteui.getCostCodeCategorytextfield().sendKeys(wb.getXLData(i, 0, 2));
			prepare_Quoteui.getCost().sendKeys(wb.getXLData(i, 1, 2));
			prepare_Quoteui.getSell().sendKeys(wb.getXLData(i, 2, 2));
			prepare_Quoteui.getSaveAddcostsellpopup().click();
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,250)", "");
		}
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor)driver).executeScript("setTimeout(function () { }, 0)");
		} else {
			throw new IllegalStateException("This driver does not support JavaScript!");
		}
		//Upload bid sheet
		commonUtils.blindWait();
		driver.findElement(By.xpath("//div[@id='bidSheet-dropzone']")).click();
		ProjectMethods_Small_Works.uploadFile("Logfails - Copy (20).txt");

		driver.findElement(By.xpath("//input[@id='fileList_flm_quoteDocument']")).click();
		ProjectMethods_Small_Works.linktoFileupload();
		commonUtils.selectByVisibleText(prepare_Quoteui.getBidSheetAuthorised(), wb.getXLData(4, 5, 0));
		prepare_Quoteui.getComments().sendKeys("Small Work form ...");
	}

	public boolean submit_SW_QuoteForm() throws InterruptedException,IOException{
		rtq.getSubmit().click();

		int sellValue_Configured = DBConnection.sell_Value_SmallWorks();
		int sell_Actual = Integer.parseInt(sell);
		String option_SmallWorks_or_Project = wb.getXLData(18,9,0);

		if(sell_Actual>sellValue_Configured){
			commonUtils.blindWait();
			Driver.driver().findElement(By.xpath("//a[text()='"+option_SmallWorks_or_Project+"']")).click();
			if(option_SmallWorks_or_Project.contentEquals("Yes")){
				Thread.sleep(2000);
				log.info("This is taking Project workflow, Currently saving the Project form");
				rtq.getSave().click();
				flag=true;
			}
			else{
				log.info("Continues with Small Works flow");
			}
		}
		//accept quote reference alert
		commonUtils.blindWait();
		rtq.getQouteRefrenceNumberAlert_Imperium().click();
		log.info("log out small work form");
		login.logout();
		log.info("loged out");
		return flag;
	}
	
	public void quoteForm_SubmitQuote() throws InterruptedException, IOException, ClassNotFoundException, SQLException{
		smallworkForm();
		flag = submit_SW_QuoteForm();
		log.info("Flag : "+flag);
		if(flag){
			Reporter.log("Small Works flow changed to Project, end this test");
		}
		else{
			if((ev.ourformat.equals("No"))||(ev.bidsheetauthorised.equals("No")))
				basic.boardApproval();
		}
		ProjectMethods_Small_Works.submit_Quoteform();
	}
	

	//prepare Revised Quite...
	public void prepareRevisedQuote() throws InterruptedException, IOException{

		//Project name
		String taskName = PropertiesUtil.getPropValues("prepare_Revised_Quote");
		basic.projectTaskName(taskName);
		commonUtils.blindWait();
		//Link file to bid document
		prepare_Quoteui.getCp2cp3biddoc().click();
		ProjectMethods_Small_Works.linktoFileupload();	
		//Link file to Quote document
		prepare_Quoteui.getCp2cp3_quote_doc().click();
		ProjectMethods_Small_Works.linktoFileupload();
		//Bid sheet value drop down value selection
		commonUtils.selectByVisibleText(prepare_Quoteui.getCp2cp3bidsheet(), wb.getXLData(15, 5, 0));
		prepare_Quoteui.getComments().sendKeys("rePrepare quote...");
	}
	//status of submitted quote - small works
	public void statusQuotesubmit_(String customerCommitmentType,String quoteStatus) throws IOException, InterruptedException{

		commonUtils.waitForPageToLoad();
		String taskName = PropertiesUtil.getPropValues("status_ofSubmitted_Quote");
		basic.projectTaskName(taskName);
		commonUtils.waitForPageToLoad();
		log.info("quotestatuscp2cp3 : "+quoteStatus);

		commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getQuoteStatus(),quoteStatus);
		commonUtils.waitForPageToLoad();
		if(quoteStatus.equals("Customer Commitment Received")){
			{
				commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getCustomerCommitmentType(), customerCommitmentType);

				if(!"Verbal Commitment Received - Under Review".equals(customerCommitmentType)){
					CosCommit_Quote_StatusUi.getUploadDoc_StatusofSubmitQuote().click();
					ProjectMethods_Small_Works.linktoFileupload();;
				}
			}
			ab.getComments().sendKeys("Quote status updated as "+quoteStatus);
			CosCommit_Quote_StatusUi.getSubmit().click();
			//Customer commitment acceptance logic
			Imperium_SmallWorks_Methods.CCAlogic_(customerCommitmentType);
		}
		else if(quoteStatus.equals("Amend Bid")){

			ab.getComments().sendKeys("Quote status is Amend Bid");
			CosCommit_Quote_StatusUi.getSubmit().click();	 
			prepareRevisedQuote();

			commonUtils.selectByVisibleText(prepare_Quoteui.getExecp3(),ev.exeCP3);
			prepare_Quoteui.getQuoteprepared().click();
			login.logout();
			if(ev.ourformat.equals("No")){
				MatrixProjects.clApproval(); 
			}
			if((ev.exeCP3.equals("Yes"))){
				basic.boardApproval();
			}
			ProjectMethods_Small_Works.submit_Quoteform();
			status_ResubmittedQuote_();
		}
	}
	//status of resubmit quote - small works
	public void status_ResubmittedQuote_() throws InterruptedException, IOException{
		//login.loginSL();
		commonUtils.waitForPageToLoad();
		String taskName = PropertiesUtil.getPropValues("status_ofRe_SubmittedQuote");
		basic.projectTaskName(taskName);
		commonUtils.waitForPageToLoad();

		commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getQuoteStatus(),ev.quote_StatusCp3Cp4);

		if(ev.quote_StatusCp3Cp4.equals("Customer Commitment Received")){ 
			{
				commonUtils.waitForPageToLoad();

				commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getCustomerCommitmentType(), ev.customerCommitmentType);

				if(!"Verbal Commitment Received - Under Review".equals(ev.customerCommitmentType)){
					CosCommit_Quote_StatusUi.getUploadDoc_StatusofSubmitQuote().click();
					ProjectMethods_Small_Works.linktoFileupload();;
				}
			}
			ab.getComments().sendKeys("Quote status is Customer Commitment Received");
			commonUtils.waitForPageToLoad();

			ab.getSubmitbutton().click();
			//Customer commitment acceptance logic
			Imperium_SmallWorks_Methods.CCAlogic_(ev.customerCommitmentType);
		}
		//******Amend bid******
		else if(ev.quote_StatusCp3Cp4.equals("Amend Bid")){

			prepareRevisedQuote();
			commonUtils.selectByVisibleText(prepare_Quoteui.getExecp3(),ev.exeCP3);
			prepare_Quoteui.getQuoteprepared().click();
			login.logout();

			if((ev.ourformat.equals("Yes")&&ev.cp2cp3ourformat.equals("No"))||(ev.cp2cp3bidsheetauthorised.equals("No"))||(ev.exeCP3.equals("Yes"))){

				basic.boardApproval();
			}
			ProjectMethods_Small_Works.submit_Quoteform();
			status_ResubmittedQuote_();
		}
	}
	//Customer commitment logic
	public static void CCAlogic_(String customerCommitmentType) throws InterruptedException, IOException{
		//customer commitment LOI/Email/Verbal received- status of submitted quote		 
		if(customerCommitmentType.equals(ev.customerCommitmentType_LOI)||customerCommitmentType.equals(ev.customerCommitmentType_Email)
				){
			commonUtils.waitForPageToLoad();
			String taskName = PropertiesUtil.getPropValues("customer_Commitment_Acceptance");
			basic.projectTaskName(taskName);
			commonUtils.selectByVisibleText(driver.findElement(By.xpath("//select[@id='st_request_for_cw']")),ev.loi_Commencement);
			ab.getComments().sendKeys("Customer Commitment Type");

			//Does LOI Received specifically request for commencement of work? = 'YES'			 	
			if(ev.loi_Commencement.equals("Yes")){

				commonUtils.waitForPageToLoad();
				Thread.sleep(1000);
				commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getAny_limitation_to_scope(), ev.any_limitation_to_scope);
				//Expenditure edit
				commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getAny_limitation_to_expenditure(), ev.any_limitation_to_expenditure);
				if(ev.any_limitation_to_expenditure.equals("Yes")){
					String expenditure = wb.getXLData(1, 5, 2);
					CosCommit_Quote_StatusUi.getExpenditureLimit().sendKeys(expenditure);
				}
				commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getAny_Time_limit_to_Instructions(), ev.any_Time_limit_to_Instructions);
				commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getAny_other_Review(), ev.any_other_Review);

				if(ev.any_limitation_to_scope.equals("Yes")||ev.any_limitation_to_expenditure.equals("Yes")||
						ev.any_Time_limit_to_Instructions.equals("Yes")||ev.any_other_Review.equals("Yes"))
				{
					driver().findElement(By.xpath("//input[@id='fileList_flm_LoiDocs']")).click();
					ProjectMethods_Small_Works.linktoFileupload();	
				}
				if(ev.any_limitation_to_scope.equals("No")&&ev.any_limitation_to_expenditure.equals("No")&&
						ev.any_Time_limit_to_Instructions.equals("Yes")&&ev.any_other_Review.equals("Yes"))
				{
					driver().findElement(By.xpath("//input[@id='fileList_flm_slreponseDocs']")).click();
					ProjectMethods_Small_Works.linktoFileupload();	
				}
				commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getExeCP4(), ev.execp4);
				basic.submit_Logout();

				TaskCP3CP4.TandCreview();
			}
			//Does LOI Received specifically request for commencement of work? = 'NO'
			if(ev.loi_Commencement.equals("No")){
				//Select explicit cp4 approval
				driver.findElement(By.xpath("//input[@id='fileList_flm_slreponseDocs']")).click();
				ProjectMethods_Small_Works.linktoFileupload();
				driver.findElement(By.xpath("//textarea[@id='response_text']")).sendKeys("Prepared Respponse");
				commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getExeCP4(), ev.execp4);
				basic.submit_Logout();
				commonUtils.waitForPageToLoad();

				TaskCP3CP4.TandCreview();
			}
		}
		//this is verbal
		else if(customerCommitmentType.equals(ev.customerCommitmentType_Verbal)){
			commonUtils.waitForPageToLoad();
			String taskName = PropertiesUtil.getPropValues("customer_Commitment_Acceptance");
			basic.projectTaskName(taskName);
			driver.findElement(By.xpath("//input[@id='fileList_flm_verbalCommitment']")).click();
			ProjectMethods_Small_Works.linktoFileupload();

			ab.getComments().sendKeys("Verbal commitment acceptance");
			basic.submit_Logout();

			g34.clApproval();
		}else if(customerCommitmentType.equals(ev.customerCommitmentType_PO)||customerCommitmentType.equals(ev.customerCommitmentType_SubCon))
		{
			//g34.customercommit();
			g34.scopeDocandContractValueVerification();
		}
	}

	public void apointkeystaf() throws IOException, InterruptedException{

		login.loginOD();
		String taskName = PropertiesUtil.getPropValues("appoint_key_staff");
		basic.projectTaskName(taskName);
		commonUtils.selectByVisibleText(appointKeyStaff_CommercialSuite_Uielements.getLeadEL(), ev.el);
		commonUtils.selectByVisibleText(appointKeyStaff_CommercialSuite_Uielements.getLeadCL(), ev.cl);
		commonUtils.selectByVisibleText(appointKeyStaff_CommercialSuite_Uielements.getLeadPL(), ev.pl);
		RandomNumber radomNum=new RandomNumber();
		int num=radomNum.randumNumber();
		appointKeyStaff_CommercialSuite_Uielements.getJobid().sendKeys(""+num);
		ab.getComments().sendKeys("Appoint Key staff");
		ab.getSubmitbutton().click();
		login.logout();
	}

	public void cummercialSuite_() throws InterruptedException, IOException{
		//Payment terms
		login.loginCL();
		commonUtils.blindWait();
		//String taskName = PropertiesUtil.getPropValues("commercial_Suite");
		basic.projectTaskName("Commercial Tasks Prior to Commencement");
		commonUtils.blindWait();
		log.info("commmercial suite payments ");
		commonUtils.selectByIndex(appointKeyStaff_CommercialSuite_Uielements.getDraftCommercialSuitProduced(),2);
		/*if(ev.draftproduced.equals("Yes")){
				  appointKeyStaff_CommercialSuite_Uielements.getPayment_Cycle_Document().click();
				  projectMethods_Small_Works.linktoFileupload();
			  }*/
		appointKeyStaff_CommercialSuite_Uielements.getPayment_Terms().sendKeys("15");
		appointKeyStaff_CommercialSuite_Uielements.getDays_From().sendKeys("3");

		appointKeyStaff_CommercialSuite_Uielements.getproductionSheduleDocCommercialSuite().click();
		ProjectMethods_Small_Works.linktoFileupload();

		appointKeyStaff_CommercialSuite_Uielements.getMilestoneDocInCommercialSuite().click();
		ProjectMethods_Small_Works.linktoFileupload();

		ab.getComments().sendKeys("Commercial Suite and Application for Payment");
		ab.getSubmitbutton().click();
		//Thread.sleep(1000);
		login.logout();
		//driver().quit();
	}
	//Sales to operation small works
	public void salesToOperation(){
		try {
			login.loginSL();
			basic.edit_Salesto_Operation();
			prepare_Quoteui.getAddnewpopup().click();
			smallWorks_PageElements.getDescription().sendKeys("Test1");
			prepare_Quoteui.getSaveAddcostsellpopup().click(); // Save button of Add New pop up

			smallWorks_PageElements.getUpload_sales().click();
			commonUtils.blindWait();
			smallWorks_PageElements.getLinkTofilebtn().click();
			commonUtils.blindWait();
			ProjectMethods_Small_Works.linktoFileupload();
			commonUtils.selectByVisibleText(sales.getMeeting(), wb.getXLData(22, 7, 0));

			String delegatetoPL=wb.getXLData(22,7,0);
			String customerCommitmentType= ev.customerCommitmentType;
			if(customerCommitmentType.equals("LOI Received - Under Review")||customerCommitmentType.equals("Email Received - Under Review")
					||customerCommitmentType.equals("Verbal Commitment Received - Under Review")){

				commonUtils.selectByVisibleText(sales.getDelegateTaskto_PL(),delegatetoPL);
			}
			sales.getComments().sendKeys("Sales to Operation form ...");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	//Operation acceptance
	public void operation_acceptance(){
		try{
			login.loginPL();
			commonUtils.blindWait();
			basic.edit_OperationAcceptance();
			List<WebElement> opacce=Driver.driver().findElements(By.xpath("//td[@aria-describedby='salesToOperationGrid_action']/select"));

			for(int j=0;j<opacce.size();j++){
				commonUtils.waitForPageToLoad();	
				commonUtils.selectByVisibleText(opacce.get(j),"Yes");
			}

			commonUtils.selectByVisibleText(appointKeyStaff_CommercialSuite_Ui.getMeetingwithsales(),wb.getXLData(6, 9, 0));

			//				  customer commitment type based delegate option selection
			String customerCommitmentType= ev.customerCommitmentType;
			if(customerCommitmentType.equals("LOI Received - Under Review")||customerCommitmentType.equals("Email Received - Under Review")
					||customerCommitmentType.equals("Verbal Commitment Received - Under Review")){
				int a = driver.findElements(By.xpath("//select[@id='st_cCChangeTaskOwnerOPS']")).size();
				if(a>0){
					commonUtils.selectByVisibleText(driver.findElement(By.xpath("//select[@id='st_cCChangeTaskOwnerOPS']")),"Yes");
				}
			}

			prepare_Quoteui.getComments().sendKeys("Operation Acceptance Form ...");
			/*ab.getReviewInvolveapprovebutton().click();
			   	login.logout();*/
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void pdp() throws IOException, InterruptedException {

		login.loginPL();
		basic.pnpdp();
		//deleting Delete links
		List<WebElement> deletebutton=Driver.driver().findElements(By.xpath("//a[starts-with(@id,'delete_')]"));
		for(int i=0;i<deletebutton.size();i++){
			commonUtils.blindWait();
			deletebutton.get(i).click();
			commonUtils.blindWait();
			Driver.driver().findElement(By.xpath("//a[@class='btn btn-small btn-info']")).click();
		}
		commonUtils.blindWait();
		pdp_Ui.getProject_Programme_document().click();
		ProjectMethods_Small_Works.linktoFileupload();
		//Value forecast grid details and adding milestone values
		commonUtils.blindWait();
		pdp_Ui.getAddnewvalueforcast().click();
		pdp_Ui.getInvoiceNumber().sendKeys("15");
		driver().findElement(By.xpath("//input[@id='milestoneDate']")).click();
		pdp_Ui.getDate().click();
		String milestonevalue = wb.getXLData(1,5, 2);
		pdp_Ui.getContractWorks().sendKeys(""+milestonevalue);
		pdp_Ui.getCommentsMilestone().sendKeys("Application is added...");
		pdp_Ui.getSavemilestone().click();

		Thread.sleep(1000);
		/*Mile stone document*/
		Driver.driver().findElement(By.xpath("//input[@id='fileList_flm_milestoneDocument']")).click();
		ProjectMethods_Small_Works.linktoFileupload();
		//Project Report settings
		commonUtils.selectByVisibleText(pdp_Ui.getReportIntverval_PDP(),ev.select_No);
		ab.getComments().sendKeys("PDP form ...");
	}
	//Delivery review submit
	public void submit_Delivery_Review() throws InterruptedException, IOException{

		login.loginPL();
		basic.pnDeliveryReview();
		Driver.driver().findElement(By.id("mdrProgDocument-dropzone")).click();
		ProjectMethods_Small_Works.uploadFile("Project programme document 1.txt");
		Driver.driver().findElement(By.id("mdrMilestoneDocument-dropzone")).click();
		ProjectMethods_Small_Works.uploadFile("Milestone Document 1.txt");

		ab.getComments().sendKeys("Delivery review submit");
	}
	//Delivery review monthly review
	public void review_Delivery_Review() throws IOException, InterruptedException{

		login.loginPL();
		basic.pnDeliveryReview();

		Driver.driver().findElement(By.id("mdrProgDocument-dropzone")).click();
		ProjectMethods_Small_Works.uploadFile("Project programme document 2.txt");
		Driver.driver().findElement(By.id("mdrMilestoneDocument-dropzone")).click();
		ProjectMethods_Small_Works.uploadFile("Milestone Document 2.txt");

		ab.getComments().sendKeys("Delivery review to monthly review");
	}
	//Delivery review project completed
	public void project_complted_DeliveryReview() throws IOException, InterruptedException{

		login.loginPL();
		basic.pnDeliveryReview();

		Driver.driver().findElement(By.id("mdrProgDocument-dropzone")).click();
		ProjectMethods_Small_Works.uploadFile("Project programme document 3.txt");
		Driver.driver().findElement(By.id("mdrMilestoneDocument-dropzone")).click();
		ProjectMethods_Small_Works.uploadFile("Milestone Document 3.txt");

		ab.getComments().sendKeys("Delivery review project completed with out changes");
		Driver.driver().findElement(By.xpath("//input[@value='Project Completed']")).click();
		commonUtils.blindWait();
		Driver.driver().findElement(By.xpath("//a[@class='btn btn-small btn-info']")).click();
		commonUtils.blindWait();
		login.logout();
	}
	//Delivery Review
	public void deliveryreview() throws IOException, InterruptedException{
		Imperium_SmallWorks_Methods delivery_ReviewMethods = new Imperium_SmallWorks_Methods();
		login.url();
		String deliveryReview_dission=wb.getXLData(10,12,0);
		//Delivery Review -- submit
		if(deliveryReview_dission.equals("Submit")){
			delivery_ReviewMethods.submit_Delivery_Review();
			ab.getSubmitbutton().click();
			login.logout();
		}
		//Delivery Review -- Monthly Review
		if(deliveryReview_dission.equals("Monthly Review")){
			delivery_ReviewMethods.review_Delivery_Review();
			Driver.driver().findElement(By.xpath("//input[@value='To Monthly Review']")).click();
			login.logout();
		}
		//Refereed from project methods small works
		ProjectMethods_Small_Works.od_approval();
		//Delivery Review -- Project completed
		delivery_ReviewMethods.project_complted_DeliveryReview();
		//Refereed from project methods small works
		ProjectMethods_Small_Works.od_approval();
	}
	/*Obtain Practical Completion task*/
	public void obtainpracticalcomplition() throws IOException, InterruptedException{

		login.loginPL();
		String taskName = PropertiesUtil.getPropValues("obtain_Practical_Completion");
		basic.projectTaskName(taskName);
		commonUtils.selectByVisibleText(pdp_Ui.getOpc_certificateobtained(), ev.certificateobtained);
		if(ev.certificateobtained.equals("Yes")){
			pdp_Ui.getOpcdoc_certificateobtained().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		commonUtils.selectByVisibleText(pdp_Ui.getOpc_retationapplied(), ev.retationapplied);
		if(ev.retationapplied.equals("Yes")){
			pdp_Ui.getOpcdoc_retentinApplied().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		commonUtils.selectByVisibleText(pdp_Ui.getOpc_onmSubmitted(), ev.onmSubmitted);
		if(ev.onmSubmitted.equals("Yes")){
			pdp_Ui.getOpcdoc_onmSubmitted().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		commonUtils.selectByVisibleText(pdp_Ui.getOpc_snagListIdentified(), ev.snagListIdentified);
		if(ev.snagListIdentified.equals("Yes")){
			pdp_Ui.getOpcdoc_snagListIdentified().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		commonUtils.selectByVisibleText(pdp_Ui.getOpc_internalCompletionDocument(), ev.internalCompletionDocument);
		if(ev.internalCompletionDocument.equals("Yes")){
			pdp_Ui.getOpcdoc_internalCompletionDocument().click();
			ProjectMethods_Small_Works.linktoFileupload();
		}
		ab.getComments().sendKeys("Obtain practical complition");
	}
}