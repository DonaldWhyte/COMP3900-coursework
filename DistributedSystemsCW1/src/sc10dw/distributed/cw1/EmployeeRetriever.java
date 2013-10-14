package sc10dw.distributed.cw1;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRetriever {

	public EmployeeRetriever(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public List<Employee> allEmployees() {
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
	
	public List<Employee> getEmployees(String surname) {
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
	
	public double averageNumHoursWorked() throws SQLException {
		Statement statement = dbConnection.createStatement();
		ResultSet result = statement.executeQuery(AVERAGE_HOURS_WORKED_QUERY);
		double avgHours = processAverage(result);
		statement.close();
		return avgHours;
	}	
	
	public double averagePayRate() throws SQLException {
		Statement statement = dbConnection.createStatement();
		ResultSet result = statement.executeQuery(AVERAGE_BASE_PAY_RATE_QUERY);
		double avgPayRate = processAverage(result);
		statement.close();
		return avgPayRate;		
	}
	
	private List<Employee> processEmployeeResults(ResultSet results) throws SQLException {
		List<Employee> employees = new ArrayList<Employee>();
		while (results.next()) {
			// Retrieve each field of table for the current row
			String forename = results.getString("forename");
			String surname = results.getString("surname");
			int avgHours = results.getInt("numberHours");
			double basePay = results.getDouble("baseRate");
			// Put all this data in an Employee object and add it to the list
			Employee employeeInstance = new Employee(forename, surname, avgHours, basePay);
			employees.add(employeeInstance);
		}
		return employees;
	}
	
	private double processAverage(ResultSet result) throws SQLException {
		result.next();
		return result.getDouble(1);
	}

	private Connection dbConnection;
	
	private final static String ALL_EMPLOYEES_QUERY = "SELECT * FROM employees";
	private final static String EMPLOYEES_BY_SURNAME_QUERY = "SELECT * FROM employees WHERE surname = '%s'";
	private final static String AVERAGE_HOURS_WORKED_QUERY = "SELECT AVG(numberHours) FROM employees";
	private final static String AVERAGE_BASE_PAY_RATE_QUERY = "SELECT AVG(baseRate) FROM employees";
	
}
