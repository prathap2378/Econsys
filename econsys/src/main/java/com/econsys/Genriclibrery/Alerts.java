package com.econsys.Genriclibrery;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.econsys.UIobjectrepositary.*;

public class Alerts extends Driver{
	RTQForm_Ui nrtq=(RTQForm_Ui) PageFactory.initElements(Driver.driver(), RTQForm_Ui.class);
	CommonUtils cu = new CommonUtils();
	Logger log = Logger.getLogger(Alerts.class.getName());
	boolean flag;
	//Duplicate file alert-A file with same name already exists in current context. Select different file
	@FindBy(xpath="//div/div[contains(text(),'A file with same name already exists in current context. Select different file')]")
	private WebElement file_DuplicateAlert;
	
	public WebElement getFile_DuplicateAlert() {
		return file_DuplicateAlert;
	}
	//Mandatory alert-Please fix the errors which are marked in red
	@FindBy(xpath="//div[contains(text(),'Please fix the errors which are marked in red')]")
	private WebElement fields_MandatoryAlert;
	
	public WebElement getFields_MandatoryAlert() {
		return fields_MandatoryAlert;
	}
	//Success message
	@FindBy(xpath="//div[@class='alert alert-success']")
	private WebElement success_Message;
	
	public WebElement getSuccess_Message() {
		return success_Message;
	}
		
	//Accept alerts
		@FindBy(xpath="//div/a[contains(text(),'Yes')]")
		private WebElement alert_Accept_Yes;

		//Accept alerts
				@FindBy(xpath="//div/a[contains(text(),'ok')]")
				private WebElement alert_Accept_Ok;
				
		/**
		 * @return the alert_Accept_Yes
		 */
		public WebElement getAlert_Accept_Yes() {
			return alert_Accept_Yes;
		}
		/**
		 * @return the alert_Accept_Ok
		 */
		public WebElement getAlert_Accept_Ok() {
			return alert_Accept_Ok;
		}
		

		
		
	public void isEmpty(){
		boolean projectname = nrtq.getProjectName().getAttribute("value").isEmpty();
	}
	public int upload_mdrProgDocument(){
		WebElement prog = driver.findElement(By.xpath("//div[@id='upload-mdrProgDocument']"));
		int upload_mdrProgDocument = prog.findElements(By.xpath(".//*")).size();
		return upload_mdrProgDocument;
	}
	public int upload_mdrMilestoneDocument(){
		WebElement mile = driver.findElement(By.xpath("//div[@id='upload-mdrMilestoneDocument']"));
		int upload_mdrMilestoneDocument = mile.findElements(By.xpath(".//*")).size();
		log.info(""+upload_mdrMilestoneDocument);
		return upload_mdrMilestoneDocument;
	}
	public int upload_mdrPanelProductionSchedule(){
		WebElement panel = driver.findElement(By.xpath("//div[@id='upload-mdrPanelProductionSchedule']"));
		int upload_mdrPanelProductionSchedule = panel.findElements(By.xpath(".//*")).size();
		log.info(""+upload_mdrPanelProductionSchedule);
		return upload_mdrPanelProductionSchedule;
	}
	public void deliveryReview(){
		System.out.println("delivery review");	
	}
	//verify success message
	public boolean successMessgae(){
		flag = driver.findElement(By.xpath("//div[@class='alert alert-success']")).isDisplayed();
		return flag;
	}
}
