package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static String getConnString(String propFileName) {
        String connString = null;
        try (FileInputStream fis = new FileInputStream(propFileName)) {
            Properties props = new Properties();
            props.load(fis);
            connString = props.getProperty("protocol") + "//" + props.getProperty("host") + ":" +
                    props.getProperty("port") + "/" + props.getProperty("database") +
                    "?user=" + props.getProperty("user") + "&password=" + props.getProperty("password") +
                    "&driver=" + props.getProperty("driver");
        } catch (FileNotFoundException ex) {
            System.out.println("The file name is not existed..");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error is occured while loading");
            ex.printStackTrace();
        }
        return connString;
    }
}

