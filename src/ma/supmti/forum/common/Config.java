package ma.supmti.forum.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	private static final Config instance = new Config();
	
	private Properties config = null;
	
	public static Config getInstance() {
		return instance;
	}
	
	private Properties getConfig() {
		if(config == null) {
			config = new Properties();
			FileInputStream in = null;
			try {
				in = new FileInputStream("./conf/config.ini");
				config.load(in);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return config;
	}
	
	public String getResource(String key) {
		return getConfig().getProperty(key);
	}
	
}
