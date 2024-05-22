package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnUtil {

    public static Connection getConnection() {
        String propFileName = "db.properties";
        String connString = DBPropertyUtil.getConnString(propFileName);
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connString);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver is not loaded..");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: Connection could not be established..");
            e.printStackTrace();
        }
        return con;
    }
}


