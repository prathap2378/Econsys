package com.econsys.UIobjectrepositary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppointkeystaffandCommerSuitUi {

	@FindBy(xpath="//select[@id='st_engineeringLead']")
	private WebElement leadEL;

	@FindBy(xpath="//select[@id='st_commercialLead']")
	private WebElement leadCL;
	
	@FindBy(xpath="//select[@id='st_projectLead']")
	private WebElement leadPL;
	
	@FindBy(xpath="//select[@id='st_applicationOrInvoicePDP']")
	private WebElement draftCommercialSuitProduced;
	
	@FindBy(xpath="//input[@id='fileList_flm_draftCommercialSuitProduced']")
	private WebElement cummercialSuit_doc;
	
	public WebElement getCummercialSuit_doc() {
		return cummercialSuit_doc;
	}
	
	@FindBy(xpath="//input[@id='in_paymentTermsDaysPDP']")
	private WebElement payment_Terms;

	@FindBy(xpath="//input[@id='st_daysFromPDP']")
	private WebElement days_From;
	
	@FindBy(xpath="//input[@id='fileList_flm_paymentCycleDocument']")
	private WebElement payment_Cycle_Document;
	
	@FindBy(id="fileList_flm_milestoneDocument")
	private WebElement milestoneDocInCommercialSuite;
	
	@FindBy(id="fileList_flm_panelProductionScheduleDocument")
	private WebElement productionSheduleDocCommercialSuite;
	
	@FindBy(xpath="//*[@id='st_projectOrSmall']")
	private WebElement projectOrSmallWorks;
	
	public WebElement getprojectorSmallWorks(){
		return projectOrSmallWorks;
	}
	
	public WebElement getproductionSheduleDocCommercialSuite(){
		return productionSheduleDocCommercialSuite;
	}
	
	public WebElement getMilestoneDocInCommercialSuite(){
		return milestoneDocInCommercialSuite;
	}
	public WebElement getPayment_Terms() {
		return payment_Terms;
	}

	public WebElement getDays_From() {
		return days_From;
	}

	public WebElement getPayment_Cycle_Document() {
		return payment_Cycle_Document;
	}	

	@FindBy(xpath="//select[@id='scheduleMeetingWithSales']")
	private WebElement meetingwithsales;
	
	@FindBy(xpath="//select[@id='explicit_approval_operation']")
	private WebElement exeOperationAcceptanceCP5;
	
	@FindBy(xpath="//input[@id='jobId']")
	private WebElement jobid;
	
	@FindBy(xpath="//select[@id='is_job_number_entered']")
	private WebElement isJobNumberEntered;
	
	/**
	 * @return the isJobNumberEntered
	 */
	public WebElement getIsJobNumberEntered() {
		return isJobNumberEntered;
	}

	public WebElement getJobid() {
		return jobid;
	}

	public WebElement getExeOperationAcceptanceCP5() {
		return exeOperationAcceptanceCP5;
	}

	public WebElement getMeetingwithsales() {
		return meetingwithsales;
	}

	public WebElement getDraftCommercialSuitProduced() {
		return draftCommercialSuitProduced;
	}

	public WebElement getLeadEL() {
		return leadEL;
	}

	public WebElement getLeadCL() {
		return leadCL;
	}

	public WebElement getLeadPL() {
		return leadPL;
	}
}
