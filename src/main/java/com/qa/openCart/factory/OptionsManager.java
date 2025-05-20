package com.qa.openCart.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	private Properties prop;

	public OptionsManager(Properties prop)
	{
		this.prop=prop;
	}
	
	public ChromeOptions getChromeOtions()
	{
		ChromeOptions co= new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless")))
		{
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
		{
			co.addArguments("--incognito");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote")))	 
				{
					co.setCapability("browserName", "chrome");
					co.setBrowserVersion(prop.getProperty("browserVersion").trim());

					Map<String, Object> selenoidOptions = new HashMap<>();
					selenoidOptions.put("screenResolution", "1280x1024x24");
					selenoidOptions.put("enableVNC", true);
					selenoidOptions.put("name", prop.getProperty("testname"));
					co.setCapability("selenoid:options", selenoidOptions);
									
				}
		return co;
	}
	public FirefoxOptions getFirefoxOtions()
	{
		FirefoxOptions co= new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless")))
		{
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
		{
			co.addArguments("--incognito");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote")))
				{
					co.setCapability("browserName", "firefox");
					co.setBrowserVersion(prop.getProperty("browserVersion").trim());

					Map<String, Object> selenoidOptions = new HashMap<>();
					selenoidOptions.put("screenResolution", "1280x1024x24");
					selenoidOptions.put("enableVNC", true);
					selenoidOptions.put("name", prop.getProperty("testname"));
					co.setCapability("selenoid:options", selenoidOptions);

				}
		return co;
	}
	public EdgeOptions getEdgeOptions()
	{
		EdgeOptions co= new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless")))
		{
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
		{
			co.addArguments("--incognito");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote")))
				{
					co.setCapability("browserName", "edge");
				}
		return co;
	}
} 
