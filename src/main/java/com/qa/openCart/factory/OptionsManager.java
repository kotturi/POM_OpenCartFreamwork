package com.qa.openCart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;

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
		return co;
	}
}
