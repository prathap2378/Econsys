package com.econsys.Projects;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.econsys.Genriclibrery.*;
import com.econsys.SmallWorks.ProjectMethods_Small_Works;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.*;


public class TaskCP3CP4 extends Driver{
	
	//Page UI classes
	Preparequote pq=PageFactory.initElements(Driver.driver(), Preparequote.class);
	static CommonUtils cu=PageFactory.initElements(Driver.driver(), CommonUtils.class);
	RTQForm_Ui nrtq=PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
	Assignsalesleader sla=PageFactory.initElements(Driver.driver(), Assignsalesleader.class);
	CosCommitQuoteStatusUi ccq=PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	static TandCverification tandCver = PageFactory.initElements(driver(), TandCverification.class);
	static ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	SDapprTandCOPacceptanceui ato=PageFactory.initElements(Driver.driver(),SDapprTandCOPacceptanceui.class);
	
	//import classes
	Logger log = Logger.getLogger(TaskCP3CP4.class.getName());
	static Workbook wb = new Workbook();
	static Monorail rtq = new Monorail();
	static Login login = new Login();
	static ReviewInvolve ri = new ReviewInvolve();
	static TasksCP4toCP5 g45 = new TasksCP4toCP5();
	static Basic b = new Basic();
	static TaskCP3CP4 g34 = new TaskCP3CP4();
	EconsysVariables ev = new EconsysVariables();
	static ProjectMethods_Small_Works pr_Methods = new ProjectMethods_Small_Works();
	//Veriables
	static String contract_Value;
	String filepath=System.getProperty("user.dir");
	
		
  @Test
  //****Customer Commitment sales New Flow******
  public void customercommit() throws IOException, InterruptedException{
		 //log.info("this method is customercommit().....");		 
		 
		 login.loginSL();
		 String taskName = PropertiesUtil.getPropValues("customer_Commitment_Acceptance_Sales");
		 System.out.println("open project");
		 b.projectTaskName(taskName);
		 System.out.println("opened");
		 cu.blindWait();
		 cu.selectByVisibleText(ccq.getFinal_quoted_scope(), ev.final_quoted_scope);
		 
		 cu.selectByVisibleText(ccq.getDocs_referenced(),ev.docs_Refrenced);
		 
		 cu.selectByVisibleText(ccq.getTerms_and_conditions(), ev.terms_and_conditions);
		 
		 ev.clarification=wb.getXLData(14, 7, 0);
		 cu.selectByVisibleText(ccq.getClarificationsSelectBox(), ev.clarification);
		 	if(ev.clarification.equals("No")){

		 		driver.findElement(By.xpath("//input[@id='img_cmt_startFinalQuote']")).click();
		 		driver.findElement(By.xpath("//*[@id='x_cmt_startFinalQuote']")).sendKeys("Selected No");
		 		driver.findElement(By.xpath("//*[@id='saveCommentBubbleATagstartFinalQuote']")).click();
		 	}
		 
		 if(ev.final_quoted_scope.equals("No")||ev.docs_Refrenced.equals("No")||ev.terms_and_conditions.equals("No")
				 ||ev.clarification.equals("No")){
				
			 ccq.getReseon_for_No().sendKeys("Reason for No"); 
		 }
		 
		 ab.getComments().sendKeys("Customer commitment Sales");
		 b.submit_Logout();
			 
		 customerAcceptanceOPS();
//		 b.submit_Logout();
		 
		 
	 }
  {
  if(ev.final_quoted_scope.equals("No")||ev.docs_Refrenced.equals("No")||ev.terms_and_conditions.equals("No")||ev.project_Start_Date.equals("No")||ev.project_End_Date.equals("No")){
		try {
			g34.clApproval();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
  }
  //Customer commitment Acceptance Ops
  public void customerAcceptanceOPS() throws IOException, InterruptedException{
	  login.loginOD();
	  String taskName = PropertiesUtil.getPropValues("customer_Commitment_Acceptance_Ops");
	  b.projectTaskName(taskName);
	  cu.selectByVisibleText(ccq.getProject_Start_Date(), ev.project_Start_Date);
	  cu.selectByVisibleText(ccq.getProject_End_Date(), ev.project_End_Date);

	  if(ev.project_Start_Date.equals("No")||ev.project_End_Date.equals("No")){

		  driver.findElement(By.xpath("//input[@id='fileList_flm_startForm']")).click();
		  pr_Methods.linktoFileupload();
	  }
		 ccq.getComments().sendKeys("Coustomer commitment Ops");
  }
 
  public void scopeDocandContractValueVerification() throws IOException, InterruptedException{
	  
	  //login.loginSL();
		 String taskName = PropertiesUtil.getPropValues("scope_Doc_Verification");
		 log.info("scope_Doc_Verification task name *****"+taskName);
		 cu.blindWait();
		 b.projectname();
		 System.out.println("opened");
		 cu.blindWait();
		 cu.selectByVisibleText(ccq.getFinal_quoted_scope(), ev.final_quoted_scope);
		 
		 cu.selectByVisibleText(ccq.getDocs_referenced(),ev.docs_Refrenced);
		 
		 cu.selectByVisibleText(ccq.getTerms_and_conditions(), ev.terms_and_conditions);
		 
		 ev.clarification=wb.getXLData(14, 7, 0);
		 cu.selectByVisibleText(ccq.getClarificationsSelectBox(), ev.clarification);
		 	if(ev.clarification.equals("No")){

		 		driver.findElement(By.xpath("//input[@id='img_cmt_startFinalQuote']")).click();
		 		driver.findElement(By.xpath("//*[@id='x_cmt_startFinalQuote']")).sendKeys("Selected No");
		 		driver.findElement(By.xpath("//*[@id='saveCommentBubbleATagstartFinalQuote']")).click();
		 	}
		 cu.selectByVisibleText(ccq.getProject_Start_Date(), ev.project_Start_Date);
		 cu.selectByVisibleText(ccq.getProject_End_Date(), ev.project_End_Date);

		 if(ev.final_quoted_scope.equals("No")||ev.docs_Refrenced.equals("No")||ev.terms_and_conditions.equals("No")
				 ||ev.clarification.equals("No")||ev.project_Start_Date.equals("No")||ev.project_End_Date.equals("No")){
			  driver.findElement(By.xpath("//input[@id='fileList_flm_scopeDocs']")).click();
			  pr_Methods.linktoFileupload();
			  
			  driver.findElement(By.xpath("//textarea[@id='reasonForNo']")).sendKeys("Reason");
		 }
		 
		 ab.getComments().sendKeys("Scope Doc and Contract Value Verification");
		 cu.selectByVisibleText(ccq.getExeCP4(), ev.execp4);
		 b.submit_Logout();

		 TandCreview();
  }
		
//T and C review in Matrix, PAG and 4eg
	 public static void TandCreview() throws InterruptedException, IOException{
		 login.loginCL();
		 cu.waitForPageToLoad();
		 b.projectname();
		 
		 cu.selectByIndex(tandCver.getCreditWorthy(), 1);
		 //upload order acknowledgement file
		 tandCver.getOrderAcknowledgement_file().click();
		 ProjectMethods_Small_Works.linktoFileupload();
		 
		 ab.getComments().sendKeys("T & C verification...");
		 tandCver.submitT_Creview.click();
		 login.logout();
	 }

  //Prepare response task
  public void prepareResponseSales() throws IOException, InterruptedException{
	  cu.waitForPageToLoad();
	  //login.loginSL();
	  String taskName = PropertiesUtil.getPropValues("prepare_Response");
	  b.projectTaskName(taskName);
	  ccq.getTextAreainPrepareResponse().sendKeys("Response by sales");
	  driver.findElement(By.xpath("//input[@id='fileList_flm_slreponseDocs']")).click();
	  pr_Methods.linktoFileupload();
	  ab.getComments().sendKeys("Prepare responce sales");
	  login.logout();
  }
  
  public void clApproval() throws IOException, InterruptedException{
	  	login.loginCL();
	  	b.projectname_ReviewApproval();
	  	ab.getComments().sendKeys("Customer Commitment CL Approval");
	  	ab.getApprove_Button().click();
	  	login.logout();
  }
  public void opsApproval() throws IOException, InterruptedException{
	  	login.loginOD();
	  	driver().findElement(By.xpath("//div[@id='gate3']/div/ul/li[1]/a")).click();
	  	b.projectname_ReviewApproval();
	  	select_Dropdowns();
	  	ab.getComments().sendKeys("Customer Commitment OD Approval");
	  	ab.getApprove_Button().click();
	  	login.logout();
}
  public void select_Dropdowns(){
	  List<WebElement> dropdown = driver.findElements(By.xpath("//div[@id='operationsleaderacceptance_Div']//select"));
	  for(int i=0;i<dropdown.size();i++){
		  WebElement selectElement=dropdown.get(i);
		  cu.selectByVisibleText(selectElement, "Yes");
	  }
  }
 
}