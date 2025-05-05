package com.qa.openCart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openCart.utils.ElementUtil;
import static  com.qa.openCart.constants.AppConstants.*;
public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private final By resultsProduct= By.cssSelector("div.product-thumb");
	
	public static final Logger log=LogManager.getLogger(SearchResultsPage.class);
 	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil= new ElementUtil(driver);
		
	}
	
	public int getResultsProductCount()
	{
	int searchCount=eleUtil.waitForAllElementsVisible(resultsProduct, DEFAULT_TIMEOUT).size();	
	log.info("Total no of Search products: "+searchCount);
	return searchCount;
	}
	
	public productInfoPage selectProduct(String productName)
	{
		log.info("Product Name:" +productName);
		eleUtil.doClick(By.linkText(productName));
		return new productInfoPage(driver);
	} 
}
	