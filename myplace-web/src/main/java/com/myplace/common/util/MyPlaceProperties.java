package com.myplace.common.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class MyPlaceProperties {

	private static final Logger logger = Logger.getLogger(MyPlaceProperties.class.getName());
	private static final String PROPERTIES_FILE = "mpplaceweb";

	private static MyPlaceProperties myplaceProperties = new MyPlaceProperties();

	private ResourceBundle resourceBundle;

	private MyPlaceProperties() {
		try {
			resourceBundle = ResourceBundle.getBundle(PROPERTIES_FILE);
		} catch (Exception e) {
			logger.error("Properties file '" + PROPERTIES_FILE + ".properties' is missing...", e);
			throw new RuntimeException(e);
		}
	}

	public static MyPlaceProperties getInstance() {
		return myplaceProperties;
	}

	/**
	 * Returns the value for key in properties file and if key is not found then null is returned.
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return getProperty(key, null);
	}

	/**
	 * If key is not found in properties file then defaultValue is returned
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String getProperty(String key, String defaultValue) {
		if (key == null) {
			throw new RuntimeException("Key can't be null");
		}
		String value = null;
		try {
			value = resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			logger.warn("There is no property for key: '" + key + "'. Returning default value: '" + defaultValue + "'");
			return defaultValue;
		}
		return value;
	}

}
