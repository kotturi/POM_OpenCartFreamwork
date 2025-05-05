package com.qa.openCart.test;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.openCart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productIfoSetup() throws InterruptedException {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] { { "macbook", "MacBook Pro" }, { "imac", "iMac" } };
	}

	@Test(dataProvider = "getProductTestData")
	public void productHeaderTest(String searchKey, String productName) {
		searchResultPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(productName);
		String actHeader = productInfoPage.getProductHearder();
		Assert.assertEquals(actHeader, productName);
	}

	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] { { "macbook", "MacBook Pro", 3 }, { "imac", "iMac", 2 } };
	}

	@Test(dataProvider = "getProductImagesTestData")
	public void productImageCountTest(String searchKey, String productName, int expectedimageCount) {
		searchResultPage = accPage.doSearch(searchKey);
		searchResultPage.selectProduct(productName);
		int actImageCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actImageCount, expectedimageCount);
	}

	@Test
	public void productInfoTest() {
		searchResultPage = accPage.doSearch("macbook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String, String> actualProductDetailsMap = productInfoPage.getProductDetailsMap();

		SoftAssert sa = new SoftAssert();
		// Assert.assertEquals(actualProductDetailsMap.get("productHeader"),"MacBook
		// Pro");
		// Assert.assertEquals(actualProductDetailsMap.get("productimages"),"3");
		sa.assertEquals(actualProductDetailsMap.get("Brand"), "Apple");
		sa.assertEquals(actualProductDetailsMap.get("Product Code"), "Product 18");
		// Assert.assertEquals(actualProductDetailsMap.get("Reward Points"),"800");
		sa.assertEquals(actualProductDetailsMap.get("Availability"), "Out Of Stock");
		sa.assertEquals(actualProductDetailsMap.get("productPrice"), "$2,000.00");
		sa.assertEquals(actualProductDetailsMap.get("exTaxPrice"), "$2,000.00");

		sa.assertAll();
	}

}
