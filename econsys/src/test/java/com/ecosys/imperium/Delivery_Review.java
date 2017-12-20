package com.ecosys.imperium;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.econsys.Genriclibrery.*;
import com.econsys.Projects.*;
import com.econsys.SmallWorks.*;
import com.econsys.UIobjectrepositary.*;

/**
 * @author bhanu.pk
 * Delivery review all actions Submit, Monthly review and Project completed
 */

public class Delivery_Review extends Driver{

	Logger log = Logger.getLogger(Delivery_Review.class.getName());
	CommonUtils commonUtils = PageFactory.initElements(Driver.driver() , CommonUtils.class);
	ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	CosCommitQuoteStatusUi ccq=PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	Preparequote quote_form = PageFactory.initElements(Driver.driver(), Preparequote.class);
	smallWorkPageElements sWp= PageFactory.initElements(Driver.driver(),smallWorkPageElements.class);
	ProjectMethods_Small_Works project_Methods_small_Works = new ProjectMethods_Small_Works();
	PDPui pdp_Ui=PageFactory.initElements(Driver.driver(),PDPui.class);
	smallWorkPageElements smallWorks_PageElements = PageFactory.initElements(Driver.driver(),smallWorkPageElements.class);
	Login login = new Login();
	SmallWorks sw = new SmallWorks();
	Monorail monorail = new Monorail();
	Basic basic = new Basic();
	TaskCP3CP4 cuc = new TaskCP3CP4();
	TasksCP4toCP5 cp4_cp5 = new TasksCP4toCP5();
	EconsysVariables ev = new EconsysVariables();
	ProjectMethods_Small_Works swMethods = new ProjectMethods_Small_Works();
	Imperium_SmallWorks_Methods imperium_SmallWorks_Methods = new Imperium_SmallWorks_Methods();
	Imperium_Project_Methods imperium_Project_Methods = new Imperium_Project_Methods();

	@Test
	public void verify_DeliveryReview() throws InterruptedException, IOException, AWTException, ClassNotFoundException, SQLException{
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
				basic.boardApproval();
			}
			imperium_Project_Methods.ASL();
			//Submit quote
			ProjectMethods_Small_Works.submit_Quoteform();
			//Status of submitted quote
			 
		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType, ev.quote_StatusCp2Cp3);
			//cp4 board approval
			if(ev.execp4.equals("Yes") || (ev.clarification.equals("No")&&
					(ev.customerCommitmentType.equalsIgnoreCase(ev.customerCommitmentType_SubCon)||
							ev.customerCommitmentType.equalsIgnoreCase(ev.customerCommitmentType_PO)))){
				basic.boardApproval();
			}
			//Submit response
			cp4_cp5.submitResponse();
			imperium_Project_Methods.permission_to_Commence();
			//CP4-CP5 Appoint key staff
			imperium_SmallWorks_Methods.apointkeystaf(ev.selectasSmallWorks);
			//Commercial suite application for payment
			imperium_SmallWorks_Methods.cummercialSuite_();
			//Sales to operation hand-over
			imperium_SmallWorks_Methods.salesToOperation();
			//select CP5 explicit approval and submit
			commonUtils.selectByVisibleText(smallWorks_PageElements.getExe_cp5_sw(),ev.exe5_SalestoOper);
			ab.getSubmitbutton().click();
			login.logout();
			//operation acceptance
			imperium_SmallWorks_Methods.operation_acceptance();
			//select CP5 explicit approval
			commonUtils.selectByVisibleText(smallWorks_PageElements.getExe_cp5_sw_OperatonAcceptance(),ev.exeCP5_OperationAccep);
			driver.findElement(By.xpath("//input[@id='approve']")).click();
			login.logout();
			//cp5 board approval
			if(ev.exe5_SalestoOper.equals("Yes")||(ev.exeCP5_OperationAccep.equals("Yes"))){
				basic.boardApproval();
			}
			//CP5-CP6 PDP used from generic class cp5_cp9
			imperium_SmallWorks_Methods.pdp();

			//select CP6 explicit and submit
			commonUtils.selectByVisibleText(pdp_Ui.getExecp6(),ev.execp6);
			smallWorks_PageElements.getSubmitBtn().click();
			login.logout();
			//CP6 explicit decision
			if(ev.execp6.equals("Yes")){
				basic.boardApproval();
			}
			//CP6-CP7
			imperium_SmallWorks_Methods.deliveryreview(ev.deliveryReview_dission_MonthlyReview);
			
			log.info("DR review --- completed");
		}
	}
}