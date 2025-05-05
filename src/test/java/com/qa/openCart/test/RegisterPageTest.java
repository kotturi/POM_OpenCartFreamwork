package com.qa.openCart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.openCart.base.BaseTest;
import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void registerSetUp()
	{
		registerPage=loginPage.navigateToRegisterPage();
	}
	@DataProvider
	public Object[][] getUserRegTestData()
	{
		return new	Object[][] {
			{"ab1", "ab2", "1234567890", "qwe@123", "yes"},
			{"ab1", "ab2", "1234567890", "qwe@123", "no"}
		};
	}
	
	//MSexcel: .xlsx :read using apache Api
	@DataProvider
	public Object[][] getUserRegData()
	{
		Object regData[][] =ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}

	//@Test(dataProvider="getUserRegTestData")
	
	@Test(dataProvider="getUserRegData")
	public void userRegisterTest(String firstName,String lastName,
			String telephone,String password,String subscribe)
	{
		Assert.assertTrue(registerPage.userRegistration(firstName,lastName,
				 telephone, password, subscribe));
	}
	
	
	

}
