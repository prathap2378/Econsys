package com.ecosys.imperium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.econsys.Projects.*; 
import com.econsys.UIobjectrepositary.*;
import com.econsys.econ4eg.Econsys4egProjects;
import com.econsys.Genriclibrery.CommonUtils;
import com.econsys.Genriclibrery.Driver;
import com.econsys.Genriclibrery.EconsysVariables;
import com.econsys.SmallWorks.ProjectMethods_Small_Works;
import com.econsys.TestData.*;

public class Imperium_SmallWork_flow extends Driver{

	private static Logger log = Logger.getLogger(Imperium_SmallWork_flow.class.getName());
	//Page factory objects
	CommonUtils commonUtils=PageFactory.initElements(Driver.driver(), CommonUtils.class);
	RTQForm_Ui nrtq=PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
	CosCommitQuoteStatusUi ccq=PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	TandCverification TandCver = PageFactory.initElements(driver(), TandCverification.class);
	TasksCP4toCP5 cp4_cp5=PageFactory.initElements(Driver.driver(), TasksCP4toCP5.class);
	TasksCP5toCP9 cp5_cp9=PageFactory.initElements(Driver.driver(), TasksCP5toCP9.class);
	smallWorkPageElements smallWorks_PageElements = PageFactory.initElements(Driver.driver(),smallWorkPageElements.class);
	PDPui pdp_Ui=PageFactory.initElements(Driver.driver(),PDPui.class);
	//Class objects
	static Workbook wb=new Workbook();
	static Monorail rtq=new Monorail();
	static ReviewInvolve ri=new ReviewInvolve();
	static TasksCP4toCP5 g45=new TasksCP4toCP5();
	static TaskCP3CP4 g34=new TaskCP3CP4();
	static Basic b=new Basic();
	static Login login=new Login();
	EconsysVariables ev = new EconsysVariables();
	Imperium_SmallWorks_Methods imperium_SmallWorks_Methods = new Imperium_SmallWorks_Methods();

	@Test
	public void smallWorks_flow()throws Exception{
		log.info("Launch browser--");
		login.url();
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
				b.boardApproval();
			}
			//Assign Sales Leader
			rtq.ASL();
			//Submit quote
			ProjectMethods_Small_Works.submit_Quoteform();
			//Status of submitted quote
			imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType, ev.quote_StatusCp2Cp3);
			//cp4 board approval
			if(ev.execp4.equals("Yes") || (ev.clarification.equals("No")&&
					(ev.customerCommitmentType.equalsIgnoreCase(ev.customerCommitmentType_SubCon)||
							ev.customerCommitmentType.equalsIgnoreCase(ev.customerCommitmentType_PO)))){
				b.boardApproval();
			}
			//Submit response
			cp4_cp5.submitResponse();
			//CP4-CP5 Appoint key staff
			imperium_SmallWorks_Methods.apointkeystaf();
			//Commercial suite application for payment
			imperium_SmallWorks_Methods.cummercialSuite_();
			//Sales to operation hand-over
			imperium_SmallWorks_Methods.salesToOperation();
			//select CP5 explicit approval and submit
			commonUtils.selectByVisibleText(smallWorks_PageElements.getExe_cp5_sw(),ev.exe5_SalestoOper);
			smallWorks_PageElements.getSubmitBtn().click();
			login.logout();
			//operation acceptance
			imperium_SmallWorks_Methods.operation_acceptance();
			//select CP5 explicit approval
			commonUtils.selectByVisibleText(smallWorks_PageElements.getExe_cp5_sw_OperatonAcceptance(),ev.exeCP5_OperationAccep);
			driver.findElement(By.xpath("//input[@id='approve']")).click();
			login.logout();
			//cp5 board approval
			if(ev.exe5_SalestoOper.equals("Yes")||(ev.exeCP5_OperationAccep.equals("Yes"))){
				b.boardApproval();
			}
			//CP5-CP6 PDP used from generic class cp5_cp9
			imperium_SmallWorks_Methods.pdp();
			//select CP6 explicit and submit
			commonUtils.selectByVisibleText(pdp_Ui.getExecp6(),ev.execp6);
			smallWorks_PageElements.getSubmitBtn().click();
			login.logout();
			//CP6 explicit decision
			if(ev.execp6.equals("Yes")){
				b.boardApproval();
			}
			//CP6-CP7
			imperium_SmallWorks_Methods.deliveryreview();

			imperium_SmallWorks_Methods.obtainpracticalcomplition();
			commonUtils.selectByVisibleText(pdp_Ui.getOpc_cp8(), ev.execp8);
			ab.getSubmitbutton().click();
			login.logout();
			//***********CP8 exe dession **********
			if(ev.certificateobtained.equals("No")||ev.retationapplied.equals("No")||ev.onmSubmitted.equals("No")||ev.snagListIdentified.equals("No")||ev.internalCompletionDocument.equals("No")||ev.execp8.equals("Yes")){
				b.boardApproval();
			}
			//CP8-CP9
			cp5_cp9.postpracticalcomplition();
			commonUtils.selectByVisibleText(pdp_Ui.getPpc_cp9(), ev.execp9);
			ab.getSubmitbutton().click();
			commonUtils.blindWait();
			driver.findElement(By.xpath("//div[div[contains(text(),'The Project has been submitted to Control Point 9 for Approval. If Approval is granted, the Project will be automatically archived')]]/div/a")).click();
			login.logout();
			//***********CP9 exe dession **********
			if(ev.finalAccountAgreement.equals("No")||ev.finalRetentionPaid.equals("No")||ev.projectDocumentArchived.equals("No")||ev.projectDebrief.equals("No")||ev.subContractAccountSettled.equals("No")||ev.closureofProject.equals("No")||ev.bondsGuarantees_Resolved.equals("No")||ev.execp9.equals("Yes")){
				b.boardApproval();
			}
		}
	}
}