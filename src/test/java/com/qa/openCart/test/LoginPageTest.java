package com.qa.openCart.test;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.openCart.base.BaseTest;
import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.factory.DriverFactory;
import com.qa.openCart.pages.AccountsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import  static com.qa.openCart.constants.AppConstants.*;

@Feature("F 50:Open Cart Login feature")
@Epic("Epic=100: Design for Open Cart Application")
@Story("US 101: implement login page for open cart application")
@Owner("Jyothi")
public class LoginPageTest extends BaseTest{
	
	//WebDriver driver;
	
	@Description("checking open cart login Page Title.......")
	@Severity(SeverityLevel.MINOR)
	@Test (description="checking login title")
	public void loginPageTitleTest()
	{
		String ActTitle= loginPage.getLoginPageTitle();	
		//log
		  ChainTestListener.log("Checking Loging Page title LOGSSS"+ActTitle);
		Assert.assertEquals(ActTitle, LOGIN_PAGE_TITILE);
	}
	
	@Description("checking open cart login page..")
	@Severity(SeverityLevel.CRITICAL)
	@Test (description="checking login URL")
	public void loginPageURLTest()
	{
		String acturl=loginPage.getLoginPageURL();
		Assert.assertTrue(acturl.contains(LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test()
	public void forgotPwdLinkExistTest()
	{
	Assert.assertTrue(loginPage.isForgotPWDLiskExists());
	}
	@Description("checking user is able to login with valid username and password..")
	@Severity(SeverityLevel.CRITICAL)
	@Test (description="checking login URL",priority = Short.MAX_VALUE)
	public void dologinTest() throws InterruptedException
	{
		//String ActAccPageTitle= loginPage.doLogin("march2024@open.com","Selenium@12345");
		accPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccPageTitle(), HOME_PAGE_TITILE);
		
	}
	
}	
