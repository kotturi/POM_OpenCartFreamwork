package com.qa.openCart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.openCart.base.BaseTest;
import com.qa.openCart.utils.ElementUtil;

public class SearchTest extends BaseTest {

	@BeforeClass
	public void searchSetup() throws InterruptedException {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void searchTest()
	{
		searchResultPage= accPage.doSearch("macbook");
		int actResultCount= searchResultPage.getResultsProductCount();
		Assert.assertEquals(actResultCount, 3);
	}
	
	
}
