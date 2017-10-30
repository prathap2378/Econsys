package com.econsys.Genriclibrery;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.econsys.Genriclibrery.Driver;

public class CommonUtils extends Driver {

	boolean flag=false;
	//implicit wait method
	public void waitForPageToLoad(){
		driver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}
	//Explicit wait 
	public void blindWait() throws InterruptedException{
		Thread.sleep(500);
	}
	//refresh
	public void refresh(){
		Driver.driver().navigate().refresh();
	}
	//backword
	public void back(){
		driver().navigate().back();
	}
	//forward
	public void forward(){
		driver().navigate().forward();
	}
	//for delete all coockies
	public void deleteAllCoockies(){
		driver().manage().deleteAllCookies();
	}
	//wait to element to be present in UI using element ID
	public void WaitForElementIDPresent(String ElementID){
		WebDriverWait wait = new WebDriverWait(Driver.driver(), 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ElementID)));
	}
	//wait to element to be present in UI using element XPATH
	public void WaitForElementXPATHPresent(String ElementXPATH){
		WebDriverWait wait = new WebDriverWait(Driver.driver(), 100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ElementXPATH)));
	}
	//wait for link present in UI
	public void waitForLinkPresent(String LinkName){
		WebDriverWait wait = new WebDriverWait(Driver.driver(), 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(LinkName)));
	}
	//explicit wait
	public void waitForElement(WebElement ElementToBePresent,int timeToWaitInSeconds) throws InterruptedException{
	Thread.sleep(1000);
	WebDriverWait wait = new WebDriverWait(Driver.driver, timeToWaitInSeconds);
	wait.until(ExpectedConditions.visibilityOf(ElementToBePresent));
	
	System.out.println("Unable To Execute waitForElement Method  ");
	}
	//select By Index
	public String selectByIndex(WebElement SelectboxWebElement,int Index){
		Select selectBox = new Select(SelectboxWebElement);
		selectBox.selectByIndex(Index);
		WebElement option= selectBox.getFirstSelectedOption();
		return option.getText();
	}
	//select By VisibleText 
	public void selectByVisibleText(WebElement SelectboxWebElement,String VisibleText){
		Select selectBox = new Select(SelectboxWebElement);
		selectBox.selectByVisibleText(VisibleText);
		}
	//select By Value
	public void selectByValueUsingXPATH(String SelectboxXPATH,String Value){
		Select selectBox = new Select(Driver.driver().findElement(By.xpath(SelectboxXPATH)));
		selectBox.selectByValue(Value);
	}
	//select all options
	public void selectAllOptions(WebElement SelectboxWebElement){
		Select selectBox = new Select(SelectboxWebElement);
		List<WebElement> listOfOptions = selectBox.getOptions();
		for(int i=0;i<=listOfOptions.size()-1;i++){
			selectBox.selectByIndex(i);
			}
	}
	//deselect All options
	public void deSelectAll(WebElement SelectboxWebElement){
		Select selectBox = new Select(SelectboxWebElement);
		selectBox.deselectAll();
	}
	//deselect By Index
	public void deSelectByIndex(WebElement SelectboxWebElement,int Index){
		Select selectBox = new Select(SelectboxWebElement);
		selectBox.deselectByIndex(Index);
	}
	//deselect By Value
	public void deSelectByValue(WebElement SelectboxWebElement,String Value){
		Select selectBox = new Select(SelectboxWebElement);
		selectBox.deselectByValue(Value);
	}
	//deselect By Visible Text
	public void deSelectByVisibleText(WebElement SelectboxWebElement,String VisibleText){
		Select selectBox = new Select(SelectboxWebElement);
		selectBox.deselectByVisibleText(VisibleText);
	}
	//whether dropdown box is multi select listbox or not
	public boolean isMultiple(WebElement SelectboxWebElement){
		Select selectBox = new Select(SelectboxWebElement);
		flag = selectBox.isMultiple();
		return flag;
	}
	//Actions Class from here
	public void actionDragAndDrop(WebElement Source,WebElement Target)	{
		Actions act = new Actions(Driver.driver());
		act.clickAndHold(Source).perform();
		act.moveToElement(Target).release();
	}
	//mouseover operation
	public void actionMouseHover(WebElement elementNeedsToMouseOver){
		Actions act = new Actions(Driver.driver());
		act.moveToElement(elementNeedsToMouseOver).perform();
	}
	//Accepting Alert
	public void alertAccept(){
		Alert alert = Driver.driver().switchTo().alert();
		alert.accept();
	}
	//Dismissing Alert
	public void alertDismiss(){
		Alert alert = Driver.driver().switchTo().alert();
		alert.dismiss();
	}
	//Getting text in alert
	public String alertGetText(){
		Alert alert = Driver.driver().switchTo().alert();
		String alertText = alert.getText();
		return alertText;
	}
	//switching To Frame
	public void switchToFrame(String FrameID){
		Driver.driver().switchTo().frame(FrameID);
	}
	//right click on particular element
	public void actionRightClick(WebElement OnElemetRightClick){
		Actions act = new Actions(Driver.driver());
		act.contextClick(OnElemetRightClick);
	}
	//maximize the opened window
	 	public void maximize(){
		Driver.driver().manage().window().maximize();
	}
	//verify text present in UI or not
	public boolean verifyTextPresent(String particularText){
		String pageSource = Driver.driver().getPageSource();
		if(pageSource.contains(particularText)){
		flag = true;
	}
		return flag;
	}
    //verify Text in Particular Area in UI
	public boolean verifyText(String wbXpath,String exepectedText){
		String actText = Driver.driver().findElement(By.xpath(wbXpath)).getText();
		if(exepectedText.equals(actText)){
		flag = true;
	}
		return flag;
	}
	//java script executor
	public void javaScriptExecution(String javaScript){
		JavascriptExecutor Script = (JavascriptExecutor)Driver.driver();
		Script.executeScript(javaScript);
	}
	//taking Screen shot
	public void screenShot(String ScreenShotLocationWithName){
		File scrfile = ((TakesScreenshot)driver()).getScreenshotAs(OutputType.FILE);
		try {
		org.apache.commons.io.FileUtils.copyFile(scrfile, new File(ScreenShotLocationWithName+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//mpuseHover and Click on link text
	public void mouseHoverAndClickLinkText(WebElement elementNeedsToMouseOver,String Linktext){
		Actions act = new Actions(Driver.driver());
		act.moveToElement(elementNeedsToMouseOver);
		Driver.driver().findElement(By.linkText(Linktext)).click();
	}
	//for file upload
	public void uploadFile(String exePath) throws Exception{
		Runtime.getRuntime().exec(exePath);
	}
	public void assert_Test(String expected, String actual){
		Assert.assertEquals(expected, actual);
	}
}