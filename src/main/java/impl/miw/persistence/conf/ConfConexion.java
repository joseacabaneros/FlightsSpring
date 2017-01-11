package impl.miw.persistence.conf;

import java.io.IOException;
import java.util.Properties;

public class ConfConexion {

	private static final String CONF_FILE = "conexion.properties";

	private static ConfConexion instance;
	private Properties properties;

	
	private ConfConexion() {
		properties = new Properties();
		try {
			properties.load(Conf.class.getClassLoader().getResourceAsStream(
					CONF_FILE));
		} catch (IOException e) {
			throw new RuntimeException("Propeties file can not be loaded", e);
		}
	}

	
	public static String get(String key) {
		return getInstance().getProperty(key);
	}

	
	private String getProperty(String key) {
		String value = properties.getProperty(key);
		if (value == null) {
			throw new RuntimeException("Property not found in config file");
		}
		return value;
	}

	private static ConfConexion getInstance() {
		if (instance == null) {
			instance = new ConfConexion();
		}
		return instance;
	}
	
}
