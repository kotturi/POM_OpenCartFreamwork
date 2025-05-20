 package com.qa.openCart.constants;

import java.util.List;

public class AppConstants {

	public static final int DEFAULT_TIMEOUT=5;
	public static final int MEDIUM_DEFAULT_TIMEOUT=10;
	public static final int LONG_DEFAULT_TIMEOUT=5;
	
	public static final String LOGIN_PAGE_TITILE="Account Login11";
	public static final String HOME_PAGE_TITILE="My Account";
	public static final String LOGIN_PAGE_FRACTION_URL="route=account/login";
	public static final String HOME_PAGE_FRACTION_URL="route=account/account";
	
	public static List<String> expectedAccPageHeaderList= List.of("My Account",
																	"My Orders",
																	"My Affiliate Account",
																	"Newsletter");

	public static final String REGISTER_PAGE_TITILE= "Register Account";
	public static final String REGISTER_PAGE__URL="route=account/register";
	public static final String REGISTER_SUCCESS__MESSG="Your Account Has Been Created";
	public static final String REGISTER_SHEET_NAME="register";
}
