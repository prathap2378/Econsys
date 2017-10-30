package com.econsys.Projects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.econsys.Genriclibrery.*;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.*;

public class ReviewInvolve extends Driver {
	static CommonUtils cu=PageFactory.initElements(Driver.driver(), CommonUtils.class);
	EngReviewInvoveUi eri=PageFactory.initElements(Driver.driver(), EngReviewInvoveUi.class);
	ActionButtonsUi ab=PageFactory.initElements(Driver.driver(),ActionButtonsUi.class);
	CommercialReviewInvolve cri=PageFactory.initElements(Driver.driver(), CommercialReviewInvolve.class);
	
	static Workbook wb=new Workbook();
	static Login login=new Login();
	static ReviewInvolve ri=new ReviewInvolve();
	static Basic b=new Basic();
	
  //@Test
  //*********Engg review********
	//*[@id="gate2"]/div/ul/li[1]/a
	public void reviewInvolvecommon() throws IOException, InterruptedException {
		  
	      cu.waitForPageToLoad();
		  ab.getReviewinvolvetasks().sendKeys(Keys.RETURN);
		  b.projectname_Reviews();
		  cu.waitForPageToLoad();

		  ab.getComments().sendKeys("Review Involves...");
		  ab.getApprove_Button().click();
		  login.logout();
	  }
	
  public void reviewEL() throws IOException, InterruptedException {
	  
      login.loginEL();
      cu.waitForPageToLoad();
	  ab.getReviewinvolvetasks().sendKeys(Keys.RETURN);
	  b.projectname_Reviews();
	  Thread.sleep(1000);

	  //select_Dropdowns();
	  ab.getComments().sendKeys("Engg review");
	  ab.getApprove_Button().click();
	  login.logout();
  }
  
  //************Engg involvement**********
  @Test
  public void involveEL() throws IOException, InterruptedException{
	  
	  login.loginEL();
	  Thread.sleep(1000);
	  ab.getReviewinvolvetasks().sendKeys(Keys.RETURN); 
	  b.projectname_Involves();
	  Thread.sleep(1000);
	  
	  //select_Dropdowns();
	  ab.getComments().sendKeys("Engg involve");
	  ab.getApprove_Button().click();
	  login.logout();
  }
  
  //**********Comm and Engineering select drop downs**************
  public static void selwect_Dropdowns(){
	  List<WebElement> SelectOptions=driver.findElements(By.xpath("//td[@class='rtqChekBoxlabel']/following-sibling::td/select"));
	  for(int i=0;i<SelectOptions.size();i++){
		  WebElement selectElement=SelectOptions.get(i);
		  cu.selectByVisibleText(selectElement, "Yes");
	  }
  }

  public static void select_No_inDropdowns(){
	  List<WebElement> SelectOptions=driver.findElements(By.xpath("//td[@class='rtqChekBoxlabel']/following-sibling::td/select"));
	  for(int i=0;i<SelectOptions.size();i++){
		  WebElement selectElement=SelectOptions.get(i);
		  cu.selectByVisibleText(selectElement, "No");
	  }
  }
  
  public void reviewCL() throws IOException, InterruptedException{
	  login.loginCL();
	  Thread.sleep(1000);
	  ab.getReviewinvolvetasks().sendKeys(Keys.RETURN); 
	  b.projectname_Reviews();
	  Thread.sleep(1000);
	  //select_Dropdowns();
	  ab.getComments().sendKeys("Comm review");
	  ab.getApprove_Button().click();
	  
	  login.logout();
  }
  
  //*************Comm involve**********
  public void involveCL() throws IOException, InterruptedException{
	  
	  login.loginCL();
	  Thread.sleep(1000);
	  ab.getReviewinvolvetasks().sendKeys(Keys.RETURN);	 
	  b.projectname_Involves();
	  Thread.sleep(1000);
	  
	  //select_Dropdowns();
	  ab.getComments().sendKeys("Comm involve");
	  ab.getApprove_Button().click();
	  
	  login.logout();
  }
  
  public void involveCLcp2cp3() throws IOException, InterruptedException{
	 
	  login.loginCL();
	  Thread.sleep(1000);
	  ab.getReviewinvolvetasks().sendKeys(Keys.RETURN);	 
	  b.projectname_Involves();
	  Thread.sleep(1000);
	  //select_Dropdowns();
	  ab.getComments().sendKeys("Comm involve CP2-CP3");
	  ab.getApprove_Button().click();
	  
	  login.logout();
  }
}
