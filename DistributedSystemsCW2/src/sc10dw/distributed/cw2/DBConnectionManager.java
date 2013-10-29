package sc10dw.distributed.cw2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author sc10dw
 * Encapsulates logic for connecting to a JDBC database.
 */
public class DBConnectionManager {

	/**
	 * Filename (relative path) to the properties file
	 * which contains the necessary information to connect
	 * to the desired database.
	 */
	private static String propertiesFilename = "jdbc.properties";
	
	/**
	 * Establish connection to database specified in JDBC propertes file.
	 * @return JDBC Connection object
	 * @throws IOException
	 * @throws SQLException
	 */
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
