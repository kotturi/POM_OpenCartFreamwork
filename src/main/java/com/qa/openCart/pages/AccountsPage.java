package com.qa.openCart.pages;

import static com.qa.openCart.constants.AppConstants.DEFAULT_TIMEOUT;
import static com.qa.openCart.constants.AppConstants.HOME_PAGE_FRACTION_URL;
import static com.qa.openCart.constants.AppConstants.HOME_PAGE_TITILE;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.openCart.factory.DriverFactory;
import com.qa.openCart.utils.ElementUtil;


public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	public static final Logger log=LogManager.getLogger(AccountsPage.class);
 	
	private final By header= By.cssSelector("div#content>h2");
	private final By search= By.name("search");
	private final By searchIcon= By.cssSelector("div#search button");
	
	public AccountsPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	public String getAccPageTitle()
	{
		String title=eleUtil.waitFotTitleIs(HOME_PAGE_TITILE, DEFAULT_TIMEOUT);
		log.info("Home Page title is: "+title);
		return title;
	}
	public String getAccPageURL()
	{
		String url=eleUtil.waitForURLContains(HOME_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		log.info("Home Page URL is: "+url);
		return url;
	}
	
	public List<String> accPageHeaders()
	{
		List<WebElement> headerList=eleUtil.getElements(header);
		List<String> headerValList=new ArrayList<String>();
		for(WebElement e:headerList)
		{
			String text=e.getText();
			headerValList.add(text);			
		}
		log.info("Acc page Headers are: "+headerValList);
		return headerValList;
	}
	
	public SearchResultsPage doSearch(String searchKey)
	{
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}
}
