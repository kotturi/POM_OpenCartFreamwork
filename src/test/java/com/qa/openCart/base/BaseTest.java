package com.qa.openCart.base;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.openCart.factory.DriverFactory;
import com.qa.openCart.pages.AccountsPage;
import com.qa.openCart.pages.LoginPage;
import com.qa.openCart.pages.RegistrationPage;
import com.qa.openCart.pages.SearchResultsPage;
import com.qa.openCart.pages.productInfoPage;
import com.qa.openCart.utils.LogsUtils;

import io.qameta.allure.Description;

//@Listeners(ChainTestListener.class)
public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultPage;
	protected productInfoPage productInfoPage;
	protected RegistrationPage registerPage;
	
	public static final Logger log=LogManager.getLogger(BaseTest.class);

	@Description("init the driver and properties")
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		df = new DriverFactory();
		prop=df.initProp();
		
		if(browserName!=null)
		{
			prop.setProperty("browser", browserName);
		}
		driver=df.initDriver(prop);//CBref
	
		loginPage = new LoginPage(driver);
	}

//	@BeforeMethod
//	public void beforeMethod(ITestContext result)
//	{
//		LogsUtils.Info("----------Method Started"+result.getName());
//	}	
	@AfterMethod
	public void attachScreenshot(ITestResult result)
	{
		
		  if(!result.isSuccess()) {//only failue
		  ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png"); }
		 
		//ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		  LogsUtils.Info("----------Method Ended"+result.getMethod().getMethodName());
	}
	@Description("closing the browser")
	@AfterTest
	public void tearDown() {
		driver.quit();
		log.info("----Closeing the Browser----");
	}

}

