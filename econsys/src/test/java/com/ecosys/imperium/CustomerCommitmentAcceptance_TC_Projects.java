package com.ecosys.imperium;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import POC.econsys.ActionButtons;

import com.econsys.Genriclibrery.*;
import com.econsys.Projects.Basic;
import com.econsys.Projects.Login;
import com.econsys.Projects.Monorail;
import com.econsys.Projects.ReviewInvolve;
import com.econsys.Projects.TaskCP3CP4;
import com.econsys.Projects.TasksCP4toCP5;
import com.econsys.Projects.TasksCP5toCP9;
import com.econsys.TestData.*;
import com.econsys.UIobjectrepositary.*;

public class CustomerCommitmentAcceptance_TC_Projects extends Driver{
	private static Logger log = Logger.getLogger(Monorail.class.getName());
	//Page UI classes
	Preparequote pq=PageFactory.initElements(Driver.driver(), Preparequote.class);
	static CommonUtils commonUtils=PageFactory.initElements(Driver.driver(), CommonUtils.class);
	static RTQForm_Ui nrtq= PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
	Assignsalesleader sla=PageFactory.initElements(Driver.driver(), Assignsalesleader.class);
	CosCommitQuoteStatusUi ccq=PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	public static Preparequote prepare_Quoteui=PageFactory.initElements(Driver.driver(), Preparequote.class);
	ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);

	//import classes
	static Workbook wb=new Workbook();
	static Monorail monorail=new Monorail();
	static ReviewInvolve ri=new ReviewInvolve();
	static TasksCP4toCP5 g45=new TasksCP4toCP5();
	static TaskCP3CP4 g34=new TaskCP3CP4();
	static Basic basic = new Basic();
	static Login login=new Login();
	static TasksCP5toCP9 pdop=new TasksCP5toCP9();
	static ActionButtons actionBtns= new ActionButtons();
	EconsysVariables ev = new EconsysVariables();
	Imperium_Project_Methods imperiumProject_methods = new Imperium_Project_Methods();
	Imperium_SmallWorks_Methods imperium_SmallWorks_Methods = new Imperium_SmallWorks_Methods(); 
	String filepath=System.getProperty("user.dir");

	String sl,userName;
	{
		try {
		userName = wb.getXLData(1, 0,0);
		sl=wb.getXLData(10, 0, 0);
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	void rtq_submitQuote() throws IOException,InterruptedException, AWTException{
		login.url();
		Thread.sleep(1000);
		boolean elementexist=driver.findElements(By.cssSelector("input[id='_58_emailInput'][name='_58_login']")).size()>0;
		if(elementexist)
		login.user();
		log.info("");
		//**** econsys RTQ form*********
		imperiumProject_methods.rtqForm(ev.estimatedSize0to100k_,ev.location_inside);
		imperiumProject_methods.submit_logout_QRnumberAlert();
		//Assign Sales Leader
		if(!sl.equals(userName)){
			imperiumProject_methods.ASL();
		}
		imperiumProject_methods.prepare_Quote(ev.overallSell,ev.locationrtq2);
		commonUtils.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),ev.select_No);
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();
		basic.pathdession(ev.estimatedSize,ev.location);
		if((ev.ourformat.equals("No"))){
			Imperium_Project_Methods.clApproval();
		}
		//**********CP2 exe dession**************
		if((ev.ourformat.equals("No"))||(ev.bidsheetauthorised.equals("No"))||(ev.exeCP2.equals("Yes"))){
			basic.boardApproval();
		}
		monorail.submitQuote();
	}
	
	@Test(priority=0)
	public void verifyLOICCA() throws InterruptedException, IOException, AWTException{
		
		rtq_submitQuote();
		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType_LOI, ev.quote_StatusCp2Cp3);
	}
	@Test(priority=1)
	public void verifyPOCCA() throws InterruptedException, IOException, AWTException{
		
		rtq_submitQuote();
		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType_PO,ev.quoteStatusCCARecived);
	}
	//Customer commitment as Verbal received
	@Test(priority=2)
	public void verifyVerbalCCA() throws InterruptedException, IOException, AWTException{

		rtq_submitQuote();
		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType_Verbal,ev.quoteStatusCCARecived);
	}
	//Customer commitment as Email received
	@Test(priority=3)
	public void verifyEmailCCA() throws InterruptedException, IOException, AWTException{
		
		rtq_submitQuote();
		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType_Email,ev.quoteStatusCCARecived);
	}
	//Customer commitment as Sub-contract received
	@Test(priority=4)
	public void verifySubContractCCA() throws InterruptedException, IOException, AWTException{
		
		rtq_submitQuote();
		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType_SubCon,ev.quoteStatusCCARecived);
	}
	@Test(priority=5)
	public void verifyAmendBidCCA() throws InterruptedException, IOException, AWTException{
		
		rtq_submitQuote();
		imperium_SmallWorks_Methods.statusQuotesubmit_ (ev.customerCommitmentType,ev.quoteStatusAmendBid);
	}
}