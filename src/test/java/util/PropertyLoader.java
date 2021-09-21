package util;

import constants.Constants;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
public class PropertyLoader {
    private static Properties properties=null;
    private static Logger logger = Logger.getLogger(PropertyLoader.class);
    private static Properties loadProperties()
    {
        //Load the properties environment wise which is passed as config.location argument
        try {
            String configLocation = System.getProperty(Constants.CONFIG_LOCATION);
            //PropertiesConfigurator is used to configure logger from properties file
            String log4jconfig = System.getProperty(Constants.LOG4J_FILE);
            if(null !=log4jconfig)
            {
                PropertyConfigurator.configure(log4jconfig);
            }

            if (null != configLocation) {

                FileInputStream fileInputStream = new FileInputStream(configLocation);

                properties = new Properties();
                properties.load(fileInputStream);
            }
        }catch (FileNotFoundException e)
        {
           //Log exeception here
            logger.error("File Not Found Exception while loading properties :"+e.getMessage());
        } catch (IOException e) {
            logger.error("IO Exception while loading properties :"+e.getMessage());
        }
        return properties;
    }
    public static Properties getConfigReader()
    {
        if(null== properties)
        {

            //load the properties if properties object null
            loadProperties();
            logger.info("Completed Loading the Properties");
        }
        return properties;
    }
}
