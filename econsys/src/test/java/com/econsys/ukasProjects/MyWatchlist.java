package com.econsys.ukasProjects;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.econsys.Genriclibrery.*;
import com.econsys.Projects.*;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.*;

public class MyWatchlist extends Driver{
	Logger log = Logger.getLogger(MyWatchlist.class.getName());
	Login login = new Login();
	 boolean flag=false;
	
	AllPages allPages=PageFactory.initElements(Driver.driver(), AllPages.class);
	ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	public static Preparequote prepare_Quoteui=PageFactory.initElements(Driver.driver(), Preparequote.class);
	static PDPui pdp_ui=PageFactory.initElements(Driver.driver(),PDPui.class);
	static Salestooperation so=PageFactory.initElements(Driver.driver(),Salestooperation.class);
	static AppointkeystaffandCommerSuitUi ak=PageFactory.initElements(Driver.driver(), AppointkeystaffandCommerSuitUi.class);
	
	Basic basic=new Basic();
	EconsysVariables ev= new EconsysVariables();
	CommonUtils cu= new CommonUtils();
	static Workbook wb=new Workbook();
	static Monorail rtq=new Monorail();
	static ReviewInvolve ri=new ReviewInvolve();
	static TasksCP4toCP5 g45=new TasksCP4toCP5();
	static TaskCP3CP4 g34=new TaskCP3CP4();
	static Basic b=new Basic();
	static TasksCP5toCP9 pdop=new TasksCP5toCP9();

	String elementID;
	String projectName;
	//Add to watch list and Verify in My watch list
	public boolean addtoWatchList(){
		
		/*System.out.println(driver.findElement(By.xpath("//input[contains(@title,'Add')]/../..")).getAttribute("id"));
		 String elementID=driver.findElement(By.xpath("//input[contains(@title,'Add')]/../..")).getAttribute("id");
		 	  driver.findElement(By.xpath("//input[contains(@title,'Add')]")).click();
		 	  driver.findElement(By.xpath("//span[contains(text(),'My Watchlist')]")).click();
		 	  flag=driver.findElement(By.id(elementID)).isDisplayed();
		 	 return flag;*/
		
		 elementID=driver.findElement(By.xpath("//input[contains(@title,'Add')]/../../td[@data-content='ID']")).getAttribute("innerHTML");
		 log.info("process Incstance ID :"+elementID);
		  projectName = driver.findElement(By.xpath("//tr[td[@data-content='ID' and text()="+elementID+"]]//td[@data-content='Project Name']")).getAttribute("title");
		 log.info("projectName :"+projectName);
		 allPages.getAddToWatchlist().click();
		 
		 //verify in My Watchlist
		 allPages.getMyWatchList().click();
		 cu.waitForPageToLoad();	  
		 	  if(driver.findElements(By.xpath("//td[@data-content='ID' or text()='"+elementID+"']/following-sibling::td[@aria-describedby='delegatestaticGrid_projectName']")).size()==0){
		 		  do{
		 			 log.info("You entered Do while loop");
		 		  driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-seek-next icon-angle-right']")).click();
		 		  
		 		  }while(driver.findElements(By.xpath("//td[@data-content='Process IntanceId' or text()='"+elementID+"']/following-sibling::td[@aria-describedby='delegatestaticGrid_projectName']")).size()==0);
		 	  }
		 	  log.info("Logical expression");
		 	  boolean flag = driver.findElement(By.xpath("//td[@title='"+projectName+"']")).isDisplayed();
		 	  //log.info("flag1 :"+flag1);
		 	  //boolean flag2 = driver.findElement(By.xpath("//td[@data-content='ID' and text()='"+elementID+"']")).isDisplayed();
		 	  //log.info("flag2 :"+flag2);
		 	  
		 	 //flag=(flag1|flag2);
		 	 
		 	 return flag;
	}
	
	public void removeMyWatchlist() throws InterruptedException{
	
		cu.blindWait();
		log.info(""+elementID);
		//driver.findElement(By.xpath("//tr[td[data-content='ID' or text()="+elementID+"]]//input[@title='Remove from My Watchlist']")).click();
		log.info("projectName remove :"+projectName);
		driver.findElement(By.xpath("//td[4][@title='"+projectName+"']/preceding-sibling::td/input[@title='Remove from My Watchlist']")).click();
	}
	
  @Test
  public void verifyHomePage_to_WatchList() throws IOException, InterruptedException, AWTException {
	  login.url();
	  login.user();
	  /*int datainGrids = driver.findElements(By.xpath("//table[@id='staticGrid']//tr")).size();
	  log.info(""+datainGrids);
	  Thread.sleep(500);
	  driver.findElement(By.xpath("//table[@id='staticGrid']//tr[2]//input")).click();
	  */
	  ab.getViewalltasks().click();
	  cu.waitForPageToLoad();
		if(driver.findElement(By.xpath("//input[contains(@title,'Add')]")).isDisplayed()){
	 flag=addtoWatchList();
	 	 Assert.assertEquals(true, flag);
	 	removeMyWatchlist();
		}
		else{
			basic.rtqForm(ev.estimatedSize,ev.location);
		    //rtq submit externally
		    ab.getSubmitbutton().click();
		    flag=addtoWatchList();
		 	 Assert.assertEquals(true, flag);
		 	removeMyWatchlist();
		}
  //login.logout();
  }
  
//@Test
  public void verifyApprovals_to_MyWatchList() throws IOException, InterruptedException, AWTException{
	  login.user();
	  allPages.view_All_Approvals.click();
	  if(driver.findElement(By.xpath("//input[contains(@title,'Add')]")).isDisplayed()){
			 flag=addtoWatchList();
			 	 Assert.assertEquals(true, flag);
			 	removeMyWatchlist();
				}
				else{
					basic.rtqForm(ev.estimatedSize,ev.location);
				    //rtq submit externally
				    ab.getSubmitbutton().click();
				    flag=addtoWatchList();
				 	 Assert.assertEquals(true, flag);
				 	removeMyWatchlist();
				}
		  //login.logout();
  }
  
  
 @Test
 //Add project to my watch list from AllProjects
  public void verifyAllProjects_to_WatchList() throws InterruptedException, IOException, AWTException{
	  login.user();
	  allPages.getAllProjects().click();
	  int allProjects_Count = driver.findElements(By.xpath("//div[@id='gview_delegatestaticGrid']//tr")).size();
		log.info("allProjectsData : "+allProjects_Count);
	  if(driver.findElement(By.xpath("//input[contains(@title,'Add')]")).isDisplayed()){
			 flag=addtoWatchList();
			 	 Assert.assertEquals(true, flag);
			 	removeMyWatchlist();
				}
				else{
					/*basic.rtqForm(ev.estimatedSize,ev.location);
				    //rtq submit externally
				    ab.getSubmitbutton().click();
				    flag=addtoWatchList();
				 	 Assert.assertEquals(true, flag);
				 	 removeMyWatchlist();*/
				}
		  //login.logout();
  }
 @Test
//Add project to my watch list from Executive dash boardApproval
  public void verifyExeDashBoard_to_MyWatchList() throws IOException, InterruptedException, AWTException{
	  login.user();
	  allPages.getExeDashLink().click();
	  
	  //Open next page when no projects present in the first page
	  int a = driver.findElements(By.xpath("//input[contains(@title,'Add')]")).size();
	  log.info(""+a);
	  if(a==0){
		  log.info("next page");
		  
		  do{
	 			 log.info("You entered Do while loop");
	 			driver.findElement(By.xpath("//td[@id='next_pager']/span")).click();
	 		  
	 		  }while(driver.findElements(By.xpath("//input[contains(@title,'Add')]")).size()==0);
		  log.info("Ok");
	  }
	  
	  if(driver.findElement(By.xpath("//input[contains(@title,'Add')]")).isDisplayed()){
			 flag=addtoWatchList();
			 	 Assert.assertEquals(true, flag);
			 	removeMyWatchlist();
				}
				else{
					/*basic.rtqForm(ev.estimatedSize,ev.location);
				    //rtq submit externally
				    ab.getSubmitbutton().click();
				    flag=addtoWatchList();
				 	 Assert.assertEquals(true, flag);
				 	removeMyWatchlist();*/
				}
		 // login.logout();
  }
  @Test
  //Add project to my watch list from Project documents
  public void verifyProjectDoc_to_MyWatchList() throws IOException, InterruptedException, AWTException{
	  login.user();
	  allPages.getAdminLink().click();
	  allPages.getProDocslink().click();
	  if(driver.findElement(By.xpath("//input[contains(@title,'Add')]")).isDisplayed()){
		  /*System.out.println(driver.findElement(By.xpath("//input[contains(@title,'Add')]/../../td[@data-content='ID']")).getAttribute("innerHTML"));
			 String elementID=driver.findElement(By.xpath("//input[contains(@title,'Add')]/../../td[@data-content='ID']")).getAttribute("innerHTML");
			 	  driver.findElement(By.xpath("//input[contains(@title,'Add')]")).click();
			 	  driver.findElement(By.xpath("//span[contains(text(),'My Watchlist')]")).click();
			 	  flag=driver.findElement(By.xpath("//td[@data-content='ID' and text()="+elementID+"]")).isDisplayed();*/
			 	// log.info("Project Name in WatchList :"+driver.findElement(By.xpath("//td[@aria-describedby='delegatestaticGrid_processIntanceId' and text()="+elementID+"]/following-sibling::td[@aria-describedby='delegatestaticGrid_projectName']")).getAttribute("title"));
		  flag=addtoWatchList();
		  Assert.assertEquals(true, flag);
		  removeMyWatchlist();
				}
				else{
					/*basic.rtqForm(ev.estimatedSize,ev.location);
				    //rtq submit externally
				    ab.getSubmitbutton().click();
				    System.out.println(driver.findElement(By.xpath("//input[contains(@title,'Add')]/../../td[@aria-describedby='viewDocumentsGrid_processInstanceId']")).getAttribute("innerHTML"));
					 String elementID=driver.findElement(By.xpath("//input[contains(@title,'Add')]/../../td[@aria-describedby='viewDocumentsGrid_processInstanceId']")).getAttribute("innerHTML");
					 	  driver.findElement(By.xpath("//input[contains(@title,'Add')]")).click();
					 	  driver.findElement(By.xpath("//span[contains(text(),'My Watchlist')]")).click();
					 	  flag=driver.findElement(By.xpath("//td[@aria-describedby='delegatestaticGrid_processIntanceId' or text()="+elementID+"]/following-sibling::td[@aria-describedby='delegatestaticGrid_projectName']")).isDisplayed();
					 	 log.info("Project Name in WatchList :"+driver.findElement(By.xpath("//td[@aria-describedby='delegatestaticGrid_processIntanceId' and text()="+"'"+elementID+"'"+"]/following-sibling::td[@aria-describedby='delegatestaticGrid_projectName']")).getAttribute("title"));
				 	 Assert.assertEquals(true, flag);
				 	removeMyWatchlist();*/
				}
		 // login.logout();
  }
  
  @Test
  //Add project to my watch list from MyOpportunities
  public void verifyMyOpportunities_to_MyWatchList() throws IOException, InterruptedException, AWTException{
	  login.user();
	  allPages.getMyOpplink().click();
	  if(driver.findElement(By.xpath("//input[contains(@title,'Add')]")).isDisplayed()){
			 flag=addtoWatchList();
			 	 Assert.assertEquals(true, flag);
			 	removeMyWatchlist();
				}
				else{
					basic.rtqForm(ev.estimatedSize,ev.location);
				    //rtq submit externally
				    ab.getSubmitbutton().click();
				    flag=addtoWatchList();
				 	 Assert.assertEquals(true, flag);
				 	 removeMyWatchlist();
				}
		 // login.logout();
  }
@Test
  public void verifyMonthlyReview_to_MyWatchList() throws IOException, InterruptedException{
	  login.user();
	  allPages.getReviewLink().click();
	  if(driver.findElements(By.xpath("//input[contains(@title,'Add')]")).size()>0){
			 flag=addtoWatchList();
			 	 Assert.assertEquals(true, flag);
			 	removeMyWatchlist();
				}
}
  
 
  @Test
  public void verifySubmitQuote_to_MyWatchList() throws IOException, InterruptedException{
	  login.user();
	  
	  allPages.getReports().click();
	  allPages.getStatusofSubmitQuote().click();
	  
	  if(driver.findElements(By.xpath("//input[contains(@title,'Add')]")).size()>0){
			 flag=addtoWatchList();
			 log.info(""+flag);
			 	 Assert.assertEquals(true, flag);
			 	removeMyWatchlist();
				}
	  }
  }