package com.econsys.UIobjectrepositary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RTQForm_Ui {

	@FindBy(linkText="New RTQ")
	private WebElement RTQTab;

	@FindBy(id="st_ProjectName")
	private WebElement ProjectName;
	//public WebElement st_ProjectName;

	@FindBy(id="st_Customer")
	private WebElement CustomerName;

	@FindBy(id="st_Consultant")
	private WebElement ConsultantName;

	@FindBy(id="st_ProjectAddress")
	private WebElement ProjectAddres;

	//new Fields added in rtq matrixsee
	@FindBy(id="st_newOrExisting")
	private WebElement neworExis;

	@FindBy(id="st_quotationType")
	private WebElement quotationType;

	@FindBy(id="st_ProjectDesc")
	private WebElement projectDesc;

	@FindBy(id="st_leadSource")
	private WebElement leadSource;

	@FindBy(id="dt_anticipatedDate")
	private WebElement anticipatedDate;

	@FindBy(id="float_EstimatedSize")
	private WebElement EstimatedSize;

	@FindBy(id="in_EstimatedPoints")
	private WebElement Points;

	@FindBy(id="st_ProductSpecified")
	private WebElement ProductSpecifieddd;

	@FindBy(id="st_TypeofBuilding")
	private WebElement TypeofBuildingdd;

	@FindBy(id="st_EndUserIndustrySector")
	private WebElement EndUserIndustrySectordd;

	@FindBy(id="st_TypeofProject")
	private WebElement TypeofProjectdd;

	@FindBy(id="st_EnquiryFormat")
	private WebElement EnquiryFormatdd;

	@FindBy(id="st_DocumentationReceived")
	private WebElement DocumentationReceiveddd;

	@FindBy(id="st_GANamed")
	private WebElement GANameddd;

	@FindBy(id="st_Location")
	private WebElement Locationdd;

	@FindBy(id="st_PreviousExperienceAtSite")
	private WebElement PreviousExperienceAtSitedd;

	@FindBy(id="st_TermsConditionsAdvised")
	private WebElement TermsConditionsAdviseddd;

	@FindBy(id="st_PCG")
	private WebElement PCGdd;

	@FindBy(id="st_PerformanceBond")
	private WebElement PerformanceBond;

	@FindBy(id="st_Comments")
	private WebElement Comments;

	@FindBy(id="save")
	private WebElement save;

	@FindBy(id="reset")
	private WebElement Reset;

	@FindBy(id="btnsubmit")
	private WebElement Submit;

	@FindBy(id="cancel")
	private WebElement cancel;


	@FindBy(xpath="//input[@id='new_Link']")
	private WebElement new_Link;

	@FindBy(xpath="//input[@id='link_project']")
	private WebElement link_Projectpagebutton;

	@FindBy(xpath="//select[@id='projectsdropDown']")
	private WebElement select_Project;

	@FindBy(xpath="//div/button[contains(text(),'Link Project')]")
	private WebElement link_Projectpopupbutton;

	@FindBy(xpath="//div/button[contains(text(),'Cancel')]")
	private WebElement linkProject_Cancel;


	@FindBy(xpath="//input[@id='unLink_project']")
	private WebElement unLink_Project;

	@FindBy(xpath="//div/a[contains(text(),'Cancel')]")
	private WebElement unLink_ProjectCancel;

	@FindBy(xpath="//label[contains(text(),'Linked Opportunities')]")
	private WebElement linkedOppotunities;

	@FindBy(xpath="//td[@aria-describedby='likedProjectDetails_projectName']")
	private WebElement linkedprojectName;

	@FindBy(xpath="//img[@id='manageAccordion'][@alt='Show-More']")
	private WebElement showMore;
	
	@FindBy(xpath="//img[@id='manageAccordion'][@alt='Show-Less']")
	private WebElement showLess;
	
	@FindBy(xpath="//img[contains(@id,'manageAccordion')]")
	private WebElement manageAccprdion;//Show More or Show Less button 

	@FindBy(linkText="RTQ")
	private WebElement newform;

	@FindBy(linkText="RTQ")
	private WebElement rtqLink;

	@FindBy(linkText="Project")
	private WebElement projectLink;

	public WebElement getNewform() {
		return newform;
	}
	@FindBy(linkText="Small Works")
	private WebElement smallWorks;

	public WebElement getSmallWorks() {
		return smallWorks;
	}
	@FindBy(xpath="//select[@id='st_smallWorkType']")
	private WebElement smallWorksType;

	public WebElement getSmallWorksType() {
		return smallWorksType;
	}

	@FindBy(xpath="//input[@id='st_location']")
	private WebElement locationUkas;



	@FindBy(xpath="//label[@id='SRTQ']")
	private WebElement saved_RTQ_Link;

	public WebElement getSaved_RTQ_Link() {
		return saved_RTQ_Link;
	}


	@FindBy(id="st_enquiryOpenToAll")
	private WebElement isenqueryopentoall;

	@FindBy(xpath="//div[@id='rtqForm-dropzone']")
	private WebElement uploaddocument;


	@FindBy(xpath="//select[@id='st_damagesAdviced']")
	private WebElement damages_Advised;

	@FindBy(xpath="//select[@id='processType']")
	private WebElement pd_Type;

	// @return the pd_Type
	 
	public WebElement getPd_Type() {
		return pd_Type;
	}

	public WebElement getDamages_Advised() {
		return damages_Advised;
	}

	public WebElement getRetention_Advised() {
		return retention_Advised;
	}

	@FindBy(xpath="//select[@id='st_retentionAdvised']")
	private WebElement retention_Advised;

	//Matrixsee new RTQ at Prepare Quote
	@FindBy(id="float_EstimatedSize")
	private WebElement eSizertq2;

	@FindBy(id="st_Location")
	private WebElement locationrtq2;

	//ukas rtq fields
	@FindBy(xpath="//select[@id='st_productType']")
	private WebElement product_Type;

	@FindBy(xpath="//select[@id='st_projectCategory']")
	private WebElement ctegory_of_Project;

	@FindBy(xpath="//select[@id='st_typeOfBuilding']")
	private WebElement type_Of_Building;

	@FindBy(xpath="//select[@id='st_hasQuoteSubmitted']")
	private WebElement has_Quote_Submitted;

	@FindBy(xpath="//select[@id='st_contractValueType']")
	private WebElement contract_Value_Type;

	public WebElement getProduct_Type() {
		return product_Type;
	}

	@FindBy(xpath=("//div[div[contains(text(),'Quote Reference Number for the requested Quote is')]]//a[contains(text(),ok)]"))
	private WebElement qouteRefrenceNumberAlert_Imperium;
	
	public WebElement getQouteRefrenceNumberAlert_Imperium(){
		return qouteRefrenceNumberAlert_Imperium;
	}
	public WebElement getCtegory_of_Project() {
		return ctegory_of_Project;
	}

	public WebElement getType_Of_Building() {
		return type_Of_Building;
	}

	public WebElement getHas_Quote_Submitted() {
		return has_Quote_Submitted;
	}

	public WebElement getContract_Value_Type() {
		return contract_Value_Type;
	}

	public WebElement getRtqLink() {
		return rtqLink;
	}

	public WebElement getProjectLink() {
		return projectLink;
	}

	public WebElement getLocationUkas() {
		return locationUkas;
	}

	public WebElement getIsenqueryopentoall() {
		return isenqueryopentoall;
	}

	public WebElement getUploaddocument() {
		return uploaddocument;
	}

	public WebElement getRTQTab() {
		return RTQTab;
	}

	//@return the new_Link
	public WebElement getNew_Link() {
		return new_Link;
	}
	//@return the link_Projectpagebutton
	public WebElement getLink_Projectpagebutton() {
		return link_Projectpagebutton;
	}

	//@return the select_Project
	public WebElement getSelect_Project() {
		return select_Project;
	}

	//@return the link_Projectpopupbutton
	public WebElement getLink_Projectpopupbutton() {
		return link_Projectpopupbutton;
	}

	//@return the linkProject_Cancel
	public WebElement getLinkProject_Cancel() {
		return linkProject_Cancel;
	}

	//@return the unLink_Project
	public WebElement getUnLink_Project() {
		return unLink_Project;
	}

	//@return the unLink_ProjectCancel
	public WebElement getUnLink_ProjectCancel() {
		return unLink_ProjectCancel;
	}

	public WebElement getProjectName() {
		return ProjectName;
	}

	public WebElement getCustomerName() {
		return CustomerName;
	}

	public WebElement getConsultantName() {
		return ConsultantName;
	}

	public WebElement getProjectAddres() {
		return ProjectAddres;
	}

	public WebElement getEstimatedSize() {
		return EstimatedSize;
	}

	public WebElement getPoints() {
		return Points;
	}

	public WebElement getProductSpecifieddd() {
		return ProductSpecifieddd;
	}

	public WebElement getTypeofBuildingdd() {
		return TypeofBuildingdd;
	}

	public WebElement getEndUserIndustrySectordd() {
		return EndUserIndustrySectordd;
	}

	public WebElement getTypeofProjectdd() {
		return TypeofProjectdd;
	}

	public WebElement getEnquiryFormatdd() {
		return EnquiryFormatdd;
	}

	public WebElement getDocumentationReceiveddd() {
		return DocumentationReceiveddd;
	}

	public WebElement getGANameddd() {
		return GANameddd;
	}

	public WebElement getLocationdd() {
		return Locationdd;
	}

	public WebElement getPreviousExperienceAtSitedd() {
		return PreviousExperienceAtSitedd;
	}

	public WebElement getTermsConditionsAdviseddd() {
		return TermsConditionsAdviseddd;
	}

	public WebElement getPCGdd() {
		return PCGdd;
	}

	public WebElement getPerformanceBond() {
		return PerformanceBond;
	}

	public WebElement getComments() {
		return Comments;
	}

	public WebElement getSave() {
		return save;
	}

	public WebElement getReset() {
		return Reset;
	}

	public WebElement getSubmit() {
		return Submit;
	}

	public WebElement getCancel() {
		return cancel;
	}

	public WebElement getLinkedOppotunities() {
		return linkedOppotunities;
	}

	public WebElement getLinkedprojectName() {
		return linkedprojectName;
	}

	public WebElement getManageAccprdion() {
		return manageAccprdion;
	}

	@FindBy(xpath="//*[@id='st_ats_quoterRefNumber']")
	private WebElement quoterRefNumber;

	//@return the quoterRefNumber
	public WebElement getQuoterRefNumber() {
		return quoterRefNumber;
	}

	//new Fields added in rtq matrixsee
	//@return the neworExis
	public WebElement getNeworExis() {
		return neworExis;
	}

	//@return the quotationType
	public WebElement getQuotationType() {
		return quotationType;
	}

	//@return the projectDesc
	public WebElement getProjectDesc() {
		return projectDesc;
	}

	//@return the leadSource
	public WebElement getLeadSource() {
		return leadSource;
	}

	//@return the anticipatedDate
	public WebElement getAnticipatedDate() {
		return anticipatedDate;
	}

	//@return the eSizertq2
	public WebElement geteSizertq2() {
		return eSizertq2;
	}

	//@return the locationrtq2
	public WebElement getLocationrtq2() {
		return locationrtq2;
	}
	
	// @return the showMore
	public WebElement getShowMore() {
		return showMore;
	}

	//@return the showLess
	public WebElement getShowLess() {
		return showLess;
	}
}