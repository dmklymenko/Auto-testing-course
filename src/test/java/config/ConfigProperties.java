package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
	    private static FileInputStream fileInputStream;
	    private static  Properties CONFIG_PROPERTIES;
	    private static Properties EMAIL_PROPERTIES;

	    static {
	    	// For CONFIG_PROPERTIES
	    	String configPath = "src/test/java/config/config.properties";
	        try {
	            fileInputStream = new FileInputStream(configPath);
	            CONFIG_PROPERTIES = new Properties();
	            CONFIG_PROPERTIES.load(fileInputStream);
	        }
	        catch (FileNotFoundException e) {
	        	e.printStackTrace();
			}
	        catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (fileInputStream != null)
	                try {
	                    fileInputStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	        }
	        
	        // For EMAIL_PROPERTIES
	        String emailPath = "src/test/java/config/email.properties";
	        try {
	            fileInputStream = new FileInputStream(emailPath);
	            EMAIL_PROPERTIES = new Properties();
	            EMAIL_PROPERTIES.load(fileInputStream);
	        }
	        catch (FileNotFoundException e) {
	        	System.out.println("For correct work, please, create file 'email.properties' using path --> " + emailPath + "\n And fill it with valid data: \n loginEmail=YOUR@EMAIL \n password=YOUR_PASSWORD");
	        	e.printStackTrace();
			}
	        catch (IOException e) {
	            e.printStackTrace();
	        } 
	        finally {
	            if (fileInputStream != null)
	                try {
	                    fileInputStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	        }
	    }

	    public static String getConfigProperty(String key) {
	        return CONFIG_PROPERTIES.getProperty(key);
	    }
	    
	    public static String getEmailProperty(String key) {
	    	return EMAIL_PROPERTIES.getProperty(key);
	    }

}
