package com.econsys.UIobjectrepositary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Salestooperation {
	
	//Specification No Radio button
		@FindBy(xpath="//input[@id='img_cmt_1411848833402']")
		private WebElement Specification;
		
		//DrawingsSchedules No Radio button
		@FindBy(xpath="//input[@id='img_cmt_1411848833403']")
		private WebElement DrawingsSchedules;
		
		//TQRFIs No Radio button
		@FindBy(xpath="//input[@id='img_cmt_1411848833404']")
		private WebElement TQRFIs;
		
		//InstallEnquiryrequesttoquoteinformation No radio button
		@FindBy(xpath="//input[@id='img_cmt_1411848833405']")
		private WebElement InstallEnquiryrequesttoquoteinformation;
		
		//InstallSubContractorQuotation No radio button
		@FindBy(xpath="//input[@id='img_cmt_1411848833406']")
		private WebElement InstallSubContractorQuotation;
		
		//PanelEnquiryrequesttoquoteinformation No Radio Button
		@FindBy(xpath="//input[@id='img_cmt_1411848833402']")
		private WebElement PanelEnquiryrequesttoquoteinformation;
		
		//PanelSubContractorQuotation No Radio button
		@FindBy(xpath="//input[@id='img_cmt_1411848833402']")
		private WebElement PanelSubContractorQuotation;
		
		//MCCSchedules No radio button
		@FindBy(xpath="//input[@id='img_cmt_1411848833402']")
		private WebElement MCCSchedules;
		
		//OtherSCPackageQuotations No radio button
		@FindBy(xpath="//input[@id='img_cmt_1411848833402']")
		private WebElement OtherSCPackageQuotations;
		
		//Other3rdPartyQuotations Radio button
		@FindBy(xpath="//input[@id='img_cmt_1411848833402']")
		private WebElement Other3rdPartyQuotations;
		
		//Equipmentshedules Radio Button No
		@FindBy(xpath="//input[@id='img_cmt_1411848833402']")
		private WebElement EquipmentSchedules;
		
		//PointShedules No radio button
		@FindBy(xpath="//input[@id='img_cmt_1411848833402']")
		private WebElement PointsSchedules;
		
		//ValveSchedules No radio button
		@FindBy(xpath="//input[@id='img_cmt_1411848833402']")
		private WebElement ValveSchedules ;
		
		//Identifed Risks and Opportunities (INCL. VALUE ENGINEERING)No radio button
		@FindBy(xpath="//input[@id='img_cmt_1411848833402']")
		private WebElement IdentifedRisksandOpportunities;

		//Schedulemeetingwithoperations No radio button
		@FindBy(xpath="//select[@id='isMeetingRequiredBtwnPLSL']")
		private WebElement meeting;
		
		@FindBy(xpath="//select[@id='st_cCChangeTaskOwner']")
		private WebElement delegateTaskto_PL;
		
		/**
		 * @return the delegateTaskto_PL
		 */
		public WebElement getDelegateTaskto_PL() {
			return delegateTaskto_PL;
		}

		public WebElement getMeeting() {
			return meeting;
		}

		@FindBy(xpath="//select[@id='explicit_approval_sales']")
		private WebElement exeCP5;
		
		//Specification Yes radio button
		@FindBy(xpath="//input[@id='salesToOpItemRadioYes_1411848833402']")
		private WebElement Specification1;
		
		@FindBy(xpath="//textarea[@id='st_Comments']")
		private WebElement Comments;

		public WebElement getSpecification() {
			return Specification;
		}

		public WebElement getDrawingsSchedules() {
			return DrawingsSchedules;
		}

		public WebElement getTQRFIs() {
			return TQRFIs;
		}

		public WebElement getInstallEnquiryrequesttoquoteinformation() {
			return InstallEnquiryrequesttoquoteinformation;
		}

		public WebElement getInstallSubContractorQuotation() {
			return InstallSubContractorQuotation;
		}

		public WebElement getPanelEnquiryrequesttoquoteinformation() {
			return PanelEnquiryrequesttoquoteinformation;
		}

		public WebElement getPanelSubContractorQuotation() {
			return PanelSubContractorQuotation;
		}

		public WebElement getMCCSchedules() {
			return MCCSchedules;
		}

		public WebElement getOtherSCPackageQuotations() {
			return OtherSCPackageQuotations;
		}

		public WebElement getOther3rdPartyQuotations() {
			return Other3rdPartyQuotations;
		}

		public WebElement getEquipmentSchedules() {
			return EquipmentSchedules;
		}

		public WebElement getPointsSchedules() {
			return PointsSchedules;
		}

		public WebElement getValveSchedules() {
			return ValveSchedules;
		}

		public WebElement getIdentifedRisksandOpportunities() {
			return IdentifedRisksandOpportunities;
		}

		public WebElement getExeCP5() {
			return exeCP5;
		}

		public WebElement getSpecification1() {
			return Specification1;
		}

		public WebElement getComments() {
			return Comments;
		}

	}



