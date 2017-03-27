/**
 * 
 */
package fr.epita.iam.services;

import java.util.Properties;

/**
 * @author tbrou
 *
 */
public class Configuration {
	
	
	private static Configuration instance;
	
	private String jdbcConnectionString;
	private String user;
	private String pwd;
	
	private Properties props;
	
	
	/**
	 * 
	 */
	private Configuration() {
		// TODO init properties
	}
	
	public static Configuration getInstance(){
		if (instance == null){
			instance = new Configuration();
		}
		return instance;
	}
	
	/**
	 * @return the jdbcConnectionString
	 */
	public String getJdbcConnectionString() {
		return props.getProperty("jdbc.connection.string");
	}


	/**
	 * @return the user
	 */
	public String getUser() {
		return props.getProperty("jdbc.connection.user");
	}

	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return props.getProperty("jdbc.connection.pwd");
	}
	


}
