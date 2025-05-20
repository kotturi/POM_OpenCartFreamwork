package com.qa.openCart.factory;

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
