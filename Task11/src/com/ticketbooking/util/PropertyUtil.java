package com.ticketbooking.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
	
	private static Properties props=null;
	static {
		props=getDbProperties();
	}
	
	private static Properties getDbProperties() {
		Properties props = new  Properties();
		try {
			props.load(new FileReader(HexaConstants.DB_FILE_NAME));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return props;
	}
	
	public static String getDriver() {
		return props.getProperty(HexaConstants.DB_DRIVER);
	}
	
	public static String getDbURL() {
		return props.getProperty(HexaConstants.DB_URL);
	}
	
	public static Properties getProps() {
		return props;
	}

}
