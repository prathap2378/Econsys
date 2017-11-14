package com.econsys.Projects;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.econsys.Genriclibrery.*;
import com.econsys.SmallWorks.ProjectMethods_Small_Works;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.*;
import com.econsys.matrix.MatrixProjects;

/**
 * @author bhanu.pk
 * Monorail class consists of end to end(CP1-CP9) flow
 along with methods
 * Assign sales leader
 * Prepare_Quote
 * Prepare_Quote CP2-CP3
 * Submit Quote
 * Status of submit Quote/Resubmit quote
 * Board approval */

public class Monorail extends Driver {
	
	private static Logger log = Logger.getLogger(Monorail.class.getName());
	//Page UI classes
	public static Preparequote prepare_Quoteui=PageFactory.initElements(Driver.driver(), Preparequote.class);
	static RTQForm_Ui nrtq=PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
	Assignsalesleader sla=PageFactory.initElements(Driver.driver(), Assignsalesleader.class);
	static TandCverification tandCver = PageFactory.initElements(driver(), TandCverification.class);
	CosCommitQuoteStatusUi ccq=PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	static ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	static Salestooperation so=PageFactory.initElements(Driver.driver(),Salestooperation.class);
	static AppointkeystaffandCommerSuitUi ak=PageFactory.initElements(Driver.driver(), AppointkeystaffandCommerSuitUi.class);
	static PDPui pdp_ui=PageFactory.initElements(Driver.driver(),PDPui.class);
	static Alerts alerts = PageFactory.initElements(Driver.driver(), Alerts.class);
	Actions actions = new Actions(driver);
	//import classes
	static Workbook wb=new Workbook();
	static Monorail rtq=new Monorail();
	static ReviewInvolve ri=new ReviewInvolve();
	static TasksCP4toCP5 g45=new TasksCP4toCP5();
	static TaskCP3CP4 g34=new TaskCP3CP4();
	static Basic b=new Basic();
	static Login login=new Login();
	static TasksCP5toCP9 pdop=new TasksCP5toCP9();
	static EconsysVariables ev = new EconsysVariables();
	static ProjectMethods_Small_Works projectMethods_Small_Works = new ProjectMethods_Small_Works();
	static CommonUtils commonUtils = new CommonUtils();
	
	//Variables
	/*public static String estimatedSize;
	public static String location;*/
	
	static int num;
	String overallSell;
	String filepath=System.getProperty("user.dir");
	
	//Monorail flow 
	@Test(invocationCount = 1,threadPoolSize = 1)
	//invocationCount = 10,threadPoolSize = 1
	public void test1() throws Exception {
		System.out.printf("%n[START] Thread Id : %s is started!", Thread.currentThread().getId());
		monorailTestFlow(ev.estimatedSize,ev.location);
		System.out.printf("%n[END] Thread Id : %s", Thread.currentThread().getId());
	}
	//Test method
	@Test
	public static void monorailTestFlow(String estimatedSize,String location) throws Exception{
		//******URL&&&&&&&&&
		login.url();

		//*****Login as genral user******
		commonUtils.waitForPageToLoad();
		//boolean elementexist=driver.findElements(By.cssSelector("input[id='_58_emailInput'][name='_58_login']")).size()>0;
		//if(elementexist)
		login.user();
		//****intiation of rtq form*********
	    b.rtqForm(estimatedSize,location);
	    //rtq submit externally
	    b.submit_Logout();
		
		//***********CP1 exe dession************
		if((estimatedSize.equals("D 500+"))||(location.equals("Other"))) {
			b.boardApproval();
		}

		//Assign Sales Leader
		String userName = wb.getXLData(1, 0,0);
		String sl=wb.getXLData(10, 0, 0);
		
		if(!sl.equals(userName)){
			rtq.ASL();
		}
			
		//Prepare Quote
		rtq.prepare_Quote();
		commonUtils.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),ev.exeCP2);
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();
		
		b.pathdession(estimatedSize,location);
		//**********CP2 exe dession**************
		if((ev.ourformat.equals("No"))||(ev.bidsheetauthorised.equals("No"))||(ev.exeCP2.equals("Yes"))){
			b.boardApproval();
		}
		rtq.submitQuote();
		rtq.statusQuotesubmit(ev.customerCommitmentType, ev.quote_StatusCp2Cp3);
		
		//**********CP4 exe dession New Flow**************
		if((ev.clarification.equals("No"))||(ev.execp4.equals("Yes"))){
			b.boardApproval();
		}
		g45.apointkeystaf();
		g45.commerSuit();
        //Sales to operation hand-over
		g45.salestoOperation();
		
  	    commonUtils.selectByVisibleText(so.getExeCP5(),ev.exe5_SalestoOper);
  	    ab.getComments().sendKeys("Sales to operation");
  	    ab.getSubmitbutton().click();
  	    login.logout();
  	    
  	    //operation hand-over
        g45.operationAcceptance();
  	  	commonUtils.selectByVisibleText(ak.getExeOperationAcceptanceCP5(), ev.exeCP5_OperationAccep);
  	  	ab.getComments().sendKeys("Operations Acceptance");
  	  	ab.getAcceptOperationAcceptance().click();
  	  	login.logout();
		
  	  	//**********CP5 exe dession **********
        if(ev.exe5_SalestoOper.equals("Yes")||(ev.exeCP5_OperationAccep.equals("Yes")||(ev.draftproduced.equals("No")))){
			b.boardApproval();
		}
        //driver.close();
		pdop.pdp();
		commonUtils.selectByVisibleText(pdp_ui.getExecp6(),ev.execp6);
		commonUtils.waitForPageToLoad();
		ab.getSubmitbutton().click();
		commonUtils.waitForPageToLoad();
		login.logout();
		//**********CP6 exe dession **********
		if(ev.execp6.equals("Yes")){
			b.boardApproval();
		}
		//driver.close();
		pdop.deveryreview();
		//**********OD approval **************
		pdop.obtainpracticalcomplition();
		
		commonUtils.selectByVisibleText(pdp_ui.getOpc_cp8(), ev.execp8);
		ab.getSubmitbutton().click();
		login.logout();
		//***********CP8 exe dession **********
		if(ev.certificateobtained.equals("No")||ev.retationapplied.equals("No")||ev.onmSubmitted.equals("No")||ev.snagListIdentified.equals("No")||ev.internalCompletionDocument.equals("No")||ev.execp8.equals("Yes")){
			b.boardApproval();
		}
		pdop.postpracticalcomplition();
		commonUtils.selectByVisibleText(pdp_ui.getPpc_cp9(), ev.execp9);
		ab.getSubmitbutton().click();
		login.logout();
		//***********CP9 exe dession **********
		if(ev.finalAccountAgreement.equals("No")||ev.finalRetentionPaid.equals("No")||ev.projectDocumentArchived.equals("No")||ev.projectDebrief.equals("No")||ev.subContractAccountSettled.equals("No")||ev.closureofProject.equals("No")||ev.bondsGuarantees_Resolved.equals("No")||ev.execp9.equals("Yes")){
			b.boardApproval();
		}
		log.info("Monorail Test script ended....");
	
	}
	
	//***************Assign sales leader task **************
	 public void ASL() throws InterruptedException, IOException {
		 login.loginSD();
			commonUtils.blindWait();
			ab.getViewalltasks().click();
			String taskName = PropertiesUtil.getPropValues("ASL");
			b.projectTaskName(taskName);
			commonUtils.selectByVisibleText(sla.getSalesleader(), ev.sl);
			sla.getComments().sendKeys("Asigne sales leader");
			//Allocate sales leader
			sla.getAllocate().click();
			login.logout();
		}
	 //********Prepare Quote task***********
	 public void prepare_Quote() throws InterruptedException, IOException {
		 
		 login.loginSL();
		 commonUtils.blindWait();
		 String taskName = PropertiesUtil.getPropValues("prepare_Quote");
		 b.projectTaskName(taskName);
		 commonUtils.blindWait();
		 //Matrix specific change Estimated size and location
		 String orgName = driver.findElement(By.xpath("//*[@id='breadcrumbs']/ul/li[1]/a[text()='"+ev.org_Name+"']")).getText();
		 if(ev.org_4eg.equalsIgnoreCase(orgName)){
			 //RTQ 2 in prepare quote
			 //String eSizertq2 = wb.getXLData(2, 4, 1);
			 String locationrtq2 = wb.getXLData(4, 4, 1);
			 //commonUtils.selectByVisibleText(nrtq.geteSizertq2(), eSizertq2);
			 commonUtils.selectByVisibleText(nrtq.getLocationrtq2(), locationrtq2);
		 }
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

		 String overallCost = wb.getXLData(1, 4, 2);
		 prepare_Quoteui.getOverallProjectCost().sendKeys(overallCost);
		  
		 overallSell = wb.getXLData(1, 5, 2);
		 prepare_Quoteui.getOverallProjectSell().sendKeys(overallSell);
		 
		 //Reading cost and sell values
		 wb.prepareQuote_BidInfoDetails();
		 prepare_Quoteui.getComments().sendKeys("Prepare Quote");
	 }
	 
	//********Prepare Quote task***********
		 public void prepare_Quote2() throws InterruptedException, IOException {
			 
			 login.loginSL();
			 String taskName = PropertiesUtil.getPropValues("prepare_Quote");
			 b.projectTaskName(taskName);
			 commonUtils.blindWait();
			 commonUtils.selectByVisibleText(prepare_Quoteui.getQuotationonourFormat(), ev.ourformat);
			 
			 commonUtils.waitForPageToLoad();
			 prepare_Quoteui.getQuotedocument_Linkfile().click();
			 ProjectMethods_Small_Works.linktoFileupload();
			 commonUtils.waitForPageToLoad();
			 
			 //prepare_Quoteui.getRiskopportunityregister_Linkfile().click();
			 //projectMethods_Small_Works.linktoFileupload();
			 
			 commonUtils.selectByVisibleText(prepare_Quoteui.getBidSheetAuthorised(), ev.bidsheetauthorised);
			  
			 prepare_Quoteui.getBidsheet_linkfile().click();
			 ProjectMethods_Small_Works.linktoFileupload();
			 commonUtils.waitForPageToLoad();
			 
			 prepare_Quoteui.getComments().sendKeys("Prepare Quote");
			 /*commonUtils.selectByVisibleText(quote_form.getExpliciteapprovalatgateway2(),exeCP2);
				  
			 quote_form.getQuoteprepared().click();
			 login.logout();*/
	     	 }

//*********SubmitQuote***********
	 public void submitQuote() throws IOException, InterruptedException{
		 login.loginSL();
		 String taskName = PropertiesUtil.getPropValues("submit_Quote");
		 b.projectTaskName(taskName);
		 Thread.sleep(1000);
		 ccq.getComments().sendKeys("Submit Quote");
		 ccq.getSubmit().click();
		 
	     }
//*********Re-SubmitQuote***********
	 public void resubmitQuote() throws IOException, InterruptedException{
		 login.loginSL();
		 b.pnReSubmitedQuote();
		 Thread.sleep(1000);
		 ccq.getComments().sendKeys("Submit Quote");
		 ccq.getSubmit().click();
		 
	     }
//*********status Quote Re-submit***********
	 //Status of resubmitted quote used for GA
	 public void status_Quote_Resubmit(String estimatedSize,String location) throws IOException, InterruptedException{
		 //login.loginSL();
		 commonUtils.waitForPageToLoad();
		 String taskName = PropertiesUtil.getPropValues("status_ofRe_SubmittedQuote");
		 b.projectTaskName(taskName);
		 commonUtils.waitForPageToLoad();
		 
		 commonUtils.selectByVisibleText(ccq.getQuoteStatus(),ev.quote_StatusCp3Cp4);
		 if(ev.quote_StatusCp3Cp4.equals("Customer Commitment Received")){ 
		 {
			 commonUtils.waitForPageToLoad();
			 
			 commonUtils.selectByVisibleText(ccq.getCustomerCommitmentType(), ev.customerCommitmentType);
			 
			 if(!"Verbal Commitment Received - Under Review".equals(ev.customerCommitmentType)){
				 ccq.getUploadDoc_StatusofSubmitQuote().click();
				 projectMethods_Small_Works.linktoFileupload();
		 		 }
		 }
		 ab.getComments().sendKeys("Quote status is Customer Commitment Received");
		 commonUtils.waitForPageToLoad();
		 
		 ab.getSubmitbutton().click();
		//Customer commitment acceptance logic
		 	customerCommitmentAcceptance_Logic(ev.customerCommitmentType);
		 }
		 
		 //******Amend bid******
		 else if(ev.quote_StatusCp3Cp4.equals("Amend Bid")){
		 	 
		 rtq.prepareQuotecp2cp3();
		 b.pathdessioncp2cp3(estimatedSize,location);
		 
		 	if((ev.ourformat.equals("Yes")&&ev.cp2cp3ourformat.equals("No"))||(ev.cp2cp3bidsheetauthorised.equals("No"))||(ev.exeCP3.equals("Yes"))){
		 		b.boardApproval();
			}
		 
		 rtq.resubmitQuote();
		 rtq.status_Quote_Resubmit(estimatedSize,location);
		 }
	 }
	  //status of submitted quote GA flow that used for Global associates
	 public void statusQuotesubmit(String customerCommitmentType,String quoteStatus) throws IOException, InterruptedException{
			
		 //login.loginSL();
		 commonUtils.waitForPageToLoad();
		 // String rtqType= driver.findElement(By.xpath("//tr[td[@title='"+wb.getXLData(1, 2, 1)+"']]/td[6]")).getText();
		 String taskName = PropertiesUtil.getPropValues("status_ofSubmitted_Quote");
		 b.projectTaskName(taskName);
		 commonUtils.waitForPageToLoad();
		 log.info("quotestatuscp2cp3 : "+quoteStatus);
		 
		 commonUtils.selectByVisibleText(ccq.getQuoteStatus(),quoteStatus);
		 commonUtils.waitForPageToLoad();
		 	if(quoteStatus.equals("Customer Commitment Received")){
		 		{
		 			commonUtils.selectByVisibleText(ccq.getCustomerCommitmentType(), customerCommitmentType);
		 			
		 			if(!"Verbal Commitment Received - Under Review".equals(customerCommitmentType)){
		 			ccq.getUploadDoc_StatusofSubmitQuote().click();
		 			projectMethods_Small_Works.linktoFileupload();
		 			}
		 		}
		 ab.getComments().sendKeys("Quote status updated as "+quoteStatus);
		 ccq.getSubmit().click();
		 
		//Customer commitment acceptance logic
		 	customerCommitmentAcceptance_Logic(customerCommitmentType);
		 	}
		 	
		 	else if(quoteStatus.equals("Amend Bid")){
		
			 ab.getComments().sendKeys("Quote status is Amend Bid");
			 ccq.getSubmit().click();	 
			 rtq.prepareQuotecp2cp3();
			 commonUtils.selectByVisibleText(prepare_Quoteui.getExecp3(),ev.exeCP3);
			 prepare_Quoteui.getQuoteprepared().click();
			 login.logout();
			 //Path
			 b.pathdessioncp2cp3(ev.estimatedSize,ev.location);
			 
			 if((ev.ourformat.equals("Yes")&&ev.cp2cp3ourformat.equals("No"))||(ev.cp2cp3bidsheetauthorised.equals("No"))||(ev.exeCP3.equals("Yes"))){
				 
				 b.boardApproval();
			 }
			 rtq.resubmitQuote();
			 rtq.status_Quote_Resubmit(ev.estimatedSize,ev.location);
		 	}
	 }
	 //used in review involve action buttons to submit status of submit quote as Ammend bid
	 public void statusofSubmitQuote(String customerCommitmentType,String quoteStatus) throws InterruptedException, IOException{
		//login.loginSL();
		 commonUtils.waitForPageToLoad();
		 // String rtqType= driver.findElement(By.xpath("//tr[td[@title='"+wb.getXLData(1, 2, 1)+"']]/td[6]")).getText();
		 String taskName = PropertiesUtil.getPropValues("status_ofSubmitted_Quote");
		 b.projectTaskName(taskName);
		 commonUtils.waitForPageToLoad();
		 log.info("quotestatuscp2cp3 : "+quoteStatus);
		 
		 commonUtils.selectByVisibleText(ccq.getQuoteStatus(),quoteStatus);
		 commonUtils.waitForPageToLoad();
		 	if(quoteStatus.equals("Customer Commitment Received")){
		 		{
		 			commonUtils.selectByVisibleText(ccq.getCustomerCommitmentType(), customerCommitmentType);
		 			
		 			if(!"Verbal Commitment Received - Under Review".equals(customerCommitmentType)){
		 			ccq.getUploadDoc_StatusofSubmitQuote().click();
		 			projectMethods_Small_Works.linktoFileupload();
		 			}
		 		}
		 ab.getComments().sendKeys("Quote status updated as "+quoteStatus);
		 ccq.getSubmit().click();
		 	}
		 	ab.getComments().sendKeys("Quote status is "+quoteStatus);
			ab.getSubmitbutton().click();
	 }
	 
		
	 //Customer commitment logic GA flow
	 public void customerCommitmentAcceptance_Logic(String customerCommitmentType) throws InterruptedException, IOException{
		 
		//customer commitment LOI/Email/Verbal received- status of submitted quote		 
		 	if(customerCommitmentType.equals("LOI Received - Under Review")||customerCommitmentType.equals("Email Received - Under Review")
		 			||customerCommitmentType.equals("Verbal Commitment Received - Under Review")){
		 		Thread.sleep(1000);
		 		String taskName = PropertiesUtil.getPropValues("customer_Commitment_Acceptance");
		 		b.projectTaskName(taskName);
		 		
		 		commonUtils.selectByVisibleText(driver.findElement(By.xpath("//select[@id='st_request_for_cw']")),ev.loi_Commencement);
		 		ab.getComments().sendKeys("Customer Commitment Type");
		 		
			//Does LOI Received specifically request for commencement of work? = 'YES'			 	
		 		if(ev.loi_Commencement.equals("Yes")){
		 			
			 	 		commonUtils.waitForPageToLoad();
			 	 		Thread.sleep(1000);
			 	 		commonUtils.selectByVisibleText(ccq.getAny_limitation_to_scope(), ev.any_limitation_to_scope);
			 	 		//Expenditure edit
			 	 		commonUtils.selectByVisibleText(ccq.getAny_limitation_to_expenditure(), ev.any_limitation_to_expenditure);
			 	 		if(ev.any_limitation_to_expenditure.equals("Yes")){
			 	 			String expenditure = wb.getXLData(1, 5, 2);
			 	 			ccq.getExpenditureLimit().sendKeys(expenditure);
			 	 		}
			 	 		commonUtils.selectByVisibleText(ccq.getAny_Time_limit_to_Instructions(), ev.any_Time_limit_to_Instructions);
			 	 		commonUtils.selectByVisibleText(ccq.getAny_other_Review(), ev.any_other_Review);
			 	 		
			 	 		if(ev.any_limitation_to_scope.equals("Yes")||ev.any_limitation_to_expenditure.equals("Yes")||
			 	 				ev.any_Time_limit_to_Instructions.equals("Yes")||ev.any_other_Review.equals("Yes"))
			 	 		{
			 	 			driver().findElement(By.xpath("//input[@id='fileList_flm_LoiDocs']")).click();
				 	 		projectMethods_Small_Works.linktoFileupload();	
			 	 		}
			 	 		commonUtils.selectByVisibleText(ccq.getExeCP4(), ev.execp4);
			 	 		b.submit_Logout();
			 	 		
			 	 		if(ev.any_limitation_to_scope.equals("No")||ev.any_limitation_to_expenditure.equals("No")||
			 	 				ev.any_Time_limit_to_Instructions.equals("No")||ev.any_other_Review.equals("No"))
			 	 		{
			 	 			g34.opsApproval();
			 	 		}
			 	 		
			 	 		if(ev.any_limitation_to_scope.equals("Yes")&&ev.any_limitation_to_expenditure.equals("Yes")&&
			 	 				ev.any_Time_limit_to_Instructions.equals("Yes")&&ev.any_other_Review.equals("Yes"))
			 	 		{
			 	 			g34.clApproval();
			 	 			
			 	 			g34.opsApproval();
			 	 		}
			 	 		
			 	 	//Board approval task
				 		b.boardApproval();
			 	 		b.boardApproval();
			 	 	
		 		}
		 	//Does LOI Received specifically request for commencement of work? = 'NO'
			 	if(ev.loi_Commencement.equals("No")){
			 		
			 		//Select explicit cp4 approval
			 		commonUtils.selectByVisibleText(ccq.getExeCP4(), ev.execp4);
			 		b.submit_Logout();
			 		commonUtils.waitForPageToLoad();
			 		commonUtils.blindWait();
			 		//alerts.getAlert_Accept_Yes();
			 		driver.findElement(By.xpath("//div/a[contains(text(),'Yes')]")).click();
						
					//Board approval task
			 		b.boardApproval();
			 		
			 		//Prepare response task
			 		login.loginSL();
			 		g34.prepareResponseSales();
			 		
			 		//Approve response to sales
			 		login.loginCL();
			 		ab.getComments().sendKeys("Commercial approval for sales respose");
			 		ab.getApprove_Button().click();
			 	}
		 	}

		 	else if(customerCommitmentType.equals("PO Received - Under Review")||customerCommitmentType.equals("Sub-Contract Received - Under Review"))
		 	{
			 g34.customercommit();
			 commonUtils.selectByVisibleText(ccq.getExeCP4(), ev.execp4);
			 b.submit_Logout();
		 	}
	 }

	 //Prepare quote Cp2-Cp3
	 public void prepareQuotecp2cp3() throws IOException, InterruptedException{
		 
		 commonUtils.waitForPageToLoad();
		 String taskName = PropertiesUtil.getPropValues("prepare_Revised_Quote");
		 b.projectTaskName(taskName);
		 commonUtils.blindWait();
		//Matrix specific change Estimated size and location
		 String orgName = driver.findElement(By.xpath("//*[@id='breadcrumbs']/ul/li[1]/a[text()='"+ev.org_Name+"']")).getText();
		 log.info("orgName____"+orgName);
		 if(ev.org_Matrixs.equalsIgnoreCase(orgName)){
			 //RTQ 3 in revised prepare quote
			 //String eSizertq3 = wb.getXLData(7, 4, 1);
			 String locationrtq3 = wb.getXLData(9, 4, 1);
			 //commonUtils.selectByVisibleText(nrtq.geteSizertq2(), eSizertq3);
			 commonUtils.selectByVisibleText(nrtq.getLocationrtq2(), locationrtq3);
		 }
		 
		 driver.findElement(By.xpath("//input[@id='fileList_flm_quoteDocument']")).click();
		 ProjectMethods_Small_Works.linktoFileupload();
		
		 commonUtils.selectByVisibleText(prepare_Quoteui.getCp2cp3gaformat(), ev.cp2cp3ourformat);
		 prepare_Quoteui.getCp2cp3biddoc().click();
		 ProjectMethods_Small_Works.linktoFileupload();
		 commonUtils.selectByVisibleText(prepare_Quoteui.getCp2cp3bidsheet(), ev.cp2cp3bidsheetauthorised);
		 ab.getComments().sendKeys("cp2-cp3 prepare quote");
		 
	 }
}