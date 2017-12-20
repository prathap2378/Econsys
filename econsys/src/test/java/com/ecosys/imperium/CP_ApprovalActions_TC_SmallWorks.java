package com.ecosys.imperium;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.econsys.Genriclibrery.CommonUtils;
import com.econsys.Genriclibrery.Driver;
import com.econsys.Genriclibrery.EconsysVariables;
import com.econsys.Listeners.TestListener;
import com.econsys.Projects.*;
import com.econsys.SmallWorks.SmallWorks;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.ActionButtonsUi;
import com.econsys.UIobjectrepositary.CosCommitQuoteStatusUi;
import com.econsys.UIobjectrepositary.Preparequote;
import com.econsys.UIobjectrepositary.TandCverification;
import com.econsys.UIobjectrepositary.smallWorkPageElements;
import com.econsys.matrix.MatrixProjects;

/**
 * @author bhanu.pk
 *This class consists of all the methods of Do not proceed and Reject of all tasks 
 */
public class CP_ApprovalActions_TC_SmallWorks extends Driver{
	static ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	static CosCommitQuoteStatusUi CosCommit_Quote_StatusUi = PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	Preparequote prepare_Quoteui = PageFactory.initElements(Driver.driver(), Preparequote.class);
	private static Logger log=Logger.getLogger(CP_ApprovalActions_TC_SmallWorks.class.getName());
	smallWorkPageElements sWp= PageFactory.initElements(Driver.driver(),smallWorkPageElements.class);
	static TandCverification tandCver = PageFactory.initElements(driver(), TandCverification.class);

	Login login = new Login();
	TestListener name = new TestListener();
	static Basic b = new Basic();
	Workbook wb= new Workbook();
	SmallWorks sw = new SmallWorks();
	Monorail monorail = new Monorail();
	TaskCP3CP4 taskCP3_CP4 = new TaskCP3CP4();
	TasksCP4toCP5 cp4_cp5 = new TasksCP4toCP5();
	Imperium_SmallWorks_Methods imperium_SmallWorks_Methods = new Imperium_SmallWorks_Methods();
	static EconsysVariables ev=new EconsysVariables();
	static CommonUtils commonUtils = new CommonUtils();

	//Do not proceed scenario from CP2 to CP5
	@Test
	public void cp2_doNotProceed() throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException {

		imperium_SmallWorks_Methods.smallworkForm();
		commonUtils.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),ev.select_Yes);
		imperium_SmallWorks_Methods.submit_SW_QuoteForm();
		//board to Reject
		login.loginboard();
		b.projectname_ReviewApproval();
		ab.getComments().sendKeys("Reject @ CP2");
		ab.getRejectbutton().click();
		login.logout();

		//User login Sales leader to submit
		login.loginSL();
		b.projectname();
		commonUtils.selectByVisibleText(prepare_Quoteui.getBidSheetAuthorised(),ev.select_Yes);
		ab.getComments().sendKeys("Quote form small works");
		commonUtils.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),ev.select_Yes);
		ab.getSubmitbutton().click();
		login.logout();
		//User login as board to do do not proceed
		login.loginboard();
		b.projectname_ReviewApproval();
		ab.getComments().sendKeys("Do not proceed at CP2");
		ab.getDonotproccedbutton().click();
		String allert = driver.findElement(By.xpath("//div[@class='modal-body']")).getTagName();
		commonUtils.blindWait();
		ab.getDonot_Proceed_allret_Ok().click();
		System.out.println(allert+"project deleted");
		//donotproceed.search();
		Thread.sleep(200);
		if(driver.findElement(By.xpath("//div[contains(text(),'Your request has been processed successfully ')]")).isDisplayed()){
			log.info("pass cp2 do not proceed");
		}
	}

	@Test(priority=1)
	public void cp3_doNotProceed() throws IOException, InterruptedException, AWTException, ClassNotFoundException, SQLException {

		//Small works quote form to submit quote
		imperium_SmallWorks_Methods.quoteForm_SubmitQuote();

		b.projectname();
		commonUtils.waitForPageToLoad();
		commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getQuoteStatus(),ev.quoteStatusAmendBid);
		ab.getComments().sendKeys("Amend bid...");
		ab.getSubmitbutton().click();
		//login as Sales leader to resubmit the quote form
		imperium_SmallWorks_Methods.prepareRevisedQuote();
		if((ev.cp2cp3ourformat.equals("No"))){
			MatrixProjects.clApproval();
		}
		commonUtils.selectByVisibleText(prepare_Quoteui.getExecp3(),ev.select_Yes);
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();

		//login as Board user to reject at cp3
		login.loginboard();
		b.projectname_ReviewApproval();
		ab.getComments().sendKeys("Reject @ cp3");
		ab.getRejectbutton().click();
		login.logout();

		//login as Sales leader to resubmit the quote form
		login.loginSL();
		imperium_SmallWorks_Methods.prepareRevisedQuote2();
		commonUtils.selectByVisibleText(prepare_Quoteui.getExecp3(),ev.select_Yes);
		prepare_Quoteui.getQuoteprepared().click();
		login.logout();
		if((ev.cp2cp3ourformat.equals("No"))){
			MatrixProjects.clApproval(); 
		}
		login.loginboard();
		b.projectname_ReviewApproval();
		ab.getComments().sendKeys("Do not proceed at CP3");
		ab.getDonotproccedbutton().click();
		String allert = driver.findElement(By.xpath("//div[@class='modal-body']")).getTagName();
		commonUtils.blindWait();
		ab.getDonot_Proceed_allret_Ok().click();
		System.out.println(allert+"project deleted");
		commonUtils.blindWait();
		if(driver.findElement(By.xpath("//div[contains(text(),'Your request has been processed successfully ')]")).isDisplayed()){
			log.info("CP3 do not proceed - pass");
		}
	}
	@Test(priority=2)
	public void cp4_doNotProceed() throws IOException, InterruptedException, AWTException, ClassNotFoundException, SQLException {
		imperium_SmallWorks_Methods.quoteForm_SubmitQuote();

		actions_CP4();
	}
	@Test(priority=3)
	public void cp5_doNotProceed() throws IOException, InterruptedException, AWTException, ClassNotFoundException, SQLException {
		log.info("cp5_doNotProceed");
		//Small works quote form to submit quote
		imperium_SmallWorks_Methods.quoteForm_SubmitQuote();

		imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType_PO, ev.quoteStatusCCARecived);
		//cp4 board approval
		if(ev.execp4.equals("Yes") || (ev.clarification.equals("No")&&
				(ev.customerCommitmentType.equalsIgnoreCase(ev.customerCommitmentType_SubCon)||
						ev.customerCommitmentType.equalsIgnoreCase(ev.customerCommitmentType_PO)))){
			b.boardApproval();
		}
		//Submit response
		cp4_cp5.submitResponse();
		//CP4-CP5 Appoint key staff
		imperium_SmallWorks_Methods.apointkeystaf(ev.selectasSmallWorks);
		//Commercial suite application for payment
		imperium_SmallWorks_Methods.cummercialSuite_();
		//submit sales to operaton
		imperium_SmallWorks_Methods.salesToOperation();
		commonUtils.selectByVisibleText(sWp.getExe_cp5_sw(),ev.select_Yes);
		sWp.getSubmitBtn().click();
		login.logout();

		//Operation Acceptance reject Project leader
		imperium_SmallWorks_Methods.operation_acceptance();
		ab.getRejectbutton().click();
		login.logout();
		//submit sales to operaton
		commonUtils.selectByVisibleText(sWp.getExe_cp5_sw(),ev.select_Yes);
		sWp.getSubmitBtn().click();
		login.logout();

		imperium_SmallWorks_Methods.operation_acceptance();
		ab.getAcceptOperationAcceptance().click();
		login.logout();

		//Board Reject @ cp5
		login.loginboard();
		b.projectname_ReviewApproval();
		ab.getComments().sendKeys("Reject sales to operation...");
		ab.getRejectbutton().click();
		login.logout();
		//submit sales to operaton
		commonUtils.selectByVisibleText(sWp.getExe_cp5_sw(),ev.select_Yes);
		sWp.getSubmitBtn().click();
		login.logout();

		imperium_SmallWorks_Methods.operation_acceptance();
		ab.getAcceptOperationAcceptance().click();
		login.logout();

		login.loginboard();
		b.projectname_ReviewApproval();
		ab.getComments().sendKeys("Do not proceed at CP5");
		ab.getDonotproccedbutton().click();
		String allert = driver.findElement(By.xpath("//div[@class='modal-body']")).getTagName();
		Thread.sleep(1000);
		ab.getDonot_Proceed_allret_Ok().click();
		System.out.println(allert+"project deleted");

		Thread.sleep(200);
		if(driver.findElement(By.xpath("//div[contains(text(),'Your request has been processed successfully ')]")).isDisplayed()){
			log.info("pass cp5 do not proceed");
		}
	}
	public void search() throws InterruptedException, IOException{
		//Project archive action


		ab.getSearch().click();
		commonUtils.waitForPageToLoad();

		commonUtils.selectByVisibleText(driver.findElement(By.xpath("//tr[3]/td[2]/select")),"Project Name");
		commonUtils.selectByVisibleText(driver.findElement(By.xpath("//tr[3]/td[3]/select")),"contains");
		String projactName = wb.getXLData(1,2,1);
		if(ab.getSearchProjectname().getAttribute("value").isEmpty())

			ab.getSearchProjectname().sendKeys(projactName);

		ab.getFind().click();
		/*//driver.findElement(By.xpath("//tr[td[span[@title='"+ev.prjname+"']]]//td[13]/a[text()='Details']")).click();
	  driver.findElement(By.xpath("//tr[td[span[@title='"+ev.prjname+"']]]//td[13]/img[@title='Details']")).click();
	  Thread.sleep(500);
	  String projectName1=driver.findElement(By.xpath("//tr[td[label[contains(text(),'Project Name')]]]/td[2]")).getText();
	  Thread.sleep(500);
	  System.out.println(projectName1+" : project do not proceeded");
	  Thread.sleep(500);*/
		//Assert.assertEquals(projectName1, projectName);
	}

	//Used fr both small works and projects
	public static void submit_Explcit_CP4(){
		try {
			b.projectname();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		commonUtils.waitForPageToLoad();
		commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getQuoteStatus(),ev.quoteStatusCCARecived);
		//Customer Commitment recived
		commonUtils.waitForPageToLoad();
		commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getCustomerCommitmentType(),ev.customerCommitmentType_LOI);
		CosCommit_Quote_StatusUi.getUploadDoc_StatusofSubmitQuote().click();
		ab.getLinkFileCheckbox().click();
		ab.getAdd_LinkfilePopup().click();
		ab.getComments().sendKeys("Customer Commitment recived - "+ev.customerCommitmentType_LOI);
		ab.getSubmitbutton().click();
		//Customer Commitment
		commonUtils.waitForPageToLoad();
		String taskName = PropertiesUtil.getPropValues("customer_Commitment_Acceptance");
		try {
			b.projectTaskName(taskName);
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		commonUtils.selectByVisibleText(driver.findElement(By.xpath("//select[@id='st_request_for_cw']")),ev.select_No);
		ab.getComments().sendKeys("Customer Commitment Type");
		ev.loi_Commencement=ev.select_No;
		//Does LOI Received specifically request for commencement of work? = 'NO'
		if(ev.loi_Commencement.equals("No")){

			Imperium_SmallWorks_Methods.commencmentofWork_No();
			//Select explicit cp4 approval
			commonUtils.selectByVisibleText(CosCommit_Quote_StatusUi.getExeCP4(), ev.select_Yes);
			try {
				b.submit_Logout();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			commonUtils.waitForPageToLoad();
		}
	}
	//Used fr both small works and projects
	public void actions_CP4() throws IOException, InterruptedException{

		submit_Explcit_CP4();	
		try {
			TaskCP3CP4.TandCreview();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		//Cancel
		login.loginboard();
		b.projectname_ReviewApproval();
		ab.getCancelbutton().click();
		//Save
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[@id='groupApprovals']")).click();
		commonUtils.waitForPageToLoad();
		driver().findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]/td/a[contains(text(),'Open')]")).click();
		ab.getComments().sendKeys("Save CP4 form");
		ab.getSavebutton().click();
		//Reject
		commonUtils.waitForPageToLoad();
		driver.findElement(By.xpath("//label[@id='myApprovals']")).click();
		commonUtils.waitForPageToLoad();
		driver().findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]/td/a[contains(text(),'Open')]")).click();
		ab.getComments().sendKeys("Reject @ CP4");
		ab.getRejectbutton().click();
		login.logout();

		login.loginSL();
		submit_Explcit_CP4();

		//Submit T&C review
		login.loginCL();
		commonUtils.waitForPageToLoad();
		b.projectname();

		ab.getComments().sendKeys("T & C verification...");
		tandCver.submitT_Creview.click();
		login.logout();

		//CP4 board
		login.loginboard();
		b.projectname_ReviewApproval();
		ab.getComments().sendKeys("Do not proceed at CP4");
		ab.getDonotproccedbutton().click();
		String allert = driver.findElement(By.xpath("//div[@class='modal-body']")).getTagName();
		Thread.sleep(500);
		ab.getDonot_Proceed_allret_Ok().click();
		System.out.println(allert+"project deleted");
		Thread.sleep(200);
		if(driver.findElement(By.xpath("//div[contains(text(),'Your request has been processed successfully ')]")).isDisplayed()){
			log.info("pass cp4 do not proceed");
		}
	}
}
