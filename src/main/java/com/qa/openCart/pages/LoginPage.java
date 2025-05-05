	package com.qa.openCart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import com.aventstack.chaintest.domain.Log;
import com.qa.openCart.factory.DriverFactory;
import com.qa.openCart.utils.ElementUtil;

import io.qameta.allure.Step;

import  static com.qa.openCart.constants.AppConstants.*;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	private final By email=By.id("input-email");
	private final By password=By.id("input-password");
 	private final By loginBut=By.xpath("//input[@value='Login']");
 	private final By forgotPWDLink= By.linkText("Forgotten Password");
 	private final By registerLink= By.linkText("Register");
 	
 	public static final Logger log=LogManager.getLogger(LoginPage.class);
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		eleutil = new ElementUtil(driver);
	}
	
	@Step("getting Login page title")
	
	public String getLoginPageTitle()
	{
		String title=eleutil.waitFotTitleIs(LOGIN_PAGE_TITILE, DEFAULT_TIMEOUT);
		//	String title=driver.getTitle();
		log.info("Login Page title is: "+title);
		
		return title;
	}
	@Step("getting Login page URL")
	public String getLoginPageURL()
	{
		String url=eleutil.waitForURLContains(LOGIN_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		//String url=driver.getCurrentUrl();
		log.info("Login Page url is: "+url);
		
		return url;
	}
	
	@Step("checking FP link exists or not")
	public boolean isForgotPWDLiskExists()
	{
		return eleutil.isElementDisplayed(forgotPWDLink);
		//return driver.findElement(forgotPWDLink).isDisplayed();
	}
	@Step("login with valid username:{0} and password{1}")
	public AccountsPage doLogin(String Uname,String pwd) throws InterruptedException
	{
		log.info("User Credentios:" +Uname+":"+pwd);
		eleutil.waitForElementVisible(email, MEDIUM_DEFAULT_TIMEOUT).sendKeys(Uname);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(loginBut);	
		return new AccountsPage(driver);
	}
	@Step("Navigating to the User Registration page")
	public RegistrationPage navigateToRegisterPage()
	{
		eleutil.clickWhenReady(registerLink, DEFAULT_TIMEOUT);
		return new RegistrationPage(driver);
	}
	

}
