package impl.miw.persistence.conf;

import java.io.IOException;
import java.util.Properties;

public class Conf {

	private static final String CONF_FILE = "configuration.properties";

	private static Conf instance;
	private Properties properties;

	/**
	 * Constructor que carga el archivo .properties que contiene todas las
	 * sentencias sql necesarias para el normal funcionamiento de la aplicacion
	 */
	private Conf() {
		properties = new Properties();
		try {
			properties.load(Conf.class.getClassLoader().getResourceAsStream(
					CONF_FILE));
		} catch (IOException e) {
			throw new RuntimeException("Propeties file can not be loaded", e);
		}
	}

	/**
	 * Metodo publico que retorna el String(sentencia sql) correspondiente a la
	 * key pasada como parametro
	 * 
	 * @param key
	 *            identificador para buscar la sentencia sql en el .properties
	 * @return retorna la sentencia sql
	 */
	public static String get(String key) {
		return getInstance().getProperty(key);
	}

	/**
	 * Metodo que retorna el string sql de pa propiedad pasada por parametro
	 * 
	 * @param key
	 *            propiedad(identificador) para buscar la sql
	 * @return retorna string sql que corresponde a la key
	 */
	private String getProperty(String key) {
		String value = properties.getProperty(key);
		if (value == null) {
			throw new RuntimeException("Property not found in config file");
		}
		return value;
	}

	/**
	 * Metodo que retorna una instancia del Conf
	 * 
	 * @return retorna instancia del Conf
	 */
	private static Conf getInstance() {
		if (instance == null) {
			instance = new Conf();
		}
		return instance;
	}

}
