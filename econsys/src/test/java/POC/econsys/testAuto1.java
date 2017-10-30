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
		String upper = "make me feel happy";
		String Upper= upper.substring(0,1).toUpperCase()+upper.substring(1);
		
		System.out.println("Upper ------"+Upper);
	String a = null;
	
	byte c = 121;
	short d =3411;
	int b =  2147483623;
	long e = 1231231232123123123L;
	boolean f = false;
	char g = 'q';
	float ii = 151231231212312123123121312312312311122.02f;
	double jj = 123456723234234234234131231234234234238910.89;
	int y = (int)ii;
	
	System.out.println("floating number "+ii);
	System.out.println("Integer number--"+y);
	int n =5,k = 2*n;
	int i = (10==10)?78:79;
	System.out.println("ternary ope---"+i);
	for(i = 0; i <= 4;i++){
	
		for(int h=0;h<k;h++){
		System.out.print(" ");
	
		}
		k=k-1;
		for(int j = 0; j < i;j++){
			System.out.print(" *");
		}
		System.out.println(" ");
	}
	for(i = 0; i <= 4;i++){
		System.out.println("k is---"+k);
		
		System.out.println(" ");
		for(int j = 4; j > i;j--){
		System.out.print(" *");
		}
		for(int h=0;h<k;h++){
			System.out.print(" ");
			}
			k=k-1;
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
