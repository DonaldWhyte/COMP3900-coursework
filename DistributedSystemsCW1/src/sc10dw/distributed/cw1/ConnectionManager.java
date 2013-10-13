package sc10dw.distributed.cw1;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

	private static String propertiesFilename = "jdbc.properties";
	
	public static Connection getConnection() throws IOException, SQLException
	{
		// Load properties
		FileInputStream in = new FileInputStream(propertiesFilename);
		Properties props = new Properties();
		props.load(in);

		// Define JDBC driver
		String drivers = props.getProperty("jdbc.drivers");
		if (drivers != null)
			System.setProperty("jdbc.drivers", drivers);
		// Setting standard system property jdbc.drivers
		// is an alternative to loading the driver manually
		// by calling Class.forName()

		// Obtain access parameters and use them to create connection

		String url = props.getProperty("jdbc.url");
		String user = props.getProperty("jdbc.user");
		String password = props.getProperty("jdbc.password");

		return DriverManager.getConnection(url, user, password);
  }	
	
}
