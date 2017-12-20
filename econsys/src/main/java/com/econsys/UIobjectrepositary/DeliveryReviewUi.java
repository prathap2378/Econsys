package com.econsys.UIobjectrepositary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.econsys.Genriclibrery.Driver;

public class DeliveryReviewUi {
	
	@FindBy(xpath=("//div[div[contains(text(),'There are changes against the approved data hence changes will go for OD Approval')]]//div/a[1]"))
	private WebElement dataChange_Alert;

	//@return the dataChange_Alert
	public WebElement getDataChange_Alert() {
		return dataChange_Alert;
	}
	
@FindBy(xpath="//input[@id='review']")
private WebElement toMonthlyReview;

public WebElement getToMonthlyReview(){
	return toMonthlyReview;
}
}
