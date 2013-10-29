package sc10dw.distributed.cw2;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sc10dw
 * Encapsulates database retrieval of employees 
 * TODO
 */
public class DBEmployeeRetriever {

	/**
	 * Construct instance of the employee retrieve which 
	 * @param dbConnection Connection to database containing employee information
	 */
	public DBEmployeeRetriever(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	/**
	 * Retrieve all employees in database.
	 * @return List of all employees in database
	 * @throws RemoteException if there's a problem instantiating Employee instances
	 */
	public List<Employee> allEmployees() throws RemoteException {
		Statement statement = null;
		try {
			statement = dbConnection.createStatement();
			ResultSet results = statement.executeQuery(ALL_EMPLOYEES_QUERY);
			return processEmployeeResults(results);
		} catch (SQLException e) {
			System.err.println("DB error: could not retrieve employee data");
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// Just return empty list if a database error occurred
		return new ArrayList<Employee>();
	}
	
	/**
	 * @param surname Surname to use to search for employees
	 * @return List of emmployees with given surname
	 * @throws RemoteException if there's a problem instantiating Employee instances
	 */
	public List<Employee> getEmployees(String surname) throws RemoteException {
		Statement statement = null;
		try {
			// NOTE: Surname is converted entirely to uppercase here as
			// as that's how they're stored on the database
			String query = String.format(EMPLOYEES_BY_SURNAME_QUERY, surname.toUpperCase());
			statement = dbConnection.createStatement();
			ResultSet results = statement.executeQuery(query);
			return processEmployeeResults(results);
		} catch (SQLException e) {
			System.err.println("DB error: could not retrieve employee data by surname");
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return new ArrayList<Employee>();
	}

	/**
	 * @param results Result of JDBC SQL query
	 * @return List of Employee instances created from given results 
	 * @throws RemoteException if there's a problem instantiating Employee instances
	 * @throws SQLException if there is an error in querying or processing SQL data
	 */
	private List<Employee> processEmployeeResults(ResultSet results) throws RemoteException, SQLException {
		List<Employee> employees = new ArrayList<Employee>();
		while (results.next()) {
			// Retrieve each field of table for the current row
			String forename = results.getString("forename");
			String surname = results.getString("surname");
			int avgHours = results.getInt("numberHours");
			double basePay = results.getDouble("baseRate");
			// Put all this data in an Employee object and add it to the list
			Employee employeeInstance = new DBEmployee(forename, surname, avgHours, basePay);
			employees.add(employeeInstance);
		}
		return employees;
	}

	/**
	 * Connection to database containing employees.
	 */
	private Connection dbConnection;
	
	/**
	 * SQL query for selecting all employees in database.
	 */
	private final static String ALL_EMPLOYEES_QUERY = "SELECT * FROM employees";
	/**
	 * SQL query for selecting employees with a specific surname.
	 */
	private final static String EMPLOYEES_BY_SURNAME_QUERY = "SELECT * FROM employees WHERE surname = '%s'";
	
}
