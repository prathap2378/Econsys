package com.econsys.SmallWorks;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.econsys.Projects.*; 
import com.econsys.UIobjectrepositary.*;
import com.econsys.matrix.MatrixProjects;
import com.econsys.Genriclibrery.*;
import com.econsys.TestData.*;

public class ProjectMethods_Small_Works extends Driver{
	//Page factory
	static Logger log=Logger.getLogger(ProjectMethods_Small_Works.class.getName());
	static RTQForm_Ui rtq = PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
	static Preparequote prepare_Quoteui = PageFactory.initElements(Driver.driver(), Preparequote.class);
	static CommonUtils commonutils = PageFactory.initElements(Driver.driver(), CommonUtils.class);
	static ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	static smallWorkPageElements sWp= PageFactory.initElements(Driver.driver(),smallWorkPageElements.class);
	TandCverification TandCver = PageFactory.initElements(driver(), TandCverification.class);
	static Salestooperation sales=PageFactory.initElements(Driver.driver(), Salestooperation.class);
	static AppointkeystaffandCommerSuitUi appointKeyStaff_CommercialSuite_Ui=PageFactory.initElements(Driver.driver(), AppointkeystaffandCommerSuitUi.class);
	CosCommitQuoteStatusUi ccq=PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	LoginPageui loginui=PageFactory.initElements(Driver.driver(), LoginPageui.class);
	PDPui pdp_Ui=PageFactory.initElements(Driver.driver(),PDPui.class);
	
	static //Import class
	Login login = new Login();
	static Workbook wb = new Workbook();
	static TaskCP3CP4 g34=new TaskCP3CP4();
	static Basic b = new Basic();
	Monorail monorail=new Monorail();
	static EconsysVariables ev = new EconsysVariables();
	boolean flag=false;
	static String filepath=System.getProperty("user.dir");
	public static void uploadFile(String filename) throws InterruptedException  {
			
		//Specify the file location with extension
		StringSelection selctFilepath = new StringSelection(filepath+"\\Documentsuploded\\"+filename);
		//Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selctFilepath,null);
		
		try {
		Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				
				Thread.sleep(4000);
				robot.keyPress(KeyEvent.VK_CANCEL);
				// you are in a popup
				/*Frame frame = null;
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));*/
				//URL doc = ProjectMethods_Small_Works.class.getResource("D:\\Workspace\\Monorail\\src\\com\\testdata\\in\\Monorail_testoutput.xls");
				//System.out.println("url:"+doc);
			} catch (AWTException e) {
				e.printStackTrace();
			}
	}

public static void linktoFileupload() throws InterruptedException{
		ProjectMethods_Small_Works pm = new ProjectMethods_Small_Works();
		commonutils.blindWait();
		int linktoFileCheckbox_Size = driver.findElements(By.xpath("//input[@class='list_item_link_docs_multiple']")).size();
		if(linktoFileCheckbox_Size>0){
			ab.getLinkFileCheckbox().click();
			ab.getAdd_LinkfilePopup().click();
			}
				else{
					commonutils.waitForPageToLoad();
					driver.findElements(By.xpath("//td/a[contains(text(),'Close')]")).get(0).click();
					log.info("check box not present so uploading file again");
					log.info("Files are not available");
					pm.uploadFile("Updated Risk and Opportunity Register 3.txt");
			}
	}
public boolean smallworkForm() throws InterruptedException, IOException, ClassNotFoundException, SQLException{
		int rownum= wb.getrowNum(2);
		boolean flag=false;
			
			login.loginSL();
			
			//DBConnection db = new DBConnection();
			//log.info(DBConnection.emailConfiguration());
			
			rtq.getNewform().click();
			//driver.findElement(By.xpath("//li[@id='layout_47']/a")).click();
			rtq.getSmallWorks().click();
			/*commonutils.blindWait();
			driver.findElement(By.xpath("//a/span[contains(text(),'mall')]")).click();*/
			commonutils.waitForPageToLoad();
			rtq.getProjectName().sendKeys(ev.projectName());
			rtq.getCustomerName().sendKeys(wb.getXLData(3, 1, 1));
			commonutils.selectByIndex(rtq.getSmallWorksType(), 1);
			
			//Company field only for 4eg
			  String orgName = (driver.findElement(By.xpath("//*[@id='breadcrumbs']/ul/li[1]/a[text()='"+ev.org_Name+"']"))).getText();
			  if(ev.org_4eg.equalsIgnoreCase(orgName)){
				  WebElement company = driver.findElement(By.xpath("//*[@id='st_company']"));
					commonutils.selectByIndex(company, 1);
			  }
			  /*int i1 = (int)(Math.random()*10000000);
			  driver.findElement(By.id("st_ats_quoterRefNumber")).sendKeys(""+i1);*/
			//Quote Info
			commonutils.selectByVisibleText(prepare_Quoteui.getQuotationonourFormat(), wb.getXLData(2, 5, 0));
			prepare_Quoteui.getOverallProjectCost().sendKeys(wb.getXLData(1, 4, 2));
			String sell = wb.getXLData(1, 5, 2);
			prepare_Quoteui.getOverallProjectSell().sendKeys(wb.getXLData(1, 5, 2));
			
			//File upload Quote document small works
			//Driver.driver().findElement(By.xpath("//div[@id='quoteDocument-dropzone']")).click();
			
			//uploadFile("Copy of Panel Production Schedule Template.xlsx");
			
			for(int i=1;i<=rownum;i++){
			prepare_Quoteui.getAddnewpopup().click();
		    commonutils.waitForPageToLoad();
		    prepare_Quoteui.getCostCodeCategorytextfield().sendKeys(wb.getXLData(i, 0, 2));
		    prepare_Quoteui.getCost().sendKeys(wb.getXLData(i, 1, 2));
			prepare_Quoteui.getSell().sendKeys(wb.getXLData(i, 2, 2));
			prepare_Quoteui.getSaveAddcostsellpopup().click();
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,250)", "");
		    }
			
			if (driver instanceof JavascriptExecutor) {
			    ((JavascriptExecutor)driver).executeScript("setTimeout(function () { }, 0)");
			} else {
			    throw new IllegalStateException("This driver does not support JavaScript!");
			}
			commonutils.blindWait();
			/*prepare_Quoteui.getBidsheet_linkfile().click();*/
			//linktoFileupload();
			driver.findElement(By.xpath("//div[@id='bidSheet-dropzone']")).click();
			uploadFile("Logfails - Copy (20).txt");
			
			driver.findElement(By.xpath("//input[@id='fileList_flm_quoteDocument']")).click();
			linktoFileupload();
			commonutils.selectByVisibleText(prepare_Quoteui.getBidSheetAuthorised(), wb.getXLData(4, 5, 0));
			prepare_Quoteui.getComments().sendKeys("Small Work form ...");
			commonutils.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(), ev.exeCP2);
			//rtq.getSubmit().click();
			
			//boolean alert = Driver.driver().findElements(By.xpath("//div[@class='modal-body']")).size()>0;
			
			int sellValue_Configured = DBConnection.sell_Value_SmallWorks();
			int sell_Actual = Integer.parseInt(sell);
			String option_SmallWorks_or_Project = wb.getXLData(18,9,0);
			
			if(sell_Actual>sellValue_Configured){
				commonutils.blindWait();
				Driver.driver().findElement(By.xpath("//a[text()='"+option_SmallWorks_or_Project+"']")).click();
				if(option_SmallWorks_or_Project.contentEquals("Yes")){
					Thread.sleep(2000);
					log.info("This is taking Project workflow, Currently saving the Project form");
					rtq.getSave().click();
					flag=true;
				}
				else{
					log.info("Continues with Small Works flow");
				}
			}
			log.info("log out small work form");
			//login.logout();
			log.info("loged out");
			return flag;
}

//prepare Revised Quite...
public void prepareRevisedQuote() throws InterruptedException, IOException{
	
	//Project name
	String taskName = PropertiesUtil.getPropValues("prepare_Revised_Quote");
	b.projectTaskName(taskName);
	commonutils.blindWait();
	
	//bid document link
	prepare_Quoteui.getCp2cp3biddoc().click();
	linktoFileupload();	
	//Quote document link
	driver.findElement(By.xpath("//input[@id='fileList_flm_quoteDocument']")).click();
	linktoFileupload();
	//Bid sheet value drop down value selection
	commonutils.selectByVisibleText(prepare_Quoteui.getCp2cp3bidsheet(), wb.getXLData(15, 5, 0));
	prepare_Quoteui.getComments().sendKeys("rePrepare quote...");

}
//Submit quote form
public static void submit_Quoteform() throws InterruptedException, IOException{
		//boolean flag=false;
		login.loginSL();
		b.projectname();
		/*try {
			commonutils.waitForPageToLoad();
			//**Verify the Quote form 
			if(sWp.getProInfo_accordion().isDisplayed()&&sWp.getQuoteInfo_accordion().isDisplayed()){
				
				flag=true;
				log.info("All elements present in the Quote form");
			}*/
			commonutils.waitForPageToLoad();
			rtq.getComments().sendKeys("Submit Quote");
			sWp.getSubmitBtn().click();
			//login.logout();
		/*} catch (Exception e) {
			e.printStackTrace();
		} 
		Assert.assertEquals(flag, true);*/
	}

//status of submitted quote for small works GA
public void statusQuotesubmit(String customerCommitmentType,String quoteStatus) throws IOException, InterruptedException{
	
	 commonutils.waitForPageToLoad();
	 String taskName = PropertiesUtil.getPropValues("status_ofSubmitted_Quote");
	 b.projectTaskName(taskName);
	 commonutils.waitForPageToLoad();
	 log.info("quotestatuscp2cp3 : "+quoteStatus);
	 
	 commonutils.selectByVisibleText(ccq.getQuoteStatus(),quoteStatus);
	 commonutils.waitForPageToLoad();
	 	if(quoteStatus.equals("Customer Commitment Received")){
	 		{
	 			commonutils.selectByVisibleText(ccq.getCustomerCommitmentType(), customerCommitmentType);
	 			
	 			if(!"Verbal Commitment Received - Under Review".equals(customerCommitmentType)){
	 			ccq.getUploadDoc_StatusofSubmitQuote().click();
	 			linktoFileupload();
	 			}
	 		}
	 ab.getComments().sendKeys("Quote status updated as "+quoteStatus);
	 ccq.getSubmit().click();
	 
	//Customer commitment acceptance logic
	 	monorail.customerCommitmentAcceptance_Logic(customerCommitmentType);
	 	}
	 	
	 	else if(quoteStatus.equals("Amend Bid")){
	
		 ab.getComments().sendKeys("Quote status is Amend Bid");
		 ccq.getSubmit().click();	 
		 prepareRevisedQuote();
		 
		 commonutils.selectByVisibleText(prepare_Quoteui.getExecp3(),ev.exeCP3);
		 prepare_Quoteui.getQuoteprepared().click();
		 login.logout();
		 
		 if((ev.ourformat.equals("Yes")&&ev.cp2cp3ourformat.equals("No"))||(ev.cp2cp3bidsheetauthorised.equals("No"))||(ev.exeCP3.equals("Yes"))){
			 
			 b.boardApproval();
		 }
		 submit_Quoteform();
		 status_ResubmittedQuote();
	 	}
}
//Status of submitted quote Matrix
//New customer commitment flow Matrix and PAG(no verbal) are same
public void statusQuotesubmit_(String customerCommitmentType,String quoteStatus) throws IOException, InterruptedException{
	
	 commonutils.waitForPageToLoad();
	 String taskName = PropertiesUtil.getPropValues("status_ofSubmitted_Quote");
	 b.projectTaskName(taskName);
	 commonutils.waitForPageToLoad();
	 log.info("quotestatuscp2cp3 : "+quoteStatus);
	 
	 commonutils.selectByVisibleText(ccq.getQuoteStatus(),quoteStatus);
	 commonutils.waitForPageToLoad();
	 	if(quoteStatus.equals("Customer Commitment Received")){
	 		{
	 			commonutils.selectByVisibleText(ccq.getCustomerCommitmentType(), customerCommitmentType);
	 			
	 			if(!"Verbal Commitment Received - Under Review".equals(customerCommitmentType)){
	 			ccq.getUploadDoc_StatusofSubmitQuote().click();
	 			linktoFileupload();
	 			}
	 		}
	 ab.getComments().sendKeys("Quote status updated as "+quoteStatus);
	 ccq.getSubmit().click();
	 
	//Customer commitment acceptance logic
	 	MatrixProjects.CCAlogic_Matrix(customerCommitmentType);
	 	}
	 	
	 	else if(quoteStatus.equals("Amend Bid")){
	
		 ab.getComments().sendKeys("Quote status is Amend Bid");
		 ccq.getSubmit().click();	 
		 prepareRevisedQuote();
		 
		 commonutils.selectByVisibleText(prepare_Quoteui.getExecp3(),ev.exeCP3);
		 prepare_Quoteui.getQuoteprepared().click();
		 login.logout();
		 
		 if(ev.ourformat.equals("No")){
			MatrixProjects.clApproval(); 
		 }
		 if((ev.exeCP3.equals("Yes"))){
			b.boardApproval();
		 }
		 else if ((ev.cp2cp3ourformat.equals("No"))||(ev.cp2cp3bidsheetauthorised.equals("No"))) {
			MatrixProjects.sdApproval();
		}
		 
		 submit_Quoteform();
		 status_ResubmittedQuote_();
	 	}
}

//status of resubmit quote small works for GA
public void status_ResubmittedQuote() throws InterruptedException, IOException{
	//login.loginSL();
	 commonutils.waitForPageToLoad();
	 String taskName = PropertiesUtil.getPropValues("status_ofRe_SubmittedQuote");
	 b.projectTaskName(taskName);
	 commonutils.waitForPageToLoad();
	 
	 commonutils.selectByVisibleText(ccq.getQuoteStatus(),ev.quote_StatusCp3Cp4);

	 if(ev.quote_StatusCp3Cp4.equals("Customer Commitment Received")){ 
	 {
		 commonutils.waitForPageToLoad();
		 
		 commonutils.selectByVisibleText(ccq.getCustomerCommitmentType(), ev.customerCommitmentType);
		 
		 if(!"Verbal Commitment Received - Under Review".equals(ev.customerCommitmentType)){
			 ccq.getUploadDoc_StatusofSubmitQuote().click();
			 linktoFileupload();
	 		 }
	 }
	 ab.getComments().sendKeys("Quote status is Customer Commitment Received");
	 commonutils.waitForPageToLoad();
	 
	 ab.getSubmitbutton().click();
	//Customer commitment acceptance logic
	 	monorail.customerCommitmentAcceptance_Logic(ev.customerCommitmentType);
	 }
	 
	 //******Amend bid******
	 else if(ev.quote_StatusCp3Cp4.equals("Amend Bid")){
	 	 
		 prepareRevisedQuote();
		 commonutils.selectByVisibleText(prepare_Quoteui.getExecp3(),ev.exeCP3);
		 prepare_Quoteui.getQuoteprepared().click();
		 login.logout();
		 
		 if((ev.ourformat.equals("Yes")&&ev.cp2cp3ourformat.equals("No"))||(ev.cp2cp3bidsheetauthorised.equals("No"))||(ev.exeCP3.equals("Yes"))){
			 
			 b.boardApproval();
		 }
		 submit_Quoteform();
		 status_ResubmittedQuote();
	 }

}
//status of resubmit quote small works Matrix
public void status_ResubmittedQuote_() throws InterruptedException, IOException{
	//login.loginSL();
	 commonutils.waitForPageToLoad();
	 String taskName = PropertiesUtil.getPropValues("status_ofRe_SubmittedQuote");
	 b.projectTaskName(taskName);
	 commonutils.waitForPageToLoad();
	 
	 commonutils.selectByVisibleText(ccq.getQuoteStatus(),ev.quote_StatusCp3Cp4);

	 if(ev.quote_StatusCp3Cp4.equals("Customer Commitment Received")){ 
	 {
		 commonutils.waitForPageToLoad();
		 
		 commonutils.selectByVisibleText(ccq.getCustomerCommitmentType(), ev.customerCommitmentType);
		 
		 if(!"Verbal Commitment Received - Under Review".equals(ev.customerCommitmentType)){
			 ccq.getUploadDoc_StatusofSubmitQuote().click();
			 linktoFileupload();
	 		 }
	 }
	 ab.getComments().sendKeys("Quote status is Customer Commitment Received");
	 commonutils.waitForPageToLoad();
	 
	 ab.getSubmitbutton().click();
	//Customer commitment acceptance logic
	 	MatrixProjects.CCAlogic_Matrix(ev.customerCommitmentType);
	 }
	 
	 //******Amend bid******
	 else if(ev.quote_StatusCp3Cp4.equals("Amend Bid")){
	 	 
		 prepareRevisedQuote();
		 commonutils.selectByVisibleText(prepare_Quoteui.getExecp3(),ev.exeCP3);
		 prepare_Quoteui.getQuoteprepared().click();
		 login.logout();
		 
		 if((ev.ourformat.equals("Yes")&&ev.cp2cp3ourformat.equals("No"))||(ev.cp2cp3bidsheetauthorised.equals("No"))||(ev.exeCP3.equals("Yes"))){
			 
			 b.boardApproval();
		 }
		 submit_Quoteform();
		 status_ResubmittedQuote();
	 }

}
//Sales to operation small works
public static void salesToOperation(){
	try {
		login.loginSL();
		b.edit_Salesto_Operation();
		prepare_Quoteui.getAddnewpopup().click();
		sWp.getDescription().sendKeys("Test1");
		prepare_Quoteui.getSaveAddcostsellpopup().click(); // Save button of Add New pop up

		sWp.getUpload_sales().click();
		commonutils.blindWait();
		sWp.getLinkTofilebtn().click();
		commonutils.blindWait();
		linktoFileupload();
		commonutils.selectByVisibleText(sales.getMeeting(), wb.getXLData(22, 7, 0));

		String delegatetoPL=wb.getXLData(22,7,0);
		String customerCommitmentType= ev.customerCommitmentType;
		if(customerCommitmentType.equals("LOI Received - Under Review")||customerCommitmentType.equals("Email Received - Under Review")
				||customerCommitmentType.equals("Verbal Commitment Received - Under Review")){

			commonutils.selectByVisibleText(sales.getDelegateTaskto_PL(),delegatetoPL);
		}
		sales.getComments().sendKeys("Sales to Operation form ...");
	} catch (Exception e) {
		e.printStackTrace();
	} 
}
//Operation acceptance
	public static void operation_acceptance(){
	
		try{
			login.loginPL();
			commonutils.blindWait();
			b.edit_OperationAcceptance();
			List<WebElement> opacce=Driver.driver().findElements(By.xpath("//td[@aria-describedby='salesToOperationGrid_action']/select"));
		 	
			for(int j=0;j<opacce.size();j++){
				commonutils.waitForPageToLoad();	
				commonutils.selectByVisibleText(opacce.get(j),"Yes");
			}
		
			commonutils.selectByVisibleText(appointKeyStaff_CommercialSuite_Ui.getMeetingwithsales(),wb.getXLData(6, 9, 0));
			
//			  customer commitment type based delegate option selection
			  String customerCommitmentType= ev.customerCommitmentType;
		      if(customerCommitmentType.equals("LOI Received - Under Review")||customerCommitmentType.equals("Email Received - Under Review")
		   			||customerCommitmentType.equals("Verbal Commitment Received - Under Review")){
			 	int a = driver.findElements(By.xpath("//select[@id='st_cCChangeTaskOwnerOPS']")).size();
			 	if(a>0){
			 		commonutils.selectByVisibleText(driver.findElement(By.xpath("//select[@id='st_cCChangeTaskOwnerOPS']")),"Yes");
			 	}
		      }
			
			prepare_Quoteui.getComments().sendKeys("Operation Acceptance Form ...");
		   	/*ab.getReviewInvolveapprovebutton().click();
		   	login.logout();*/
		}catch(Exception e){
			e.printStackTrace();
			}
	}
	
	public void pdp() throws IOException, InterruptedException {
		  
		  login.loginPL();
		  b.pnpdp();
		  
		  List<WebElement> deletebutton=Driver.driver().findElements(By.xpath("//a[starts-with(@id,'delete_')]"));
		  for(int i=0;i<deletebutton.size();i++){
			  commonutils.blindWait();
			  deletebutton.get(i).click();
			  commonutils.blindWait();
			  Driver.driver().findElement(By.xpath("//a[@class='btn btn-small btn-info']")).click();
		  }
		  commonutils.blindWait();
		  /*pdp.getPlandoc().sendKeys(filepath+"\\Documentsuploded\\plandoc.docx");*/
		  pdp_Ui.getProject_Programme_document().click();
		  linktoFileupload();
		  
		  /*Mile stone Comments*/
		  Thread.sleep(1000);
		  /*Driver.driver().findElement(By.xpath("//textarea[starts-with(@id,'x_cmt_prodAppForcGrid_Row')]")).sendKeys("milestone");
		  Driver.driver().findElement(By.linkText("Save")).click();*/
		  /*Mile stone document*/
		  Driver.driver().findElement(By.xpath("//input[@id='fileList_flm_milestoneDocument']")).click();
		  linktoFileupload();
		  
		  
		  ab.getComments().sendKeys("PDP form ...");
		  /*
		  commonutils.selectByVisibleText(pdp_Ui.getExecp6(),execp6);
		  Thread.sleep(1000);
		  sWp.getSubmitBtn().click();
		  Thread.sleep(1000);
		  login.logout();*/
	  }
	public void submit_Delivery_Review() throws InterruptedException, IOException{
		  login.loginPL();
		  b.pnDeliveryReview();
		  
		  Driver.driver().findElement(By.id("mdrProgDocument-dropzone")).click();
		  uploadFile("Project programme document 1.txt");
		  Driver.driver().findElement(By.id("mdrMilestoneDocument-dropzone")).click();
		  uploadFile("Milestone Document 1.txt");
		  
		  
		  ab.getComments().sendKeys("Delivery review submit");
		  
	  }
	public void review_Delivery_Review() throws IOException, InterruptedException{
		login.loginPL();
		b.pnDeliveryReview();
		  
		  Driver.driver().findElement(By.id("mdrProgDocument-dropzone")).click();
		  uploadFile("Project programme document 2.txt");
		  Driver.driver().findElement(By.id("mdrMilestoneDocument-dropzone")).click();
		  uploadFile("Milestone Document 2.txt");
		  
		  
		  ab.getComments().sendKeys("Delivery review to monthly review");
		  
	}
	public static void od_approval() throws IOException, InterruptedException{
		//Login as ****** OD
		  login.loginOD();
		  b.projectname_ReviewApproval();
		  ab.getComments().sendKeys("Approved monthly review");
		  ab.getApprove_Button().click();
		  login.logout();
	}
public void project_complted_DeliveryReview() throws IOException, InterruptedException{
	/*login as PL *********** Project Completed*/
	  login.loginPL();
	  b.pnDeliveryReview();
	  
	  Driver.driver().findElement(By.id("mdrProgDocument-dropzone")).click();
	  uploadFile("Project programme document 3.txt");
	  Driver.driver().findElement(By.id("mdrMilestoneDocument-dropzone")).click();
	  uploadFile("Milestone Document 3.txt");
	  
	  
	  ab.getComments().sendKeys("Delivery review project completed with out changes");
	  ab.getProjectCompletedbutton().click();
	  commonutils.blindWait();
	  Driver.driver().findElement(By.xpath("//a[@class='btn btn-small btn-info']")).click();
	  commonutils.blindWait();
	  login.logout();
}
	//Delivery Review
  public void deliveryreview() throws IOException, InterruptedException{
	  ProjectMethods_Small_Works delivery_ReviewMethods = new ProjectMethods_Small_Works();
		  
		  login.url();
		  String deliveryReview_dission=wb.getXLData(10,12,0);
		  
		  
		  //login as PL to Delivery Review *********** submit
		  if(deliveryReview_dission.equals("Submit")){
			  delivery_ReviewMethods.submit_Delivery_Review();
			  ab.getSubmitbutton().click();
			  login.logout();
		  }
		 
		 // login as PL for ************ Monthly Review
		  if(deliveryReview_dission.equals("Monthly Review")){
			  delivery_ReviewMethods.review_Delivery_Review();
			  Driver.driver().findElement(By.xpath("//input[@value='To Monthly Review']")).click();
			  login.logout();
		  }

		  delivery_ReviewMethods.od_approval();
	
		  delivery_ReviewMethods.project_complted_DeliveryReview();
		  
		  delivery_ReviewMethods.od_approval();
		
	  }
  //Small work rtq form with out file upload
  public void smallWorkForm1() throws IOException, InterruptedException{
			
			login.loginSL();
			
			rtq.getNewform().click();
			rtq.getSmallWorks().click();
			commonutils.waitForPageToLoad();
			rtq.getProjectName().sendKeys(ev.projectName());
			rtq.getCustomerName().sendKeys(wb.getXLData(3, 1, 1));
			commonutils.selectByIndex(rtq.getSmallWorksType(), 1);
			//Quote Info
			commonutils.selectByVisibleText(prepare_Quoteui.getQuotationonourFormat(), wb.getXLData(2, 5, 0));
			
			prepare_Quoteui.getComments().sendKeys("Small Work form ...");
			
  }
  public void smallWorkForm2() throws InterruptedException, IOException{
			login.url();
			login.loginSL();
			
			//rtq.getNewform().click();
			driver.findElement(By.xpath("//li[@id='layout_47']/a")).click();
			//rtq.getSmallWorks().click();
			commonutils.blindWait();
			driver.findElement(By.xpath("//a/span[contains(text(),'mall')]")).click();
			commonutils.waitForPageToLoad();
			rtq.getProjectName().sendKeys(ev.projectName());
			rtq.getCustomerName().sendKeys(wb.getXLData(3, 1, 1));
			commonutils.selectByIndex(rtq.getSmallWorksType(), 1);
			//Quote Info
			commonutils.selectByVisibleText(prepare_Quoteui.getQuotationonourFormat(), wb.getXLData(2, 5, 0));
			prepare_Quoteui.getOverallProjectCost().sendKeys("12");
			prepare_Quoteui.getOverallProjectSell().sendKeys("15");
			
			prepare_Quoteui.getAddnewpopup().click();
		    commonutils.waitForPageToLoad();
		    prepare_Quoteui.getCostCodeCategorytextfield().sendKeys("Quote");
		    prepare_Quoteui.getCost().sendKeys("12");
			prepare_Quoteui.getSell().sendKeys("15");
			prepare_Quoteui.getSaveAddcostsellpopup().click();
			
			//File upload Quote document small works
			commonutils.blindWait();
			Driver.driver().findElement(By.xpath("//div[@id='quoteDocument-dropzone']")).click();
			commonutils.waitForPageToLoad();
			uploadFile("Copy of Panel Production Schedule Template.xlsx");

			commonutils.blindWait();
			if (driver instanceof JavascriptExecutor) {
			    ((JavascriptExecutor)driver).executeScript("setTimeout(function () { }, 0)");
			} else {
			    throw new IllegalStateException("This driver does not support JavaScript!");
			}
			//Select bid sheet
			commonutils.selectByVisibleText(prepare_Quoteui.getBidSheetAuthorised(), wb.getXLData(15, 5, 0));
			
			prepare_Quoteui.getBidsheet_linkfile().click();
			linktoFileupload();
			
			prepare_Quoteui.getComments().sendKeys("Small Work form ...");
			//rtq.getSubmit().click();
			
  }
}
