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
		// Just return empty list if a dayabase error occurred
		return new ArrayList<Employee>();
	}
	
	public List<Employee> getEmployees(String surname) {
		Statement statement = null;
		try {
			String query = String.format(EMPLOYEES_BY_SURNAME_QUERY, surname);
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
	
	private Connection dbConnection;
	
	private final static String ALL_EMPLOYEES_QUERY = "SELECT * FROM employees";
	private final static String EMPLOYEES_BY_SURNAME_QUERY = "SELECT * FROM employees WHERE surname = '%s'";
	
}
