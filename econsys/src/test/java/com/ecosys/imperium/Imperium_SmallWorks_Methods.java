package com.ecosys.imperium;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import POC.econsys.RandomNumber;

import com.econsys.Projects.*; 
import com.econsys.UIobjectrepositary.*;
import com.econsys.matrix.MatrixProjects;
import com.econsys.Genriclibrery.*;
import com.econsys.SmallWorks.ProjectMethods_Small_Works;
import com.econsys.TestData.*;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class Imperium_SmallWorks_Methods extends Driver{
	//Page factory
	Logger log = Logger.getLogger(Imperium_SmallWorks_Methods.class.getName());
	RTQForm_Ui rtq = PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
	static Preparequote prepare_Quoteui = PageFactory.initElements(Driver.driver(), Preparequote.class);
	static ActionButtonsUi ab = PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	static smallWorkPageElements smallWorks_PageElements = PageFactory.initElements(Driver.driver(),smallWorkPageElements.class);
	static Salestooperation sales = PageFactory.initElements(Driver.driver(), Salestooperation.class);
	static AppointkeystaffandCommerSuitUi appointKeyStaff_CommercialSuite_Ui = PageFactory.initElements(Driver.driver(), AppointkeystaffandCommerSuitUi.class);
	static CosCommitQuoteStatusUi CosCommit_Quote_StatusUi = PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	LoginPageui loginui = PageFactory.initElements(Driver.driver(), LoginPageui.class);
	PDPui pdp_Ui = PageFactory.initElements(Driver.driver(),PDPui.class);
	AppointkeystaffandCommerSuitUi appointKeyStaff_CommercialSuite_Uielements = PageFactory.initElements(Driver.driver(), AppointkeystaffandCommerSuitUi.class);
	static TandCverification tandCverification = PageFactory.initElements(driver(), TandCverification.class);
	DeliveryReviewUi deliveryReviewUi = PageFactory.initElements(Driver.driver(), DeliveryReviewUi.class);

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
	static String expenditure;

	public void smallworkForm() throws  ClassNotFoundException, SQLException{
		try{
			int rownum= wb.getrowNum(2);

			login.loginSL();
			rtq.getNewform().click();
			rtq.getSmallWorks().click();
			commonUtils.waitForPageToLoad();
			rtq.getProjectName().sendKeys(ev.projectName());
			rtq.getCustomerName().sendKeys(wb.getXLData(3, 1, 1));
			commonUtils.selectByIndex(rtq.getSmallWorksType(), 1);
			//anticipated date
			rtq.getAnticipatedDate().click();
			driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/button")).click();
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
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
		}
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
	//Used in TC to disable the unwanted fields
	public void prepareRevisedQuote2() throws InterruptedException, IOException{
		//Project name
		String taskName = PropertiesUtil.getPropValues("prepare_Revised_Quote");
		basic.projectTaskName(taskName);
		commonUtils.blindWait();
		//Delete the bid document first
		driver.findElement(By.xpath("//*[@id='bidSheet_multifile']//a[contains(text(),'Delete')]")).click();
		prepare_Quoteui.getCp2cp3biddoc().click();
		ProjectMethods_Small_Works.linktoFileupload();	
		commonUtils.selectByVisibleText(prepare_Quoteui.getCp2cp3bidsheet(), wb.getXLData(15, 5, 0));
		prepare_Quoteui.getComments().sendKeys("rePrepare quote...");
	}

	//status of submitted quote 
	public void statusQuotesubmit_(String customerCommitmentType,String quoteStatus) throws IOException, InterruptedException{

		commonUtils.waitForPageToLoad();
		String taskName = PropertiesUtil.getPropValues("status_ofSubmitted_Quote");
		basic.projectTaskName(taskName);
		commonUtils.waitForPageToLoad();
		log.info("quotestatuscp2cp3 : "+quoteStatus);

		if(quoteStatus.equals(ev.quoteStatusCCARecived)){
			commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getQuoteStatus(),ev.quoteStatusCCARecived);
			commonUtils.waitForPageToLoad();
			commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getCustomerCommitmentType(), customerCommitmentType);

			if(!"Verbal Commitment Received - Under Review".equals(customerCommitmentType)){
				//select creaditworthy
				commonUtils.selectByVisibleText(tandCverification.getCreditWorthy(), ev.select_Yes);
				boolean ds = tandCverification.getCreditWorthy().isDisplayed();
				log.info("asdsd---"+ds);
				commonUtils.blindWait();
				System.out.println(driver().findElement(By.xpath("//*[@id='st_on_what_basis']")).isDisplayed());
				driver().findElement(By.xpath("//*[@id='st_on_what_basis']")).sendKeys("on_what_basis");
				driver().findElement(By.xpath("//*[@id='st_on_what_basis']")).isDisplayed();
				
				commonUtils.blindWait();
				log.info("go for upload...");
				boolean dropZoneafiles = driver.findElement(By.xpath("//*[@id='file_upload_tr']")).isDisplayed();
				log.info("dropZoneafiles---"+dropZoneafiles);
				
				
				boolean dropZoneafile = driver.findElement(By.xpath("//*[@id='file_upload_tr']//div[@id='quoteSubmitStatus-dropzone']")).isDisplayed();
				boolean dropZoneafil = driver.findElement(By.xpath("//*[@id='file_upload_tr']//div[@id='quoteSubmitStatus-dropzone']")).isEnabled();
				log.info("dropZoneafiles---"+dropZoneafile);
				log.info("dropZoneafil"+dropZoneafil);
				commonUtils.blindWait();
				driver.findElement(By.xpath("//*[@id='file_upload_tr']//div[@id='quoteSubmitStatus-dropzone']")).click();
				ProjectMethods_Small_Works.uploadFile("Logfails - Copy (19).txt");
				log.info("uploaded...");
				/*CosCommit_Quote_StatusUi.getUploadDoc_StatusofSubmitQuote().click();
				ProjectMethods_Small_Works.linktoFileupload();*/
			}
			
			ab.getComments().sendKeys("Quote status updated as "+quoteStatus +" as "+ev.quoteStatusCCARecived);
			CosCommit_Quote_StatusUi.getSubmit().click();
			CCAlogic_(ev.customerCommitmentType);
		}

		else if(quoteStatus.equals(ev.quoteStatusAmendBid)){
			commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getQuoteStatus(),ev.quoteStatusAmendBid);
			ab.getComments().sendKeys("Quote status is Amend Bid");
			CosCommit_Quote_StatusUi.getSubmit().click();	 
			try {
				prepareRevisedQuote();

				commonUtils.selectByVisibleText(prepare_Quoteui.getExecp3(),ev.exeCP3);
				prepare_Quoteui.getQuoteprepared().click();
				login.logout();
				//Need to check
				basic.pathdessioncp2cp3_Mat(ev.eSizertq3,ev.locationrtq3);
				if(ev.ourformat.equals("No")){
					MatrixProjects.clApproval();
				}
				if((ev.exeCP3.equals("Yes"))){
					basic.boardApproval();
				}
				ProjectMethods_Small_Works.submit_Quoteform();
				status_ResubmittedQuote_();
			}
			catch (Exception e) {
			}
		}

		//Select Amend bid and submit the quote status
		else if(quoteStatus.equals(ev.quoteStatusAmendBidSubmit)){

			commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getQuoteStatus(),ev.quoteStatusAmendBid);
			ab.getComments().sendKeys("Quote status is Amend Bid");
			CosCommit_Quote_StatusUi.getSubmit().click();	 
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
			CCAlogic_(ev.customerCommitmentType);
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

				commencmentofWork_Yes();
				commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getExeCP4(), ev.execp4);
				basic.submit_Logout();
			}
			//Does LOI Received specifically request for commencement of work? = 'NO'
			if(ev.loi_Commencement.equals("No")){

				commencmentofWork_No();
				//Select explicit cp4 approval
				commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getExeCP4(), ev.execp4);
				basic.submit_Logout();
				commonUtils.waitForPageToLoad();
			}
			TaskCP3CP4.TandCreview();
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
	public static void commencmentofWork_Yes(){
		commonUtils.waitForPageToLoad();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getAny_limitation_to_scope(), ev.any_limitation_to_scope);
		//Expenditure edit
		commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getAny_limitation_to_expenditure(), ev.any_limitation_to_expenditure);
		if(ev.any_limitation_to_expenditure.equals("Yes")){

			try {
				expenditure = wb.getXLData(1, 5, 2);
			} catch (IOException e) {
				e.printStackTrace();
			}
			CosCommit_Quote_StatusUi.getExpenditureLimit().sendKeys(expenditure);
		}
		commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getAny_Time_limit_to_Instructions(), ev.any_Time_limit_to_Instructions);
		commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getAny_other_Review(), ev.any_other_Review);

		commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.st_any_terms_and_conditions, ev.emailLoi_introduce_TandC);

		if(ev.any_limitation_to_scope.equals("Yes")||ev.any_limitation_to_expenditure.equals("Yes")||
				ev.any_Time_limit_to_Instructions.equals("Yes")||ev.any_other_Review.equals("Yes")||
				ev.emailLoi_introduce_TandC.equals("Yes"))
		{
			driver().findElement(By.xpath("//input[@id='fileList_flm_LoiDocs']")).click();
			try {
				ProjectMethods_Small_Works.linktoFileupload();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		if(ev.any_limitation_to_scope.equals("No")&&ev.any_limitation_to_expenditure.equals("No")&&
				ev.any_Time_limit_to_Instructions.equals("No")&&ev.any_other_Review.equals("No")&&
				ev.emailLoi_introduce_TandC.equals("No"))
		{
			driver().findElement(By.xpath("//input[@id='fileList_flm_slreponseDocs']")).click();
			try {
				ProjectMethods_Small_Works.linktoFileupload();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}

	}
	public static void commencmentofWork_No(){

		driver.findElement(By.xpath("//textarea[@id='response_text']")).sendKeys("Prepared Respponse");
	}
	public void apointkeystaf(String projectorSW) throws IOException, InterruptedException{
		login.loginOD();
		String taskName = PropertiesUtil.getPropValues("appoint_key_staff");
		basic.projectTaskName(taskName);
		commonUtils.selectByVisibleText(appointKeyStaff_CommercialSuite_Uielements.getLeadEL(), ev.el);
		commonUtils.selectByVisibleText(appointKeyStaff_CommercialSuite_Uielements.getLeadCL(), ev.cl);
		commonUtils.selectByVisibleText(appointKeyStaff_CommercialSuite_Uielements.getLeadPL(), ev.pl);

		commonUtils.selectByVisibleText(appointKeyStaff_CommercialSuite_Ui.getprojectorSmallWorks(), projectorSW);
		RandomNumber radomNum=new RandomNumber();
		int num=radomNum.randumNumber();
		appointKeyStaff_CommercialSuite_Uielements.getJobid().sendKeys(""+num);
		ab.getComments().sendKeys("Appoint Key staff");
		ab.getSubmitbutton().click();
		login.logout();
	}

	public void cummercialSuite_() throws InterruptedException, IOException{
		try{
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

			appointKeyStaff_CommercialSuite_Uielements.getSuspend_Period().sendKeys("25");

			commonUtils.selectByVisibleText(appointKeyStaff_CommercialSuite_Uielements.getScheduleOfRatesNeeded(), ev.select_No);
			/*appointKeyStaff_CommercialSuite_Uielements.getproductionSheduleDocCommercialSuite().click();
			ProjectMethods_Small_Works.linktoFileupload();*/

			appointKeyStaff_CommercialSuite_Uielements.getPayment_Request_document().click();
			ProjectMethods_Small_Works.linktoFileupload();

			ab.getComments().sendKeys("Commercial Suite and Application for Payment");
			ab.getSubmitbutton().click();
			//Thread.sleep(1000);
			login.logout();
		} catch (Exception e) {
			e.printStackTrace();
		} 
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
			List<WebElement> opacce = Driver.driver().findElements(By.xpath("//td[@aria-describedby='salesToOperationGrid_action']/select"));
			log.info("opacce---"+opacce);
			for(int j=0;j<opacce.size();j++){
				commonUtils.waitForPageToLoad();	
				commonUtils.selectByVisibleText(opacce.get(j),"Yes");
			}
			commonUtils.selectByVisibleText(appointKeyStaff_CommercialSuite_Ui.getMeetingwithsales(),wb.getXLData(6, 9, 0));
			//customer commitment type based delegate option selection
			if(ev.customerCommitmentType.equals(ev.customerCommitmentType_LOI)||ev.customerCommitmentType.equals(ev.customerCommitmentType_Email)
					||ev.customerCommitmentType.equals(ev.customerCommitmentType_Verbal)){
				int a = driver.findElements(By.xpath("//select[@id='st_cCChangeTaskOwner']")).size();
				if(a>0){
				commonUtils.selectByVisibleText(driver.findElement(By.xpath("//select[@id='st_cCChangeTaskOwner']")),"Yes");
				}
			}
			prepare_Quoteui.getComments().sendKeys("Operation Acceptance Form ...");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public void pdp() {
		try{
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
			pdp_Ui.getProdOfAppValueForecastGridPager_left().click();//Add new button
			commonUtils.blindWait();
			pdp_Ui.getSubmitedBy().sendKeys("Me");
			//pdp_Ui.getInvoiceNumber().sendKeys("15");Removed

			pdp_Ui.getContractWorks().sendKeys(""+ev.overallSell);
			pdp_Ui.getCommentsMilestone().sendKeys("Application is added...");
			pdp_Ui.getSavemilestone().click();

			/*Mile stone document removed 
			Driver.driver().findElement(By.xpath("//input[@id='fileList_flm_milestoneDocument']")).click();
			ProjectMethods_Small_Works.linktoFileupload();*/
			//Project Report settings
			commonUtils.selectByVisibleText(pdp_Ui.getReportIntverval_PDP(),ev.select_No);
			ab.getComments().sendKeys("PDP form ...");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	//Delivery review submit
	public void submit_Delivery_Review() throws InterruptedException, IOException{

		login.loginPL();
		basic.pnDeliveryReview();
		Driver.driver().findElement(By.id("mdrProgDocument-dropzone")).click();
		ProjectMethods_Small_Works.uploadFile("Project programme document 1.txt");
		//Removed
		/*Driver.driver().findElement(By.id("mdrMilestoneDocument-dropzone")).click();
		ProjectMethods_Small_Works.uploadFile("Milestone Document 1.txt");*/

		ab.getComments().sendKeys("Delivery review ---"+ev.deliveryReview_dission_Submit);
	}
	//Delivery review monthly review
	public void review_Delivery_Review() throws IOException, InterruptedException{

		login.loginPL();
		basic.pnDeliveryReview();

		Driver.driver().findElement(By.id("mdrProgDocument-dropzone")).click();
		ProjectMethods_Small_Works.uploadFile("Project programme document 2.txt");

		ab.getComments().sendKeys("Delivery review ---"+ev.deliveryReview_dission_MonthlyReview);
	}
	//Delivery review project completed
	public void project_complted_DeliveryReview() throws IOException, InterruptedException{

		login.loginPL();
		basic.pnDeliveryReview();

		Driver.driver().findElement(By.id("mdrProgDocument-dropzone")).click();
		ProjectMethods_Small_Works.uploadFile("Project programme document 3.txt");

		ab.getComments().sendKeys("Delivery review ---"+ev.deliveryReview_dission_ProjectCompleted);
		Driver.driver().findElement(By.xpath("//input[@value='Project Completed']")).click();
		commonUtils.blindWait();
		Driver.driver().findElement(By.xpath("//a[@class='btn btn-small btn-info']")).click();
		commonUtils.blindWait();
		login.logout();
	}
	//Delivery Review
	public void deliveryreview(String deliveryReview_dission) throws IOException, InterruptedException{
		Imperium_SmallWorks_Methods delivery_ReviewMethods = new Imperium_SmallWorks_Methods();
		login.url();
		//Delivery Review -- submit
		if(deliveryReview_dission.equals(ev.deliveryReview_dission_Submit)){
			delivery_ReviewMethods.submit_Delivery_Review();
			ab.getSubmitbutton().click();
			login.logout();
		}
		//Delivery Review -- Monthly Review
		if(deliveryReview_dission.equals(ev.deliveryReview_dission_MonthlyReview)){
			delivery_ReviewMethods.review_Delivery_Review();
			deliveryReviewUi.getToMonthlyReview().click();
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