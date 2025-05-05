package com.qa.openCart.pages;

import static com.qa.openCart.constants.AppConstants.DEFAULT_TIMEOUT;
import static com.qa.openCart.constants.AppConstants.MEDIUM_DEFAULT_TIMEOUT;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.openCart.utils.ElementUtil;

public class productInfoPage {

	WebDriver driver;
	ElementUtil eleUtil;
	
	private Map<String,String> productMap;
	
	protected final By productHeader=By.tagName("h1");
	protected final By productImages= By.cssSelector("li.image-additional");
	protected final By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	protected final By productPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	public static final Logger log=LogManager.getLogger(productInfoPage.class);
 	
	public productInfoPage(WebDriver driver) {
		this.driver= driver;
		eleUtil= new ElementUtil(driver);
	}
	
	public String getProductHearder()
	{
		String header= eleUtil.waitForElementVisible(productHeader, DEFAULT_TIMEOUT).getText();
		 log.info("THis is Product Header:"+header);
		 return header;
	}
	
	public int getProductImagesCount()
	{
	int imgCount=eleUtil.waitForAllElementsVisible(productImages,MEDIUM_DEFAULT_TIMEOUT).size();
	log.info("Total number of Images count:"+imgCount);
	return imgCount;
	}
	
	public Map<String, String> getProductDetailsMap()
	{
	//	productMap=new HashMap<String, String>();/HashMap isNonorder collection
		//O/p:Full product detaisl: {Brand=Apple, Availability=Out Of Stock, productHeader=MacBook Pro, productimages=3, Product Code=Product 18, Reward Points=800, productPrice=$2,000.00, exTaxPrice=$2,000.00}
//LinkedHashMap is order based
	//	productMap=new LinkedHashMap<String, String>();
		//Full product detaisl: {productHeader=MacBook Pro, productimages=3, Brand=Apple, Product Code=Product 18, Reward Points=800, Availability=Out Of Stock, productPrice=$2,000.00, exTaxPrice=$2,000.00}
//Sorted order
		productMap=new TreeMap<String, String>();
//Full product detaisl: {Availability=Out Of Stock, Brand=Apple, Product Code=Product 18, Reward Points=800, exTaxPrice=$2,000.00, productHeader=MacBook Pro, productPrice=$2,000.00, productimages=3}
 		
		productMap.put("productHeader", getProductHearder());
		productMap.put("productimages", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		log.info("Full product detaisl: "+productMap);
		return productMap;
	}

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock
	public void getProductMetaData()
	{
		List<WebElement> MetaList=eleUtil.waitForAllElementsVisible(productMetaData, DEFAULT_TIMEOUT);
		for(WebElement e:MetaList)
		{
			String metaData=e.getText();
			String meta[]=metaData.split(":");
			String metaKey=meta[0].trim();
			String metaValue=meta[1].trim();
			productMap.put(metaKey, metaValue);
		}
		}
	
//	$2,000.00
//	Ex Tax: $2,000.00
	public void getProductPriceData()
	{
		List<WebElement> priceList=eleUtil.waitForAllElementsVisible(productPriceData, DEFAULT_TIMEOUT);
		String productPrice=priceList.get(0).getText();
		String exTaxPrice=priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice", productPrice);
		productMap.put("exTaxPrice", exTaxPrice);
	}
	
}
