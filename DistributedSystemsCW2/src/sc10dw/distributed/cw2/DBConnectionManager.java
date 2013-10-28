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

public class DBConnectionManager {

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
	
	public static void displayDatabaseMetadata(Connection dbConnection) throws SQLException {
		DatabaseMetaData dbMetadata = dbConnection.getMetaData();
		System.out.println("Database Product Name: " + dbMetadata.getDatabaseProductName());
		System.out.println("Database Product Version: " + dbMetadata.getDatabaseProductVersion());
		System.out.println("Driver Name: " + dbMetadata.getDriverName());
		System.out.println("Driver Version: " + dbMetadata.getDriverVersion());
		System.out.println("JDBC Version" + dbMetadata.getJDBCMajorVersion() + "." + dbMetadata.getJDBCMinorVersion() );		
	}
	
	public static List<String> retrieveDatabaseTables(Connection dbConnection) throws SQLException {
		// Retrieve table metadata in the form a ResultSet
		DatabaseMetaData dbMetadata = dbConnection.getMetaData();
		String[] types = { "TABLE" };
		String catalog = dbConnection.getCatalog();
		String schema = "tc30_cw1"; // name of the database we want tables of
		ResultSet tables = dbMetadata.getTables(catalog, schema, null, types);
		
		List<String> tableNames = new ArrayList<String>();
		while (tables.next()) {
			tableNames.add( tables.getString("TABLE_NAME") );
		}
		return tableNames;
	}
	
}
