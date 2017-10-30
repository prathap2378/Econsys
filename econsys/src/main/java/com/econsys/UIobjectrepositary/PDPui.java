package com.econsys.UIobjectrepositary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PDPui {
@FindBy(xpath="//input[@id='flm_att_0multifile_hidden_planDocument']")
private WebElement plandoc;

@FindBy(xpath="//tr/td[6]/a[@id='delete_1']")
private WebElement delete1;

@FindBy(xpath="//tr/td[6]/a[@id='delete_2']")
private WebElement delete2;

@FindBy(xpath="//tr/td[6]/a[@id='delete_3']")
private WebElement delete3;

@FindBy(xpath="//td[@id='prodOfAppValueForecastGridPager_left']/table/tbody/tr/td/div")
private WebElement Addnewvalueforcast;

@FindBy(xpath="//input[@id='invoiceNumber']")
private WebElement invoiceNumber;

@FindBy(xpath="//input[@id='variation']")
private WebElement variation;

@FindBy(xpath="//input[@id='claim']")
private WebElement claim;

@FindBy(xpath="//textarea[@id='comments']")
private WebElement commentsMilestone;
public WebElement getInvoiceNumber() {
	return invoiceNumber;
}

public WebElement getVariation() {
	return variation;
}

public WebElement getClaim() {
	return claim;
}

public WebElement getCommentsMilestone() {
	return commentsMilestone;
}

@FindBy(xpath="//input[@id='mileStone']")
private WebElement milestone;

@FindBy(xpath="//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[5]/a")
private WebElement date;

@FindBy(xpath="//input[@id='contractWorks']")
private WebElement contractWorks;

public WebElement getContractWorks() {
	return contractWorks;
}

@FindBy(xpath="//select[@id='appOrInvoice']")
private WebElement apporInvoice;

public WebElement getApporInvoice() {
	return apporInvoice;
}

@FindBy(xpath="//a[@id='sData']")
private WebElement savemilestone;

@FindBy(xpath="//div[@id='gbox_subContractorProcPlanGrid']//a[@id='projectplanedit_1']")
private WebElement edit1;

@FindBy(xpath="//div[@id='gbox_subContractorProcPlanGrid']//a[@id='projectplanedit_2']")
private WebElement edit2;

@FindBy(xpath="//div[@id='gbox_subContractorProcPlanGrid']//a[@id='delete_1']")
private WebElement delete1subContractorProcPlanGrid;

@FindBy(xpath="//div[@id='gbox_subContractorProcPlanGrid']//a[@id='delete_2']")
private WebElement delete2subContractorProcPlanGrid;

@FindBy(xpath="//input[@id='fileList_flm_panelProductionScheduleDocument']")
private WebElement productionscheduledoc ;

@FindBy(xpath="//select[@id='explicit_approval_g6']")
private WebElement execp6;
//input[@value="Submit"]

//instruction grid ui objects
@FindBy(xpath="//td[@id='InstructionsGridPager_left']/table/tbody/tr/td/div[contains(text(),'Add New')]")
private WebElement addNew_InstructionGrid;

public WebElement getAddNew_InstructionGrid(){
	return addNew_InstructionGrid;
}

@FindBy(xpath="//a[starts-with(@class,'InstructionsGrid_rowedit')]")
private WebElement edit_InstructionsGrid;

public WebElement getEdit_InstructionsGrid(){
  return edit_InstructionsGrid;
}

@FindBy(xpath="//a[@id='sData']")
private WebElement saveButton_PDPpopup;

 public WebElement getSaveButton_PDPpopup(){
	 return saveButton_PDPpopup;
 }
 
@FindBy(xpath="//a[@id='cData']")
private WebElement cancelButton_PDPpopup;

public WebElement getCancelButton_PDPpopup(){
	return cancelButton_PDPpopup;
}
@FindBy(xpath="//input[@id='description']")
private WebElement description;

public WebElement getDescription(){
	return description;
}

@FindBy(xpath="//textarea[@id='comments']")
private WebElement comments_PDPpopup;

public WebElement getComments_PDPpopup(){
	return comments_PDPpopup;
}

@FindBy(xpath="//select[@id='editFormReportIntverval']")
private WebElement ReportIntverval_PDP;

@FindBy(xpath="//a[starts-with(@id,'img_cmt_InstructionsGriddoc')]")
private WebElement doc_instructions;

public WebElement getDoc_instructions(){
	return doc_instructions;
}
@FindBy(xpath="//div[@id='instructions_div']//tr/td[3]/input[@value='Link to File']")
private WebElement linktoFile_instructions;

public WebElement getLinktoFile_instructions(){
	return linktoFile_instructions;
}

//Day work sheet
@FindBy(xpath="//td[@id='DayWorkSheetsGridPager_left']/table/tbody/tr/td/div[contains(text(),'Add New')]")
private WebElement addnew_DayWorkSheetsGrid;
@FindBy(xpath="//a[starts-with(@class,'DayWorkSheetsGrid_rowedit')]")
private WebElement edit_DayWorkSheetsGrid;
@FindBy(xpath="//a[starts-with(@id,'img_cmt_DayWorkSheetsGriddoc')]")
private WebElement doc_DayWorkSheetsGrid;
@FindBy(xpath="//div[@id='daywork_sheets_div']//tr/td[3]/input[@value='Link to File']")
private WebElement linktoFile_daywork_sheets;

//Commercial /importent email
@FindBy(xpath="//td[@id='CommercialCorrespondenceGridPager_left']/table/tbody/tr/td/div[contains(text(),'Add New')]")
private WebElement addnew_CommercialCorrespondenceGrid;
@FindBy(xpath="//a[starts-with(@id,'img_cmt_CommercialCorrespondenceGriddoc')]")
private WebElement doc_CommercialCorrespondenceGrid;
@FindBy(xpath="//a[starts-with(@class,'CommercialCorrespondenceGrid_rowedit')]")
private WebElement edit_CommercialCorrespondenceGrid;
@FindBy(xpath="//div[@id='commercial_correspondence_ie_div']//tr/td[3]/input[@value='Link to File']")
private WebElement linktoFile_CommercialCorrespondenceGrid;

//NCR
@FindBy(xpath="//td[@id='NonComplianceReportsGridPager_left']/table/tbody/tr/td/div[contains(text(),'Add New')]")
private WebElement addnew_NonComplianceReportsGrid;
@FindBy(xpath="//select[@id='ncrStatus']")
private WebElement status_NonComplianceReportsGrid;
@FindBy(xpath="//a[starts-with(@id,'img_cmt_NonComplianceReportsGriddoc')]")
private WebElement doc_NonComplianceReportsGrid;
@FindBy(xpath="//a[starts-with(@class,'NonComplianceReportsGrid_rowedit')]")
private WebElement edit_NonComplianceReportsGrid;
@FindBy(xpath="//div[@id='non_compliance_reports_div']//tr/td[3]/input[@value='Link to File']")
private WebElement linktoFile_NonComplianceReportsGrid;

//Samplnce Acceptance
@FindBy(xpath="//td[@id='SampleAcceptanceGridPager_left']/table/tbody/tr/td/div[contains(text(),'Add New')]")
private WebElement addnew_SampleAcceptanceGrid;
@FindBy(xpath="//select[@id='SAStatus']")
private WebElement status_SampleAcceptanceGrid;
@FindBy(xpath="//input[@id='type']")
private WebElement type_SampleAcceptanceGrid;
@FindBy(xpath="//a[starts-with(@id,'img_cmt_SampleAcceptanceGriddoc')]")
private WebElement doc_SampleAcceptanceGrid;
@FindBy(xpath="//a[starts-with(@class,'SampleAcceptanceGrid_rowedit')]")
private WebElement edit_SampleAcceptanceGrid;
@FindBy(xpath="//div[@id='sample_acceptance_div']//tr/td[3]/input[@value='Link to File']")
private WebElement linktoFile_SampleAcceptanceGrid;

public WebElement getLinktoFile_daywork_sheets() {
	return linktoFile_daywork_sheets;
}

public WebElement getLinktoFile_CommercialCorrespondenceGrid() {
	return linktoFile_CommercialCorrespondenceGrid;
}

public WebElement getLinktoFile_NonComplianceReportsGrid() {
	return linktoFile_NonComplianceReportsGrid;
}

public WebElement getLinktoFile_SampleAcceptanceGrid() {
	return linktoFile_SampleAcceptanceGrid;
}

public WebElement getAddnew_NonComplianceReportsGrid() {
	return addnew_NonComplianceReportsGrid;
}

public void setAddnew_NonComplianceReportsGrid(
		WebElement addnew_NonComplianceReportsGrid) {
	this.addnew_NonComplianceReportsGrid = addnew_NonComplianceReportsGrid;
}

public WebElement getStatus_NonComplianceReportsGrid() {
	return status_NonComplianceReportsGrid;
}

public WebElement getDoc_NonComplianceReportsGrid() {
	return doc_NonComplianceReportsGrid;
}

public void setDoc_NonComplianceReportsGrid(
		WebElement doc_NonComplianceReportsGrid) {
	this.doc_NonComplianceReportsGrid = doc_NonComplianceReportsGrid;
}

public WebElement getEdit_NonComplianceReportsGrid() {
	return edit_NonComplianceReportsGrid;
}

public void setEdit_NonComplianceReportsGrid(
		WebElement edit_NonComplianceReportsGrid) {
	this.edit_NonComplianceReportsGrid = edit_NonComplianceReportsGrid;
}

public WebElement getAddnew_SampleAcceptanceGrid() {
	return addnew_SampleAcceptanceGrid;
}

public void setAddnew_SampleAcceptanceGrid(
		WebElement addnew_SampleAcceptanceGrid) {
	this.addnew_SampleAcceptanceGrid = addnew_SampleAcceptanceGrid;
}

public WebElement getStatus_SampleAcceptanceGrid() {
	return status_SampleAcceptanceGrid;
}

public void setStatus_SampleAcceptanceGrid(
		WebElement status_SampleAcceptanceGrid) {
	this.status_SampleAcceptanceGrid = status_SampleAcceptanceGrid;
}

public WebElement getType_SampleAcceptanceGrid() {
	return type_SampleAcceptanceGrid;
}

public void setType_SampleAcceptanceGrid(WebElement type_SampleAcceptanceGrid) {
	this.type_SampleAcceptanceGrid = type_SampleAcceptanceGrid;
}

public WebElement getDoc_SampleAcceptanceGrid() {
	return doc_SampleAcceptanceGrid;
}

public void setDoc_SampleAcceptanceGrid(WebElement doc_SampleAcceptanceGrid) {
	this.doc_SampleAcceptanceGrid = doc_SampleAcceptanceGrid;
}

public WebElement getEdit_SampleAcceptanceGrid() {
	return edit_SampleAcceptanceGrid;
}

public void setEdit_SampleAcceptanceGrid(WebElement edit_SampleAcceptanceGrid) {
	this.edit_SampleAcceptanceGrid = edit_SampleAcceptanceGrid;
}

public WebElement getAddnew_CommercialCorrespondenceGrid() {
	return addnew_CommercialCorrespondenceGrid;
}

public WebElement getDoc_CommercialCorrespondenceGrid() {
	return doc_CommercialCorrespondenceGrid;
}

public WebElement getEdit_CommercialCorrespondenceGrid() {
	return edit_CommercialCorrespondenceGrid;
}

public WebElement getAddnew_DayWorkSheetsGrid() {
	return addnew_DayWorkSheetsGrid;
}

public WebElement getEdit_DayWorkSheetsGrid() {
	return edit_DayWorkSheetsGrid;
}

public WebElement getDoc_DayWorkSheetsGrid() {
	return doc_DayWorkSheetsGrid;
}

//Obtain practical complition***********
@FindBy(xpath="//select[@id='st_pcCertificateObtainedCheckBox']")
private WebElement opc_certificateobtained;

@FindBy(xpath="//select[@id='st_retentionApplied']")
private WebElement opc_retationapplied;

@FindBy(xpath="//select[@id='st_onmSubmitted']")
private WebElement opc_onmSubmitted;

@FindBy(xpath="//select[@id='st_snagListIdentified']")
private WebElement opc_snagListIdentified;

@FindBy(xpath="//select[@id='st_internalCompletionDocument']")
private WebElement opc_internalCompletionDocument;

@FindBy(xpath="//input[@id='fileList_flm_pcCertificate']")
private WebElement opcdoc_certificateobtained;

@FindBy(xpath="//input[@id='fileList_flm_retentionApplied']")
private WebElement opcdoc_retentinApplied;

@FindBy(xpath="//input[@id='fileList_flm_onmSubmitted']")
private WebElement opcdoc_onmSubmitted;

@FindBy(xpath="//input[@id='fileList_flm_snagListIdentified']")
private WebElement opcdoc_snagListIdentified;

@FindBy(xpath="//input[@id='fileList_flm_internalCompletionDocument']")
private WebElement opcdoc_internalCompletionDocument;

@FindBy(xpath="//select[@id='explicit_approval_g8']")
private WebElement opc_cp8;


//post practical completion**************
@FindBy(xpath="//select[@id='st_finalAccountAgreement']")
private WebElement ppc_finalAccountAgreement;

@FindBy(xpath="//select[@id='st_finalRetentionPaid']")
private WebElement ppc_finalRetentionPaid;

@FindBy(xpath="//select[@id='st_projectDocumentArchived']")
private WebElement ppc_projectDocumentArchived;

@FindBy(xpath="//select[@id='st_projectDebrief']")
private WebElement ppc_projectDebrief;

@FindBy(xpath="//select[@id='st_subContractAccountSettled']")
private WebElement ppc_subContractAccountSettled;

@FindBy(xpath="//select[@id='st_closureofProject']")
private WebElement ppc_closureofProject;

@FindBy(xpath="//select[@id='st_bondsGuaranteesResolved']")
private WebElement ppc_bondsGuaranteesResolved;

@FindBy(xpath="//input[@id='fileList_flm_finalAccountAgreement']")
private WebElement ppcdoc_finalAccountAgreement;

@FindBy(xpath="//input[@id='fileList_flm_fl_finalRetentionPaid']")
private WebElement ppcdoc_finalRetentionPaid;

@FindBy(xpath="//input[@id='fileList_flm_fl_projectDocumentArchived']")
private WebElement ppcdoc_projectDocumentArchived;

@FindBy(xpath="//input[@id='fileList_flm_fl_subContractAccountSettled']")
private WebElement ppcdoc_subContractAccountSettled;

@FindBy(xpath="//input[@id='fileList_flm_iclosureofProject']")
private WebElement ppcdoc_closureofProject;

@FindBy(xpath="//input[@id='fileList_flm_fl_bondsGuaranteesResolved']")
private WebElement ppcdoc_bondsGuaranteesResolved;

@FindBy(xpath="//select[@id='explicit_approval_g9']")
private WebElement ppc_cp9;

@FindBy(xpath="//input[@id='fileList_flm_progDocument']")
private WebElement project_Programme_document;

@FindBy(xpath=("//div[div[contains(text(),'The Project has been submitted to Control Point 9 for Approval. If Approval is granted, the Project will be automatically archived')]]/div/a"))
private WebElement projectSubmitCP9Alert;

public WebElement getProjectSubmitCP9Alert(){
	return projectSubmitCP9Alert;
}

public WebElement getOpcdoc_certificateobtained() {
	return opcdoc_certificateobtained;
}

public WebElement getOpcdoc_retentinApplied() {
	return opcdoc_retentinApplied;
}
public WebElement getOpcdoc_onmSubmitted() {
	return opcdoc_onmSubmitted;
}
public WebElement getOpcdoc_snagListIdentified() {
	return opcdoc_snagListIdentified;
}
public WebElement getAddnewvalueforcast() {
	return Addnewvalueforcast;
}
public WebElement getOpcdoc_internalCompletionDocument() {
	return opcdoc_internalCompletionDocument;
}

public WebElement getPpcdoc_finalAccountAgreement() {
	return ppcdoc_finalAccountAgreement;
}

public WebElement getPpcdoc_finalRetentionPaid() {
	return ppcdoc_finalRetentionPaid;
}

public WebElement getPpcdoc_projectDocumentArchived() {
	return ppcdoc_projectDocumentArchived;
}

public WebElement getPpcdoc_subContractAccountSettled() {
	return ppcdoc_subContractAccountSettled;
}

public WebElement getPpcdoc_closureofProject() {
	return ppcdoc_closureofProject;
}

public WebElement getPpcdoc_bondsGuaranteesResolved() {
	return ppcdoc_bondsGuaranteesResolved;
}

public WebElement getOpc_certificateobtained() {
	return opc_certificateobtained;
}

public WebElement getOpc_retationapplied() {
	return opc_retationapplied;
}

public WebElement getOpc_onmSubmitted() {
	return opc_onmSubmitted;
}

public WebElement getOpc_snagListIdentified() {
	return opc_snagListIdentified;
}

public WebElement getOpc_internalCompletionDocument() {
	return opc_internalCompletionDocument;
}

public WebElement getOpc_cp8() {
	return opc_cp8;
}

public WebElement getPpc_finalAccountAgreement() {
	return ppc_finalAccountAgreement;
}

public WebElement getPpc_finalRetentionPaid() {
	return ppc_finalRetentionPaid;
}

public WebElement getPpc_projectDocumentArchived() {
	return ppc_projectDocumentArchived;
}

public WebElement getPpc_projectDebrief() {
	return ppc_projectDebrief;
}

public WebElement getPpc_subContractAccountSettled() {
	return ppc_subContractAccountSettled;
}

public WebElement getPpc_closureofProject() {
	return ppc_closureofProject;
}

public WebElement getPpc_bondsGuaranteesResolved() {
	return ppc_bondsGuaranteesResolved;
}

public WebElement getPpc_cp9() {
	return ppc_cp9;
}


public WebElement getPlandoc() {
	return plandoc;
}

public WebElement getDelete1() {
	return delete1;
}

public WebElement getDelete2() {
	return delete2;
}

public WebElement getDelete3() {
	return delete3;
}

public WebElement getMilestone() {
	return milestone;
}

public WebElement getDate() {
	return date;
}

public WebElement getSavemilestone() {
	return savemilestone;
}

public WebElement getEdit1() {
	return edit1;
}

public WebElement getEdit2() {
	return edit2;
}

public WebElement getDelete1subContractorProcPlanGrid() {
	return delete1subContractorProcPlanGrid;
}

public WebElement getDelete2subContractorProcPlanGrid() {
	return delete2subContractorProcPlanGrid;
}

public WebElement getProductionscheduledoc() {
	return productionscheduledoc;
}

public WebElement getExecp6() {
	return execp6;
}

public WebElement getProject_Programme_document(){
	return project_Programme_document;
}
//@return the ReportIntverval_PDP
public WebElement getReportIntverval_PDP() {
	return ReportIntverval_PDP;
}
}
