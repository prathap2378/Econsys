package com.ecosys.imperium;

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
import com.econsys.SmallWorks.ProjectMethods_Small_Works;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.*;

public class MyWatchlistImperium extends Driver{
	Logger log = Logger.getLogger(MyWatchlistImperium.class.getName());
	Login login = new Login();
	boolean flag=false;

	AllPages allPages=PageFactory.initElements(Driver.driver(), AllPages.class);
	ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	public static Preparequote prepare_Quoteui=PageFactory.initElements(Driver.driver(), Preparequote.class);
	static PDPui pdp_ui=PageFactory.initElements(Driver.driver(),PDPui.class);
	static Salestooperation salestooperation =PageFactory.initElements(Driver.driver(),Salestooperation.class);
	static AppointkeystaffandCommerSuitUi ak=PageFactory.initElements(Driver.driver(), AppointkeystaffandCommerSuitUi.class);

	Basic basic = new Basic();
	EconsysVariables ev= new EconsysVariables();
	CommonUtils commonUtils = new CommonUtils();
	static Workbook wb=new Workbook();
	static Monorail rtq=new Monorail();
	static ReviewInvolve ri=new ReviewInvolve();
	static TasksCP4toCP5 g45=new TasksCP4toCP5();
	static TaskCP3CP4 g34=new TaskCP3CP4();
	static TasksCP5toCP9 pdop=new TasksCP5toCP9();
	Imperium_Project_Methods imperium_Project_Methods = new Imperium_Project_Methods(); 
	Imperium_SmallWorks_Methods imperium_SmallWorks_Methods = new Imperium_SmallWorks_Methods();
	String elementID;
	String projectName;

	//Add to watch list and Verify in My watch list
	public boolean addtoWatchList(){

		elementID = driver.findElement(By.xpath("//input[contains(@title,'Add')]/../../td[@data-content='ID']")).getAttribute("innerHTML");
		log.info("process Incstance ID :"+elementID);
		projectName = driver.findElement(By.xpath("//tr[td[@data-content='ID' and text()="+elementID+"]]//td[@data-content='Project']")).getAttribute("title");
		log.info("projectName :"+projectName);
		allPages.getAddToWatchlist().click();

		//verify in My Watchlist
		allPages.getMyWatchList().click();
		commonUtils.waitForPageToLoad();	  
		if(driver.findElements(By.xpath("//td[@data-content='ID' or text()='"+elementID+"']/following-sibling::td[@aria-describedby='delegatestaticGrid_projectName']")).size()==0){
			do{
				log.info("You entered Do while loop");
				driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-seek-next icon-angle-right']")).click();
			}while(driver.findElements(By.xpath("//td[@data-content='Process IntanceId' or text()='"+elementID+"']/following-sibling::td[@aria-describedby='delegatestaticGrid_projectName']")).size()==0);
		}
		log.info("Logical expression");
		boolean flag = driver.findElement(By.xpath("//td[@title='"+projectName+"']")).isDisplayed();
		return flag;
	}

	public void removeMyWatchlist() throws InterruptedException{

		commonUtils.blindWait();
		log.info(""+elementID);
		//driver.findElement(By.xpath("//tr[td[data-content='ID' or text()="+elementID+"]]//input[@title='Remove from My Watchlist']")).click();
		log.info("projectName remove :"+projectName);
		driver.findElement(By.xpath("//td[@title='"+projectName+"']/preceding-sibling::td/input[@title='Remove from My Watchlist']")).click();
	}
	@Test(priority=0)
	public void verifyApprovals_to_MyWatchList() throws IOException, InterruptedException, AWTException{
		login.user();
		commonUtils.blindWait();
		ab.getGroupapprovals().click();
		if(driver.findElement(By.xpath("//input[contains(@title,'Add')]")).isDisplayed()){
			flag=addtoWatchList();
			Assert.assertEquals(true, flag);
			removeMyWatchlist();
		}
		else{
			imperium_Project_Methods.rtqForm(ev.estimatedSize500_,ev.location);
			//rtq submit externally
			ab.getSubmitbutton().click();
			flag=addtoWatchList();
			Assert.assertEquals(true, flag);
			removeMyWatchlist();
		}
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
		else{
			try{
				//****intiation of rtq form*********
				imperium_Project_Methods.rtqForm(ev.estimatedSize0to100k_,ev.location_inside);

				//rtq submit externally
				ab.getSubmitbutton().click();

				//Logout
				login.logout();

				//***********CP1 exe dession************
				if((ev.estimatedSize.equals("D 500+"))||(ev.location.equals("Other"))) {
					basic.boardApproval();
				}

				imperium_Project_Methods.ASL();

				//Prepare Quote
				imperium_Project_Methods.prepare_Quote(ev.overallSell,ev.locationrtq2);
				commonUtils.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),ev.exeCP2);
				prepare_Quoteui.getQuoteprepared().click();
				login.logout();

				basic.pathdession(ev.estimatedSize,ev.location);
				//**********CP2 exe dession**************
				if((ev.ourformat.equals("No"))||(ev.bidsheetauthorised.equals("No"))||(ev.exeCP2.equals("Yes"))){
					basic.boardApproval();
				}
				ProjectMethods_Small_Works.submit_Quoteform();
				imperium_SmallWorks_Methods.statusQuotesubmit_(ev.customerCommitmentType, ev.quote_StatusCp2Cp3);
				//**********CP4 exe dession New Flow**************
				if(ev.execp4.equals("Yes") || (ev.clarification.equals("No") &&
						(ev.customerCommitmentType.equalsIgnoreCase(ev.customerCommitmentType_PO) || 
								ev.customerCommitmentType.equalsIgnoreCase(ev.customerCommitmentType_SubCon)))){
					basic.boardApproval();
				}
				//Submit response
				g45.submitResponse();
				
				imperium_Project_Methods.permission_to_Commence();
				//Appoint Key staff
				imperium_SmallWorks_Methods.apointkeystaf(ev.selectasProject);

				//commercial suite
				imperium_SmallWorks_Methods.cummercialSuite_();
				//Sales to operation hand-over
				imperium_Project_Methods.salestoOperation();
				commonUtils.selectByVisibleText(salestooperation.getExeCP5(),ev.exe5_SalestoOper);
				ab.getComments().sendKeys("Sales to operation");
				ab.getSubmitbutton().click();
				login.logout();

				//operations hand over
				imperium_Project_Methods.operationAcceptance();
				commonUtils.selectByVisibleText(ak.getExeOperationAcceptanceCP5(), ev.exeCP5_OperationAccep);
				ab.getAcceptOperationAcceptance().click();
				login.logout();
				//Meeting Notes
				if(ev.meetingwithSL.equals("Yes")||ev.meeting.equals("Yes")){
					g45.meetings();
				}
				//CP5 explicit decision
				if(ev.exe5_SalestoOper.equals("Yes")||(ev.exeCP5_OperationAccep.equals("Yes")||(ev.draftproduced.equals("No")))){
					basic.boardApproval();
				}
				//Project delivery plan(PDP)
				imperium_Project_Methods.pdp_();
				commonUtils.selectByVisibleText(pdp_ui.getExecp6(),ev.execp6);
				ab.getSubmitbutton().click();
				commonUtils.waitForPageToLoad();
				login.logout();
				//CP6 explicit decision
				if(ev.execp6.equals("Yes")){
					basic.boardApproval();
				}

				//driver.close();
				imperium_Project_Methods.deveryreview(ev.deliveryReview_dission_MonthlyReview);
				allPages.getReviewLink().click();
				flag=addtoWatchList();
				Assert.assertEquals(true, flag);
				removeMyWatchlist();
			}
			catch(Exception e){
				log.error(e);
			}
		}
	}
	@Test
	public void verifyReview_toMyWatchList() throws IOException, InterruptedException, AWTException{
		login.user();
		allPages.getViewReviews().sendKeys(Keys.RETURN);
		commonUtils.blindWait();
		try{
			if(driver.findElement(By.xpath("//input[contains(@title,'Add')]")).isDisplayed()){
				//log.info(driver.findElement(By.xpath("//input[contains(@title,'Add')]")).isDisplayed());
				flag=addtoWatchList();
				Assert.assertEquals(true, flag);
				removeMyWatchlist();
			}
		}
		catch(Exception e){
			login.url();
			commonUtils.waitForPageToLoad();
			login.user();
			imperium_Project_Methods.rtqForm(ev.estimatedSize250_,ev.location_SouthEast);
			imperium_Project_Methods.submit_logout_QRnumberAlert();
			//***********CP1 explicit approval decision************
			if((ev.estimatedSize.equals(ev.estimatedSize500_))||(ev.location.equals(ev.location_other))) {
				basic.boardApproval();
			}
			//Assign Sales Leader
			imperium_Project_Methods.ASL();
			
			//Prepare Quote
			imperium_Project_Methods.prepare_Quote(ev.overallSell,ev.locationrtq2);
			commonUtils.selectByVisibleText(prepare_Quoteui.getExpliciteapprovalatgateway2(),ev.exeCP2);
			prepare_Quoteui.getQuoteprepared().click();
			login.logout();

			allPages.getViewReviews().sendKeys(Keys.RETURN);
			commonUtils.blindWait();
			flag=addtoWatchList();
			Assert.assertEquals(true, flag);
			removeMyWatchlist();
		}
	}
	@Test
	public void verifyHomePage_to_WatchList() throws IOException, InterruptedException, AWTException {

		login.user();
		ab.getViewalltasks().click();
		commonUtils.waitForPageToLoad();
			flag=addtoWatchList();
			Assert.assertEquals(true, flag);
			removeMyWatchlist();
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
	}
	@Test
	//Add project to my watch list from Executive dash board
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
	}
	@Test
	//Add project to my watch list from Project documents
	public void verifyProjectDoc_to_MyWatchList() throws IOException, InterruptedException, AWTException{
		login.user();
		//allPages.getAdminLink().click();
		allPages.getProDocslink().click();

			flag=addtoWatchList();
			Assert.assertEquals(true, flag);
			removeMyWatchlist();
	}

	@Test
	//Add project to my watch list from MyOpportunities
	public void verifyMyOpportunities_to_MyWatchList() throws IOException, InterruptedException, AWTException{
		login.user();
		allPages.getMyOpplink().click();
			flag=addtoWatchList();
			Assert.assertEquals(true, flag);
			removeMyWatchlist();
	}
}