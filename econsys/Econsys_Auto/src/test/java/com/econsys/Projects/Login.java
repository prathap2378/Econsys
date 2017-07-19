package com.econsys.Projects;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.econsys.UIobjectrepositary.*;
import com.econsys.Genriclibrery.*;
import com.econsys.TestData.*;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * Login class provides the login credentials for all roles
 * validation of user authentication test cases
 * @author bhanu.pk
 */
public class Login extends Driver {
	
	private static Logger log = Logger.getLogger(Login.class.getName());
	Workbook wb=new Workbook();
	LoginPageui loginUi=PageFactory.initElements(Driver.driver(), LoginPageui.class);
	CommonUtils cu=PageFactory.initElements(Driver.driver(), CommonUtils.class);
	Driver d = new Driver();
	String sd, psw, bor, cl, el, sl, pl, od, sa=null;
	String userName;
	String password;
	
	{
		try {
			password = wb.getXLData(1, 1, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
  
public void user()throws IOException, InterruptedException {
	  //(String un,String pwd)
	  cu.waitForPageToLoad();
	  userName = wb.getXLData(1, 0,0);
	  loginUi.getLoginName().sendKeys(userName);
	  //log.info("login as user:"+un+"");
	  loginUi.getPasword().sendKeys(password);
	  loginUi.getLoginButton().click();
}

public void url() throws IOException {
	  //String url
	  String workingURL = wb.getXLData(1,3,0);
	  driver().get(workingURL);
	  cu.maximize();
	  //driver.navigate().refresh();
}
//Logout method
public void logout() throws InterruptedException{
	 Thread.sleep(200);
	 /*if(loginUi.getLogout().isDisplayed()){
		//OLD theme logout link
		loginUi.getLogout().click();
	 }*/
	 //New theme logout link
	 Actions action=new Actions(Driver.driver());
	 action.moveToElement( d.driver().findElement(By.xpath("//span[@class='nav-item-label']/span"))).build().perform();
	 Thread.sleep(200);
	 //driver.findElement(By.xpath("//span[@class='nav-item-label']/span")).click();
	 driver.findElement(By.xpath("//span[contains(text(),'Sign Out')]")).click();
}
//Login super admin
public void loginsuperadminTestServer() throws IOException, InterruptedException{
	 cu.waitForPageToLoad();
	 sa=wb.getXLData(28, 0, 0);
	 psw=wb.getXLData(28,1, 0);
	 loginUi.getLoginName().sendKeys(sa);
	 loginUi.getPasword().sendKeys(psw);
	 loginUi.getLoginButton().click();
}
 //CL login details
 public void loginCL() throws IOException, InterruptedException{
	// Login login=new Login();
	 cu.waitForPageToLoad();
	 cl=wb.getXLData(16, 0, 0);
	 psw=wb.getXLData(16,1, 0);
	 loginUi.getLoginName().sendKeys(cl);
	 loginUi.getPasword().sendKeys(psw);
	 loginUi.getLoginButton().click();

}
//EL login details
public void loginEL() throws IOException, InterruptedException{
	 cu.waitForPageToLoad();
	 el=wb.getXLData(13, 0, 0);
	 psw=wb.getXLData(13,1, 0);
	 loginUi.getLoginName().sendKeys(el);
	 loginUi.getPasword().sendKeys(psw);
	 loginUi.getLoginButton().click();
}
//Board login details
public void loginboard() throws IOException, InterruptedException{
	 cu.waitForPageToLoad();
	 bor=wb.getXLData(4, 0, 0);
	 psw=wb.getXLData(4,1, 0);
	 loginUi.getLoginName().sendKeys(bor);
	 loginUi.getPasword().sendKeys(psw);
	 loginUi.getLoginButton().click();
}
//SL login details
public void loginSL() throws IOException, InterruptedException{
	 cu.waitForPageToLoad();
	 sl=wb.getXLData(10, 0, 0);
	 psw=wb.getXLData(10,1, 0);
	 loginUi.getLoginName().sendKeys(sl);
	 loginUi.getPasword().sendKeys(psw);
	 loginUi.getLoginButton().click();
}
//SD login details
public void loginSD() throws IOException, InterruptedException{
	 cu.waitForPageToLoad();
	 sd=wb.getXLData(7, 0, 0);
	 psw=wb.getXLData(7, 1, 0);
	 loginUi.getLoginName().sendKeys(sd);
	 loginUi.getPasword().sendKeys(psw);
	 loginUi.getLoginButton().click();
}
//PL login details
public void loginPL() throws IOException, InterruptedException{
	 cu.waitForPageToLoad();
	 pl=wb.getXLData(22,0, 0);
	 psw=wb.getXLData(22,1, 0);
	 loginUi.getLoginName().sendKeys(pl);
	 loginUi.getPasword().sendKeys(psw);
	 loginUi.getLoginButton().click();
}
public void loginOD() throws IOException, InterruptedException{
	 //Login login=new Login();
	 cu.waitForPageToLoad();
	 od=wb.getXLData(19,0, 0);
	 psw=wb.getXLData(19,1, 0);
	 loginUi.getLoginName().sendKeys(od);
	 loginUi.getPasword().sendKeys(psw);
	 loginUi.getLoginButton().click();
}

public void loginAdmin(){
	loginUi.getLoginName().sendKeys("admin@quadwave.com");
	loginUi.getPasword().sendKeys("test1");
	loginUi.getLoginButton().click();
}
}