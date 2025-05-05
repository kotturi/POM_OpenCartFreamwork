package com.qa.openCart.test;

import static com.qa.openCart.constants.AppConstants.*;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.openCart.base.BaseTest;
import com.qa.openCart.constants.AppConstants;

public class AccountPageTest extends BaseTest {

	// BT--BC
	@BeforeClass
	public void accPageSetup() throws InterruptedException {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	

	@Test
	public void getAccPageTitleTest() {
		Assert.assertEquals(accPage.getAccPageTitle(), HOME_PAGE_TITILE);
	}

	@Test
	public void getAccPageURLTest() {
		Assert.assertTrue(accPage.getAccPageURL().contains(HOME_PAGE_FRACTION_URL));
	}
	
	@Test
	public void accPageHeaderTest()
	{
		List<String> actHeaderList=accPage.accPageHeaders();
		Assert.assertEquals(actHeaderList, expectedAccPageHeaderList);
	}
	

	
}
