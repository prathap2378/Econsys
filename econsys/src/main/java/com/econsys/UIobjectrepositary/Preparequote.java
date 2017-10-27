package com.econsys.UIobjectrepositary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Preparequote {
	@FindBy(xpath="//input[@type='hidden']")
	private WebElement link_Filecheckbox;
	public WebElement getLink_Filecheckbox() {
		return link_Filecheckbox;
	}
	@FindBy(xpath="//a[@class='addlink']")
	private WebElement add_LinkfilePopup;
	public WebElement getAdd_LinkfilePopup() {
		return add_LinkfilePopup;
	}
	@FindBy(xpath="//div[@id='link_docs_fl_']/div/input[@class='list_item_link_docs_multiple']")
	private WebElement link_firstCheckbox;
	
	@FindBy(xpath="//a[text()='Close']")
	private WebElement close_uploadfield;
	
	public WebElement getClose_uploadfield() {
		return close_uploadfield;
	}
	public WebElement getLink_firstCheckbox() {
		return link_firstCheckbox;
	}
	@FindBy(id="st_gaFormat")
	private WebElement QuotationonourFormat;
	
	@FindBy(id="in_overallXProjectCost")
	private WebElement OverallProjectCost;
	
	@FindBy(id="in_overallXProjectSell")
	private WebElement OverallProjectSell;
	
	@FindBy(id="authorize_bid_sheet_g2")
	private WebElement BidSheetAuthorised;
	
	@FindBy(xpath="//div[contains(text(),'Add New')]")
	private WebElement Addnewpopup;
	
	@FindBy(xpath="//input[@id='category']")
	private WebElement CostCodeCategorytextfield;
		
	@FindBy(id="cost")
	private WebElement Cost;
	
	@FindBy(id="sell")
	private WebElement Sell;
	
	@FindBy(id="sData")
	private WebElement SaveAddcostsellpopup;
	
	@FindBy(id="cData")
	private WebElement CancelAddcostsellpopup;
	
	@FindBy(id="edit_1417097470592")
	private WebElement Editlink;
	
	@FindBy(id="delete_1417097470592")
	private WebElement Deletelink;
	
	@FindBy(linkText="Link to File")
	private WebElement Linktofile;
	
	@FindBy(xpath="//input[@id='fileList_flm_quoteDocument']")
	private WebElement quotedocument_Linkfile;
	
	public WebElement getQuotedocument_Linkfile() {
		return quotedocument_Linkfile;
	}
	public WebElement getBidsheet_linkfile() {
		return bidsheet_linkfile;
	}
	public WebElement getRiskopportunityregister_Linkfile() {
		return riskopportunityregister_Linkfile;
	}
	@FindBy(xpath="//input[@id='fileList_flm_bidSheet']")
	private WebElement bidsheet_linkfile;
	//[@type='file']fl_att_1
	
	@FindBy(xpath="//input[@id='fileList_flm_riskRegisterFiles']")
	private WebElement riskopportunityregister_Linkfile;
	
	@FindBy(id="st_Comments")
	private WebElement Comments;
	
	@FindBy(id="explicit_approval_g2")
	private WebElement Expliciteapprovalatgateway2;
	
	@FindBy(id="Save")
	private WebElement Savepagelevael;
	
	@FindBy(id="quoteprepared")
	private WebElement Quoteprepared;
	
	@FindBy(id="Cancel")
	private WebElement Cancelpageleval;

	@FindBy(xpath="//select[@id='explicit_approval_g3']")
	private WebElement execp3;
	
	//cp2-cp3 prepare quote***********
	
	@FindBy(xpath="//select[@id='st_gaFormat_g2g3']")
	private WebElement cp2cp3gaformat;

	@FindBy(xpath="//input[@id='flm_att_0multifile_hidden_riskRegister-g2g3']")
	private WebElement cp2cp3riskregisterdoc;

	@FindBy(xpath="//input[@id='flm_att_0multifile_hidden_quoteDocument-g2g3']")
	private WebElement cp2cp3quotedoc;

	@FindBy(xpath="//input[@id='fileList_flm_bidSheet']")
	private WebElement cp2cp3biddoc;
	
	@FindBy(xpath="//input[@id='fileList_flm_quoteDocument']")
	WebElement cp2cp3_quote_doc;

	@FindBy(xpath="//select[@id='authorize_bid_sheet_g3']")
	private WebElement cp2cp3bidsheet;

	//delete bid sheet
	@FindBy(xpath="//div[@id='bidSheet_Span']//u[contains(text(),'Delete')]")
	private WebElement cp2cp3deletebidsheet;
		
	public WebElement getExecp3() {
		return execp3;
	}
	public WebElement getQuotationonourFormat() {
		return QuotationonourFormat;
	}
	public WebElement getOverallProjectCost() {
		return OverallProjectCost;
	}
	public WebElement getOverallProjectSell() {
		return OverallProjectSell;
	}
	public WebElement getBidSheetAuthorised() {
		return BidSheetAuthorised;
	}
	public WebElement getAddnewpopup() {
		return Addnewpopup;
	}
	
	public WebElement getCostCodeCategorytextfield() {
		return CostCodeCategorytextfield;
	}
	public WebElement getCost() {
		return Cost;
	}
	public WebElement getSell() {
		return Sell;
	}
	public WebElement getSaveAddcostsellpopup() {
		return SaveAddcostsellpopup;
	}
	public WebElement getCancelAddcostsellpopup() {
		return CancelAddcostsellpopup;
	}
	public WebElement getEditlink() {
		return Editlink;
	}
	public WebElement getDeletelink() {
		return Deletelink;
	}
	public WebElement getLinktofile() {
		return Linktofile;
	}
	
	public WebElement getComments() {
		return Comments;
	}
	public WebElement getExpliciteapprovalatgateway2() {
		return Expliciteapprovalatgateway2;
	}
	public WebElement getSavepagelevael() {
		return Savepagelevael;
	}
	public WebElement getQuoteprepared() {
		return Quoteprepared;
	}
	public WebElement getCancelpageleval() {
		return Cancelpageleval;
	}
	public WebElement getCp2cp3gaformat() {
		return cp2cp3gaformat;
	}
	public WebElement getCp2cp3riskregisterdoc() {
		return cp2cp3riskregisterdoc;
	}
	public WebElement getCp2cp3quotedoc() {
		return cp2cp3quotedoc;
	}
	public WebElement getCp2cp3biddoc() {
		return cp2cp3biddoc;
	}
	public WebElement getCp2cp3bidsheet() {
		return cp2cp3bidsheet;
	}
	public WebElement getCp2cp3deletebidsheet() {
		return cp2cp3deletebidsheet;
	}
	//@return the cp2cp3_quote_doc
	public WebElement getCp2cp3_quote_doc() {
		return cp2cp3_quote_doc;
	}
}