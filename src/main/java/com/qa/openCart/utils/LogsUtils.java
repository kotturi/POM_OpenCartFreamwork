package com.qa.openCart.utils;

import org.apache.logging.log4j.*;
import com.qa.openCart.factory.DriverFactory;

public class LogsUtils {
	public static  Logger log=LogManager.getLogger(DriverFactory.class);
	
	public static void Info(String mesg)
	{
		log.info(mesg);
	}
}
