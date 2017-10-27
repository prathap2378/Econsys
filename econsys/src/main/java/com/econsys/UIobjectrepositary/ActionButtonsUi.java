package com.econsys.UIobjectrepositary;

import javax.xml.xpath.XPath;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ActionButtonsUi {
	
	@FindBy(xpath="//input[@value='Save']")
	private WebElement savebutton;
	
	@FindBy(xpath="//input[@value='Approve']")
	private WebElement approve_Button;
	
	@FindBy(xpath="//input[@id='reject']")
	private WebElement rejectbutton;
	
	@FindBy(xpath="//input[@id='dontProceed']")
	private WebElement donotproccedbutton;
	
	@FindBy(xpath="//input[@class='list_item_link_docs_multiple']")
	private WebElement linkFileCheckbox;
	public WebElement getLinkFileCheckbox() {
		return linkFileCheckbox;
	}

	@FindBy(linkText="Project Archive")
	private WebElement project_Archive;
	@FindBy(linkText="All Projects")
	private WebElement all_Projects;
	@FindBy(linkText="Quote Submit Status")
	private WebElement quote_Submit_Status;
	@FindBy(linkText="Executive Dash Board")
	private WebElement executive_Dash_Board;
	@FindBy(linkText="Review")
	private WebElement review;
	@FindBy(linkText="My Opportunities")
	private WebElement my_Opportunities;
	@FindBy(linkText="Home")
	private WebElement home;
	@FindBy(linkText="New RTQ")
	private WebElement RTQTab;
	@FindBy(linkText="Small Works")
	private WebElement smallWorks;
	@FindBy(linkText="RTQ")
	private WebElement newform;
	
	@FindBy(xpath="//span[contains(text(),'Admin')]")
	private WebElement admin;
	
	//Operation acceptance Accept
	@FindBy(xpath="//input[@id='approve']")
	private WebElement acceptOperationAcceptance;
	
	public WebElement getAcceptOperationAcceptance() {
		return acceptOperationAcceptance;
	}

	public WebElement getAdmin() {
		return admin;
	}
	@FindBy(xpath="//button[starts-with(@id,'actn-btn')]")
	private WebElement options_Button;
	
	public WebElement getOptions_Button() {
		return options_Button;
	}
	@FindBy(xpath="//td[starts-with(@id,'search_')]")
	private WebElement search;
	
	public WebElement getSearch() {
		return search;
	}
	
	@FindBy(xpath="//a[contains(text(),'Find')]")
	private WebElement find;
	
	public WebElement getFind() {
		return find;
	}
	
	@FindBy(xpath="//input[@class='input-elm']")
	private WebElement searchProjectname;
	
	public WebElement getSearchProjectname() {
		return searchProjectname;
	}

	public WebElement getRTQTab() {
		return RTQTab;
	}
	
	public WebElement getSmallWorks() {
		return smallWorks;
	}

	public WebElement getNewform() {
		return newform;
	}
	
	public WebElement getProject_Archive() {
		return project_Archive;
	}

	public WebElement getAll_Projects() {
		return all_Projects;
	}

	public WebElement getQuote_Submit_Status() {
		return quote_Submit_Status;
	}

	public WebElement getExecutive_Dash_Board() {
		return executive_Dash_Board;
	}

	public WebElement getReview() {
		return review;
	}

	public WebElement getMy_Opportunities() {
		return my_Opportunities;
	}

	public WebElement getHome() {
		return home;
	}
	@FindBy(xpath="//a[@class='addlink']")
	private WebElement add_LinkfilePopup;

	public WebElement getAdd_LinkfilePopup() {
		return add_LinkfilePopup;
	}	
	
	@FindBy(xpath="//a[@class='closelink']")
	private WebElement close_LinkfilePopup;
	
	public WebElement getClose_LinkfilePopup() {
		return close_LinkfilePopup;
	}
	
	@FindBy(xpath="//input[@id='cancel']")
	private WebElement cancelbutton;
	
	@FindBy(xpath="//textarea[@id='st_Comments']")
	private WebElement comments;
	
	@FindBy(xpath="//textarea[@id='comments']")
	private WebElement pop_Comments;
	
	public WebElement getPop_Comments() {
		return pop_Comments;
	}

	public WebElement getDonot_Proceed_allret_Cancel() {
		return donot_Proceed_allret_Cancel;
	}
	@FindBy(xpath="//input[@value='Submit']")
	private WebElement submitbutton;
	
	@FindBy(xpath="//input[@value='To Monthly Review']")
	private WebElement mrbutton;

	@FindBy(xpath="//input[@value='Project Completed']")
	private WebElement projectCompletedbutton;
	
	/*@FindBy(xpath="//input[@value='Approve']")
	private WebElement reviewInvolveapprovebutton;,,*/
	
	@FindBy(xpath="//div[@id='gate2']/div/ul/li[1]/a")
	private WebElement reviewinvolvetasks;
	
	public WebElement getReviewinvolvetasks() {
		return reviewinvolvetasks;
	}
	@FindBy(xpath="//label[@id='myApprovals']")
	private WebElement bymeapprovals;
	
	@FindBy(xpath="//label[@id='groupApprovals']")
	private WebElement groupapprovals;
	
	@FindBy(xpath="//div[@id='gate1']/div/ul/li[1]/a")
	private WebElement viewalltasks;
	
	@FindBy(xpath="//input[@id='save']")
	private WebElement save_RTQ;
	
	@FindBy(xpath="//input[@id='reset']")
	private WebElement resetButton;
	
	@FindBy(xpath="//span[contains(text(),'Saved Rtq')]")
	private WebElement savedRTQ;

	public WebElement getSavedRTQ() {
		return savedRTQ;
	}

	public WebElement getResetButton() {
		return resetButton;
	}

	public WebElement getSave_RTQ() {
		return save_RTQ;
	}
	
	@FindBy(linkText="Edit")
	private WebElement editLink;
	
	@FindBy(xpath="//div[@class='modal-footer']/a[1]")
	private WebElement donot_Proceed_allret_Ok;
    @FindBy(xpath="//div[@class='modal-footer']/a[2]")
    private WebElement donot_Proceed_allret_Cancel;
    
    /*@FindBy(xpath="//input[@value='Approve']")
    private WebElement ApproveBtn;..
    
	public WebElement getApproveBtn() {
		return ApproveBtn;
	}*/

	public WebElement getDonot_Proceed_allret_Ok() {
		return donot_Proceed_allret_Ok;
	}

	public WebElement getDonotProceed_allret_Cancel() {
		return donot_Proceed_allret_Cancel;
	}
	
	

	public WebElement getEditLink() {
		return editLink;
	}

	public WebElement getBymeapprovals() {
		return bymeapprovals;
	}
	@FindBy(linkText="Delete")
	private WebElement deleteLink;

	public WebElement getDeleteLink() {
		return deleteLink;
	}

	public WebElement getGroupapprovals() {
		return groupapprovals;
	}

	public WebElement getViewalltasks() {
		return viewalltasks;
	}

	/**
	 * @return the approve_Button
	 */
	public WebElement getApprove_Button() {
		return approve_Button;
	}

	public WebElement getSavebutton() {
		return savebutton;
	}

	public WebElement getRejectbutton() {
	return rejectbutton;
	}

	public WebElement getDonotproccedbutton() {
		return donotproccedbutton;
	}

	public WebElement getCancelbutton() {
		return cancelbutton;
	}

	public WebElement getComments() {
		return comments;
	}

	public WebElement getSubmitbutton() {
		return submitbutton;
	}

	public WebElement getMrbutton() {
		return mrbutton;
	}

	public WebElement getProjectCompletedbutton() {
		return projectCompletedbutton;
	}
	/*public WebElement getReviewInvolveapprovebutton() {
		return reviewInvolveapprovebutton;
	}*/

	@FindBy(xpath="//div[@id='gate3']/div/ul/li[1]/a")
	private WebElement board_Newtask_button;
	
	public WebElement getBoard_Newtask_button() {
		return board_Newtask_button;
	}
	
}
