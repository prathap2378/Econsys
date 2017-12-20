package POC.econsys;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import com.econsys.Genriclibrery.CommonUtils;
import com.econsys.Genriclibrery.Driver;
import com.econsys.Projects.Login;
import com.econsys.TestData.Workbook;
import com.econsys.UIobjectrepositary.AllPages;
//import com.econsys.Genriclibrery.Driver;

public class testAuto1{
	private static Logger log=Logger.getLogger(testAuto1.class.getName());
	CommonUtils cu = new CommonUtils();
	Login login = new Login();
	AllPages allPages= PageFactory.initElements(Driver.driver(), AllPages.class);
	Workbook wb = new Workbook();
	public static void main(String[] args) {
		
	try{
		int i = 50;
		int a[] = {1,23,32,23,23,23};  
	    a[10]=30;  
		System.out.println(i);
	}
	finally{
		System.out.println("Hi after error12");
		System.out.println("Hi finalyas");
	}
	}
//@Test()
public void test1()throws Exception{
	
	/*boolean flag=false;
	flag=Driver.driver().findElement(By.xpath("//span[text()=' RTQ']")).isDisplayed();
	Assert.assertEquals(flag, true);
	log.info("Test script executed successfully");*/
	/*login.url();
	login.user();
	cu.waitForPageToLoad();
	
	allPages.getAdminLink().click();
	cu.blindWait();
	allPages.getPromptsAlerts().click();
	
	//cu.selectByIndex(driver.findElement(By.id("stage")), 1);
	cu.waitForPageToLoad();
	int s = driver.findElements(By.xpath("//select[@id='tasks']/option")).size();
	log.info("s count of options---"+s);
	for(int j=0;j<s;j++){
		
		int i = j+1;
		String f = driver.findElement(By.xpath("//select[@id='tasks']/option["+i+"]")).getText();
		System.out.println(f);
		wb.setExcelData(4, s, 0, f);
	}*/
}
}
