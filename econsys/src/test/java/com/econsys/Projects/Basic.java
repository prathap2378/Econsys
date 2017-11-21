package com.econsys.Projects;
import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.econsys.UIobjectrepositary.*;
import com.econsys.Genriclibrery.*;
import com.econsys.SmallWorks.ProjectMethods_Small_Works;
import com.econsys.TestData.*;

public class Basic extends Driver {
	Preparequote pq=PageFactory.initElements(Driver.driver(), Preparequote.class);
	CommonUtils commonUtils = PageFactory.initElements(Driver.driver(), CommonUtils.class);
	RTQForm_Ui nrtq=PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
	Assignsalesleader sla=PageFactory.initElements(Driver.driver(), Assignsalesleader.class);
	CosCommitQuoteStatusUi ccq=PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	Logger log = Logger.getLogger(Basic.class.getName());
	//import classes
	static Workbook wb=new Workbook();
	static Monorail rtq=new Monorail();
	static Login login=new Login();
	static ReviewInvolve ri=new ReviewInvolve();
	static TasksCP4toCP5 g45=new TasksCP4toCP5();
	static TaskCP3CP4 g34=new TaskCP3CP4();
	static EconsysVariables ev = new EconsysVariables();
	
	//Variables
	String taskNameCP;
	static String productSpecified,Consultant,projectAddress,size=null;
	String isEnqueryOpentoAll,performanceBond,pCG,termsandconditionsadvised,endUserIndustrySector=null;
	String haveweworkedonthissitebefore,areweNamedSpecified,documentationReceived,points,typeofBuilding=null;
	String enquiryFormat,typeofProject=null;
	
  @Test
  //******RTQ form inputs******
	public void rtqForm(String estimatedSize,String location) throws InterruptedException, IOException,AWTException {
		
	  commonUtils.waitForPageToLoad();
	  nrtq.getRtqLink().click();
	  nrtq.getProjectLink().click();
	  
	  log.info("Project name basic: "+ev.projectName());
	  nrtq.getProjectName().sendKeys(ev.projectName());
	  
	  String paddress=wb.getXLData(6,1,1);
	  nrtq.getProjectAddres().sendKeys(paddress);
	  
	  String consultant=wb.getXLData(4,1,1);
	  nrtq.getConsultantName().sendKeys(consultant);
	  
	  String customer=wb.getXLData(2,1,1);
	  nrtq.getCustomerName().sendKeys(customer);
	  
	  // select Size
	  commonUtils.selectByVisibleText(nrtq.getEstimatedSize(), estimatedSize);
	  
	  
	  int quoteRefenceNumber = (int)(Math.random()*10000000);
	  //Company field only for 4eg
	  String orgName = driver.findElement(By.xpath("//*[@id='breadcrumbs']/ul/li[1]/a[text()='"+ev.org_Name+"']")).getText();
	  if(ev.org_4eg.equalsIgnoreCase(orgName)){
		  points=wb.getXLData(10,1,1);
		  nrtq.getPoints().sendKeys(""+points);
		  //select company
		  WebElement company = driver.findElement(By.xpath("//*[@id='st_company']"));
		  commonUtils.selectByIndex(company, 1);
		  commonUtils.selectByIndex(nrtq.getNeworExis(), 1);
		//Quote reference number
		  nrtq.getQuoterRefNumber().sendKeys(""+quoteRefenceNumber);
	  }
	  //Matrix specific fields 
	  if(ev.org_Matrixs.equalsIgnoreCase(orgName)){
		  points=wb.getXLData(10,1,1);
		  nrtq.getPoints().sendKeys(""+points);
		  //select quotation type
		  commonUtils.selectByIndex(nrtq.getQuotationType(), 1);
		//Quote reference number
		  nrtq.getQuoterRefNumber().sendKeys(""+quoteRefenceNumber);
	  }
	  
	  commonUtils.selectByIndex(nrtq.getNeworExis(), 1);
	  commonUtils.selectByIndex(nrtq.getLeadSource(), 1);
	  //anticipated date
	  nrtq.getAnticipatedDate().click();
	  driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/button")).click();
	  //Engineering
	  //productSpecified=wb.getXLData(12,1,1);
	  commonUtils.selectByIndex(nrtq.getProductSpecifieddd(),1);
	  
	  //typeofBuilding=wb.getXLData(18, 1, 1);
	  commonUtils.selectByIndex(nrtq.getTypeofBuildingdd(), 1);
	  
	  //endUserIndustrySector=wb.getXLData(14, 1, 1);
	  commonUtils.selectByIndex(nrtq.getEndUserIndustrySectordd(), 1);
	  
	  //typeofProject=wb.getXLData(16, 1, 1);
	  commonUtils.selectByIndex(nrtq.getTypeofProjectdd(), 1);
	  
	  //commercial
	  enquiryFormat=wb.getXLData(3,2,1);
	  commonUtils.selectByVisibleText(nrtq.getEnquiryFormatdd(), enquiryFormat);
	  
	  documentationReceived=wb.getXLData(5, 2, 1);
	  commonUtils.selectByVisibleText(nrtq.getDocumentationReceiveddd(), documentationReceived);
	  
	  areweNamedSpecified=wb.getXLData(7, 2, 1);
	  commonUtils.selectByVisibleText(nrtq.getGANameddd(), areweNamedSpecified);
	  
	  //select Location
	  commonUtils.selectByVisibleText(nrtq.getLocationdd(), location);	  
	  	  
	  haveweworkedonthissitebefore=wb.getXLData(11, 2, 1);
	  commonUtils.selectByVisibleText(nrtq.getPreviousExperienceAtSitedd(), haveweworkedonthissitebefore);
	  
	  termsandconditionsadvised=wb.getXLData(13, 2, 1);
	  commonUtils.selectByVisibleText(nrtq.getTermsConditionsAdviseddd(), termsandconditionsadvised);
	   
	  pCG=wb.getXLData(15,2,1);
	  commonUtils.selectByVisibleText(nrtq.getPCGdd(), pCG);
	  
	  performanceBond=wb.getXLData(17, 2,1);
	  commonUtils.selectByVisibleText(nrtq.getPerformanceBond(), performanceBond);
	  	
	  isEnqueryOpentoAll=wb.getXLData(19, 2, 1);
	  commonUtils.selectByVisibleText(nrtq.getIsenqueryopentoall(), isEnqueryOpentoAll);
	  
	  String damage_Advised=wb.getXLData(22,1,1);
	  commonUtils.selectByVisibleText(nrtq.getDamages_Advised(),damage_Advised);
	  
	  String retention_Advised=wb.getXLData(21,2,1);
	  commonUtils.selectByVisibleText(nrtq.getRetention_Advised(),retention_Advised);
	  if(retention_Advised.equalsIgnoreCase(ev.select_Yes)){
			driver.findElement(By.xpath("//input[@id='st_RetentionValue']")).sendKeys("10");
		}
	  
	  //File upload in RTQ fom
	  nrtq.getUploaddocument().click();
	  
	  ProjectMethods_Small_Works project_Metods = new ProjectMethods_Small_Works();
	  project_Metods.uploadFile("Logfails - Copy (19).txt");
	 
	  //uploading documents in case not uploaded for first time
	  commonUtils.waitForPageToLoad();
	  WebElement rtqDocs = driver.findElement(By.xpath("//div[@id='upload-container']"));
	  int docsCount = rtqDocs.findElements(By.xpath(".//*")).size();
	  log.info("docsCount RTQ :"+docsCount);
	  
	   if(docsCount<1){
		   do{
			   log.info("ReUpload document");
			   nrtq.getUploaddocument().click();
			   project_Metods.uploadFile("Milestone Document 1.txt");
			   log.info("Uploaded");
		   }while(docsCount<1);
	   }
	   
	   else{
		   commonUtils.waitForPageToLoad();
			  String rtqComments=wb.getXLData(20,1,1);
			  nrtq.getComments().sendKeys(rtqComments);
			  //ab.getSavebutton().click();
	   }
	  
  }

//green-amber-red 'Decide path'
public void pathdession(String estimatedSize,String location) throws InterruptedException, IOException{
		
		if(!(ev.eSizertq2.equals(ev.estimatedSize0to100k_)||ev.estimatedSize.equalsIgnoreCase(ev.estimatedSize250_)||ev.estimatedSize.equalsIgnoreCase(ev.estimatedSize0to100k_))){
			System.out.println("Eng review path");
			ri.reviewEL();
		}
		if(!(ev.locationrtq2.equals(ev.location_inside)||ev.location.equalsIgnoreCase(ev.location_inside)||ev.location.equalsIgnoreCase(ev.location_SouthEast))){
			ri.reviewCL();
		}
		if(ev.estimatedSize.equals(ev.estimatedSize500_)){
			System.out.println("Eng Involve path");
			ri.involveEL();
		}
		if(ev.location.equals(ev.location_other)){
			System.out.println("Comer Involve path");
			ri.involveCL();
		}
	}
	
	public void pathdessioncp2cp3(String estimatedSize,String location) throws InterruptedException, IOException{
		
		if(estimatedSize.equals(ev.estimatedSize250_)){
			ri.reviewEL();
		}
		if(location.equals(ev.location_SouthEast)){
			ri.reviewCL();
		}
		if(ev.eSizertq2.equals(ev.estimatedSize500_)){
			ri.involveEL();
		}
		if(ev.locationrtq2.equals(ev.location_other)){
			ri.involveCLcp2cp3();
		}
	}
	
	//green-amber-red 'Decide path'
	public void pathdession_Mat(String estimatedSize,String location) throws InterruptedException, IOException{
			
			if(estimatedSize.equals(ev.estimatedSize250_)){
				login.loginEL();
			    ri.reviewInvolvecommon();
			}
			if(location.equals(ev.location_SouthEast)){
				login.loginCL();
				ri.reviewInvolvecommon();
			}
			if(estimatedSize.equals(ev.estimatedSize500_)){
				ri.involveEL();
			}
			if(location.equals(ev.location_other)){
				ri.involveCL();
			}
		}
	public void pathdessioncp2cp3_Mat(String estimatedSize,String location) throws InterruptedException, IOException{
			
			if(estimatedSize.equals(ev.estimatedSize250_)){
				login.loginEL();
			    ri.reviewInvolvecommon();
			}
			if(location.equals(ev.location_SouthEast)){
				login.loginCL();
				ri.reviewInvolvecommon();
			}
			if(estimatedSize.equals(ev.estimatedSize500_)){
				ri.involveEL();
			}
			if(location.equals(ev.location_other)){
				ri.involveCL();
			}
		}
	//to get Action button of Project any where in portal 
	//this can be used to find Action button for specific project
	public void actionButton() throws IOException{
		driver.findElement(By.xpath("//td[@title="+ev.projectName()+"]//following-sibling::td/div/button")).click();
	}
	
public void projectname() throws InterruptedException, IOException{
	
	Thread.sleep(1000);
	ab.getViewalltasks().click();
	String actionbutton = "//td[@title="+ev.projectName()+"]//following-sibling::td/div/button";
	commonUtils.blindWait();
	commonUtils.WaitForElementXPATHPresent(actionbutton);
	driver.findElement(By.xpath(actionbutton)).click();
	   
	String exactXpathForProject = "//div[@id='action-items-div']/table/tbody/tr[1]/td/span[contains(text(),'Open')]";
	commonUtils.WaitForElementXPATHPresent(exactXpathForProject);
	driver.findElement(By.xpath(exactXpathForProject)).click();
}
public String projectID() throws IOException{
	String n = driver.findElement(By.xpath("//tr[td[@title="+ev.projectName()+"]]")).getAttribute("id");
	return n;
 }
public void projectTaskName(String taskName) throws InterruptedException, IOException{
	
	ab.getViewalltasks().click();
	commonUtils.blindWait();
	String projectName = "//tr[td[@title='"+taskName+"']][td[@title="+ev.projectName()+
			"]]//preceding-sibling::td/a[contains(text(),'"+taskName+"')]";	
	try{
		driver.findElement(By.xpath(projectName)).click();
	}catch(StaleElementReferenceException e){
	}catch(WebDriverException w){
		commonUtils.blindWait();
	}
}
public void pnRtq() throws InterruptedException, IOException{
	
	String taskName = PropertiesUtil.getPropValues("Rtq");
	projectTaskName(taskName);	
}
public void pnPrepareQuote() throws InterruptedException, IOException{
	
	String taskName = PropertiesUtil.getPropValues("prepare_Quote");
	projectTaskName(taskName);	
}
public void pnReSubmitedQuote() throws InterruptedException, IOException{
	
	String taskName = PropertiesUtil.getPropValues("resubmit_Quote");
	projectTaskName(taskName);
}
public void edit_StatusofSubmitedQuote() throws InterruptedException, IOException{
	
	String taskName = PropertiesUtil.getPropValues("status_ofSubmitted_Quote");
	projectTaskName(taskName);	
}
public void pnAppointKeyStaff() throws InterruptedException, IOException{
	
	String taskName = PropertiesUtil.getPropValues("appoint_key_staff");
	projectTaskName(taskName);	
}
public void edit_Salesto_Operation() throws InterruptedException, IOException{
	
	String taskName = PropertiesUtil.getPropValues("salesto_Operation");
	projectTaskName(taskName);	
}
public void edit_OperationAcceptance() throws InterruptedException, IOException{
	
	String taskName = PropertiesUtil.getPropValues("operations_Acceptanceof_Handover");
	projectTaskName(taskName);	
}
public void pnpdp() throws InterruptedException, IOException{
	
	String taskName = PropertiesUtil.getPropValues("PDP");
	projectTaskName(taskName);	
}
public void pnDeliveryReview() throws InterruptedException, IOException{
	
	String taskName = PropertiesUtil.getPropValues("delivery_Review");
	projectTaskName(taskName);	
}
//Open Project
public void projectname_ReviewApproval() throws InterruptedException, IOException{
	//label[@id='myInvolved']
	commonUtils.waitForPageToLoad();
	driver.findElement(By.xpath("//label[@id='groupApprovals']")).click();
	//taskNameCP = driver.findElement(By.xpath("//td[@title="+ev.projectName()+"]//preceding-sibling::td[1]")).getAttribute("title");
	//System.out.println("Task Name "+taskNameCP);//td[@title="+ev.projectName()+"]//preceding-sibling::td[3]
	String exactXpathForProject = "//tr[td[@title="+ev.projectName()+"]]//following-sibling::td/a[contains(text(),'Open')]";
	commonUtils.blindWait();
	//52343-CMB 20 Fenchurch Street
	commonUtils.WaitForElementXPATHPresent(exactXpathForProject);
	driver().findElement(By.xpath(exactXpathForProject)).click();
	}
//task listed under by New list
public void projectName_New() throws IOException, InterruptedException{
	driver.findElement(By.xpath("//*[@id='gate3']/div/ul/li[1]/a")).click();
	String exactXpathForProject = "//tr[td[@title="+ev.projectName()+"]]//following-sibling::td/a[contains(text(),'Open')]";
	commonUtils.blindWait();
	//52343-CMB 20 Fenchurch Street
	commonUtils.WaitForElementXPATHPresent(exactXpathForProject);
	driver().findElement(By.xpath(exactXpathForProject)).click();
}
//task listed under by me list
public void projectName_Board_Byme() throws InterruptedException, IOException{
	commonUtils.waitForPageToLoad();
	driver.findElement(By.xpath("//label[@id='myApprovals']")).click();
	String exactXpathForProject = "//tr[td[@title="+ev.projectName()+"]]//following-sibling::td/a[contains(text(),'Open')]";
	commonUtils.WaitForElementXPATHPresent(exactXpathForProject);
	driver().findElement(By.xpath(exactXpathForProject)).click();
}
public void projectName_Board_ByGroupApprovals() throws InterruptedException, IOException{
	commonUtils.blindWait();
	driver.findElement(By.xpath("//label[@id='groupApprovals']")).click();
	String exactXpathForProject = "//td[@title="+ev.projectName()+"]//following-sibling::td/a[contains(text(),'Open')]";
	commonUtils.WaitForElementXPATHPresent(exactXpathForProject);
	driver().findElement(By.xpath(exactXpathForProject)).click();
}
public void projectname_Involves() throws InterruptedException, IOException{
	
	commonUtils.waitForPageToLoad();
	driver.findElement(By.xpath("//label[@id='myInvolved']")).click();
	String exactXpathForProject = "//tr[td[@title="+ev.projectName()+"]]//following-sibling::td/a[contains(text(),'Open')]";
	commonUtils.blindWait();
	commonUtils.WaitForElementXPATHPresent(exactXpathForProject);
	driver().findElement(By.xpath(exactXpathForProject)).click();
				
}
public void projectname_Reviews() throws IOException, InterruptedException{
	
	commonUtils.waitForPageToLoad();
	driver.findElement(By.xpath("//label[@id='myReviews']")).click();
	String exactXpathForProject = "//tr[td[@title="+ev.projectName()+"]]//following-sibling::td/a[contains(text(),'Open')]";
	commonUtils.blindWait();
	commonUtils.WaitForElementXPATHPresent(exactXpathForProject);
	driver().findElement(By.xpath(exactXpathForProject)).click();
}
//Submit the form and logOut 
public void submit_Logout() throws InterruptedException{
	//submit form
	  ab.getSubmitbutton().click();
	  commonUtils.waitForPageToLoad();
	//log out  
	  login.logout();
}
//Board Approval
	 public void boardApproval() throws IOException, InterruptedException{
		try {
		
		 login.loginboard();
		 commonUtils.waitForPageToLoad();
		 log.info("open project");
		 //driver().findElement(By.xpath("//div[@id='gate3']/div/ul/li[1]/a[contains(text(),'New')]")).click();
		 //actions.moveToElement(element).click().perform();
		 taskNameCP = driver.findElement(By.xpath("//td[@title="+ev.projectName()+"]//preceding-sibling::td[3]")).getAttribute("title");
		 log.info("taskNameCP------"+taskNameCP);
		 projectname_ReviewApproval();
		 if(taskNameCP.equals("Control Point 4 Approval")){
			 commonUtils.blindWait();
			 commonUtils.selectByVisibleText(driver().findElement(By.xpath("//select[@id='st_authoriseCommencement']")), ev.atherize);
		 }
		 Thread.sleep(1000);
		 ab.getComments().sendKeys("Board");
		 ab.getApprove_Button().click();
		 if(taskNameCP.equals("Control Point 9 Approval")){
			 
			 commonUtils.blindWait();
		     driver.findElement(By.xpath("//div[@class='modal-footer']/a[1]")).click();
		 }
		 login.logout();
		} catch (StaleElementReferenceException se) {
			se.printStackTrace();
		}
		}
	 
	 public void search() throws InterruptedException, IOException{

			//Project archive action
			ab.getSearch().click();
			commonUtils.waitForPageToLoad();

			commonUtils.selectByVisibleText(driver.findElement(By.xpath("//tr[3]/td[2]/select")),"Project");
			commonUtils.selectByVisibleText(driver.findElement(By.xpath("//tr[3]/td[3]/select")),"contains");

			if(ab.getSearchProjectname().getAttribute("value").isEmpty())
				ab.getSearchProjectname().sendKeys(ev.prjname1);
			ab.getFind().click();
		}
}
