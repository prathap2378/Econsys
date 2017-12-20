package com.econsys.econ4eg;
import java.io.IOException;

import org.apache.log4j.Logger;
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

public class Econsys4egProjects extends Driver {
	
	private static Logger log = Logger.getLogger(Econsys4egProjects.class.getName());
	//Page UI classes
	public static Preparequote prepare_Quoteui = PageFactory.initElements(Driver.driver(), Preparequote.class);
	static CommonUtils cu = PageFactory.initElements(Driver.driver(), CommonUtils.class);
	static RTQForm_Ui nrtq = PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
	static Assignsalesleader sla = PageFactory.initElements(Driver.driver(), Assignsalesleader.class);
	static CosCommitQuoteStatusUi ccq = PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	static ActionButtonsUi ab = PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	static Salestooperation so = PageFactory.initElements(Driver.driver(),Salestooperation.class);
	static AppointkeystaffandCommerSuitUi ak = PageFactory.initElements(Driver.driver(), AppointkeystaffandCommerSuitUi.class);
	static PDPui pdp_ui = PageFactory.initElements(Driver.driver(),PDPui.class);
	static Alerts alerts = PageFactory.initElements(Driver.driver(), Alerts.class);
	Actions actions = new Actions(driver);
	//imported classes
	static Workbook wb=new Workbook();
	static ReviewInvolve ri=new ReviewInvolve();
	static TasksCP4toCP5 g45=new TasksCP4toCP5();
	static TaskCP3CP4 g34=new TaskCP3CP4();
	static Basic b=new Basic();
	static Monorail monorail = new Monorail();
	static Login login=new Login();
	static TasksCP5toCP9 pdop=new TasksCP5toCP9();
	static EconsysVariables ev = new EconsysVariables();
	static ProjectMethods_Small_Works projectMethods_Small_Works = new ProjectMethods_Small_Works();
	static boolean isCP2amber, isCP2green;
	static int num;
	static double overallSell;
	static String filepath=System.getProperty("user.dir");

	//Matrix flow
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
	
		//login.url_Matrixe();
		login.url();
		//*****Login as genral user******
		cu.waitForPageToLoad();
		login.user();
		//****intiation of rtq form*********
	    b.rtqForm(estimatedSize,location);
	    //submit rtq
	    b.submit_Logout();
		//***********CP1 exe dession************
		if((estimatedSize.equals(ev.estimatedSize500_))||(location.equals(ev.location_other))) {
			b.boardApproval();
		}
		//Assign Sales Leader
		String userName = wb.getXLData(1, 0,0);
		String sl=wb.getXLData(10, 0, 0);
		if(!sl.equals(userName)){
		monorail.ASL();
		}
		//Prepare Quote
		monorail.prepare_Quote();
		cu.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),ev.exeCP2);
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();
				
		b.pathdession(ev.eSizertq2,ev.locationrtq2);
		//For CL approval in case of Quotation in our format is No
		//boolean isCP1green = estimatedSize.equalsIgnoreCase(ev.estimatedSize0to100k_)&&location.equalsIgnoreCase(ev.location_inside);
		//boolean isCP1amber = estimatedSize.equalsIgnoreCase(ev.estimatedSize250_)|| location.equalsIgnoreCase(ev.location_SouthEast);
		//boolean isCP2red = (ev.eSizertq2.equalsIgnoreCase(ev.estimatedSize500_)||ev.locationrtq2.equalsIgnoreCase(ev.location_other));
		//isCP2amber = (ev.eSizertq2.equalsIgnoreCase(ev.estimatedSize250_)||ev.locationrtq2.equalsIgnoreCase(ev.location_SouthEast));
		//isCP2green = ((ev.eSizertq2.equalsIgnoreCase(ev.estimatedSize0to100k_) && ev.locationrtq2.equalsIgnoreCase(ev.location_inside)));
		if((ev.ourformat.equals("No"))){
			log.info("In CL approval");
			clApproval();
		}
		//**********CP2 exe dession**************
		if(ev.exeCP2.equals("Yes") || ev.bidsheetauthorised.equals("No")||ev.ourformat.equals("No")){
			b.boardApproval();
		}
		monorail.submitQuote();
		statusQuotesubmit_(ev.customerCommitmentType, ev.quote_StatusCp2Cp3);
		//**********CP4 exe dession New Flow**************
		if(ev.execp4.equals("Yes") || (ev.clarification.equals("No") &&
				(ev.customerCommitmentType.equalsIgnoreCase(ev.customerCommitmentType_PO) || 
						ev.customerCommitmentType.equalsIgnoreCase(ev.customerCommitmentType_SubCon)))){
			b.boardApproval();
		}
		//Submit response
		g45.submitResponse();
		//Appoint Key staff
		g45.apointkeystaf();
		//commercial suite
		g45.cummercialSuite_4eg();
		//Sales to operation hand-over
		g45.salestoOperation();
  	    cu.selectByVisibleText(so.getExeCP5(),ev.exe5_SalestoOper);
  	    ab.getComments().sendKeys("Sales to operation");
  	    ab.getSubmitbutton().click();
  	    login.logout();
  	    //operation hand-over
        g45.operationAcceptance();
  	  	cu.selectByVisibleText(ak.getExeOperationAcceptanceCP5(), ev.exeCP5_OperationAccep);
  	  	ab.getComments().sendKeys("Operations Acceptance");
  	  	ab.getAcceptOperationAcceptance().click();
  	  	login.logout();
  	  	//Meeting Notes
  	  	if(ev.meetingwithSL.equals("Ye s")||ev.meeting.equals("Yes")){
  	  		g45.meetings();
  	  	}
  	  	//**********CP5 exe dession **********
        if(ev.exe5_SalestoOper.equals("Yes")||(ev.exeCP5_OperationAccep.equals("Yes")||(ev.draftproduced.equals("No")))){
			b.boardApproval();
		}
        //Project delivery plan(PDP)
		pdop.pdp_4eg();
		cu.selectByVisibleText(pdp_ui.getExecp6(),ev.execp6);
		cu.waitForPageToLoad();
		ab.getSubmitbutton().click();
		cu.waitForPageToLoad();
		login.logout();
		//**********CP6 exe dession **********svn
		if(ev.execp6.equals("Yes")){
			b.boardApproval();
		}
		//Delivery Review
		pdop.deveryreview();
		//**********OD approval **************
		pdop.obtainpracticalcomplition();
		cu.selectByVisibleText(pdp_ui.getOpc_cp8(), ev.execp8);
		ab.getSubmitbutton().click();
		login.logout();
		//***********CP8 exe dession **********
		if(ev.certificateobtained.equals("No")||ev.retationapplied.equals("No")||ev.onmSubmitted.equals("No")||ev.snagListIdentified.equals("No")||ev.internalCompletionDocument.equals("No")||ev.execp8.equals("Yes")){
			b.boardApproval();
		}
		pdop.postpracticalcomplition();
		cu.selectByVisibleText(pdp_ui.getPpc_cp9(), ev.execp9);
		ab.getSubmitbutton().click();
		login.logout();
		//***********CP9 exe dession **********
		if(ev.finalAccountAgreement.equals("No")||ev.finalRetentionPaid.equals("No")||ev.projectDocumentArchived.equals("No")||ev.projectDebrief.equals("No")||ev.subContractAccountSettled.equals("No")||ev.closureofProject.equals("No")||ev.bondsGuarantees_Resolved.equals("No")||ev.execp9.equals("Yes")){
			b.boardApproval();
		}
		log.info("Monorail Test script ended....");
	}
	
	public static void clApproval() throws IOException, InterruptedException {
		
		login.loginCL();
		cu.blindWait();
		//b.projectname_ReviewApproval();
		ab.getComments().sendKeys("Prepare quote CL Approval...");
		//Authorize button
		driver.findElement(By.id("authorise")).click();
		cu.waitForPageToLoad();
		login.logout();
	}
//status of submitted quote Matrix and 4eg flow
public static void statusQuotesubmit_(String customerCommitmentType,String quoteStatus) throws IOException, InterruptedException{
		
	 //login.loginSL();
	 cu.waitForPageToLoad();
	 // String rtqType= driver.findElement(By.xpath("//tr[td[@title='"+wb.getXLData(1, 2, 1)+"']]/td[6]")).getText();
	 String taskName = PropertiesUtil.getPropValues("status_ofSubmitted_Quote");
	 b.projectTaskName(taskName);
	 cu.waitForPageToLoad();
	 log.info("quotestatuscp2cp3 : "+quoteStatus);
	 
	 cu.selectByVisibleText(ccq.getQuoteStatus(),quoteStatus);
	 cu.waitForPageToLoad();
	 	if(quoteStatus.equals("Customer Commitment Received")){
	 		{
	 			cu.selectByVisibleText(ccq.getCustomerCommitmentType(), customerCommitmentType);
	 			if(!ev.customerCommitmentType_Verbal.equals(customerCommitmentType)){
	 			ccq.getUploadDoc_StatusofSubmitQuote().click();
	 			projectMethods_Small_Works.linktoFileupload();
	 			}
	 		}
	 ab.getComments().sendKeys("Quote status updated as "+quoteStatus);
	 ccq.getSubmit().click();
	//Customer commitment acceptance logic
	 	CCAlogic_Matrix(customerCommitmentType);
	 	}
	 	else if(quoteStatus.equals(ev.quoteStatusAmendBid)){
		 ab.getComments().sendKeys("Quote status is Amend Bid");
		 ccq.getSubmit().click();
		 monorail.prepareQuotecp2cp3();
		 cu.selectByVisibleText(prepare_Quoteui.getExecp3(),ev.exeCP3);
		 prepare_Quoteui.getQuoteprepared().click();
		 login.logout();
		
		 b.pathdessioncp2cp3(ev.eSizertq3,ev.locationrtq3);
		 boolean isCP3green = (ev.eSizertq3.equalsIgnoreCase("A 0-100k") && ev.locationrtq3.equalsIgnoreCase("Inside M25"));
		 boolean isCP3red = (ev.eSizertq3.equalsIgnoreCase(ev.estimatedSize500_) || ev.locationrtq3.equalsIgnoreCase(ev.location_other));
		 //Quote not in our formate for CL approval
		 if(ev.cp2cp3ourformat.equals("No") && isCP3green){
			 log.info("In CL approval cp2-cp3");
			 clApproval();
		 }
		 //CP3 approval board
		 if(isCP3red && (isCP2amber || isCP2green) ||ev.exeCP3.equals("Yes") || ev.cp2cp3bidsheetauthorised.equals("No")){
			 b.boardApproval();
		 }

		 monorail.resubmitQuote();
		 status_Quote_Resubmit_(ev.estimatedSize,ev.location);
	 	}
}
//Status of resubmitted quote used for Matrix and 4eg
	 public static void status_Quote_Resubmit_(String estimatedSize,String location) throws IOException, InterruptedException{
		 //login.loginSL();
		 cu.waitForPageToLoad();
		 String taskName = PropertiesUtil.getPropValues("status_ofRe_SubmittedQuote");
		 b.projectTaskName(taskName);
		 cu.waitForPageToLoad();
		 
		 cu.selectByVisibleText(ccq.getQuoteStatus(),ev.quote_StatusCp3Cp4);
		 
		 if(ev.quote_StatusCp3Cp4.equals("Customer Commitment Received")){ 
		 {
			 cu.waitForPageToLoad();
			 
			 cu.selectByVisibleText(ccq.getCustomerCommitmentType(), ev.customerCommitmentType);
			 
			 if(!"Verbal Commitment Received - Under Review".equals(ev.customerCommitmentType)){
				 ccq.getUploadDoc_StatusofSubmitQuote().click();
				 projectMethods_Small_Works.linktoFileupload();
		 		 }
		 }
		 ab.getComments().sendKeys("Quote status is Customer Commitment Received");
		 cu.waitForPageToLoad();
		 
		 ab.getSubmitbutton().click();
		//Customer commitment acceptance logic
		 	CCAlogic_Matrix(ev.customerCommitmentType);
		 }
		 //******Amend bid******
		 else if(ev.quote_StatusCp3Cp4.equals("Amend Bid")){
		 	 
		 monorail.prepareQuotecp2cp3();
		 
		 b.pathdessioncp2cp3_Mat(ev.estimatedSize,ev.location);
		 //Cl approval quation on our format is Np
		 	if(ev.cp2cp3ourformat.equalsIgnoreCase("No")){
		 		clApproval();
		 	}
		 	//Explicit board approval
		 	if(ev.exeCP3.equals("Yes") || ev.cp2cp3bidsheetauthorised.equals("No")){
		 		b.boardApproval();
			}
		 
		 monorail.resubmitQuote();
		 status_Quote_Resubmit_(estimatedSize,location);
		 }
	 }
	//Customer commitment logic matrix, PAG and UKAS(may not work for ukas)
		 public static void CCAlogic_Matrix(String customerCommitmentType) throws InterruptedException, IOException{
			 //customer commitment LOI/Email/Verbal received- status of submitted quote		 
			 	if(customerCommitmentType.equals(ev.customerCommitmentType_LOI)||
			 			customerCommitmentType.equals(ev.customerCommitmentType_Email)
			 		){
			 		cu.waitForPageToLoad();
			 		String taskName = PropertiesUtil.getPropValues("customer_Commitment_Acceptance");
			 		b.projectTaskName(taskName);
			 		cu.selectByVisibleText(driver.findElement(By.xpath("//select[@id='st_request_for_cw']")),ev.loi_Commencement);
			 		ab.getComments().sendKeys("Customer Commitment Type");
			 		
				//Does LOI Received specifically request for commencement of work? = 'YES'			 	
			 		if(ev.loi_Commencement.equals("Yes")){
			 			
				 	 		cu.waitForPageToLoad();
				 	 		Thread.sleep(1000);
				 	 		cu.selectByVisibleText(ccq.getAny_limitation_to_scope(), ev.any_limitation_to_scope);
				 	 		//Expenditure edit
				 	 		cu.selectByVisibleText(ccq.getAny_limitation_to_expenditure(), ev.any_limitation_to_expenditure);
				 	 		if(ev.any_limitation_to_expenditure.equals("Yes")){
				 	 			String expenditure = wb.getXLData(1, 5, 2);
				 	 			ccq.getExpenditureLimit().sendKeys(expenditure);
				 	 		}
				 	 		cu.selectByVisibleText(ccq.getAny_Time_limit_to_Instructions(), ev.any_Time_limit_to_Instructions);
				 	 		cu.selectByVisibleText(ccq.getAny_other_Review(), ev.any_other_Review);
				 	 		
				 	 		if(ev.any_limitation_to_scope.equals("Yes")||ev.any_limitation_to_expenditure.equals("Yes")||
				 	 				ev.any_Time_limit_to_Instructions.equals("Yes")||ev.any_other_Review.equals("Yes"))
				 	 		{
				 	 			driver().findElement(By.xpath("//input[@id='fileList_flm_LoiDocs']")).click();
					 	 		projectMethods_Small_Works.linktoFileupload();	
				 	 		}
				 	 		if(ev.any_limitation_to_scope.equals("No")&&ev.any_limitation_to_expenditure.equals("No")&&
				 	 				ev.any_Time_limit_to_Instructions.equals("Yes")&&ev.any_other_Review.equals("Yes"))
				 	 		{
				 	 			driver().findElement(By.xpath("//input[@id='fileList_flm_slreponseDocs']")).click();
					 	 		projectMethods_Small_Works.linktoFileupload();	
				 	 		}
				 	 		cu.selectByVisibleText(ccq.getExeCP4(), ev.execp4);
				 	 		b.submit_Logout();
				 	 		
				 	 		TaskCP3CP4.TandCreview();
			 		}
			 	//Does LOI Received specifically request for commencement of work? = 'NO'
				 	if(ev.loi_Commencement.equals("No")){
				 		//Select explicit cp4 approval
				 		driver.findElement(By.xpath("//input[@id='fileList_flm_slreponseDocs']")).click();
				 		projectMethods_Small_Works.linktoFileupload();
				 		driver.findElement(By.xpath("//textarea[@id='response_text']")).sendKeys("Prepared Respponse");
				 		cu.selectByVisibleText(ccq.getExeCP4(), ev.execp4);
				 		b.submit_Logout();
				 		cu.waitForPageToLoad();
				 		
				 		TaskCP3CP4.TandCreview();
				 	}
			 	}
			 	//this is verbal
			 	else if(customerCommitmentType.equals(ev.customerCommitmentType_Verbal)){
				 		cu.waitForPageToLoad();
				 		String taskName = PropertiesUtil.getPropValues("customer_Commitment_Acceptance");
				 		b.projectTaskName(taskName);
			 			driver.findElement(By.xpath("//input[@id='fileList_flm_verbalCommitment']")).click();
			 			projectMethods_Small_Works.linktoFileupload();
			 			
			 			ab.getComments().sendKeys("Verbal commitment acceptance");
			 			b.submit_Logout();
			 			
			 			g34.clApproval();
		 		}else if(customerCommitmentType.equals(ev.customerCommitmentType_PO)||
		 				customerCommitmentType.equals(ev.customerCommitmentType_SubCon))
			 	{
				 //g34.customercommit();
			 		g34.scopeDocandContractValueVerification();
			 	}
		 }
}