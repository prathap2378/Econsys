package com.econsys.testCases;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.econsys.Genriclibrery.CommonUtils;
import com.econsys.Genriclibrery.Driver;
import com.econsys.Genriclibrery.EconsysVariables;
import com.econsys.Projects.Basic;
import com.econsys.Projects.Login;
import com.econsys.Projects.Monorail;
import com.econsys.Projects.TasksCP4toCP5;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.AllPages;
import com.econsys.UIobjectrepositary.Preparequote;

public class RoleReassignment {
	AllPages allPages= PageFactory.initElements(Driver.driver(), AllPages.class);
	Preparequote prepare_Quoteui=PageFactory.initElements(Driver.driver(), Preparequote.class);
	Login login= new Login();
	EconsysVariables ev= new EconsysVariables();
	CommonUtils cu= new CommonUtils();
	Basic basic = new Basic();
	Monorail rtq= new Monorail();
	Workbook wb= new Workbook();
	static TasksCP4toCP5 g45=new TasksCP4toCP5();
	ActionButtonsCPApprovalsSW dr= new ActionButtonsCPApprovalsSW();
  @Test
  public void verifyRoleReassign() throws IOException, InterruptedException, AWTException {
	  login.user();
	    
	//*****Login as genral user******
	  		cu.waitForPageToLoad();
	  		//boolean elementexist=driver.findElements(By.cssSelector("input[id='_58_emailInput'][name='_58_login']")).size()>0;
	  		//if(elementexist)
	  		login.user(); 
	  		//****intiation of rtq form*********
	  	    basic.rtqForm(ev.estimatedSize,ev.location);
	  	    //rtq submit externally
	  	    basic.submit_Logout();
	  		
	  		//***********CP1 exe dession************
	  		if((ev.estimatedSize.equals("D 500+"))||(ev.location.equals("Other"))) {
	  			basic.boardApproval();
	  		}

	  		//Assign Sales Leader
	  		String userName = wb.getXLData(1, 0,0);
	  		String sl=wb.getXLData(10, 0, 0);
	  		
	  		if(!sl.equals(userName)){
	  			rtq.ASL();
	  			
	  		//Prepare Quote
	  		rtq.prepare_Quote();
	  		cu.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),ev.exeCP2);
	  		prepare_Quoteui.getQuoteprepared().click();
	  		login.logout();
	  		
	  		basic.pathdession(ev.estimatedSize,ev.location);
	  		//**********CP2 exe dession**************
	  		if((ev.ourformat.equals("No"))||(ev.bidsheetauthorised.equals("No"))||(ev.exeCP2.equals("Yes"))){
	  			basic.boardApproval();
	  		}
	  		rtq.submitQuote();
	  		rtq.statusQuotesubmit(ev.customerCommitmentType, ev.quote_StatusCp2Cp3);
	  		
	  		//**********CP4 exe dession New Flow**************
	  		if((ev.clarification.equals("No"))||(ev.execp4.equals("Yes"))){
	  			basic.boardApproval();
	  		}	
	  		g45.apointkeystaf();
  
	  	}
	  		allPages.getAllProjects().click();
	  		basic.search();
	  		allPages.getOptionBtn().click();
	  		allPages.getReassign_option().click();
	  		cu.selectByIndex(allPages.getSalesdLead(), 1);
	  		
	  		
}
}