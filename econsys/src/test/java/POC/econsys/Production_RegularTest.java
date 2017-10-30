package POC.econsys;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.econsys.Genriclibrery.*;
import com.econsys.Projects.*;
import com.econsys.SmallWorks.ProjectMethods_Small_Works;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.*;

public class Production_RegularTest extends Driver{
	//Page factory
	Preparequote pq=PageFactory.initElements(Driver.driver(), Preparequote.class);
	CommonUtils cu=PageFactory.initElements(Driver.driver(), CommonUtils.class);
	RTQForm_Ui nrtq=PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
	Assignsalesleader asl=PageFactory.initElements(Driver.driver(), Assignsalesleader.class);
	CosCommitQuoteStatusUi ccq=PageFactory.initElements(Driver.driver(), CosCommitQuoteStatusUi.class);
	ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	LoginPageui loginui=PageFactory.initElements(Driver.driver(), LoginPageui.class);
	
	//Class import
	Basic b = new Basic();
	Workbook wb = new Workbook();
	static Monorail rtq=new Monorail();
	static Login login=new Login();
	static ReviewInvolve ri=new ReviewInvolve();
	static TasksCP4toCP5 g45=new TasksCP4toCP5();
	static TaskCP3CP4 g34=new TaskCP3CP4();
	Robotqq r = new Robotqq();
	//private SoftAssert softAssert = new SoftAssert();
	
	String estimatedSize, location;
	static String ourformat, cp2cp3ourformat, bidsheetauthorised, cp2cp3bidsheetauthorised=null;
	static String cusrecivedas, scope, docrefrence, termsandcond, clarification=null;
	static String docsatis, haveweworkedonthissitebefore=null;
	static String execp4, areweNamedSpecified, termsandconditionsadvised=null;
	static String exe, size, enquiryFormat, documentationReceived=null;
	String productSpecified, projectAddress, points, typeofBuilding, endUserIndustrySector, typeofProject;
	String Customer, pCG, performanceBond, isEnqueryOpentoAll, Consultant;
	String prjname;
	
	Production_RegularTest() throws IOException{
	estimatedSize = wb.getXLData(20, 6, 1);
	location = wb.getXLData(20, 7, 1);
	}
	
	@BeforeTest
	public void url() throws IOException{
		String url = wb.getXLData(1,0,3);
		driver.get(url);
		//cu.maximize();
	}
@Test
 public void assign_Sales_Leader() throws IOException, InterruptedException {
	  
	  login.loginSD();
	  Thread.sleep(500);
   	  ab.getViewalltasks().click();
   	  
   	  cu.waitForPageToLoad();
   	  String prjname=wb.getXLData(3,0,3);
   	  String nprjname = "\""+prjname+"\"";
   	  driver.findElement(By.xpath("//td[@title="+nprjname+"]//following-sibling::td/div/button")).click();
   	  //String exactXpathForProject = "//tr[td[@title="+nprjname+"]]/td[9]/a[text()='Open']";
   	  cu.blindWait();
   	  //td[@title="+ev.projectName()+"]//following-sibling::td/a[contains(text(),'Open')]
   	  driver.findElement(By.xpath("//div[@id='action-items-div']/table/tbody/tr[1]/td/span[contains(text(),'Open')]")).click();
   	  //driver().findElement(By.xpath(exactXpathForProject)).click();
	  
   	  /*ab.getViewalltasks().click();*/
	  Select select=new Select(asl.getSalesleader());
	  
	  String sl=wb.getXLData(7,3, 0);
	  //select sales leader
	  
	  select.selectByVisibleText(sl);
	  
	  asl.getComments().sendKeys("Asigne sales leader");
	  
	  asl.getAllocate().click();

	  cu.blindWait();
	  login.logout();
	  
	  }
@Test(priority=1)
  public void rtq_Submit() throws InterruptedException, IOException, AWTException {
	  	  
	  //User login
	  loginui.getLoginName().sendKeys("Stephenmonorail@gmail.com");
	  //log.info("login as user:"+un+"");
	  loginui.getPasword().sendKeys("test1");
	  loginui.getLoginButton().click();
	  
	  cu.waitForPageToLoad();
	  //Old RTQ Form page
	  driver.findElement(By.linkText("RTQ")).click();
	  driver().findElement(By.linkText("Project")).click();
	  
	  wb.setExcelData(3, 3, 0, "econsysTest_"+Dates.dateFormat());
	  
	  String prjname=wb.getXLData(3,0,3);
	  nrtq.getProjectName().sendKeys(prjname);
	  
	  String paddress=wb.getXLData(6,1,1);
	  nrtq.getProjectAddres().sendKeys(paddress);
	  
	  String consultant=wb.getXLData(4,1,1);
	  nrtq.getConsultantName().sendKeys(consultant);
	  
	  String customer=wb.getXLData(2,1,1);
	  nrtq.getCustomerName().sendKeys(customer);
	  
	  //select Size
	  estimatedSize=wb.getXLData(5,0,3);
	  cu.selectByVisibleText(nrtq.getEstimatedSize(), estimatedSize);
	  
	  points=wb.getXLData(10,1,1);
	  nrtq.getPoints().sendKeys(""+points);
	  
	  //Engineering
	  productSpecified=wb.getXLData(12,1,1);
	  cu.selectByVisibleText(nrtq.getProductSpecifieddd(), productSpecified);
	  
	  typeofBuilding=wb.getXLData(18, 1, 1);
	  cu.selectByVisibleText(nrtq.getTypeofBuildingdd(), typeofBuilding);
	  
	  endUserIndustrySector=wb.getXLData(14, 1, 1);
	  cu.selectByVisibleText(nrtq.getEndUserIndustrySectordd(), endUserIndustrySector);
	  
	  typeofProject=wb.getXLData(16, 1, 1);
	  cu.selectByVisibleText(nrtq.getTypeofProjectdd(), typeofProject);
	  
	  //commercial
	  enquiryFormat=wb.getXLData(3,2,1);
	  cu.selectByVisibleText(nrtq.getEnquiryFormatdd(), enquiryFormat);
	  
	  documentationReceived=wb.getXLData(5, 2, 1);
	  cu.selectByVisibleText(nrtq.getDocumentationReceiveddd(), documentationReceived);
	  
	  areweNamedSpecified=wb.getXLData(7, 2, 1);
	  cu.selectByVisibleText(nrtq.getGANameddd(), areweNamedSpecified);
	  
	  //select Location
	  location=wb.getXLData(7,0,3);
	  cu.selectByVisibleText(nrtq.getLocationdd(), location);	  
	  	  
	  haveweworkedonthissitebefore=wb.getXLData(11, 2, 1);
	  cu.selectByVisibleText(nrtq.getPreviousExperienceAtSitedd(), haveweworkedonthissitebefore);
	  
	  termsandconditionsadvised=wb.getXLData(13, 2, 1);
	  cu.selectByVisibleText(nrtq.getTermsConditionsAdviseddd(), termsandconditionsadvised);
	   
	  pCG=wb.getXLData(15,2,1);
	  cu.selectByVisibleText(nrtq.getPCGdd(), pCG);
	  
	  performanceBond=wb.getXLData(17, 2,1);
	  cu.selectByVisibleText(nrtq.getPerformanceBond(), performanceBond);
	  	
	  isEnqueryOpentoAll=wb.getXLData(19, 2, 1);
	  cu.selectByVisibleText(nrtq.getIsenqueryopentoall(), isEnqueryOpentoAll);
	  
	  String retention_Advised=wb.getXLData(21,2,1);
	  cu.selectByVisibleText(nrtq.getRetention_Advised(),retention_Advised);
	  
	  String damage_Advised=wb.getXLData(22,1,1);
	  cu.selectByVisibleText(nrtq.getDamages_Advised(),damage_Advised);

	  String filepath=System.getProperty("user.dir");
	  Thread.sleep(1000);
	  
	  //nrtq.getUploaddocument().click();
	  //driver.findElement(By.name("flm_rtqForm")).sendKeys(filepath+"\\Documentsuploded\\G1 Gateway Approval.doc");
	  //nrtq.getUploaddocument().sendKeys(filepath+"\\Documentsuploded\\G1 Gateway Approval.doc");

	  nrtq.getUploaddocument().click();

	  ProjectMethods_Small_Works project_Metods = new ProjectMethods_Small_Works();
	  project_Metods.uploadFile("Logfails - Copy (19).txt");

	  String rtqComments=wb.getXLData(20,1,1);
	  nrtq.getComments().sendKeys(rtqComments);
	  
	  nrtq.getSubmit().click();
	  cu.waitForPageToLoad();
	  login.logout();
  }
 
// @Test(priority=2)
  public void GTM_Login() throws InterruptedException{
	//Need to change 
  }
 //@Test
 public void popup() throws InterruptedException{
	 
	    driver.get("http://www.freepdfconvert.com/pdf-word");
	    Thread.sleep(1000);
	    System.out.println("click client upload");
	    driver.findElement(By.xpath("//input[@id='clientUpload']")).click();
	    driver.switchTo().activeElement().sendKeys("/home/likewise-open/GLOBAL/123/Documents/filename.txt");
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    driver.findElement(By.id("convertButton"));
 }
}
