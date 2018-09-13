package com.thamiz.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {

	public static Properties getConfiguration(String config) throws IOException {

		//Properties prop = new Properties();
		//prop.load(ConfigUtils.class.getResourceAsStream(config + ".properties"));

		
		
		final Properties prop = new Properties();
		try (final InputStream stream =
				ConfigUtils.class.getResourceAsStream("/"+config + ".properties")) {
			prop.load(stream);
		    /* or properties.loadFromXML(...) */
		}
		return prop;
		
	}
}
