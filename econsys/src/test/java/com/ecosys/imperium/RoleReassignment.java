package com.ecosys.imperium;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.econsys.Genriclibrery.*;
import com.econsys.Projects.*;
import com.econsys.SmallWorks.ProjectMethods_Small_Works;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.*;
import com.econsys.testCases.ActionButtonsCPApprovalsSW;

public class RoleReassignment extends Driver{

	Logger log = Logger.getLogger(RoleReassignment.class);
	AllPages allPages = PageFactory.initElements(Driver.driver(), AllPages.class);
	Preparequote prepare_Quoteui=PageFactory.initElements(Driver.driver(), Preparequote.class);
	Login login= new Login();
	EconsysVariables ev= new EconsysVariables();
	CommonUtils commonUtils = new CommonUtils();
	Basic basic = new Basic();
	Monorail rtq= new Monorail();
	Workbook wb= new Workbook();
	static TasksCP4toCP5 g45=new TasksCP4toCP5();
	ActionButtonsCPApprovalsSW dr= new ActionButtonsCPApprovalsSW();
	Imperium_Project_Methods imperium_Project_Methods = new Imperium_Project_Methods(); 
	Imperium_SmallWorks_Methods imperium_SmallWorks_Methods = new Imperium_SmallWorks_Methods();

	@Test
	public void verifyRoleReassign() throws IOException, InterruptedException, AWTException {

		//*****Login as genral user******
		commonUtils.waitForPageToLoad();
		boolean elementexist = driver().findElements(By.cssSelector("input[id='_58_emailInput'][name='_58_login']")).size()>0;
		if(elementexist)
			login.user(); 
		//****intiation of rtq form*********
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

		//Prepare quote CL approval for quote is not in our format
		if((ev.ourformat.equals(ev.select_No))){
			log.info("In CL approval");
			Imperium_Project_Methods.clApproval();
		}
		//**********CP2 exe dession**************
		if(ev.exeCP2.equals("Yes") || ev.bidsheetauthorised.equals("No")){
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
		imperium_SmallWorks_Methods.apointkeystaf(ev.projectorSW);

		login.loginOrgAdmin();
		allPages.getAllProjects().click();
		basic.search();
		allPages.getOptionBtn().click();             
		allPages.getReassign_option().click();
		commonUtils.selectByIndex(allPages.getSalesdLead(),1);
	}
}