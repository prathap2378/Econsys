package com.ecosys.imperium;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.econsys.Genriclibrery.*;
import com.econsys.Projects.*;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.*;

public class CustomerCommitmentAcceptance_TC_SmallWorks {
	private static Logger log = Logger.getLogger(CustomerCommitmentAcceptance_TC_SmallWorks.class.getName());

	Preparequote pq=PageFactory.initElements(Driver.driver(), Preparequote.class);
	CommonUtils cu=PageFactory.initElements(Driver.driver(), CommonUtils.class);
	RTQForm_Ui nrtq=PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
	CosCommitQuoteStatusUi ccq=PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	ActionButtonsUi ab=PageFactory.initElements(Driver.driver() ,ActionButtonsUi.class);
	TasksCP4toCP5 cp45=PageFactory.initElements(Driver.driver(), TasksCP4toCP5.class);
	TasksCP5toCP9 pdop=PageFactory.initElements(Driver.driver(), TasksCP5toCP9.class);

	static Workbook wb=new Workbook();
	static Monorail monorail=new Monorail();
	static ReviewInvolve ri=new ReviewInvolve();
	static TasksCP4toCP5 g45=new TasksCP4toCP5();
	static TaskCP3CP4 g34=new TaskCP3CP4();
	static Basic b=new Basic();
	static Login login=new Login();
	EconsysVariables ev = new EconsysVariables();
	Imperium_SmallWorks_Methods imperium_SmallWorks_Methods = new Imperium_SmallWorks_Methods();
	boolean flag=false;

	@Test(priority=0)
	public void verify_SW_POCCA() throws IOException, InterruptedException, ClassNotFoundException, SQLException{
		imperium_SmallWorks_Methods.quoteForm_SubmitQuote();
		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType_PO, ev.quoteStatusCCARecived);
		if((ev.clarification.equals("No"))||(ev.execp4.equals("Yes"))){
			b.boardApproval();
		}
	}

	@Test(priority=1)
	public void verify_SW_VerbalCCA() throws InterruptedException, IOException, AWTException, ClassNotFoundException, SQLException{

		imperium_SmallWorks_Methods.quoteForm_SubmitQuote();
		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType_Verbal, ev.quoteStatusCCARecived);
		if((ev.clarification.equals("No"))||(ev.execp4.equals("Yes"))){
			b.boardApproval();
		}
	}
	@Test(priority=2)
	public void verify_SW_EmailCCA() throws InterruptedException, IOException, AWTException, ClassNotFoundException, SQLException{
		imperium_SmallWorks_Methods.quoteForm_SubmitQuote();
		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType_Email, ev.quoteStatusCCARecived);
		if((ev.clarification.equals("No"))||(ev.execp4.equals("Yes"))){
			b.boardApproval();
		}
	}
	@Test(priority=3)
	public void verify_SW_SubContractCCA() throws InterruptedException, IOException, AWTException, ClassNotFoundException, SQLException{
		imperium_SmallWorks_Methods.quoteForm_SubmitQuote();
		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType_SubCon, ev.quoteStatusCCARecived);
		if((ev.clarification.equals("No"))||(ev.execp4.equals("Yes"))){
			b.boardApproval();
		}
	}
	@Test(priority=4)
	public void verify_SW_LOICCA() throws InterruptedException, IOException, AWTException, ClassNotFoundException, SQLException{
		imperium_SmallWorks_Methods.quoteForm_SubmitQuote();
		//smallWorks_Imperium.statusofSubmitQuote(customerCommitmentType);
		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType_LOI,ev.quoteStatusCCARecived);
		if((ev.clarification.equals("No"))||(ev.execp4.equals("Yes"))){
			b.boardApproval();
		}
	}
	@Test(priority=5)
	public void verify_SW_AmendBid() throws InterruptedException, IOException, AWTException, ClassNotFoundException, SQLException{
		imperium_SmallWorks_Methods.quoteForm_SubmitQuote();
		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType_LOI,ev.quoteStatusAmendBid);
		if((ev.clarification.equals("No"))||(ev.execp4.equals("Yes"))){
			b.boardApproval();
		}
	}
}