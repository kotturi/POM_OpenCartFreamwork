package com.qa.openCart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.openCart.exceptions.BrowserException;

public class DriverFactory {

	WebDriver driver;
	protected Properties prop;
	OptionsManager optionManager;
	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<WebDriver>();

	public static final Logger log=LogManager.getLogger(DriverFactory.class);
	public WebDriver initDriver(Properties prop) {
		log.info("Properties: "+prop);
		String browserName = prop.getProperty("browser");
		optionManager = new OptionsManager(prop);
		//System.out.println("Browser Name:" + browserName);
		log.info("Browser Name:" +browserName);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionManager.getChromeOtions()));
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
		//	System.out.println("Please pass the valid browser name" + browserName);
			log.error("Please pass the valid browser name" + browserName);
			throw new BrowserException("===Invalid Browser");
		}
		// driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}
	
	/**
	 * getDriver
	 * @return 
	 * @return
	 */
	public static WebDriver getDriver()
	{
		return tlDriver.get();
	}

 	// mvn clean install -Denv="qa"
	public Properties initProp() {

		String envName = System.getProperty("env");
		FileInputStream ip = null;
		prop= new Properties();
		try {
		if (envName == null) {
			//System.out.println("env is null, hence running the test in QA env...:" + envName);
			log.warn("env is null, hence running the test in QA env...:" + envName);
			ip = new FileInputStream("./src/test/resource/config/qa.config.properties");
		} else {
			//
			System.out.println("Running the test on env:" + envName);
			log.info("Running the test on env:" + envName);
			switch (envName.toLowerCase().trim()) {

			case "qa":
				ip = new FileInputStream("./src/test/resource/config/qa.config.properties");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resource/config/dev.config.properties");
				break;
			case "uat":
				ip = new FileInputStream("./src/test/resource/config/uat.config.properties");
				break;
			case "prod":
				ip = new FileInputStream("./src/test/resource/config/config.properties");
				break;
			default:
				log.error("Invalid Env...:"+envName);
				throw new IllegalArgumentException("Unexpected value: ");
			}
		}
	}catch(FileNotFoundException e)
	{
		e.printStackTrace();
	}

		try {
			try {
				prop.load(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * TakeScreenshot
	 * @return 
	 */
	public static File getScreenshotFile()
	{
	 	
//		TakesScreenshot t=	(TakesScreenshot) driver;
//		File file=t.getScreenshotAs(OutputType.FILE);
		File srcFile=((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		return srcFile;
	}
}
