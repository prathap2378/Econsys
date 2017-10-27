/**
 * 
 */
package com.econsys.UIobjectrepositary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

/**
 * @author bhanu.pk
 *
 */
public class TandCverification {

	@FindBy(xpath="//select[@id='st_tandcProposedCreditWorthy']")
	private WebElement creditWorthy;
	
	@FindBy(xpath="//textarea[@id='st_proposed_course_action']")
	private WebElement proposedCourseofAction;
	
	@FindBy(xpath="//input[@id='fileList_flm_orderAcknowledgement']")
	private WebElement orderAcknowledgement_file;

	/**
	 * @return the creditWorthy
	 */
	public WebElement getCreditWorthy() {
		return creditWorthy;
	}
	//T&C review submit button
	@FindBy(id="btnsubmit") public WebElement submitT_Creview;
	//@return the proposedCourseofAction
	public WebElement getProposedCourseofAction() {
		return proposedCourseofAction;
	}

	/**
	 * @return the orderAcknowledgement_file
	 */
	public WebElement getOrderAcknowledgement_file() {
		return orderAcknowledgement_file;
	}
}
