package com.econsys.UIobjectrepositary;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.econsys.Genriclibrery.EconsysVariables;

public class AllPages {
	EconsysVariables ev= new EconsysVariables();
@FindBy(xpath="//div[text()='My Opportunities ']")
WebElement my_opportunities;
@FindBy(xpath="//div[text()='Project Archive ']")
WebElement proArchive;
@FindBy(xpath="//div[text()='Executive Dashboard ']")
WebElement exeDashboard;
@FindBy(xpath="//span[contains(text(),'All Projects')]")
WebElement allProjects;
@FindBy(xpath="//span[text()=' My Opportunities']")
WebElement myOpplink;
@FindBy(xpath="//span[text()=' Admin']")
WebElement adminLink;
/*@FindBy(xpath="//span[contains(text(),'All Projects')]")
WebElement allProLink;*/
@FindBy(xpath="//span[text()=' Project Archive']")
WebElement proArchLink;
@FindBy(xpath="//span[text()=' Executive Dash Board']")
WebElement exeDashLink;


@FindBy(xpath="//span[text()=' Saved RTQ' or text()=' Saved Rtq']")
WebElement saveRTQlink;
@FindBy(xpath="//span[text()=' Auto Approval']")
WebElement autoApplink;
@FindBy(xpath="//span[text()=' Business Rules']")
WebElement businessRuleslink;
@FindBy(xpath="//span[text()=' User List']")
WebElement userListlink;
@FindBy(xpath="//span[text()=' Project Documents']")
WebElement proDocslink;
@FindBy(xpath="//span[text()=' Guidance Notes']")
WebElement guidanceNoteslink;
@FindBy(xpath="//span[text()=' Master Data']")
WebElement gridMasterlink;
@FindBy(xpath="//span[text()=' Data Report']")
WebElement dataReportlink;
@FindBy(xpath="//a//span[contains(text(),'Settings')]")
WebElement settingslink;

@FindBy(xpath="//a//span[contains(text(),'Prompts & Alerts')]")
WebElement promptsAlerts;

@FindBy(xpath="//span[@class='ui-jqgrid-title' and text()='Saved RTQ']")
WebElement savedRTQ;
@FindBy(xpath="//div[text()='Auto Approval Criteria']")
WebElement autoApproval;
@FindBy(xpath="//div[text()='Business Rules']")
WebElement businessRules;
@FindBy(xpath="//div[text()='User List']")
WebElement userList;
@FindBy(xpath="//div[text()='Project Documents']")
WebElement proDoc;
@FindBy(xpath="//div[text()='Guidance Notes']")
WebElement guidanceNotes;
@FindBy(xpath="//div[text()='Master Data']")
WebElement gridMasters;
@FindBy(xpath="//span[@class='ui-jqgrid-title' and text()='Data Report']")
WebElement dataReport;
@FindBy(xpath="//div[text()='Settings/Preferences']")
WebElement settings;
@FindBy(xpath="//span[text()=' Review']")
WebElement reviewLink;

@FindBy(xpath="//a[text()='Details']")
List<WebElement> reviewDetailsLink; 

@FindBy(xpath="//img[@src='http://10.0.0.16:8080/ace-theme/images/icons/Details.png']")//changed
List<WebElement> detailsLink;
@FindBy(xpath="//a[text()='View']")
List<WebElement> viewLink;
@FindBy(xpath="//label[text()='Guidance Notes']")
WebElement guidanceNotesaccordion;
@FindBy(xpath="//label[contains(text(),'Cost Forecast Changes')]")
WebElement reviewContent;

@FindBy(xpath="//tbody/tr[2]/td[4]")
WebElement projectName;//In All project page ,name of the project in 1st row
@FindBy(xpath="//img[@src[contains(., 'Delete.png')]]")
WebElement deleteOption;//Delete option to delete the Project
@FindBy(xpath="//button[@class='btn btn-small btn-info dropdown-toggle']")
List<WebElement> optionBtn;//Option button in All Projects page
@FindBy(xpath="//span[text()='Delegate']")
WebElement delegate_option;//select delegate option in All Projects page
@FindBy(xpath="//span[text()='Re-Assign']")
WebElement reassign_option;//select Reassign option in All Projects page
@FindBy(xpath="//select[@id='delegateTo']")
WebElement select_Delegate;//Delegate dropdown element
@FindBy(xpath="//input[@id='delegate']")
WebElement delegateBtn;
@FindBy(xpath="//span[text()='View']")
WebElement viewOption;
@FindBy(xpath="//label[text()='Comment History']")
WebElement commentHistoryAccordion;
@FindBy(xpath="//span[contains(text(),'Delegated')]")
List<WebElement> delgatedCommentHistory;
@FindBy(id="_deligatertq_WAR_deligatertqportlet_delegateTo")
WebElement savedRTQDelegate_select;
@FindBy(xpath="//span/img[@src='http://10.0.0.16:8080/ace-theme/images/icons/Deligate.png']")
WebElement delegateOption_savedRTQ;
@FindBy(xpath="//span/img[@src='http://10.0.0.16:8080/ace-theme/images/icons/view.png']")
WebElement view_SavedRTQ;
@FindBy(xpath="//td[@aria-describedby='delegateGrid_owners']")
List<WebElement> delegateOwners;
@FindBy(xpath="//td[@aria-describedby='delegatestaticGrid_assaignedTo']")
List<WebElement> delegatedOwner_AllPro;
@FindBy(xpath="//div[div[contains(text(),'delete')]]//div/a[1]")
WebElement Ok_PopUpBtn;

@FindBy(xpath="//ul[li[a[contains(@onclick,'viewAllApprovals')]]]//a[contains(@onclick,'viewAllApprovals')]") 
public WebElement view_All_Approvals;

@FindBy(xpath="//span[contains(text(),'Reports')]")
private WebElement reports;
@FindBy(xpath="//span[contains(text(),'Status of Submitted Quote')]")
private WebElement statusofSubmitQuote;

//My watch List
@FindBy(xpath="//span[contains(text(),'My Watchlist')]")
private WebElement myWatchList;
@FindBy(xpath="//a[contains(@onclick,'viewAllReviews')]")
private WebElement viewReviews;

//Role Reassign Oprions
@FindBy(xpath="//td[text()='Sales Lead: ']/following-sibling::td")
private WebElement salesdLead;
@FindBy(xpath="//span[contains(text(),'My Account')]")
private WebElement myAccount;

@FindBy(xpath="//td[text()='Engineering Lead: ']/following-sibling::td")
private WebElement engineeringLead;
@FindBy(xpath="//td[text()='Commercial Lead: ']/following-sibling::td")
private WebElement commercialLead;
@FindBy(xpath="//td[text()='Project Lead: ']/following-sibling::td")
private WebElement projectLead;
@FindBy(xpath="//td[text()='Operations Lead: ']/following-sibling::td")
private WebElement operationLead;

public WebElement getSalesdLead() {
	return salesdLead;
}

public WebElement getEngineeringLead() {
	return engineeringLead;
}

public WebElement getCommercialLead() {
	return commercialLead;
}

public WebElement getProjectLead() {
	return projectLead;
}

public WebElement getOperationLead() {
	return operationLead;
}

public WebElement getViewReviews() {
	System.out.println("inside review");
	return viewReviews;
}

public WebElement getMyWatchList() {
	return myWatchList;
}
@FindBy(xpath="//input[contains(@title,'Add')]")
private WebElement addToWatchlist;

public WebElement getAddToWatchlist() {
	return addToWatchlist;
}
	
public WebElement getStatusofSubmitQuote() {
	return statusofSubmitQuote;
}
public WebElement getReports() {
	return reports;
}

public WebElement getOk_PopUpBtn() {
	return Ok_PopUpBtn;
}
public WebElement getDeleteOption() {
	return deleteOption;
}
public WebElement getreviewDetailsLink(){
	return reviewDetailsLink.get(0);
}
public WebElement getDelegatedOwner_AllPro() {
	return delegatedOwner_AllPro.get(0);
}
public WebElement getDelegateOwners() {
	return delegateOwners.get(0);
}
public WebElement getView_SavedRTQ() {
	return view_SavedRTQ;
}
public WebElement getDelegateOption_savedRTQ() {
	return delegateOption_savedRTQ;
}
public WebElement getSavedRTQDelegate_select() {
	return savedRTQDelegate_select;
}
public List<WebElement> getDelgatedCommentHistory() {
	return delgatedCommentHistory;
}
public WebElement getCommentHistoryAccordion() {
	return commentHistoryAccordion;
}
public WebElement getViewOption() {
	return viewOption;
}
public WebElement getDelegateBtn() {
	return delegateBtn;
}
public WebElement getSelect_Delegate() {
	return select_Delegate;
}
public WebElement getReassign_option() {
	return reassign_option;
}
public WebElement getProjectName() {
	return projectName;
}
public WebElement getOptionBtn() {
	return optionBtn.get(0);
}
public WebElement getDelegate_option() {
	return delegate_option;
}
public WebElement getViewLink() {
	return viewLink.get(0);
}
public WebElement getGuidanceNotesaccordion() {
	return guidanceNotesaccordion;
}
public WebElement getReviewLink() {
	return reviewLink;
}
public WebElement getdetailsLink() {
	return detailsLink.get(0);
}
public WebElement getReviewContent() {
	return reviewContent;
}
public WebElement getSaveRTQlink() {
	return saveRTQlink;
}
public WebElement getAutoApplink() {
	return autoApplink;
}
public WebElement getBusinessRuleslink() {
	return businessRuleslink;
}
public WebElement getUserListlink() {
	return userListlink;
}
public WebElement getProDocslink() {
	return proDocslink;
}
public WebElement getGuidanceNoteslink() {
	return guidanceNoteslink;
}
public WebElement getGridMasterlink() {
	return gridMasterlink;
}
public WebElement getDataReportlink() {
	return dataReportlink;
}
public WebElement getSettingslink() {
	return settingslink;
}
public WebElement getSavedRTQ() {
	return savedRTQ;
}
public WebElement getAutoApproval() {
	return autoApproval;
}
public WebElement getBusinessRules() {
	return businessRules;
}
public WebElement getUserList() {
	return userList;
}
public WebElement getProDoc() {
	return proDoc;
}
public WebElement getGuidanceNotes() {
	return guidanceNotes;
}
public WebElement getGridMasters() {
	return gridMasters;
}
public WebElement getDataReport() {
	return dataReport;
}
public WebElement getSettings() {
	return settings;
}
public WebElement getMy_opportunities() {
	return my_opportunities;
}
public WebElement getProArchive() {
	return proArchive;
}
public WebElement getExeDashboard() {
	return exeDashboard;
}
public WebElement getAllProjects() {
	return allProjects;
}
public WebElement getMyOpplink() {
	return myOpplink;
}
public WebElement getAdminLink() {
	return adminLink;
}
/*public WebElement getAllProLink() {
	return allProLink;
}*/
public WebElement getProArchLink() {
	return proArchLink;
}
public WebElement getExeDashLink() {
	return exeDashLink;
}

/**
 * @return the myAccount
 */
public WebElement getMyAccount() {
	return myAccount;
}

public WebElement getPromptsAlerts() {
	return promptsAlerts;
}
}

