package com.econsys.testCases;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.econsys.Genriclibrery.*;
import com.econsys.Projects.Login;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.LoginPageui;

public class LoginVerification extends Driver{
	private static Logger log=Logger.getLogger(LoginVerification.class.getName());
	LoginPageui loginUi=PageFactory.initElements(driver(), LoginPageui.class);

	Login login=new Login();
	Workbook wb = new Workbook();
	CommonUtils cu= new CommonUtils();

	//UN authorized Login with user name
	@Test
	public void unAthorized_Login_userName() throws IOException, InterruptedException{

		String od=wb.getXLData(19,0, 0);
		loginUi.getLoginName().sendKeys(od);
		loginUi.getPasword().sendKeys("abc");
		loginUi.getLoginButton().click();
		Thread.sleep(200);
		boolean username_isPresent = loginUi.getLoginName().isDisplayed();
		log.info(username_isPresent);
	}

	//UN authorized Login with user password
	@Test(priority=1)
	public void unAthorized_Login_userPassword() throws InterruptedException{

		cu.waitForPageToLoad();
		loginUi.getLoginName().sendKeys("monorail@gmail.com");
		loginUi.getPasword().sendKeys("test1");
		loginUi.getLoginButton().click();
		Thread.sleep(200);
		boolean username_isPresent = loginUi.getLoginName().isDisplayed();
		log.info(username_isPresent);

	}
	//UN authorized Login with user name and password
	@Test(priority=2)
	public void unAthorized_Login_userNameANDpassword() throws IOException, InterruptedException{

		loginUi.getLoginName().sendKeys("test");
		loginUi.getPasword().sendKeys("test");
		loginUi.getLoginButton().click();
		cu.waitForPageToLoad();
		boolean username_isPresent = loginUi.getLoginName().isDisplayed();
		log.info(username_isPresent);
	}
}
