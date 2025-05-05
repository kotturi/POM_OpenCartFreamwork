package com.qa.openCart.pages;

import java.lang.annotation.ElementType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openCart.utils.ElementUtil;
import com.qa.openCart.utils.StringUtils;

import  static com.qa.openCart.constants.AppConstants.*;
public class RegistrationPage {

	protected WebDriver driver;
	protected ElementUtil eleUtil;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.name("lastname");
	private By email = By.xpath("//*[@id=\"input-email\"]");
	private By telephone = By.name("telephone");
	private By password = By.cssSelector("#input-password");
	private By confirmPassword = By.name("confirm");
	private By agreeCheckBox = By.name("agree");
	// By linkPrivacy=By.linkText("Privacy Policy");
	private By continueButton = By.xpath("//*[@id=\"content\"]/form/div/div/input[2]");
	private By successMesg = By.tagName("h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink=By.linkText("Register");
	
	private By subscribeYes=By.xpath("(//input[@name='newsletter'])[1]");
	private By subscribeNo=By.xpath("(//input[@name='newsletter'])[2]");
	
	public RegistrationPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil= new ElementUtil(driver);
	}
	
	public String getRegisterPageTitle()
	{
		String registerTitle=eleUtil.waitFotTitleIs(REGISTER_PAGE_TITILE,DEFAULT_TIMEOUT);
		System.out.println("Registraion Page Title is" +registerTitle);
		return registerTitle;
	}
	
	public boolean userRegistration(String firstName,String lastName,
								String telephone,String password,String subscribe)
	{
		eleUtil.waitForElementVisible(this.firstName, DEFAULT_TIMEOUT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, StringUtils.getRandomEmailId());
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		if(subscribe.equalsIgnoreCase("yes"))
		{
		eleUtil.doClick(subscribeYes);
		}
		else
		{
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		eleUtil.waitForElementPresence(successMesg, MEDIUM_DEFAULT_TIMEOUT);
		if(eleUtil.waitForElementPresence(successMesg, MEDIUM_DEFAULT_TIMEOUT).getText().contains(REGISTER_SUCCESS__MESSG))
		{
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		else 
			return false;
	}
}
 