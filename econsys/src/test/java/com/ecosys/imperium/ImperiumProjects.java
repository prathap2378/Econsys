package com.ecosys.imperium;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.jboss.netty.util.EstimatableObjectWrapper;
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

public class ImperiumProjects extends Driver {

	private static Logger log = Logger.getLogger(ImperiumProjects.class.getName());
	//Page UI classes
	public static Preparequote prepare_Quoteui = PageFactory.initElements(Driver.driver(), Preparequote.class);
	static CommonUtils commonUtils = PageFactory.initElements(Driver.driver(), CommonUtils.class);
	static CosCommitQuoteStatusUi ccq = PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	static ActionButtonsUi ab = PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	static Salestooperation salestoOperation = PageFactory.initElements(Driver.driver(),Salestooperation.class);
	static AppointkeystaffandCommerSuitUi ak = PageFactory.initElements(Driver.driver(), AppointkeystaffandCommerSuitUi.class);
	static PDPui pdp_ui = PageFactory.initElements(Driver.driver(),PDPui.class);
	
	//imported classes
	static Workbook wb=new Workbook();
	static TasksCP4toCP5 g45=new TasksCP4toCP5();
	static TaskCP3CP4 g34=new TaskCP3CP4();
	static Basic basic=new Basic();
	static Monorail monorail = new Monorail();
	static Login login=new Login();
	static TasksCP5toCP9 pdop=new TasksCP5toCP9();
	static EconsysVariables ev = new EconsysVariables();
	static ProjectMethods_Small_Works projectMethods_Small_Works = new ProjectMethods_Small_Works();
	static boolean isCP2amber, isCP2green;
	static int num;
	static double overallSell;
	static String filepath=System.getProperty("user.dir");
	static Imperium_Project_Methods imperium_Project_Methods = new Imperium_Project_Methods();
	Imperium_SmallWorks_Methods imperium_SmallWorks_Methods = new Imperium_SmallWorks_Methods();

	@Test(invocationCount = 0,threadPoolSize = 0)
	public void test1() throws Exception {
		System.out.printf("%n[START] Thread Id : %s is started!", Thread.currentThread().getId());
		imperiumProjectFlow();
		System.out.printf("%n[END] Thread Id : %s", Thread.currentThread().getId());
	}
	@Test
	public void imperiumProjectFlow() throws Exception{

		login.url();
		//*****Login as genral user******
		commonUtils.waitForPageToLoad();
		login.user();
		//****Initiation of rtq form*********
		imperium_Project_Methods.rtqForm(ev.estimatedSize,ev.location);
		imperium_Project_Methods.submit_logout_QRnumberAlert();
		//***********CP1 explicit approval decision************
		if((ev.estimatedSize.equals(ev.estimatedSize500_))||(ev.location.equals(ev.location_other))) {
			basic.boardApproval();
		}
		//Assign Sales Leader
		imperium_Project_Methods.ASL();
		
		//Prepare Quote
		imperium_Project_Methods.prepare_Quote(ev.overallSell,ev.locationrtq2);
		commonUtils.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),ev.exeCP2);
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();

		basic.pathdession(ev.eSizertq2,ev.locationrtq2);
		//For CL approval in case of Quotation in our format is No
		//boolean isCP1green = estimatedSize.equalsIgnoreCase(ev.estimatedSize0to100k_)&&location.equalsIgnoreCase(ev.location_inside);
		//boolean isCP1amber = estimatedSize.equalsIgnoreCase(ev.estimatedSize250_)|| location.equalsIgnoreCase(ev.location_SouthEast);
		//boolean isCP2red = (ev.eSizertq2.equalsIgnoreCase(ev.estimatedSize500_)||ev.locationrtq2.equalsIgnoreCase(ev.location_other));
		//isCP2amber = (ev.eSizertq2.equalsIgnoreCase(ev.estimatedSize250_)||ev.locationrtq2.equalsIgnoreCase(ev.location_SouthEast));
		//isCP2green = ((ev.eSizertq2.equalsIgnoreCase(ev.estimatedSize0to100k_) && ev.locationrtq2.equalsIgnoreCase(ev.location_inside)));
		if((ev.ourformat.equals("No"))){
			log.info("In CL approval");
			Imperium_Project_Methods.clApproval();
		}
		//**********CP2 exe dession**************
		if(ev.exeCP2.equals("Yes") || ev.bidsheetauthorised.equals("No")||ev.ourformat.equals("No")){
			basic.boardApproval();
		}
		ProjectMethods_Small_Works.submit_Quoteform();
		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType, ev.quote_StatusCp2Cp3);
		//**********CP4 exe dession New Flow**************
		if(ev.execp4.equals("Yes") || (ev.clarification.equals("No") &&
				(ev.customerCommitmentType.equalsIgnoreCase(ev.customerCommitmentType_PO) || 
						ev.customerCommitmentType.equalsIgnoreCase(ev.customerCommitmentType_SubCon)))){
			basic.boardApproval();
		}
		//Submit response
		g45.submitResponse();
		imperium_Project_Methods.permission_to_Commence();
		//Appoint Key staff
		imperium_SmallWorks_Methods.apointkeystaf();

		//commercial suite
		imperium_SmallWorks_Methods.cummercialSuite_();

		//Sales to operation hand-over
		imperium_Project_Methods.salestoOperation();
		commonUtils.selectByVisibleText(salestoOperation.getExeCP5(),ev.exe5_SalestoOper);
		ab.getComments().sendKeys("Sales to operation");
		ab.getSubmitbutton().click();
		login.logout();

		//operations hand over
		imperium_Project_Methods.operationAcceptance();
		commonUtils.selectByVisibleText(ak.getExeOperationAcceptanceCP5(), ev.exeCP5_OperationAccep);
		ab.getAcceptOperationAcceptance().click();
		login.logout();
		//Meeting Notes
		if(ev.meetingwithSL.equals("Yes")||ev.meeting.equals("Yes")){
			g45.meetings();
		}
		//CP5 explicit decision
		if(ev.exe5_SalestoOper.equals("Yes")||(ev.exeCP5_OperationAccep.equals("Yes")||(ev.draftproduced.equals("No")))){
			basic.boardApproval();
		}
		//Project delivery plan(PDP)
		imperium_Project_Methods.pdp_();
		commonUtils.selectByVisibleText(pdp_ui.getExecp6(),ev.execp6);
		ab.getSubmitbutton().click();
		commonUtils.waitForPageToLoad();
		login.logout();
		//CP6 explicit decision
		if(ev.execp6.equals("Yes")){
			basic.boardApproval();
		}
		//Delivery Review
		imperium_Project_Methods.deveryreview(ev.deliveryReview_dission);
		//**********OD approval **************
		imperium_Project_Methods.obtainpracticalcomplition();
		commonUtils.selectByVisibleText(pdp_ui.getOpc_cp8(), ev.execp8);
		ab.getSubmitbutton().click();
		login.logout();
		//CP8 explicit decision
		if(ev.certificateobtained.equals("No")||ev.retationapplied.equals("No")||ev.onmSubmitted.equals("No")||ev.snagListIdentified.equals("No")||ev.internalCompletionDocument.equals("No")||ev.execp8.equals("Yes")){
			basic.boardApproval();
		}
		imperium_Project_Methods.postpracticalcomplition();
		commonUtils.selectByVisibleText(pdp_ui.getPpc_cp9(), ev.execp9);
		ab.getSubmitbutton().click();
		commonUtils.blindWait();
		pdp_ui.getProjectSubmitCP9Alert().click();
		login.logout();
		//***********CP9 exe dession **********
		if(ev.finalAccountAgreement.equals("No")||ev.finalRetentionPaid.equals("No")||ev.projectDocumentArchived.equals("No")||ev.projectDebrief.equals("No")||ev.subContractAccountSettled.equals("No")||ev.closureofProject.equals("No")||ev.bondsGuarantees_Resolved.equals("No")||ev.execp9.equals("Yes")){
			basic.boardApproval();
		}
		log.info("Monorail Test script ended....");
	}
}